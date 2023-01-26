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
/////////////////////// ////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through MultipleChoiceQuestion(s) in a singly linked list defined
 * by its head
 *
 * @author Nico
 */
public class QuizQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  /** refers to a node in the singly linked list of MultipleChoiceQuestion */
  private LinkedNode<MultipleChoiceQuestion> next;

  /**
   * Creates a new QuizQuestionsIterator to start iterating through a singly linked list storing
   * MultipleChoiceQuestion elements
   *
   * @param startNode pointer to the head of the singly linked list
   */
  public QuizQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    next = startNode;
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s).
   *
   * @return true if there are more MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext() {
    // if the next node is null, there are no more questions
    return next != null;
  }

  /**
   * Returns the next MultipleChoiceQuestion in this iteration
   *
   * @return the next MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more questions
   *     in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {

    // if there are no more questions, throw an exception
    if (!hasNext()) {
      throw new NoSuchElementException("There are no more questions in this iteration");
    }
    // store the next question
    MultipleChoiceQuestion nextQuestion = next.getData();

    // move to the next question
    next = next.getNext();

    // return the next question
    return nextQuestion;
    
  }
}
