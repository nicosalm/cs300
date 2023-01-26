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
 * Implements an iterator to iterate over incorrectly answered MultipleChoiceQuestion(s) stored in a
 * singly linked list defined by its head.
 *
 * @author Nico
 */
public class IncorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  /** refers to a node in the singly linked list of MultipleChoiceQuestion */
  private LinkedNode<MultipleChoiceQuestion> next;

  /**
   * Creates a new InCorrectQuestionsIterator to start iterating through a singly linked list
   * storing MultipleChoiceQuestion elements
   *
   * @param startNode pointer to the head of the singly linked list
   */
  public IncorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    next = startNode;
    for (; next != null; next = next.getNext()) {
      if (!next.getData().isCorrect())
        break;
    }
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s) answered incorrectly.
   *
   * @return true if there are more incorrect MultipleChoiceQuestion(s) in this iteration
   */
  @Override
   public boolean hasNext() {
    // if the next node is null, there are no more questions
    return next != null;
  }

  /**
   * Returns the next incorrect MultipleChoiceQuestion in this iteration
   *
   * @return the next incorrect MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more incorrect
   *     questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (!hasNext()) { // if there are no more questions
      throw new NoSuchElementException("No more incorrect questions in this iteration");
    }
    LinkedNode<MultipleChoiceQuestion> temp = next;
    
    // find the next incorrect question
    for (next = next.getNext(); next != null; next = next.getNext()) {
      if (!next.getData().isCorrect())
        break;
    }

    // return the next incorrect question or NoSuchElementException if there are no more
    if (!temp.getData().isCorrect()) {
      return temp.getData();
    } else {
      throw new NoSuchElementException("No more incorrect questions in this iteration");
    }
  }
}
