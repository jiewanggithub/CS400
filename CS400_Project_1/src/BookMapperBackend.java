import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookMapperBackend implements IBookMapperBackend {
  protected final HashtableMap<String, IBook> ISBN_DB;
  private String authFilter;

  public BookMapperBackend() {
    ISBN_DB = new HashtableMap<>();
  }
  /**
   * Adds a new book to the backend's database and is stored in
   * a hash table internally.
   * @param book the book to add
   */
  public void addBook(IBook book) {
    if((new ISBNValidator()).validate(book.getISBN13().trim()))
	  ISBN_DB.put(book.getISBN13().trim(), book);
  }

  /**
   * Returns the number of books stored in the backend's database.
   * @return the number of books
   */
  public int getNumberOfBooks() {
    return ISBN_DB.size();
  }

  /**
   * This method can be used to set a filter for the author names
   * contained in the search results. A book is only returned as
   * a result for a search by title, it is also contains the string
   * filterBy in the names of its authors.
   * @param filterBy the string that the book's author names must contain
   */
  public void setAuthorFilter(String filterBy) {
    authFilter = filterBy;
  }

  /**
   * Returns the string used as the author filter, null if no author
   * filter is currently set.
   * @return the string used as the author filter, or null if none is set
   */
  public String getAuthorFilter() {
    return authFilter;
  }

  /**
   * Resets the author filter to null (no filter).
   */
  public void resetAuthorFilter() {
    authFilter = null;
  }

  /**
   * Search through all the books in the title base and return books whose
   * title contains the string word (and that satisfies the author filter,
   * if an author filter is set).
   * @param word word that must be contained in a book's title in result set
   * @return list of books found
   */
  public List<IBook> searchByTitleWord(String word) {
    List<IBook> results = new ArrayList<>();
    if(word==null) return results;
    for(IBook book : ISBN_DB) {
      if(book.getTitle().contains(word)) {
        if(authFilter==null) {
          results.add(book);
        } else {
          if(book.getAuthors().contains(authFilter)) results.add(book);
        }
      }
    }
    return results;
  }

  /**
   * Return the book uniquely identified by the ISBN, or null if ISBN is not
   * present in the dataset.
   * @param ISBN the book's ISBN
   * @return the book identified by the ISBN, or null if ISBN not in database
   */
  public IBook getByISBN(String ISBN) {
    if(ISBN == null) return null;
    ISBN = ISBN.trim();  
    try {
      return (new ISBNValidator()).validate(ISBN) ? ISBN_DB.get(ISBN) : null;
    } catch(NoSuchElementException e) {
      return null;
    }
  }

}
