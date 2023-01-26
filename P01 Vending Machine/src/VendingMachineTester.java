//////////////// P01 - VENDING MACHINE //////////////////////////
//
// Title:    P01 VendingMachineTester Class - Contains tester methods used to ensure
//                                            program functions as intended without error.
// Course:   CS 300 Fall 2022
//
// Author:   Nico Salm
// Email:    nbsalm@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * Contains a variety of tester methods intended to test the functionality of the class
 * VendingMachine. Each test will return true or false depending on its success, and output a
 * debug message.
 *
 * @author Nico Salm (nbsalm@wisc.edu)
 */

public class VendingMachineTester {

  /**
   * Tests the VendingMachine class method 'getIndexNextItem' with 4 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testGetIndexNextItem() {
    // 1. Next item to be dispensed is NOT found: the expected output is -1
    {
      // define the vending machine
      String[][] items =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, null, null, null};
      int itemsCount = 3;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Taco", items, itemsCount) != -1) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did not "
                + "return -1 when no match found.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, null, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 2. Next item to be dispensed is at index 0
    {
      String[][] items =
          new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Tea", items, itemsCount) != 0) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did not "
                + "return the expected output when the vending machines contains multiple matches "
                + "with the provided item description and the matching item with the soonest "
                + "expiration date is at index 0.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 3. Next item to be dispensed is at the end of the array
    {
      String[][] items =
          new String[][] {{"Tea", "15"}, {"Blueberries", "20"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Blueberries", items, itemsCount) != 6) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did not "
                + "return the expected output when the vending machines contains multiple matches "
                + "with the provided item description and the matching item with the soonest "
                + "expiration date is at the end of the array");
        return false;
      }

      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "20"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 4. Next item to be dispensed is at the middle of the array
    {
      String[][] items =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Tea", items, itemsCount) != 3) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did not "
                + "return the expected output when the vending machines contains matches with the "
                + "provided item description with different expiration dates.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items passed as
      // input
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }
    return true; // No bug detected
  }

  /**
   * Tests the VendingMachine class method 'containsItem' with 2 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testContainsItem() {

    String[][] items = // defines the vending machine
        new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
            {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
    int itemsCount = 7;

    // 1. Ensures that if item matching the description is present, the method returns true.
    if (!VendingMachine.containsItem("Tea", items, itemsCount)) {
      System.out.println("testContainsItem-scenario 1. Problem detected: Your containsItem is not "
          + "returning true when the searched item is present in the array.");
      return false;
    }
    // 2. Ensures that if item matching the description is NOT present, the method returns false.
    if (VendingMachine.containsItem("Coffee", items, itemsCount)) {
      System.out.println("testContainsItem-scenario 2. Problem detected: Your containsItem is not "
          + "returning false when the searched item i not present in the array.");
      return false;
    }

    return true; // no bugs detected
  }

  /**
   * Tests the VendingMachine class method 'getItemAtIndex' with 2 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testGetItemAtIndex() {
    // 1. Checks if method acts correctly provided the index is out of range [0, itemsCount-1].
    {
      String[][] items = // defines the vending machine
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      int itemsCount = 7;

      // checks the correctness of the output.
      if (!VendingMachine.getItemAtIndex(100, items, itemsCount).equals("ERROR INVALID INDEX")) {
        System.out.println("testGetItemAtIndex-scenario 1. Problem detected: the method does not "
            + "output correctly given an out-of-bounds index.");
        return false;
      }

      // ensures no changes are made to the array.
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};

      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println("testGetItemAtIndex-scenario 1. Problem detected: the method made "
            + "changes to the array.");
        return false;
      }
    }
    // 2. Checks if the method acts correctly provided the index is within range [0, itemsCount-1].
    {
      String[][] items =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      int itemsCount = 7;

      // checks correctness of the output.
      if (!VendingMachine.getItemAtIndex(1, items, itemsCount).equals("Blueberries (10)")) {
        System.out.println("testGetItemAtIndex-scenario 2. Problem detected: the method does not "
            + "output the correct item given the appropriate index, or the item is not formatted"
            + "correctly.");
        return false;
      }

      // ensures no changes are made to the array.
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};

      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println("testGetItemAtIndex-scenario 1. Problem detected: the method made "
            + "changes to the array.");
        return false;
      }
    }
    return true; // no bug detected
  }

  /**
   * Tests the VendingMachine class method 'getItemOccurrences' with 2 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testGetItemsOccurrences() {
    // 1. Ensures if searched description yields no results (i.e. the item is not present), method
    // returns zero.
    {
      // defines the vending machine
      String[][] items =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      int itemsCount = 7;

      // checks correctness of output.
      if (VendingMachine.getItemOccurrences("Coffee", items, itemsCount) != 0) {
        System.out.println("testGetItemOccurrences-scenario 1. Problem detected: Your "
            + "getItemOccurrences is returning something other than 0 when there are no "
            + "items with the given description in the vending machine.");
        return false;
      }

      // checks to make sure no changes were made to the array
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};

      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println("testGetItemOccurrences-scenario 1. Problem detected: Changes were made "
            + "to the content of the array passed as input.");
        return false;
      }
    }
    // 2. Ensures if searched description yields multiple occurrences of provided item
    // description, the method returns the amount of occurrences.
    {
      String[][] items =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};
      int itemsCount = 7;

      // checks correctness of output
      if (VendingMachine.getItemOccurrences("Blueberries", items, itemsCount) != 2) {
        System.out.println("testGetItemOccurrences-scenario 2. Problem detected: Your "
            + "getItemOccurrences is not counting and returning correctly when there several"
            + "items with the same description within the vending machine.");
        return false;
      }

      // checks to ensure no changes were made to the array.
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null, null};

      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println("testGetItemOccurrences-scenario 2. Problem detected: Changes were made "
            + "to the content of the array passed as input.");
        return false;
      }
    }
    return true; // no bug detected
  }

  /**
   * Tests the VendingMachine class method 'addItem' with 3 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testAddItem() {
    // 1. Checks if an item is added correctly to an empty vending machine.
    {
      String[][] items = // defines an empty vending machine.
          new String[][] {null, null, null, null, null};
      int itemsCount = 0;

      // checks the correctness of the output.
      if (VendingMachine.addItem("Tea", "10", items, itemsCount) != 1) {
        System.out.println("testAddItem-scenario 1. Problem detected: Your addItem did not return "
            + "an itemsCount of 1 when adding an item to an empty vending machine.");
        return false;
      }

      // checks to ensure only the expected changes were made to the array.
      String[][] expectedItems = new String[][] {{"Tea", "10"}, null, null, null, null};

      if (!Arrays.deepEquals(items, expectedItems)) {
        System.out.println("testAddItem-scenario 1. Problem detected: Changes made when adding "
            + "an item to an otherwise null array do not match the expected result.");
        return false;
      }
    }

    // 2. Checks if an item can be added correctly to a non-empty, non-full vending machine.
    {
      String[][] items = // defines a non-empty, non-full vending machine.
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, null, null, null};
      int itemsCount = 3;

      // checks the correctness of the output.
      if (VendingMachine.addItem("Tea", "10", items, itemsCount) != 4) {
        System.out.println("testAddItem-scenario 2. Problem detected: Your addItem did not return "
            + "the appropriate itemsCount when adding an item to a non-empty, non-full vending "
            + "machine.");
        return false;
      }

      // checks to ensure only the expected changes were made to the array.
      String[][] expectedItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "10"},
              null, null};

      if (!Arrays.deepEquals(items, expectedItems)) {
        System.out.println("testGetItemOccurrences-scenario 2. Problem detected: Changes made "
            + "to the content of the array passed as input do not match the expected changes when "
            + "the array is non-empty and non-full.");
        return false;
      }
    }

    // 3. Checks if adding an item to a full vending machine results in no change.
    {
      String[][] items = // defines a full vending machine
          new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}};
      int itemsCount = 7;

      // checks correctness of the output
      if (VendingMachine.addItem("Tea", "10", items, itemsCount) != 7) {
        System.out.println("testAddItem-scenario 3. Problem detected: Your addItem cannot add items"
            + " to a full vending machine.");
        return false;
      }

      // checks to ensure no changes were made to the array.
      String[][] originalItems =
          new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}};

      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println("testGetItemOccurrences-scenario 3. Problem detected: Changes should "
            + "not be made to a vending machine array when that array is full.");
        return false;
      }
    }
    return true; // no bug detected
  }

  /**
   * Tests the VendingMachine class method 'removeItem' with 4 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testRemoveNextItem() {
    // 1. Checks to see if removing a non-existing item from the vending machine results in
    // no change to the vending machine.
    {
      String[][] items = // defines a non-empty, non-full vending machine.
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Tea", "20"}, null, null, null};
      int itemsCount = 3;

      // checks correctness of the output.
      if (VendingMachine.removeNextItem("Coffee", items, 3) != itemsCount) {
        System.out.println(
            "testRemoveNextItem-scenario 1. Problem detected. Your removeNextItem is "
                + "trying to remove an item that doesn't exist.");
        return false;
      }

      // checks to ensure no changes were made to the array.
      String[][] originalItems =
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Tea", "20"}, null, null, null};
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println("testRemoveNextItem-scenario 1. Problem detected. Changes were made to "
            + "the array when the item being removed was not present in the array.");
        return false;
      }

    }
    // 2. Removing an item at index 0 of the vending machine array.
    {
      String[][] items = // defines the vending machine.
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Tea", "20"}, null, null, null};
      int itemsCount = 3;

      // checks correctness of the output, and ensures the array is correctly "array"d hah.
      if (VendingMachine.removeNextItem("Tea", items, 3) != itemsCount - 1) {
        System.out.println("testRemoveNextItem-scenario 2. Problem detected. Your removeNextItem "
            + "is having trouble removing the item at index 0 of the vending machine array.");
        return false;
      }

      // checks to ensure only the expected changes were made to the array.
      String[][] expectedItems =
          new String[][] {{"Blueberries", "10"}, {"Tea", "20"}, null, null, null, null};

      if (!Arrays.deepEquals(items, expectedItems)) {
        System.out.println("testRemoveNextItem-scenario 2. Problem detected. The changes made to "
            + "the array do not match the expected changes.");
        return false;
      }
    }
    // 3. Removing an item at index itemsCount (the end of the array).
    {
      String[][] items = // defines the vending machine
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Coffee", "20"}, null, null, null};
      int itemsCount = 3;

      // checks correctness of the output
      if (VendingMachine.removeNextItem("Coffee", items, 3) != itemsCount - 1) {
        System.out.println("testRemoveNextItem-scenario 3. Problem detected. Your removeNextItem "
            + "cannot remove the item at index itemsCount of the vending machine array.");
        return false;
      }

      // checks to ensure only the expected changes were made to the array
      String[][] expectedItems = // defines a vending machine to compare 'items' against.
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, null, null, null, null};

      if (!Arrays.deepEquals(items, expectedItems)) {
        System.out.println("testRemoveNextItem-scenario 3. Problem detected. The changes made to "
            + "the array do not match the expected changes.");
        return false;
      }
    }
    // 4. Removing an item in the middle of the array.
    {
      String[][] items = // defines a vending machine with a middle.
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Coffee", "20"}, null, null, null};
      int itemsCount = 3;

      // checks correctness of output
      if (VendingMachine.removeNextItem("Blueberries", items, 3) != itemsCount - 1) {
        System.out.println("testRemoveNextItem-scenario 3. Problem detected. Your removeNextItem "
            + "cannot remove the an item from the middle of the vending machine array.");
        return false;
      }

      // checks to ensure only the expected changes were made to the array
      String[][] expectedItems = // defines a vending machine to compare 'items' against.
          new String[][] {{"Tea", "15"}, {"Coffee", "20"}, null, null, null, null};

      if (!Arrays.deepEquals(items, expectedItems)) {
        System.out.println("testRemoveNextItem-scenario 3. Problem detected. The changes made to "
            + "the array do not match the expected changes.");
        return false;
      }
    }
    return true; // no bug detected
  }

  /**
   * Tests the VendingMachine class method 'getItemOccurrencesByExpirationDate' with 2 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testGetItemsOccurrencesByExpirationDate() {
    // 1. Checks to ensure the method returns 0 when there are no items with expirationDates greater
    // than or equal to the given date.
    {
      // defines an array which should remain unaltered.
      String[][] items =
          new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null};
      int itemsCount = 7;

      // checks correctness of the output.
      if (VendingMachine.getItemsOccurrencesByExpirationDate("Blueberries", "20", items, itemsCount)
          != 0) {
        System.out.println("testGetItemOccurrencesByExpirationDate-scenario 1. Problem detected: "
            + "Your getItemOccurrencesByExpirationDate is returning something other than 0 when "
            + "searching for an item that does not exist in the vending machine.");
        return false;
      }

      // checks to ensure the array remains unaltered.
      String[][] originalItems =
          new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
              {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null};

      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println("testGetItemOccurrencesByExpirationDate-scenario 1. Problem detected: "
            + "Your getItemOccurrencesByExpirationDate is changing the content of the array.");
        return false;
      }
    }

    // 2. Checks to ensure the method returns the correct number of items when there are items with
    // expiration dates greater than or equal to the given date.

    // checks correctness of the output.
    String[][] items =
        new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
            {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null};
    int itemsCount = 7;

    if (VendingMachine.getItemsOccurrencesByExpirationDate("Tea", "1", items, itemsCount) != 3) {
      System.out.println("testGetItemOccurrencesByExpirationDate-scenario 2. Problem detected. "
          + "Your getItemOccurrences is not returning the correct count when there are multiple "
          + "items with expiration dates greater than or equal to expirationDate.");
      return false;
    }
    // checks to ensure the array remains unaltered.
    String[][] originalItems =
        new String[][] {{"Tea", "1"}, {"Blueberries", "10"}, {"Vodka", "20"}, {"Tea", "5"},
            {"Taco", "30"}, {"Tea", "15"}, {"Blueberries", "10"}, null};

    if (!Arrays.deepEquals(items, originalItems)) {
      System.out.println("testGetItemOccurrencesByExpirationDate-scenario 1. Problem detected: "
          + "Your getItemOccurrencesByExpirationDate is changing the content of the array.");
      return false;
    }

    return true; // no bugs detected
  }

  /**
   * Tests the VendingMachine class method 'getItemsSummary' with 3 scenarios.
   *
   * @return boolean true or false to communicate whether the tests pass or fail, and problem
   * message readout to assist in pinpointing bug location.
   */
  public static boolean testGetItemsSummary() {
    // 1. Ensures an empty string is returned when the vending machine is empty.
    {
      // defines an empty vending machine.
      String[][] items = new String[][] {null, null, null, null};

      // checks correctness of the output.
      if (!VendingMachine.getItemsSummary(items, 0).equals("")) {
        System.out.println("testGetItemsSummary-scenario 1. Problem detected. Your getItemsSummary "
            + "is not returning an empty String when given an empty vending machine.");
        return false;
      }
    }

    // 2. Ensures a vending machine with no duplicates outputs the appropriate item list.
    {
      String[][] items = // defines a vending machine with no duplicates.
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Coffee", "20"}, null, null, null};
      int itemsCount = 3;

      // creates a sample output to compare the method output against.
      String output = """
          Tea (1)
          Blueberries (1)
          Coffee (1)
          """; //expected method output

      // checks correctness of the output.
      if (!VendingMachine.getItemsSummary(items, itemsCount).equals(output)) {
        System.out.println("testGetItemsSummary-scenario 2. Problem detected. Your getItemsSummary "
            + "is not returning the right list when given a vending machine with no duplicates.");
        return false;
      }
    }
    // 3. Ensures a vending machine with duplicate items outputs the appropriate item list.
    {
      String[][] items = // defines a vending machine with duplicates.
          new String[][] {{"Tea", "15"}, {"Blueberries", "10"}, {"Coffee", "20"}, {"Coffee", "20"},
              null, null};
      int itemsCount = 4;

      // creates a sample output to compare the method output against.
      String output = """
          Tea (1)
          Blueberries (1)
          Coffee (2)
          """;

      // checks correctness of the output.
      if (!VendingMachine.getItemsSummary(items, itemsCount).equals(output)) {
        System.out.println("testGetItemsSummary-scenario 2. Problem detected. Your getItemsSummary "
            + "is not returning the right list when given a vending machine WITH DUPLICATES.");
        return false;
      }
    }
    return true; // no bugs detected
  }


