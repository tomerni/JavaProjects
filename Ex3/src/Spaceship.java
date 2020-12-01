import oop.ex3.spaceship.Item;

import java.util.HashMap;

/**
 * implements the spaceship class
 */
public class Spaceship {

	// the name of the ship
	private final String shipName;

	// the number of lockers in the ship
	private final int numLockers;

	// array with the ids of the crew
	private final int[] crewID;

	// the constraints of the items
	private final Item[][] cons;

	// the long term storage of the ships
	private final LongTermStorage lts;

	// array of the lockers in the ship
	private final Locker[] lockers;

	// map that matches between lockers and ids
	private final HashMap<Integer, Locker> lockersAndIds;

	// the current number of lockers in the ship
	private int currentLockers;

	/**
	 * constructor
	 * @param name the name of the ship
	 * @param crewIDs list of the crew ids
	 * @param numOfLockers the total number of lockers in the ship
	 * @param constraints the constraints of the items in the ship
	 */
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

	/**
	 * @return the long term storage of the ship
	 */
	public LongTermStorage getLongTermStorage() {
		return lts;
	}

	/**
	 * @return the ids of the crew
	 */
	public int[] getCrewIDs() {
		return crewID;
	}

	/**
	 * @return the lockers in the ship
	 */
	public Locker[] getLockers() {
		return lockers;
	}

	/*
	checks if the given id is valid or not
	 */
	private boolean checkValidID(int id) {
		for (int i : crewID) {
			if (i == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * creates a locker for a crew member
	 * @param crewID the id of the crew member
	 * @param capacity the capacity of the locker
	 * @return if the creating was successful - 0, if the id wasn't valid - -1, if the capacity is negative -
	 * 		-2, if can't add more lockers to the ship - -3
	 */
	public int createLocker(int crewID, int capacity) {
		if (!checkValidID(crewID)) {
			return -1;
		}
		if (capacity < 0) {
			return -2;
		}
		if (currentLockers == numLockers) {
			return -3;
		}
		Locker newLocker = new Locker(lts, capacity, cons);
		lockers[currentLockers] = newLocker; // maybe need to check if already has a locker
		currentLockers++;
		lockersAndIds.put(crewID, newLocker);
		return 0;
	}

}
