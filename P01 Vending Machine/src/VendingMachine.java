//////////////// P01 - VENDING MACHINE //////////////////////////
//
// Title:    P01 VendingMachine Class - Contains methods for program functionality.
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


/**
 * This class contains all methods needed for Vending Machine functionality. That is, the methods
 * needed for the program to run as intended.
 *
 * @author Nico Salm (nbsalm@wisc.edu)
 */

public class VendingMachine {
  /**
   * Adds/appends an item defined by its description and expirationDate to a vending machine
   * represented by an oversize array of strings defined by the two-dimensional array items and its
   * size itemsCount. The item will be added to the end of the array. If the vending machine is
   * full, the new item won't be added and the method returns the items count passed as input
   * without making any changes to the contents of the array items.
   *
   * @param description    description of the item to be added to the vending machine
   * @param expirationDate a string parsable to a positive integer which represents the expiration
   *                       date of the item. The date "0" represents January 1st 2023.
   * @param items          a two-dimensional of strings storing items. items[i][0] and items[i][1]
   *                       respectively represent the description and the expiration date of the
   *                       item stored at index i
   * @param itemsCount     number of items in the vending machine
   * @return the size of the vending machine after trying to add the new item
   */

   

  public static int addItem(String description, String expirationDate, String[][] items,
      int itemsCount) {
    // Note that we suppose that the expirationDate is valid, meaning it is correctly
    // parsable to a positive integer

    if (itemsCount == 0 && itemsCount < items.length) { // if the vending machine is empty
      // item is added to the first spot in the vending machine array.
      items[0] = new String[] {description, expirationDate};
      itemsCount++; // count of items within the vending machine increases by 1.
    } else if (itemsCount < items.length) { // if vending machine is not full
      items[itemsCount] = new String[] {description, expirationDate};
      itemsCount++; // count of items within vending machine increases by 1.
    }

    return itemsCount; // returns size of vending machine after trying to add the new item.
  }

  /**
   * Returns without removing a string representation of the item at the given index within the
   * vending machine defined by the array items and its size itemsCount. This method does not make
   * any changes to the contents of the vending machine.
   *
   * @param items      two dimensional array storing items within a vending machine where
   *                   items[i][0] represents the description of the item at index i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @param index      index of an item within the provided vending machine
   * @return a string representation of the item stored at the given index within the vending
   * machine defined by items and itemsCount. The returned string must have the following
   * format: "description (expiration date)". If the provided index is out of the range of
   * indexes 0..itemsCount-1, the method returns "ERROR INVALID INDEX"
   */
  public static String getItemAtIndex(int index, String[][] items, int itemsCount) {

    if (index >= 0 && index < itemsCount) { // if index points to an existing item
      return items[index][0] + " (" + items[index][1] + ")"; // returns formatted string
    }
    return "ERROR INVALID INDEX"; // error if the index is out of [0, itemsCount - 1];
  }

  /**
   * Returns without removing the index of the item having the provided description and the smallest
   * expiration date within the vending machine defined by the array items and its size itemsCount.
   *
   * @param description description of the item to get its index
   * @param items       two-dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the index of the next item, meaning the item with the given description and the
   * smallest expiration date. If no match found, return -1.
   */
  public static int getIndexNextItem(String description, String[][] items, int itemsCount) {
    // If the vending machine contains more than one item with the given description,
    // return the index of the one with the smallest expiration date.

    int index = -1; // index to keep track of lowest expiration.
    int lowestExpiration = Integer.MAX_VALUE; // value of lowest expiration for comparison.

    for (int i = 0; i < itemsCount; i++) { // for each item in the vending machine
      if (items[i][0].equals(description) && Integer.parseInt(items[i][1]) < lowestExpiration) {
        lowestExpiration = Integer.parseInt(items[i][1]);
        index = i; // selects the corresponding item with lowest expiration date.
      }
    }
    return index; // returns index of matching item with lowest expiration date.
  }

