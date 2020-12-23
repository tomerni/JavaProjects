package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * Size comparator
 */
public class SizeComparator implements Comparator<File> {

    /**
     * compares between two files according their size. If they have the same type, compares according to
     * their path
     * @param a the first file
     * @param b the second file
     * @return -1 if a is smaller then b, 1 if a is greater then b, 0 if they are equal
     */
    @Override
    public int compare(File a, File b) {
        int sizeCompare = Float.compare(a.length(), b.length());
        return (sizeCompare == 0) ? a.getAbsolutePath().compareTo(b.getAbsolutePath()) : sizeCompare;
    }
}
