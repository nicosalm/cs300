//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Course Registration! Our final project!
// Course: COMP SCI 300 Fall 2022
//
// Author: Nico Salm
// Email: nbsalm@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the max-heap
 * invariant, so that the Course at the root should have the highest score, and all children always
 * have a score lower than or equal to their parent's.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>, PriorityQueueADT<Course> { // TODO: add
                                                                                 // PriorityQueueADT<Course>
                                                                                 // once Course is
                                                                                 // Comparable

  // data fields
  private Course[] queue; // array max-heap of courses representing this priority queue
  private int size; // number of courses currently in this priority queue

  /**
   * Creates a new, empty CourseQueue with the given capacity
   * 
   * @param capacity the capacity of this CourseQueue
   * @throws IllegalArgumentException if the capacity is not a positive integer
   */
  public CourseQueue(int capacity) {
    Course[] queue = new Course[capacity];
    this.queue = queue;
    this.size = 0;
  }

  /**
   * Returns a deep copy of this CourseQueue containing all of its elements in the same order. This
   * method does not return the deepest copy, meaning that you do not need to duplicate courses.
   * Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
   */
  public CourseQueue deepCopy() {
    Course[] newQ = new Course[this.queue.length];
    System.arraycopy(this.queue, 0, newQ, 0, this.queue.length);
    CourseQueue newQueue = new CourseQueue(this.queue.length);
    newQueue.queue = newQ;
    newQueue.size = this.size;
    return newQueue;
  }

  /**
   * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
   * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY of
   * this queue.
   * 
   * @see CourseIterator
   * @return an Iterator for this CourseQueue
   */
  @Override
  public Iterator<Course> iterator() {
    return new CourseIterator(this);
  }

  ///////////////////////////// TODO: PRIORITY QUEUE METHODS //////////////////////////////////
  // Add the @Override annotation to these methods once this class implements PriorityQueueADT!

  /**
   * Checks whether this CourseQueue is empty
   * 
   * @return {@code true} if this CourseQueue is empty
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns the size of this CourseQueue
   * 
   * @return the size of this CourseQueue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Adds the given Course to this CourseQueue and use the percolateUp() method to maintain max-heap
   * invariant of CourseQueue. Courses should be compared using the Course.compareTo() method.
   * 
   * 
   * @param toAdd Course to add to this CourseQueue
   * @throws NullPointerException  if the given Course is null
   * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
   */
  @Override
  public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
    if (toAdd == null) {
      throw new NullPointerException("Cannot add null Course to CourseQueue!");
    }
    if (size == queue.length) {
      throw new IllegalStateException("Cannot add Course to full CourseQueue!");
    }
    queue[size] = toAdd;
    size++;
    percolateUp(this.size - 1);
  }

  /**
   * Removes and returns the Course at the root of this CourseQueue, i.e. the Course with the
   * highest priority. Use the percolateDown() method to maintain max-heap invariant of CourseQueue.
   * Courses should be compared using the Course.compareTo() method.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException with a descriptive error message if this CourseQueue is empty
   */
  @Override
  public Course dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot dequeue from empty CourseQueue!");
    }
    Course toRemove = queue[0];
    if (size == 1) {
      queue[0] = null;
      size--;
      return toRemove;
    }
    queue[0] = queue[size - 1];
    queue[size - 1] = null;
    size--;
    percolateDown(0);
    return toRemove;
  }

  /**
   * Returns the Course at the root of this CourseQueue, i.e. the Course with the highest priority.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException if this CourseQueue is empty
   */
  @Override
  public Course peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot peek at empty CourseQueue!");
    }
    return queue[0];
  }

  ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////

  /**
   * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If
   * the element at the given index does not violate the max-heap invariant (it is higher priority
   * than its children), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > this.size()) {
      throw new IndexOutOfBoundsException("Bad index for percolateDown!");
    }
    int leftChild = index*2+1;
    int rightChild = index*2+2;
    
    if (leftChild >= this.size()) {
      return;
    }
    else if (rightChild >= this.size()) {
      int compare = queue[leftChild].compareTo(queue[index]);
      
      if (compare > 0) {
        Course temp = queue[index];
        queue[index] = queue[leftChild];
        queue[leftChild] = temp;
        
        return;
      }
      else {
        return;
      }
    }
    else {
      int compareLeft = queue[leftChild].compareTo(queue[index]);

      int compareRight = queue[rightChild].compareTo(queue[index]);
      
      if (compareLeft > 0 || compareRight > 0) {
        int largerIndex = leftChild; 
        if (queue[rightChild].compareTo(queue[leftChild]) >= 0) {
          largerIndex = rightChild;
        }
        Course temp = queue[index];
        queue[index] = queue[largerIndex];
        queue[largerIndex] = temp;
        
        percolateDown(largerIndex);
      }
      else {
        return;
      }
    }
  }

  /**
   * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element
   * at the given index does not violate the max-heap invariant (it is lower priority than its
   * parent), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds!");
    }

    // get the index of the parent
    int parent = (index - 1) / 2;

    // case 1: root node
    if (index == 0) {
      return;
    }
    // case 2: not root node
    else {
      // swap with parent if this value is larger than it
      if (queue[index].compareTo(queue[parent]) > 0) {
        // swap
        Course temp = queue[index];
        queue[index] = queue[parent];
        queue[parent] = temp;

        // continue percolating
        percolateUp(parent);
      }
      // stop percolating when newRoot is less than both children
      else {
        return;
      }
    }
  }

  ////////////////////////////// PROVIDED: TO STRING ////////////////////////////////////

  /**
   * Returns a String representing this CourseQueue, where each element (course) of the queue is
   * listed on a separate line, in order from the highest priority to the lowest priority.
   * 
   * @author yiwei
   * @see Course#toString()
   * @see CourseIterator
   * @return a String representing this CourseQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Course c : this) {
      val.append(c).append("\n");
    }

    return val.toString().trim();
  }

}
