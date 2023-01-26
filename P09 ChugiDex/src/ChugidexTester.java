//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ChugiDex
// Course: COMP SCI 300 Fall 2022
//
// Author: @Nico
// Email: nbsalm@wisc.edu
// Lecturer: @Hobbes
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Alex Lu Maye
// Partner Email: ajlumaye@wisc.edu
// Partner Lecturer's Name: @Mouna
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _✓_ Write-up states that pair programming is allowed for this assignment.
// _✓_ We have both read and understand the course Pair Programming Policy.
// _✓_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the Chugimon
 * and ChugiTree classes.
 * 
 * @author Alex, Nico
 *
 */
public class ChugidexTester {


  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Chugimon class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonCompareToEquals() {
    try {
      Chugimon bulbasaur = new Chugimon(1, 2);
      Chugimon bulbasaur2 = new Chugimon(1, 2);
      Chugimon ivysaur = new Chugimon(1, 4);

      if (bulbasaur.compareTo(bulbasaur2) != 0) {
        System.out.println("- Failed compareTo with equal pokemon");
        return false;
      }

      if (bulbasaur.compareTo(ivysaur) < 0) {
        System.out.println("- Failed compareTo lower value pokemon with greater value");
        return false;
      }

      if (ivysaur.compareTo(bulbasaur) > 0) {
        System.out.println("- Failed compareTo greater value pokemon with higher value");
        return false;
      }

      if (!bulbasaur.equals(bulbasaur2)) {
        System.out.println("- Failed equals when comparing identical pokemon");
        return false;
      }

      if (bulbasaur.equals(ivysaur)) {
        System.out.println("- Failed equals when comparing different pokemon");
        return false;
      }

      if (bulbasaur.equals(new Object())) {
        System.out.println("- Failed equals when comparing pokemon to Object");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the implementation of Chugimon.toString() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonToString() {
    try {
      Chugimon bulbasaur = new Chugimon(1, 2);
      Chugimon venusaur = new Chugimon(1, 3);

      if (!bulbasaur.toString().startsWith("Bulbysaur")) {
        System.out.println("- Failed toString for name of pokemon");
        return false;
      }

      if (!bulbasaur.toString().endsWith("#1.2")) {
        System.out.println("- Failed toString for id of pokemon");
        return false;
      }

      if (!venusaur.toString().equals("Bulbusaur#1.3")) {
        System.out.println("- Failed toString for name and id of pokemon");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the implementation of ChugiTree.isValidBSTHelper() method. This
   * tester should consider at least three scenarios. (1) An empty tree whose root is null should be
   * a valid BST. (2) Consider a valid BST whose height is at least 3. Create the tree using the
   * constructors of the BSTNode and its setters methods, (3) Consider a NON-valid BST where the
   * search order property is violated at at least one internal node.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIsValidBSTHelper() {
    try {
      if (!ChugiTree.isValidBSTHelper(null)) {
        System.out.println("- Tree with null root should be a valid tree.");
        return false;
      }

      BSTNode<Chugimon> node6 = new BSTNode<Chugimon>(new Chugimon(3, 4)); // Venumander (6) Rt Rt
      BSTNode<Chugimon> node5 = new BSTNode<Chugimon>(new Chugimon(2, 3)); // Ivyusaur (5) Rt
      BSTNode<Chugimon> node = new BSTNode<Chugimon>(new Chugimon(1, 2)); // Bulbysaur (1) Lft Lft
      BSTNode<Chugimon> node3 = new BSTNode<Chugimon>(new Chugimon(4, 5)); // Charmeleon (3) Root
      BSTNode<Chugimon> node2 = new BSTNode<Chugimon>(new Chugimon(5, 6)); // Charizard (2) Lft
      BSTNode<Chugimon> node4 = new BSTNode<Chugimon>(new Chugimon(6, 7)); // Chartle (4) Rt Lft

      node3.setLeft(node2);
      node2.setLeft(node);
      node3.setRight(node5);
      node5.setLeft(node4);
      node5.setRight(node6);

      if (!ChugiTree.isValidBSTHelper(node3)) {
        System.out.println("- Valid tree labeled as invalid tree.");
        return false;
      }

      node5.setLeft(node);

      if (ChugiTree.isValidBSTHelper(node3)) {
        System.out.println("- Invalid tree labeled as valid tree.");
        return false;
      }

      node5.setLeft(node6);
      node5.setRight(node4);

      if (ChugiTree.isValidBSTHelper(node3)) {
        System.out.println("- Invalid tree labeled as valid tree.");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the implementation of both add() and toString() methods implemented
   * in the ChugiTree class. This unit test considers at least the following scenarios. (1) Create a
   * new empty ChugiTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one Chugimon and then check that the add()
   * method call returns true, the tree is not empty, its size is 1, and the toString() called on
   * the tree returns the expected output. (3) Try adding another Chugimon which is less than the
   * Chugimon at the root, (4) Try adding a third Chugimon which is greater than the one at the
   * root, (5) Try adding at least two further Chugimons such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, that the ChugiTree is a valid BST, and that the toString() method returns the expected
   * string representation of the contents of the binary search tree in an increasing order from the
   * smallest to the greatest Chugimon. (6) Try adding a Chugimon already stored in the tree. Make
   * sure that the add() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddToStringSize() {
    try {
      ChugiTree tree = new ChugiTree();
      if (!tree.isEmpty()) {
        System.out.println("- Tree with null root should be empty");
        return false;
      }

      if (!tree.toString().equals("")) {
        System.out.println("- Tree with null root should have a toString as \"\"");
        return false;
      }

      if (!tree.add(new Chugimon(1, 50))) {
        System.out.println("- Failed to add pokemon to empty tree.");
        return false;
      }

      if (tree.isEmpty()) {
        System.out.println("- Tree labeled as empty after adding a pokemon");
        return false;
      }

      if (tree.size() != 1) {
        System.out.println("- Tree does not have size of 1 after adding a pokemon");
        return false;
      }

      if (!tree.toString().equals("Bulblett#1.50")) {
        System.out.println("- Tree has incorrect toString with 1 pokemon");
        return false;
      }

      if (!tree.add(new Chugimon(1, 25)) || !tree.add(new Chugimon(1, 75))) {
        System.out.println("- Failed to add pokemon on left or right of root node");
        return false;
      }

      if (tree.size() != 3) {
        System.out.println("- Size is not 3 as it is supposed to be.");
        return false;
      }

      if (!tree.toString().equals("Bulbchu#1.25,Bulbeler#1.75,Bulblett#1.50")) {
        System.out.println("- Incorrect toString with 3 pokemon, add may be incorrect");
        return false;
      }

      if (!tree.add(new Chugimon(1, 26)) || !tree.add(new Chugimon(1, 51))) {
        System.out.println("- Failed to add pokemon #1.26 and #1.51 to tree");
        return false;
      }

      if (tree.size() != 5) {
        System.out.println("- Size is not 5 as it is supposed to be.");
        return false;
      }

      if (!tree.toString()
          .equals("Bulbchu#1.25,Bulbchu#1.26,Bulbeler#1.75,Bulblett#1.50,Bulbtrio#1.51")) {
        System.out.println("- Incorrect toString with 5 pokemon, add() may be incorrect");
        return false;
      }

      if (tree.add(new Chugimon(1, 25))) {
        System.out.println("- Successfully added pokemon that already exists in tree.");
        return false;
      }

      if (tree.size() != 5) {
        System.out.println("- Size changed after adding a pokemon that already exists in tree");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * This method checks mainly for the correctness of the ChugiTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ChugiTree. Then, check that
   * calling the lookup() method on an empty ChugiTree returns false. (2) Consider a ChugiTree of
   * height 3 which contains at least 5 Chugimons. Then, try to call lookup() method to search for a
   * Chugimon having a match at the root of the tree. (3) Then, search for a Chugimon at the right
   * and left subtrees at different levels considering successful and unsuccessful search
   * operations. Make sure that the lookup() method returns the expected output for every method
   * call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    try {
      ChugiTree tree = new ChugiTree();
      if (tree.lookup(1, 50) != null) {
        System.out.println("- Null not returned when calling lookUp on empty tree.");
        return false;
      }

      Chugimon mon1and50 = new Chugimon(1, 50);
      Chugimon mon1and51 = new Chugimon(1, 51);
      Chugimon mon1and25 = new Chugimon(1, 25);
      Chugimon mon1and75 = new Chugimon(1, 75);
      Chugimon mon1and26 = new Chugimon(1, 26);

      tree.add(mon1and50);
      tree.add(mon1and25);
      tree.add(mon1and75);
      tree.add(mon1and26);
      tree.add(mon1and51);

      if (!tree.lookup(1, 50).equals(mon1and50)) {
        System.out.println("- Chugimon 1.50 not found despite being in tree root");
        return false;
      }

      if (!tree.lookup(1, 51).equals(mon1and51)) {
        System.out.println("- Chugimon 1.51 not found despite being in tree leaf");
        return false;
      }

      if (!tree.lookup(1, 75).equals(mon1and75)) {
        System.out.println("- Chugimon 1.75 not found despite being in tree leaf");
        return false;
      }

      if (tree.lookup(1, 49) != null) {
        System.out.println("- Chugimon 1.49 supposedly found despite not existing in tree");
        return false;
      }

      if (tree.lookup(1, 2) != null) {
        System.out.println("- Chugimon 1.2 supposedly found despite not existing in tree");
        return false;
      }

      if (!tree.lookup(1, 25).equals(mon1and25)) {
        System.out.println("- Chugimon 1.25 not found despite existing in tree");
        return false;
      }

      if (!tree.lookup(1, 26).equals(mon1and26)) {
        System.out.println("- Chugimon 1.26 not found despite existing in tree");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.countType() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCountType() {
    try {
      ChugiTree tree = new ChugiTree();
      Chugimon mon1 = new Chugimon(22, 77); // Normal, Fire
      Chugimon mon2 = new Chugimon(57, 95); // Fighting, Rock
      Chugimon mon3 = new Chugimon(95, 59); // Rock, Fire
      Chugimon mon4 = new Chugimon(150, 151); // Psychic, Null

      tree.add(mon1);
      tree.add(mon2);
      tree.add(mon3);
      tree.add(mon4);

      if (tree.countType(ChugiType.BUG) != 0) {
        System.out.println("- countType not 0, counting bug types, 0 bug types in tree");
        return false;
      }

      if (tree.countType(ChugiType.NORMAL) != 1) {
        System.out.println("- countType not 1, counting normal types, 1 normal type in tree");
        return false;
      }

      if (tree.countType(ChugiType.ROCK) != 2) {
        System.out
            .println("- countType not 2 while one type is primary and the other is secondary");
        return false;
      }

      if (tree.countType(ChugiType.FIRE) != 2) {
        System.out.println("- countType not 2 while both types are secondary");
        return false;
      }

      if (tree.countType(ChugiType.PSYCHIC) != 1) {
        System.out.println("- countType not 1 while searching for singularly typed chugimon.");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Chugimon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ChugiTree with four levels for instance, is 4.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    try {
      ChugiTree tree = new ChugiTree();

      if (tree.height() != 0) {
        System.out.println("- Height non-zero when root is null.");
        return false;
      }

      Chugimon mon1and50 = new Chugimon(1, 50);
      tree.add(mon1and50);

      if (tree.height() != 1) {
        System.out.println("- Height not 1 when only root mon exists.");
        return false;
      }

      Chugimon mon1and51 = new Chugimon(1, 51);
      Chugimon mon1and25 = new Chugimon(1, 25);

      tree.add(mon1and25);
      tree.add(mon1and51);

      if (tree.height() != 2) {
        System.out.println("- Height not two when only 1 branch off of root.");
        return false;
      }

      Chugimon mon1and26 = new Chugimon(1, 26);
      tree.add(mon1and26);

      if (tree.height() != 3) {
        System.out.println("- Height not three when branch exists off root child");
        return false;
      }

      // check if valid bst is maintained
      if (!tree.isValidBST()) {
        System.out.println("- Tree not valid BST after adding mon1and26");
        return false;
      } 

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.getFirst() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetFirst() {
    try {
      ChugiTree tree = new ChugiTree();

      if (tree.getFirst() != null) {
        System.out.println("- getFirst did not return null while tree is empty.");
        return false;
      }

      Chugimon mon = new Chugimon(1, 2);
      tree.add(mon);

      if (!tree.getFirst().equals(mon)) {
        System.out.println("- getFirst did not return correct mon when only root exists.");
        return false;
      }

      Chugimon mon2 = new Chugimon(1, 3);
      tree.add(mon2);

      if (!tree.getFirst().equals(mon2)) {
        System.out.println("- getFirst did not return correct mon when branches exist.");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.getLast() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLast() {
    try {
      ChugiTree tree = new ChugiTree();

      if (tree.getLast() != null) {
        System.out.println("- getLast did not return null while tree is empty.");
        return false;
      }

      Chugimon mon = new Chugimon(1, 11);
      tree.add(mon);

      if (!tree.getLast().equals(mon)) {
        System.out.println("- getLast did not return correct mon when only root exists.");
        return false;
      }

      Chugimon mon2 = new Chugimon(1, 6);
      tree.add(mon2);

      if (!tree.getLast().equals(mon)) {
        System.out.println("- getLast did not return correct mon when earlier mon exists.");
        return false;
      }

      Chugimon mon3 = new Chugimon(5, 99);
      tree.add(mon3);

      if (!tree.getLast().equals(mon3)) {
        System.out.println("- getLast did not return correct mon when later mon exists.");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.delete() method. This test must consider at least 3
   * test scenarios. (1) Remove a Chugimon that is at leaf node (2) Remove a Chugimon at non-leaf
   * node. For each of these scenarios, check that the size of the tree was decremented by one and
   * that the resulting ChugiTree is a valid BST, (3) ensures that the ChugiTree.delete() method
   * returns false when called on an Chugimon that is not present in the BST.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testDelete() {
    try {
      // creates a new ChugiTree
      ChugiTree tree = new ChugiTree();

      Chugimon c1 = new Chugimon(10, 1);
      tree.add(c1);

      Chugimon c2 = new Chugimon(20, 2);
      tree.add(c2);

      Chugimon c3 = new Chugimon(30, 3);
      tree.add(c3);

      //check if tree is valid
      if (!tree.isValidBST()) {
        System.out.println("- Tree is not valid BST");
        return false;
      }

      // test scenario 1 - remove a Chugimon that is at leaf node
      if (!tree.delete(c1)) {
        System.out.println(
            "testDelete() failed: delete() returned false when called on a Chugimon that is present in the BST");
        return false;
      }

      // check if tree is valid
      if (!tree.isValidBST()) {
        System.out.println("- Tree is not valid BST");
        return false;
      }

      if (tree.size() != 2) {
        System.out.println("testDelete() failed: delete() did not decrement the size of the tree");
        return false;
      }

      // test sceanrio 2 - remove a Chugimon at non-leaf node
      if (!tree.delete(c3)) {
        System.out.println(
            "testDelete() failed: delete() returned false when called on a Chugimon that is present in the BST");
        return false;
      }

      // check if tree is valid
      if (!tree.isValidBST()) {
        System.out.println("- Tree is not valid BST");
        return false;
      }

      if (tree.size() != 1) {
        System.out.println("testDelete() failed: delete() did not decrement the size of the tree");
        return false;
      }

      // test scenario 3 - ensures that the ChugiTree.delete() method returns false when called on
      // an Chugimon that is not present in the BST

      // tries to delete a Chugimon that is not in the BST
      if (tree.delete(new Chugimon(1, 30))) {
        System.out.println(
            "testDelete() failed: delete() returned true when called on a Chugimon that is not present in the BST");
        return false;
      }

      // check if tree is valid
      if (!tree.isValidBST()) {
        System.out.println("- Tree is not valid BST");
        return false;
      }

      if (tree.size() != 1) {
        System.out.println("testDelete() failed: delete() did not decrement the size of the tree");
        return false;
      }

      return true;
    } catch (Exception e) {
      System.out.println("- Encountered error");
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.next() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testNext() {
    ChugiTree tree = new ChugiTree();

    Chugimon a = new Chugimon(10, 30); // root
    tree.add(a);
    Chugimon b = new Chugimon(5, 20); // right child of root
    tree.add(b);
    Chugimon c = new Chugimon(15, 40); // left child of root
    tree.add(c);
    Chugimon d = new Chugimon(3, 10); // right child of right child of root
    tree.add(d);
    Chugimon e = new Chugimon(7, 25); // left child of right child of right child of root
    tree.add(e);
    Chugimon f = new Chugimon(12, 35); // left child of right child of root
    tree.add(f);
    Chugimon g = new Chugimon(18, 50); // right child of right child of root
    tree.add(g);
    // right child of right child of right child of root
    Chugimon h = new Chugimon(20, 60);
    tree.add(h);
    // left child of right child of right child of right child of root
    Chugimon i = new Chugimon(19, 55);
    tree.add(i);

    if (tree.next(a) != b) {
      System.out.println("testNext() failed: next() did not return correct Chugimon");
      return false;
    }

    if (tree.next(b) != g) {
      System.out.println("testNext() failed: next() did not return correct Chugimon");
      return false;
    }

    if (tree.next(c) != f) {
      System.out.println("testNext() failed: next() did not return correct Chugimon");
      return false;
    }

    try {
      tree.next(d);
      System.out.println("testNext() failed: next() did not throw NoSuchElementException");
      return false;
    } catch (NoSuchElementException nse) {
      // expected
    }

    if (tree.next(e) != d) {
      System.out.println("testNext() failed: next() did not return correct Chugimon");
      return false;
    }

    if (tree.next(f) != a) {
      System.out.println("testNext() failed: next() did not return correct Chugimon");
      return false;
    }

    if (tree.next(g) != i) {
      System.out.println("testNext() failed: next() did not return correct Chugimon");
      return false;
    }

    if (tree.next(h) != e) {
      System.out.println("testNext() failed: next() did not return correct Chugimon");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ChugiTree.previous() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testPrevious() {
    ChugiTree tree = new ChugiTree();

    Chugimon a = new Chugimon(10, 30); // root
    tree.add(a);
    Chugimon b = new Chugimon(5, 20); // left child of root
    tree.add(b);
    Chugimon c = new Chugimon(15, 40); // right child of root
    tree.add(c);
    Chugimon d = new Chugimon(3, 10); // left child of left child of root
    tree.add(d);
    Chugimon e = new Chugimon(7, 25); // right child of left child of root
    tree.add(e);
    Chugimon f = new Chugimon(12, 35); // left child of right child of root
    tree.add(f);
    Chugimon g = new Chugimon(18, 50); // right child of right child of root
    tree.add(g);
    // right child of right child of right child of root
    Chugimon h = new Chugimon(20, 60);
    tree.add(h);
    // left child of right child of right child of right child of root
    Chugimon i = new Chugimon(19, 55);
    tree.add(i);

    if (tree.previous(a) != f) {
      System.out.println("testPrevious() failed: previous() did not return correct Chugimon");
      return false;
    }

    if (tree.previous(b) != a) {
      System.out.println("testPrevious() failed: previous() did not return correct Chugimon");
      return false;
    }

    try {
      tree.previous(c);
      System.out.println("testPrevious() failed: previous() did not throw NoSuchElementException");
      return false;
    } catch (NoSuchElementException nse) {
      // expected
    }

    if (tree.previous(d) != e) {
      System.out.println("testPrevious() failed: previous() did not return correct Chugimon");
      return false;
    }

    if (tree.previous(e) != h) {
      System.out.println("testPrevious() failed: previous() did not return correct Chugimon");
      return false;
    }

    if (tree.previous(f) != c) {
      System.out.println("testPrevious() failed: previous() did not return correct Chugimon");
      return false;
    }

    if (tree.previous(g) != b) {
      System.out.println("testPrevious() failed: previous() did not return correct Chugimon");
      return false;
    }

    if (tree.previous(h) != i) {
      System.out.println("testPrevious() failed: previous() did not return correct Chugimon");
      return false;
    }

    return true;
  }



  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    String[] methods = {"testChugimonCompareToEquals", "testChugimonToString",
        "testIsValidBSTHelper", "testAddToStringSize", "testLookup", "testHeight", "testCountType",
        "testGetFirst", "testGetLast", "testDelete", "testNext", "testPrevious"};
    for (String method : methods) {
      System.out.println(method + "()");
      try {
        ChugidexTester.class.getMethod(method, (Class<?>[]) null).invoke(null);
      } catch (Exception e) {
      }
    }
  }

}
