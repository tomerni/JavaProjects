/**
 * implements the open hash set
 */
public class OpenHashSet extends SimpleHashSet {

	/** the hash table */
	public LinkedListWrapper[] hashTable;

	// CONSTRUCTORS

	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
	 * factor (0.75) and lower load factor (0.25).
	 */
	public OpenHashSet() {
		super();
		hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
		initTable(hashTable);
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param loweLoadFactor The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float loweLoadFactor) {
		super(upperLoadFactor, loweLoadFactor);
		hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
		initTable(hashTable);
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
	 * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
	 * lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public OpenHashSet(String[] data) {
		super();
		hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
		initTable(hashTable);
		for (String s : data) {
			add(s);
		}
	}

	// PRIVATE METHODS

	/*
	 * init the hash table with linked list wrappers
	 */
	private void initTable(LinkedListWrapper[] hashTable) {
		for (int i = 0; i < curCapacity; i++) {
			hashTable[i] = new LinkedListWrapper();
		}
	}

	/*
	 * re-hashes the values in the table after resizing
	 */
	private void reHash() {
		LinkedListWrapper[] newHashTable = new LinkedListWrapper[curCapacity];
		initTable(newHashTable);
		for (LinkedListWrapper w : hashTable) {
			for (String s : w.str) {
				newHashTable[clamp(s.hashCode())].add(s);
			}
		}
		hashTable = newHashTable;
	}

	// PUBLIC AND PROTECTED METHODS

	/**
	 * Add a specified element to the set if it's not already in it.
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	public boolean add(String newValue) {
		if (contains(newValue)) {
			return false;
		}
		curNumOfItems++;
		if ((float) curNumOfItems / curCapacity > upperFactor) {
			curCapacity *= 2;
			reHash();
		}
		int index = clamp(newValue.hashCode());
		hashTable[index].add(newValue);
		return true;
	}

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal) {
		int index = clamp(searchVal.hashCode());
		return hashTable[index].contains(searchVal);
	}

	/**
	 * Remove the input element from the set.
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	public boolean delete(String toDelete) {
		if (!contains(toDelete)) {
			return false;
		}
		curNumOfItems--;
		if ((float) curNumOfItems / curCapacity < lowerFactor) {
			curCapacity /= 2;
			reHash();
		}
		int index = clamp(toDelete.hashCode());
		hashTable[index].delete(toDelete);
		return true;
	}
}
