package oop.ex6.variables;

/**
 * implements the FinalAssignmentException exception that extends VariableException
 */
public class FinalAssignmentException extends VariableException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: assignment to final variable";

	/**
	 * constructor
	 */
	FinalAssignmentException() {
		super(ERROR_MESSAGE);
	}
}
