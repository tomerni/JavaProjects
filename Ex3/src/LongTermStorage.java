import oop.ex3.spaceship.Item;
import java.util.HashMap;
import java.util.Map;

/**
 * implementing the LongTermStorage class
 */
public class LongTermStorage {

	// the left capacity in the locker
	private int leftCapacity;

	// the total capacity
	private static final int CAPACITY = 1000;

	// hash map the matches between item name and his appearances
	private HashMap<String, Integer> curItems;

	/**
	 * constructor
	 */
	LongTermStorage() {
		leftCapacity = CAPACITY;
		curItems = new HashMap<String, Integer>();
	}

	/**
	 * returns the available capacity of the locker
	 * @return the available capacity
	 */
	public int getAvailableCapacity() {
		return leftCapacity;
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
	 * returns the appearances of the type
	 * @param type the type of the item
	 * @return the number of appearances of the type
	 */
	public int getItemCount(String type) {
		return (curItems.containsKey(type) ? curItems.get(type) : 0);
	}

	/**
	 * @return the inventory of the locker
	 */
	public Map<String, Integer> getInventory() {
		return curItems;
	}

	/**
	 * @return the total capacity of the locker
	 */
	public int getCapacity() {
		return CAPACITY;
	}

	/**
	 * resets the locker
	 */
	public void resetInventory() {
		leftCapacity = CAPACITY;
		curItems = new HashMap<String, Integer>();
	}
}
