package fileprocessing.filter;

import java.io.File;

public class SizeFilter implements Filter {

	private final String filterName;

	private final String[] splitString;

	private final boolean flag;

	public SizeFilter(String filterName, String[] splitString, boolean flag) {
		this.filterName = filterName;
		this.splitString = splitString;
		this.flag = flag;
	}

	@Override
	public void validate() throws BadFilterException {
		if (filterName.equals("between")) {
			double lowFilter = Double.parseDouble(splitString[1]);
			double highFilter = Double.parseDouble(splitString[2]);
			if (lowFilter < 0 || highFilter < 0 || highFilter < lowFilter) {
				throw new BadFilterException();
			}
		} else {
			double size = Double.parseDouble(splitString[1]);
			if (size < 0) {
				throw new BadFilterException();
			}
		}
	}

	@Override
	public boolean accept(File pathname) {
		if (filterName.equals("between")) {
			FourWayMatcher<Long, Double, Double, Boolean> filter = betweenFilter();
			double lowFilter = Double.parseDouble(splitString[1]);
			double highFilter = Double.parseDouble(splitString[2]);
			if (flag) {
				return filter.apply(pathname.length(), lowFilter, highFilter) && pathname.isFile();
			} else {
				return !filter.apply(pathname.length(), lowFilter, highFilter) && pathname.isFile();
			}
		} else {
			ThreeWayMatcher<Long, Double, Boolean> filter = greaterSmallerFilter();
			double size = Double.parseDouble(splitString[1]);
			if (flag) {
				return filter.apply(pathname.length(), size) && pathname.isFile();
			} else {
				return !filter.apply(pathname.length(), size) && pathname.isFile();
			}
		}
	}

	private ThreeWayMatcher<Long, Double, Boolean> greaterSmallerFilter() {
		if (filterName.equals("greater_than")) {
			return (Long size, Double filter) -> (size / 1024f > filter);
		} else {
			return (Long size, Double filter) -> (size / 1024f < filter);
		}
	}

	private FourWayMatcher<Long, Double, Double, Boolean> betweenFilter() {
		return (Long size, Double lowFilter, Double highFilter) ->
				(size / 1024f >= lowFilter && size / 1024f <= highFilter);
	}
}
