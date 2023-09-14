// --== CS400 Project One File Header ==--
// Name: <Jie Wang>
// CSL Username: <jiew>
// Email: <jwang2585@wisc.edu>
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;

/**
 * This HashtableMapTests class is using for to test the method created in the HashtableMap class,
 * I used 5 static test method to test them. The first one is using for testing the put method, second one
 * is using for testing the get method. Then rest of three is using for testing bundles of methods.
 */
public class HashtableMapTests {
    /**
     * This method is mainly test about put method and size method in the HashtableMap class
     *
     * @return true if the expected value is equal to the method return value, otherwise,
     * return false
     */
    public static boolean test1() {

        {   // test of put method 1

            HashtableMap test = new HashtableMap(7);
            test.put("Apple", "2");

            // size should equal the expected size 1
            if (false != test.put("Apple", "2") && 1 != test.size()) {
                return false;
            }

        }

        {   // test of put method 2

            HashtableMap test = new HashtableMap(7);
            test.put("Apple", "2");
            test.put("Orange", "3");
            test.put("Potato", 3);
            test.put(5, 3);
            test.put(2.7, 5);
            test.put('w', 'j');

            // size should equal the expected size 6
            if (true != test.put("School", "2") && 6 != test.size()) {
                return false;
            }
        }
        {   // test of put method 3

            HashtableMap test = new HashtableMap();

            // size should equal to expected size 1
            if (true != test.put(2, 3) && 1 != test.size()) {
                return false;
            }
        }
        {   // test of put method 4

            HashtableMap test = new HashtableMap();
            test.put("2", 2);

            // size check !
            return false == test.put("2", 3) || 1 == test.size();
        }
    }

    /**
     * This method is mainly test about get method and size method in the HashtableMap class
     *
     * @return true if the expected value is equal to the method return value, otherwise,
     * return false
     */
    public static boolean test2() {
        {
            // test of get method 1

            // created a hashtable map with 8 pairs of key and value
            // there is a repetitive one, so it won't add into the hashtable
            // the size should be 7
            HashtableMap test = new HashtableMap(7);
            test.put("Apple", "2");
            test.put("Apple", "2");
            test.put("Orange", "3");
            test.put("Potato", 3);
            test.put(5, 3);
            test.put(2.7, 5);
            test.put('w', 'j');
            test.put("7", "7");

            // if the get method don't return the expected one returning false
            try {
                if (!"7".equals(test.get("7")) && 7 != test.size()) {
                    return false;
                }

                // if catch the NoSUchElement Exception return false
            } catch (NoSuchElementException e) {
                return false;
            }

        }
        {
            // test of get method 2

            // Created hashtable map with 8 pairs of key, actual size is 7 due to
            // repetitive Apple
            HashtableMap test = new HashtableMap(7);
            test.put("Apple", "2");
            test.put("Apple", "2");
            test.put("Orange", "3");
            test.put("Potato", 3);
            test.put(5, 3);
            test.put(2.7, 5);
            test.put('w', 'j');
            test.put("7", "7");

            // check the size of test with non-existent pair key
            try {
                if ("7".equals(test.get(7)) && 7 != test.size()) {
                    return false;
                }

                // it should catch the NoSuchElementException
            } catch (NoSuchElementException e) {
                //                    System.out.println(e.getMessage()); // check the message
                return e.getMessage() != null;
            }

        }
        {
            // test of get method 3

            // Created hashtable map with 13 pairs of key, actual size is 12 due to
            // repetitive Apple
            HashtableMap test = new HashtableMap(7);
            test.put(1, "2");
            test.put(2, "2");
            test.put(3, "3");
            test.put(4, 3);
            test.put(5, 3);
            test.put(6, 5);
            test.put(7, "7");
            test.put(8, "7");
            test.put(9, "9");
            test.put("Apple", "2");
            test.put("Apple", "2");
            test.put("Orange", "3");
            test.put("Potato", 3);

            // check get method
            try {
                if (!"7".equals(test.get(7)) && 12 != test.size()) {
                    return false;
                }
            } catch (NoSuchElementException e) {
                return false;
            }

        }
        return true;
    }

