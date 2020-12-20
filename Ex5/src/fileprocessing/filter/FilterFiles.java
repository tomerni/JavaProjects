package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class FilterFiles {

	private final ArrayList<File> files;

	private final String filterLine;


	// CONSTRUCTOR

	public FilterFiles(ArrayList<File> files, String filterLine) {
		this.files = files;
		this.filterLine = filterLine;
	}

	public ArrayList<File> filter() throws BadFilterException {
		String[] splitString = filterLine.split("#");
		String typeOfFilter = splitString[0];
		if (typeOfFilter.equals("all")) {
			return files;
		}
		Filter curFilter = FilterFactory.createFilter(typeOfFilter);
		if (splitString[splitString.length - 1].equals("NOT")) {
			return curFilter.filter(splitString, files, false);
		} else {
			return curFilter.filter(splitString, files, true);
		}
	}
}
