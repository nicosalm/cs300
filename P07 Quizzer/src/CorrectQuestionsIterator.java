//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Quizzer
// Course:   CS 300 Fall 2022
//
// Author:   @Nico
// Email:    nbsalm@wisc.edu
// Lecturer: @legault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an iterator to iterate over correctly answered MultipleChoiceQuestion(s) stored in a
 * singly linked list defined by its head.
 *
 * @author Nico
 */
public class CorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  /** refers to a node in the singly linked list of MultipleChoiceQuestion to traverse */
  private LinkedNode<MultipleChoiceQuestion> next;

  /**
   * Creates a new CorrectQuestionsIterator to start iterating through a singly linked list storing
   * MultipleChoiceQuestion elements
   *
   * @param startNode pointer to the head of the singly linked list
   */
  public CorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    next = startNode;

    // if the first node is not null, and the first node is not correct, then move to the next node
    while (next != null && !next.getData().isCorrect()) {
      next = next.getNext();
    }
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s) answered correctly.
   *
   * @return true if there are more correct MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext() {
    // if the next node is null, there are no more questions
    return next != null;
  }

  /**
   * Returns the next correct MultipleChoiceQuestion in this iteration
   *
   * @return the next correct MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more correct
   *     questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    // if there are no more correct questions, throw an exception
    if (!hasNext()) {
      throw new NoSuchElementException("No more correct questions in this iteration");
    }

    // store the current node's data
    MultipleChoiceQuestion current = next.getData();

    // move to the next node
    next = next.getNext();

    // if the next node is not null, and the next node is not correct, then move to the next node
    while (next != null && !next.getData().isCorrect()) {
      next = next.getNext();
    }

    // return the current node's data
    return current;
  }
}
