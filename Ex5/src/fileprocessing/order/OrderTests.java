package fileprocessing.order;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderTests {

	@Test
	public void tryTest() throws BadOrderNameException {
		File directory = new File("C:\\Users\\tomer\\Desktop");
		File[] cont = directory.listFiles();
		ArrayList<File> arr = new ArrayList<File>(Arrays.asList(cont));
		OrderFiles a = new OrderFiles(arr, "gadg");
		ArrayList<String> b = a.order();
		for (String t : b) {
			System.out.println(t);
		}
	}
}
