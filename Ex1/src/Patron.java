/**
 * This class represents a library patron that has a name and assigns values to different literary aspects of
 * books.
 */
public class Patron {

	/** The first name of the patron */
	final String firstName;

	/** The last name of the patron */
	final String lastName;

	/** The comic tendency of the patron */
	final int patronComicTendency;

	/** The dramatic tendency of the patron */
	final int patronDramaticTendency;

	/** The educational tendency of the patron */
	final int patronEducationalTendency;

	/** The enjoyment threshold of the patron */
	final int enjoymentThreshold;

	/*----=  Constructors  =-----*/

	/**
	 * Creates a new patron with the given characteristic.
	 * @param patronFirstName - The first name of the patron.
	 * @param patronLastName - The last name of the patron.
	 * @param comicTendency - The weight the patron assigns to the comic aspects of books.
	 * @param dramaticTendency - The weight the patron assigns to the dramatic aspects of books.
	 * @param educationalTendency - The weight the patron assigns to the educational aspects of books.
	 * @param patronEnjoymentThreshold - The minimal literary value a book must have for this patron to
	 * 		enjoy it.
	 */
	Patron(String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
		   int educationalTendency, int patronEnjoymentThreshold) {
		firstName = patronFirstName;
		lastName = patronLastName;
		patronComicTendency = comicTendency;
		patronDramaticTendency = dramaticTendency;
		patronEducationalTendency = educationalTendency;
		enjoymentThreshold = patronEnjoymentThreshold;
	}

	/*----=  Instance Methods  =-----*/

	/**
	 * Returns a string representation of the patron
	 * @return the String representation of this patron.
	 */
	String stringRepresentation() {
		return firstName + " " + lastName;
	}

	/**
	 * Returns the literary value this patron assigns to the given book.
	 * @param book - The book to asses.
	 * @return the literary value this patron assigns to the given book.
	 */
	int getBookScore(Book book) {
		return (book.comicValue * patronComicTendency) + (book.dramaticValue * patronDramaticTendency) +
			   (book.educationalValue * patronEducationalTendency);
	}

	/**
	 * Returns true of this patron will enjoy the given book, false otherwise.
	 * @param book - The book to asses.
	 * @return true of this patron will enjoy the given book, false otherwise.
	 */
	boolean willEnjoyBook(Book book) {
		return getBookScore(book) > enjoymentThreshold;
	}
}
