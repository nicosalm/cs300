import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Exceptional Vending Machine: A better vending machine making use of exceptions,
//           inheritance and classes. It's exceptional! Haha, get it? Exceptional? Haha, I'm so
//           funny. Just kidding. I'm not funny
// Course:   COMP SCI 300 Fall 2022
//
// Author:   Nico Salm
// Email:    nbsalm@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         Kendall!! who is an awesome TA in general advice and providing a try/catch
//                  template to our discord for use.
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements testers to check the correctness of the methods
 * implemented in 004 Exceptional Vending Machine
 */
public class ExceptionalVendingMachineTester {

  /**
   * Checks the correctness of the constructor of the class Item when passed
   * invalid inputs
   *
   * @return true if the test verifies a correct functionality and false if any
   *         bug is detected
   */

  public static boolean testItemConstructorNotValidInput() {
    { // test 1: description is null
      try { // null description
        Item item = new Item(null, 0);
        // we expect the program to throw an exception before reaching this line
        System.out.println("testItemConstructorNotValidInput-1 failed. Expected exception when"
            + "description date is null.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testItemConstructorNotValidInput-1 failed. Unexpected exception when"
            + "expiration date is null.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 2: description is empty
      try { // empty description string
        Item item = new Item("   ", 0);
        // we should not reach this line
        System.out.println("testItemConstructorNotValidInput-2 failed. Expected exception when"
            + "description date is an empty string.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testItemConstructorNotValidInput-2 failed. Unexpected exception when"
            + "expiration date is an empty string.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 3: expiration date is negative
      try { // negative expiration date
        Item item = new Item("item", -13);
        // we should not reach this line
        System.out.println("testItemConstructorNotValidInput-3 failed. Expected exception when"
            + "expiration date is negative.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testItemConstructorNotValidInput-3 failed. Unexpected exception when"
            + "expiration date is negative.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    return true; // 3 tests passed, no bugs detected
  }

  /**
   * Checks the correctness of the constructor of the class Item,
   * Item.getDescription(),
   * Item.getExpirationDate(), Item.setDescription(), and Item.toString() when
   * passed valid inputs
   *
   * @return true if the test verifies a correct functionality and false if any
   *         bug is detected.
   */
  public static boolean testItemConstructorGettersSetters() {
    { // test 1: description is "item", expiration date is 10
      Item item = new Item("item", 10);

      if (!item.getDescription().equals("item")) {
        System.out.println("testItemConstructorGettersSetters-1 failed. Expected description to be"
            + " \"item\" but was " + item.getDescription());
        return false;
      }
      if (item.getExpirationDate() != 10) {
        System.out.println("testItemConstructorGettersSetters-1 failed. Expected expiration date to"
            + " be 10 but was " + item.getExpirationDate());
        return false;
      }
      if (!item.toString().equals("item: 10")) {
        System.out.println("testItemConstructorGettersSetters-1 failed. Expected toString() to be"
            + " \"item: 10\" but was " + item.toString());
        return false;
      }
    }
    return true; // 1 test passed, no bugs detected
  }

  /**
   * Checks the correctness of the Item.equals() method. You should consider at
   * least the following
   * four scenarios. (1) Create an item with valid description and expiration
   * date, comparing it to
   * itself should return true. (2) Two items having the same description but
   * different expiration
   * dates should be equal. (3) Passing a null reference to the Item.equals()
   * method should return
   * false. (4) An item MUST NOT be equal to an object NOT instance of the class
   * Item, for instance
   * a string object.
   *
   * @return true if the test verifies a correct functionality and false if any
   *         bug is detected
   */
  public static boolean testItemEquals() {
    { // test 1: compare an item to itself
      Item item = new Item("item", 1);
      if (!item.equals(item)) {
        System.out.println("testItemEquals-1 failed. Expected item to be equal to itself.");
        return false;
      }
    }
    { // test 2: two items with same description but different expiration dates should
      // be equal
      Item item1 = new Item("item", 1);
      Item item2 = new Item("item", 2);
      if (!item1.equals(item2)) {
        System.out.println("testItemEquals-2 failed. Expected item1 to be equal to item2.");
        return false;
      }
    }
    { // test 3: passing a null reference to the Item.equals() method should return
      // false
      Item item = new Item("item", 1);
      if (item.equals(null)) {
        System.out.println("testItemEquals-3 failed. Expected item to not be equal to null.");
        return false;
      }
    }
    { // test 4: an item MUST NOT be equal to an object NOT instance of the class
      // Item, for instance
      // a string object
      Item item = new Item("item", 1);
      if (item.equals("item")) {
        System.out.println("testItemEquals-4 failed. Expected item to not be equal to a string.");
        return false;
      }
    }
    return true; // 4 tests passed, no bugs detected
  }

  /**
   * Checks the correctness of the constructor of the ExceptionalVendingMachine
   * when passed invalid
   * input
   *
   * @return true if the test verifies a correct functionality and false if any
   *         bug is detected
   */
  public static boolean testExceptionalVendingMachineConstructor() {
    { // test 1: capacity is negative
      try { // negative capacity
        ExceptionalVendingMachine machine = new ExceptionalVendingMachine(-1);
        // we expect the program to throw an exception before reaching this line
        System.out.println("testExceptionalVendingMachineConstructor-1 failed. Expected exception"
            + " when capacity is negative.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineConstructor-1 failed. Unexpected exception"
            + " when capacity is negative.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 2: capacity is 0
      try { // capacity is 0
        ExceptionalVendingMachine machine = new ExceptionalVendingMachine(0);
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineConstructor-2 failed. Expected exception"
            + " when capacity is 0.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineConstructor-2 failed. Unexpected exception"
            + " when capacity is 0.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    return true; // 2 tests passed, no bugs detected
  }

  /**
   * Checks the correctness of the following methods defined in the
   * ExceptionalVendingMachine class
   * when an exception is expected to be thrown:
   *
   * addItem(), containsItem(), getIndexNextItem(), getItemAtIndex(),
   * getItemOccurrences(),
   * getItemOccurrencesByExpirationDate(), removeNextItem().
   *
   * @return true if the test verifies a correct functionality and false if any
   *         bug is detected
   */
  public static boolean testExceptionalVendingMachineAddContainsRemoveGetters() {

    { // test 1: addItem() should throw exception when the machine is full
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      machine.addItem("item", 1); // add an item to the machine
      try {
        machine.addItem("item2", 1); // try to add another item to the machine
        // we expect the program to throw an exception before reaching this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-1 failed for "
            + "addItem(). Expected exception when adding item2 to a full machine.");
        return false; // no exception thrown
      } catch (IllegalStateException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-1 failed for "
            + "addItem(). Unexpected exception when adding an item to a full machine.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 2: containsItem()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      machine.addItem("item", 1); // add an item to the machine
      // scenario 1: null reference
      try {
        machine.containsItem(null); // try to check if null is in the machine
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-2.1 failed for "
            + "containsItem(). Expected exception when checking if null is in the machine.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-2.1 failed for "
            + "containsItem(). Unexpected exception when checking if null is in the machine.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 2: whitespace string
      try {
        machine.containsItem("  "); // try to check if whitespace is in the machine
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-2.2 failed for "
            + "containsItem(). Expected exception when checking if whitespace is in the machine.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-2.2 failed for "
            + "containsItem(). Unexpected exception when checking if whitespace is in the machine.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 3: getIndexNextItem()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(3);
      machine.addItem("item", 1); // add an item to the machine
      machine.addItem(" item3", 15);
      machine.addItem("coffee  ", 12);
      // scenario 1: null reference
      try {
        machine.getIndexNextItem(null); // try to get the index of null
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-3.1 failed for "
            + "getIndexNextItem(). Expected exception when getting the index of null.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-3.1 failed for "
            + "getIndexNextItem(). Unexpected exception when getting the index of null.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 2: whitespace string
      try {
        machine.getIndexNextItem("  "); // try to get the index of whitespace
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-3.2 failed for "
            + "getIndexNextItem(). Expected exception when getting the index of whitespace.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-3.2 failed for "
            + "getIndexNextItem(). Unexpected exception when getting the index of whitespace.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 3: item not in the machine
      try {
        machine.getIndexNextItem("item2"); // try to get the index of nonexistent item
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-3.3 failed for "
            + "getIndexNextItem(). Expected exception when getting the index of an item not in the "
            + "machine.");
        return false; // no exception thrown
      } catch (NoSuchElementException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-3.3 failed for "
            + "getIndexNextItem(). Unexpected exception when getting the index of an item not in "
            + "the machine.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 4: getItemAtIndex()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      machine.addItem("item", 1); // add an item to the machine
      // scenario 1: index is negative
      try {
        machine.getItemAtIndex(-1); // try to get the item at index -1
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-4.1 failed for "
            + "getItemAtIndex(). Expected exception when getting the item at index -1.");
        return false; // no exception thrown
      } catch (IndexOutOfBoundsException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-4.1 failed for "
            + "getItemAtIndex(). Unexpected exception when getting the item at index -1.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 2: index is greater than the size of the machine
      try {
        machine.getItemAtIndex(2); // try to get the item at index 1
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-4.2 failed for "
            + "getItemAtIndex(). Expected exception when getting the item at index 2.");
        return false; // no exception thrown
      } catch (IndexOutOfBoundsException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-4.2 failed for "
            + "getItemAtIndex(). Unexpected exception when getting the item at index 2.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 5: getItemOccurrences()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      machine.addItem("item", 1); // add an item to the machine
      // scenario 1: null reference
      try {
        machine.getItemOccurrences(null); // try to get the occurrences of null
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-5.1 failed for "
            + "getItemOccurrences(). Expected exception when getting the occurrences of null.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-5.1 failed for "
            + "getItemOccurrences(). Unexpected exception when getting the occurrences of null.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 2: whitespace string
      try {
        machine.getItemOccurrences(" "); // try to get the occurrences of whitespace
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-5.2 failed for "
            + "getItemOccurrences(). Expected exception when getting the occurrences of whitespace.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-5.2 failed for "
            + "getItemOccurrences(). Unexpected exception when getting the occurrences of "
            + "whitespace.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 6: getItemOccurrencesByExpirationDate()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      machine.addItem("item", 1); // add an item to the machine
      // scenario 1: null reference
      try {
        machine.getItemOccurrencesByExpirationDate(null, 1);
        // try to get the occurrences of null
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-6.1 failed for "
            + "getItemOccurrencesByExpirationDate(). Expected exception when getting the "
            + "occurrences of null.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-6.1 failed for "
            + "getItemOccurrencesByExpirationDate(). Unexpected exception when getting the "
            + "occurrences of null.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 2: whitespace string
      try {
        machine.getItemOccurrencesByExpirationDate(" ", 1);
        // try to get the occurrences of whitespace
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-6.2 failed for "
            + "getItemOccurrencesByExpirationDate(). Expected exception when getting the "
            + "occurrences of whitespace.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-6.2 failed for "
            + "getItemOccurrencesByExpirationDate(). Unexpected exception when getting the "
            + "occurrences of whitespace.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 3: expirationDate negative
      try {
        machine.getItemOccurrencesByExpirationDate("item", -1);
        // try to get the occurrences of an item with negative expirationDate
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-6.3 failed for "
            + "getItemOccurrencesByExpirationDate(). Expected exception when getting the "
            + "occurrences of item with negative expirationDate.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-6.3 failed for "
            + "getItemOccurrencesByExpirationDate(). Unexpected exception when getting the "
            + "occurrences of item with negative expirationDate.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    { // test 7: removeNextItem()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      machine.addItem("item", 1); // add an item to the machine
      // scenario 1: null reference
      try {
        machine.removeNextItem(null); // try to remove null
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-7.1 failed for "
            + "removeNextItem(). Expected exception when removing null.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-7.1 failed for "
            + "removeNextItem(). Unexpected exception when removing null.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 2: whitespace string
      try {
        machine.removeNextItem(" "); // try to remove whitespace
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-7.2 failed for "
            + "removeNextItem(). Expected exception when removing whitespace.");
        return false; // no exception thrown
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-7.2 failed for "
            + "removeNextItem(). Unexpected exception when removing whitespace.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
      // scenario 3: item not in the machine
      try {
        machine.removeNextItem("item2"); // try to remove nonexistent item
        // we should not reach this line
        System.out.println("testExceptionalVendingMachineAdd ContainsRemoveGetters-7.3 failed for "
            + "removeNextItem(). Expected exception when removing an item not in the machine.");
        return false; // no exception thrown
      } catch (NoSuchElementException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testExceptionalVendingMachineAddContainsRemoveGetters-7.3 failed for "
            + "removeNextItem(). Unexpected exception when removing an item not in the machine.");
            e.printStackTrace();
        return false; // wrong exception thrown
      }
    }
    return true; // no bugs found over 7 tests
  }

  /**
   * Checks the correctness of isEmpty(), size(), and isFull() methods defined in
   * the ExceptionalVendingMachine class
   *
   * @return true if the test verifies a correct functionality and false if any
   *         bug is detected
   */
  public static boolean testEmptySizeFullExceptionalVendingMachine() {
    { // test 1: isEmpty()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(3);
      // scenario 1: empty machine
      if (!machine.isEmpty()) {
        // we expect the program to throw an exception before reaching this line
        System.out.println("testEmptySizeFullExceptionalVendingMachine-1.1 failed for isEmpty(). "
            + "Expected true when checking if an empty machine is empty.");
        return false;
      }
      // scenario 2: non-empty machine
      machine.addItem("item", 1);
      if (machine.isEmpty()) {
        // we should not reach this line
        System.out.println("testEmptySizeFullExceptionalVendingMachine-1.2 failed for isEmpty(). "
            + "Expected false when checking if a non-empty machine is empty.");
        return false;
      }
    }
    { // test 2: size()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(3);
      // scenario 1: empty machine
      if (machine.size() != 0) {
        System.out.println("testEmptySizeFullExceptionalVendingMachine-2.1 failed for size(). "
            + "Expected 0 when checking the size of an empty machine.");
        return false;
      }
      // scenario 2: non-empty machine
      machine.addItem("item", 1);
      if (machine.size() != 1) {
        System.out.println("testEmptySizeFullExceptionalVendingMachine-2.2 failed for size(). "
            + "Expected 1 when checking the size of a non-empty machine.");
        return false;
      }
    }
    { // test 3: isFull()
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      // scenario 1: empty machine
      if (machine.isFull()) {
        System.out.println("testEmptySizeFullExceptionalVendingMachine-3.1 failed for isFull(). "
            + "Expected false when checking if an empty machine is full.");
        return false;
      }
      // scenario 2: non-empty machine
      machine.addItem("item", 1);
      if (!machine.isFull()) {
        System.out.println("testEmptySizeFullExceptionalVendingMachine-3.2 failed for isFull(). "
            + "Expected true when checking if a non-empty machine is full.");
        return false;
      }
    }
    return true; // no bugs found over 3 tests
  }

  /**
   * Checks the correctness of loadOneItem method with respect to its
   * specification. Consider at
   * least the four following scenarios. (1) Successful scenario for loading one
   * item with a valid
   * string representation to a non-full vending machine. (2) Unsuccessful
   * scenario for passing null
   * or a blank string (for instance one space or empty string) to the
   * loadOneItem() method call, an
   * IllegalArgumentEXception is expected to be thrown. (3) Unsuccessful scenario
   * for passing a
   * badly formatted string to the loadOneItem method. A DataFormatException is
   * expected to be
   * thrown. (4) Unsuccessful scenario for trying to load an item with a valid
   * representation to a
   * full vending machine. An IllegalStateException is expected to be thrown.
   *
   * @return true if the test verifies a correct functionality and false if any
   *         bug is detected
   */
  public static boolean testLoadOneItem() {
      ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
      // scenario 1: successful scenario
      try {
        machine.loadOneItem("item: 1");
        if (!machine.containsItem("item")) {
          System.out.println("testLoadOneItem-1.1 failed for loadOneItem(). Expected item to be "
              + "loaded to the machine.");
          return false;
        }
      } catch (Exception e) {
        System.out.println("testLoadOneItem-1.1 failed for loadOneItem(). Unexpected exception "
            + "thrown when loading a valid item to a non-full machine.");
            e.printStackTrace();
        return false;
      }
      // scenario 2: null reference
      try {
        machine.loadOneItem(null);
        System.out.println("testLoadOneItem-1.2 failed for loadOneItem(). Expected exception "
            + "when loading null.");
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testLoadOneItem-1.2 failed for loadOneItem(). Unexpected exception "
            + "thrown when loading null.");
            e.printStackTrace();
        return false;
      }
      // scenario 3: whitespace string
      try {
        machine.loadOneItem(" ");
        System.out.println("testLoadOneItem-1.3 failed for loadOneItem(). Expected exception "
            + "when loading whitespace.");
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testLoadOneItem-1.3 failed for loadOneItem(). Unexpected exception "
            + "thrown when loading whitespace.");
            e.printStackTrace();
        return false;
      }
      // scenario 4: badly formatted string
      try {
        machine.loadOneItem("item,1");
        System.out.println("testLoadOneItem-1.4 failed for loadOneItem(). Expected exception "
            + "when loading a badly formatted string.");
        return false;
      } catch (DataFormatException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testLoadOneItem-1.4 failed for loadOneItem(). Unexpected exception "
            + "thrown when loading a badly formatted string.");
            e.printStackTrace();
        return false;
      }
      // scenario 5: full machine
      try { // load one more item
        machine.loadOneItem("item2: 1");
        System.out.println("testLoadOneItem-1.5 failed for loadOneItem(). Expected exception "
            + "when loading an item to a full machine.");
        return false;
      } catch (IllegalStateException e) {
        // expected
      } catch (Exception e) {
        System.out.println("testLoadOneItem-1.5 failed for loadOneItem(). Unexpected exception "
            + "thrown when loading an item to a full machine.");
            e.printStackTrace();
        return false;
      }
      return true; // no bugs found over 5 tests
    }

  /**
   * Checks the correctness of loadItems method with respect to its
   * specification. Consider at least the four following scenarios. (1)
   * Successful scenario for loading items from a file with valid string
   * representations to a non-full vending machine. (2) Unsuccessful scenario for
   * passing a file name
   * that does not exist to the loadItems method. A FileNotFoundException
   * is expected to be thrown. (3) Unsuccessful scenario for trying to load items
   * from a file with valid string representations to a full vending machine. An
   * IllegalStateException is expected to be thrown.
   *
   * @return true if the test verifies correct functionality and false if any
   *         bug is detected.
   * @requires file name named "items.txt" in the same directory as this class
   */
  public static boolean testLoadItems() {
    ExceptionalVendingMachine machine = new ExceptionalVendingMachine(5);
    File items = new File("items.txt");
    { // test 1: everything works out
      try {
        machine.loadItems(items);
        if (!machine.containsItem("item1") && !machine.containsItem("item2")
            && machine.containsItem("item3") && machine.containsItem("item4")
            && !machine.containsItem("item5")) {
          System.out.println("testLoadItems-1.1 failed for loadItems(). Expected all items to be "
              + "loaded to the machine.");
              System.out.println(machine.getItemsSummary());
          return false;
        }
      } catch (Exception e) {
        System.out.println("testLoadItems-1.1 failed for loadItems(). Unexpected exception "
            + "thrown when loading valid items to a non-full machine.");
            e.printStackTrace();
        return false;
      }
      return true;
    }
  }

  /**
   * Tests the thing with the thing please I need to go to sleep.
   *
   * @return true if the test verifies correct functionality and false if any bug
   *         is detected.
   */
  public static boolean testSaveVendingMachineSummary() {
    ExceptionalVendingMachine machine = new ExceptionalVendingMachine(1);
    machine.addItem("item", 1); // add an item to the machine

    File summary = new File("summary.txt"); // create a new file

    try { // should not throw any exception
      summary.createNewFile();
    } catch (IOException e) { // we should not reach this line
      System.out.println("An error occurred creating a summary file.");
      return false;
    }

    try {
      machine.saveVendingMachineSummary(summary); // save the summary to the file
    } catch (Exception e) { // we should not reach this line
      System.out.println("testSaveVendingMachineSummary failed for saveVendingMachineSummary(). "
          + "Unexpected exception when saving a file that exists.");
          e.printStackTrace();
      return false;
    }
    summary.delete(); // delete the file after the test
    return true; // no bugs found
  }

  /**
   * Invokes all the public tester methods implemented in this class
   *
   * @return true if all testers pass with no errors, and false if any of the
   *         tester fails.
   */
  public static boolean runAllTests() {
    return testItemConstructorNotValidInput() && testItemConstructorGettersSetters() &&
        testItemEquals() && testExceptionalVendingMachineConstructor() &&
        testExceptionalVendingMachineAddContainsRemoveGetters() &&
        testEmptySizeFullExceptionalVendingMachine() && testLoadOneItem();
  }

  /**
   * Main method for the tester class
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("Required tests passed: " + runAllTests());
    System.out.println("testLoadItems(): " + testLoadItems());
    System.out.println("testSaveVendingMachineSummary(): " + testSaveVendingMachineSummary());
  }

}
