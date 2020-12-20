package fileprocessing.filter;

import java.io.File;

public class AllFilter implements Filter {

	private final boolean flag;

	public AllFilter(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void validate(){
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
