import oop.ex3.spaceship.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * implements the locker tests
 */
public class LockerTest {

	static LongTermStorage ltsTest = new LongTermStorage();
	static Item[] allItems = ItemFactory.createAllLegalItems();
	static Item[][] cons = ItemFactory.getConstraintPairs();
	static Item item1; // baseball, 2
	static Item item2; // helmet size 1, 3
	static Item item3; // helmet size 3, 5
	static Item item4; // spores engine, 10
	static Item item5; // football, 4
	static Locker smallLocker, emptyLocker, smallLocker1, bigLocker, bigLocker1, bigLocker2, smallLocker2;

	/**
	 * creating the items for the test
	 */
	@BeforeClass
	public static void generatingItems() {
		for (Item i : allItems) {
			if (i.getType().equals("baseball bat")) {
				item1 = i;
			}
			if (i.getType().equals("helmet, size 1")) {
				item2 = i;
			}
			if (i.getType().equals("helmet, size 3")) {
				item3 = i;
			}
			if (i.getType().equals("spores engine")) {
				item4 = i;
			}
			if (i.getType().equals("football")) {
				item5 = i;
			}
		}
		smallLocker = new Locker(ltsTest, 10, cons);
		emptyLocker = new Locker(ltsTest, 0, cons);
		smallLocker1 = new Locker(ltsTest, 10, cons);
		bigLocker = new Locker(ltsTest, 1000, cons);
		bigLocker1 = new Locker(ltsTest, 1000, cons);
		smallLocker2 = new Locker(ltsTest, 10, cons);
		bigLocker2 = new Locker(ltsTest, 1000, cons);
	}

	/**
	 * tests the add item method with small locker
	 */
	@Test
	public void testAddItemSmall() {
		assertEquals("testAddItemSmall(LockerTest): valid enter failure", 0,
					 smallLocker.addItem(item1, 1)); // can
		// enter
		assertEquals("testAddItemSmall(LockerTest): didn't update the item count", 1,
					 smallLocker.getItemCount(item1.getType())); // checks
		// enter
		assertEquals("testAddItemSmall(LockerTest): didn't update the available capacity", 8,
					 smallLocker.getAvailableCapacity());

		assertEquals("testAddItemSmall(LockerTest): move to lts failure", 1,
					 smallLocker.addItem(item1, 2)); // need to
		// move 2 to lts
		assertEquals("testAddItemSmall(LockerTest): add count failure", 1,
					 smallLocker.getItemCount(item1.getType()));
		// checks enter
		assertEquals("testAddItemSmall(LockerTest): add count failure", 2,
					 ltsTest.getItemCount(item1.getType()));
		assertEquals("testAddItemSmall(LockerTest): available capacity failure", 8,
					 smallLocker.getAvailableCapacity());
		assertEquals("testAddItemSmall(LockerTest): available capacity failure", 996,
					 ltsTest.getAvailableCapacity());

		assertEquals("testAddItemSmall(LockerTest): invalid add failure", -1,
					 smallLocker.addItem(item1, 10)); // can't enter at all
		assertEquals("testAddItemSmall(LockerTest): add count failure", 1,
					 smallLocker.getItemCount(item1.getType()));
		assertEquals("testAddItemSmall(LockerTest): add count failure", 2,
					 ltsTest.getItemCount(item1.getType()));

		assertEquals("testAddItemSmall(LockerTest): move to lts failure", 1,
					 smallLocker.addItem(item2, 2)); // move to lts
		assertEquals("testAddItemSmall(LockerTest): add count failure", 0,
					 smallLocker.getItemCount(item2.getType()));
		assertEquals("testAddItemSmall(LockerTest): add count failure", 2,
					 ltsTest.getItemCount(item2.getType()));
		assertEquals("testAddItemSmall(LockerTest): available capacity failure", 8,
					 smallLocker.getAvailableCapacity());
		assertEquals("testAddItemSmall(LockerTest): available capacity failure", 990,
					 ltsTest.getAvailableCapacity());

		ltsTest.resetInventory();
	}

	/**
	 * tests the add item method with empty locker
	 */
	@Test
	public void testAddItemEmptyLocker() {
		assertEquals(-1, emptyLocker.addItem(item1, 1)); // can't enter at all
		assertEquals(0, emptyLocker.getItemCount(item1.getType())); // checks enter
		assertEquals(0, emptyLocker.getAvailableCapacity());
	}

	/**
	 * tests irregular cases
	 */
	@Test
	public void testNotRegular() {
		assertEquals("testNotRegular(LockerTest): entering 0 items failure", 0,
					 smallLocker1.addItem(item1, 0)); //can
		// enter 0 items
		assertEquals("testNotRegular(LockerTest): didn't need to change the capacity", 10,
					 smallLocker1.getAvailableCapacity()); //doesn't need to
		// change

		assertEquals("testNotRegular(LockerTest): adding negative items failure", -1,
					 smallLocker1.addItem(item1, -100)); //can't enter
		// negative
		// items
		assertEquals("testNotRegular(LockerTest): available capacity failure", 10,
					 smallLocker1.getAvailableCapacity()); //doesn't need
		// to change

		assertEquals("testNotRegular(LockerTest): entering null failure", -1,
					 smallLocker1.addItem(null, 10)); //can't enter null item
		assertEquals("testNotRegular(LockerTest): available capacity failure", 10,
					 smallLocker1.getAvailableCapacity()); //doesn't need to change
	}

