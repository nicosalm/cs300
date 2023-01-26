//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Quizzer
// Course: CS 300 Fall 2022
//
// Author: @Nico
// Email: nbsalm@wisc.edu
// Lecturer: @legault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class models an iterable singly-linked list data structure which stores
 * elements of type
 * MultipleChoiceQuestion.
 *
 * @author TODO
 */
public class ListQuizzer implements Iterable<MultipleChoiceQuestion> {
  /** Head of this singly linked list */
  private LinkedNode<MultipleChoiceQuestion> head;

  /** Tail of this singly linked list */
  private LinkedNode<MultipleChoiceQuestion> tail;

  /** Total number of MultipleChoiceQuestions stored in this ListQuizzer */
  private int size;

  /**
   * The listing mode of this list quizzer which defines which questions are going
   * to be listed
   * while iterating through this list
   */
  private ListingMode listingMode;

  /**
   * this constructor creates a new empty instance of ListQuizzer which contains
   * zero elements and
   * sets its listing mode to be ListingMode.ALL by default.
   */
  public ListQuizzer() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    listingMode = ListingMode.ALL;
  }

  /**
   * Checks whether this list is empty
   *
   * @return true if this list is empty and false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Gets the size of this list
   *
   * @return the size of this list
   */
  public int size() {
    return size;
  }

  /**
   * Sets the listing mode of this list to the one provided as input
   *
   * @param listingMode listing mode to set
   */
  public void switchMode(ListingMode listingMode) {
    this.listingMode = listingMode;
  }

  /**
   * Adds a specific MultipleChoiceQuestion to a given position of this list
   *
   * @param index    position index where to add the newQuestion to this list
   * @param question some MultipleChoiceQuestion to add to this list
   * @throws NullPointerException      with a descriptive error message if
   *                                   newQuestion is null
   * @throws IndexOutOfBoundsException with a descriptive error message if index
   *                                   is OUT of the range
   *                                   0 .. size() inclusive
   */
  public void add(int index, MultipleChoiceQuestion question)
      throws NullPointerException, IndexOutOfBoundsException {

    if (question == null) { // if the question is null
      throw new NullPointerException("The question is null");
    }

    if (index < 0 || index > size) { // if the index is out of range
      throw new IndexOutOfBoundsException("The index is out of bounds");
    }

    LinkedNode<MultipleChoiceQuestion> newNode = new LinkedNode<MultipleChoiceQuestion>(question);

    if (index == 0) { // if the index is 0
      newNode.setNext(head);
      head = newNode;
    } else { // if the index is not 0
      LinkedNode<MultipleChoiceQuestion> last = head; // create a new node, last, and set it to head
      for (int i = 0; i < index - 1; i++) { // loop through the list
        last = last.getNext(); // get the next node
      }
      newNode.setNext(last.getNext()); // set the next node
      last.setNext(newNode); 
    }

    if (index == size) { // finally if the index is the size
      tail = newNode; // set the tail to the new node
    }

    size++; // increment the size
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the head of this list
   *
   * @param question some MultipleChoiceQuestion to add to the head of this list
   * @throws NullPointerException with a descriptive error message if newQuestion
   *                              is null
   */
  public void addFirst(MultipleChoiceQuestion question) throws NullPointerException {

    if (question == null) { // if the question is null
      throw new NullPointerException("The question is null");
    }

    add(0, question); // call the add method
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the tail of this list
   *
   * @param question some MultipleChoiceQuestion to add to the tail of this list
   * @throws NullPointerException with a descriptive error message if newQuestion
   *                              is null
   */
  public void addLast(MultipleChoiceQuestion question) throws NullPointerException {

    if (question == null) { // if the question is null
      throw new NullPointerException("The question is null");
    }
    
    add(size, question); // call the add method
  }

  /**
   * This method removes all the question from this list. The list should be empty
   * after this method
   * is called.
   */
  public void clear() {
    for (int i = 0; i < size; i++) { // iterate through the list
      remove(i); // remove the node each the index
    }
  }

  /**
   * Checks whether this list contains a match with someQuestion
   *
   * @param someQuestion some question to find
   * @return true if this list contains a match with someQuestion and false
   *         otherwise
   */
  public boolean contains(MultipleChoiceQuestion someQuestion) {
    for (MultipleChoiceQuestion question : this) { // iterate through the list
      if (question.equals(someQuestion)) { // if the question is equal to someQuestion
        return true; // return true
      }
    }
    return false; // otherwise return false
  }

  /**
   * Returns the MultipleChoiceQuestion stored at the given index within this list
   *
   * @param index index of the MultipleChoiceQuestion to return
   * @return the MultipleChoiceQuestion stored at the given index within this list
   * @throws IndexOutOfBoundsException if index is out of the range 0 .. size()
   */
  public MultipleChoiceQuestion get(int index) throws IndexOutOfBoundsException {

    if (index < 0 || index >= size) { // if the index is out of range
      throw new IndexOutOfBoundsException("The index is out of bounds");
    }

    LinkedNode<MultipleChoiceQuestion> current = head; // create a new node, current, and set it to head
    for (int i = 0; i < index; i++) { // iterate through the list until the index
      current = current.getNext(); // getting the next node
    }

    return current.getData(); // return the data
  }

  /**
   * Gets the MultipleChoiceQuestion at the head of this list
   *
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException with a descriptive error message if this list
   *                                is empty
   */
  public MultipleChoiceQuestion getFirst() throws NoSuchElementException {
    if (isEmpty()) { // if the list is empty
      throw new NoSuchElementException(
          "ERROR: Cannot get first question because the list is empty.");
    }
    return head.getData(); // return the data of the head
  }

  /**
   * Gets the MultipleChoiceQuestion at the tail of this list
   *
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException with a descriptive error message if this list
   *                                is empty
   */
  public MultipleChoiceQuestion getLast() throws NoSuchElementException {
    if (isEmpty()) { // if the list is empty
      throw new NoSuchElementException(
          "ERROR: Cannot get last question because the list is empty.");
    }
    return tail.getData(); // return the data of the tail
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the given index
   *
   * @param index of the MultipleChoiceQuestion to remove
   * @return the removed MultipleChoiceQuestion
   * @throws IndexOutOfBoundsException with a descriptive error message if index
   *                                   is out of the range
   *                                   0 .. size()
   */
  public MultipleChoiceQuestion remove(int index) throws IndexOutOfBoundsException {

    if (index < 0 || index >= size) { // if the index is out of range
      throw new IndexOutOfBoundsException("The index is out of bounds");
    }

    LinkedNode<MultipleChoiceQuestion> current = head; // create a new node, current, and set it to head
    LinkedNode<MultipleChoiceQuestion> previous = null; // create a new node, previous, and set it to null

    for (int i = 0; i < index; i++) { // iterate through the list until the index
      previous = current; // set the previous node to the current node
      current = current.getNext(); // set the current node to the next node
    }

    if (previous == null) { // if the previous node is null
      head = current.getNext(); // set the head to the next node
    } else { // otherwise
      previous.setNext(current.getNext()); // set the next node to the current node
    }

    if (index == size - 1) { // if the index is the last node
      tail = previous; // set the tail to the previous node
    }

    size--; // decrement the size
 
    return current.getData(); // return the data of the current node
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the head of this list
   *
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException with a descriptive error message if this list
   *                                is empty
   */
  public MultipleChoiceQuestion removeFirst() throws NoSuchElementException {

    if (isEmpty()) { // if the list is empty
      throw new NoSuchElementException(
          "ERROR: Cannot remove first question because the list is empty.");
    }

    return remove(0); // remove the first node
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the tail of this list
   *
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException with a descriptive error message if this list
   *                                is empty
   */
  public MultipleChoiceQuestion removeLast() throws NoSuchElementException {

    if (isEmpty()) { // if the list is empty
      throw new NoSuchElementException(
          "ERROR: Cannot remove last question because the list is empty.");
    }

    return remove(size-1); // remove the last node
  }

  /**
   * Returns an iterator to iterate through this list with respect to the
   * listingMode. If the
   * listingMode is ALL, the returned iterator is initialized to the head of this
   * list. If the
   * listingMode is CORRECT, the returned iterator is initialized to the node
   * carrying first
   * correctly answered question If the listingMode is INCORRECT, the returned
   * iterator is
   * initialized to the node carrying first incorrectly answered question
   *
   * @return an iterator to iterate through this list with respect to the
   *         listingMode of this list.
   */
  @Override
   public Iterator<MultipleChoiceQuestion> iterator() {

    // IF the listingMode is CORRECT, return an iterator starting at the first
    // correct answer
    if (listingMode == ListingMode.CORRECT) {
      return new CorrectQuestionsIterator(head);
    }

    // IF the listingMode is INCORRECT, return an iterator starting at the first
    // incorrect answer
    if (listingMode == ListingMode.INCORRECT) {
      return new IncorrectQuestionsIterator(head);
    }

    // IF the listingMode is ALL, return an iterator starting at the head
    return new QuizQuestionsIterator(head);
  }

  /**
   * Calculates the total points of the correctly answered questions of this
   * ListQuizzer
   *
   * @return the score of this ListQuizzer
   */
  public int calculateScore() {

    int score = 0; // initialize the score to 0

    LinkedNode<MultipleChoiceQuestion> current = head; // start at the head
    while (current != null) { // iterate through the list
      if (current.getData().isCorrect()) { // if the current node's data is correct
        score += current.getData().getPointsPossible(); // add the current node's data's points to
                                                        // the score
      }
      current = current.getNext(); // set the current node to the next node
    }

    return score; // return the score
  }

  /**
   * Calculates the total possible points of this ListQuizzer
   *
   * @return the score of this ListQuizzer
   */
  public int calculateTotalPoints() {

    int totalPoints = 0; // initialize the total points to 0

    LinkedNode<MultipleChoiceQuestion> current = head; // start at the head
    while (current != null) { // iterate through the list
      totalPoints += current.getData().getPointsPossible(); // add the current node's data's points
                                                            // to the total points
      current = current.getNext(); // set the current node to the next node
    }

    return totalPoints; // return the total points
  }

  /**
   * Returns a deep copy of this list. This method creates a copy of this list as
   * a new ListQuizzer
   * and adds deep copies of each MultipleChoiceQuestion stored in this list to
   * the deep copy.
   *
   * @return a deep copy of this list.
   */
  public ListQuizzer copy() {

    ListQuizzer copy = new ListQuizzer(); // create a new ListQuizzer

    LinkedNode<MultipleChoiceQuestion> current = head; // start at the head
    int index = 0; // initialize the index to 0
    while (current != null) { // iterate through the list
      copy.add(index, current.getData().copy()); // add a copy of the current node's data to the
                                                 // copy
      current = current.getNext(); // set the current node to the next node
      index++; // increment the index
    }

    return copy; // return the copy
  }

  /**
   * Loads MultipleChoiceQuestions from a file
   * 
   * @author Jeff and Mouna
   *
   * @param file file to read
   * @return the number of added MultipleChoiceQuestions to this list
   * 
   * @throws FileNotFoundException if the file is not found
   */
  public int loadQuestions(File file) throws FileNotFoundException {
    int loadedCount = 0;
    // try to read the file
    Scanner reader = null;
    try {
      reader = new Scanner(file);
      // parse the file lines line by line
      while (reader.hasNextLine()) {
        String title = reader.nextLine();
        String question = reader.nextLine();
        int answerCount = reader.nextInt();
        String[] answerList = new String[answerCount];
        int index = 0;
        while (!reader.hasNextInt()) {
          String answer = reader.nextLine();
          answerList[index] = answer;
          index++;
        }

        String line = reader.nextLine();
        Scanner lineScanner = new Scanner(line);
        int indexCorrectAnswer = lineScanner.nextInt();
        lineScanner.close();

        line = reader.nextLine();
        lineScanner = new Scanner(line);
        int points = lineScanner.nextInt();
        lineScanner.close();

        MultipleChoiceQuestion quizQuestion = new MultipleChoiceQuestion(title, question, answerList,
            indexCorrectAnswer, points);

        addLast(quizQuestion);
        loadedCount += 1;
      }
    } finally {
      if (reader != null)
        reader.close();
    }

    return loadedCount;
  }

  /**
   * Allows a user to take this quiz. The quiz should be taken on a deep copy of
   * this ListQuizzer.
   * This method should not make any changes to the contents of this ListQuizzer.
   * 
   * @author Jeff and Mouna
   *
   * @return the instance of ListQuizzer taken by the user. It should include the
   *         user's responses.
   */
  public ListQuizzer takeQuiz() {

    ListQuizzer copy = this.copy();
    copy.switchMode(ListingMode.ALL);
    Scanner input = new Scanner(System.in);
    for (MultipleChoiceQuestion question : copy) {
      System.out.println(question);
      System.out.print("Enter your answer: ");
      int entry = input.nextInt();
      question.setStudentAnswerIndex(entry - 1);
      if (question.isCorrect()) {
        System.out.println("Correct!");
      } else {
        System.out.println("Incorrect!");
      }
    }
    int correctPoints = copy.calculateScore();
    int totalPoints = copy.calculateTotalPoints();
    System.out.println("Your Score: " + correctPoints);
    System.out.println("Percentage: " + correctPoints / (double) totalPoints);
    input.close();
    return copy;
  }

  /**
   * Returns true if o is a ListQuizzer which has the exact same contents as this
   * ListQuizzer
   *
   * @param o an object to compare with
   * @return true if o is instanceof ListQuizzer with the exact same contents as
   *         this ListQuizzer
   * 
   * @author Mouna
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof ListQuizzer) {
      ListQuizzer other = (ListQuizzer) o;
      if (this.size() != other.size())
        return false;
      this.switchMode(ListingMode.ALL);
      other.switchMode(ListingMode.ALL);
      Iterator<MultipleChoiceQuestion> iterator = this.iterator();
      Iterator<MultipleChoiceQuestion> otherIterator = other.iterator();
      while (iterator.hasNext()) {
        if (!iterator.next().equals(otherIterator.next()))
          return false;
      }
      return true;
    }
    return false;
  }
}
