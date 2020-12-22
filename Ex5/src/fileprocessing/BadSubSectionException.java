package fileprocessing;

/**
 * BadSubSectionException class
 */
public class BadSubSectionException extends Exception {

	// for serialization
	private	static final long serialVersionUID = 1L;

	// the error message
	private static final String ERROR_MESSAGE = "ERROR: bad sub-section name \n";

	/**
	 * Constructor
	 */
	public BadSubSectionException() {
		super(ERROR_MESSAGE);
	}
}
