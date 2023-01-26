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
 * This class implements a ChugidexStorage as a Binary Search Tree.
 * 
 * @author Alex, Nico
 */
public class ChugiTree implements ChugidexStorage {

  // The root of this ChugiTree. Set to null when tree is empty.
  private BSTNode<Chugimon> root;

  // The size of this ChugiTree (total number of Chugimon stored in this BST)
  private int size;


  /**
   * Getter method for the Chugimon at the root of this BST.
   * 
   * @return the root of the BST.
   */
  public Chugimon getRoot() {
    return root.getData();
  }

  /**
   * A helper method for determining whether this ChugiTree is a valid BST. In order to be a valid
   * BST, the following must be true: For every internal (non-leaf) node X of a binary tree, all the
   * values in the node's left subtree are less than the value in X, and all the values in the
   * node's right subtree are greater than the value in X.  
   * 
   * @return true if this ChugiTree is a valid BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(root);
  }

  /**
   * A helper method for determining whether a BST rooted at node is a valid BST. In order to be a
   * valid BST, the following must be true: For every internal (non-leaf) node of a binary tree, all
   * the values in a node's left subtree are less than the values in a node's right subtree.
   * 
   * @param node The root of the BST.
   * @return true if the binary tree rooted at node is a BST, false otherwise
   */
  public static boolean isValidBSTHelper(BSTNode<Chugimon> node) {
    if (node == null)
      return true;

    BSTNode<Chugimon> leftNode = node.getLeft();
    BSTNode<Chugimon> rightNode = node.getRight();

    // Calls isValidNode on both the right and left side of the current node
    boolean isValid = isValidNode(node, leftNode, true) && isValidNode(node, rightNode, false);

    // Calls isValidNode on every node by looping through isValidBSTHelper
    return isValid && isValidBSTHelper(leftNode) && isValidBSTHelper(rightNode);
  }

  /**
   * Recursion used to check if the branches of a node are valid.
   * 
   * @param nodeToCheck  - The node which everything is being compared to
   * @param currentNode  - The current node that is being compared in this loop
   * @param isLeftBranch - Whether the left or right branch is being examined. If it's left branch
   *                     then everything should be less than the currentNode. If right, then
   *                     greater.
   * @return whether or not the specified branch of the nodeToCheck is valid
   */
  private static boolean isValidNode(BSTNode<Chugimon> nodeToCheck, BSTNode<Chugimon> currentNode,
      boolean isLeftBranch) {
    if (currentNode == null)
      return true;

    BSTNode<Chugimon> leftNode = currentNode.getLeft();
    BSTNode<Chugimon> rightNode = currentNode.getRight();

    // Checks to see if currentNode is either less or greater than nodeToCheck depending on
    // isLeftBranch
    boolean isValid = (currentNode.getData().compareTo(nodeToCheck.getData()) < 0) == isLeftBranch;

    // Loops through every node in the branch to check the above isValid.
    return isValid && isValidNode(nodeToCheck, leftNode, isLeftBranch)
        && isValidNode(nodeToCheck, rightNode, isLeftBranch);
  }

