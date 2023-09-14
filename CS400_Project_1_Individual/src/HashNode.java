// --== CS400 Project Three File Header ==--
// Name: Xuexuan Wang
// CSL Username: xuexuan
// Email: xwang2568@wisc.edu
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class is using for creating node pair of KeyType and ValueType
 *
 * @param <KeyType>   the type of key when creating a new HashNode
 * @param <ValueType> is the value type stored in the linked list in every array index
 * @author Xuexuan Wang
 */
public class HashNode<KeyType, ValueType> {
    KeyType key;
    ValueType value;

    /**
     * This is a HashNode constructor that using for hold the key and value in different type
     *
     * @param key   is the key type for using searching, contain, and other methods
     * @param value is the value stored in the linked list in every array index
     */
    public HashNode(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    /**
     * This is a getter method of key
     *
     * @return the key in KeyType
     */
    public KeyType getKey() {
        return key;
    }

    /**
     * This is a getter method of value
     *
     * @return the value in ValueType
     */
    public ValueType getValue() {
        return value;
    }
}
