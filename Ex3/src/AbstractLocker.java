import oop.ex3.spaceship.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * implements an abstract locker
 */
public abstract class AbstractLocker {

	/** the left capacity of the locker */
	protected int leftCapacity;

	/** the inventory of the locker */
	protected HashMap<String, Integer> curItems;

	/** the total capacity of the locker */
	protected int capacity;

	/** default error message */
	protected static final String defaultError = "Error: Your request cannot be completed at this time";

	/** contradiction error message */
	protected static final String contradictError = "Error: Your request cannot be completed at this time." +
													" " +
													"Problem: the locker cannot contain items of type %s, " +
													"as" +
													" " +
													"it contains a contradicting item";

	/** not enough room in the locker error message */
	protected static final String notEnoughRoomInLockerError = "Error: Your request cannot be completed at" +
															   " " +
															   "this time. Problem: no room for %d items " +
															   "of" +
															   " type %s";

	/** moved items to LTS error message */
	protected static final String moveToLTSError = "Warning: Action successful, but has caused items to be" +
												   " " +
												   "moved to storage";

	/** trying to remove negative number of items error message */
	protected static final String removeNegativeError = "Error: Your request cannot be completed at this " +
														"time. Problem: the locker cannot remove a " +
														"negative" +
														" number of items of type %s";

	/** trying to remove wrong number of items error message */
	protected static final String removeWrongItemError = "Error: Your request cannot be completed at this " +
														 " " +
														 "time. Problem: the locker does not contain %d " +
														 "items of type %s";

	/**
	 * adds the given item to the locker n times. Every child has to implement it.
	 * @param item the given item
	 * @param n the number of appearances
	 * @return 0 for success, different number for failure
	 */
	public abstract int addItem(Item item, int n);

	/**
	 * returns the available capacity of the locker
	 * @return the available capacity
	 */
	public int getAvailableCapacity() {
		return leftCapacity;
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
		return capacity;
	}

}
