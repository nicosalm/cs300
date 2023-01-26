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
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in an
 * interactive console method. This music player can load playlists of music or add individual song
 * files to the queue.
 *
 * @author Nico
 */
public class MusicPlayer300 {

  /** The current playlist of Songs */
  private Playlist playlist;

  /** Whether the current playback mode should be filtered by artist; false by default */
  private boolean filterPlay;

  /** The artist to play if filterPlay is true; should be null otherwise */
  private String filterArtist;

  /** Creates a new MusicPlayer300 with an empty playlist */
  public MusicPlayer300() {
    playlist = new Playlist();
    filterPlay = false;
    filterArtist = null;
  }

  /** Stops any song that is playing and clears out the playlist */
  public void clear() {
    for (int i = 0; i < playlist.size(); i++) { // iterate through the playlist
      playlist.dequeue(); // dequeue each song
    }
    filterPlay = false; // reset filterPlay
    filterArtist = null; // reset filterArtist
  }

  /**
   * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded.
   * Note that filenames in the provided files do NOT include the audio directory, and will need
   * that added before they are loaded. Print "Loading" and the song's title in quotes before you
   * begin loading a song, and an "X" if the load was unsuccessful for any reason.
   *
   * @param file the File object to load
   * @throws FileNotFoundException if the playlist file cannot be loaded
   */
  public void loadPlaylist(File file) throws FileNotFoundException {
    // checks if something is jank with the file and throws an exception if so
    if (file == null || !file.exists() || !file.getPath().contains(".txt")) {
      throw new FileNotFoundException("File cannot be loaded");
    }
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine()) { // while there are still lines in the file
      String songString = sc.nextLine();
      // a formatted song will have a title, artist and filepath separated by commas. If it doesn't
      // have all three, it's not a valid song. An X will be printed for each invalid song.
      if (songString.split(",").length != 3) {
        System.out.println("X");
        continue;
      }
      String title = songString.split(",")[0]; // first part
      String artist = songString.split(",")[1]; // second part
      String filepath = "audio" + File.separator + songString.split(",")[2]; // third part
      
      try {
        loadOneSong(title, artist, filepath); // try to load the song
      } catch (IllegalArgumentException e) { // if it fails, print an X
        System.out.println("X");
        continue; // and move on to the next song
      }
      System.out.println("Loading \"" + title + "\""); // prints the loading message
    }
    sc.close(); // close the scanner!!!!!
  }

  /**
   * Loads a single song to the end of the playlist given the title, artist, and filepath. Filepaths
   * for P08 must refer to files in the audio directory.
   *
   * @param title    the title of the song
   * @param artist   the artist of this song
   * @param filepath the full relative path to the song file, begins with the "audio" directory for
   *                 P08
   * @throws IllegalArgumentException if the song file cannot be read
   */
  public void loadOneSong(String title, String artist, String filepath) {
    // exception handled in Song constructor
    Song song = new Song(title, artist, filepath);
    playlist.enqueue(song); // adds the song to the playlist
  }

  /**
   * Provides a string representation of all songs in the current playlist
   *
   * @return a string representation of all songs in the current playlist
   */
  public String printPlaylist() {
    return playlist.toString(); // calls the toString method of the playlist
  }

  /**
   * Creates and returns the menu of options for the interactive console program.
   *
   * @return the formatted menu String
   */
  public String getMenu() {
    return "Enter one of the following options:\n"
        + "[A <filename>] to enqueue a new song file to the end of this playlist\n"
        + "[F <filename>] to load a new playlist from the given file\n"
        + "[L] to list all songs in the current playlist\n"
        + "[P] to start playing ALL songs in the playlist from the beginning\n"
        + "[P -t <Title>] to play all songs in the playlist starting from <Title>\n"
        + "[P -a <Artist>] to start playing only the songs in the playlist by Artist\n"
        + "[N] to play the next song\n" + "[Q] to stop playing music and quit the program";
  }
  
  /**
   * Stops playback of the current song (if one is playing) and advances to the next song in the
   * playlist.
   *
   * @throws IllegalStateException if the playlist is null or empty, or becomes empty at any time
   *                               during this method
   */
  public void playNextSong() throws IllegalStateException {

    // error case 1 - playlist is null or empty
    if (playlist == null || playlist.isEmpty()) {
      throw new IllegalStateException("Playlist is null or empty");
    }

    // special case 1 - song is already playing
    if (playlist.peek().isPlaying()) {
      playlist.peek().stop();
    }

    // general case 1 - filterPlay is true
    if (filterPlay) {
      playlist.dequeue();
      clearIfEmpty(); // check if empty
      while (playlist.peek() != null) { // while the playlist is not empty
        if (playlist.peek().getArtist().equals(filterArtist)) { // if the artist matches
          break; // break out of the loop
        } else { // otherwise
          playlist.dequeue(); // dequeue the song
          clearIfEmpty();
        }
      }
      // general case 2 - filterPlay is false
    } else {
      playlist.dequeue(); // dequeue the song
      clearIfEmpty();
    }
    clearIfEmpty();
    playlist.peek().play(); // play the next song

  }

  /* 
   * Helper method to clear the playlist if it becomes empty
   * 
   * @throws IllegalStateException if the playlist is null or empty, or becomes empty at any time
   */
  private void clearIfEmpty() {
    if (playlist.isEmpty()) { // if the playlist is empty
      clear(); // clear the playlist
      throw new IllegalStateException("Playlist is empty");
    }
  }

  /**
   * ß Commands - [A <filename>] to enqueue a new song to the end of this playlist - [F <filename>]
   * to load a new playlist from the given file - [L] to list all songs in the current playlist -
   * [P] to start playing ALL songs in the playlist from the beginning - [P -t <Title>] to play all
   * songs in the playlist starting from <Title> - [P -a <Artist>] to start playing only the songs
   * in the playlist by Artist - [N] to play the next song - [Q] to stop playing music and quit the
   * program - Anything else, "I don't know how to do that." is printed
   */
  public void runMusicPlayer300(Scanner in) {

    // ~ The stuff we need to set up and use throughout ~
    Scanner sc = in; // a scanner
    File file; // the file to load
    boolean playing = true; // whether or not the playlist is playing

    while (playing) {
      System.out.println(getMenu()); // the menu
      System.out.print("> "); // the prompt

      // input filtering
      String input = sc.nextLine().trim(); // the input
      // split the input into an array of 3 strings
      String[] inputArray = input.split(" ", 3);
      String command = inputArray[0]; // the command

      String mod = ""; // the modifier
      String arg = "";

      // if the input has a modifier, it will be split into the command and the modifier
      if (input.split(" ").length > 1) {
        mod = inputArray[1]; // the modifier
      }
      if (input.split(" ").length > 2) {
        arg = inputArray[2]; // the argument
      }

      switch (command) {
        // A - enqueue a new song to the end of this playlist
        case "A":
          System.out.print("Title: ");
          String title = sc.nextLine().trim(); // the title
          System.out.print("\nArtist: ");
          String artist = sc.nextLine().trim(); // the artist
          System.out.print("\nFilepath: ");
          String filepath = sc.nextLine().trim(); // the filepath
          try {
            loadOneSong(title, artist, filepath); // load the song with the fields
          } catch (IllegalArgumentException e) {
            System.out.println("Unable to load that song");
            continue;
          }
          break;

        // F - load a new playlist from the given file
        case "F":
          file = new File(mod); // the file to load
          try {
            loadPlaylist(file); // load the playlist
          } catch (FileNotFoundException e) {
            System.out.println("File not found");
          }
          break;

        // L - list all songs in the current playlist
        case "L":
          System.out.println(printPlaylist()); // print the playlist, nice and neat
          break;
        case "P":
          switch (mod) {
            case "-t": // play all songs in the playlist starting from <Title>
              // loop through the playlist until the song with the title is found
              while (playlist.peek() != null) {
                if (playlist.peek().getTitle().equals(arg)) { // if the title matches
                  break; // break out of the loop
                } else {
                  playlist.dequeue(); // otherwise, dequeue the song
                }
              }
              break;
            case "-a": // start playing only the songs in the playlist by Artist
              filterPlay = true; // set filterPlay to true
              filterArtist = arg; // set the filterArtist to the argument

              // loop through the playlist until the song with the artist is found
              while (playlist.peek() != null) {
                if (playlist.peek().getArtist().equals(arg)) { // if the artist matches
                  break; // break out of the loop
                } else {
                  playlist.dequeue(); // otherwise, dequeue the song
                }
              }
              break;
            default: // start playing ALL songs in the playlist from the beginning
              break;
          }
          // for all cases, play the first song in the playlist
          playlist.dequeue().play();
          break;

        // N - play the next song
        case "N":
          // if a song is playing, stop it
          try {
            if (playlist.peek().isPlaying()) {
              playlist.peek().stop();
            }
            // play the next song
            playlist.dequeue().play();
            break;
          }
          // if the playlist is empty, clear the playlist
          catch (NullPointerException e) {
            clear();
            System.out.println("Playlist is empty");
            break;
          }

        // Q - stop playing music and quit the program
        case "Q":
          System.out.println("Goodbye!");
          playing = false;
          break;
        default:
          // anything else
          System.out.println("I don't know how to do that.");
          break;
      }
    }
  }
}
