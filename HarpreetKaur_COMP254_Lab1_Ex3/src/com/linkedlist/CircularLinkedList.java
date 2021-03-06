package com.linkedlist;

public class CircularLinkedList<E> implements Cloneable {
  //---------------- nested Node class ----------------
  /**
   * Singly linked node, which stores a reference to its element and
   * to the subsequent node in the list.
   */
  private static class Node<E> {

    /** The element stored at this node */
    private E element;     // an element stored at this node

    /** A reference to the subsequent node in the list */
    private Node<E> next;  // a reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the CircularlyLinkedList
  /** The designated cursor of the list */
  private Node<E> tail = null;                  // we store tail (but not head)

  /** Number of nodes in the list */
  private int size = 0;                         // number of nodes in the list

  /** Constructs an initially empty list. */
  public CircularLinkedList() { }             // constructs an initially empty list

  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() {             // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return tail.getNext().getElement();         // the head is *after* the tail
  }

  /**
   * Returns (but does not remove) the last element of the list
   * @return element at the back of the list (or null if empty)
   */
  public E last() {              // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }

  // update methods
  /**
   * Rotate the first element to the back of the list.
   */
  public void rotate() {         // rotate the first element to the back of the list
    if (tail != null)                // if empty, do nothing
      tail = tail.getNext();         // the old head becomes the new tail
  }

  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e) {                // adds element e to the front of the list
    if (size == 0) {
      tail = new Node<>(e, null);
      tail.setNext(tail);                     // link to itself circularly
    } else {
      Node<E> newest = new Node<>(e, tail.getNext());
      tail.setNext(newest);
    }
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) { // adds element e to the end of the list
    addFirst(e);             // insert new element at front of list
    tail = tail.getNext();   // now new element becomes the tail
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {                   // removes and returns the first element
    if (isEmpty()) return null;              // nothing to remove
    Node<E> head = tail.getNext();
    if (head == tail) tail = null;           // must be the only node left
    else tail.setNext(head.getNext());       // removes "head" from the list
    size--;
    return head.getElement();
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    if (tail == null) return "()";
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = tail;
    do {
      walk = walk.getNext();
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
    } while (walk != tail);
    sb.append(")");
    return sb.toString();
  }
  
  
  //Method to create a clone of circular linked list
  @SuppressWarnings("unchecked")
  public CircularLinkedList<E> clone() throws CloneNotSupportedException{	  
	
	CircularLinkedList<E> other = (CircularLinkedList<E>) super.clone(); //Safe cast
	  if (size > 0) {
	      other.tail = new Node<>(tail.getElement(), null);
	      Node<E> walk = tail.getNext();      // walk through remainder of original list
	      Node<E> otherTail = other.tail;     // remember most recently created node
	      while (walk != tail) {            //walk until tail element is reached, since it is a circular list
	        Node<E> newest = new Node<>(walk.getElement(), null);
	        otherTail.setNext(newest);     // link previous node to this one
	        otherTail = newest;
	        walk = walk.getNext();
	      }
	      
	      if(walk == tail) { //If tail node has reached, link last added node to tail node
	    	  otherTail.setNext(other.tail);
	    	  otherTail = other.tail; //Not necessarily needed(At this point, tail, otherTail and walk point to same node)
	      }
	    }
	  return other;
  }
  
//main method
  public static void main(String[] args)
  {
	  
	  //(LAX, MSP, ATL, BOS)
	  CircularLinkedList<String> circularList = new CircularLinkedList<String>();
	  circularList.addFirst("LAX");
	  circularList.addLast("MSP");
	  circularList.addLast("ATL");
	  circularList.addLast("BOS");
	  //
	  System.out.println("Original List: " + circularList);
	  circularList.removeFirst();
	  System.out.println("Originl List after removing first node: " + circularList);
	  circularList.rotate();
	  System.out.println("List after rotation: " +circularList);
	  
	  try {
		  CircularLinkedList<String> cloneList = circularList.clone();
		  System.out.println("Cloned List:" + cloneList);
	  }catch(CloneNotSupportedException ce) {
		  System.out.println("Exception thrown:" + ce);
	  }

  }
}
