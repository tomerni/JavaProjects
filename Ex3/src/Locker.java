import oop.ex3.spaceship.*;

import java.util.HashMap;
import java.util.Map;

public class Locker {

	private final int capacity;

	private final Item[][] constraints;

	private final LongTermStorage lts;

	private int leftCapacity;

	private HashMap<String, Integer> curItems;

	private Map<Locker, Locker[]> lockerConstraints;

	Locker(LongTermStorage lts1, int capacity1, Item[][] constraints1) {
		capacity = capacity1;
		constraints = constraints1;
		lts = lts1;
		leftCapacity = capacity1;
		curItems = new HashMap<String, Integer>();
	}


	private int addAlsoToLTS(Item item, int n, int newItemCount) {
		int numberOfValidItemsToAdd = (int) (0.2 * capacity / item.getVolume());
		int itemsToLts = newItemCount - numberOfValidItemsToAdd;
		if (lts.getAvailableCapacity() < itemsToLts * item.getVolume()) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: no room for " +
							   Integer.toString(n) + " items of type " + item.getType());
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
			System.out.println("Error: Your request cannot be completed at this time. Problem: no room for " +
							  Integer.toString(n) + " items of type " + item.getType());
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

	public int removeItem(Item item, int n) {
		if (n < 0) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: the locker " +
							   "cannot remove a negative number of items of type " + item.getType());
			return -1;
		}
		if (!curItems.containsKey(item.getType()) | curItems.get(item.getType()) < n) {
			System.out.println("Error: Your request cannot be completed at this time. Problem: the locker " +
							   "does not contain " + Integer.toString(n) + " items of type " + item.getType());
			return -1;
		} else {
			return validRemove(item, n);
		}
	}

	public int getItemCount(String type) {
		if (curItems.containsKey(type)) {
			return curItems.get(type);
		}
		return 0;
	}

	public Map<String, Integer> getInventory() {
		return curItems;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getAvailableCapacity() {
		return leftCapacity;
	}
}
