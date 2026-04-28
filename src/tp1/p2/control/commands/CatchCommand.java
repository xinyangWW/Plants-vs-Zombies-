package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if(!caughtSunThisCycle) {
			game.tryToCatchObject(col, row);
			caughtSunThisCycle = true;
			return true;
		}
		throw new GameException(Messages.SUN_ALREADY_CAUGHT);
	}

	@Override
	public Command create(String[] parameters) throws GameException{
		if(parameters.length < 2) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
            //return null;
		}
		
		try {
    		col = Integer.parseInt(parameters[0]);
    		row = Integer.parseInt(parameters[1]);
    	}
    	catch(Exception e) {
    		throw new CommandParseException(String.format(Messages.INVALID_POSITION, parameters[0], parameters[1]));
    		//return null;
    	}
		
		return new CatchCommand(col, row);
	}

}
