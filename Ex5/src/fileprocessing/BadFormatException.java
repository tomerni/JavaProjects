package fileprocessing;

public class BadFormatException extends Exception {

	private	static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "ERROR: bad format of the command file \n";

	public BadFormatException() {
		super(ERROR_MESSAGE);
	}
}
