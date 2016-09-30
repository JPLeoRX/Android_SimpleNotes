package com.tekleo.simple_notes.util.data_structures;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Leo on 22-Jan-16.
 */

/**
 * Binary Search Tree is a data structure where each node can have zero, one, or two children
 * Any value less than the node�s value goes to the left child (or a child of the left child)
 * Any value greater than, or equal to, the node�s value goes to the right child (or a child thereof)
 * @param <E> type of objects stored
 */
public class BinarySearchTree<E extends Comparable> implements Iterable<E>, Serializable
{
    // Traversal types
    public static final int TRAVERSAL_IN_ORDER = 1;
    public static final int TRAVERSAL_PRE_ORDER = 2;
    public static final int TRAVERSAL_POST_ORDER = 3;

    private BinaryTreeNode<E> root;                                     // Root of the tree
    private int size;                                                   // Number of elements in the tree

    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------- Tree --------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Adds the provided value to the correct location within the tree
     * Performance: O(log n) usual
     *              O(n) worst case
     * @param item value to be added
     */
    public void add(E item) {
        if (root == null)                                               // If the tree is empty
            root = new BinaryTreeNode<>(item);                          // Insert new node as a root
        else                                                            // If the tree is not empty
            addRec(root, item);                                         // Recursively find the right location to insert the node
        size++;                                                         // Increase the size
    }

    /**
     * Removes the first node found with the indicated value
     * Performance: O(log n) usual
     *              O(n) worst case
     * @param item value to be removed
     * @return true if the value was removed, false otherwise
     */
    public boolean remove(E item) {
        BinaryTreeNode<E> removedNode = this.find(item);                            // Find the node to be removed
        if (removedNode == null)                                                    // If node was not found
            return false;                                                           // Exit

        // Case One: The node to be removed has no right child
        // In this case, the removal operation can simply move the left child, if there is one, into the place of the removed node
        if (removedNode.hasNoRightChild()) {
            if (removedNode.hasNoParent())
                root = removedNode.getLeft();
            else if (removedNode.isParentsLeft())
                removedNode.getParent().setLeft(removedNode.getLeft());
            else if (removedNode.isParentsRight())
                removedNode.getParent().setRight(removedNode.getLeft());
        }


        // Case Two: The node to be removed has a right child which, in turn, has no left child
        // In this case, we want to move the removed node�s right child into the place of the removed node
        else if (removedNode.hasRightChild() && removedNode.getRight().hasNoLeftChild()) {
            removedNode.getRight().setLeft(removedNode.getLeft());
            if (removedNode.hasNoParent())
                root = removedNode.getRight();
            else if (removedNode.isParentsLeft())
                removedNode.getParent().setLeft(removedNode.getRight());
            else if (removedNode.isParentsRight())
                removedNode.getParent().setRight(removedNode.getRight());
        }

        // Case Three: The node to be removed has a right child which, in turn, has a left child
        // In this case, the left-most child of the removed node�s right child must be placed into the removed node�s slot
        else if (removedNode.hasRightChild() && removedNode.getRight().hasLeftChild()) {
            BinaryTreeNode<E> leftmost = removedNode.getRight().findLeftMostChild();            // Find the right's left-most child.
            leftmost.getParent().setLeft(leftmost.getRight());                                  // The parent's left subtree becomes the leftmost's right subtree.
            leftmost.setLeft(removedNode.getLeft());                                            // Assign leftmost's left to current's left children.
            leftmost.setRight(removedNode.getRight());                                          // Assign leftmost's right to current's right children.
            if (removedNode.hasNoParent())
                root = leftmost;
            else if (removedNode.isParentsLeft())
                removedNode.getParent().setLeft(leftmost);
            else if (removedNode.isParentsRight())
                removedNode.getParent().setRight(leftmost);
        }

        else                                                                                    // Invalid state
            throw new IllegalStateException("Tree is in invalid state!");                       // Throw an exception
        size--; return true;                                                                    // Decrease the size and exit
    }

