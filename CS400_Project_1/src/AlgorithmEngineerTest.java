import java.util.Iterator;
import java.util.List;

/**
 * This tester class will test the new implementation of Algorithm engineer.
 */
public class AlgorithmEngineerTest {
    /**
     * This tester method will check the correctness of iterator in hashtable map class
     *
     * @return true if the behavior is correct, otherwise return false
     */
    public static String test1() {
        {
            HashtableMap test = new HashtableMap(10);
            test.put(1, "bird");
            test.put(2, "dragon");
            test.put(3, "tiger");
            test.put(4, "water");
            test.put(5, "null");
            test.put(6, "java");
            Iterator iterator = test.iterator();
            String sum = "";
            while (iterator.hasNext()) {
                sum += iterator.next();
                //                System.out.println(sum);
            }
            if (!sum.contains("bird") || !sum.contains("dragon") || !sum.contains("tiger")
                || !sum.contains("water") || !sum.contains("null") || !sum.contains("java")) {
                return "failed";
            }
        }

        return "passed";
    }

    /**
     * This tester method will check the correctness of iterator in hashtable map class
     *
     * @return true if the behavior is correct, otherwise return false
     */
    public static String test2() {
        {
            String s = "";
            HashtableMap test = new HashtableMap(10);
            test.put("1", 1);
            test.put("2", 2);
            test.put("3", 3);
            test.put("4", 4);
            test.put("5", 5);
            test.put("6", 6);
            Iterator iterator = test.iterator();
            String sum = "";
            while (iterator.hasNext()) {
                sum += iterator.next();
                //                System.out.println(sum);
            }
            if (!sum.contains("1") || !sum.contains("2") || !sum.contains("3") || !sum.contains("4")
                || !sum.contains("5") || !sum.contains("6")) {
                return "failed";
            }
        }
        return "passed";
    }

    /**
     * This tester method will check the correctness of iterator in hashtable map class
     *
     * @return true if the behavior is correct, otherwise return false
     */
    public static String test3() {
        {
            HashtableMap test = new HashtableMap(10);
            test.put("Orange", "3");
            test.put("Potato", "6");
            test.put(34, "6");
            test.put(23, "String");
            Iterator iterator = test.iterator();
            String sum = "";
            while (iterator.hasNext()) {
                sum += iterator.next();
            }
            if (!sum.contains("3") || !sum.contains("6") || !sum.contains("String")) {
                return "failed";
            }
        }
        return "passed";
    }

    /**
     * This test method is using for to test the IISBN class
     *
     * @return true if the behavior is correct, otherwise return false.
     */
    public static String test4() {
        ISBNValidator test = new ISBNValidator();
        String ISBN = "9780142437346";
        if (false == test.validate(ISBN)) {
            return "failed";
        }
        return "passed";
    }

    /**
     * This test method is using for to test the IISBN class
     *
     * @return true if the behavior is correct, otherwise return false.
     */
    public static String test5() {
        ISBNValidator test = new ISBNValidator();
        String ISBN = "9780141182800";
        if (true == test.validate(ISBN)) {
            return "failed";
        }
        return "passed";
    }

