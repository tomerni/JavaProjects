package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterFiles {

	private final List<String> validFilters = Arrays.asList("greater_than", "between", "smaller_than",
															"file", "contains", "prefix", "suffix",
															"writable", "executable", "hidden", "all");

	private final ArrayList<File> files;

	private final String filterLine;


	// CONSTRUCTOR

	public FilterFiles(ArrayList<File> files, String filterLine) {
		this.files = files;
		this.filterLine = filterLine;
	}
}
