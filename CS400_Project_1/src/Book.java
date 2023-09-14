public class Book implements IBook{
	String title;
	String authors;
	String isbn13;
	
	public Book(String title, String authors, String isbn13){
		
		this.title = title;
		this.authors = authors;
		this.isbn13 = isbn13;
	}

	@Override
	public String getTitle(){
		return this.title;
	}
	@Override
	public String getAuthors(){
		return this.authors;
	}
	@Override
	public String getISBN13(){
		return this.isbn13;
	}
}
