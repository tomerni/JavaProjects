package fileprocessing.filter;

import java.io.File;

/**
 * implements the name filters
 */
public class NameFilter implements Filter {

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
    public NameFilter(String filterName, String[] splitString, boolean flag) {
        this.filterName = filterName;
        this.splitString = splitString;
        this.flag = flag;
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
        ThreeWayMatcher<String, String, Boolean> filter = filterFinder();
        if (flag) {
            return filter.apply(pathname.getName(), splitString[1]) && pathname.isFile();
        } else {
            return !filter.apply(pathname.getName(), splitString[1]) && pathname.isFile();
        }
    }

    /**
     * validates that the filter is valid
     * @throws BadFilterException exception if the filter is wrong
     */
    @Override
    public void validate() throws BadFilterException {
        if (splitString.length == 3 && !splitString[splitString.length - 1].equals("NOT")) {
            throw new BadFilterException();
        }
    }

    /*
     * factory method that returns the correct function according to the given filter
     */
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
