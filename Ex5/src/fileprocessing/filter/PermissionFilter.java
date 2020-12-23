package fileprocessing.filter;

import java.io.File;
import java.util.function.Function;

/**
 * implements the permission fitlers
 */
public class PermissionFilter implements Filter {

    // the filter name
    private final String filterName;

    // list of the words in the filter
    private final String[] splitString;

    // flag for not filter
    private boolean flag;

    /**
     * Constructor
     * @param filterName the name of the filter
     * @param splitString list of words in the filter
     * @param flag flag for "not" filter
     */
    public PermissionFilter(String filterName, String[] splitString, boolean flag) {
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
        if (splitString.length == 2 && !splitString[splitString.length - 1].equals("NOT")) {
            throw new BadFilterException();
        }
        if (!(splitString[1].equals("NO") || splitString[1].equals("YES"))) {
            throw new BadFilterException();
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

    /*
     * returns the correct filter according to the filter name
     */
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