    /**
     * Determines whether the given value is in the tree
     * @param item value to be found
     * @return true if the value was found, false otherwise
     */
    public boolean contains(E item) {
        BinaryTreeNode<E> current = root;                                                       // Start from the root
        while (current != null) {                                                               // Looping until the tree ends
            if (current.compareTo(item) > 0)                                                    // If value less than current node
                current = current.getLeft();                                                    // Go to the left
            else if (current.compareTo(item) < 0)                                               // If value greater than current node
                current = current.getRight();                                                   // Go to the right
            else                                                                                // If the value is equal to the current node
                return true;                                                                    // The value was found, exit the loop
        }
        return false;                                                                           // The value was not found
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- Helpers ------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Recursive call to add value to a tree
     * @param currentNode current node of the tree
     * @param data value to be added
     */
    private void addRec(BinaryTreeNode<E> currentNode, E data) {
        if (data.compareTo(currentNode.getData()) < 0)                  // Case 1: Value is less than the current node value
            addLeft(currentNode, data);                                 // Try adding it to the left
        else                                                            // Case 2: Value is equal to or greater than the current value.
            addRight(currentNode, data);                                // Try adding it to the right
    }

    /**
     * Add to the left of the current node
     * @param currentNode current node of the tree
     * @param data value to be added
     */
    private void addLeft(BinaryTreeNode<E> currentNode, E data) {
        if (currentNode.hasNoLeftChild())                               // If there is no left child, make it the new left child
            currentNode.setLeft(new BinaryTreeNode<E>(data));           // Assign new left child, finish recursion
        else                                                            // Else add it to the left child node
            addRec(currentNode.getLeft(), data);                        // Continue recursive call
    }

    /**
     * Add to the right of the current node
     * @param currentNode current node of the tree
     * @param data value to be added
     */
    private void addRight(BinaryTreeNode<E> currentNode, E data) {
        if (currentNode.hasNoRightChild())                              // If there is no right child, make it the new right child
            currentNode.setRight(new BinaryTreeNode<E>(data));          // Assign new right child, finish recursion
        else                                                            // Else add it to the right child node
            addRec(currentNode.getRight(), data);                       // Continue recursive call
    }

    /**
     * Find the node corresponding to given value
     * @param item value to be found
     * @return the node of this value, null if the value was not found
     */
    private BinaryTreeNode<E> find(E item) {
        BinaryTreeNode<E> current = root;                                                       // Start from the root
        while (current != null) {                                                               // Looping until the tree ends
            if (current.compareTo(item) > 0)                                                    // If value less than current node
                current = current.getLeft();                                                    // Go to the left
            else if (current.compareTo(item) < 0)                                               // If value greater than current node
                current = current.getRight();                                                   // Go to the right
            else                                                                                // If the value is equal to the current node
                break;                                                                          // Exit the loop
        }
        return current;                                                                         // Return found node, or null
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------ Traversing a tree -----------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Convert tree to list
     * @param traversalType type of traversing
     * @return tree in form of a list
     */
    public DoublyLinkedList<E> toList(int traversalType) {
        DoublyLinkedList<E> linkedList = new DoublyLinkedList<>();
        if (traversalType == TRAVERSAL_IN_ORDER)
            inOrderAddition(linkedList, root);
        else if (traversalType == TRAVERSAL_PRE_ORDER)
            preOrderAddition(linkedList, root);
        else if (traversalType == TRAVERSAL_POST_ORDER)
            postOrderAddition(linkedList, root);
        else
            throw new IllegalArgumentException("Invalid traversal type!");
        return linkedList;
    }

    /**
     * Recursively traverse a list in in-order manner
     * @param list resulting list
     * @param currentNode current node
     */
    private void inOrderAddition(DoublyLinkedList<E> list, BinaryTreeNode<E> currentNode) {
        if (currentNode != null) {
            inOrderAddition(list, currentNode.getLeft());
            list.add(currentNode.getData());
            inOrderAddition(list, currentNode.getRight());
        }
    }

    /**
     * Recursively traverse a list in pre-order manner
     * @param list resulting list
     * @param currentNode current node
     */
    private void preOrderAddition(DoublyLinkedList<E> list, BinaryTreeNode<E> currentNode) {
        if (currentNode != null) {
            list.add(currentNode.getData());
            preOrderAddition(list, currentNode.getLeft());
            preOrderAddition(list, currentNode.getRight());
        }
    }

    /**
     * Recursively traverse a list in post-order manner
     * @param list resulting list
     * @param currentNode current node
     */
    private void postOrderAddition(DoublyLinkedList<E> list, BinaryTreeNode<E> currentNode) {
        if (currentNode != null) {
            postOrderAddition(list, currentNode.getLeft());
            postOrderAddition(list, currentNode.getRight());
            list.add(currentNode.getData());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------- Utility -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return toList(TRAVERSAL_IN_ORDER).toString();
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        root = null; size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return toList(TRAVERSAL_IN_ORDER).iterator();
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
}

/**
 * References
 *
 * Book: Introduction to Algorithms (3rd edition)
 *       Massachusetts Institute of Technology
 *       by Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein
 *
 * Book: Data Structures & Problem Solving Using Java (4th edition)
 *       Pearson Education
 *       by Mark Allen Weiss
 */