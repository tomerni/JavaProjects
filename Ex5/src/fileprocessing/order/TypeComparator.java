package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * Type comparator
 */
public class TypeComparator implements Comparator<File> {

    /**
     * compares between two files according their type. If they have the same type, compares according to
     * their path
     * @param a the first file
     * @param b the second file
     * @return -1 if a is smaller then b, 1 if a is greater then b, 0 if they are equal
     */
    @Override
    public int compare(File a, File b) {
        String aType, bType;
        int aDot = a.getName().lastIndexOf('.');
        int bDot = b.getName().lastIndexOf('.');
        aType = ((aDot > 0) ? a.getName().substring(aDot + 1) : "");
        bType = ((bDot > 0) ? b.getName().substring(bDot + 1) : "");
        int typeCompare = aType.compareTo(bType);
        if (typeCompare == 0) {
            return a.getAbsolutePath().compareTo(b.getAbsolutePath());
        }
        return typeCompare;
    }
}
