//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Dragon Treasure Adventure; a game where you run from a fire-breathing dragon and
// collect treasure. This is the class that manages our dragon.
// Course: COMP SCI 300 Fall 2022
//
// Author: Nico Salm
// Email: nbsalm@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Random; // for making life a bit more random.

/**
 * This class represents a dragon in the game. A dragon has a current room.
 */
public class Dragon {

  private Room currentRoom; // current location of the dragon
  private Random randGen; // random num generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";

  /**
   * Constructor for the dragon.
   * 
   * @param currentRoom the current room of the dragon.
   */
  public Dragon(Room currentRoom) {
    this.currentRoom = currentRoom;
    randGen = new Random();
  }

  /**
   * Returns the current room of the dragon.
   * 
   * @return currentRoom the current room of the dragon.
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Sets the current room of the dragon.
   * 
   * @param currentRoom the current room of the dragon.
   */
  public void changeRooms() {
    try { // try to move the dragon.
      ArrayList<Room> adjRooms = currentRoom.getAdjacentRooms(); // get the adjacent rooms.

      if (adjRooms.size() == 0) { // if there are no adjacent rooms the dragon will not move.
        return;
      }

      int randIndex = randGen.nextInt(adjRooms.size()); // get a random index.
      Room newRoom = adjRooms.get(randIndex); // get the room at that index.
      // if the dragon can go into new room, then move the dragon inside.
      if (newRoom != null && newRoom.getType() != RoomType.PORTAL) {
        currentRoom = newRoom;
      }

    } catch (NullPointerException npe) { // if adjRooms list is null
      System.out.println("The dragon freaking out! Check for nulls in the constructor.");
    }
  }

  /**
   * Causes the player to freak the hell out, and probably run away.
   * 
   * @return DRAGON_WARNING the warning message, which means something dangerous is very very close
   *         and you should probably run away. We know what happens when Smaug has a bad day.
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING; // a constant String
  }
}
