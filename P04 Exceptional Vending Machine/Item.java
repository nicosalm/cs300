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

/**
 * Item class represents an item in a vending machine
 */
public class Item {

  private String description; // human-readable description of item
  private int expirationDate; // expiration date of item

  /**
   * Creates a new item with the given description and expiration date.
   * @param description the description of the item
   * @param expirationDate the expiration date of the item
   * @throws IllegalArgumentException if description is null or empty, or if expirationDate is < 0
   */
  public Item(String description, int expirationDate) throws IllegalArgumentException {
    if (description == null || description.isBlank()) { // description is null or empty
      throw new IllegalArgumentException("description cannot be null or empty");
    }
    if (expirationDate < 0) { // expiration date is negative
      throw new IllegalArgumentException("expiration date cannot be negative");
    }
    // if inputs are valid, constructs object
    this.description = description;
    this.expirationDate = expirationDate;
  }

  /**
   * Gets the description of the item
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the expiration date of the item
   * @return the expiration date
   */
  public int getExpirationDate() {
    return expirationDate;
  }

  /**
   * Sets the description of the item
   * @param description the new description of the item.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * toString method override for Item class
   * @return a string representation of the item
   */
  @Override
  public String toString() {
    return description + ": " + expirationDate;
  }

  /**
   * equals method override for Item class
   * @param o the object to compare to this item
   * @return true if the given object is an Item with the same description
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) { // obj is null
      return false;
    }
    if (!(obj instanceof Item)) { // obj is not an instance of Item
      return false;
    }
    Item other = (Item) obj; // casts obj to Item
    return description.equals(other.description); // returns true if descriptions are equal
  }

}