    /**
     * This method is mainly test about every method in the HashtableMap class
     *
     * @return true if the expected value is equal to the method return value, otherwise,
     * return false
     */
    public static boolean test3() {
        {
            // Created a HashtableMap with initial capacity of 10;
            HashtableMap test = new HashtableMap(10);

            // Using the put method to add 6 pairs into the hashtable
            test.put(12, "2");
            test.put(13, "2");
            test.put(34, "3");
            test.put(23, "7");
            test.put(6, "2");
            test.put(7, "2");
            test.put(8, "3");
            test.put(9, "7");
            test.put(7, "7"); // key
            test.put(8, "7"); // key
            test.put(9, "9"); // key
            test.put("Apple", "2");
            test.put("Apple", "2"); // key
            test.put("Orange", "3");
            test.put("Potato", 3);
            test.put(34, "6"); // key
            test.put(23, "String"); // key

            // because there are six pairs having duplicate key value, so the there are six pair
            // will not add into the hashtable successfully. So, the size of hashtable
            // should be 11 instead of 17;

            // Check the size of the hashtable
            int expectedSize = 11;
            if (expectedSize != test.size()) {
                return false;
            }

            // Using get method to get the pair value with key "Orange"
            String expectedString = "7";
            try {
                if (!expectedString.equals(test.get(23))) {
                    return false;
                }
            }
            // if we catch NoSuchElementException then we return false
            catch (NoSuchElementException e) {
                return false;
            }

            // Using remove method to remove three pairs to check the size

            expectedSize = 8;
            test.remove(6);
            test.remove(7);
            test.remove(8);

            if (test.size() != expectedSize) {
                return false;
            }

            // using contain key method to check the input of null  expected returning null

            return false == test.containsKey(null);
        }
    }

    /**
     * This method is mainly test about every method in the HashtableMap class
     *
     * @return true if the expected value is equal to the method return value, otherwise,
     * return false
     */
    public static boolean test4() {
        {
            // Created a HashtableMap with initial capacity of 10;
            HashtableMap test = new HashtableMap(10);

            // Using the put method to add 6 pairs into the hashtable
            test.put(1, "2");
            test.put(2, "2");
            test.put("Apple", "2");
            test.put("Apple", "2");
            test.put("Orange", "3");
            test.put("Potato", 3);

            // because there are two pairs with key String App, so the fourth one
            // will not add into the hashtable successfully. So, the size of hashtable
            // should be 5 instead of 6;

            // Check the size of the hashtable
            int expectedSize = 5;
            if (expectedSize != test.size()) {
                return false;
            }

            // Using get method to get the pair value with key "Orange"
            String expectedString = "3";
            try {
                if (!expectedString.equals(test.get("Orange"))) {
                    return false;
                }
            }
            // if we catch NoSuchElementException then we return false
            catch (NoSuchElementException e) {
                return false;
            }

            // Using remove method to remove two pairs to check the size

            expectedSize = 3;
            test.remove("Orange");
            test.remove("Potato");

            if (test.size() != expectedSize) {
                return false;
            }

            // using contain key method to check

            return false != test.containsKey("Apple");
        }
    }

    /**
     * This method is mainly test about every method in the HashtableMap class
     *
     * @return true if the expected value is equal to the method return value, otherwise,
     * return false
     */
    public static boolean test5() {
        {
            // Created a HashtableMap with initial capacity of 7;
            HashtableMap test = new HashtableMap(7);
            // Using the put method to add 8 pairs into the hashtable
            test.put("Apple", "2");
            test.put("Apple", "2");
            test.put("Orange", "3");
            test.put("Potato", 3);
            test.put(5, 3);
            test.put(2.7, 5);
            test.put('w', 'j');
            test.put("7", "7");

            // because there are two pairs with key String App, so the second one
            // will not add into the hashtable successfully. So, the size of hashtable
            // should be 7 instead of 8;

            // Check the size of the hashtable
            int expectedSize = 7;
            if (expectedSize != test.size()) {
                return false;
            }

            // Using get method to get the pair value with key "Orange"
            String expectedString = "3";
            try {
                if (!expectedString.equals(test.get("Orange"))) {
                    return false;
                }
            }
            // if we catch NoSuchElementException then we return false
            catch (NoSuchElementException e) {
                return false;
            }

            // Using remove method to remove two pairs to check the size

            expectedSize = 5;
            test.remove("Orange");
            test.remove("Potato");

            if (test.size() != expectedSize) {
                return false;
            }

            // using contain key method to check

            if (false != test.containsKey("Orange")) {
                return false;
            }
            // clearing every thing in the hash table
            test.clear();
            expectedSize = 0;
            return expectedSize == test.size;
        }
    }

    /**
     * This main method will return the boolean value of 5 testers above
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());

    }
}
