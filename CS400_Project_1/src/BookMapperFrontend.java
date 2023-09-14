import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookMapperFrontend implements IBookMapperFrontend {
  private Scanner scnr;
  private BookMapperBackend backend;
  private ISBNValidator validator;
  
  public BookMapperFrontend(Scanner userInputScanner, BookMapperBackend backend, ISBNValidator validator) {
    this.scnr = userInputScanner;
    this.backend = backend;
    this.validator = validator;
}
  @Override
  public void runCommandLoop() {
    boolean keepRunning = true;
    boolean valid = false;
    int chosenValue = -1;
    System.out.println("Welcome to the Book Mapper Application!");
    System.out.println("*-**-**-**-**-**-**-**-**-**-**-**-**-*");
    System.out.println();


    while (keepRunning) {
        displayMainMenu();
        while(!valid) {
            try {
                chosenValue = Integer.parseInt(scnr.nextLine());
                if(chosenValue > 0 && chosenValue < 5) {
                    break;
                }
                else {
                  System.out.println("Invalid Value!");
              }
          }
          catch (Exception e) {
              System.out.println("Invalid Value!");
              scnr.nextLine();
          }
      }

      if(chosenValue == 1) {
          isbnLookup();
      }
      else if (chosenValue == 2) {
          titleSearch();
      }
      else if (chosenValue == 3) {
          System.out.println("You are in the Set Author Filter Menu:");
          String currentAuthor = backend.getAuthorFilter();
          if(currentAuthor == null) {
            currentAuthor = "none";
          }       
          System.out.println("Author name must currently contain: " + currentAuthor);
          System.out.println("Enter a new string for author names to contain (empty for any): ");
          String authorFilter;
          try{
              authorFilter = scnr.nextLine();
              backend.setAuthorFilter(authorFilter);
          }
          catch (NoSuchElementException e){
              backend.resetAuthorFilter();
          }
      }
      else if (chosenValue == 4) {
          System.out.println("Thank you for using the book mapper application, Good Bye!");
          keepRunning = false;
      }
      chosenValue = -1;
  }
  }



  @Override
  public void displayMainMenu() {
    System.out.println("\n\n\nYou are in the Main Menu:");
    System.out.println("1) Lookup ISBN\n2) Search by Title Word\n3) Set Author Name Filter\n4) Exit Application"); 
  }



  @Override
  public void displayBooks(List<IBook> books) {
    for (int i = 0; i < books.size(); ++i){
      IBook tempBook = books.get(i);
      String bookTitle = tempBook.getTitle();
      String bookAuthor = tempBook.getAuthors();
      String ISBNNumber = tempBook.getISBN13();

      System.out.println((i+1)+". \"" + bookTitle + "\" by " + bookAuthor + ", ISBN: " + ISBNNumber);
    }
  }



  @Override
  public void isbnLookup() {
    System.out.println("\n\n\nYou are in the Lookup ISBN Menu:\nEnter the ISBN Number: ");
    String ISBNNumber = scnr.nextLine();
    if(validator.validate(ISBNNumber)){
      try {
        IBook returnBook = backend.getByISBN(ISBNNumber);
        String bookTitle = returnBook.getTitle();
        String bookAuthor = returnBook.getAuthors();
        System.out.println("1. \""+ bookTitle +"\" by " + bookAuthor + ", ISBN: " + ISBNNumber);
      }
      catch(Exception e) {
        System.out.println("Invalid ISBN Number");
      }
        return;
    }
    else{
        System.out.println("Invalid ISBN Number");
    }
    
  }



  @Override
  public void titleSearch() {
    System.out.println("\n\n\nYou are in the Search for Title Word Menu:");
    System.out.println("Enter a word to search for in book titles (empty for all books): ");
    String title;
    try{
        title = scnr.nextLine();
    }
    catch (NoSuchElementException e){
        title = "";
    }
    
    List<IBook> books = backend.searchByTitleWord(title);
    String currentAuthor = backend.getAuthorFilter();
    if(currentAuthor == null) {
      currentAuthor = "none";
    }   
    System.out.println("Matches (author filter: " + currentAuthor + ") " + books.size() + " of " + backend.getNumberOfBooks());
    displayBooks(books);
    
    
  }
  

}

