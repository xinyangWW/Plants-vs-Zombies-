package tp1.p2.control.exceptions;

import tp1.p2.view.Messages;

public class InvalidGameObjectException extends CommandExecuteException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidGameObjectException() {
		super(Messages.INVALID_GAME_OBJECT);
	}

	public InvalidGameObjectException(String message) {
		super(message);
	}
	
	public InvalidGameObjectException(Throwable cause) {
		super(cause);
	}

	public InvalidGameObjectException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
