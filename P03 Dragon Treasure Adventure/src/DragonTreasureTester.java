//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Dragon Treasure Adventure; a game where you run from a fire-breathing dragon and
// collect treasure. This is the tester class for the program's classes.
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

/**
 * This class contains tester methods for Dragon, Player, and Room Class.
 */
public class DragonTreasureTester {

  /**
   * Method for testing instance methods.
   * 
   * @return true if all tests pass, false otherwise.
   */
  public static boolean testRoomInstanceMethods() {
    // checkmark - "\u2714", xmark - "\u2718", arrow - "\u2192"

    // * 1. Checks the correctness of instance getter/setter methods.
    // 1.1 Checks correctness of getType().
    {
      Room room = new Room(1, "This is a room.");
      if (room.getType() != RoomType.NORMAL) { // ensures the room is the default type (NORMAL)
        System.out.println(
            "\u2718 Problem in testRoomInstanceMethods-1.1 \u2192 " + "Room.getType() failed.");
        return false;
      }
    }
    // 1.2 Checks correctness of setRoomType().
    {
      // declares a room with type NORMAL.
      Room room = new Room(1, "This is a room.");
      room.setRoomType(RoomType.PORTAL); // sets the room type to PORTAL.
      if (room.getType() != RoomType.PORTAL) { // checks if the room type is PORTAL.
        System.out.println(
            "\u2718 Problem in testRoomInstanceMethods-1.2 \u2192 " + "Room.setRoomType() failed.");
        return false;
      }
    }
    // 1.3 Checks correctness of addToAdjacentRooms()
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      try { // check if the room is null when adding it to the list of adjacent rooms.
        room.addToAdjacentRooms(room2);
      } catch (NullPointerException nullPointerException) { // if the room is null, print an error.
        System.out.println("\u2718 Problem in testRoomInstanceMethods-1.3 \u2192 "
            + "Room.addToAdjacentRooms() " + "failed because the room is null.");
        return false;
      }
      // checks correctness of output by comparing the size of adjRooms actual vs. indended.
      if (room.getAdjacentRooms().size() != 1) {
        System.out.println("\u2718 Problem in testRoomInstanceMethods-1.3 \u2192 "
            + "Room.addToAdjacentRooms() failed.");
        return false;
      }
    }
    // 1.4 Checks correctness of isAdjacent()
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      Room room3 = new Room(2, "This is a third test room.");
      room.addToAdjacentRooms(room2); // adds them to be adjacent to room
      room2.addToAdjacentRooms(room3);

