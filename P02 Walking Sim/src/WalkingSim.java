//////////////// P02 - WalkerSim //////////////////////////
//
// Title:    P02 - Flat Earth Simulator. Just like in real life, when you reach the end you
//           just wrap around and start over.
//
// Course:   CS 300 Fall 2022
//
// Author:   Nico Salm
// Email:    nbsalm@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;
import processing.core.PImage;

public class WalkingSim {

  private static Random randGen; // Random number
  private static int bgColor; // color of background
  private static PImage[] frames; // image frames array
  private static Walker[] walkers; // walkers array


  public static void setup() {
    // setup
    randGen = new Random();
    bgColor = randGen.nextInt();
    frames = new PImage[Walker.NUM_FRAMES];
    for (int i = 0; i < Walker.NUM_FRAMES; i++) {
      frames[i] = Utility.loadImage("images" + File.separator + "walk-" + i + ".png");
    }

    // populating walkers
    walkers = new Walker[8];
    for (int i = 0; i < randGen.nextInt(0, 8) + 1; i++) {
        walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
    }
  }

  public static void draw() {
    Utility.background(bgColor);

    // drawing the walkers
    for (Walker walker : walkers) {
      // if the walker is walking
      if (walker != null && walker.isWalking() && walker.getCurrentFrame() < Walker.NUM_FRAMES - 1) {
        Utility.image(frames[walker.getCurrentFrame() + 1], walker.getPositionX(), walker.getPositionY());
        walker.setPositionX(walker.getPositionX() + 3);
        walker.update();
        if (walker.getPositionX() > 800) { // if the walker has reached the end of the screen
          walker.setPositionX(0); // walker wraps to the beginning
        }
      }
      // if the walker is walking and it's in the last frame
      else if (walker != null && walker.isWalking()) {
        Utility.image(frames[0], walker.getPositionX(), walker.getPositionY());
        walker.update();
        walker.setPositionX(walker.getPositionX() + 3);
        if (walker.getPositionX() % 800 == 0) {
          walker.setPositionX(0);
        }
      }

      // if the walker was just spawned and has not been clicked yet
      else if (walker != null) {
        // sets to default frame
        Utility.image(frames[0], walker.getPositionX(), walker.getPositionY());
      }
    }
  }

  public static boolean isMouseOver(Walker walker) {
    if ( Utility.mouseX() > (walker.getPositionX() - frames[3].width / 2) &&
        Utility.mouseX() < (walker.getPositionX() + frames[3].width / 2) &&
        Utility.mouseY() > (walker.getPositionY() - frames[3].height / 2) &&
        Utility.mouseY() < (walker.getPositionY() + frames[3].height / 2)
        ) {
      return true;
    }
    return false;
  }

  public static void mousePressed() {
    for (Walker walker : walkers) {
      if (walker != null && isMouseOver(walker)) {
        walker.setWalking(true);
        break;
      }
    }
  }
  public static void keyPressed(char key) {
    if (Character.compare(key, 'a') == 0 || Character.compare(key, 'A') == 0) {
      for (int i = 0; i < walkers.length; i++) {
        if (walkers[i] == null) {
          walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
          break;
        }
      }
    }

    if (Character.compare(key, 's') == 0 || Character.compare(key, 'S') == 0) {
      for (Walker walker : walkers) {
        if (walker != null) {
          walker.setWalking(false);
        }
      }
    }
  }

  public static void main(String[] args) {
    Utility.runApplication();
  }
}
