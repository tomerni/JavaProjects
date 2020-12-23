package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * Absolute path comparator
 */
public class AbsComparator implements Comparator<File> {

	/**
	 * compares according to their path
	 * @param a the first file
	 * @param b the second file
	 * @return -1 if a is smaller then b, 1 if a is greater then b, 0 if they are equal
	 */
	@Override
	public int compare(File a, File b) {
		return a.getAbsolutePath().compareTo(b.getAbsolutePath());
	}
}