    public static String test8() {
        {
            Book book1 = new Book("Love","Jie Wang" ,"9780330491198");
            Book book2 = new Book("Java17","Jie Wang","9780681403222");
            BookMapperBackend bookMapperBackend = new BookMapperBackend();
            bookMapperBackend.addBook(book1);
            if (1 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.addBook(book2);
            if (2 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.setAuthorFilter("Jie Wang");

            String expectedAuthorBook1 = "Jie Wang";
            String expectedISBNBook1 = "9780330491198";
            if (!expectedAuthorBook1.equals(bookMapperBackend.getAuthorFilter())) {
                return "failed";
            }
            if (null == bookMapperBackend.getByISBN(expectedISBNBook1)) {
                return "failed";
            }

            List<IBook> testlist = bookMapperBackend.searchByTitleWord("v");
            if (testlist.size() != 2) {
                return "failed";
            }

            List<IBook> testlist2 = bookMapperBackend.searchByTitleWord("17");
            if (testlist2.size() != 1) {
                return "failed";
            }
            bookMapperBackend.resetAuthorFilter();
            if (null != bookMapperBackend.getAuthorFilter()) {
                return "failed";
            }


        }

        return "passed";
    }

    public static String test9() {
        {
            Book book1 = new Book("Love","Jie Wang","9781400052929");
            Book book2 = new Book("Java17","Jie Wang","9780739332078");
            Book book3 = new Book("java1755","James","9780345453746"); // invalid
            Book book4 = new Book("888","James","9780345453746");
            BookMapperBackend bookMapperBackend = new BookMapperBackend();
            bookMapperBackend.addBook(book1);
            if (1 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.addBook(book2);
            if (2 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.addBook(book3);
            if (2 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.addBook(book4);
            if (3 == bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.setAuthorFilter("James");

            String expectedAuthorBook1 = "James";
            String expectedISBNBook1 = "9781400052929";
            if (!expectedAuthorBook1.equals(bookMapperBackend.getAuthorFilter())) {
                return "failed";
            }
            if (null == bookMapperBackend.getByISBN(expectedISBNBook1)) {
                return "failed";
            }

            List<IBook> testlist = bookMapperBackend.searchByTitleWord("v");
            if (testlist.size() != 0) {
                return "failed";
            }

            List<IBook> testlist2 = bookMapperBackend.searchByTitleWord("17");
            if (testlist2.size() != 0) {
                return "failed";
            }
            bookMapperBackend.resetAuthorFilter();
            if (null != bookMapperBackend.getAuthorFilter()) {
                return "failed";
            }


        }

        return "passed";
    }

    public static String test6() {
        {
            Book book1 = new Book("Love","Jie Wang","9780330491198");
            Book book2 = new Book("Java17","Jie Wang","9780681403222");
            BookMapperBackend bookMapperBackend = new BookMapperBackend();
            bookMapperBackend.addBook(book1);
            if (1 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.addBook(book2);
            if (2 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }

            String expectedAuthorBook1 = "Jie Wang";
            String expectedISBNBook1 = "9780330491198";
            String expectedTitleBook1 = "Love";

            IBook test = bookMapperBackend.getByISBN(expectedISBNBook1);
            if (!test.getISBN13().equals(expectedISBNBook1) || !test.getAuthors()
                .equals(expectedAuthorBook1) || !test.getTitle().equals(expectedTitleBook1)) {
                return "failed";
            }

            String expectedAuthorBook2 = "Jie Wang";
            String expectedISBNBook2 = "9780681403222";
            String expectedTitleBook2 = "Java17";

            IBook test2 = bookMapperBackend.getByISBN(expectedISBNBook2);
            if (!test2.getISBN13().equals(expectedISBNBook2) || !test2.getAuthors()
                .equals(expectedAuthorBook2) || !test2.getTitle().equals(expectedTitleBook2)) {
                return "failed";
            }


        }
        return "passed";
    }

    public static String test7() {
        {
            Book book1 = new Book("Love","Jay","9780330491198");
            Book book2 = new Book("Java17","Jie Wang","9780681403224"); // invalid ISBN
            BookMapperBackend bookMapperBackend = new BookMapperBackend();
            bookMapperBackend.addBook(book1);
            if (1 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }
            bookMapperBackend.addBook(book2);
            if (1 != bookMapperBackend.getNumberOfBooks()) {
                return "failed";
            }

            String expectedAuthorBook1 = "Jay";
            String expectedISBNBook1 = "9780330491198";
            String expectedTitleBook1 = "Love";

            IBook test = bookMapperBackend.getByISBN(expectedISBNBook1);
            if (!test.getISBN13().equals(expectedISBNBook1) || !test.getAuthors()
                .equals(expectedAuthorBook1) || !test.getTitle().equals(expectedTitleBook1)) {
                return "failed";
            }



            IBook test2 = bookMapperBackend.getByISBN("9780681403224");
            if (test2 != null) {
                return "failed";
            }


        }
        return "passed";
    }

    /**
     * This main method will print the return value from test method above
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("AlgorithmEngineer Individual Test 1: " + test1());
        System.out.println("AlgorithmEngineer Individual Test 2: " + test2());
        System.out.println("AlgorithmEngineer Individual Test 3: " + test3());
        System.out.println("AlgorithmEngineer Individual Test 4: " + test4());
        System.out.println("AlgorithmEngineer Individual Test 5: " + test5());
        System.out.println("AlgorithmEngineer Integration Test 1: " + test6());
        System.out.println("AlgorithmEngineer Integration Test 2: " + test7());
        System.out.println("AlgorithmEngineer Partner (BackendDeveloper) Test 1: " + test8());
        System.out.println("AlgorithmEngineer Partner (BackendDeveloper) Test 2: " + test9());
    }
}
