import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class FrontendDeveloperTest {
  public static Scanner scnr;

  
  public static boolean test1() {
    //tests the code for runCommandLoop
    
    TextUITester tester = new TextUITester("3\nHello\n3\nSaarthak\n4\n");
    scnr = new Scanner(System.in);
    Backend back = new Backend();
    Validator valid = new Validator();
    Frontend1 front = new Frontend1(scnr, back, valid);
    front.runCommandLoop();
    String output = tester.checkOutput();

    System.out.println(output);
    if((!output.startsWith("Welcome to the Book Mapper Application!")) && (!output.contains("Author name must currently contain: Hello"))) {
      return false;
    }
    
    if((!output.contains("Thank you for using the book mapper application, Good Bye!"))) {
      return false;
    }
    
    return true;
  }
  public static boolean test2() {
    //test Main Menu
    TextUITester tester = new TextUITester("");
    scnr = new Scanner(System.in);
    Backend back = new Backend();
    Validator valid = new Validator();
    Frontend1 front = new Frontend1(scnr, back, valid);
    
    front.displayMainMenu();
    
    String output = tester.checkOutput();
    
    if((!output.contains("You are in the Main Menu:"))) {
      return false;
    }
    if((!output.contains("1) Lookup ISBN"))) {
      return false;
    }
    if((!output.contains("2) Search by Title Word"))) {
      return false;
    }
    if((!output.contains("3) Set Author Name Filter"))) {
      return false;
    }
    if((!output.contains("4) Exit Application"))) {
      return false;
    }
    
    return true;
  }
  public static boolean test3() {
    //tests displaybooks
    TextUITester tester = new TextUITester("");
    scnr = new Scanner(System.in);
    Backend back = new Backend();
    Validator valid = new Validator();
    Frontend1 front = new Frontend1(scnr, back, valid);
    
    List<IBook> books = new ArrayList<>();
    
    BookFront book1 = new BookFront("Harry Potter", "JK Rowling", "12432532523");
    BookFront book2 = new BookFront("Harry Potter 2", "JK Rowling", "1245343242523");
    BookFront book3 = new BookFront("Harry Potter 3", "JK Rowling", "12434314523");
    
    books.add(book1);
    books.add(book2);
    books.add(book3);
    
    
    front.displayBooks(books);
    
    String output = tester.checkOutput();
    
    if((!output.contains("1. \"Harry Potter\" by JK Rowling, ISBN: 12432532523"))) {
      System.out.println("hello");
      return false;
    }
    if((!output.contains("2. \"Harry Potter 2\" by JK Rowling, ISBN: 1245343242523"))) {
      return false;
    }
    if((!output.contains("3. \"Harry Potter 3\" by JK Rowling, ISBN: 12434314523"))) {
      return false;
    }
    
    return true;
  }
  public static boolean test4() {
    //tests ISBNLookup
    TextUITester tester = new TextUITester("1243253252367\n1245343242523\n1243431452379\n12312\n");
    scnr = new Scanner(System.in);
    Backend back = new Backend();
    Validator valid = new Validator();
    Frontend1 front = new Frontend1(scnr, back, valid);
    
    
    BookFront book1 = new BookFront("Harry Potter", "JK Rowling", "1243253252367");
    BookFront book2 = new BookFront("Harry Potter 2", "JK Rowling", "1245343242523");
    BookFront book3 = new BookFront("Harry Potter 3", "JK Rowling", "1243431452378");
    BookFront book4 = new BookFront("Percy Jackson", "Rick Riordian", "1243431452379");
    BookFront book5 = new BookFront("Baghwat Geeta", "Krishna", "4204204204202");
    
    front.isbnLookup();
    front.isbnLookup();
    front.isbnLookup();
    front.isbnLookup();
    String output = tester.checkOutput();
    if((!output.contains("1. \"Harry Potter\" by JK Rowling, ISBN: 1243253252367"))) {
      return false;
    }
    if((!output.contains("1. \"Harry Potter 2\" by JK Rowling, ISBN: 1245343242523"))) {
      return false;
    }
    if((!output.contains("1. \"Percy Jackson\" by Rick Riordian, ISBN: 1243431452379"))) {
      return false;
    }
    if((!output.contains("Enter the ISBN Number: "))) {
      return false;
    }
    if((!output.contains("Invalid ISBN Number"))) {
      return false;
    }
    return true;
  }
  
  
  public static boolean test5() {
    //tests searchtitle
    TextUITester tester = new TextUITester("harry\npercy\nhello\n");
    scnr = new Scanner(System.in);
    Backend back = new Backend();
    Validator valid = new Validator();
    Frontend1 front = new Frontend1(scnr, back, valid);
    
    
    BookFront book1 = new BookFront("Harry Potter", "JK Rowling", "1243253252367");
    BookFront book2 = new BookFront("Harry Potter 2", "JK Rowling", "1245343242523");
    BookFront book3 = new BookFront("Harry Potter 3", "JK Rowling", "1243431452378");
    BookFront book4 = new BookFront("Percy Jackson", "Rick Riordian", "1243431452379");
    BookFront book5 = new BookFront("Baghwat Geeta", "Krishna", "4204204204202");
    
    front.titleSearch();
    front.titleSearch();
    front.titleSearch();
    
    
    String output = tester.checkOutput();
    if((!output.contains("Matches (author filter: none) 3 of 5"))) {
      return false;
    }
    if((!output.contains("Matches (author filter: none) 1 of 5"))) {
      return false;
    }
    if((!output.contains("3. \"Harry Potter 3\" by JK Rowling, ISBN: 1243431452378"))) {
      return false;
    }
    return true;
  }
  
  public static boolean test6() {
    //testing with backend for ISBN Lookup
    
    TextUITester tester = new TextUITester("9780517149256\n9780618517657\n9780618510825\n13221312\n");
    scnr = new Scanner(System.in);
    BookMapperBackend back = new BookMapperBackend();
    ISBNValidator valid = new ISBNValidator();
    BookMapperFrontend front = new BookMapperFrontend(scnr, back, valid);
    
    Book book1 = new Book("Hatchet (Brian's Saga  #1)", "Gary Paulsen", "9780517149256");
    Book book2 = new Book("The Changeling Sea", "Patricia A. McKillip", "9780618517657");
    Book book3 = new Book("The Lord of the Rings: Complete Visual Companion", "Jude Fisher", "9780618510825");
    
    back.addBook(book1);
    back.addBook(book2);
    back.addBook(book3);
    
    try {
      front.isbnLookup();
      front.isbnLookup();
      front.isbnLookup();
      front.isbnLookup();
    }
    catch(Exception e){
      return false;
    }
    
    String output = tester.checkOutput();
    
    if((!output.contains("1. \"Hatchet (Brian's Saga  #1)\" by Gary Paulsen, ISBN: 9780517149256"))) {
      return false;
    }
    if((!output.contains("1. \"The Changeling Sea\" by Patricia A. McKillip, ISBN: 9780618517657"))) {
      return false;
    }
    if((!output.contains("1. \"The Lord of the Rings: Complete Visual Companion\" by Jude Fisher, ISBN: 9780618510825"))) {
      return false;
    }
    if((!output.contains("Invalid ISBN Number"))) {
      return false;
    }
    
    return true;
  }
  
  public static boolean test7() {
    //testing with backend for title search
    
    TextUITester tester = new TextUITester("Harry\nChangeling\nhello\n");
    scnr = new Scanner(System.in);
    BookMapperBackend back = new BookMapperBackend();
    ISBNValidator valid = new ISBNValidator();
    BookMapperFrontend front = new BookMapperFrontend(scnr, back, valid);
    
    Book book1 = new Book("Harry Potter and the Half-Blood Prince (Harry Potter  #6)","J.K. Rowling/Mary GrandPrÃ©", "9780439785969");
    Book book2 = new Book("Harry Potter and the Chamber of Secrets (Harry Potter  #2)", "J.K. Rowling/Mary GrandPrÃ©", "9780439554893");
    Book book3 = new Book("Harry Potter and the Prisoner of Azkaban (Harry Potter  #3)", "J.K. Rowling/Mary GrandPrÃ©", "9780439655484");
    
    Book book4 = new Book("The Changeling", "Zilpha Keatley Snyder", "9780595321803");
    
    back.addBook(book1);
    back.addBook(book2);
    back.addBook(book3);
    back.addBook(book4);
    
    
    try {
      front.titleSearch();
      front.titleSearch();
      front.titleSearch();
    }
    catch(Exception e){
      return false;
    }
    
    String output = tester.checkOutput();

    if((!output.contains("\"Harry Potter and the Half-Blood Prince (Harry Potter  #6)\" by J.K. Rowling/Mary GrandPrÃ©, ISBN: 9780439785969"))) {
      return false;
    }
    if((!output.contains("\"Harry Potter and the Chamber of Secrets (Harry Potter  #2)\" by J.K. Rowling/Mary GrandPrÃ©, ISBN: 9780439554893"))) {
      return false;
    }
    if((!output.contains("\"Harry Potter and the Prisoner of Azkaban (Harry Potter  #3)\" by J.K. Rowling/Mary GrandPrÃ©, ISBN: 9780439655484"))) {
      return false;
    }
    if((!output.contains("\"The Changeling\" by Zilpha Keatley Snyder, ISBN: 9780595321803"))) {
      return false;
    }
    if((!output.contains("Matches (author filter: none) 0"))) {
      return false;
    }
    
    return true;
  }
  
  public static boolean test8() {
    //testing with Book class for Data Wrangler 
    
    Book book1 = new Book("Harry Potter and the Chamber of Secrets (Harry Potter  #2)","J.K. Rowling", "9780439554893");
    
    if (!book1.getTitle().equals("Harry Potter and the Chamber of Secrets (Harry Potter  #2)")) {
      return false;
    }
    if (!book1.getAuthors().equals("J.K. Rowling")) {
      return false;
    }
    if (!book1.getISBN13().equals("9780439554893")) {
      return false;
    }
    
    //Checking authors with multiple commas
    Book book2 = new Book("Streetcar Suburbs: The Process of Growth in Boston  1870-1900", "Sam Bass Warner, Jr./Sam B. Warner", "9780674842113");
    
    if (!book2.getTitle().equals("Streetcar Suburbs: The Process of Growth in Boston  1870-1900")) {
      return false;
    }
    if (!book2.getAuthors().equals("Sam Bass Warner, Jr./Sam B. Warner")) {
      return false;
    }
    if (!book2.getISBN13().equals("9780674842113")) {
      return false;
    }

    //Checking with values that are empty / null
    Book book3 = new Book("Harry Potter and the Chamber of Secrets (Harry Potter  #2)","", "9780439554893");
    
    if (!book3.getTitle().equals("Harry Potter and the Chamber of Secrets (Harry Potter  #2)")) {
      return false;
    }
    if (!book3.getAuthors().equals("")) {
      return false;
    }
    if (!book3.getISBN13().equals("9780439554893")) {
      return false;
    }

    
    return true;
  }
  public static boolean test9() {
    //testing with BookLoader class for Data Wrangler 
    BookLoader loader = new BookLoader();
    
    //testing for FileNotFoundException
    
    try {
      loader.loadBooks("asdasdasdsa");
    } catch(FileNotFoundException e) {
      
    }
    catch (Exception e) {
      return false;
    }
    
    
    //testing loading in of books.csv
    List<IBook> list = new ArrayList<>();
    try {
      list = loader.loadBooks("books.csv");
    }
    catch (Exception e) {
      return false;
    }
    
    
    //testing first value
    if(!list.get(0).getTitle().equals("Harry Potter and the Half-Blood Prince (Harry Potter  #6)")) {
      return false;
    }
    //testing middle value
    if(!list.get(66).getTitle().equals("Chapterhouse: Dune (Dune Chronicles #6)")) {
      return false;
    }
    
    //testing authors
    if(!list.get(1476).getAuthors().equals("Julie Mullaney")) {
      return false;
    }
    if(!list.get(320).getAuthors().equals("Mike   Mason")) {
      return false;
    }
    
    //testing ISBN Number
    if(!list.get(283).getISBN13().equals("9780060887964")) {
      return false;
    }
    if(!list.get(407).getISBN13().equals("9780140447941")) {
      return false;
    }
    
    //testing author's with commas
    if(!list.get(3347).getAuthors().equals("Sam Bass Warner, Jr./Sam B. Warner")) {
      System.out.println("\nDoes not account for edge case, where authors have commas.");
      return false;
    }
    if(!list.get(4701).getAuthors().equals("David E. Smith (Turgon of TheOneRing.net, one of the founding members of this Tolkien website)/Verlyn Flieger/Turgon (=David E. Smith)")) {
      System.out.println("\nDoes not account for edge case, where authors have commas.");
      return false;
    }
    
    return true;
  }
  
  
  
  public static void main(String[] args) {
    if(test1()) {
      System.out.println("Frontend Individual Test 1 Passed");
    }
    else {
      System.out.println("Frontend Individual Test 1 Failed");
    }
    if(test2()) {
      System.out.println("Frontend Individual Test 2 Passed");
    }
    else {
      System.out.println("Frontend Individual Test 2 Failed");
    }
    if(test3()) {
      System.out.println("Frontend Individual Test 3 Passed");
    }
    else {
      System.out.println("Frontend Individual Test 3 Failed");
    }
    if(test4()) {
      System.out.println("Frontend Individual Test 4 Passed");
    }
    else {
      System.out.println("Frontend Individual Test 4 Failed");
    }
    if(test5()) {
      System.out.println("Frontend Individual Test 5 Passed");
    }
    else {
      System.out.println("Frontend Individual Test 5 Failed");
    }
    if(test6()) {
      System.out.println("Frontend Integration Test 1 Passed");
    }
    else {
      System.out.println("Frontend Integration Test 1 Failed");
    }
    if(test7()) {
      System.out.println("Frontend Integration Test 2 Passed");
    }
    else {
      System.out.println("Frontend Integration Test 2 Failed");
    }
    if(test8()) {
      System.out.println("Frontend Partner (Data Wrangler) Test 1 Passed");
    }
    else {
      System.out.println("Frontend Partner (Data Wrangler) Test 1 Failed");
    }
    if(test9()) {
      System.out.println("Frontend Partner (Data Wrangler) Test 2 Passed");
    }
    else {
      System.out.println("Frontend Partner (Data Wrangler) Test 2 Failed");
    }
  }
}
