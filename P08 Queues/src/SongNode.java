//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongQueue
// Course:   CS 300 Fall 2022
//
// Author:   @Nico
// Email:    nbsalm@wisc.edu
// Lecturer: @legault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A singly-linked node for our linked queue, which contains a Song object
 *
 * @author Nico
 */
public class SongNode {
  /** The Song object in this node */
  private Song song;

  /** The next SongNode in this queue */
  private SongNode next;

  /**
   * Constructs a single SongNode containing the given data, not linked to any other SongNodes
   *
   * @param data the Song for this node
   * @throws IllegalArgumentException if data is null
   */
  public SongNode(Song data) throws IllegalArgumentException {
    if (data == null) { // if the given data is null, throw an exception
      throw new IllegalArgumentException("SongNode constructor: data is null");
    }
    song = data; // set the song field to the given data
    next = null; // set the next field to null
  }

  /**
   * Constructs a single SongNode containing the given data, linked to the specified SongNode
   *
   * @param data the Song for this node
   * @param next the next node in the queue
   * @throws IllegalArgumentException if data is null
   */
  public SongNode(Song data, SongNode next) throws IllegalArgumentException {
    if (data == null) { // if the given data is null, throw an exception
      throw new IllegalArgumentException("SongNode constructor: data is null");
    }
    song = data; // set the song to the given data
    this.next = next; // set the next SongNode to the given next SongNode
  }

  /**
   * Accessor method for this node's data
   *
   * @return the Song in this node
   */
  public Song getSong() {
    return song;
  }

  /**
   * Accessor method for the next node in the queue
   *
   * @return the SongNode following this one, if any
   */
  public SongNode getNext() {
    return next;
  }

  /**
   * Changes the value of this SongNode's next data field to the given value
   *
   * @param next the SongNode to follow this one; may be null
   */
  public void setNext(SongNode next) {
    this.next = next;
  }
}
