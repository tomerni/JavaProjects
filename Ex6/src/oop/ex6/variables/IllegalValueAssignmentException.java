package oop.ex6.variables;

/**
 * implements the IllegalValueAssignmentException exception that extends VariableException
 */
public class IllegalValueAssignmentException extends VariableException{

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: illegal value assignment";

	/**
	 * constructor
	 */
	IllegalValueAssignmentException() {
		super(ERROR_MESSAGE);
	}
}
