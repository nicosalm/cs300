import java.util.Arrays;
///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Nico Salm
// Campus ID: 9084185819
// WiscEmail: nbsalm@wisc.edu
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// This file contains ONE utility class and ONE data type class. You will need to 
// complete the implementation of the utility class with respect to the provided 
// requirements in the TODO tags for full credit.
//
// You should not modify the data type class in any way.
// 
// You are not required to add error checking to any of these methods.
////////////////////////////////////////////////////////////////////////////////

/**
 *  This class contains a single public method: sortMovies(), along with 
 *  several helper methods and two tests.  This public method uses the heap sort
 *  algorithm to sort movie titles in ascending order.
 *  
 *  // TODO #1-9 below ask you to finish implementing the following methods
 *  (only make edits below each of these numbered TODOs, as described for each):
 *  peekMovie() - returns largest title from the root position of the max-heap
 *  percolateDown() - recursively enforces heap ordering down from specified index
 *  heapify() - iteratively moves values from a randomly-ordered array into heap order
 *  removeMovie() - removes movie value from root position in heap, and returns new size
 *  sortMovies() - computes and returns a sorted array of movie values
 *  
 *  Additionally, the following helper and test methods are provided:
 *  swap() - swaps the values at two different positions within an array
 *  bestOf() - returns the largest of its three string/null arguments
 *  testHeapify() - returns true when this test passes and false otherwise
 *  testSort() - returns true when this test passes and false otherwise
 *  main() - calls and prints out the value returned from test()
 */
public class MovieSorter {
  
  /**
   * Returns movie title from the root position in the max-heap (lexicographically latest title)
   * We suppose that the provided heap array is NOT empty.
   * 
   * @param heap a provided max-heap
   * @return movie title from the root position in the max-heap
   */
  private static Movie peekMovie(Movie[] heap) {
    return heap[0]; // the root of the heap is at index 0
    
  }
  
  /**
   * Recursive helper method which implements percolateDown operation to restore the heap order
   * property if it is violated at a given index position of a provided max-heap.
   * 
   * @param index position of the element to start the percolate-down operation from
   * @param heap  a provided max-heap array
   * @param size  of the heap
   */
  private static void percolateDown(int index, Movie[] heap, int size) {
    if (index >= size)
      return; // cannot percolate further below bottom of heap
    // compute indexes of left and right children positions within heap
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;

    // store values from index, left, and right child positions into separate variables
    Movie indexValue = heap[index];
    Movie rightChildValueOrNull = null;
    if (rightChildIndex < size)
      rightChildValueOrNull = heap[rightChildIndex];
    Movie leftChildValueOrNull = null;
    if (leftChildIndex < size)
      leftChildValueOrNull = heap[leftChildIndex];

    // compute highest priority value between index and its left and right children
    Movie best = bestOf(indexValue, rightChildValueOrNull, leftChildValueOrNull);

    // percolate down through left subtree if best is left child or if left child is null
    if (best.equals(leftChildValueOrNull)) {
      swap(heap, index, leftChildIndex);
      percolateDown(leftChildIndex, heap, size);

    // percolate down through right subtree if best is right child or if right child is null
    } else if (best.equals(rightChildValueOrNull)) {
      swap(heap, index, rightChildIndex);
      percolateDown(rightChildIndex, heap, size);
    }
  }
  
  /**
   * Reorganizes an array of Movie values into a valid min heap.
   * This heapify operation does NOT operate in-place.
   * 
   * @param values a provided oversize array of values, in no particular order
   * @param size number of values in the provided oversize array
   * @return a DEEP COPY of the values array, in min-heap order
   */
  private static Movie[] heapify(Movie[] values, int size) {
    Movie[] heap = Arrays.copyOf(values, size);

    int heapifyIndex = (size - 1) / 2;

    while (heapifyIndex >= 0) {
      percolateDown(heapifyIndex, heap, size);
      heapifyIndex--;
    }

    return heap;
  }
  
  /**
   * Removes movie from root position in heap, and then returns heap's new size.
   * Assume that the provided array is NOT empty and is a valid max-heap.
   * 
   * @param heap a provided max-heap
   * @param size of heap before removeMovie is called.
   * @return size is the new size of heap after removeMovie is performed
   */
  private static int removeMovie(Movie[] heap, int size) {
    
    // TODO #7: swap root with last element in heap, percolate down from root, and return new size
    swap(heap, 0, size-1);
    percolateDown(0, heap, size-1);
    return size-1;    
  }
  
