package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;

public class SizeFilter implements Filter {

	private final String filterName;

	public SizeFilter(String filter) {
		filterName = filter;
	}

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag) {
		ArrayList<File> results = new ArrayList<File>();
		if (filterName.equals("between")) {
			return null;
		} else {
			ThreeWayMatcher<Long, Double, Boolean> filter = greaterSmallerFilter();
			double size = Double.parseDouble(splitString[1]);
			if (size < 0) {
				return null;
			}
			for (File f : files) {
				if (flag) {
					if (filter.apply(f.length(), size)) {
						results.add(f);
					}
				}
				else {
					if (!filter.apply(f.length(), size)) {
						results.add(f);
					}
				}
			}
			return results;
		}
	}

	private ThreeWayMatcher<Long, Double, Boolean> greaterSmallerFilter() {
		if (filterName.equals("greater_than")) {
			return (Long size, Double filter) -> (size / 1024f > filter);
		} else {
			return (Long size, Double filter) -> (size / 1024f < filter);
		}
	}

	private FourWayMatcher<Double, Double, Double, Boolean> betweenFilter() {
		return (Double size, Double lowFilter, Double highFilter) ->
				(size / 1024f >= lowFilter && size / 1024f <= highFilter);
	}
}
