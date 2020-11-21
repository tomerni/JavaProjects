import oop.ex3.spaceship.Item;

import java.util.HashMap;

public class Spaceship {

	private final String shipName;

	private final int numLockers;

	private final int[] crewID;

	private final Item[][] cons;

	private final LongTermStorage lts;

	private final Locker[] lockers;

	private final HashMap<Integer, Locker> lockersAndIds;

	private int currentLockers;

	public Spaceship(String name, int[] crewIDs, int numOfLockers, Item[][] constraints) {
		shipName = name;
		crewID = crewIDs;
		numLockers = numOfLockers;
		cons = constraints;
		lts = new LongTermStorage();
		lockers = new Locker[numLockers];
		currentLockers = 0;
		lockersAndIds = new HashMap<Integer, Locker>();
	}

	public LongTermStorage getLongTermStorage() {
		return lts;
	}

	public int[] getCrewIDs() {
		return crewID;
	}

	public Locker[] getLockers() {
		return lockers;
	}

	private boolean checkValidID(int id) {
		for (int i : crewID) {
			if (i == id) {
				return true;
			}
		}
		return false;
	}

	public int createLocker(int crewID, int capacity) {
		if (!checkValidID(crewID)) {
			return -1;
		}
		if (currentLockers == numLockers) {
			return -3;
		}
		if (capacity < 0) {
			return -2;
		}
		Locker newLocker = new Locker(lts, capacity, cons);
		lockers[currentLockers] = newLocker; // maybe need to check if already has a locker
		currentLockers++;
		lockersAndIds.put(crewID, newLocker);
		return 0;
	}

}