  /**
   * Sorts an array of movies in lexicographic order using a max-heap. This sort operation
   * does not operate in-place. Assume that the provided array is NOT empty.
   * 
   * @param heap a max-heap
   * @return reference to the sorted array
   */
  public static Movie[] sortMovies(Movie[] titles, int size) {
    int heapSize = size;
    // Sorted is an over sized array that will be filled with Movies
    // from back to front. When it is full, it will be returned.
    Movie[] heap = heapify(titles, size);
    Movie[] sorted = new Movie[size];
    int sortedIndex = size-1;

    while (heapSize > 0) {
      sorted[sortedIndex] = peekMovie(heap);
      heapSize = removeMovie(heap, heapSize);
      sortedIndex--;
    }

    return sorted;
  }
  
  //////////////////////////// UTILITY METHODS, PROVIDED ///////////////////////
  
  /**
   * Swaps the elements at indexes i and j in the provided array
   * 
   * @param array in which elements are being moved
   * @param i     the first index being changed in that array
   * @param j     the second index being changed in that array
   */
  private static void swap(Movie[] array, int i, int j) {
    Movie temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
  
  /**
   * Returns the lexicographically latest string between the provided arguments. Null arguments are
   * ignored (considered lexicographically earlier than strings).
   * 
   * @param a, b, c are the strings being compared
   * @return the lexicographically latest of those strings
   * @throws IllegalArgumentException when all strings are null
   */
  private static Movie bestOf(Movie a, Movie b, Movie c) {
    if (a != null && (b == null || a.compareTo(b) >= 0) && (c == null || a.compareTo(c) >= 0))
      return a;
    else if (b != null && (a == null || b.compareTo(a) >= 0) && (c == null || b.compareTo(c) >= 0))
      return b;
    else if (c != null && (a == null || c.compareTo(a) >= 0) && (b == null || c.compareTo(b) >= 0))
      return c;
    else
      throw new IllegalArgumentException("None of these strings are the best");
  }
  
  /**
   * One short and simple test for the heapify method.
   * 
   * @return true when this test passes, otherwise false
   */
  private static boolean testHeapify() {
    try {
      Movie[] randomValues = new Movie[] {
          new Movie("Dark Knight"),
          new Movie("Angry Men"),
          new Movie("Boats"),
          new Movie("Django Unchained"),
          new Movie("Aliens"), 
          new Movie("Oceans 11")
      };
      Movie[] heapOfMovies = new Movie[] {
          new Movie("Oceans 11"), 
          new Movie("Django Unchained"), 
          new Movie("Dark Knight"),
          new Movie("Angry Men"),
          new Movie("Aliens"),
          new Movie("Boats")
      };
      
      System.out.println("Expected: "+Arrays.deepToString(heapOfMovies));
      System.out.println("Actual:   "+
             Arrays.deepToString(heapify(randomValues, randomValues.length)));

      if(!Arrays.deepEquals(heapify(randomValues, randomValues.length), heapOfMovies))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }
  
  /**
   * One short and simple test for the sortMovies method.
   * 
   * @return true when this test passes, otherwise false
   */
  private static boolean testSort() {
    try {
      Movie[] heapOfMovies = new Movie[] {
          new Movie("Oceans 11"), 
          new Movie("Dark Knight"),
          new Movie("Django Unchained"),
          new Movie("Angry Men"), 
          new Movie("Aliens"), 
          new Movie("Boats")
      };
      Movie[] sortedMovies = new Movie[] {
          new Movie("Aliens"), 
          new Movie("Angry Men"), 
          new Movie("Boats"),
          new Movie("Dark Knight"), 
          new Movie("Django Unchained"), 
          new Movie("Oceans 11")
      };
      
      System.out.println("Expected: "+Arrays.deepToString(sortedMovies));
      System.out.println("Actual:   "+
             Arrays.deepToString(sortMovies(heapOfMovies, heapOfMovies.length)));

      if (!Arrays.deepEquals(sortMovies(heapOfMovies, heapOfMovies.length), sortedMovies))
        return false;

    } catch (Exception e) {
      return false;
    }

    return true;
  }
  
  /**
   * Driver for the test methods above.
   * @param args is unused
   */
  public static void main(String[] args) {
    System.out.println("testHeapify(): "+testHeapify());
    System.out.println("testSort(): "+testSort());
  }

}

/**
 * Object being sorted in our heapsort methods above
 */
class Movie implements Comparable<Movie> {
  private String title;
  public Movie(String t) { this.title = t; }
  @Override
  public String toString() { return ""+this.title; }
  @Override
  public int compareTo(Movie o) { return this.title.compareTo( ((Movie)o).title ); }
  @Override
  public boolean equals(Object o) { 
    if (o instanceof Movie) return ((Movie)o).title.equals(this.title); 
    return false;
  }
}