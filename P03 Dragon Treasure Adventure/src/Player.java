//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Dragon Treasure Adventure; a game where you run from a fire-breathing dragon and 
//       collect treasure. This is the class for our player.
// Course:   COMP SCI 300 Fall 2022
//
// Author:   Nico Salm
// Email:    nbsalm@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class represents a player in the game. A player has current room.
 */
public class Player {
  private Room currentRoom; //current location of the player

  /**
   * Constructor for the player.
   * @param currentRoom
   */
  public Player (Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  /**
   * Returns the current room of the player.
   * @return currentRoom the current room of the player.
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Sets the current room of the player.
   * @param currentRoom the current room of the player.
   */
  public void changeRooms(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  /**
   * Checks if the player can move to a room.
   * @param destination the room to move to.
   * @return true if the player can move to the room, false otherwise.
   */
  public boolean canMoveTo(Room destination) {
    // iterate through the list of adjacent rooms.
    ArrayList<Room> adjacentRooms = currentRoom.getAdjacentRooms();
    for (Room room : adjacentRooms) {
      if (room.equals(destination) && !currentRoom.equals(destination)) {
        // If the destination room is in the list, return true.
        return true;
      }
    }
    return false; // return false otherwise.
  }

  /**
   * Returns whether or not the player needs to teleport, because they are in a portal room.
   * @return true if the player is in a portal room, false otherwise.
   */
  public boolean shouldTeleport() {
    // checks to see if the the player's room ID matches the ID of the portal room. If so, return true.
    return Room.getTeleportationRoom() == currentRoom.getID();
  }

  /**
   * Returns the list of adjacent rooms to the current room.
   * @return adjRooms the list of adjacent rooms to the current room.
   */
  public ArrayList<Room> getAdjacentRoomsToPlayer() {
    return currentRoom.getAdjacentRooms();
  }
 
  /**
   * Returns whether or not the dragon is in the player's adjacent rooms.
   * @param d the dragon.
   * @return true if the dragon is in the player's adjacent rooms, false otherwise.
   */
  public boolean isDragonNearby(Dragon d) {
    // for every room in the player's adjacent rooms, check to see if the dragon is in that room.
    for (int i = 0; i < currentRoom.getAdjacentRooms().size(); i++) {
      if (currentRoom.getAdjacentRooms().get(i).getID() == d.getCurrentRoom().getID()) {
        return true; // if the dragon is in the player's adjacent rooms, return true.
      }
    }
    return false; // return false otherwise.
  }

  /**
   * Returns whether or not a portal is in the player's adjacent rooms.
   * @return true if a portal is in the player's adjacent rooms, false otherwise.
   */
  public boolean isPortalNearby() {
    // for every room in the player's adjacent rooms, check to see if the room is a portal.
    for (int i = 0; i < currentRoom.getAdjacentRooms().size(); i++) {
      if (currentRoom.getAdjacentRooms().get(i).getType() == RoomType.PORTAL) {
        return true; // if the room is a portal, return true.
      }
    }
    return false; // return false otherwise.
  }

  /**
   * Returns whether or not treasure is nearby! (in the player's adjacent rooms)
   * @return true if treasure is nearby, false otherwise.
   */
  public boolean isTreasureNearby() {
    // for every room in the player's adjacent rooms, check to see if the room is a treasure room.
    for (int i = 0; i < currentRoom.getAdjacentRooms().size(); i++) {
      if (currentRoom.getAdjacentRooms().get(i).getType() == RoomType.TREASURE) {
        return true; // return true if the room is a treasure room.
      }
    }
    return false; // return false otherwise.
  }
}
