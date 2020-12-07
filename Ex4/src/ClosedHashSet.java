/**
 * implements the closed hash set
 */
public class ClosedHashSet extends SimpleHashSet {


	/** the hash table */
	public Object[] hashTable;

	// CONSTRUCTORS

	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
	 * factor (0.75) and lower load factor (0.25).
	 */
	public ClosedHashSet() {
		super();
		hashTable = new Object[INITIAL_CAPACITY];
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param loweLoadFactor The lower load factor of the hash table.
	 */
	public ClosedHashSet(float upperLoadFactor, float loweLoadFactor) {
		super(upperLoadFactor, loweLoadFactor);
		hashTable = new Object[INITIAL_CAPACITY];
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
	 * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
	 * lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public ClosedHashSet(String[] data) {
		super();
		hashTable = new Object[INITIAL_CAPACITY];
		for (String s : data) {
			add(s);
		}
	}

	// PRIVATE METHODS

	/*
	 * Finds empty space in the table and adds the value
	 * @param newValue New value to add to the set
	 */
	private void findSpaceAndAdd(Object newValue, Object[] hashTable) {
		for (int i = 0; i < hashTable.length; i++) {
			int index = clamp(newValue.hashCode() + (i + i * i) / 2);
			if (hashTable[index] == null | hashTable[index] instanceof DeletedString) {
				hashTable[index] = newValue;
				return;
			}
		}
	}

	/*
	 * Deletes the given item from the table (the item is in the table for sure)
	 * @param toDelete Value to delete
	 */
	private void deleteCurValue(String toDelete) {
		for (int i = 0; i < curCapacity; i++) {
			int index = clamp(toDelete.hashCode() + (i + i * i) / 2);
			if (hashTable[index].equals(toDelete)) {
				hashTable[index] = new DeletedString(toDelete);
				return;
			}
		}
	}

	/*
	 * re-hashes the values in the table after resizing
	 */
	private void reHash() {
		Object[] newHashTable = new Object[curCapacity];
		for (Object s : hashTable) {
			if (s != null & !(s instanceof DeletedString)) {
				findSpaceAndAdd(s, newHashTable);
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
		findSpaceAndAdd(newValue, hashTable);
		return true;
	}

	/**
	 * Look for a specified value in the set.
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal) {
		for (int i = 0; i < curCapacity; i++) {
			int index = clamp(searchVal.hashCode() + (i + i * i) / 2);
			if (hashTable[index] == null) {
				return false;
			}
			if (hashTable[index].equals(searchVal)) {
				return true;
			}
		}
		return false;
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
		deleteCurValue(toDelete);
		return true;
	}
}