      // checks correctness of output by checking if room2 is adjacent to room.
      if (room.isAdjacent(room2) && !room.isAdjacent(room3)) {
        System.out.println(
            "\u2718 Problem in testRoomInstanceMethods-1.4 \u2192 Room.isAdjacent() failed.");
        return false;
      }
    }
    // 1.5 Checks correctness of getID()
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      if (room.getID() != 1) { // checks if the room ID is indeed 1.
        System.out
            .println("\u2718 Problem in testRoomInstanceMethods-1.5 \u2192 Room.getID() failed.");
        return false;
      }
    }
    // 1.6 Checks correctness of getRoomDescription()
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      // checks if the room description is indeed "This is a test room."
      if (!room.getRoomDescription().equals("This is a test room.")) {
        System.out.println("\u2718 Problem in testRoomInstanceMethods-1.6 \u2192 "
            + "Room.getRoomDescription() failed.");
        return false;
      }
    }

    // * 2. Checks the correctness of the sole constructor.
    // 2.1 Checks the correctness of the constructor
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      // checks if all values are properly set to their intended values.
      if (room.getID() != 1 || !room.getRoomDescription().equals("This is a test room.")
          || room.getType() != RoomType.NORMAL || room.getAdjacentRooms().size() != 0) {
        System.out.println(
            "\u2718 Problem in testRoomInstanceMethods-2.1 \u2192 " + "Room constructor failed.");
        return false;
      }
    }
    return true; // no bugs found over 1 constructor & 7 instance methods
  }

  /**
   * Method for testing static methods yay.
   * 
   * @return true if no bugs found, false otherwise.
   */
  public static boolean testRoomStaticMethods() {

    // * 1. Checks the correctness of static methods.
    {
      // 1.1 Checks correctness of getTeleportLocation()
      if (Room.getTeleportationRoom() != 0) { // checks if the teleportation room is 0 by default.
        System.out.println("\u2718 Problem in testRoomStaticMethods-1.1 \u2192 "
            + "Room.getTeleportationRoom() failed.");
        return false;
      }

      // 1.2 Checks correctness of assignTeleportLocation()
      Room.assignTeleportLocation(2); // assigns the teleportation room to 2.
      if (Room.getTeleportationRoom() != 2) { // checks if the teleportation room is 2.
        System.out.println("\u2718 Problem in testRoomStaticMethods-1.2 \u2192 "
            + "Room.assignTeleportLocation() failed.");
        return false;
      }
    }
    // 1.3 Checks correctness of getTreasureWarning()
    if (!Room.getTreasureWarning().equals("You sense that there is treasure nearby.\n")) {
      System.out.println("\u2718 Problem in testRoomStaticMethods-1.3 \u2192 "
          // checks if the treasure warning is indeed "You sense that there is treasure nearby.\n"
          + "Room.getTreasureWarning() failed.");
      return false;
    }

    // 1.4 Checks correctness of getPortalWarning()
    if (!Room.getPortalWarning().equals("You feel a distortion in space nearby.\n")) {
      System.out.println("\u2718 Problem in testRoomStaticMethods-1.4 \u2192 "
          + "Room.getPortalWarning() failed.");
      // checks if the portal warning is indeed "You feel a distortion in space nearby.\n"
      return false;
    }
    return true; // no bugs found over 4 static methods
  }

  /**
   * This method checks for the correctness of the canMoveTo() method.
   * 
   * @return true if no bugs found, false otherwise.
   */
  public static boolean testPlayerCanMoveTo() {
    // 1.1 Checks correctness of canMoveTo() when the player is in a room with no adjacent rooms.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Player player = new Player(room); // creates a player in room
      if (player.canMoveTo(room)) { // ensures the player CANNOT move to room.
        System.out.println("\u2718 Problem in testPlayerCanMoveTo-1.1 \u2192 "
            + "Player.canMoveTo() failed when the player is in a room with no adjacent rooms.");
        return false;
      }
    }
    // 1.2 Checks correctness of canMoveTo() when the player is in a room with adjacent rooms.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      room.addToAdjacentRooms(room2); // adds them to be adjacent to room
      Player player = new Player(room); // creates a player in room
      if (!player.canMoveTo(room2)) { // ensures the player CAN move to room2.
        System.out.println("\u2718 Problem in testPlayerCanMoveTo-1.2 \u2192 "
            + "Player.canMoveTo() failed when the player is in a room with adjacent rooms.");
        return false;
      }
    }
    return true; // no bugs found over 2 tests
  }

  /**
   * This method checks for the correctness of the shouldTeleport() method.
   * 
   * @return true if no bugs found, false otherwise.
   */
  public static boolean testPlayerShouldTeleport() {
    // 1.1 Checks correctness of shouldTeleport() when the player enters a room that is not
    // the teleporter room.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Player player = new Player(room); // creates a player in room
      if (player.shouldTeleport()) { // ensures the player should NOT teleport.
        System.out.println("\u2718 Problem in testPlayerShouldTeleport-1.1 \u2192 "
            + "Player.shouldTeleport() failed when the player enters a room that is not the "
            + "teleporter room.");
        return false;
      }
    }
    // 1.2 Checks correctness of shouldTeleport() when the player enters the teleporter room.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Player player = new Player(room); // creates a player in room
      Room.assignTeleportLocation(1); // assigns the teleportation room to 1.
      if (!player.shouldTeleport()) { // ensures the player CAN teleport.
        System.out.println("\u2718 Problem in testPlayerShouldTeleport-1.2 \u2192 "
            + "Player.shouldTeleport() failed when the player enters the teleporter room.");
        return false;
      }
    }
    return true; // no bugs found over 2 tests
  }

  /**
   * This method checks for the correctness of both the isPortalNearby() and isTreasureNearby()
   * methods.
   * 
   * @return true if no bugs found, false otherwise.
   */
  public static boolean testPlayerDetectNearbyRooms() {
    // 1.1 Checks correctness of isPortalNearby() when the player is in a room with no adjacent
    // rooms.
    {
      // declares and intializes room to be used in testing.
      Room room = new Room(1, "This is a test room.");
      Player player = new Player(room); // creates a player in room
      if (player.isPortalNearby()) { // ensures the player CANNOT detect a portal nearby.
        System.out.println("\u2718 Problem in testPlayerDetectNearbyRooms-1.1 \u2192 "
            + "Player.isPortalNearby() failed when the player is in a room with no adjacent rooms.");
        return false;
      }
    }
    // 1.2 Checks correctness of isPortalNearby() when the player is in a room with adjacent rooms
    // that do not contain a portal.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      room.addToAdjacentRooms(room2); // adds them to be adjacent to room
      Player player = new Player(room); // creates a player in room
      if (player.isPortalNearby()) { // ensures the player CANNOT detect a portal nearby.
        System.out.println("\u2718 Problem in testPlayerDetectNearbyRooms-1.2 \u2192 "
            + "Player.isPortalNearby() failed when the player is in a room with adjacent rooms "
            + "that do not contain a portal.");
        return false;
      }
    }
    // 1.3 Checks correctness of isPortalNearby() when the player is in a room with adjacent rooms
    // that contain a portal.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      room.addToAdjacentRooms(room2); // adds them to be adjacent to room
      room2.setRoomType(RoomType.PORTAL); // sets room2 to be a portal room
      Player player = new Player(room); // creates a player in room
      if (!player.isPortalNearby()) { // ensures the player CAN detect a portal nearby.
        System.out.println("\u2718 Problem in testPlayerDetectNearbyRooms-1.6 \u2192 "
            + "Player.isTreasureNearby() failed when the player is in a room with adjacent rooms "
            + "that contain a treasure.");
        return false;
      }
    }
    // 1.4 Checks correctness of isTreasureNearby() when the player is in a room with no adjacent
    // rooms.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Player player = new Player(room); // creates a player in room
      if (player.isTreasureNearby()) { // ensures the player CANNOT detect a treasure nearby.
        System.out.println("\u2718 Problem in testPlayerDetectNearbyRooms-1.4 \u2192 "
            + "Player.isTreasureNearby() failed when the player is in a room with no adjacent "
            + "rooms.");
        return false;
      }
    }
    // 1.5 Checks correctness of isTreasureNearby() when the player is in a room with adjacent
    // rooms that do not contain a treasure.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      room.addToAdjacentRooms(room2); // adds them to be adjacent to room
      Player player = new Player(room); // creates a player in room
      if (player.isTreasureNearby()) { // ensures the player CANNOT detect a treasure nearby.
        System.out.println("\u2718 Problem in testPlayerDetectNearbyRooms-1.5 \u2192 "
            + "Player.isTreasureNearby() failed when the player is in a room with adjacent rooms "
            + "that do not contain a treasure.");
        return false;
      }
    }
    // 1.6 Checks correctness of isTreasureNearby() when the player is in a room with adjacent
    // rooms that contain a treasure.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      room.addToAdjacentRooms(room2); // adds them to be adjacent to room
      room2.setRoomType(RoomType.TREASURE); // sets room2 to be a treasure room
      Player player = new Player(room); // creates a player in room
      if (!player.isTreasureNearby()) { // ensures the player CAN detect a treasure nearby.
        System.out.println("\u2718 Problem in testPlayerDetectNearbyRooms-1.6 \u2192 "
            + "Player.isTreasureNearby() failed when the player is in a room with adjacent rooms "
            + "that contain a treasure.");
        return false;
      }
    }
    return true; // no bugs found over 6 tests
  }

  public static boolean testDragonChangeRooms() {
    // 1.1 Checks the correctness of Dragon.changeRooms() when there is not an adjacent room.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Dragon dragon = new Dragon(room); // creates a dragon in room
      dragon.changeRooms(); // changes the dragon's room, when adjRooms is empty.
      if (dragon.getCurrentRoom() != room) { // ensures the dragon did not change rooms.
        System.out.println("\u2718 Problem in testDragonChangeRooms-1.1 \u2192 "
            + "Dragon.changeRooms() tried to change the room of the dragon with no adj rooms. ");
      }
    }
    // 1.2 Checks the correctness of the Dragon.changeRooms() when adjRooms >= 1.
    {
      // declares and intializes rooms to be used in testing
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      Room room3 = new Room(3, "This is a third test room.");
      room.addToAdjacentRooms(room2); // adds them to be adjacent to room
      room.addToAdjacentRooms(room3);
      room2.setRoomType(RoomType.PORTAL); // sets room2 to be a portal room
      Dragon dragon = new Dragon(room); // creates Smaug the Terrible, basically.

      dragon.changeRooms(); // changes the room of the fire-breathing dragon.

      for (int i = 0; i < 20; i++) { // ensures the dragon never enters the portal room.
        if (dragon.getCurrentRoom() == room2) {
          System.out.println("\u2718 Problem in testDragonChangeRooms-1.2 \u2192 "
              + "Dragon.changeRooms() failed to change the room of the dragon.");
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Tests the correctness of the Player.isDragonNearby() method.
   * @return true if no bugs are found, false otherwise.
   */
  public static boolean testIsDragonNearby() {
    // 1.7 The dragon is not nearby.
    {
      // declares rooms and player and dragon.
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      Room room3 = new Room(3, "This is a third test room.");

      room.addToAdjacentRooms(room2);
      room2.addToAdjacentRooms(room);
      room2.addToAdjacentRooms(room3);
      room3.addToAdjacentRooms(room2);

      Player p = new Player(room);

      Dragon d = new Dragon(room3);

      if (p.isDragonNearby(d) != false) { // ensures the dragon is not nearby.
        System.out.println("scenario 1.");
        return false;
      }
    }
    // 1.8 The dragon IS nearby (AKA in an adjacent room.)
    {
      // declares rooms and player and dragon.
      Room room = new Room(1, "This is a test room.");
      Room room2 = new Room(2, "This is a second test room.");
      Room room3 = new Room(3, "This is a third test room.");

      room.addToAdjacentRooms(room2);
      room2.addToAdjacentRooms(room);
      room2.addToAdjacentRooms(room3);
      room3.addToAdjacentRooms(room2);

      Player p = new Player(room);

      Dragon d = new Dragon(room2);

      if (p.isDragonNearby(d) != true) { // ensures the dragon is nearby.
        System.out.println("Scenario 2.");
        return false;
      }
    }
    return true; // no bugs found over 2 tests.
  }

  /**
   * Method to run all tests.
   * 
   * @param args - command line arguments
   */
  public static void main(String[] args) {

    // unit test assessment
    if (testRoomInstanceMethods() && testRoomStaticMethods() && testPlayerCanMoveTo()
        && testPlayerShouldTeleport() && testPlayerDetectNearbyRooms() && testDragonChangeRooms()
        && testIsDragonNearby()) {
      System.out.println("\u2714 All tests passed!");
    } else {
      System.out.println("\u2718 Some tests failed.");
    }
  }
}
