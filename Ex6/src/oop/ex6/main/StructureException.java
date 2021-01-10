package oop.ex6.main;

/**
 * Father class for structure exceptions
 */
public class StructureException extends Exception{

	/* for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @param message indicative error message
	 */
	StructureException(String message) {
		super(message);
	}
}
