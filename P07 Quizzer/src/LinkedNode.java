//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Quizzer
// Course: CS 300 Fall 2022
//
// Author: @Nico
// Email: nbsalm@wisc.edu
// Lecturer: @legault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: Referenced the Lec2_Node class from the lecture slides.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An instance of this class represents a single node within a singly linked list.
 *
 * @author Nico
 */
public class LinkedNode<T> {
  /** data carried by this linked node */
  private T data;

  /** reference to the next linked node */
  private LinkedNode<T> next;

  /**
   * Constructs a new node with the provided information.
   *
   * @param data to be stored within this node
   * @param next node, which comes after this node in a singly linked list
   * @throws NullPointerException with a descriptive error message if data is null
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    if (data == null)
      throw new NullPointerException("Data in this LinkedNode cannot be null.");
    this.data = data;
    this.next = next;
  }

  /**
   * Constructs a new node with a specific data and NO next node in the list.
   *
   * @param data to be stored within this node
   * @throws NullPointerException with a descriptive error message if data is null
   */
  public LinkedNode(T data) {
    this(data, null); // we don't like boilerplate code
  }

  /**
   * Accessor method for this node's next node reference.
   *
   * @return the next reference to the node that comes after this one in the list, or null when this
   *         is the last node in that list
   */
  public LinkedNode<T> getNext() {
    return next;
  }

  /**
   * Mutator method for this node's next node reference.
   *
   * @param next node, which comes after this node in a doubly linked list
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }

  /**
   * Accessor method for this node's data.
   *
   * @return the data stored within this node.
   */
  public T getData() {
    return data;
  }

  /**
   * Returns a string representation of this linked node formatted as follows: data.toString() if
   * this node does NOT have a next node in the list data.toString() + "->" if this node has a next
   * node in the list
   *
   * @return a String representation of this node in the list
   */
  @Override
  public String toString() {
    if (next == null) return data.toString(); // if there is no next node, just return the data
    return data.toString() + "->"; // otherwise, return the data and an arrow
  }
}
