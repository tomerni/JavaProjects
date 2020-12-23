package fileprocessing.filter;

/**
 * factory class for filters
 */
public class FilterFactory {

    /**
     * factory method for filters
     * @param filterName the name of the filter
     * @param splitString list of the words in the filter
     * @param flag true - for regular, false - for "NOT" filter
     * @return Filter object according to the given parameters
     * @throws BadFilterException throws the exception if the filter name is invalid
     */
    public static Filter createFilter(String filterName, String[] splitString, boolean flag) throws BadFilterException {
        if (filterName.equals("greater_than") || filterName.equals("smaller_than") || filterName.equals(
                "between")) {
            return new SizeFilter(filterName, splitString, flag);
        }
        if (filterName.equals("hidden") || filterName.equals("writable") || filterName.equals("executable")) {
            return new PermissionFilter(filterName, splitString, flag);
        }
        if (filterName.equals("file") || filterName.equals("contains") || filterName.equals("suffix") ||
                filterName.equals("prefix")) {
            return new NameFilter(filterName, splitString, flag);
        }
        if (filterName.equals("all")) {
            return new AllFilter(flag, splitString);
        }
        throw new BadFilterException();
    }
}
