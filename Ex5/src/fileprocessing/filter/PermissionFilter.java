package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Function;

public class PermissionFilter implements Filter{

	private final String filterName;

	public PermissionFilter(String filterName) {
		this.filterName = filterName;
	}

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag)
			throws BadFilterException {
		Function<File, Boolean> filter = filterFinder();
		ArrayList<File> results = new ArrayList<>();
		if (!(splitString[1].equals("NO") || splitString[1].equals("YES"))) {
			throw new BadFilterException();
		}
		if (splitString[1].equals("NO")) {
			flag = !flag;
		}
		for (File f : files) {
			if (flag) {
				if (filter.apply(f)) {
					results.add(f);
				}
			}
			else {
				if (!filter.apply(f)) {
					results.add(f);
				}
			}
		}
		return results;
	}

	private Function<File, Boolean> filterFinder() {
		switch (filterName) {
		case "writable":
			return File::canWrite;
		case "executable":
			return File::canExecute;
		default:
			return File::isHidden;
		}
	}
}
