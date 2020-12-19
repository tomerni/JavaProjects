package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

public class AbsComparator implements Comparator<File> {

	@Override
	public int compare(File a, File b) {
		return a.getAbsolutePath().compareTo(b.getAbsolutePath());
	}
}
