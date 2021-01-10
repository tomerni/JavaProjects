package oop.ex6.main;

/**
 * implements the InvalidLineStructureException exception that extends StructureException
 */
public class InvalidLineStructureException extends StructureException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: Invalid line";

	/**
	 * constructor
	 */
	InvalidLineStructureException() {
		super(ERROR_MESSAGE);
	}

}
