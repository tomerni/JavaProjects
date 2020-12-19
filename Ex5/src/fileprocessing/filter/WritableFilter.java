package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class WritableFilter implements Filter {

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag) {
		ArrayList<File> results = new ArrayList<File>();
		if (!(splitString[1].equals("NO") || splitString[1].equals("YES"))) {
			return null;
		}
		if (splitString[1].equals("NO")) {
			flag = !flag;
		}
		for (File f : files) {
			if (flag) {
				if (f.canWrite()) {
					results.add(f);
				}
			}
			else {
				if (!f.canWrite()) {
					results.add(f);
				}
			}
		}
		return results;
	}
}
