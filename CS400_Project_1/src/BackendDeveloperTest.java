import java.util.ArrayList;

public class BackendDeveloperTest {
  /**
   * Tests the book addition function
   * @return true if passed
   */
  public static boolean testAddBook() {
    Book b1 = new Book("T1","A1","9788053292764");
    Book b2 = new Book("T2", "A2","9787868543788");
    Book b3 = new Book("T3", "A3", "9789899459281");
    BookMapperBackend b = new BookMapperBackend();

    try {
      b.addBook(b1);
      if(!b.ISBN_DB.containsKey("9788053292764")) {
        System.out.println("Unsuccessful book addition 1");
        return false;
      }

      b.addBook(b2);
      if(!b.ISBN_DB.containsKey("9787868543788")) {
        System.out.println("Unsuccessful book addition 2");
        return false;
      }

      b.addBook(b3);
      if(!b.ISBN_DB.containsKey("9789899459281")) {
        System.out.println("Unsuccessful book addition");
        return false;
      }
    } catch(Exception e) {
      System.out.println("Not expecting error: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests title search without the author filter
   * @return true if passed
   */
  public static boolean testTitleSearch() {
    Book b1 = new Book("T1", "Ernst Zermelo", "9788053292764");
    Book b2 = new Book("T2", "Terrence Tao", "9787868543788");
    Book b3 = new Book("T3 and Hello", "Arzela / Ascoli", "9789899459281");
    Book b4 = new Book("T2 and Hello to thou", "Terrence Tao", "9781868638734");
    BookMapperBackend b = new BookMapperBackend();
    b.addBook(b1);
    b.addBook(b2);
    b.addBook(b3);
    b.addBook(b4);
    try {
      ArrayList<IBook> l = (ArrayList<IBook>) b.searchByTitleWord("T1");
      if(!(l.get(0).getISBN13().equals(b1.getISBN13()))) {
        System.out.println("Did not find ISBN13 1");
        return false;
      }

      l = (ArrayList<IBook>) b.searchByTitleWord("T2");
      if(!(l.get(1).getISBN13().equals(b2.getISBN13()))) {
        System.out.println("Did not find ISBN13 2");
        return false;
      }

      l = (ArrayList<IBook>) b.searchByTitleWord("T3");
      if(!(l.get(0).getISBN13().equals(b3.getISBN13()))) {
        System.out.println("Did not find ISBN13 3");
        return false;
      }

      l = (ArrayList<IBook>) b.searchByTitleWord("Hello");
      boolean found1 = false, found2 = false;
      for(IBook i : l) {
        found1 = i.getISBN13().equals(b3.getISBN13()) || found1;
        found2 = i.getISBN13().equals(b4.getISBN13()) || found2;
      }
      if(!(found1 && found2)) {
        System.out.println("Did not find both hellos");
        return false;
      }
    } catch(Exception e) {
      System.out.println("Not expecting error: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests the author filter function
   * @return true if passed
   */
  public static boolean testAuthFilter() {
    Book b1 = new Book("T1", "Ernst Zermelo", "9788053292764");
    Book b2 = new Book("T2", "Terrence Tao","9787868543788");
    Book b3 = new Book("T3 and Hello", "Arzela / Ascoli", "9789899459281");
    Book b4 = new Book("T2 and Hello to thou", "Terrence Tao", "9781868638734");
    BookMapperBackend b = new BookMapperBackend();
    b.addBook(b1);
    b.addBook(b2);
    b.addBook(b3);
    b.addBook(b4);

    try {
      b.setAuthorFilter("Zermelooo");
      ArrayList<IBook> l = (ArrayList<IBook>) b.searchByTitleWord("T1");

      if(l.size() != 0) {
        System.out.println("Unexpected behavior from nonexistent author");
        return false;
      }

      b.setAuthorFilter("Arzela / Ascoli");
      l = (ArrayList<IBook>) b.searchByTitleWord("wot");
      if(l.size() != 0) {
        System.out.println("Unexpected behavior on author with nonexistent book");
        return false;
      }

      b.setAuthorFilter("Ascoli");
      l = (ArrayList<IBook>) b.searchByTitleWord("T3");
      if(!l.get(0).getISBN13().equals(b3.getISBN13())) {
        System.out.println("Expecting to get book T3 and Hello for Ascoli");
        return false;
      }

      b.setAuthorFilter("Tao");
      l = (ArrayList<IBook>) b.searchByTitleWord("T2");
      boolean found1 = false, found2 = false;
      for(IBook i : l) {
        found1 = i.getISBN13().equals(b2.getISBN13()) || found1;
        found2 = i.getISBN13().equals(b4.getISBN13()) || found2;
      }
      if(!(found1 && found2)) {
        System.out.println("Did not find both Terry Tao books");
        return false;
      }

    } catch(Exception e) {
      System.out.println("Not expecting error: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * tests the ISBN retrieval
   * @return
   */
  public static boolean testGetISBN() {
    Book b1 = new Book("T1","Ernst Zermelo", "9788053292764");
    Book b2 = new Book("T2", "Terrence Tao","9787868543788");
    Book b3 = new Book("T3 and Hello", "Arzela / Ascoli",  "9789899459281");
    Book b4 = new Book("T2 and Hello to thou", "Terrence Tao", "9781868638734");
    BookMapperBackend b = new BookMapperBackend();
    b.addBook(b1);
    b.addBook(b2);
    b.addBook(b3);
    b.addBook(b4);

    try {
      IBook got = b.getByISBN("9788053292764");
      if(got == null || !got.getAuthors().equals("Ernst Zermelo")) {
        System.out.println("Error getting Zermelo's book");
        return false;
      }

      got = b.getByISBN("9787868543788");
      if(got == null || !got.getAuthors().equals("Terrence Tao") || !got.getTitle().equals("T2")) {
        System.out.println("Error getting T2 Terry Tao book");
        return false;
      }

      got = b.getByISBN("9789899459281");
      if(got == null || !got.getAuthors().equals("Arzela / Ascoli")) {
        System.out.println("Error getting T3 book");
        return false;
      }

      got = b.getByISBN("9781868638734");
      if(got == null || !got.getAuthors().equals("Terrence Tao") || !got.getTitle().equals("T2 and Hello to thou")) {
        System.out.println("Error getting T2 and Hello Terry Tao book");
        return false;
      }

      got = b.getByISBN("9781477800903");
      if(got != null) {
        System.out.println("Somehow got a book");
        return false;
      }
    }catch(Exception e) {
      System.out.println("Not expecting error: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * tests the misc helper methods
   * @return
   */
  public static boolean testMisc() {
    BookMapperBackend b = new BookMapperBackend();
    b.setAuthorFilter("John");
    if(!b.getAuthorFilter().equals("John")) return false;

    b.resetAuthorFilter();
    if(!(b.getAuthorFilter()==null)) return false;

    if(b.getNumberOfBooks() != 0) return false;

    Book b1 = new Book("T1", "Ernst Zermelo", "9780141308807");
    b.addBook(b1); // last test
    return b.getNumberOfBooks() == 1;
  }

  /**
   * Runs all the tester methods
   * @return true if all pass
   */
  public static boolean testAll() {
    return testAddBook() && testTitleSearch() && testAuthFilter() && testGetISBN() && testMisc();
  }
	
  /**
   * Tests adding books and removing books without author filter
   *
   * @return true if all pass
   */
  public static boolean integrationTest1() {
    try {
	Book b1 = new Book("T1", "John Wayne", "9781815312809");
	BookMapperBackend b = new BookMapperBackend();
	
	b.addBook(b1);
	if(!b.ISBN_DB.containsKey("9781815312809")) return false;

	// Making sure there are no additional books
	if(b.getNumberOfBooks() != 1) return false;
	
	b.addBook(new Book("T2", "hap", "9780141308807"));
	if(b.getNumberOfBooks() != 2 || !b.ISBN_DB.containsKey("9780141308807")) return false;	
    } catch(Exception e) {
	System.out.println("Not expecting an error " + e.getMessage());
	e.printStackTrace();
	return false;
    }	    
    return true;
  }
  
  /**
   * Tests adding and removing books with an author / title / ISBN filter enabled
   * @return true if all pass
   */  
  public static boolean integrationTest2() {
    try {
	BookMapperBackend b = new BookMapperBackend();
	b.addBook(new Book("Title 1", "John", "9781815312809"));
	b.addBook(new Book("B2", "Henry", "9780141308807"));
	b.addBook(new Book("Title 3", "John", "9786930615347"));

	b.setAuthorFilter("John");
	boolean found1 = false, found2 = false, found3 = false;
	for(IBook book : b.searchByTitleWord("Title")) {
	  found1 = book.getISBN13().equals("9781815312809") || found1;
	  found2 = book.getISBN13().equals("9780141308807") || found2;
	  found3 = book.getISBN13().equals("9786930615347") || found3; 
	}
        // Expecting to find third book and first book
	if(!found1 || found2 || !found3) {
	  System.out.println("Found " + found1 + " " + found2 + " " + found3);
	  return false;
	}

	b.resetAuthorFilter();
	found1 = found2 = found3 = false;
	for(IBook book : b.searchByTitleWord("B2")) {
          found1 = book.getISBN13().equals("9781815312809") || found1;
          found2 = book.getISBN13().equals("9780141308807") || found2;
          found3 = book.getISBN13().equals("9786930615347") || found3;
        }
	// Expecting to find only the second book
	if(found1 || !found2 || found3) {
	  System.out.println("Found " + found1 + " " + found2 + " " + found3);
          return false;
	}

	// This book exists
	if(b.getByISBN("9780141308807") == null) {
	  System.out.println("Valid ISBN ? " + (new ISBNValidator()).validate("9780141308807"));
	  return false;
	}

    } catch(Exception e) {
	System.out.println("Not expecting error " + e.getMessage());
	e.printStackTrace();
	return false;
    }
    return true;
  }

  /**
   * Testing the algorithm engineer's validator
   * @return true if expected behavior
   */
  public static boolean testValidator() {
    try {
	IISBNValidator v = new ISBNValidator();
	if(!(v.validate("9781815312809") || v.validate("9780141308807") || 
				v.validate("9786930615347"))) {
	  return false;
	}
	if(v.validate("9781815312804")) {
	  System.out.println("Validated incorrect ISBN");
	  return false;
	}

	if(v.validate("hi")) {
	  System.out.println("validated a \"hi\"");
	  return false;
	}
    } catch(Exception e) {
    	System.out.println("Not expection error " + e.getMessage());
	e.printStackTrace();
	return false;
    }
    return true;
  }

  public static boolean testIterator() {
    try {
       BookMapperBackend b = new BookMapperBackend();
       b.addBook(new Book("Title 1", "John", "9781815312809"));
       b.addBook(new Book("B2", "Henry", "9780141308807"));
       b.addBook(new Book("Title 3", "John", "9786930615347"));
       
       boolean found1 = false, found2 = false, found3 = false;
       for(IBook book : b.ISBN_DB) {
         found1 = book.getISBN13().equals("9781815312809") || found1;
         found2 = book.getISBN13().equals("9780141308807") || found2;
         found3 = book.getISBN13().equals("9786930615347") || found3;
       }

       if(!found1 || !found2 || !found3) {
       	 System.out.println("Did not find every book");
	 return false;
       }

       return true;
    } catch(Exception e) {
	System.out.println("Not expecting error " + e.getMessage());
	e.printStackTrace();
	return false;
    } 
  }



  /**
  * Just a helper class for code concision
  * @returns "pass" if a boolean is true, "failed" else
  */
  private static String p(boolean s) {
    return s ? "passed" : "failed";
  }

  /**
   * Runs all the tester methods
   */
  public static void main(String[] args) {
    System.out.println("BackendDeveloper Individual Test 1: " + p(testAddBook()));
    System.out.println("BackendDeveloper Individual Test 2: " + p(testTitleSearch()));
    System.out.println("BackendDeveloper Individual Test 3: " + p(testAuthFilter()));
    System.out.println("BackendDeveloper Individual Test 4: " + p(testGetISBN()));
    System.out.println("BackendDeveloper Individual Test 5: " + p(testMisc())); 
    System.out.println("BackendDeveloper Integration Test 1: " + p(integrationTest1()));
    System.out.println("BackendDeveloper Integration Test 2: " + p(integrationTest2()));
    System.out.println("BackendDeveloper Partner AlgorithmEngineer Test 1: " + p(testValidator()));
    System.out.println("BackendDeveloper Partner AlgorithmEngineer Test 2: " + p(testIterator()));
  }
}
