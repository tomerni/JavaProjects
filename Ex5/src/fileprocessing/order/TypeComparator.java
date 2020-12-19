package fileprocessing.order;

import java.io.File;
import java.util.Comparator;

public class TypeComparator implements Comparator<File> {

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
