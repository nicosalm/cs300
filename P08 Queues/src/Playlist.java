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
 * A FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 *
 * @author Nico
 */
public class Playlist implements QueueADT<Song> {
  /** The current first song in the queue; the next one up to play (front of the queue) */
  private SongNode first;

  /** The current last song in the queue; the most-recently added one (back of the queue) */
  private SongNode last;

  /** The number of songs currently in the queue */
  private int numSongs;

  /** Constructs a new, empty playlist queue */
  public Playlist() {
    first = null; // my constructor that isn't really needed but I like it.
    last = null;
    numSongs = 0;
  }

  /**
   * Adds a new song to the end of the queue
   *
   * @param element the song to add to the Playlist
   */
  @Override
  public void enqueue(Song element) {
    // special case - the queue is empty
    if (first == null) { 
      first = new SongNode(element); // create a new SongNode with the given song
      last = first; // the first and last SongNode are the same
    } else {
      // general case - the queue is not empty
      SongNode newNode = new SongNode(element); // create a new SongNode with the given song
      last.setNext(newNode); // set the next SongNode of the current last SongNode to the new one
      last = newNode; // set the new SongNode as the last SongNode
    }
    numSongs++; // increment the number of songs in the queue
  }

  /**
   * Removes the song from the beginning of the queue
   *
   * @return the song that was removed from the queue, or null if the queue is empty
   */
  @Override
  public Song dequeue() {
    // special case - the queue is empty
    if (isEmpty()) {
      return null;
    }
    // general case - the queue is not empty
    SongNode oldFirst = first; // save the old first node
    first = first.getNext(); // move the first pointer to the next node
    numSongs--; // decrement the number of songs
    return oldFirst.getSong(); // return the song that was removed
  }

  /**
   * Returns the song at the front of the queue without removing it
   *
   * @return the song that is at the front of the queue, or null if the queue is empty
   */
  @Override
  public Song peek() {
    if (isEmpty()) {
      return null;
    }

    return first.getSong();
    
  }

  /**
   * Returns true if and only if there are no songs in this queue
   *
   * @return true if this queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return numSongs == 0 || first == null; // redundant, but just in case
  }

  /**
   * Returns the number of songs in this queue
   *
   * @return the number of songs in this queue
   */
  @Override
  public int size() {
    return numSongs;
  }

  /**
   * Creates and returns a formatted string representation of this playlist, with the string version
   * of each song in the list on a separate line. For example: "He's A Pirate" (1:21) by Klaus
   * Badelt "Africa" (4:16) by Toto "Waterloo" (2:45) by ABBA "All I Want For Christmas Is You"
   * (4:10) by Mariah Carey "Sandstorm" (5:41) by Darude "Never Gonna Give You Up" (3:40) by Rick
   * Astley
   *
   * @return the string representation of this playlist
   */
  @Override
  public String toString() {
    String result = ""; // the string to return
    SongNode current = first;
    while (current != null) { // loop through the queue
      result = result.concat(current.getSong().toString() + "\n"); // build the string
      current = current.getNext();
    }
    return result; // return the string
  }
}