  /**
   * Checks whether this ChugiTree is empty or not
   * 
   * @return true if this tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return root == null; // it's empty :(
  }

  /**
   * Gets the size of this ChugiTree
   * 
   * @return the total number of Chugimons stored in this tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns a String representation of all the Chugimons stored within this ChugiTree in the
   * increasing order with respect to the result of Chugimon.compareTo() method. The string should
   * be a comma-separated list of all the Chugimon toString() values. No spaces are expected to be
   * in the resulting string. No comma should be at the end of the resulting string. For instance,
   * 
   * "nameOne#12.25,nameTwo#12.56,nameTwo#89.27"
   * 
   * @return a string containing all of the Chugimon, in the increasing order. Returns an empty
   *         string "" if this BST is empty.
   * 
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the ChugiTree rooted at node.
   * An example of the String representation of the contents of a ChugiTree storing three Chugimons
   * is provided in the description of the above toString() method.
   * 
   * @param node references the root of a subtree
   * @return a String representation of all the Chugimons stored in the sub-tree rooted at node in
   *         increasing order. Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Chugimon> node) {
    // if node is null, return empty string
    if (node == null)
      return "";

    String thisNodeString = node.getData().toString();
    // print left, then print self, then print right.
    BSTNode<Chugimon> leftBranch = null;
    BSTNode<Chugimon> rightBranch = null;

    if (node.getLeft() != null) {
      leftBranch = node.getLeft(); // if the left child is not null, set leftBranch to the left
                                   // child
    }

    if (node.getRight() != null) {
      rightBranch = node.getRight(); // if the right child is not null, set rightBranch to the right
                                     // child
    }

    // make the string
    String left = toStringHelper(leftBranch);
    String right = toStringHelper(rightBranch);
    return left + (left == "" ? "" : ",") + thisNodeString + (right == "" ? "" : ",") + right;
  }

  /**
   * Adds a new Chugimon to this ChugiTree. Duplicate Chugimons are NOT allowed.
   * 
   * @param newChugimon Chugimon to add to this ChugiTree
   * @return true if if the newChugimon was successfully added to the ChugiTree, false if a match
   *         with newChugimon is already present in the tree.
   * @throws IllegalArgumentException with a descriptive error message if newChugimon is null.
   */
  @Override
  public boolean add(Chugimon newChugimon) {
    if (newChugimon == null)
      throw new IllegalArgumentException("Chugimon cannot be null.");

    if (root == null) { // if the root is null, set the root to the newChugimon and return true
      size++;
      root = new BSTNode<Chugimon>(newChugimon);
      return true;
    } else { // otherwise, call the recursive helper method
      if (addHelper(newChugimon, root)) { // if the helper method returns true, increment the size
                                          // and return true
        size++;
        return true;
      } else {
        return false; // otherwise, return false
      }
    }
  }

  /**
   * Recursive helper method to insert a new Chugimon to a Pokedex rooted at node.
   * 
   * @param node        The "root" of the subtree we are inserting the new Chugimon into.
   * @param newChugimon The Chugimon to be added to a BST rooted at node. We assume that newChugimon
   *                    is NOT null.
   * @return true if the newChugimon was successfully added to the ChugiTree, false if a match with
   *         newChugimon is already present in the subtree rooted at node.
   */
  protected static boolean addHelper(Chugimon newChugimon, BSTNode<Chugimon> node) {
    Chugimon nodeMon = node.getData(); // get the data of the node

    int comparisonValue = newChugimon.compareTo(nodeMon); // compare the newChugimon to the nodeMon
    if (comparisonValue < 0) {
      if (node.getLeft() != null) // if the newChugimon is less than the nodeMon, and the left
                                  // child is not null, call the helper method on the left child
        return addHelper(newChugimon, node.getLeft()); // return the result of the helper method

      node.setLeft(new BSTNode<Chugimon>(newChugimon)); // otherwise, set the left child to the
                                                        // newChugimon and return true
      return true;

    } else if (comparisonValue > 0) { // if the newChugimon is greater than the nodeMon, and the
                                      // right child is not null, call the helper method on the
                                      // right
                                      // child
      if (node.getRight() != null)
        return addHelper(newChugimon, node.getRight()); // return the result of the helper method

      node.setRight(new BSTNode<Chugimon>(newChugimon)); // otherwise, set the right child to the
                                                         // newChugimon and return true
      return true;
    } else {
      return false; // if the newChugimon is equal to the nodeMon, return false
    }
  }

  /**
   * Searches a Chugimon given its first and second identifiers.
   * 
   * @param firstId  First identifier of the Chugimon to find
   * @param secondId Second identifier of the Chugimon to find
   * @return the matching Chugimon if match found, null otherwise.
   */
  @Override
  public Chugimon lookup(int firstId, int secondId) {
    Chugimon toFind = new Chugimon(firstId, secondId); // create a new Chugimon with the given
                                                       // firstId and secondId
    return lookupHelper(toFind, root); // call the recursive helper method
  }

