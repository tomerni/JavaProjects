import java.util.Collection;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common type with the
 * implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {

    protected Collection<String> col;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param c the collection
     */
    public CollectionFacadeSet(Collection<String> c) {
        col = c;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (contains(newValue)) { // checks for duplicates
            return false;
        }
        return col.add(newValue);
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        return col.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        return col.remove(toDelete);
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return col.size();
    }
}
