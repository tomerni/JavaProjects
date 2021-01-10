package oop.ex6.variables;

/**
 * implements the VariableStructureException exception that extends VariableException
 */
public class VariableStructureException extends VariableException{

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: Invalid variable assignment or declaration line " +
												"structure";

	/**
	 * constructor
	 */
	VariableStructureException(){
		super(ERROR_MESSAGE);
	}

}