  /**
   * Recursive helper method to search and return a match with a given Chugimon in the subtree
   * rooted at node, if present.
   * 
   * @param toFind a Chugimon to be searched in the BST rooted at node. We assume that toFind is NOT
   *               null.
   * @param node   "root" of the subtree we are checking whether it contains a match to target.
   * @return a reference to the matching Chugimon if found, null otherwise.
   */
  protected static Chugimon lookupHelper(Chugimon toFind, BSTNode<Chugimon> node) {
    if (node == null) { // if the node is null, return null
      return null;
    }
    if (toFind.compareTo(node.getData()) == 0) { // if the toFind is equal to the node, return the
                                                 // node
      return node.getData();
    } else if (toFind.compareTo(node.getData()) < 0) { // if the toFind is less than the node, call
                                                       // the helper method on the left child
      return lookupHelper(toFind, node.getLeft());
    } else { // otherwise, call the helper method on the right child
      return lookupHelper(toFind, node.getRight());
    }
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root); // call the recursive helper method
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at node counting the
   * number of nodes and NOT the number of edges from node to the deepest leaf
   * 
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  protected static int heightHelper(BSTNode<Chugimon> node) {
    if (node == null) { // if the node is null, return 0
      return 0;
    }
    // otherwise, return the max of the height of the left child and the height of the right child
    return 1 + Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight()));
  }

  /**
   * Recursive method to find and return the first Chugimon, in the increasing order, within this
   * ChugiTree (meaning the smallest element stored in the tree).
   * 
   * @return the first element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getFirst() {
    if (root == null) { // if the root is null, return null
      return null;
    }

    BSTNode<Chugimon> current = root; // otherwise, set the current node to the root

    // use getFirstHelper to find the first Chugimon in the tree recursively
    return getFirstHelper(current);
  }

  /**
   * Recursive helper method for getFirst().
   * 
   * @param root the node from which to find the the minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getFirstHelper(BSTNode<Chugimon> root) {
    /*
     * if the left child of the root is null, return the data of the root otherwise, call the helper
     * method on the left child
     */
    return root == null ? null : getFirstHelper(root);
  }

  /**
   * Recursive method to find and return the last Chugimon, in the increasing order, within this
   * ChugiTree (meaning the greatest element stored in the tree).
   * 
   * @return the last element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getLast() {
    if (root == null) { // if the root is null, return null
      return null;
    }

    BSTNode<Chugimon> current = root;

    // use getLastHelper to find the last Chugimon in the tree recursively
    return getLastHelper(current);
  }

  /**
   * Recursive helper method for getLast().
   * 
   * @param root the node from which to find the the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getLastHelper(BSTNode<Chugimon> root) {
    /*
     * if the right child of the root is null, return the data of the root otherwise, call the
     * helper method on the right child
     */
    return root == null ? null : getLastHelper(root);
  }

  /**
   * Recursive method to get the number of Chugimon with a primary or secondary type of the
   * specified type, stored in this ChugiTree.
   * 
   * @param chugiType the type of Chugimons to count. We assume that chugiType is NOT null.
   * @return the number of all the Chugimon objects with a primary or secondary type of the
   *         specified type stored in this ChugiTree.
   */
  public int countType(ChugiType chugiType) {
    if (root == null) {
      return 0;
    }

    // use countTypeHelper to count the number of Chugimon with the specified type
    return countTypeHelper(chugiType, root);
  }

  private int countTypeHelper(ChugiType chugiType, BSTNode<Chugimon> node) {
    // search for the Chugimon with the specified type

    if (node == null) {
      return 0;
    }

    int count = 0;

    // conditional to match primary type or secondary type (which could be null)
    if (node.getData().getPrimaryType().equals(chugiType)
        || (node.getData().getSecondaryType() != null
            && node.getData().getSecondaryType().equals(chugiType))) {
      count++;
    }

    return count + countTypeHelper(chugiType, node.getLeft()) // count the left child
        + countTypeHelper(chugiType, node.getRight());
  }

  /**
   * Finds and returns the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its successor
   * @return the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   with a descriptive error message if the Chugimon provided as
   *                                  input has no in-order successor in this ChugiTree.
   */
  @Override
  public Chugimon next(Chugimon chugi) {
    if (chugi == null) {
      throw new IllegalArgumentException("ERROR: Cannot find the successor of a null Chugimon.");
    }

    // use nextHelper to find the successor of chugi recursively
    return nextHelper(chugi, root, null);
  }

  /**
   * Recursive helper method to find and return the next Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order successor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param next  a BSTNode which stores a potentional candidate for next node
   * @return the next Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order successor in the subtree rooted at node.
   */
  protected static Chugimon nextHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> next) {

    if (chugi.compareTo(node.getData()) < 0) { // if chugi is less than the data of the node
      return nextHelper(chugi, node.getLeft(), node);

    } else if (chugi.compareTo(node.getData()) > 0) { // if chugi is greater than the data of the
                                                      // node
      return nextHelper(chugi, node.getRight(), next);

    } else {
      if (node.getRight() != null) { // if the right child of the node is not null
        return getFirstHelper(node.getRight());
      } else { // otherwise, return the next node
        if (next == null) { // if the next node is null, throw an exception
          throw new NoSuchElementException(
              "ERROR: The Chugimon provided as input has no in-order successor in this ChugiTree.");
        } else {
          return next.getData(); // otherwise, return the data of the next node
        }
      }
    }
  }

  /**
   * Finds and returns the in-order predecessor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its predecessor
   * @return the in-order predecessor of a specified Chugimon in this ChugiTree.
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   if there is no Chugimon directly before the provided Chugimon
   */
  @Override
  public Chugimon previous(Chugimon chugi) {
    if (chugi == null) { // if chugi is null, throw an exception
      throw new IllegalArgumentException("ERROR: Cannot find the predecessor of a null Chugimon.");
    }

    return previousHelper(chugi, root, null);
  }

  /**
   * Recursive helper method to find and return the previous Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order predecessor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param prev  a BSTNode which stores a potentional candidate for previous node
   * @return the previous Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order predecessor in the subtree rooted at node.
   */
  protected static Chugimon previousHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> prev) {

    if (chugi.compareTo(node.getData()) < 0) { // if chugi is less than the data of the node
      return previousHelper(chugi, node.getLeft(), prev);

    } else if (chugi.compareTo(node.getData()) > 0) { // if chugi is greater than the data of the
                                                      // node
      return previousHelper(chugi, node.getRight(), node);

    } else { // otherwise, chugi is equal to the data of the node
      if (node.getLeft() != null) { // if the left child of the node is not null
        return getLastHelper(node.getLeft()); // return the last node in the left subtree
      } else { // otherwise, return the previous node
        if (prev == null) { // if the previous node is null, throw an exception
          throw new NoSuchElementException(
              "ERROR: The Chugimon provided as input has no in-order predecessor in this ChugiTree.");
        } else {
          return prev.getData(); // otherwise, return the data of the previous node
        }
      }
    }
  }

  /**
   * Deletes a specific Chugimon from this ChugiTree.
   * 
   * @param chugi the Chugimon to delete
   * @return true if the specific Chugimon is successfully deleted, false if no match found with any
   *         Chugimon in this tree.
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   */
  @Override
  public boolean delete(Chugimon chugi) {
    if (isEmpty()) { // if chugi is null, throw an exception
      throw new IllegalArgumentException("ERROR: Cannot delete a null Chugimon.");
    }

    try { // try to delete the chugi
      root = deleteChugimonHelper(chugi, root); // if the chugi is found, delete it
      size--; // decrement the size
      return true; // return true
    } catch (NoSuchElementException e) { // if the chugi is not found, catch the exception
      return false; // return false
    }
  }

  /**
   * Recursive helper method to search and delete a specific Chugimon from the BST rooted at node
   * 
   * @param target a reference to a Chugimon to delete from the BST rooted at node. We assume that
   *               target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match with the target
   *               Chugimon.
   * 
   * 
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Chugimon
   *                                matching target in the BST rooted at <b>node</b>
   */
  protected static BSTNode<Chugimon> deleteChugimonHelper(Chugimon target, BSTNode<Chugimon> node) {
    if ((node == null)) { // if node is null, throw an exception. It will be caught in delete
      throw new NoSuchElementException("ERROR: Node not found in tree.");
    } else if (node.getRight() == null && node.getLeft() == null) { // if node is a leaf
      if (target.compareTo(node.getData()) == 0) { // if the target is found
        return null; // return null
      } else { // if the target is not found
        throw new NoSuchElementException("ERROR: Node not found in tree."); // throw an exception
      }
    }
    if (target.compareTo(node.getData()) < 0) { // if target is less than the data at node, search
                                                // the left subtree
      node.setLeft(deleteChugimonHelper(target, node.getLeft()));
    } else if (target.compareTo(node.getData()) > 0) { // if target is greater than the data at
                                                       // node, search the right subtree
      node.setRight(deleteChugimonHelper(target, node.getRight()));
    } else { // if target is equal to the data at node, delete the node
      if (node.getLeft() == null) { // if the left child is null, return the right child
        return node.getRight();
      } else if (node.getRight() == null) { // if the right child is null, return the left child
        return node.getLeft();
      } else { // if both children are not null, replace the node with the successor
        Chugimon successor = getFirstHelper(node.getRight());
        node = new BSTNode<Chugimon>(successor, node.getLeft(), node.getRight());
        node.setRight(deleteChugimonHelper(successor, node.getRight()));
      }
    }
    return node; // return the node
  }
}
