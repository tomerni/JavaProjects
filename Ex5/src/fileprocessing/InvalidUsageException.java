package fileprocessing;

public class InvalidUsageException extends Exception {

	private	static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "ERROR: invalid usage \n";

	public InvalidUsageException() {
		super(ERROR_MESSAGE);
	}
}
