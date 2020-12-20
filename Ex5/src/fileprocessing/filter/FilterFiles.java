package fileprocessing.filter;

import java.io.File;

public abstract class FilterFiles {

	public static File[] filter(String directoryPath, String filterLine) throws BadFilterException {
		String[] splitString = filterLine.split("#");
		String typeOfFilter = splitString[0];
		boolean flag = true;
		if (splitString[splitString.length - 1].equals("NOT")) {
			flag = false;
		}
		Filter curFilter = FilterFactory.createFilter(typeOfFilter, splitString, flag);
		curFilter.validate(); // CAN THROW EXCEPTION
		File directory = new File(directoryPath);
		return directory.listFiles(curFilter);
	}
}
