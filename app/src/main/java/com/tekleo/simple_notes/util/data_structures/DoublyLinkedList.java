package com.tekleo.simple_notes.util.data_structures;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Leo on 20-Jan-16.
 */

/**
 * Data structure consisting of a group of nodes which together represent a sequence
 * Linked from starting node to ending node & linked from ending node to starting node
 * @param <E> type of objects stored
 * @see Node
 */
public class DoublyLinkedList<E> implements Iterable<E>, Serializable
{
    private Node<E> head = null;                                    // Starting node
    private Node<E> tail = null;                                    // Ending node
    private int size = 0;                                           // Number of nodes

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------- Constructors --------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public DoublyLinkedList() {
    }

    public DoublyLinkedList(E headItem) {
        this(); this.add(headItem);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------- Adding --------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Adds the provided value to the beginning of the linked list
     * Performance: O(1)
     * @param item value to be added
     */
    public void addFirst(E item)
    {
        Node<E> node = new Node<>(item);                            // Create new node
        if (isEmpty()) {                                            // If the list is empty
            head = node; tail = node;                               // Head and tail must be the new node
        }
        else {                                                      // If the list is not empty
            head.setPrevious(node); node.setNext(head);             // Relink
            head = node;                                            // Previous head must be reset
        }
        size++;                                                     // Size must be increased in any case
    }

    /**
     * Adds the provided value to the end of the linked list
     * Performance: O(1)
     * @param item value to be added
     */
    public void addLast(E item)
    {
        Node<E> node = new Node<>(item);                            // Create new node
        if (isEmpty()) {                                            // If the list is empty
            head = node; tail = node;                               // Head and tail must be the new node
        }
        else {                                                      // If the list is not empty
            tail.setNext(node); node.setPrevious(tail);             // Relink
            tail = node;                                            // Previous tail must be reset
        }
        size++;                                                     // Size must be increased in any case
    }

    /**
     * Adds the provided value to the end of the linked list
     * Performance: O(1)
     * @param item value to be added
     */
    public void add (E item)
    {
        this.addLast(item);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------- Removing -------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Removes the first node in the list whose value equals the provided value
     * The method returns true if a value was removed. Otherwise it returns false
     * Performance: O(n)
     * @param item value to be removed
     * @return true if a value was removed, false otherwise
     */
    public boolean remove (E item)
    {
        // If the list is empty - don't do anything
        if (isEmpty())
            return false;                                           // Exit, the item is not in the list

        // The node might be the only node in the list
        else if (size == 1) {
            if (head.getData().equals(item)) {                      // If this single node is the item to be removed
                head = null; tail = null;                           // Reset head and tail
                size--; return true;                                // Decrease size and exit
            }
            else                                                    // If this single node is not the item to be removed
                return false;                                       // Exit, the item is not in the list
        }

        // The node might be the first node in the list
        else if (head.getData().equals(item)) {
            head = head.getNext(); head.setPrevious(null);          // Reset head
            size--; return true;                                    // Decrease size and exit
        }

        // The node might be the last node in the list
        else if (tail.getData().equals(item)) {
            tail = tail.getPrevious(); tail.setNext(null);          // Reset tail
            size--; return true;                                    // Decrease size and exit
        }

        // The node might be in the middle of the list
        else {
            Node<E> previousNode = head;                            // Keep track of previous node

            // Until the next node is the node to be removed
            while (!previousNode.getNext().getData().equals(item)) {
                previousNode = previousNode.getNext();              // Try next node
                if (previousNode.getNext() == null)                 // If current node is the last node
                    return false;                                   // Exit, the item is not in the list
            }

            Node<E> nextNode = previousNode.getNext().getNext();    // Find next node
            previousNode.setNext(nextNode);                         // Reset pointer in previous node
            nextNode.setPrevious(nextNode);                         // Reset pointer in next node
            size--; return true;                                    // Decrease size and exit
        }
    }

    /**
     * Removes and returns the first element of the list
     * Performance: O(1)
     * @return the first item of the list
     */
    public E removeFirst()
    {
        if (isEmpty())                                              // If the list is empty - don't do anything
            return null;                                            // Exit, the item is not in the list

        if (getSize() == 1) {                                       // If only one item in the list
            E n = head.getData();                                   // Save data from head
            head = null; tail = null;                               // Reset head and tail
            size--; return n;                                       // Decrease size and exit
        }

        E n = head.getData();                                       // Save data from head
        head = head.getNext(); head.setPrevious(null);              // Reset head
        size--; return n;                                           // Decrease size and exit
    }

    /**
     * Removes and returns the last element of the list
     * Performance: O(1)
     * @return the last item of the list
     */
    public E removeLast()
    {
        if (isEmpty())                                              // If the list is empty - don't do anything
            return null;                                            // Exit, the item is not in the list

        if (getSize() == 1) {                                       // If only one item in the list
            E n = head.getData();                                   // Save data from head
            head = null; tail = null;                               // Reset head and tail
            size--; return n;                                       // Decrease size and exit
        }

        E n = tail.getData();                                       // Save data from tail
        tail = tail.getPrevious(); tail.setNext(null);              // Reset tail
        size--; return n;                                           // Decrease size and exit
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------- Other --------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Returns a boolean that indicates whether the provided value exists within the linked list
     * Performance: O(n)
     * @param item value
     * @return true if a value exists in the list, false otherwise
     */
    public boolean contains(E item)
    {
        Node<E> currentNode = head;                                 // Keep track of current node
        while (currentNode != null) {                               // For each node of the list
            if (currentNode.getData().equals(item))                 // If this is the desired node
                return true;                                        // Exit
            currentNode = currentNode.getNext();                    // Try next node
        }
        return false;                                               // Item was not found, exit
    }

//    /**
//     * Reverse all the nodes in the list
//     * Performance: O(2n)
//     */
//    public void reverse()
//    {
//        LinkedList<E> reversedList = new LinkedList<>();            // Temporary list
//        Node<E> currentNode = tail;                                 // Keep track of current node
//
//        while (currentNode != null) {                               // For each node of the list
//            reversedList.add(currentNode.getData());                // Add value to reversed list
//            currentNode = currentNode.getPrevious();                // Try next node
//        }
//
//        this.clear();                                               // Clear this list
//        for (E item : reversedList)                                 // For each reversed item
//            this.add(item);                                         // Add it to this list
//    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------- Getters -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private Node<E> getHead() {
        return head;
    }

    private Node<E> getTail() {
        return tail;
    }

    public E getFirst() {
        if (!isEmpty())
            return head.getData();
        else
            return null;
    }

    public E getLast() {
        if (!isEmpty())
            return tail.getData();
        else
            return null;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------- Utility -----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        String message = ""; Node<E> temp = head;

        while (temp != null) {
            if(temp.getNext() == null)
                message += "[" + temp.getData().toString() + "]";
            else
                message += "[" + temp.getData().toString() + "]; ";
            temp = temp.getNext();
        }

        return message;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null; tail = null; size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new NodeIterator<>(this);
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------- Inner classes --------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Iterator class for linked list
     * @param <E> type of objects stored
     */
    private class NodeIterator<E> implements Iterator<E>
    {
        private Node<E> next;

        public NodeIterator(DoublyLinkedList list) {
            next = list.getHead();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            E n = next.getData(); next = next.getNext(); return n;
        }

        @Override
        public void remove() {

        }
    }

    /**
     * A container class that provides the ability to both store data and connect to other nodes
     * Double links
     * @param <E> type of objects stored
     */
    private class Node<E>
    {
        private E data;                                     // Data stored in this node
        private Node<E> next;                               // Pointer to next node
        private Node<E> previous;                           // Pointer to previous node

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> next, Node<E> previous) {
            this.data = data; this.next = next; this.previous = previous;
        }

        public Node() {
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setData(E data) {
            this.data = data;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }
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