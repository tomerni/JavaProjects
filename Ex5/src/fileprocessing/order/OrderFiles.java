package fileprocessing.order;

import java.io.*;
import java.util.*;

public abstract class OrderFiles {

	// PRIVATE METHODS

	private static void quickSort(ArrayList<File> list, int low, int high, Comparator<File> comparator) {
		if (low < high) {
			int pi = partition(list, low, high, comparator);
			quickSort(list, low, pi - 1, comparator);
			quickSort(list, pi + 1, high, comparator);
		}
	}

	private static int partition(ArrayList<File> list, int low, int high, Comparator<File> comparator) {
		File pivot = list.get(high);
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			int compareResult = comparator.compare(list.get(j), pivot);
			if (compareResult < 0) {
				i++;
				File temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		File temp = list.get(i + 1);
		list.set(i + 1, list.get(high));
		list.set(high, temp);

		return i + 1;
	}

	// PUBLIC METHODS

	public static ArrayList<String> order(ArrayList<File> filteredFiles, String orderLine)
			throws BadOrderNameException {
		String[] splitString = orderLine.split("#");
		ArrayList<String> namesList = new ArrayList<>();
		String typeOfOrder = splitString[0];
		Comparator<File> comp = ComparatorFactory.createComparator(typeOfOrder);
		quickSort(filteredFiles, 0, filteredFiles.size() - 1, comp);
		if (splitString.length == 2 && splitString[1].equals("REVERSE")) {
			Collections.reverse(filteredFiles);
		}
		for (File f : filteredFiles) {
			namesList.add(f.getName());
		}
		return namesList; // CAN BE EMPTY
	}
}
