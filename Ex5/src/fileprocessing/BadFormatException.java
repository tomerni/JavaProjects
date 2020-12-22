package fileprocessing;

/**
 * BadFormatException class
 */
public class BadFormatException extends Exception {

	// for serialization
	private	static final long serialVersionUID = 1L;

	// the error message
	private static final String ERROR_MESSAGE = "ERROR: bad format of the command file \n";

	/**
	 * Constructor
	 */
	public BadFormatException() {
		super(ERROR_MESSAGE);
	}
}
