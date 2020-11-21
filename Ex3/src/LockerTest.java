import oop.ex3.spaceship.*;
import org.junit.*;
import static org.junit.Assert.*;


public class LockerTest {

	static LongTermStorage ltsTest = new LongTermStorage();
	static Item[] allItems = ItemFactory.createAllLegalItems();
	static Item[][] cons = ItemFactory.getConstraintPairs();
	static Item item1 = allItems[0]; // baseball, 2
	static Item item2 = allItems[1]; // helmet size 1, 3
	static Item item3 = allItems[2]; // helmet size 3, 5
	static Item item4 = allItems[3]; // spores engine, 10
	static Item item5 = allItems[4]; // football, 4

	@Test
	public void testAddItemSmall() {
		Locker smallLocker = new Locker(ltsTest, 10, cons);
		assertEquals(0, smallLocker.addItem(item1, 1)); // can enter
		assertEquals(1, smallLocker.getItemCount(item1.getType())); // checks enter
		assertEquals(8, smallLocker.getAvailableCapacity());

		assertEquals(1, smallLocker.addItem(item1, 2)); // need to move 2 to lts
		assertEquals(1, smallLocker.getItemCount(item1.getType())); // checks enter
		assertEquals(2, ltsTest.getItemCount(item1.getType()));
		assertEquals(8, smallLocker.getAvailableCapacity());
		assertEquals(996, ltsTest.getAvailableCapacity());

		assertEquals(-1, smallLocker.addItem(item1, 10)); // can't enter at all
		assertEquals(1, smallLocker.getItemCount(item1.getType()));
		assertEquals(2, ltsTest.getItemCount(item1.getType()));

		assertEquals(1, smallLocker.addItem(item2, 2)); // move to lts
		assertEquals(0, smallLocker.getItemCount(item2.getType()));
		assertEquals(2, ltsTest.getItemCount(item2.getType()));
		assertEquals(8, smallLocker.getAvailableCapacity());
		assertEquals(990, ltsTest.getAvailableCapacity());

		ltsTest.resetInventory();
	}

	@Test
	public void testAddItemEmptyLocker() {
		Locker emptyLocker = new Locker(ltsTest, 0, cons);
		assertEquals(-1, emptyLocker.addItem(item1, 1)); // can't enter at all
		assertEquals(0, emptyLocker.getItemCount(item1.getType())); // checks enter
		assertEquals(0, emptyLocker.getAvailableCapacity());

	}

	@Test
	public void testNotRegular() {
		Locker smallLocker = new Locker(ltsTest, 10, cons);
		assertEquals(-1, smallLocker.addItem(item1, 0)); //can't enter 0 items
		assertEquals(10, smallLocker.getCapacity()); //doesn't need to change

		assertEquals(-1, smallLocker.addItem(item1, -100)); //can't enter negative items
		assertEquals(10, smallLocker.getCapacity()); //doesn't need to change

		assertEquals(-1, smallLocker.addItem(null, 10)); //can't enter null item
		assertEquals(10, smallLocker.getCapacity()); //doesn't need to change
	}

	@Test
	public void testAddItemBig() {
		Locker bigLocker = new Locker(ltsTest, 1000, cons);
		assertEquals(0, bigLocker.addItem(item3, 30)); // can enter
		assertEquals(30, bigLocker.getItemCount(item3.getType())); // checks enter
		assertEquals(850, bigLocker.getAvailableCapacity());

		assertEquals(1, bigLocker.addItem(item4, 60)); // need to move 20 to lts
		assertEquals(20, bigLocker.getItemCount(item4.getType())); // checks enter
		assertEquals(40, ltsTest.getItemCount(item4.getType()));
		assertEquals(650, bigLocker.getAvailableCapacity());
		assertEquals(600, ltsTest.getAvailableCapacity());

		assertEquals(-1, bigLocker.addItem(item4, 65)); // can't enter to lts
		assertEquals(650, bigLocker.getAvailableCapacity());
		assertEquals(600, ltsTest.getAvailableCapacity());

		ltsTest.resetInventory();
	}

	@Test
	public void movingToLTSAfterSecondAdd() {
		Locker smallLocker = new Locker(ltsTest, 10, cons);
		assertEquals(0, smallLocker.addItem(item1, 2)); // only 40%
		assertEquals(6, smallLocker.getAvailableCapacity());

		assertEquals(1, smallLocker.addItem(item1, 2)); // now 80%, need to move 3 to LTS
		assertEquals(8, smallLocker.getAvailableCapacity());
		assertEquals(1, smallLocker.getItemCount(item1.getType()));
		assertEquals(3, ltsTest.getItemCount(item1.getType()));
	}

	@Test
	public void removeTest() {
		Locker bigLocker = new Locker(ltsTest, 1000, cons);
		bigLocker.addItem(item1, 10);
		assertEquals(0, bigLocker.removeItem(item1, 8)); // can remove
		assertEquals(2, bigLocker.getItemCount(item1.getType()));

		assertEquals(-1, bigLocker.removeItem(item1, -1)); // can't remove negative
		assertEquals(2, bigLocker.getItemCount(item1.getType()));

		assertEquals(0, bigLocker.removeItem(item1, 0)); // can remove 0
		assertEquals(2, bigLocker.getItemCount(item1.getType()));

		assertEquals(-1, bigLocker.removeItem(item1, 5)); // can't remove 5, only 2
		assertEquals(2, bigLocker.getItemCount(item1.getType()));

		assertEquals(0, bigLocker.removeItem(item1, 2)); // can remove 2
		assertEquals(0, bigLocker.getItemCount(item1.getType()));
	}

	@Test
	public void constraintsTest() {
		Locker bigLocker = new Locker(ltsTest, 1000, cons);
		bigLocker.addItem(item1,1); //baseball bat
		assertEquals(-2, bigLocker.addItem(item5,1)); //adding football, contradiction
		assertEquals(0, bigLocker.getItemCount(item5.getType()));
		assertEquals(0, bigLocker.addItem(item3,1)); //adding helmet, not a contradiction
		assertEquals(1, bigLocker.getItemCount(item3.getType()));
	}

}
