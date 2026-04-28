package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.*;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}


	@Override
	public boolean execute(GameWorld game) throws GameException {
		// TODO add your code here
		if(!PlantFactory.isValidPlant(plantName)) throw new InvalidGameObjectException();

		if(col < 0 || col >= GameWorld.NUM_COLS || row < 0 || row >= GameWorld.NUM_ROWS) {
    		throw new InvalidPositionException(col, row);
    	}
		
		game.checkValidPlantPosition(col, row);
		Plant plant = PlantFactory.spawnPlant(plantName, game, col, row);
		if(plant != null) {
			if(consumeCoins) game.tryToBuy(plant.getCost());
			game.addItem(plant);
			game.update();
		}
		else throw new InvalidGameObjectException();
		
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		// TODO add your code here
		if(parameters.length < 3) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		
		try {
    		plantName = parameters[0];
    		col = Integer.parseInt(parameters[1]);
    		row = Integer.parseInt(parameters[2]);
    	}
    	catch(Exception e) {
    		throw new CommandParseException(String.format(Messages.INVALID_POSITION, parameters[1], parameters[2]));
    	}
		
		return this;
	}

}


