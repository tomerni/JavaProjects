package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class NameFilter implements Filter {

	private final String filterName;

	public NameFilter(String filterName) {
		this.filterName = filterName;
	}

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag) {
		ArrayList<File> results = new ArrayList<File>();
		ThreeWayMatcher<String, String, Boolean> filter = filterFinder();
		for (File f : files) {
			if (flag) {
				if (filter.apply(f.getName(), splitString[1])) {
					results.add(f);
				}
			}
			else {
				if (!filter.apply(f.getName(), splitString[1])) {
					results.add(f);
				}
			}
		}
		return results;
	}

	private ThreeWayMatcher<String, String, Boolean> filterFinder() {
		switch (filterName) {
		case ("file"):
			return String::equals;
		case ("contains"):
			return String::contains;
		case ("prefix"):
			return String::startsWith;
		case ("suffix"):
			return String::endsWith;
		}
		return null;
	}
}
