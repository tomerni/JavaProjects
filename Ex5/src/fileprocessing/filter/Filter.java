package fileprocessing.filter;

import java.io.FileFilter;

/**
 * filter interface that extends the FileFilter interface
 */
public interface Filter extends FileFilter {

    /**
     * validates that the filter is valid
     * @throws BadFilterException exception if the filter is wrong
     */
    void validate() throws BadFilterException;
}
