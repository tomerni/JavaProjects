package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class SizeFilter implements Filter {

	private final String filterName;

	public SizeFilter(String filter) {
		filterName = filter;
	}

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag)
			throws BadFilterException {
		ArrayList<File> results = new ArrayList<>();
		if (filterName.equals("between")) {
			FourWayMatcher<Long, Double, Double, Boolean> filter = betweenFilter();
			double lowFilter = Double.parseDouble(splitString[1]);
			double highFilter = Double.parseDouble(splitString[2]);
			if (lowFilter < 0 || highFilter < 0 || highFilter < lowFilter) {
				throw new BadFilterException();
			}
			for (File f : files) {
				if (flag) {
					if (filter.apply(f.length(), lowFilter, highFilter)) {
						results.add(f);
					}
				} else {
					if (!filter.apply(f.length(), lowFilter, highFilter)) {
						results.add(f);
					}
				}
			}
		} else {
			ThreeWayMatcher<Long, Double, Boolean> filter = greaterSmallerFilter();
			double size = Double.parseDouble(splitString[1]);
			if (size < 0) {
				throw new BadFilterException();
			}
			for (File f : files) {
				if (flag) {
					if (filter.apply(f.length(), size)) {
						results.add(f);
					}
				} else {
					if (!filter.apply(f.length(), size)) {
						results.add(f);
					}
				}
			}
		}
		return results;
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
