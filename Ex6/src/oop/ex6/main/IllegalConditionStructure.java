package oop.ex6.main;

/**
 * implements the IllegalConditionStructure exception that extends StructureException
 */
public class IllegalConditionStructure extends StructureException {

	// for serialization
	private static final long serialVersionUID = 1L;

	// error message
	private static final String ERROR_MESSAGE = "ERROR: Illegal condition structure";

	/**
	 * constructor
	 */
	IllegalConditionStructure() {
		super(ERROR_MESSAGE);
	}
}
