// --== CS400 Project Three File Header ==--
// Name: Xuexuan Wang
// CSL Username: xuexuan
// Email: xwang2568@wisc.edu
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;

/**
 * This is a class that will implement the hashtable with technique of open addresing with linear
 * probing, this class will contain the general methods of hashtable map, including put,remove,
 * contain and clear and so on.
 *
 * @author Xuexuan wang
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    protected HashNode<KeyType, ValueType>[] hashTable;
    int size;
    int capacity;
    double loadFactor;

    /**
     * This is a constructor one that will create hashtable map object
     *
     * @param capacity hashtable map length
     */
    public HashtableMap(int capacity) {
        hashTable = new HashNode[capacity];
        this.capacity = capacity;
        this.loadFactor = 0;
    }

    /**
     * This is a constructor two that will create hashtable map object with default capacity
     */
    public HashtableMap() {
        this(8);
    }

    /**
     * This is a rehashing function when the loadfactor of this hashtablemap equal or bigger than
     * 0.7.
     */
    private void rehash() {
        capacity = 2 * capacity;

        HashNode<KeyType, ValueType>[] newHashTable = new HashNode[capacity];
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                int newIndex = Math.abs(hashTable[i].getKey().hashCode() % capacity);
                newHashTable[newIndex] = hashTable[i];
                hashTable[i] = null;
            }
        }
        hashTable = newHashTable;
        this.loadFactor = (double) this.size / this.capacity;
    }

    /**
     * This is a put method overried from the mapADT, the functionality of this method is to insert
     * a new key-value pair into the hashtable map
     *
     * @param key   The key will need to used to find index
     * @param value The value will be store in the hashtable map
     */
    @Override
    public void put(KeyType key, ValueType value) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (containsKey(key)) {
            throw new IllegalArgumentException("key is already in the table");
        }
        int index = (Math.abs(key.hashCode() % capacity));
        int temp = index;
        HashNode<KeyType, ValueType> newOne = new HashNode<KeyType, ValueType>(key, value);
        do {
            if (hashTable[index] == null) {
                hashTable[index] = newOne;
                size++;
                this.loadFactor = (double) this.size / this.capacity;
                if (loadFactor >= 0.7) {
                    this.rehash(); // rehash if needed
                }
                return;
            }

            index = (index + 1) % capacity;
        } while (index != temp);
    }

    /**
     * This is a method will check wether a key is in the hashtable map
     *
     * @param key key used to search in the hashtable map
     * @return true if found,otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        if (key == null) {
            return false;
        }
        for (int i = 0; i < this.getCapacity(); i++) {
            if (hashTable[i] != null && hashTable[i].getKey() != null && hashTable[i].getKey()
                    .equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This is a method to get the value relative a specific key
     *
     * @param key key to search
     * @return value in hashtable map
     * @throws NoSuchElementException if no element was found
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        if (key == null || !containsKey(key)) {
            throw new NoSuchElementException("The key is null");
        }
        for (int i = 0; i < this.getCapacity(); i++) {
            if (hashTable[i] != null && hashTable[i].getKey() != null && hashTable[i].getKey()
                    .equals(key)) {
                return hashTable[i].getValue();
            }
        }
        throw new NoSuchElementException("not found value");
    }

    /**
     * This is a method to remove a specific key value pair in the hashtbale map
     *
     * @param key key to search
     * @return the value that be removed
     * @throws NoSuchElementException if no element was found
     */
    @Override
    public ValueType remove(KeyType key) throws NoSuchElementException {
        if (key == null || !containsKey(key)) {
            throw new NoSuchElementException("not in this collection");
        }

        ValueType toReturn = null;
        for (int index = (Math.abs(key.hashCode() % capacity));
             index < hashTable.length; index = (index + 1) % capacity) {
            if (hashTable[index] != null && hashTable[index].getKey().equals(key)) {
                toReturn = hashTable[index].getValue();
                hashTable[index] = null;
                break;
            }
        }
        size--;
        loadFactor = (double) this.size / this.capacity;
        return toReturn;
    }

    /**
     * This is a clear method to delete all key-value pair in the hashtable map
     */
    @Override
    public void clear() {
        size = 0;
        this.loadFactor = (double) this.size / this.capacity;
        hashTable = new HashNode[capacity];
    }

    /**
     * This is a method to return the size of the hashtable map
     *
     * @return hashtable map size
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * This is a method return a capacity
     *
     * @return capacity
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
