package fileprocessing.filter;

import fileprocessing.order.BadOrderNameException;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterFilesTest {

	@Test
	public void tryTest() throws BadFilterException {
		File directory = new File("C:\\Users\\tomer\\Desktop");
		File[] cont = directory.listFiles();
		ArrayList<File> arr = new ArrayList<File>(Arrays.asList(cont));
		System.out.println(arr.size());
		FilterFiles a = new FilterFiles(arr, "hiden#YES");
		ArrayList<File> b = a.filter();
		System.out.println(b.size());
		for (File f : b) {
			System.out.println(f.length() / 1024f + " " + f.getName());
		}
	}
}
