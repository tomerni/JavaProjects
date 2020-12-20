package fileprocessing;

import fileprocessing.filter.BadFilterException;
import fileprocessing.filter.FilterFiles;
import fileprocessing.order.BadOrderNameException;
import fileprocessing.order.OrderFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class SectionProcessor {

	private final static String WARNING_MESSAGE = "Warning in line %d";

	private static boolean filterException = false;

	private static boolean orderException = false;

	public static ArrayList<String> processSection(int lineNumber, String path, String filter, String order) {
		orderException = false;
		filterException = false;
		File[] filteredFiles = processFilter(path, filter);
		ArrayList<File> filteredFilesArr = new ArrayList<>(Arrays.asList(filteredFiles));
		ArrayList<String> orderedFiles = processOrder(filteredFilesArr, order);
		if (orderException) {
			orderedFiles.add(0, String.format(WARNING_MESSAGE, lineNumber + 2));
		}
		if (filterException) {
			orderedFiles.add(0, String.format(WARNING_MESSAGE, lineNumber));
		}
		return orderedFiles;
	}

	private static File[] processFilter(String path, String filter) {
		try {
			return FilterFiles.filter(path, filter);
		} catch (BadFilterException e) {
			filterException = true;
			File directory = new File(path);
			return directory.listFiles(File::isFile);
		}
	}

	private static ArrayList<String> processOrder(ArrayList<File> filtered, String order) {
		try {
			return OrderFiles.order(filtered, order);
		} catch (BadOrderNameException e) {
			orderException = true;
			try {
				return OrderFiles.order(filtered, "abs");
			} catch (BadOrderNameException f) { // CAN'T HAPPEN
				return new ArrayList<>();
			}
		}
	}
}
