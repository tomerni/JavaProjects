package oop.ex6.variables;

/**
 * implements the DuplicateVariableException exception that extends VariableException
 */
public class DuplicateVariableException extends VariableException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: duplicate variable name in the same scope";

	/**
	 * Constructor
	 */
	DuplicateVariableException() {
		super(ERROR_MESSAGE);
	}
}
