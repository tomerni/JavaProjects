import java.util.LinkedList;

/**
 * wraps java's linked list
 */
public class LinkedListWrapper {

	/** the linked list */
	public LinkedList<String> str = new LinkedList<String>();

	/**
	 * delegates to the linked list
	 * @param newValue the value to add
	 */
	public void add(String newValue) {
		str.add(newValue);
	}

	/**
	 * delegates to the linked list
	 * @param searchVal the value to search
	 */
	public boolean contains(String searchVal) {
		return str.contains(searchVal);
	}

	/**
	 * delegates to the linked list
	 * @param toDelete the value to delete
	 */
	public void delete(String toDelete) {
		str.remove(toDelete);
	}
}
