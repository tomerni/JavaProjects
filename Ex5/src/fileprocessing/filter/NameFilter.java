package fileprocessing.filter;

import java.io.File;

public class NameFilter implements Filter {

	private final String filterName;

	private final String[] splitString;

	private final boolean flag;

	public NameFilter(String filterName, String[] splitString, boolean flag) {
		this.filterName = filterName;
		this.splitString = splitString;
		this.flag = flag;
	}

	@Override
	public boolean accept(File pathname) {
		ThreeWayMatcher<String, String, Boolean> filter = filterFinder();
		if (flag) {
			return filter.apply(pathname.getName(), splitString[1]) && pathname.isFile();
		} else {
			return !filter.apply(pathname.getName(), splitString[1]) && pathname.isFile();
		}
	}

	@Override
	public void validate() {

	}

	private ThreeWayMatcher<String, String, Boolean> filterFinder() {
		switch (filterName) {
		case ("file"):
			return String::equals;
		case ("contains"):
			return String::contains;
		case ("prefix"):
			return String::startsWith;
		default:
			return String::endsWith;
		}
	}
}
