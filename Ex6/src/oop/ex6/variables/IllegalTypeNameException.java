package oop.ex6.variables;

/**
 * implements the IllegalTypeNameException exception that extends VariableException
 */
public class IllegalTypeNameException extends VariableException{

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: illegal type name";

	/**
	 * constructor
	 */
	IllegalTypeNameException(){
		super(ERROR_MESSAGE);
	}
}
