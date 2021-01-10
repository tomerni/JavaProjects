package oop.ex6.variables;

/**
 * implements the UsageOfNonAssignedException exception that extends VariableException
 */
public class UsageOfNonAssignedException extends VariableException{

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: usage of a non-assigned variable";

	/**
	 * constructor
	 */
	UsageOfNonAssignedException(){
		super(ERROR_MESSAGE);
	}
}
