package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class GreaterThanFilter implements Filter{

	public ArrayList<File> filter(String line, ArrayList<File> files) {
		boolean flag = true;
		ArrayList<File> results = new ArrayList<File>();
		String[] splitString = line.split("#");
		double size = Double.parseDouble(splitString[1]);
		if (size < 0) {
			return null; // EXCEPTION
		}
		if (splitString.length == 3) {
			if(splitString[2].equals("NOT")) {
				flag = false;
			}
			else {
				return null; // EXCEPTION
			}
		}
		for (File f : files) {
			if (flag) {
				if ((f.length() / 1024f) > size) {
					results.add(f);
				}
			}
			else {
				if ((f.length() / 1024f) <= size) {
					results.add(f);
				}
			}
		}
		return results;
	}
}
