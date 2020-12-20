package fileprocessing.order;

import java.io.*;
import java.util.*;

public class OrderFiles {

	//private final List<String> validOrders = Arrays.asList("abs", "type", "size");

	private final ArrayList<File> filteredFiles;

	private final String orderLine;


	// CONSTRUCTOR

	public OrderFiles(ArrayList<File> filteredFiles, String orderLine) {
		this.filteredFiles = filteredFiles;
		this.orderLine = orderLine;
	}

	// PRIVATE METHODS

	private void quickSort(ArrayList<File> list, int low, int high, Comparator<File> comparator) {
		if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
			int pi = partition(list, low, high, comparator);

			// Recursively sort elements before
			// partition and after partition
			quickSort(list, low, pi - 1, comparator);
			quickSort(list, pi + 1, high, comparator);
		}
	}

	private int partition(ArrayList<File> list, int low, int high, Comparator<File> comparator) {
		File pivot = list.get(high);
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than the pivot
			int compareResult = comparator.compare(list.get(j), pivot);
			if (compareResult < 0) {
				i++;

				// swap arr[i] and arr[j]
				File temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		File temp = list.get(i + 1);
		list.set(i + 1, list.get(high));
		list.set(high, temp);

		return i + 1;
	}

	// PUBLIC METHODS

	public ArrayList<String> order() throws BadOrderNameException {
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
