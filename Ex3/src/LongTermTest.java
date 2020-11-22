import oop.ex3.spaceship.*;
import org.junit.*;

import static org.junit.Assert.*;

public class LongTermTest {
	static LongTermStorage ltsTest = new LongTermStorage();
	static Item[] allItems = ItemFactory.createAllLegalItems();
	static Item item4; // spores engine, 10

	@BeforeClass
	public static void generatingItems() {
		for (Item i : allItems) {
			if (i.getType().equals("spores engine")) {
				item4 = i;
			}
		}
	}

	@Test
	public void testAddItem() {
		assertEquals(0, ltsTest.addItem(item4, 10)); //can enter
		assertEquals(900, ltsTest.getAvailableCapacity());
		assertEquals(10, ltsTest.getItemCount(item4.getType()));

		assertEquals(-1, ltsTest.addItem(item4, 100)); // can't enter
		assertEquals(900, ltsTest.getAvailableCapacity());
		assertEquals(10, ltsTest.getItemCount(item4.getType()));
	}

	@Test
	public void testReset() {
		LongTermStorage ltsTest1 = new LongTermStorage();
		assertEquals(0, ltsTest1.addItem(item4, 10));
		assertEquals(900, ltsTest1.getAvailableCapacity());
		assertEquals(10, ltsTest1.getItemCount(item4.getType()));

		ltsTest1.resetInventory();

		assertEquals(1000, ltsTest1.getAvailableCapacity()); //full size
		assertEquals(0, ltsTest1.getInventory().keySet().size()); //empty map
	}
}
