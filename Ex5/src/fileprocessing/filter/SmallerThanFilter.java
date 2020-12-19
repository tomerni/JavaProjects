package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class SmallerThanFilter implements Filter {

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag) {
		ArrayList<File> results = new ArrayList<File>();
		double size = Double.parseDouble(splitString[1]);
		if (size < 0) {
			return null; // EXCEPTION
		}
		for (File f : files) {
			if (flag) {
				if ((f.length() / 1024f) < size) {
					results.add(f);
				}
			}
			else {
				if ((f.length() / 1024f) >= size) {
					results.add(f);
				}
			}
		}
		return results;
	}
}
