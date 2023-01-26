import java.util.Iterator;
import java.util.NoSuchElementException;

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

/**
 * This class checks the correctness of the an implementation of cs300 Fall 2022 p07 Quizzer
 *
 * @author Nico
 */
public class QuizzerTester {
  /**
   * Main method
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
    
    ListQuizzer list = new ListQuizzer();
    String[] choices = {"A", "B", "C", "D"};
    MultipleChoiceQuestion a = new MultipleChoiceQuestion("node1", "q", choices, 0, 1);
    MultipleChoiceQuestion b = new MultipleChoiceQuestion("node2", "q", choices, 0, 1);
    MultipleChoiceQuestion c = new MultipleChoiceQuestion("node3", "q", choices, 0, 1);

    list.addFirst(a);
    list.addFirst(b);
    list.addFirst(c);

    list.takeQuiz();
  }

  /**
   * Runs all the tester methods defined in this QuizzerTester
   *
   * @return true if all tests pass and false if any of the tests fails
   */
  public static boolean runAllTests() {
    return testMultipleChoiceQuestion() && testLinkedNode() && testCorrectQuestionsIterator()
        && testInCorrectQuestionsIterator() && testQuizQuestionsIterator() && testAddLast()
        && testAddFirst() && testAdd() && testRemoveFirst() && testRemoveLast() && testRemove();
  }

