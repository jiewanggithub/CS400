import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Backend implements IBookMapperBackend{
  private String author = null;
  @Override
  public void addBook(IBook book) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int getNumberOfBooks() {
    // TODO Auto-generated method stub
    return 5;
  }

  @Override
  public void setAuthorFilter(String filterBy) {
    author = filterBy;
    
  }

  @Override
  public String getAuthorFilter() {
    // TODO Auto-generated method stub
    return author;
  }

  @Override
  public void resetAuthorFilter() {
    author = null;
    
  }

  @Override
  public List<IBook> searchByTitleWord(String word) {    
    BookFront book1 = new BookFront("Harry Potter", "JK Rowling", "1243253252367");
    BookFront book2 = new BookFront("Harry Potter 2", "JK Rowling", "1245343242523");
    BookFront book3 = new BookFront("Harry Potter 3", "JK Rowling", "1243431452378");
    BookFront book4 = new BookFront("Percy Jackson", "Rick Riordian", "1243431452379");
    BookFront book5 = new BookFront("Baghwat Geeta", "Krishna", "4204204204202");
    
    List<IBook> books = new ArrayList<>();
    
    if(word.equals("harry")) {
      books.add(book1);
      books.add(book2);
      books.add(book3);
    }
    else if(word.equals("percy")){
      books.add(book4);
    }

    return books;
  }

  @Override
  public IBook getByISBN(String ISBN) {
    BookFront book1 = new BookFront("Harry Potter", "JK Rowling", "1243253252367");
    BookFront book2 = new BookFront("Harry Potter 2", "JK Rowling", "1245343242523");
    BookFront book3 = new BookFront("Harry Potter 3", "JK Rowling", "1243431452378");
    BookFront book4 = new BookFront("Percy Jackson", "Rick Riordian", "1243431452379");
    BookFront book5 = new BookFront("Baghwat Geeta", "Krishna", "4204204204202");
    
    if (ISBN.equals(book1.getISBN13())) {
      return book1;
    }
    else if (ISBN.equals(book2.getISBN13())) {
      return book2;
    }
    else if (ISBN.equals(book3.getISBN13())) {
      return book3;
    }
    else if (ISBN.equals(book4.getISBN13())) {
      return book4;
    }
    else if (ISBN.equals(book5.getISBN13())) {
      return book5;
    }
    
    
    return null;
  }

}
