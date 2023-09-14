// --== CS400 Project Three File Header ==--
// Name: Xuexuan Wang
// CSL Username: xuexuan
// Email: xwang2568@wisc.edu
// Notes to Grader: <any optional extra notes to your grader>

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a junit 5 tester class
 */
class HashtableMapTests {
    public static HashtableMap<Integer,Integer> test;
    @BeforeEach
    public void createInstance(){
         test = new HashtableMap();
    }

    /**
     * Test put method
     */
    @Test
    void put() {
        for (int i = 0; i < 100; i++){
            test.put(i,i);
            assertEquals(i+1,test.getSize());
        }
    }
    /**
     * Test containsKey method
     */
    @Test
    void containsKey() {
        test.put(11,11);
        assertEquals(true,test.containsKey(11));
        assertEquals(false,test.containsKey(12));}
    /**
     * Test get method
     */
    @Test
    void get() {
        for (int i = 0; i < 100; i++){
            test.put(i,i);
            assertEquals(i+1,test.getSize());
            assertEquals(i,test.get(i));
        }

    }
    /**
     * Test remove method
     */
    @Test
    void remove() {
        for (int i = 0; i < 100; i++){
            test.put(i,i);
            assertEquals(i+1,test.getSize());
        }
        for (int i = 0; i < 100; i++){
            test.remove(i);
        }
        assertEquals(0,test.getSize());
    }
    /**
     * Test clear method
     */
    @Test
    void clear() {
        for (int i = 0; i < 1000; i++){
            test.put(i,i);
            assertEquals(i+1,test.getSize());
        }
        test.clear();
        assertEquals(0,test.getSize());
    }
    /**
     * Test getSize method
     */
    @Test
    void getSize() {
        for (int i = 0; i < 100; i++){
            test.put(i,i);
            assertEquals(i+1,test.getSize());
        }
    }
    /**
     * Test getCapacity method
     */
    @Test
    void getCapacity() {
        assertEquals(8,test.getCapacity());
    }
}