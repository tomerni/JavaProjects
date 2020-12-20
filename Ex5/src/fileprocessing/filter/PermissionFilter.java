package fileprocessing.filter;

import java.io.File;
import java.util.function.Function;

public class PermissionFilter implements Filter{

	private final String filterName;

	private final String[] splitString;

	private boolean flag;

	public PermissionFilter(String filterName, String[] splitString, boolean flag) {
		this.filterName = filterName;
		this.splitString = splitString;
		this.flag = flag;
	}

	@Override
	public void validate() throws BadFilterException {
		if (!(splitString[1].equals("NO") || splitString[1].equals("YES"))) {
			throw new BadFilterException();
		}
	}

	@Override
	public boolean accept(File pathname) {
		Function<File, Boolean> filter = filterFinder();
		if (splitString[1].equals("NO")) {
			flag = !flag;
		}
		if (flag) {
			return filter.apply(pathname) && pathname.isFile();
		} else {
			return !filter.apply(pathname) && pathname.isFile();
		}
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
