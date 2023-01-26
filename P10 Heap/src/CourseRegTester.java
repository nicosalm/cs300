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

import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Course, CourseIterator,
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {

  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() {
    try {
      // 1. test constructor
      Course c1 = new Course("CS", 300, 3, 150);

      // 2. test methods: getNumCredits(), equals(), toString(), setProfessor, setSeatsAvailable()
      // getNumCredits()
      if (c1.getNumCredits() != 3) {
        System.out.println("getNumCredits() failed");
        return false;
      }

      // equals()
      Course c2 = new Course("CS", 300, 3, 150);
      if (!c1.equals(c2)) {
        System.out.println("equals() failed");
        return false;
      }

      if (!c1.equals(c1)) {
        System.out.println("equals() failed");
        return false;
      }

      // toString()
      if (!c1.toString().equals("CS 300 (150 seats)")) {
        System.out.println("toString() failed");
        return false;
      }

      // setProfessor()
      c1.setProfessor("Hobbes LeGault", 4.5);
      if (!c1.toString().equals("CS 300 (150 seats) with Hobbes LeGault (4.5)")) {
        System.out.println("setProfessor() failed");
        return false;
      }

      // setSeatsAvailable()
      c1.setSeatsAvailable(100);
      if (!c1.toString().equals("CS 300 (100 seats) with Hobbes LeGault (4.5)")) {
        System.out.println("setSeatsAvailable() failed");
        return false;
      }

      // compareTo() will get its own test method

      // 3. test constructor with invalid arguments
      try {
        Course c3 = new Course("", 300, 3, 150);
        System.out.println("constructor failed");
        return false;
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      try {
        Course c3 = new Course("CS", 0, 3, 150);
        System.out.println("constructor failed");
        return false;
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      try {
        Course c3 = new Course("CS", 300, 0, 150);
        System.out.println("constructor failed");
        return false;
      } catch (IllegalArgumentException e) {
        // do nothing
      }
      try {
        Course c3 = new Course("CS", 300, 3, -1);
        System.out.println("constructor failed");
        return false;
      } catch (IllegalArgumentException e) {
        // do nothing
      }

      // 4. Ensure we return false Course whose setProfessor() method erroneously checks for a valid
      // rating when profName is null.
      try {
        Course c3 = new Course("CS", 300, 3, 150);
        c3.setProfessor(null, -1);
      } catch (IllegalArgumentException e) {
        System.out.println("setProfessor() failed");
        return false;
      }

      return true; // all tests passed!
    } catch (Exception e) {
      System.out.println("testCourse() failed");
      return false;
    }
  }

  /**
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels of
   * the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() {

    try {
      // 1. test compareTo() for all four levels of comparison
      // - major department, same seat availability, known professor, professor rating

      // two courses with the same major department, same seat availability, known professor,
      // professor
      // rating
      {
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("CS", 300, 3, 150);
        if (c1.compareTo(c2) != 0) {
          System.out.println("compareTo() failed");
          return false;
        }
      }
      // different professor rating
      {
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("CS", 300, 3, 150);
        c1.setProfessor("Hobbes LeGault", 4.5);
        c2.setProfessor("Hobbes LeGault", 4.0);
        if (c1.compareTo(c2) != 1) {
          System.out.println("compareTo() failed");
          return false;
        }
      }
      // known professor
      {
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("CS", 300, 3, 150);
        c1.setProfessor("Hobbes LeGault", 4.5);
        if (c1.compareTo(c2) != 1) {
          System.out.println("compareTo() failed");
          return false;
        }
      }
      // one has seats available, the other does not
      {
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("CS", 300, 3, 150);
        c1.setSeatsAvailable(0);
        if (c1.compareTo(c2) != -1) {
          System.out.println("compareTo() failed");
          return false;
        }
      }
      // both have seats available but different seat availability
      {
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("CS", 300, 3, 150);
        c1.setSeatsAvailable(100);
        c2.setSeatsAvailable(50);
        if (c1.compareTo(c2) != 0) {
          System.out.println("compareTo() failed");
          return false;
        }
      }
      // different major department
      {
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("MATH", 300, 3, 150);
        if (c1.compareTo(c2) != 1) {
          System.out.println("compareTo() failed");
          return false;
        }
      }

      return true; // all tests passed!
    } catch (Exception e) {
      System.out.println("compareTo() failed");
      return false;
    }
  }

  /**
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() {
    try {
      // isEmpty test cases (normal and error cases)
      {
        CourseQueue queue = new CourseQueue(3);
        // 1. test isEmpty() for normal cases
        // - empty queue
        if (!queue.isEmpty()) {
          System.out.println("isEmpty() failed");
          return false;
        }
        // - non-empty queue
        queue.enqueue(new Course("CS", 300, 3, 150));
        if (queue.isEmpty()) {
          System.out.println("isEmpty() failed");
          return false;
        }
        // - full queue
        queue.enqueue(new Course("CS", 300, 3, 150));
        queue.enqueue(new Course("CS", 300, 3, 150));
        if (queue.isEmpty()) {
          System.out.println("isEmpty() failed");
          return false;
        }
        // - re-emptied queue
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        if (!queue.isEmpty()) {
          System.out.println("isEmpty() failed");
          return false;
        }
        // 2. test isEmpty() for error cases
        // - null queue
        try {
          queue = null;
          queue.isEmpty();
          System.out.println("isEmpty() failed");
          return false;
        } catch (NullPointerException e) {
          // do nothing
        }
      }

      // size test cases (normal and error cases)
      {
        CourseQueue queue = new CourseQueue(3);
        // 1. test size() for normal cases
        // - empty queue
        if (queue.size() != 0) {
          System.out.println("size() failed");
          return false;
        }
        // - non-empty queue
        queue.enqueue(new Course("CS", 300, 3, 150));
        if (queue.size() != 1) {
          System.out.println("size() failed");
          return false;
        }
        // - full queue
        queue.enqueue(new Course("CS", 300, 3, 150));
        queue.enqueue(new Course("CS", 300, 3, 150));
        if (queue.size() != 3) {
          System.out.println("size() failed");
          return false;
        }
        // - re-emptied queue
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        if (queue.size() != 0) {
          System.out.println("size() failed");
          return false;
        }
        // 2. test size() for error cases
        // - null queue
        try {
          queue = null;
          queue.size();
          System.out.println("size() failed");
          return false;
        } catch (NullPointerException e) {
          // do nothing
        }
      }

      // peek test cases (normal and error cases)
      {
        CourseQueue queue = new CourseQueue(3);
        // 1. test peek() for normal cases
        // - empty queue
        try {
          queue.peek();
          System.out.println("peek() failed");
          return false;
        } catch (NoSuchElementException e) {
          // do nothing
        }
        // - non-empty queue
        queue.enqueue(new Course("CS", 300, 3, 150));
        if (queue.peek() == null) {
          System.out.println("peek() failed");
          return false;
        }
        // - full queue
        queue.enqueue(new Course("CS", 300, 3, 150));
        queue.enqueue(new Course("CS", 300, 3, 150));
        if (queue.peek() == null) {
          System.out.println("peek() failed");
          return false;
        }
        // - re-emptied queue
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        try {
          queue.peek();
          System.out.println("peek() failed");
          return false;
        } catch (NoSuchElementException e) {
          // do nothing
        }
        // 2. test peek() for error cases
        // - null queue
        try {
          queue = null;
          queue.peek();
          System.out.println("peek() failed");
          return false;
        } catch (NullPointerException e) {
          // do nothing
        }
        // - return false on a peek method which checks the last element in the array instead of the
        // first
        queue = new CourseQueue(3);
        queue.enqueue(new Course("CS", 300, 3, 150));
        queue.enqueue(new Course("CS", 400, 3, 150));
        if (queue.peek().equals(new Course("CS", 400, 3, 150))) {
          System.out.println("peek() failed");
          return false;
        }
      }

      return true;
    } catch (Exception e) {
      System.out.println("Exception thrown: " + e);
      return false;
    }
  }

  /**
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() {
    try {
      // Erronious swap
      {
        CourseQueue q = new CourseQueue(4);
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("CS", 200, 3, 0);
        c2.setProfessor("Hobbes", 3.5);
        Course c3 = new Course("CS", 200, 3, 0);
        Course c4 = new Course("Math", 221, 5, 150);
        q.enqueue(c1);
        q.enqueue(c2);
        q.enqueue(c3);
        q.enqueue(c4);
        q.dequeue();

        if (q.peek() == c3) {
          System.out.println("enqueue() failed");
          return false;
        }
        
        if (!q.peek().equals(c2)) {
          System.out.println("enqueue() failed");
          return false;
        }
      }
      {
        CourseQueue queue = new CourseQueue(3);
        // ~ The ENqueue method ~
        // 1. test enqueue() for normal cases
        // - empty queue
        if (queue.size() != 0) {
          System.out.println("enqueue() failed");
          return false;
        }
        try {
          queue.peek();
          return false;
        } catch (NoSuchElementException e) {
          // do nothing
        }
        // - non-empty queue, valid enqueue
        try {
          queue.enqueue(new Course("MATH", 100, 3, 150));
          if (queue.peek() == null) {
            return false;
          }
          if (!queue.peek().equals(new Course("MATH", 100, 3, 150))) {
            return false;
          }
          if (queue.size() != 1) {
            return false;
          }
        } catch (Exception e) {
          return false;
        }
        // - non-empty queue, invalid enqueue
        try {
          queue.enqueue(null);
          return false;
        } catch (NullPointerException e) {
          if (queue.peek() == null) {
            return false;
          }
          if (!queue.peek().equals(new Course("MATH", 100, 3, 150))) {
            return false;
          }
        }
        queue.enqueue(new Course("CS", 200, 3, 0));
        if (!queue.peek().equals(new Course("CS", 200, 3, 0))) {
          return false;
        }
        if (queue.size() != 2) {
          return false;
        }
        queue.enqueue(new Course("CS", 300, 3, 150));
        if (!queue.peek().equals(new Course("CS", 300, 3, 150))) {
          return false;
        }
        if (queue.size() != 3) {
          return false;
        }
        try {
          queue.enqueue(new Course("CS", 699, 3, 10));
        } catch (IllegalStateException e) {
          // do nothing
        }
        // ~ The Dequeue method ~
        queue.dequeue();
        if (!queue.peek().equals(new Course("CS", 200, 3, 0))) {
          return false;
        }
        if (queue.size() != 2) {
          return false;
        }
        queue.dequeue();
        if (!queue.peek().equals(new Course("MATH", 100, 3, 150))) {
          return false;
        }
        if (queue.size() != 1) {
          return false;
        }
        queue.dequeue();
        if (queue.size() != 0) {
          return false;
        }
        try {
          queue.dequeue();
          return false;
        } catch (NoSuchElementException e) {
          // do nothing
        }
        // 2. test enqueue() for error cases
        // - null queue
        try {
          queue = null;
          queue.enqueue(new Course("CS", 300, 3, 150));
          System.out.println("enqueue() failed");
          return false;
        } catch (NullPointerException e) {
          // do nothing
        }
        // - return false on an enqueue method which checks the last element in the array instead of
        // the first
        queue = new CourseQueue(3);
        queue.enqueue(new Course("CS", 300, 3, 150));
        queue.enqueue(new Course("CS", 400, 3, 150));
        if (queue.peek().equals(new Course("CS", 400, 3, 150))) {
          System.out.println("enqueue() failed");
          return false;
        }
        // 3. test dequeue() for error cases
        // - null queue
        try {
          queue = null;
          queue.dequeue();
          System.out.println("dequeue() failed");
          return false;
        } catch (NullPointerException e) {
          // do nothing
        }
        // - return false on a dequeue method which checks the last element in the array instead of
        // the first
        queue = new CourseQueue(3);
        queue.enqueue(new Course("CS", 300, 3, 150));
        queue.enqueue(new Course("CS", 400, 3, 0));
        queue.dequeue();
        if (queue.peek().equals(new Course("CS", 300, 3, 150))) {
          System.out.println("dequeue() failed");
          return false;
        }
      }

    } catch (Exception e) {
      System.out.println("Exception thrown: " + e);
      return false;
    }
    return true;
  }

  /**
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() {

    CourseQueue queue = new CourseQueue(3);
    Course c1 = new Course("CS", 300, 3, 150);
    Course c2 = new Course("MATH", 400, 5, 0);
    Course c3 = new Course("CS", 200, 3, 0);

    queue.enqueue(c1);
    queue.enqueue(c2);
    queue.enqueue(c3);

    CourseIterator iter = new CourseIterator(queue);

    if (!iter.hasNext()) {
      System.out.println("hasNext() failed 1");
      return false;
    }

    if (!iter.next().equals(c1)) {
      System.out.println("next() failed 1");
      return false;
    }

    if (!iter.hasNext()) {
      System.out.println("hasNext() failed 2");
      return false;
    }

    if (!iter.next().equals(c3)) {
      System.out.println("next() failed 2");
      return false;
    }

    if (!iter.hasNext()) {
      System.out.println("hasNext() failed 3");
      return false;
    }

    if (!iter.next().equals(c2)) {
      System.out.println("next() failed 3");
      return false;
    }

    if (iter.hasNext()) {
      System.out.println("hasNext() failed 4");
      return false;
    }

    try {
      iter.next();
      System.out.println("next() failed 4");
      return false;
    } catch (IllegalStateException e) {
      return false;
    } catch (NoSuchElementException e) {
      // do nothing
    }

    return true;
  }

  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() {
    try {
      // ~ Constructor
      {
        // - normal case
        try {
          CourseReg reg = new CourseReg(3, 1);
        } catch (Exception e) {
          System.out.println("Constructor failed 0");
          return false;
        }
        // - error case
        try {
          CourseReg reg = new CourseReg(0, 1);
          System.out.println("Constructor failed 1");
          return false;
        } catch (IllegalArgumentException e) {
          // do nothing
        }
        // - error case
        try {
          CourseReg reg = new CourseReg(-1, 1);
          System.out.println("Constructor failed 2");
          return false;
        } catch (IllegalArgumentException e) {
          // do nothing
        }
        // - error case
        try {
          CourseReg reg = new CourseReg(3, 0);
          System.out.println("Constructor failed 3");
          return false;
        } catch (IllegalArgumentException e) {
          // do nothing
        }
        // - error case
        try {
          CourseReg reg = new CourseReg(3, -1);
          System.out.println("Constructor failed 4");
          return false;
        } catch (IllegalArgumentException e) {
          // do nothing
        }
      }

      // ~ setCreditLoad()
      {
        // - normal case
        try {
          CourseReg reg = new CourseReg(3, 1);
          reg.setCreditLoad(3);
        } catch (Exception e) {
          System.out.println("setCreditLoad() failed 0");
          return false;
        }
        // - error case
        try {
          CourseReg reg = new CourseReg(3, 1);
          reg.setCreditLoad(0);
          System.out.println("setCreditLoad() failed 1");
          return false;
        } catch (IllegalArgumentException e) {
          // do nothing
        }
        // - error case
        try {
          CourseReg reg = new CourseReg(3, 1);
          reg.setCreditLoad(-1);
          System.out.println("setCreditLoad() failed 2");
          return false;
        } catch (IllegalArgumentException e) {
          // do nothing
        }
      }
      
      // ~ add()
      {
        CourseReg queue = new CourseReg(3, 3);
        Course c1 = new Course("CS", 300, 3, 150);
        Course c2 = new Course("MATH", 400, 5, 0);
        Course c3 = new Course("CS", 200, 3, 0);
        Course c4 = null;

        // - adding a null
        try {
          if (!queue.add(c1)) {
            System.out.println("add() failed 1");
            return false;
          }
          if (!queue.add(c2)) {
            System.out.println("add() failed 2");
            return false;
          }
          if (queue.add(c4)) {
            System.out.println("add() failed 3");
            return false;
          }
          if (!queue.add(c3)) {
            System.out.println("add() failed 4");
            return false;
          }
          if (queue.add(c3)) {
            System.out.println("add() failed 5");
            return false;
          }
        } catch (Exception e) {
          System.out.println("add() failed 0");
          return false;
        }

      }
      // ~ getRecommendedCourses()
      {
        {
          CourseReg queue = new CourseReg(4, 4);
          Course c1 = new Course("CS", 300, 3, 150);
          c1.setProfessor("Hobbes", 5.0);
          Course c2 = new Course("ART", 200, 2, 10);
          c2.setProfessor("James", 4.0);
          Course c3 = new Course("ART", 200, 2, 10);
          c3.setProfessor("James", 4.0);
          Course c4 = new Course("MATH", 200, 1, 0);

          queue.add(c1);
          queue.add(c2);
          queue.add(c3);
          queue.add(c4);

          String rec = queue.getRecommendedCourses();

          if (!rec.equals("CS 300 (150 seats) with Hobbes (5.0)\n")) {
            System.out.println("getRecommendedCourses() failed 0");
            return false;
          }

          if (rec.equals("CS 300 (150 seats) with Hobbes (5.0)\nMATH 200 (closed)\n")) {
            System.out.println("getRecommendedCourses() failed 1");
            return false;
          }
        }
      }

    } catch (Exception e) {
      System.out.println("Exception thrown: " + e);
      return false;
    }

    return true;
  }

  /**
   * This method calls all test methods defined by us; you may add additional methods to this if you
   * like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() {
    boolean testVal = true;

    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    return testVal;
  }

  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
