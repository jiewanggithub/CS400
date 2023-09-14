import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * Instances of this class can be used to load books from a CSV file
 * into a list of book objects.
 * This class implements the IBookLoader interface.
 */
public class BookLoader implements IBookLoader{

    /**
     * This method loads the list of books from a CSV file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filepathToCSV), "UTF-8");
        scanner.useDelimiter(",");
        String[] firstLine = scanner.nextLine().split(",");
        int title = Arrays.asList(firstLine).indexOf("title");
        int authors = Arrays.asList(firstLine).indexOf("authors");
        int ISBN13 = Arrays.asList(firstLine).indexOf("isbn13");
        List<IBook> list = new ArrayList<IBook>();

        while(scanner.hasNext()) {
            String[] line = scanner.nextLine().split(",");

            String str1 = line[title];
            String str2 = line[authors];
            String str3 = line[ISBN13];

            str1 = String.join("", str1.split("/"));
            str1 = String.join("", str1.split("\\?"));
            str1 = String.join("\"", str1.split("\\\""));
            str2 = String.join("", str2.split("\""));
            str2 = String.join("", str2.split("\\\""));

            Book book = new Book(str1, str2, str3);
            list.add(book);
        }
        scanner.close();

        return list;
    }

    }




