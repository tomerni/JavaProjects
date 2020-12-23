package fileprocessing.filter;

import java.io.File;

public class AllFilter implements Filter {

	private final boolean flag;

	private final String[] splitString;

	public AllFilter(boolean flag, String[] splitString) {
		this.flag = flag;
		this.splitString = splitString;
	}

	@Override
	public void validate() throws BadFilterException {
		if(splitString.length == 2 && !splitString[splitString.length-1].equals("NOT")) {
			throw new BadFilterException();
		}
	}

	@Override
	public boolean accept(File pathname) {
		if (flag) {
			return pathname.isFile();
		} else {
			return false;
		}
	}
}
