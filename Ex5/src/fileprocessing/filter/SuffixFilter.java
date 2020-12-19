package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class SuffixFilter implements Filter {

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag) {
		ArrayList<File> results = new ArrayList<File>();
		for (File f : files) {
			if (flag) {
				if (f.getName().endsWith(splitString[1])) {
					results.add(f);
				}
			}
			else {
				if (!f.getName().endsWith(splitString[1])) {
					results.add(f);
				}
			}
		}
		return results;
	}
}
