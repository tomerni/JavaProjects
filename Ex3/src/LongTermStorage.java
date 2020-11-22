import oop.ex3.spaceship.Item;
import java.util.HashMap;
import java.util.Map;

public class LongTermStorage {

	private int leftCapacity;

	private final int capacity = 1000;

	private HashMap<String, Integer> curItems;

	LongTermStorage() {
		leftCapacity = capacity;
		curItems = new HashMap<String, Integer>();
	}

	public int getAvailableCapacity() {
		return leftCapacity;
	}

	public int addItem(Item item, int n) {
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

	public void resetInventory() {
		leftCapacity = capacity;
		curItems = new HashMap<String, Integer>();
	}
}
