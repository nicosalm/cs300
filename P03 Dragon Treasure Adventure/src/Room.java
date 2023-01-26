//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Dragon Treasure Adventure; a game where you run from a fire-breathing dragon and 
//       collect treasure. This is the class that builds out the rooms for the game.
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

import java.util.ArrayList;

/**
 * This class represents a room in the game.
 * 
 * @author Nico Salm
 * @version Sep 2022
 */
public class Room {

  private RoomType type; // one of the four types a room could be
  private String roomDescription; // a brief description of the room
  private ArrayList<Room> adjRooms; // arraylist that holds the rooms adjacent
  private final int ID; // unique ID for each room to identify it
  private static int teleportLocationID; // place where all portal rooms will go to
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";

  /**
   * Constructor for a room.
   * 
   * @param id              the unique ID for the room
   * @param roomDescription the description of the room
   */
  public Room(int id, String roomDescription) {
    this.ID = id; // assigns an ID for a room
    this.roomDescription = roomDescription; // assigns a description for a room
    // initializes remaining values as their defaults.
    this.type = RoomType.NORMAL;
    this.adjRooms = new ArrayList<Room>();
  }

  // * Mutator methods


  /**
   * Adds a room to the list of adjacent rooms.
   * @param toAdd
   */
  public void addToAdjacentRooms(Room toAdd) {
    adjRooms.add(toAdd); // adds to the end of the ArrayList
  }

  /**
   * Sets the teleportation room ID.
   * @param teleportLocationID the ID of the room that all portals will teleport to.
   */
  public static void assignTeleportLocation(int teleportID) {
    teleportLocationID = teleportID;
  }

  /**
   * Sets the room type.
   * @param newType the type of room.
   */
  public void setRoomType(RoomType newType) {
    type = newType;
  }

  // * Accessor methods

  /**
   * Gets adjacent rooms.
   * @return adjRooms the ArrayList of adjacent rooms to a given room.
   */
  public ArrayList<Room> getAdjacentRooms() {
    return adjRooms;
  }

  /**
   * Gets the ID of a given room instance.
   * @return ID of the room.
   */
  public int getID() {
    return ID;
  }

  /**
   * Gets the description of a room. 
   * @return roomDescription the description of the room.
   */
  public String getRoomDescription() {
    return roomDescription;
  }

  /**
   * Gets the type of a room.
   * @return type the type of room.
   */
  public RoomType getType() {
    return type;
  }

  /**
   * Checks whether the given room is adjacent to this one.
   * @param r the room to check adjacency with.
   * @return true if the room is adjacent, false otherwise.
   */
  public boolean isAdjacent(Room r) {
    return adjRooms.contains(r);
  }

  /**
   * Getter for treasure warning String.
   * @return the string that is the room class's treasure warning, indicating there is shiny stuff
   *        nearby. We like gold here in CS 300.
   */
  public static String getTreasureWarning() {
    return TREASURE_WARNING;
  }

  /**
   * Getter for the portal warning. Returns the string that is the room class's portal warning,
   * indicating that magical fairy stuff is nearby.
   */
  public static String getPortalWarning() {
    return PORTAL_WARNING;
  }

  /**
   * Getter for the teleport room location ID.
   * @return the ID where all the portals will teleport to.
   */
  public static int getTeleportationRoom() {
    return teleportLocationID;
  }

  // * Override methods (@author Michelle)

  /**
   * Determines if the given object is equal to this room. They are equal if other is a Room and
   * their IDs are the same.
   * 
   * @param other, another object to check if it is equal to this
   * @return true if the two rooms are equal, false otherwise
   * @author Michelle
   */
  @Override
  public boolean equals(Room other) {
    if (other instanceof Room) {
      Room otherRoom = (Room) other;
      return this.ID == otherRoom.ID;
    }
    return false;
  }

  /**
   * Returns a String representation of this room.
   * 
   * @return the string representation of this room and it’s object data field values
   * @author Michelle
   */
  @Override
  public String toString() {
    String s = this.ID + ": " + this.roomDescription + " (" + type + ")\n Adjacent Rooms: ";
    for (int i = 0; i < adjRooms.size(); i++) {
      s += adjRooms.get(i).ID + " ";
    }
    return s;
  }
}
