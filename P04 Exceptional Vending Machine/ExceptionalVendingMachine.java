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
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

// Add import statement to relevant exceptions and FilePrinter or FileWriter
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * This class models a vending machine. When requested, the item with the
 * soonest expiration date
 * will be dispensed first.
 */
public class ExceptionalVendingMachine {
  private Item[] items; // array storing the items available within this vending machine
  private int size; // number of items available in this vending machine

  /**
   * Creates a new vending machine with a given capacity
   *
   * @param capacity maximum number of items that can be held by this vending
   *                 machine
   * @throws IllegalArgumentException with a descriptive error message if capacity
   *                                  is zero or
   *                                  negative
   */
  public ExceptionalVendingMachine(int capacity) {
    if (capacity <= 0) { // throws exception if capacity is zero or negative
      throw new IllegalArgumentException("capacity cannot be zero or negative");
    }
    
    items = new Item[capacity]; // creates new array of items with given capacity
    size = 0; // initializes size to 0
  }

  /**
   * Checks whether this vending machine is empty
   *
   * @return true if this vending machine is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0; // returns true if size is 0, false otherwise
  }

  /**
   * Checks whether this vending machine is full
   *
   * @return true if this vending machine is full, false otherwise
   */
  public boolean isFull() {
    return size() == items.length; // returns true if size is equal to the length of the array,
    // false otherwise
  }

  /**
   * Returns the total number of items available in this vending machine
   *
   * @return the size of this vending machine
   */
  public int size() {
    return size; // returns the size of the vending machine
  }

  /**
   * Appends an item defined by its description and expirationDate to this vending
   * machine. The item
   * will be added to the end of the vending machine.
   *
   * @param description    description of the item to be added to the vending
   *                       machine
   * @param expirationDate a positive integer which represents the expiration date
   *                       of the item.
   * @throws IllegalStateException    with a descriptive error message if the
   *                                  vending machine is
   *                                  full
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  description is null or
   *                                  blank or if expirationDate is negative (
   *                                  &lt; 0)
   */
  public void addItem(String description, int expirationDate) {
    if (isFull()) { // throws exception if the vending machine is full
      throw new IllegalStateException("vending machine is full");
    }
    if (description == null || description.isBlank()) { // throws exception if description is null
                                                        // or blank
      throw new IllegalArgumentException("description cannot be null or blank");
    }
    if (expirationDate < 0) { // throws exception if expiration date is negative
      throw new IllegalArgumentException("expiration date cannot be negative");
    }

    // create a new item and add it to the end of this vending machine
    items[size] = new Item(description, expirationDate);
    size++;
  }

  /**
   * Returns without removing the string representation of the item at the given
   * index within the
   * vending machine
   *
   * @param index index of an item within the vending machine
   * @return the string representation of the item stored at the given index
   *         within the vending
   *         machine defined by items and itemsCount. The returned string must
   *         have the following
   *         format: "description: expirationDate".
   * @throws IndexOutOfBoundsException with a descriptive error message if index <
   *                                   0 or index
   *                                   > size of the vending machine
   */
  public String getItemAtIndex(int index) {
    if (index < 0 || index >= size()) { // throws exception if index is less than 0 or greater than
                                        // the size of the vending machine
      throw new IndexOutOfBoundsException("index cannot be less than 0 or greater than the size of"
          + " the vending machine");
    }

    return items[index].toString(); // returns the string representation of the item at the given
                                    // index
  }

  /**
   * Returns the number of occurrences of items with a given description within
   * this vending machine
   *
   * @param description description (name) of an item
   * @return the number of occurrences of items with a given description within
   *         the vending machine
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  description is null or
   *                                  blank
   */
  public int getItemOccurrences(String description) {
    if (description == null || description.isBlank()) { // throws exception if description is null
                                                        // or blank
      throw new IllegalArgumentException("description cannot be null or blank");
    }

    int nbOccurrences = 0;
    for (int i = 0; i < size; i++) { // iterates through the array of items
      if (description.equals(items[i].getDescription())) { // increments nbOccurrences if the
                                                           // description of the item at index i
                                                           // matches the given description
        nbOccurrences++;
      }
    }
    return nbOccurrences;
  }

  /**
   * Checks whether the vending machine contains at least one item with the
   * provided description
   *
   * @param description description (name) of an item to search
   * @return true if there is a match with description in the vending machine,
   *         false otherwise
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  description is null or
   *                                  blank
   */
  public boolean containsItem(String description) {
    try {
      return getItemOccurrences(description) != 0; // returns true if the number of occurrences of
                                                   // the given description is not 0, false otherwise
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("description cannot be null or blank");
    }
  }

