//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ChugiDex
// Course:   COMP SCI 300 Fall 2022
//
// Author:   @Nico
// Email:    nbsalm@wisc.edu
// Lecturer: @Hobbes
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Alex Lu Maye
// Partner Email:   ajlumaye@wisc.edu
// Partner Lecturer's Name: @Mouna
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _✓_ Write-up states that pair programming is allowed for this assignment.
//   _✓_ We have both read and understand the course Pair Programming Policy.
//   _✓_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models the Chugimon data type.
 *
 * @author Nico
 */
public class Chugimon implements Comparable<Chugimon> {
  /** The first ID number */
  public final int FIRST_ID;

  /** The height of the Chugimon in meters */
  public final double HEIGHT;

  /** The minimum ID number */
  public static final int MIN_ID = ChugidexUtility.MIN_CHUGI_ID;

  /** The maximum ID number */
  public static final int MAX_ID = ChugidexUtility.MAX_CHUGI_ID;

  /** The name of this Chugimon */
  public final String NAME;

  /** The primary type of the Chugimon; cannot be null; cannot be the same as the secondary type */
  public final ChugiType PRIMARY_TYPE;

  /** The second ID of the Chugimon */
  public final int SECOND_ID;

  /** The secondary type of the Chugimon; may be null; cannot be the same as the primary type */
  public final ChugiType SECONDARY_TYPE;

  /** The weight of the Chugimon in kilograms */
  public final double WEIGHT;

  /**
   * Creates a new Chugimon with specific first and second IDs and initializes all its data fields
   * accordingly.
   *
   * @param firstID  the first ID of the Chugimon, between 1 and 151 inclusive
   * @param secondID the second ID of the Chugimon, between 1 and 151
   * @throws IllegalArgumentException if the first and second ID are out of bounds or equal to each
   *                                  other.
   */
  public Chugimon(int firstID, int secondID) {

    // initializes the first and second ID fields
    FIRST_ID = firstID;
    SECOND_ID = secondID;

    // grabs the types of the Chugimon from the ChugidexUtility class
    ChugiType[] types = ChugidexUtility.getChugimonTypes(firstID, secondID);
    PRIMARY_TYPE = types[0];
    SECONDARY_TYPE = types[1];

    // grabs the attributes of the Chugimon from the ChugidexUtility class
    NAME = ChugidexUtility.getChugimonName(firstID, secondID);
    HEIGHT = ChugidexUtility.getChugimonHeight(firstID, secondID);
    WEIGHT = ChugidexUtility.getChugimonWeight(firstID, secondID);

    // checks if the first and second ID are out of bounds
    if (firstID < MIN_ID || firstID > MAX_ID || secondID < MIN_ID || secondID > MAX_ID) {
      throw new IllegalArgumentException("ERROR: Invalid ID");
    }

    // checks if the first and second ID are equal
    if (firstID == secondID) {
      throw new IllegalArgumentException("ERROR: Invalid ID");
    }

    // checks if the primary and secondary types are the same
    if (PRIMARY_TYPE == SECONDARY_TYPE) {
      throw new IllegalArgumentException("ERROR: Invalid type");
    }

    // if the primary or secondary type is
  }

  /**
   * Gets the name of this Chugimon
   *
   * @return the name of the Chugimon
   */
  public String getName() {
    return NAME;
  }

  /**
   * Gets the first ID of this Chugimon
   *
   * @return the first ID of the Chugimon
   */
  public int getFirstID() {
    return FIRST_ID;
  }

  /**
   * Gets the second ID of thid Chugimon
   *
   * @return the second ID of the Chugimon
   */
  public int getSecondID() {
    return SECOND_ID;
  }

  /**
   * Gets the primary type of this Chugimon
   *
   * @return the primary type of the Chugimon
   */
  public ChugiType getPrimaryType() {
    return PRIMARY_TYPE;
  }

  /**
   * Gets the secondary type of this Chugimon
   *
   * @return the secondary type of the Chugimon
   */
  public ChugiType getSecondaryType() {
    return SECONDARY_TYPE;
  }

  /**
   * Gets the height of this Chugimon
   *
   * @return the height of the Chugimon
   */
  public double getHeight() {
    return HEIGHT;
  }

  /**
   * Gets the the weight of the Chugimon.
   *
   * @return the weight of the Chugimon.
   */
  public double getWeight() {
    return WEIGHT;
  }

  /**
   * Determines the ordering of Chugimon. Chugimon are ordered by: 1) name (alphabetical) 2) the
   * first ID (if name is equal). The one with the smaller first ID is less than the other. 3) the
   * second ID (if name and first ID are equal). The one with the smaller second ID is less than the
   * other. A Chugimon with identical #1-3 are considered equal.
   *
   * @param otherChugi the other Chugimon to compare this Chugimon to.
   * @return a negative int if this Chugimon is less than other, a positive int if this Chugimon is
   *         greater than other, or 0 if this and the other Chugimon are equal.
   */
  @Override
  public int compareTo(Chugimon otherChugi) {
    // if the names are not equal, compare the names and return the result
    if (this.NAME.compareTo(otherChugi.NAME) != 0) {
      return this.NAME.compareTo(otherChugi.NAME);

      // if the names are equal, compare the first IDs and return the result
    } else if (this.FIRST_ID != otherChugi.FIRST_ID) {
      return this.FIRST_ID - otherChugi.FIRST_ID;

      // if the names and first IDs are equal, compare the second IDs and return the result
    } else {
      return this.SECOND_ID - otherChugi.SECOND_ID;
    }

  }

  /**
   * A Chugimon's String representation is its name followed by "#FIRST_ID.SECOND_ID" -- Example:
   * "Zapchu#145.25"
   *
   * @return a String representation of this Chugimon
   */
  @Override
  public String toString() {
    return NAME + "#" + FIRST_ID + "." + SECOND_ID;
  }

  /**
   * Equals method for Chugimon. This Chugimon equals another object if other is a Chugimon with the
   * exact same name, and their both first and second IDs match.
   *
   * @param other Object to determine equality against this Chugimon
   * @return true if this Chugimon and other Object are equal, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Chugimon))
      return false;

    Chugimon otherChugi = (Chugimon) other;
    return NAME.equals(otherChugi.NAME) && FIRST_ID == otherChugi.FIRST_ID
        && SECOND_ID == otherChugi.SECOND_ID;

  }
}
