package oop.ex6.variables;

/**
 * implements the IllegalVarNameException exception that extends VariableException
 */
public class IllegalVarNameException extends VariableException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: illegal variable name";

	/**
	 * constructor
	 */
	public IllegalVarNameException() {
		super(ERROR_MESSAGE);
	}
}
