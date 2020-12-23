package fileprocessing.filter;

import java.io.File;

/**
 * implements the all filter
 */
public class AllFilter implements Filter {

    // flag for not filter
    private final boolean flag;

    // list of the words in the filter
    private final String[] splitString;

    /**
     * Constructor
     * @param splitString list of words in the filter
     * @param flag flag for "not" filter
     */
    public AllFilter(boolean flag, String[] splitString) {
        this.flag = flag;
        this.splitString = splitString;
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
        if (flag) {
            return pathname.isFile();
        } else {
            return false;
        }
    }
}