  /**
   * Returns the number of items in the vending machine with a specific
   * description and whose
   * expiration dates are greater or equal to a specific expiration date
   *
   * @param description    description of the item to find its number of
   *                       occurrences
   * @param expirationDate specific (earliest) expiration date
   * @return the number of items with a specific description and whose expiration
   *         date is greater or
   *         equal to the given one
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  expirationDate is negative
   *                                  (less than zero) or description is null or
   *                                  blank
   */
  public int getItemOccurrencesByExpirationDate(String description, int expirationDate) {
    // goes through and ensures everything is valid
    if (expirationDate < 0) { // throws exception if expiration date is negative
      throw new IllegalArgumentException("expiration date cannot be negative");
    }

    if (description == null) { // throws exception if description is null
      throw new IllegalArgumentException("description cannot be null.");
    }

    if (description.isBlank()) { // throws exception if description blank
      throw new IllegalArgumentException("description cannot be blank.");
    }

    int nbOccurrences = 0; // number of occurrences of the matching items
    // traverse the vending machine looking for matching items
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())
          && items[i].getExpirationDate() >= expirationDate) {// match found
        nbOccurrences++;
      }
    }
    // return the number of occurrences of the matching items
    return nbOccurrences;
  }

  /**
   * Returns without removing the index of the item having the provided
   * description and the smallest
   * expiration date within the vending machine.
   *
   * @param description description of an item
   * @return the index of the next item, meaning the item with the given
   *         description and the
   *         smallest expiration date.
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match
   *                                  found
   */
  public int getIndexNextItem(String description) {

    if (description == null || description.isBlank()) { // throws exception if description is null
                                                        // or blank
      throw new IllegalArgumentException("description cannot be null or blank");
    }

    int index = -1; // index of the search item
    int minExpirationDate = -1; // smallest expiration date of matching items

    // traverse the vending machine looking for the matching item with the smallest
    // expiration date
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())) {
        int itemExpirationDate = items[i].getExpirationDate();
        if (index == -1) { // first match found
          minExpirationDate = items[i].getExpirationDate();
          index = i;
        } else { // another match found
          if (itemExpirationDate < minExpirationDate) {
            // match with smaller (sooner) expiration date found
            minExpirationDate = itemExpirationDate; // update minimum expiration date
            index = i; // update the index of the next item
          }
        }
      }
    }

    if (index == -1) { // throws exception if no match found
      throw new NoSuchElementException("no match found");
    }

    return index; // return the index of the item with the given description and the smallest
                  // expiration date if found
  }

  /**
   * Removes and returns the item having the provided description with the
   * smallest expiration date
   * within the vending machine. This method should maintain the order of
   * precedence of items in the
   * vending machine. This means that the remove operation involves a shift
   * operation.
   *
   * @param description description of the item to remove or dispense
   * @return The removed or dispensed item if it is available
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match
   *                                  found
   *
   */
  public Item removeNextItem(String description) {
    // get the index of the next item to dispense by this vending machine
    int index = getIndexNextItem(description); // exceptions throws by this method call should
                                               // propagate

    // save a copy of the item to dispense
    Item itemToDispense = items[index];

    // remove the item at index using a shift operation
    for (int i = index + 1; i < size; i++) {
      items[i - 1] = items[i];
    }
    items[size - 1] = null;
    size--;

    return itemToDispense; // return the removed item
  }

  /**
   * Returns a summary of the contents of this vending machine in the following
   * format: Each line
   * contains the description or item name followed by one the number of
   * occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending
   * machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary
   * description will be as
   * follows. water (5)\n chocolate (10)\n snack (7) If the vending machine is
   * empty, this method
   * returns an empty string ""
   *
   * @return a descriptive summary of the contents of the vending machine
   */
  public String getItemsSummary() {
    String summary = ""; // empty string

    // traverse the vending machine and build its items summary description
    for (int i = 0; i < size; i++) {
      // add the item's description and count if not yet processed
      if (!summary.contains(items[i].getDescription())) {
        summary = summary + items[i].getDescription() + " ("
            + getItemOccurrences(items[i].getDescription()) + ")\n"; // god line if statement D:
      }
    }
    return summary.trim(); // return the items' summary
  }

  /**
   * Parse an item's string representation and add the corresponding item to this
   * vending machine
   *
   * @param itemRepresentation a String representation of an item formatted as
   *                           "description:
   *                           expirationDate". Extra spaces at the beginning and
   *                           end of the item
   *                           description and expirationDate can be disregarded.
   * @throws IllegalArgumentException with a descriptive error message if
   *                                  itemRepresentation is null
   *                                  or blank
   * @throws DataFormatException      with a descriptive error message if the
   *                                  provided string is not
   *                                  correctly formatted. A correct format of the
   *                                  itemRepresentation is "description:
   *                                  expirationDate". The
   *                                  description must be a NOT blank string. The
   *                                  expirationDate
   *                                  must be a non-empty string parsable to a
   *                                  positive integer. The
   *                                  item's description and its expiration date
   *                                  must be separated
   *                                  by one colon ":". Extra whitespace at the
   *                                  beginning and end of
   *                                  description or expirationDate should be
   *                                  disregarded.
   * @throws IllegalStateException    with a descriptive error message if the
   *                                  vending machine is
   *                                  full
   */
  public void loadOneItem(String itemRepresentation) throws DataFormatException {

    // [HINT] Use String.split() and String.trim() methods to help parsing the
    // itemRepresentation
    // This method MUST call addItem(String, int) to try adding the parsed item to
    // the vending
    // machine

    // This is a complex method. Try to decompose it into steps. We highly recommend
    // breaking its
    // functionality down the way that you see fits using private helper methods.

    // begin by ensuring the string passed in is okay to work with
    if (itemRepresentation == null || itemRepresentation.isBlank()) { // throws exception if
                                                                      // itemRepresentation is null
                                                                      // or blank
      throw new IllegalArgumentException("itemRepresentation cannot be null or blank");
    }

    // split the itemRepresentation into description and expirationDate
    String[] itemRepresentationParts = itemRepresentation.split(":");

    if (itemRepresentationParts.length != 2) { // throws exception if itemRepresentation is not
                                               // correctly formatted
      throw new DataFormatException("itemRepresentation is not correctly formatted");
    }

    String description = itemRepresentationParts[0].trim(); // description of the item
    String expirationDate = itemRepresentationParts[1].trim(); // expiration date of the item

    // make sure the format of the string pieces is good
    if (description.isBlank()) { // throws exception if description is blank
      throw new DataFormatException("description cannot be blank");
    }

    int expirationDateInt = 0; // expiration date of the item as an integer

    try {
      expirationDateInt = Integer.parseInt(expirationDate); // parse expirationDate to an integer
    } catch (NumberFormatException e) { // throws exception if expirationDate is not parsable to an
                                        // integer
      throw new DataFormatException("expirationDate is not parsable to an integer");
    }

    if (expirationDateInt <= 0) { // throws exception if expirationDate is not positive
      throw new DataFormatException("expirationDate is not positive");
    }

    // add the item to the vending machine, now that we know everything is good.
    addItem(description, expirationDateInt);
  }

  /**
   * Reads and parses the file passed as input line by line and loads the
   * corresponding items to the
   * vending machine. Each line in the file represents an item description
   * formatted as
   * "description: expirationDate". Blank and badly formatted lines must be
   * skipped.
   *
   * Displays "Vending machine FULL. No more items can be loaded." when trying to
   * load a new item to
   * the vending machine if it is or becomes full.
   *
   * @param file file to load items from
   * @return the total number of new items loaded to this vending machine
   * @throws FileNotFoundException if the file object does not correspond to an
   *                               actual file within
   *                               the file system.
   */
  public int loadItems(File file) throws FileNotFoundException {
    // Create and use a java.util.Scanner object to open and read the file
    // This method MUST call the loadOneItem(String) method to operate while parsing
    // each line

    // Notice carefully that this method does not throw any exception if the vending
    // machine is full
    // or becomes full while trying to lead an item.

    int numberOfItemsLoaded = 0; // number of items loaded to the vending machine

    Scanner fileScanner = null; // scanner to read the file

    // work your way through the file, and load each item into the list if it is valid
    try {
      fileScanner = new Scanner(file); // create a scanner to read the file

      while (fileScanner.hasNextLine()) { // read the file line by line
        String line = fileScanner.nextLine(); // read the next line

        try {
          loadOneItem(line); // load the item to the vending machine
          numberOfItemsLoaded++; // increment the number of items loaded
        } catch (DataFormatException | IllegalArgumentException e) { // skip the line if it is badly formatted
          continue; // skip the line
        } catch (IllegalStateException e) { // display a message if the vending machine is full
          System.out.println("Vending machine FULL with " + this.size() +
              " items. No more items can be loaded.");
          break;
        }
      }
    } catch (FileNotFoundException e) { // throw exception if the file does not exist
      throw new FileNotFoundException("File does not exist");
    } finally {
      if (fileScanner != null) {
        fileScanner.close(); // close the scanner
      }
    }

    return numberOfItemsLoaded; // return the number of items loaded
  }

  /**
   * Saves the summary of this vending machine to the file object passed as input
   *
   * @param file file object where the vending machine summary will be saved
   */
  public void saveVendingMachineSummary(File file) {
    // You can use either a java.io.PrintWriter or a java.io.FileWriter to write
    // into the file

    // This method MUST call the getItemsSummary() to get the summary of this
    // vending machine to be
    // saved to the file

    // This method MUST NOT throw any exception
    PrintWriter fileWriter = null; // writer to write to the file
    try {
      fileWriter = new PrintWriter(file); // create a writer to write to the file
      fileWriter.println(getItemsSummary()); // write the summary to the file
    } catch (IOException e) { // catch exception if the file does not exist
      System.out.println("File does not exist.");
    } finally {
      if (fileWriter != null) {
        fileWriter.close(); // close the writer
      }
    }
  }

}
