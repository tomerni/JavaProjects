package fileprocessing;

public class BadSubSectionException extends Exception {

	private	static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "ERROR: bad sub-section name \n";

	public BadSubSectionException() {
		super(ERROR_MESSAGE);
	}
}