  /**
   * Runs all tester methods.
   *
   * @return boolean true or false to communicate if all tests return true.
   */
  public static boolean runAllTests() {
    // checks if all methods run as intended, and returns false if one or more error.
    if (!(testGetItemsSummary() && testGetItemsOccurrencesByExpirationDate() && testContainsItem()
        && testAddItem() && testGetIndexNextItem() && testRemoveNextItem() && testGetItemAtIndex()
        && testRemoveNextItem())) {
      System.out.println("You're fucked, things are broke. Abandon ship!!");
      return false;
    }
    return true; // no bugs are detected in any tester method.
  }

  /**
   * main method to run all tests.
   */
  public static void main(String[] args) {
    System.out.println("testGetIndexNextItem(): " + testGetIndexNextItem());
    System.out.println("testContainsItem(): " + testContainsItem());
    System.out.println("testGetItemAtIndex(): " + testGetItemAtIndex());
    System.out.println("testAddItem(): " + testAddItem());
    System.out.println("testRemoveNextItem(): " + testRemoveNextItem());
    System.out.println("testGetItemOccurrences(): " + testGetItemsOccurrences());
    System.out.println(
        "testGetItemOccurrencesByExpirationDate(): " + testGetItemsOccurrencesByExpirationDate());
    System.out.println("testGetItemsSummary(): " + testGetItemsSummary());
    System.out.println("runAllTests(): " + runAllTests());
  }
  // End of my VendingMachineTester... please rate 5/5 on GoodReads!
}
