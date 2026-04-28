package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		// TODO add your code here
		
		if(level != null) game.reset(level, seed);
		else game.reset();

		System.out.println(String.format(Messages.CONFIGURED_LEVEL, game.getLevelname()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, game.getSeed()));
		
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException{
		// TODO add your code here
		if(parameters.length != 0) {
			Level l = Level.valueOfIgnoreCase(parameters[0]);
			if (l == null) {
				throw new CommandParseException(Messages.INVALID_COMMAND);
				//return null;
			}
			
			try {
				if (parameters.length == 2) {
					long s = Long.parseLong(parameters[1]);
					return new ResetCommand(l,s);
				}
			} catch (NumberFormatException nfe) {
				throw new CommandParseException(nfe.getMessage());
				//System.out.println(String.format(Messages.SEED_NOT_A_NUMBER_ERROR, parameters[1]));
				//return null;
			}
		}
		
		return this;
	}

}
