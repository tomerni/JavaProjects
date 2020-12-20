package fileprocessing.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public interface Filter extends FileFilter {

	void validate() throws BadFilterException;
}
