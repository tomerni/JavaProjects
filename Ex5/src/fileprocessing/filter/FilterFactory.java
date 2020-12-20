package fileprocessing.filter;

public class FilterFactory {

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
			return new AllFilter(flag);
		}
		throw new BadFilterException();
	}
}