	/**
	 * tests the add item method with big locker
	 */
	@Test
	public void testAddItemBig() {
		assertEquals("testAddItemBig(LockerTest): valid add failure", 0,
					 bigLocker.addItem(item3, 30)); // can enter
		assertEquals("testAddItemBig(LockerTest): add count failure", 30,
					 bigLocker.getItemCount(item3.getType())); // checks enter
		assertEquals("testAddItemBig(LockerTest): available capacity failure", 850,
					 bigLocker.getAvailableCapacity());

		assertEquals("testAddItemBig(LockerTest): add to lts failure", 1,
					 bigLocker.addItem(item4, 60)); // need to move 20 to lts
		assertEquals("testAddItemBig(LockerTest): add count failure", 20,
					 bigLocker.getItemCount(item4.getType())); // checks enter
		assertEquals("testAddItemBig(LockerTest): add count failure", 40,
					 ltsTest.getItemCount(item4.getType()));
		assertEquals("testAddItemBig(LockerTest): available capacity failure", 650,
					 bigLocker.getAvailableCapacity());
		assertEquals("testAddItemBig(LockerTest): available capacity failure", 600,
					 ltsTest.getAvailableCapacity());

		assertEquals("testAddItemBig(LockerTest): can't enter to lts failure", -1,
					 bigLocker.addItem(item4, 65)); // can't enter to lts
		assertEquals("testAddItemBig(LockerTest): available capacity failure", 650,
					 bigLocker.getAvailableCapacity());
		assertEquals("testAddItemBig(LockerTest): available capacity failure", 600,
					 ltsTest.getAvailableCapacity());

		ltsTest.resetInventory();
	}

	/**
	 * tests the case in which item should be moved to the lts in the add item method
	 */
	@Test
	public void movingToLTSAfterSecondAdd() {
		assertEquals(0, smallLocker2.addItem(item1, 2)); // only 40%
		assertEquals(6, smallLocker2.getAvailableCapacity());

		assertEquals(1, smallLocker2.addItem(item1, 2)); // now 80%, need to move 3 to LTS
		assertEquals(8, smallLocker2.getAvailableCapacity());
		assertEquals(1, smallLocker2.getItemCount(item1.getType()));
		assertEquals(3, ltsTest.getItemCount(item1.getType()));
	}

	/**
	 * tests the remove method with big locker
	 */
	@Test
	public void removeTest() {
		bigLocker1.addItem(item1, 10);
		assertEquals(0, bigLocker1.removeItem(item1, 8)); // can remove
		assertEquals(2, bigLocker1.getItemCount(item1.getType()));

		assertEquals("removeTest(LockerTest): remove negative failure", -1,
					 bigLocker1.removeItem(item1, -1)); // can't
		// remove
		// negative
		assertEquals("removeTest(LockerTest): remove count failure", 2,
					 bigLocker1.getItemCount(item1.getType()));

		assertEquals("removeTest(LockerTest): remove 0 failure", 0,
					 bigLocker1.removeItem(item1, 0)); // can remove 0
		assertEquals("removeTest(LockerTest): remove count failure", 2,
					 bigLocker1.getItemCount(item1.getType()));

		assertEquals("removeTest(LockerTest): remove too much failure", -1,
					 bigLocker1.removeItem(item1, 5)); // can't remove 5,
		// only 2
		assertEquals("removeTest(LockerTest): remove count failure", 2,
					 bigLocker1.getItemCount(item1.getType()));

		assertEquals("removeTest(LockerTest): valid remove failure", 0,
					 bigLocker1.removeItem(item1, 2)); // can remove 2
		assertEquals("removeTest(LockerTest): remove count failure", 0,
					 bigLocker1.getItemCount(item1.getType()));

		assertEquals("removeTest(LockerTest): remove null failure", -1,
					 bigLocker1.removeItem(null, 2)); // can't remove null
	}

	/**
	 * tests the constraints in the add item method
	 */
	@Test
	public void constraintsTest() {
		bigLocker2.addItem(item1, 1); //baseball bat
		assertEquals("constraintsTest(LockerTest): contradiction failure", -2,
					 bigLocker2.addItem(item5, 1)); //adding
		// football,
		// contradiction
		assertEquals(0, bigLocker2.getItemCount(item5.getType()));
		assertEquals("constraintsTest(LockerTest): not a contradiction failure", 0,
					 bigLocker2.addItem(item3, 1));
		//adding helmet, not a
		// contradiction
		assertEquals(1, bigLocker2.getItemCount(item3.getType()));
	}

	/**
	 * tests the get inventory method
	 */
	@Test
	public void getInventoryTest() {
		assertNotNull(bigLocker.getInventory());
	}
}