  /**
   * This method test and make use of the MultipleChoiceQuestion constructor, an accessor (getter)
   * method, overridden method toString() and equal() method defined in the MultipleChoiceQuestion
   * class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMultipleChoiceQuestion() {
    try {
      // creates a new MultipleChoiceQuestion object with the following information for testing:
      String[] choices = {"Paris", "Lyon", "Marseille", "Bordeaux"};
      MultipleChoiceQuestion question =
          new MultipleChoiceQuestion("Capitals", "What is the capital of France?", choices, 0, 2);

      //////////////////////////////////////// ACCESSORS /////////////////////////////////////////
      // 1. Test validity of constructor
      // (tests all instance getters, correctAnswerIndex getter, and pointsPossible getter)

      if (!question.getTitle().equals("Capitals")) { // if the title is not "Capitals"
        System.out.println("MultipleChoiceQuestion constructor failed: title is incorrect");
        return false;
      }

      // - question getter
      // if the question is not "What is the capital of France?"
      if (!question.getQuestion().equals("What is the capital of France?")) {
        System.out.println("MultipleChoiceQuestion constructor failed: question is incorrect");
        return false;
      }

      // - answers getter
      // if the answers are not {"Paris", "Lyon", "Marseille", "Bordeaux"}
      if (!question.getAnswers().equals("1. Paris\n2. Lyon\n3. Marseille\n4. Bordeaux")) {
        System.out.println("MultipleChoiceQuestion constructor failed: answers[0] is incorrect");
        return false;
      }

      // - correctAnswerIndex getter
      // if the correctAnswerIndex is not 0
      if (question.getCorrectAnswerIndex() != 0) {
        System.out
            .println("MultipleChoiceQuestion constructor failed: correctAnswerIndex is incorrect");
        return false;
      }

      // - pointsPossible getter
      // if the pointsPossible is not 2
      if (question.getPointsPossible() != 2) {
        System.out
            .println("MultipleChoiceQuestion constructor failed: pointsPossible is incorrect");
        return false;
      }

      // - makes sure IndexOutOfBoundsException is thrown when correctAnswerIndex is out of range
      try {
        // making a new MultipleChoiceQuestion object with a correctAnswerIndex that is out of range
        new MultipleChoiceQuestion("Capitals", "What is the capital of France?", choices, 4, 2);
        System.out.println("MultipleChoiceQuestion constructor failed: "
            + "IndexOutOfBoundsException not thrown when correctAnswerIndex is out of range");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected
      } catch (Exception e) {
        System.out.println("MultipleChoiceQuestion constructor failed: "
            + "IndexOutOfBoundsException not thrown when correctAnswerIndex is out of range");
        return false;
      }

      // - makes sure IllegalArgumentException is thrown when pointsPossible is negative
      try { // making a new MultipleChoiceQuestion object with a pointsPossible that is negative
        new MultipleChoiceQuestion("Capitals", "What is the capital of France?", choices, 0, -2);
        System.out.println("MultipleChoiceQuestion constructor failed: "
            + "IllegalArgumentException not thrown when pointsPossible is negative");
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("MultipleChoiceQuestion constructor failed: "
            + "IllegalArgumentException not thrown when pointsPossible is negative");
        return false;
      }

      // 2. Test validity of studentAnswerIndex getter
      if (question.getStudentAnswerIndex() != -1) { // if the studentAnswerIndex is not -1
        System.out
            .println("MultipleChoiceQuestion constructor failed: studentAnswerIndex is incorrect");
        return false;
      }

      //////////////////////////////////////// MUTATORS /////////////////////////////////////////
      // 3. Test validity of title setter
      question.setTitle("Capitals of France");
      // if the title is not "Capitals of France"
      if (!question.getTitle().equals("Capitals of France")) {
        System.out.println("MultipleChoiceQuestion setTitle failed: title is incorrect");
        return false;
      }

      // 4. Test validity of question setter
      question.setQuestion("What is the capital of France?");
      // if the question is not "What is the capital of France?"
      if (!question.getQuestion().equals("What is the capital of France?")) {
        System.out.println("MultipleChoiceQuestion setQuestion failed: question is incorrect");
        return false;
      }
      // 5. Test validity of answers setter
      String[] newChoices = {"Paris", "Lyon", "Marseille", "Bordeaux"};
      question.setAnswers(newChoices);
      // if the answers are not {"Paris", "Lyon", "Marseille", "Bordeaux"}
      if (!question.getAnswers().equals("1. Paris\n2. Lyon\n3. Marseille\n4. Bordeaux")) {
        System.out.println("MultipleChoiceQuestion setAnswers failed: answers[0] is incorrect");
        return false;
      }
      // 6. Test validity of correctAnswerIndex setter
      question.setCorrectAnswerIndex(1);
      // if the correctAnswerIndex is not 1
      if (question.getCorrectAnswerIndex() != 1) {
        System.out.println(
            "MultipleChoiceQuestion setCorrectAnswerIndex failed: correctAnswerIndex is incorrect");
        return false;
      }
      // 7. Test validity of pointsPossible setter
      question.setPointsPossible(3);
      // if the pointsPossible is not 3
      if (question.getPointsPossible() != 3) {
        System.out.println(
            "MultipleChoiceQuestion setPointsPossible failed: pointsPossible is incorrect");
        return false;
      }
      // - makes sure IllegalArgumentException is thrown when pointsPossible is negative
      try { // making a new MultipleChoiceQuestion object with a pointsPossible that is negative
        question.setPointsPossible(-2);
        System.out.println("MultipleChoiceQuestion setPointsPossible failed: "
            + "IllegalArgumentException not thrown when pointsPossible is negative");
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("MultipleChoiceQuestion setPointsPossible failed: "
            + "IllegalArgumentException not thrown when pointsPossible is negative");
        return false;
      }
      // - makes sure IllegalArgumentException is thrown when pointsPossible is zero
      try { // making a new MultipleChoiceQuestion object with a pointsPossible that is zero
        question.setPointsPossible(0);
        System.out.println("MultipleChoiceQuestion setPointsPossible failed: "
            + "IllegalArgumentException not thrown when pointsPossible is zero");
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      } catch (Exception e) {
        System.out.println("MultipleChoiceQuestion setPointsPossible failed: "
            + "IllegalArgumentException not thrown when pointsPossible is zero");
        return false;
      }
      // 8. Test validity of studentAnswerIndex setter
      question.setStudentAnswerIndex(2);
      // if the studentAnswerIndex is not 2
      if (question.getStudentAnswerIndex() != 2) {
        System.out.println(
            "MultipleChoiceQuestion setStudentAnswerIndex failed: studentAnswerIndex is incorrect");
        return false;
      }
      // - makes sure IndexOutOfBoundsException is thrown when studentAnswerIndex is out of range
      // 0 <= studentAnswerIndex < answers.length inclusive
      try { // making a new MultipleChoiceQuestion object with a studentAnswerIndex that is out of
            // range
        question.setStudentAnswerIndex(4);
        System.out.println("MultipleChoiceQuestion setStudentAnswerIndex failed: "
            + "IndexOutOfBoundsException not thrown when studentAnswerIndex is out of range");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected
      } catch (Exception e) {
        System.out.println("MultipleChoiceQuestion setStudentAnswerIndex failed: "
            + "IndexOutOfBoundsException not thrown when studentAnswerIndex is out of range");
        return false;
      }

      //////////////////////////////////////// OTHER /////////////////////////////////////////
      // 9. Test validity of isCorrect() method
      // - correct answer
      question.setStudentAnswerIndex(1);
      if (!question.isCorrect()) {
        System.out.println("MultipleChoiceQuestion isCorrect failed: isCorrect is incorrect");
        return false;
      }
      // - incorrect answer
      question.setStudentAnswerIndex(0);
      if (question.isCorrect()) {
        System.out.println("MultipleChoiceQuestion isCorrect failed: isCorrect is incorrect");
        return false;
      }

      // 10. Test validity of toString() method
      String toCompareAgainst = "QUESTION TITLE: " + "\"" + "Capitals of France" + "\"" + "\n"
          + "Question:\n" + "What is the capital of France?" + "\n" + "Available Answers:\n"
          + "1. Paris\n2. Lyon\n3. Marseille\n4. Bordeaux";

      if (!question.toString().equals(toCompareAgainst)) {
        System.out.println("MultipleChoiceQuestion toString failed: toString is incorrect");
        return false;
      }

      // 11. Test validity of equals() method

      String[] equalsChoices = {"A", "B", "C", "D"};
      // create a new question
      MultipleChoiceQuestion equalsQ1 =
          new MultipleChoiceQuestion("node1", "question", equalsChoices, 0, 1);
      // create an identical question
      MultipleChoiceQuestion equalsQ2 =
          new MultipleChoiceQuestion("node1", "question", equalsChoices, 0, 1);

      // - same object
      if (!equalsQ1.equals(equalsQ2)) {
        System.out.println(
            "MultipleChoiceQuestion equals failed: equals is incorrect when comparing same object");
        return false;
      }
      // - different object

      if (!equalsQ1.equals(equalsQ2)) {
        System.out.println(
            "MultipleChoiceQuestion equals failed: equals is incorrect when same same but different object");
        return false;
      }

      // 12. Test validity of copy() method
      MultipleChoiceQuestion copiedQuestion = question.copy();
      if (!question.equals(copiedQuestion)) {
        System.out.println("MultipleChoiceQuestion copy failed: copy is incorrect");
        return false;
      }

    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // test passed
  }

  /**
   * This method test and make use of the LinkedNode constructor, an accessor (getter) method, and a
   * mutator (setter) method defined in the LinkedNode class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode() {

    try {
      // creates LinkedNode objects to test
      LinkedNode<String> node1 = new LinkedNode<String>("node1");
      LinkedNode<String> node2 = new LinkedNode<String>("node2");
      LinkedNode<String> node3 = new LinkedNode<String>("node3");
      node1.setNext(node2); // tests setNext() method
      node2.setNext(node3); // node1 -> node2 -> node3

      // tests getNext() and setNext() method
      if (!node1.getNext().equals(node2)) { // if node1's next is not node2
        System.out.println("testLinkedNode() failed: node1.getNext() should return node2");
        return false;
      }

      if (!node2.getNext().equals(node3)) { // if node2's next is not node3
        System.out.println("testLinkedNode() failed: node2.getNext() should return node3");
        return false;
      }

      if (node3.getNext() != null) { // if node3's next is not null
        System.out.println("testLinkedNode() failed: node3.getNext() should return null");
        return false;
      }
      // tests getData() method
      if (!node1.getData().equals("node1")) {
        System.out.println("testLinkedNode() failed: node1.getData() should return \"node1\"");
        return false;
      }

      // tests toString() method
      if (!node1.toString().equals("node1->")) {
        System.out.println("testLinkedNode() failed: node1.toString() should return \"node1->\"");
        return false;
      }
      if (!node3.toString().equals("node3")) {
        System.out.println("testLinkedNode() failed: node3.toString() should return \"node3\"");
        return false;
      }

    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // test passed
  }

  /**
   * This method checks for the correctness of CorrectQuestionsIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCorrectQuestionsIterator() {
    try {
      // 1. Testing on an empty list
      {
        // creates a new QuestionList object
        ListQuizzer questionList = new ListQuizzer();
        questionList.switchMode(ListingMode.CORRECT);
        Iterator<MultipleChoiceQuestion> correctQuestionsIterator = questionList.iterator();

        // tests hasNext() method
        if (correctQuestionsIterator.hasNext()) {
          System.out
              .println("testCorrectQuestionsIterator() failed: hasNext() should return false");
          return false;
        }

        // tests next() method
        try {
          correctQuestionsIterator.next();
          System.out.println(
              "testCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out.println(
              "testCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        }
      }
      // 2. Testing on a list with only incorrect answers
      {
        ListQuizzer questionList = new ListQuizzer();
        questionList.switchMode(ListingMode.CORRECT);
        Iterator<MultipleChoiceQuestion> correctQuestionsIterator = questionList.iterator();

        // adds 3 questions to the list
        String[] choices = {"A", "B", "C", "D"};
        MultipleChoiceQuestion question1 =
            new MultipleChoiceQuestion("node1", "question", choices, 0, 1);
        MultipleChoiceQuestion question2 =
            new MultipleChoiceQuestion("node2", "question", choices, 0, 1);
        MultipleChoiceQuestion question3 =
            new MultipleChoiceQuestion("node3", "question", choices, 0, 1);

        questionList.add(0, question1);
        questionList.add(1, question2);
        questionList.add(2, question3);

        question1.setStudentAnswerIndex(2);
        question2.setStudentAnswerIndex(2);
        question3.setStudentAnswerIndex(2);

        // enhanced for loop to test hasNext() and next() method
        for (MultipleChoiceQuestion question : questionList) {
          System.out
              .println("testCorrectQuestionsIterator() failed: hasNext() should return false");
          return false;
        }

        // tests next() method
        try {
          correctQuestionsIterator.next();
          System.out.println(
              "testCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out.println(
              "testCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        }

      }
      // Now, let's the the same thing but to a list with some correct answers
      {
        ListQuizzer questionList = new ListQuizzer();
        questionList.switchMode(ListingMode.CORRECT);
        Iterator<MultipleChoiceQuestion> correctQuestionsIterator = questionList.iterator();

        // adds 3 questions to the list
        String[] choices = {"A", "B", "C", "D"};
        MultipleChoiceQuestion question1 =
            new MultipleChoiceQuestion("node1", "question", choices, 0, 1);
        MultipleChoiceQuestion question2 =
            new MultipleChoiceQuestion("node2", "question", choices, 0, 1);
        MultipleChoiceQuestion question3 =
            new MultipleChoiceQuestion("node3", "question", choices, 0, 1);

        questionList.add(0, question1);
        questionList.add(1, question2);
        questionList.add(2, question3);

        question1.setStudentAnswerIndex(2); // incorrect
        question2.setStudentAnswerIndex(2); // incorrect
        question3.setStudentAnswerIndex(0); // correct

        // enhanced for loop to test hasNext() and next() methods
        for (MultipleChoiceQuestion question : questionList) { // should only iterate once
          if (question != question3) { // if the question is not question3
            System.out
                .println("testCorrectQuestionsIterator() failed: hasNext() should return false");
            return false; // test failed
          }
        }

        // tests next() method
        try {
          correctQuestionsIterator.next(); // should return question3
          System.out.println(
              "testCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out.println(
              "testCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        }
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }
    return true; // test passed

  }

  /**
   * This method checks for the correctness of IncorrectQuestionsIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInCorrectQuestionsIterator() {
    try {
      // Tests an empty list
      {
        // creates a new QuestionList object
        ListQuizzer questionList = new ListQuizzer(); // creates a new QuestionList object
        questionList.switchMode(ListingMode.INCORRECT); // switches to incorrect mode
        // creates a new iterator
        Iterator<MultipleChoiceQuestion> incorrectQuestionsIterator = questionList.iterator();

        // tests hasNext() method
        if (incorrectQuestionsIterator.hasNext()) { // should return false because the list is empty
          System.out
              .println("testInCorrectQuestionsIterator() failed: hasNext() should return false");
          return false;
        }

        // tests next() method
        try {
          incorrectQuestionsIterator.next();
          System.out.println(
              "testInCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out.println(
              "testInCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        }
      }
      // Tests a list with only correct answers
      {
        ListQuizzer questionList = new ListQuizzer(); // creates a new QuestionList object
        questionList.switchMode(ListingMode.INCORRECT); // switches to incorrect mode
        // creates a new iterator
        Iterator<MultipleChoiceQuestion> incorrectQuestionsIterator = questionList.iterator();

        // adds 3 questions to the list
        String[] choices = {"A", "B", "C", "D"};
        MultipleChoiceQuestion question1 =
            new MultipleChoiceQuestion("node1", "question", choices, 0, 1);
        MultipleChoiceQuestion question2 =
            new MultipleChoiceQuestion("node2", "question", choices, 0, 1);
        MultipleChoiceQuestion question3 =
            new MultipleChoiceQuestion("node3", "question", choices, 0, 1);

        questionList.add(0, question1);
        questionList.add(1, question2);
        questionList.add(2, question3);

        question1.setStudentAnswerIndex(0); // correct
        question2.setStudentAnswerIndex(0); // correct
        question3.setStudentAnswerIndex(0); // correct

        // enhanced for loop to test hasNext() and next() method
        for (MultipleChoiceQuestion question : questionList) { // should not iterate
          System.out
              .println("testInCorrectQuestionsIterator() failed: hasNext() should return false");
          return false;
        }

        // tests next() method
        try {
          incorrectQuestionsIterator.next();
          System.out.println(
              "testInCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out.println(
              "testInCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        }
      }
      // Tests a list with some incorrect answers
      {
        ListQuizzer questionList = new ListQuizzer(); // creates a new QuestionList object
        questionList.switchMode(ListingMode.INCORRECT); // switches to incorrect mode
        // creates a new iterator
        Iterator<MultipleChoiceQuestion> incorrectQuestionsIterator = questionList.iterator();

        // adds 3 questions to the list
        String[] choices = {"A", "B", "C", "D"};
        MultipleChoiceQuestion question1 =
            new MultipleChoiceQuestion("node1", "question", choices, 0, 1);
        MultipleChoiceQuestion question2 =
            new MultipleChoiceQuestion("node2", "question", choices, 0, 1);
        MultipleChoiceQuestion question3 =
            new MultipleChoiceQuestion("node3", "question", choices, 0, 1);

        questionList.add(0, question1);
        questionList.add(1, question2);
        questionList.add(2, question3);

        question1.setStudentAnswerIndex(2); // incorrect
        question2.setStudentAnswerIndex(2); // incorrect
        question3.setStudentAnswerIndex(0); // correct

        // enhanced for loop to test hasNext() and next() methods
        for (MultipleChoiceQuestion question : questionList) { // should iterate once
          if (question != question1 && question != question2) { // if the question is not question1
                                                                // or
                                                                // question2
            System.out
                .println("testInCorrectQuestionsIterator() failed: hasNext() should return false");
            return false;
          }
        }

        // tests next() method
        try {
          incorrectQuestionsIterator.next(); // should throw NoSuchElementException
          System.out.println(
              "testInCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out.println(
              "testInCorrectQuestionsIterator() failed: next() should throw NoSuchElementException");
          return false;
        }
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // test passed
  }

  /**
   * This method checks for the correctness of QuizQuestionsIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testQuizQuestionsIterator() {
    try {
      // 1. Testing on an empty list
      {
        // i - create an empty list
        ListQuizzer quiz = new ListQuizzer(); // creates a new QuestionList object
        quiz.switchMode(ListingMode.ALL); // switches to all mode
        Iterator<MultipleChoiceQuestion> iterator = quiz.iterator(); // creates a new iterator

        // ii - test hasNext() method
        if (iterator.hasNext()) { // should return false because the list is empty
          System.out.println("QuizQuestionsIterator hasNext failed: hasNext is incorrect");
          return false;
        }

        // iii - test next() method
        try { // should throw NoSuchElementException
          iterator.next();
          System.out.println("QuizQuestionsIterator next failed: next is incorrect");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out
              .println("QuizQuestionsIterator next failed: NoSuchElementException not thrown");
          return false;
        }
      }
      // 2. Testing on a list with a single item
      {
        // i - create a list with a single item
        ListQuizzer quiz = new ListQuizzer(); // creates a new QuestionList object
        quiz.switchMode(ListingMode.ALL); // switches to all mode
        quiz.add(0, new MultipleChoiceQuestion("node1", "question",
            new String[] {"A", "B", "C", "D"}, 0, 1));
        Iterator<MultipleChoiceQuestion> iterator = quiz.iterator(); // creates a new iterator

        // ii - test hasNext() method
        if (!iterator.hasNext()) { // should return true because the list has a single item
          System.out.println("QuizQuestionsIterator hasNext failed: hasNext is incorrect");
          return false;
        }

        // iii - test next() method
        try {
          iterator.next(); // should not throw NoSuchElementException
        } catch (Exception e) {
          System.out.println("QuizQuestionsIterator next failed: next is incorrect");
          return false;
        }

        // iv - test hasNext() method
        if (iterator.hasNext()) { // should return false because the list has a single item
          System.out.println("QuizQuestionsIterator hasNext failed: hasNext is incorrect");
          return false;
        }

        // v - test next() method
        try { // should throw NoSuchElementException
          iterator.next();
          System.out.println("QuizQuestionsIterator next failed: next is incorrect");
          return false;
        } catch (NoSuchElementException e) {
          // expected
        } catch (Exception e) {
          System.out
              .println("QuizQuestionsIterator next failed: NoSuchElementException not thrown");
          return false;
        }
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }
    return true; // test passed
  }

  /**
   * Tester for ListQuizzer.addLast() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddLast() {

    try {
      // 1. Test validity of addLast() method
      ListQuizzer list = new ListQuizzer(); // creates a new QuestionList object
      String[] choices = {"A", "B", "C", "D"}; // creates a new String array

      // - add to empty list
      MultipleChoiceQuestion a = new MultipleChoiceQuestion("node1", "q", choices, 0, 1);
      list.addLast(a); // adds node1 to the list

      if (!list.getLast().equals(a)) { // if first node is not node1
        System.out
            .println("ListQuizzer addLast failed: addLast is incorrect when adding to empty list");
        return false;
      }
      // - add to non-empty list
      MultipleChoiceQuestion b = new MultipleChoiceQuestion("node2", "q", choices, 0, 1);
      list.addLast(b); // adds node2 to the end of the list

      if (!list.getLast().equals(b)) { // if last node is not node 2
        System.out.println(
            "ListQuizzer addLast failed: addLast is incorrect when adding to non-empty list");
        return false;
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Tester for ListQuizzer.removeLast() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveLast() {

    try {
      // 1. Test validity of removeLast() method
      ListQuizzer list = new ListQuizzer();
      String[] choices = {"A", "B", "C", "D"};

      // - remove from empty list
      try { // should throw NoSuchElementException
        list.removeLast();
        System.out.println(
            "ListQuizzer removeLast failed: removeLast is incorrect when removing from empty list");
        return false;
      } catch (NoSuchElementException e) {
        // do nothing
      } catch (Exception e) {
        System.out.println("ListQuizzer removeLast failed: encountered an unexpected exception");
        return false;
      }

      // - remove from non-empty list
      MultipleChoiceQuestion a = new MultipleChoiceQuestion("node1", "q", choices, 0, 1);
      MultipleChoiceQuestion b = new MultipleChoiceQuestion("node2", "q", choices, 0, 1);
      list.addLast(a); // adds node1 to the list
      list.addLast(b); // adds node2 to the list after node1
      list.removeLast(); // removes node2 from the list

      if (!list.getLast().equals(a)) { // if list last is not node1
        System.out.println(
            "ListQuizzer removeLast failed: removeLast is incorrect when removing from non-empty list");
        return false;
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Tester for ListQuizzer.removeFirst() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveFirst() {

    try {
      // 1. Test validity of removeFirst() method
      ListQuizzer list = new ListQuizzer();
      String[] choices = {"A", "B", "C", "D"};

      // - remove from empty list
      try {
        list.removeFirst();
        System.out.println(
            "ListQuizzer removeFirst failed: removeFirst is incorrect when removing from empty list");
        return false;
      } catch (NoSuchElementException e) {
        // do nothing
      } catch (Exception e) {
        System.out.println("ListQuizzer removeFirst failed: encountered an unexpected exception");
        return false;
      }

      // - remove from non-empty list
      MultipleChoiceQuestion a = new MultipleChoiceQuestion("node1", "q", choices, 0, 1);
      MultipleChoiceQuestion b = new MultipleChoiceQuestion("node2", "q", choices, 0, 1);
      list.addLast(a);
      list.addLast(b);
      list.removeFirst();

      if (!list.get(0).equals(b)) { // if first node is not node2
        System.out.println(
            "ListQuizzer removeFirst failed: removeFirst is incorrect when removing from non-empty list");
        return false;
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Tester for ListQuizzer.remove() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove() {

    try {
      // 1. Test validity of remove() method
      ListQuizzer list = new ListQuizzer();
      String[] choices = {"A", "B", "C", "D"};

      // - remove from empty list
      try {
        list.remove(0);
        System.out.println(
            "ListQuizzer remove failed: remove is incorrect when removing from empty list");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // do nothing
      } catch (Exception e) {
        System.out.println(
            "ListQuizzer remove failed: encountered an unexpected exception when removing from empty list");
        return false;
      }

      // - remove given bad index
      MultipleChoiceQuestion a = new MultipleChoiceQuestion("node1", "q", choices, 0, 1);
      MultipleChoiceQuestion b = new MultipleChoiceQuestion("node2", "q", choices, 0, 1);
      list.addLast(a); // adds node1 to the list
      list.addLast(b); // adds node2 to the list after node1
      try {
        list.remove(-1); // tries to remove a node at an invalid index
        System.out.println(
            "ListQuizzer remove failed: remove is incorrect when removing given bad index");
        return false;
      } catch (IndexOutOfBoundsException e) { // should throw IndexOutOfBoundsException
        // do nothing, expected behavior
      } catch (Exception e) {
        System.out.println(
            "ListQuizzer remove failed: encountered an unexpected exception when removing given bad index");
        return false;
      }

      // - remove from the start of a list
      list.removeFirst();

      if (!list.getFirst().equals(b)) { // if list is not node2
        System.out.println(
            "ListQuizzer remove failed: remove is incorrect when removing from the start of a list");
        return false;
      }

      // - remove from the middle of a list
      MultipleChoiceQuestion c = new MultipleChoiceQuestion("node3", "q", choices, 0, 1);
      MultipleChoiceQuestion d = new MultipleChoiceQuestion("node4", "q", choices, 0, 1);
      list.addLast(c); // adds node3 to the list
      list.addLast(d); // adds node4 to the list after node3
      list.remove(1); // removes node3 from the list

      if (!list.get(1).equals(d)) { // if list is not node4
        System.out.println(
            "ListQuizzer remove failed: remove is incorrect when removing from the middle of a list");
        return false;
      }

      // - remove from the end of a list
      list.remove(1);

      if (!list.getLast().equals(b)) { // if list is not node2
        System.out.println(
            "ListQuizzer remove failed: remove is incorrect when removing from the end of a list");
        return false;
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Tester for ListQuizzer.add() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAdd() {

    try {
      // 1. Test validity of add() method
      ListQuizzer list = new ListQuizzer();
      String[] choices = {"A", "B", "C", "D"};

      // - add to empty list
      MultipleChoiceQuestion a = new MultipleChoiceQuestion("node1", "q", choices, 0, 1);
      list.add(0, a); // adds node1 to the list at index 0

      if (!list.getFirst().equals(a)) { // if list is not node1
        System.out.println("ListQuizzer add failed: add is incorrect when adding to empty list");
        return false;
      }

      // - add to the start of a list
      MultipleChoiceQuestion b = new MultipleChoiceQuestion("node2", "q", choices, 0, 1);
      list.add(0, b); // adds node2 to the list at index 0

      if (!list.getFirst().equals(b)) { // if list is not node2->node1
        System.out
            .println("ListQuizzer add failed: add is incorrect when adding to the start of a list");
        return false;
      }

      // - add to the middle of a list
      MultipleChoiceQuestion c = new MultipleChoiceQuestion("node3", "q", choices, 0, 1);
      list.add(1, c); // adds node3 to the list at index 1

      if (!list.get(1).equals(c)) { // if list is not node2->node3->node1
        System.out.println(
            "ListQuizzer add failed: add is incorrect when adding to the middle of a list");
        return false;
      }

      // - add to the end of a list
      MultipleChoiceQuestion d = new MultipleChoiceQuestion("node4", "q", choices, 0, 1);
      list.add(3, d);

      if (!list.getLast().equals(d)) { // if list is not node2->node3->node1->node4
        System.out
            .println("ListQuizzer add failed: add is incorrect when adding to the end of a list");
        return false;
      }

      // - add given bad index
      try {
        list.add(-1, new MultipleChoiceQuestion("node5", "q", choices, 0, 1));
        System.out.println("ListQuizzer add failed: add is incorrect when adding given bad index");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // do nothing
      } catch (Exception e) {
        System.out.println(
            "ListQuizzer add failed: encountered an unexpected exception when adding bad index");
        return false;
      }

      // - add given null data
      try {
        list.add(0, null);
        System.out.println("ListQuizzer add failed: add is incorrect when adding given null data");
        return false;
      } catch (NullPointerException e) {
        // do nothing
      } catch (Exception e) {
        System.out.println(
            "ListQuizzer add failed: encountered an unexpected exception when adding null data");
        return false;
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Tester for ListQuizzer.addFirst() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddFirst() {

    try {
      // 1. Test validity of addFirst() method
      ListQuizzer list = new ListQuizzer();
      String[] choices = {"A", "B", "C", "D"};

      // - add to empty list
      MultipleChoiceQuestion a = new MultipleChoiceQuestion("node1", "q", choices, 0, 1);
      list.addFirst(a); // adds node1 to the list

      if (!list.getFirst().equals(a)) { // if list is not node1
        System.out.println(
            "ListQuizzer addFirst failed: addFirst is incorrect when adding to empty list");
        return false;
      }

      // - add to the start of a list
      MultipleChoiceQuestion b = new MultipleChoiceQuestion("node2", "q", choices, 0, 1);
      list.addFirst(b); // adds node2 to the list

      if (!list.getFirst().equals(b)) { // if list is not node2->node1
        System.out.println(
            "ListQuizzer addFirst failed: addFirst is incorrect when adding to the start of a list");
        return false;
      }

      // - given null data
      try { // should throw NullPointerException
        list.addFirst(null);
        System.out.println(
            "ListQuizzer addFirst failed: addFirst is incorrect when adding given null data");
        return false;
      } catch (NullPointerException e) {
        // do nothing
      } catch (Exception e) {
        System.out.println("ListQuizzer addFirst failed: encountered an unexpected exception");
        return false;
      }
    } catch (Exception e) { // catch any unexpected exceptions due to broken implementations.
      System.out.println("Incorrect implementation detected! " + e);
      return false;
    }

    return true; // all tests passed
  }
}
