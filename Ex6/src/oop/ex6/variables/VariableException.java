package oop.ex6.variables;

/**
 * Father class for variable exceptions
 */
public class VariableException extends Exception{

	// for serialization
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @param message indicative error message
	 */
	VariableException(String message) {
		super(message);
	}
}
