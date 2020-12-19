package fileprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public interface Filter {

	public ArrayList<File> filter(String line, ArrayList<File> files);
}
