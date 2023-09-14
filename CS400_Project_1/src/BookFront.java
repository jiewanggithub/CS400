
public class BookFront implements IBook{
  
  private String title;
  private String authors;
  private String ISBN;
  
  public BookFront(String title, String authors, String ISBN) {
    this.title = title;
    this.authors = authors;
    this.ISBN = ISBN;
  }
  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return title;
  }

  @Override
  public String getAuthors() {
    // TODO Auto-generated method stub
    return authors;
  }

  @Override
  public String getISBN13() {
    // TODO Auto-generated method stub
    return ISBN;
  }

}

