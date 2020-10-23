/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library to be able to
 * check out books, if a copy of the requested book is available.
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

    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity){
        bookCapacity = maxBookCapacity;
        borrowedBooksCapacity = maxBorrowedBooks;
        patronCapacity = maxPatronCapacity;
        bookArchive = new Book[bookCapacity];
        patronArr = new Patron[patronCapacity];
        patronBorrowCounter = new int[patronCapacity];
        patronCapacityCounter = 0;
        bookCounter = 0;
    }

    boolean isBookIdValid(int bookId){
        return bookId >= 0 && bookId < bookCounter;
    }

    int addBookToLibrary(Book book){
        if(bookCounter + 1 >= bookCapacity) {
            return -1;
        }
        int tempId = getBookId(book);
        if(tempId == -1) {
            bookArchive[bookCounter] = book;
            bookCounter++;
            return bookCounter - 1;
        }
        else{
            return tempId;
        }
    }

    int getBookId(Book book){
        for(int i = 0; i <= bookCounter; i++){
            if(bookArchive[i].equals(book)){
                return i;
            }
        }
        return -1;
    }

    boolean isBookAvailable(int bookId){
        return isBookIdValid(bookId) && bookArchive[bookId].currentBorrowerId == -1;
    }

    int registerPatronToLibrary(Patron patron){
        if(patronCapacityCounter + 1 >= patronCapacity){
            return -1;
        }
        int tempId = getPatronId(patron);
        if(tempId == -1) {
            patronArr[patronCapacity] = patron;
            patronCapacityCounter++;
            return patronCapacityCounter - 1;
        }
        else{
            return tempId;
        }
    }

    boolean isPatronIdValid(int patronId)
    {
        return patronId >= 0 && patronId < patronCapacityCounter;
    }

    int getPatronId(Patron patron){
        for(int i = 0; i <= patronCapacity; i++){
            if(patronArr[i].equals(patron)){
                return i;
            }
        }
        return -1;
    }

    boolean borrowBook(int bookId, int patronId){
       if(!isBookIdValid(bookId) || !isPatronIdValid(patronId) || bookArchive[bookId].currentBorrowerId != -1 ||
          patronBorrowCounter[patronId] + 1 >= borrowedBooksCapacity ||
               !patronArr[patronId].willEnjoyBook(bookArchive[bookId])){
           return false;
       }
        bookArchive[bookId].currentBorrowerId = patronId;
        patronBorrowCounter[patronId]++;
        return true;
    }

    void returnBook(int bookId){
        patronBorrowCounter[bookArchive[bookId].currentBorrowerId]--;
        bookArchive[bookId].currentBorrowerId = -1;
    }

    Book suggestBookToPatron(int patronId){
        int max = -1;
        Book selectedBook = null;
        Patron curPatron = patronArr[patronId];
        for(Book b : bookArchive)
        {
            if(curPatron.getBookScore(b) > max && b.currentBorrowerId == -1)
            {
                max = curPatron.getBookScore(b);
                selectedBook = b;
            }
        }
        return selectedBook;
    }

}
