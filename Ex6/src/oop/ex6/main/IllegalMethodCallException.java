package oop.ex6.main;

/**
 * implements the IllegalMethodCallException exception that extends MethodException
 */
public class IllegalMethodCallException extends MethodException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: Invalid method name";

	/**
	 * constructor
	 */
	IllegalMethodCallException() {
		super(ERROR_MESSAGE);
	}
}
