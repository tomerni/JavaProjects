package oop.ex6.main;

/**
 * implements the InvalidMethodStructureException exception that extends StructureException
 */
public class InvalidMethodStructureException extends StructureException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: Invalid method structure";

	/**
	 * constructor
	 */
	InvalidMethodStructureException() {
		super(ERROR_MESSAGE);
	}
}
