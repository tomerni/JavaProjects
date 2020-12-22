package fileprocessing;

/**
 * InvalidUsageException class
 */
public class InvalidUsageException extends Exception {

    // for serialization
    private static final long serialVersionUID = 1L;

    // the error message
    private static final String ERROR_MESSAGE = "ERROR: invalid usage \n";

    /**
     * Constructor
     */
    public InvalidUsageException() {
        super(ERROR_MESSAGE);
    }
}
