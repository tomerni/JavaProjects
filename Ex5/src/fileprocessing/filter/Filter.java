package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public interface Filter {

	public ArrayList<File> filter(String[] splitString, ArrayList<File> files, boolean flag);
}
