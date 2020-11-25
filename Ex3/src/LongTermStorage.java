import oop.ex3.spaceship.Item;

import java.util.HashMap;

/**
 * implementing the LongTermStorage class
 */
public class LongTermStorage extends AbstractLocker {

	// the total capacity
	private static final int FINAL_CAPACITY = 1000;

	/**
	 * constructor
	 */
	LongTermStorage() {
		capacity = FINAL_CAPACITY;
		leftCapacity = FINAL_CAPACITY;
		curItems = new HashMap<String, Integer>();
	}

	/**
	 * addes the given item to the locker n times
	 * @param item the given item
	 * @param n the number of appearances
	 * @return 0 for success, -1 for failure
	 */
	public int addItem(Item item, int n) {
		if (item == null | n < 0) {
			System.out.println("Error: Your request cannot be completed at this time");
			return -1;
		}
		int overallWeight = n * item.getVolume();
		if (overallWeight <= leftCapacity) {
			leftCapacity -= overallWeight;
			curItems.put(item.getType(), n);
			return 0;
		} else {
			System.out.printf("Error: Your request cannot be completed at this time. Problem: no room for " +
							  "%d items of type %s\n", n, item.getType());
			return -1;
		}
	}

	/**
	 * resets the locker
	 */
	public void resetInventory() {
		leftCapacity = capacity;
		curItems = new HashMap<String, Integer>();
	}
}
