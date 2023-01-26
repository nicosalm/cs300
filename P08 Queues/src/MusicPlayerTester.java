//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: SongQueue
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

public class MusicPlayerTester {

  public static boolean testSongConstructor() {
    try {
      new Song("title", "artist", "invalidfile");
      return false;
    } catch (IllegalArgumentException e) {
      // expected
    } catch (Exception e) {
      return false;
    }
    try {
      Song song = new Song("exactly-what-you-think", "Rick Astley", "audio/exactly-what-you-think.mid");
      if (!song.getTitle().equals("exactly-what-you-think") || !song.getArtist().equals("Rick Astley")) {
        return false;
      }
      AudioUtility audio = new AudioUtility("audio/exactly-what-you-think.mid");
      int dur = audio.getClipLength();
      if (!song.toString()
          .equals("\"exactly-what-you-think\" (" + dur / 60 + ":" + dur % 60 + ") by Rick Astley")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean testSongPlayback() {

    try {
      String validPath = "audio" + File.separator + "1.mid";
      Song s = new Song("title", "artist", validPath);

      // funtionality 1: play a song
      s.play();
      try {
        Thread.sleep(1000); // plays for 1 second
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (!s.isPlaying()) { // song should still be playing
        System.out.println("Song play() failed");
        return false;
      }

      // functionality 2: stop a song
      s.stop();
      if (s.isPlaying()) { // song should not be playing
        System.out.println("Song stop() failed");
        return false;
      }

      return true; // all tests passed
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean testSongNode() {

    try {
      String validPath = "audio" + File.separator + "1.mid";

      // case 1: null data
      try {
        SongNode n = new SongNode(null);
        System.out.println("SongNode constructor failed case 1");
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      }
      // case 2: valid data, null next
      try {
        SongNode n = new SongNode(new Song("title", "artist", validPath));
      } catch (IllegalArgumentException e) {
        System.out.println("SongNode constructor failed case 2");
        return false;
      }
      // case 3: valid data, valid next
      SongNode n = new SongNode(new Song("title", "artist", validPath));
      SongNode n2 = new SongNode(new Song("title", "artist", validPath), n);
      if (n2.getNext() != n) {
        System.out.println("SongNode constructor failed case 3");
        return false;
      }

      return true; // all tests passed
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean testEnqueue() {

    try {
      String validPath = "audio" + File.separator + "1.mid";

      // create a new Playlist
      Playlist p = new Playlist();

      // case 1: null song
      try {
        p.enqueue(null);
        System.out.println("Playlist enqueue() failed case 1");
        return false;
      } catch (IllegalArgumentException e) {
        // expected
      }

      // case 2: valid song (empty playlist)
      Song s = new Song("title", "artist", validPath);
      p.enqueue(s);
      if (p.size() != 1) {
        System.out.println("Playlist enqueue() failed case 2");
        return false;
      }

      // case 3: valid song (non-empty playlist)
      Song s2 = new Song("title2", "artist2", validPath);
      p.enqueue(s2);
      if (p.size() != 2 || p.peek() != s) {
        System.out.println("Playlist enqueue() failed case 3");
        return false;
      }

      return true; // all tests passed
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean testDequeue() {

    try {
      String validPath = "audio" + File.separator + "1.mid";

      // create a new Playlist
      Playlist p = new Playlist();

      // case 1: empty playlist
      if (p.dequeue() != null) {
        System.out.println("Playlist dequeue() failed case 1");
        return false;
      }

      // case 2: non-empty playlist
      Song s = new Song("title", "artist", validPath);
      p.enqueue(s);
      Song s2 = new Song("title2", "artist2", validPath);
      p.enqueue(s2);
      Song s3 = new Song("title3", "artist3", validPath);
      p.enqueue(s3);
      if (p.dequeue() != s) {
        System.out.println("Playlist dequeue() failed case 2");
        return false;
      }
      if (p.size() != 2) {
        System.out.println("Playlist dequeue() failed case 2");
        return false;
      }
      if (p.peek() != s2) {
        System.out.println("Playlist dequeue() failed case 2");
        return false;
      }

      // case 3: remove the last element, set head and tail to null
      p.dequeue();
      p.dequeue();
      if (p.size() != 0 || p.peek() != null) {
        System.out.println("Playlist dequeue() failed case 3");
        return false;
      }

      return true; // all tests passed
    } catch (Exception e) {
      return false;
    }
  }

  public static void main(String[] args) {
    // test all methods
    int numPassed = 0;
    if (testSongConstructor()) {
      numPassed++;
      System.out.println("Song constructor passed");
    } else {
      System.out.println("Song constructor failed");
    }
    if (testSongPlayback()) {
      numPassed++;
      System.out.println("Song playback passed");
    } else {
      System.out.println("Song playback failed");
    }
    if (testSongNode()) {
      numPassed++;
      System.out.println("SongNode constructor passed");
    } else {
      System.out.println("SongNode constructor failed");
    }
    if (testEnqueue()) {
      numPassed++;
      System.out.println("Playlist enqueue() passed");
    } else {
      System.out.println("Playlist enqueue() failed");
    }
    if (testDequeue()) {
      numPassed++;
      System.out.println("Playlist dequeue() passed");
    } else {
      System.out.println("Playlist dequeue() failed");
    }

  }
}
