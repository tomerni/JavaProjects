package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

public class SizeComparator implements Comparator<File> {

	@Override
	public int compare(File a, File b) {
		int sizeCompare = Float.compare(a.length(), b.length());
		return (sizeCompare == 0) ? a.getAbsolutePath().compareTo(b.getAbsolutePath()) : sizeCompare;
	}
}
