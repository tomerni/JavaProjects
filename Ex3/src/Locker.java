import oop.ex3.spaceship.*;
import java.util.HashMap;
import java.util.Map;

/**
 * implementing the Locker class
 */
public class Locker {

	// the total capacity of the locker
	private final int capacity;

	// the constraints of the items
	private final Item[][] constraints;

	// the lts of this locker
	private final LongTermStorage lts;

	// the left capacity of the locker
	private int leftCapacity;

	// the inventory of the locker
	private HashMap<String, Integer> curItems;

	/**
	 * constructor
	 * @param lts1 the lts
	 * @param capacity1 the total capacity
	 * @param constraints1 the constraints
	 */
	Locker(LongTermStorage lts1, int capacity1, Item[][] constraints1) {
		capacity = capacity1;
		constraints = constraints1;
		lts = lts1;
		leftCapacity = capacity1;
		curItems = new HashMap<String, Integer>();
	}

	/*
	adding the item to the lts if possible and returns 1. else returns -1
	 */
	private int addAlsoToLTS(Item item, int n, int newItemCount) {
		int numberOfValidItemsToAdd = (int) (0.2 * capacity / item.getVolume());
		int itemsToLts = newItemCount - numberOfValidItemsToAdd;
		if (lts.getAvailableCapacity() < itemsToLts * item.getVolume()) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: no room for" +
							   " " + n + " items of type " + item.getType());
			return -1;
		} else {
			lts.addItem(item, itemsToLts);
			leftCapacity += (newItemCount - n) * item.getVolume();
			leftCapacity -= numberOfValidItemsToAdd * item.getVolume();
			if (numberOfValidItemsToAdd > 0) {
				curItems.put(item.getType(), numberOfValidItemsToAdd);
			}
			System.out.println("Warning: Action successful, but has caused items to be moved to storage\n");
			return 1;
		}
	}

	/*
	checks if the given item has any constraints with the items in the locker.
	 */
	private boolean checkConstraints(Item item) {
		for (Item[] list : constraints) {
			if (list[0].getType().equals(item.getType()) & getItemCount(list[1].getType()) > 0) {
				return true;
			}
			if (list[1].getType().equals(item.getType()) & getItemCount(list[0].getType()) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * addes the given item to the locker n times
	 * @param item the given item
	 * @param n the number of appearances
	 * @return 0 for success, -1 for failure
	 */
	public int addItem(Item item, int n) {
		if (item == null | n < 1) {
			System.out.println("Error: Your request cannot be completed at this time");
			return -1;
		}
		if (checkConstraints(item)) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: the locker " +
							   "cannot contain items of type " + item.getType() + ", as it contains a " +
							   "contradicting item");
			return -2;
		}
		int overallWeight = n * item.getVolume();
		if (overallWeight > leftCapacity) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: no room for" +
							   " " + n + " items of type " + item.getType());
			return -1;
		}
		int newItemCount = getItemCount(item.getType()) + n;
		double percentage = ((double) (newItemCount * item.getVolume()) / capacity) * 100;
		if (percentage <= 50) {
			leftCapacity -= overallWeight;
			curItems.put(item.getType(), newItemCount);
			return 0;
		} else {
			return addAlsoToLTS(item, n, newItemCount);
		}
	}

	/*
	removes the item from the locker
	 */
	private int validRemove(Item item, int n) {
		if (curItems.get(item.getType()) == n) {
			leftCapacity += n * item.getVolume();
			curItems.remove(item.getType());
			return 0;
		} else {
			leftCapacity += n * item.getVolume();
			curItems.put(item.getType(), curItems.get(item.getType()) - n);
			return 0;
		}
	}

	/**
	 * removes the given item n times
	 * @param item the given item
	 * @param n the number of appearances to remove
	 * @return 0 for success, -1 for failure
	 */
	public int removeItem(Item item, int n) {
		if (item == null) {
			System.out.println("Error: Your request cannot be completed at this time");
			return -1;
		}
		if (n < 0) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: the locker " +
							   "cannot remove a negative number of items of type " + item.getType());
			return -1;
		}
		if (!curItems.containsKey(item.getType()) | curItems.get(item.getType()) < n) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: the locker " +
							   "does not contain " + n + " items of type " +
							   item.getType());
			return -1;
		} else {
			return validRemove(item, n);
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
		return capacity;
	}

	/**
	 * returns the available capacity of the locker
	 * @return the available capacity
	 */
	public int getAvailableCapacity() {
		return leftCapacity;
	}
}
