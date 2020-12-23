package fileprocessing.filter;

import java.io.File;

/**
 * implements the size filters
 */
public class SizeFilter implements Filter {

    // the filter name
    private final String filterName;

    // list of the words in the filter
    private final String[] splitString;

    // flag for not filter
    private final boolean flag;

    /**
     * Constructor
     * @param filterName the name of the filter
     * @param splitString list of words in the filter
     * @param flag flag for "not" filter
     */
    public SizeFilter(String filterName, String[] splitString, boolean flag) {
        this.filterName = filterName;
        this.splitString = splitString;
        this.flag = flag;
    }

    /**
     * validates that the filter is valid
     * @throws BadFilterException exception if the filter is wrong
     */
    @Override
    public void validate() throws BadFilterException {
        if (filterName.equals("between")) {
            double lowFilter = Double.parseDouble(splitString[1]);
            double highFilter = Double.parseDouble(splitString[2]);
            if (splitString.length == 4 && !splitString[splitString.length - 1].equals("NOT")) {
                throw new BadFilterException();
            }
            if (lowFilter < 0 || highFilter < 0 || highFilter < lowFilter) {
                throw new BadFilterException();
            }
        } else {
            double size = Double.parseDouble(splitString[1]);
            if (splitString.length == 3 && !splitString[splitString.length - 1].equals("NOT")) {
                throw new BadFilterException();
            }
            if (size < 0) {
                throw new BadFilterException();
            }
        }
    }

    /**
     * Tests whether or not the specified abstract pathname should be
     * included in a pathname list.
     * @param pathname The abstract pathname to be tested
     * @return <code>true</code> if and only if <code>pathname</code>
     *         should be included
     */
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

    /*
     * returns the correct function according the given filter (greater_than, smaller_than)
     */
    private ThreeWayMatcher<Long, Double, Boolean> greaterSmallerFilter() {
        if (filterName.equals("greater_than")) {
            return (Long size, Double filter) -> (size / 1024f > filter);
        } else {
            return (Long size, Double filter) -> (size / 1024f < filter);
        }
    }

    /*
     * returns the function for the between filter
     */
    private FourWayMatcher<Long, Double, Double, Boolean> betweenFilter() {
        return (Long size, Double lowFilter, Double highFilter) ->
                (size / 1024f >= lowFilter && size / 1024f <= highFilter);
    }
}
