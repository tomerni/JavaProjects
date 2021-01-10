package oop.ex6.main;

/**
 * implements the IllegalArgumentsException exception that extends MethodException
 */
public class IllegalArgumentsException extends MethodException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: Illegal number of arguments";

	/**
	 * constructor
	 */
	IllegalArgumentsException() {
		super(ERROR_MESSAGE);
	}

}
