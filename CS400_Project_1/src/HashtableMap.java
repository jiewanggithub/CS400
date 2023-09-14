// --== CS400 Project One File Header ==--
// Name: <Jie Wang>
// CSL Username: <jiew>
// Email: <jwang2585@wisc.edu>
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This HashtableMap class is using for implementing the chaining technique when using hashmap
 * Creating a few methods to accomplish the hashtable map
 *
 * @param <KeyType>
 * @param <ValueType>
 * @author Jie Wang
 */
public class HashtableMap<KeyType, ValueType> implements IterableMapADT<KeyType, ValueType> {
    protected LinkedList<HashNode<KeyType, ValueType>>[] hashTable;
    int size;
    int capacity;
    double loadFactor;

    // constructor
    public HashtableMap(int capacity) {
        hashTable = new LinkedList[capacity];
        this.capacity = capacity;

        // creating linked list in every index of the array
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new LinkedList<HashNode<KeyType, ValueType>>();
        }
    }

    // constructor with 15 buckets
    public HashtableMap() {
        this(15);   // with default capacity = 15
    }

    /**
     * THis helper method helps us to rehash the hashtable map when the load factor is
     * bigger than 70%
     */
    private void helper() {
        capacity = 2 * capacity;
        LinkedList<HashNode<KeyType, ValueType>>[] newHashTable = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            newHashTable[i] = new LinkedList<HashNode<KeyType, ValueType>>();
        }

        // rehashing
        for (int i = 0; i < hashTable.length; i++) {
            for (int j = 0; j < hashTable[i].size(); j++) {
                int newIndex = Math.abs(hashTable[i].get(j).getKey().hashCode() % capacity);
                newHashTable[newIndex].add(hashTable[i].get(j));
            }
        }
        hashTable = newHashTable;
        loadFactor = (double) size / capacity;
    }

    /**
     * This method will insert a new HashNode pair into the hashtable map
     *
     * @param key   the key of the (key, value) pair to store
     * @param value the value that the key will map to
     * @return true the pair was successfully added, otherwise
     * return false
     */
    @Override public boolean put(KeyType key, ValueType value) {

        int index; // The index for the key to add in the array
        if (key == null) {
            // System.out.println("The key is null.");
            return false;
        }
        if (containsKey(key) == true) {
            // System.out.println("The key has already exists.");
            return false;
        }
        index = (Math.abs(key.hashCode() % capacity));
        HashNode<KeyType, ValueType> newOne = new HashNode<KeyType, ValueType>(key, value);
        hashTable[index].add(newOne);
        size++;
        loadFactor = (double) size / capacity; // update loadfactor
        if (loadFactor >= 0.7) {
            this.helper(); // rehash if needed
        }
        return true;
    }

    /**
     * This method will return the value in ValueType when the key is found in our hashtable map
     * otherwise throwing NoSuchElementException
     *
     * @param key the key for which to look up the value
     * @return the ValueTYpe value if the key is found, otherwise throw exception
     * @throws NoSuchElementException
     */
    @Override public ValueType get(KeyType key) throws NoSuchElementException {
        if (key == null) {
            throw new NoSuchElementException("The key is null");
        }
        int index = (Math.abs(key.hashCode() % capacity));
        for (int i = 0; i < hashTable[index].size(); i++) {
            if (hashTable[index].get(i).getKey().equals(key)) {
                return hashTable[index].get(i).getValue();
            }

        }
        throw new NoSuchElementException("We didn't find the key");
    }

    /**
     * This method will remove the pair node by using the key to find in the hashtable map
     *
     * @param key the key for the (key, value) pair to remove
     * @return null if the key is null or the key didn't find in the hashtable map, if found
     * return the corresponding key in the pair
     */
    @Override public ValueType remove(KeyType key) {
        if (key == null) {
            return null;
        }
        int index = (Math.abs(key.hashCode() % capacity));
        for (int i = 0; i < hashTable[index].size(); i++) {
            if (hashTable[index].get(i).getKey().equals(key)) {
                ValueType value = hashTable[index].get(i).getValue();
                hashTable[index].remove(i);
                size--;
                loadFactor = (double) size / capacity;
                return value;
            }

        }
        return null;
    }

    /**
     * This method is using for to check whether the hashtable map containing one key
     *
     * @param key the key to check for
     * @return false if the key is equal to null or doesn't exist otherwise return true
     */
    @Override public boolean containsKey(KeyType key) {
        if (key == null) {
            return false;
        }
        int index = (Math.abs(key.hashCode() % capacity));
        for (int i = 0; i < hashTable[index].size(); i++) {
            if (hashTable[index].get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is using for getting the size of the hashtable
     *
     * @return the size of the hashtable map
     */
    @Override public int size() {
        return this.size;
    }

    /**
     * This method is using for clearing the whole hashtable map
     */
    @Override public void clear() {
        for (int i = 0; i < capacity; i++) {
            hashTable[i].clear();
        }
        size = 0;
        loadFactor = (double) size / capacity;
    }

    @Override
    //    public Iterator<ValueType> iterator() {
    //        Iterator<ValueType> iterator = new Iterator<ValueType>() {
    //            int sizeTrack = 0;
    //            int mapIndexTrack = 0;
    //            int bucketLastIndex = 0;
    //            int bucketIndexTrack = 0;
    //            @Override
    //            public boolean hasNext() {
    //                return sizeTrack  < size();
    //            }
    //
    //            @Override
    //            public ValueType next() {
    //                ValueType valueToReturn = null;
    //                while(hasNext()) {
    //                    bucketLastIndex = (hashTable[mapIndexTrack].size() - 1);
    //                    if (hashTable[mapIndexTrack].size() >= 1 && bucketLastIndex >= bucketIndexTrack ) {
    //                        valueToReturn = hashTable[mapIndexTrack].get(bucketIndexTrack).getValue();
    //                        bucketIndexTrack++;
    //                        sizeTrack++;
    //                        return valueToReturn;
    //                    }
    //                    else{
    //                        mapIndexTrack++;
    //                        bucketIndexTrack = 0;
    //                    }
    //                }
    //                return valueToReturn;
    //            }
    //        };
    //
    //        return iterator;
    //    }



    /**
     * Anonymous class version of iterator
     */ public Iterator<ValueType> iterator() {
        return new Iterator<ValueType>() {
            int sizeTrack = 0;
            int mapIndexTrack = 0;
            int bucketLastIndex = 0;
            int bucketIndexTrack = 0;

            @Override public boolean hasNext() {
                return sizeTrack < size();
            }

            @Override public ValueType next() {
                ValueType valueToReturn = null;
                while (hasNext()) {
                    bucketLastIndex = (hashTable[mapIndexTrack].size() - 1);
                    if (hashTable[mapIndexTrack].size() >= 1
                        && bucketLastIndex >= bucketIndexTrack) {
                        valueToReturn = hashTable[mapIndexTrack].get(bucketIndexTrack).getValue();
                        bucketIndexTrack++;
                        sizeTrack++;
                        return valueToReturn;
                    } else {
                        mapIndexTrack++;
                        bucketIndexTrack = 0;
                    }
                }
                return valueToReturn;
            }
        };
    }
}