  /**
   * Removes the item having the provided description with the smallest expiration date within the
   * vending machine defined by the array items and its size itemsCount. This method should maintain
   * the order of precedence of items in the vending machine. This means that the remove operation
   * involves a shift operation.
   *
   * @param description description of the item to remove or dispense
   * @param items       array storing items within a vending machine
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return size of the vending machine after removing the item with the given description and the
   * smallest expiration date. If no match found, this method must return the provided
   * itemsCount without making any change to the contents of the vending machine.
   */
  public static int removeNextItem(String description, String[][] items, int itemsCount) {

    // checks to see if item with given description exists in vending machine,
    // returns without modifying itemsCount if no item is detected.
    if (getIndexNextItem(description, items, itemsCount) == -1) {
      return itemsCount;
    }

    // if an item exists to remove, it will be removed, and the items following it in the vending
    // machine will be shifted one to the left.
    for (int i = getIndexNextItem(description, items, itemsCount); i < itemsCount; i++) {
      items[i] = items[i + 1];
    }
    items[itemsCount - 1] = null; // the final item (which is now duplicated) will be removed.

    itemsCount--; // itemsCount decrements because an item was removed.

    return itemsCount; // returns itemsCount - 1 if an item is successfully removed, or
    // itemsCount if nothing is removed.
  }

  /**
   * Returns the number of occurrences of an item with a given description within the vending
   * machine defined by items and itemsCount
   *
   * @param description description (name) of an item
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the number of occurrences of an item with a given description within the vending
   * machine
   */
  public static int getItemOccurrences(String description, String[][] items, int itemsCount) {

    int numOfOccurences = 0; // tracks the number of an item in the vending machine.

    for (int i = 0; i < itemsCount; i++) {
      if (items[i][0].equals(description)) {
        numOfOccurences++; // increments if found
      }
    }
    return numOfOccurences; // returns the amount of items with the given description, or 0 if none.
  }

  /**
   * Checks whether a vending machine defined by the array items and its size itemsCount contains at
   * least an item with the provided description
   *
   * @param description description (name) of an item to search
   * @param items       two dimensional array storing items within a vending machine where
   *                    items[i][0] represents the description of the item at index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return true if there is a match with description in the vending machine, false otherwise
   */
  public static boolean containsItem(String description, String[][] items, int itemsCount) {

    for (int i = 0; i < itemsCount; i++) { // looks to see if the item is in the vending machine
      if (items[i][0].equals(description)) {
        return true; // if found, returns true
      }
    }
    return false; // returns false if the given item is not in the vending machine
  }

  /**
   * Returns the number of items in the vending machine with a specific description and whose
   * expiration dates are greater or equal to a specific expiration date
   *
   * @param description    description of the item to find its number of occurrences
   * @param expirationDate specific (earliest) expiration date
   * @param items          two dimensional array storing items within a vending machine where
   *                       items[i][0] represents the description of the item at index i and
   *                       items[i][1] stores its expiration date.
   * @param itemsCount     (size) number of items stored in the vending machine
   * @return the number of items with a specific description and whose expiration date is greater or
   * equal to the given one
   */
  public static int getItemsOccurrencesByExpirationDate(String description, String expirationDate,
      String[][] items, int itemsCount) {
    int numOfItems = 0; // counter to keep track of occurrences of item with an expiration date
    // greater than or equal to the one given.

    for (int i = 0; i < itemsCount; i++) { //traverses the vending machine
      if (items[i][0].equals(description) && Integer.parseInt(items[i][1]) >= Integer.parseInt(
          expirationDate)) { // checks description and expiration date
        numOfItems++; // increments
      }
    }
    return numOfItems; // returns number of occurrences
  }

  /**
   * Returns a summary of the contents of a vending machine in the following format: Each line
   * contains the description or item name followed by one the number of occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary description will be as
   * follows. "water (5)\nchocolate (10)\nsnack (7)"
   * If the vending machine is empty, this method returns an empty string ""
   *
   * @param items      two dimensional array storing items within a vending machine where
   *                   items[i][0] represents the description of the item at index i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @return a descriptive summary of the contents of a vending machine
   */
  public static String getItemsSummary(String[][] items, int itemsCount) {
    if (items[0] == null) {
      // returns empty string right away if vending machine is empty to save time.
      return "";
    }

    String list = ""; // creates an empty list to store the summary
    for (int i = 0; i < itemsCount; i++) {
      // adds each unique item to list, and includes count of duplicates that exist.
      int occurrences = getItemOccurrences(items[i][0], items, itemsCount);
      if (occurrences > 0 && !list.contains(items[i][0])) {
        list += items[i][0]; // "item (count of item)"
        list += " (" + occurrences + ")\n";
      }
    }
    return list; // returns a list of unique items in vending machine, without duplicates.
  }

}
