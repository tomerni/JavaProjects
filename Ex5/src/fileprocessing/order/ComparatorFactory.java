package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * Factory class for comparators
 */
public class ComparatorFactory {

    /**
     * factory method for comparators
     * @param orderLine the line of the order command
     * @return comparator according the order command
     * @throws BadOrderNameException throws an exception if the order name is not valid
     */
    public static Comparator<File> createComparator(String orderLine) throws BadOrderNameException {
        switch (orderLine) {
            case "abs":
                return new AbsComparator();
            case "type":
                return new TypeComparator();
            case "size":
                return new SizeComparator();
        }
        throw new BadOrderNameException();
    }
}
