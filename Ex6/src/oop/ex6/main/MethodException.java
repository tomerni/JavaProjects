package oop.ex6.main;

/**
 * Father class for method exceptions
 */
public class MethodException extends Exception{

	/* for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @param message indicative error message
	 */
	MethodException(String message) {
		super(message);
	}
}
