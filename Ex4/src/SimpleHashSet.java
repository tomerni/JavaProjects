/**
 * implements the simple hash set class
 */
public abstract class SimpleHashSet implements SimpleSet {

    /** Describes the higher load factor of a newly created hash set. */
    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /** Describes the lower load factor of a newly created hash set. */
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f;

    /** Describes the capacity of a newly created hash set. */
    protected static int INITIAL_CAPACITY = 16;

    /** Describes the current higher load factor of the hash set. */
    protected float upperFactor;

    /** Describes the current lower load factor of the hash set. */
    protected float lowerFactor;

    /** Describes the current capacity of the hash set. */
    protected int curCapacity;

    /** Describes the current number of items in the hash set. */
    protected int curNumOfItems;

    // CONSTRUCTORS

    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet() {
        upperFactor = DEFAULT_HIGHER_CAPACITY;
        lowerFactor = DEFAULT_LOWER_CAPACITY;
        curCapacity = INITIAL_CAPACITY;
        curNumOfItems = 0;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY.
     * @param upperLoadFactor the upper load factor before rehashing
     * @param lowerLoadFactor the lower load factor before rehashing
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        upperFactor = upperLoadFactor;
        lowerFactor = lowerLoadFactor;
        curCapacity = INITIAL_CAPACITY;
        curNumOfItems = 0;
    }

    // PUBLIC AND PROTECTED METHODS

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return curCapacity;
    }

    /**
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor() {
        return lowerFactor;
    }

    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor() {
        return upperFactor;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity. OVERRIDING IN CHILDREN
     * @param index the index before clamping.
     * @return an index properly clamped.
     */
    protected int clamp(int index) {
        return index & (curCapacity - 1);
    }

    // ABSTRACT IMPLEMENTATIONS

    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return curNumOfItems;
    }
}
