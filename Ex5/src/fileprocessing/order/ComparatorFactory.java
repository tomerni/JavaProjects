package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

public class ComparatorFactory {

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
