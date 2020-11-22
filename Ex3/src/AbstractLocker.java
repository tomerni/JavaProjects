import oop.ex3.spaceship.Item;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLocker {

	private int leftCapacity;

	private HashMap<String, Integer> curItems;

	private int capacity;

	public abstract int addItem(Item item, int n);

	public int getAvailableCapacity() {
		return leftCapacity;
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

}
