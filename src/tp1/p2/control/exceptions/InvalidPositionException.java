package tp1.p2.control.exceptions;

import tp1.p2.view.Messages;

public class InvalidPositionException extends CommandExecuteException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPositionException(int col, int row) {
		super(String.format(Messages.INVALID_POSITION, Integer.toString(col) ,Integer.toString(row)));
	}
	
	public InvalidPositionException(String col, String row) {
		super(String.format(Messages.INVALID_POSITION, col ,row));
	}

	public InvalidPositionException(String message) {
		super(message);
	}

	public InvalidPositionException(Throwable cause) {
		super(cause);
	}

	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
