
public class Validator implements IISBNValidator{

  @Override
  public boolean validate(String isbn13) {
    // TODO Auto-generated method stub
    if(isbn13.length() == 13) {
      return true;
    }
    return false;
  }

}
