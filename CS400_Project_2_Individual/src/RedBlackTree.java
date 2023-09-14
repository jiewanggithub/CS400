// --== CS400 Fall 2022 File Header Information ==--
// Name: <Jie Wang>
// Email: <jwang2585@wisc.edu>
// Team: <CB>
// TA: <Callie>
// Lecturer: <Gary>
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm. You can use this class' insert method to build a
 * regular binary search tree, and its toString method to display a level-order traversal of the
 * tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree the parent, left, and
     * right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public int blackHeight;

        public Node(T data) {
            this.data = data;
            this.blackHeight = 0;
        }

        /**
         * @return true when this node has a parent and is the left child of that parent, otherwise
         * return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

    }


    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input data value to a new node
     * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
     * balance the tree. This tree will not hold null references, nor duplicate data values.
     *
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain equal data references
     */
    @Override
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if (data == null)
            throw new NullPointerException("This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
            size++;
            root.blackHeight = 1;
            return true;
        } // add first node to an empty tree
        else {
            boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
            if (returnValue) {
                size++;
                root.blackHeight = 1;
            } else {
                throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            }
            return returnValue;
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the position that the
     * newNode should be inserted, and then extend this tree by the newNode in that position.
     *
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the newNode should be inserted
     *                as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if (compare == 0)
            return false;

            // store newNode within left subtree of subtree
        else if (compare < 0) {
            if (subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else
                return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if (subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else
                return insertHelper(newNode, subtree.rightChild);
        }
    }

    /**
     * This method maintains the fundamental properties of Red Black Tree by rotating the nodes and
     * color changes. Removing all the violations appearing the RBT after inserting and adding
     * the new node.
     *
     * @param newNode is the new node added to the Red Black Tree
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
        if (newNode != null && newNode.parent != null && newNode.parent.parent != null
            && newNode.parent.blackHeight == 0) {
            if (newNode.parent.isLeftChild()) {
                // parent's sibling is right child
                // case 3:
                if (newNode.parent.parent.rightChild != null
                    && newNode.parent.parent.rightChild.blackHeight == 0) {
                    newNode.parent.parent.rightChild.blackHeight = 1;
                    newNode.parent.blackHeight = 1;
                    newNode.parent.parent.blackHeight = 0;
                } else {
                    if (!newNode.isLeftChild()) {
                        // parent's sibling and new node is both on right
                        // case 1:
                        rotate(newNode, newNode.parent);
                        newNode = newNode.leftChild;
                    }
                    // parent's sibling is on right and new node is on left
                    // case 2:
                    rotate(newNode.parent, newNode.parent.parent);
                    newNode.parent.blackHeight = 1;
                    newNode.parent.rightChild.blackHeight = 0;
                }

            } else {
                // parent's sibling is left child
                if (newNode.parent.parent.leftChild != null
                    && newNode.parent.parent.leftChild.blackHeight == 0) {
                    newNode.parent.parent.leftChild.blackHeight = 1;
                    newNode.parent.blackHeight = 1;
                    newNode.parent.parent.blackHeight = 0;
                } else {
                    if (newNode.isLeftChild()) {
                        // parent's sibling and new node is both on left
                        // case 1:
                        rotate(newNode, newNode.parent);
                        newNode = newNode.rightChild;
                    }
                    // parent's sibling is on left and new node is on right
                    // case 2:
                    rotate(newNode.parent, newNode.parent.parent);
                    newNode.parent.blackHeight = 1;
                    newNode.parent.leftChild.blackHeight = 0;
                }
            }
            enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
            // recursively check upper level RBTree Properties
        }

    }

    /**
     * Performs the rotation operation on the provided nodes within this tree. When the provided child
     * is a leftChild of the provided parent, this method will perform a right rotation. When the
     * provided child is a rightChild of the provided parent, this method will perform a left
     * rotation. When the provided nodes are not related in one of these ways, this method will throw
     * an IllegalArgumentException.
     *
     * @param child  is the node being rotated from child to parent position (between these two node
     *               arguments)
     * @param parent is the node being rotated from parent to child position (between these two node
     *               arguments)
     * @throws IllegalArgumentException when the provided child and parent node references are not
     *                                  initially (pre-rotation) ;related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        Node<T> grandParent = parent.parent; // grandparent
        boolean isLeftChild = parent.isLeftChild();
        // right rotation
        if (parent.leftChild == child) {
            parent.leftChild = child.rightChild;

            if (parent.leftChild != null) {
                parent.leftChild.parent = parent;
            }
            child.rightChild = parent;
            parent.parent = child;
        }
        // left rotation
        if (parent.rightChild == child) {
            parent.rightChild = child.leftChild;

            if (parent.rightChild != null) {
                parent.rightChild.parent = parent;
            }
            child.leftChild = parent;
            parent.parent = child;
        }
        if (parent == root) {
            root = child;
            child.parent = null;
            return;
        }
        if (isLeftChild == true) {
            child.parent = grandParent;
            grandParent.leftChild = child;
            return;
        }
        child.parent = grandParent;
        grandParent.rightChild = child;
        return;
    }

    /**
     * Get the size of the tree (its number of nodes).
     *
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     *
     * @return true of this.size() return 0, false if this.size() > 0
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     *
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null)
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks for the value *data*.
     *
     * @param data    the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }


    /**
     * This method performs an inorder traversal of the tree. The string representations of each data
     * value within this tree are assembled into a comma separated string within brackets (similar to
     * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note
     * that this RedBlackTree class implementation of toString generates an inorder traversal. The
     * toString of the Node class class above produces a level order traversal of the nodes / values
     * of the tree.
     *
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node) {
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The string
     * representations of each data value within this tree are assembled into a comma separated string
     * within brackets (similar to many implementations of java.util.Collection). Note that the Node's
     * implementation of toString generates a level order traversal. The toString of the RedBlackTree
     * class below produces an inorder traversal of the nodes / values of the tree. This method will
     * be helpful as a helper for the debugging and testing of your rotation implementation.
     *
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while (!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if (next.leftChild != null)
                    q.add(next.leftChild);
                if (next.rightChild != null)
                    q.add(next.rightChild);
                output += next.data.toString();
                if (!q.isEmpty())
                    output += ", ";
            }
        }
        return output + " ]";
    }

    @Override
    public String toString() {
        return "level order: " + this.toLevelOrderString() + "\nin order: "
            + this.toInOrderString();
    }

    /**
     * This is a Test class by using Junit 5
     */
    static class RedBlackTreeTest{
        public static RedBlackTree<Integer> RBT;
        @BeforeEach
        //BeforeEach annotation makes a method invocked automatically
        //before each test
        /**
         * This method will create a RBT instance
         */
        public void createTreeInstance(){
            RBT = new RedBlackTree<Integer>();
        }

        /**
         * The insert 5 numbers on the right subtrees
         *                 2
         *               /   \
         *              1     4
         *             / \   / \
         *                  3   5
         *                 / \   \
         *                        6
         */
        @Test
        void insertFiveNumberOnRightSubtree() {

            RBT.insert(1);
            RBT.insert(2);
            RBT.insert(3);
            RBT.insert(4);
            RBT.insert(5);
            RBT.insert(6);
            assertEquals("[ 2, 1, 4, 3, 5, 6 ]",RBT.toLevelOrderString());
            assertEquals("[ 1, 2, 3, 4, 5, 6 ]",RBT.toInOrderString());
        }
        /**
         * The insert 5 numbers on the left subtrees
         *                 8
         *               /   \
         *              6     9
         *             / \   / \
         *            5   7
         *           / \   \
         *          4
         */
        @Test
        void insertSixNumberOnLeftSubtree() {

            RBT.insert(9);
            RBT.insert(8);
            RBT.insert(7);
            RBT.insert(6);
            RBT.insert(5);
            RBT.insert(4);
            assertEquals("[ 8, 6, 9, 5, 7, 4 ]",RBT.toLevelOrderString());
            assertEquals("[ 4, 5, 6, 7, 8, 9 ]",RBT.toInOrderString());
        }
        /**
         * The insert 5 numbers on the left subtrees
         *                 19
         *               /   \
         *              8     72
         *             / \    / \
         *                  42   3224
         *                 / \    / \
         *               24  64  94 3422
         */
        @Test
        void insertNineSelectedNumber() {

            RBT.insert(19);
            RBT.insert(8);
            RBT.insert(72);
            RBT.insert(94);
            RBT.insert(42);
            RBT.insert(64);
            RBT.insert(3224);
            RBT.insert(3422);
            RBT.insert(24);
            assertEquals("[ 19, 8, 72, 42, 3224, 24, 64, 94, 3422 ]",RBT.toLevelOrderString());
            assertEquals("[ 8, 19, 24, 42, 64, 72, 94, 3224, 3422 ]",RBT.toInOrderString());
        }

        /**
         * Testing the size after inserting 1000 random numbers
         */
        @Test
        void insert1000RandomNumber(){

            Random random = new Random();
            for (int i = 0; i < 1000; i++){
                RBT.insert(i);
            }
            assertEquals(1000,RBT.size());
        }
    }
}

