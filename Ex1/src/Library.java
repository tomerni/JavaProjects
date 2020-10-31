/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library to
 * be able to check out books, if a copy of the requested book is available.
 */
public class Library {
	/** The max capacity of books in the library */
	final int bookCapacity;

	/** The max capacity of borrowed books allowed to the patrons in the library */
	final int borrowedBooksCapacity;

	/** The max capacity of patrons in the library */
	final int patronCapacity;

	/** Array of the books in the library */
	final private Book[] bookArchive;

	/** Array of the patrons in the library */
	final private Patron[] patronArr;

	/** Array of the number of books the each patron borrowed in the library */
	final private int[] patronBorrowCounter;

	/** Counter of the current number of books in the library */
	private int bookCounter;

	/** Counter of the current number of patrons in the library */
	private int patronCapacityCounter;

	/**
	 * Creates a new library with the given parameters.
	 * @param maxBookCapacity The maximal number of books this library can hold.
	 * @param maxBorrowedBooks The maximal number of books this library allows a single patron to borrow at
	 * 		the same time.
	 * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
	 */
	Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
		bookCapacity = maxBookCapacity;
		borrowedBooksCapacity = maxBorrowedBooks;
		patronCapacity = maxPatronCapacity;
		bookArchive = new Book[bookCapacity];
		patronArr = new Patron[patronCapacity];
		patronBorrowCounter = new int[patronCapacity];
		patronCapacityCounter = 0;
		bookCounter = 0;
	}

	/**
	 * Returns true if the given number is an id of some book in the library, false otherwise.
	 * @param bookId The id to check.
	 * @return true if the given number is an id of some book in the library, false otherwise.
	 */
	boolean isBookIdValid(int bookId) {
		return bookId >= 0 && bookId < bookCounter;
	}

	/**
	 * Adds the given book to this library, if there is place available, and it isn't already in the library.
	 * @param book The book to add to this library.
	 * @return a non-negative id number for the book if there was a spot and the book was successfully added,
	 * 		or if the book was already in the library; a negative number otherwise.
	 */
	int addBookToLibrary(Book book) {
		int tempId = getBookId(book);
		if (bookCounter + 1 > bookCapacity && tempId == -1) {
			return -1;
		} else if (tempId == -1) {
			bookArchive[bookCounter] = book;
			bookCounter++;
			return bookCounter - 1;
		} else {
			return tempId;
		}
	}

	/**
	 * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
	 * @param book The book for which to find the id number.
	 * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
	 */
	int getBookId(Book book) {
		for (int i = 0; i < bookCounter; i++) {
			if (bookArchive[i] == book) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns true if the book with the given id is available, false otherwise.
	 * @param bookId The id number of the book to check.
	 * @return true if the book with the given id is available, false otherwise.
	 */
	boolean isBookAvailable(int bookId) {
		return isBookIdValid(bookId) && bookArchive[bookId].currentBorrowerId == -1;
	}

	/**
	 * Registers the given Patron to this library, if there is a spot available.
	 * @param patron The patron to register to this library.
	 * @return a non-negative id number for the patron if there was a spot and the patron was successfully
	 * 		registered or if the patron was already registered. a negative number otherwise.
	 */
	int registerPatronToLibrary(Patron patron) {
		int tempId = getPatronId(patron);
		if (patronCapacityCounter + 1 > patronCapacity && tempId == -1) {
			return -1;
		} else if (tempId == -1) {
			patronArr[patronCapacityCounter] = patron;
			patronCapacityCounter++;
			return patronCapacityCounter - 1;
		} else {
			return tempId;
		}
	}

	/**
	 * Returns true if the given number is an id of a patron in the library, false otherwise.
	 * @param patronId The id to check.
	 * @return true if the given number is an id of a patron in the library, false otherwise.
	 */
	boolean isPatronIdValid(int patronId) {
		return patronId >= 0 && patronId < patronCapacityCounter;
	}

	/**
	 * Returns the non-negative id number of the given patron if he is registered to this library, -1
	 * otherwise.
	 * @param patron The patron for which to find the id number.
	 * @return a non-negative id number of the given patron if he is registered to this library, -1
     * otherwise.
	 */
	int getPatronId(Patron patron) {
		for (int i = 0; i < patronCapacity; i++) {
			if (patronArr[i] == patron) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Marks the book with the given id number as borrowed by the patron with the given patron id, if this
	 * book is available, the given patron isn't already borrowing the maximal number of books allowed,
     * and if
	 * the patron will enjoy this book.
	 * @param bookId The id number of the book to borrow.
	 * @param patronId The id number of the patron that will borrow the book.
	 * @return true if the book was borrowed successfully, false otherwise
	 */
	boolean borrowBook(int bookId, int patronId) {
		if (!isBookIdValid(bookId) || !isPatronIdValid(patronId) ||
			bookArchive[bookId].currentBorrowerId != -1 ||
			patronBorrowCounter[patronId] + 1 > borrowedBooksCapacity ||
			!patronArr[patronId].willEnjoyBook(bookArchive[bookId])) {
			return false;
		}
		bookArchive[bookId].currentBorrowerId = patronId;
		patronBorrowCounter[patronId]++;
		return true;
	}

	/**
	 * Return the given book.
	 * @param bookId The id number of the book to return.
	 */
	void returnBook(int bookId) {
		if (isBookIdValid(bookId) && bookArchive[bookId].currentBorrowerId != -1) {
			patronBorrowCounter[bookArchive[bookId].currentBorrowerId]--;
			bookArchive[bookId].currentBorrowerId = -1;
		}
	}

	/**
	 * Suggest the patron with the given id the book he will enjoy the most, out of all available books he
	 * will enjoy, if any such exist.
	 * @param patronId The id number of the patron to suggest the book to.
	 * @return The available book the patron with the given ID will enjoy the most. Null if no book is
	 * 		available.
	 */
	Book suggestBookToPatron(int patronId) {
		int max = -1;
		Book selectedBook = null;
		if (isPatronIdValid(patronId)) {
			Patron curPatron = patronArr[patronId];
			for (int i = 0; i < bookCounter; i++) {
				Book b = bookArchive[i];
				if (curPatron.willEnjoyBook(b) && curPatron.getBookScore(b) > max &&
					b.currentBorrowerId == -1) {
					max = curPatron.getBookScore(b);
					selectedBook = b;
				}
			}
		}
		return selectedBook;
	}
}
