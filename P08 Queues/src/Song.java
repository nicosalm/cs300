import java.io.IOException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongQueue
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

/**
 * A representation of a single Song. Interfaces with the provided AudioUtility class, which uses
 * the javax.sound.sampled package to play audio to your computer's audio output device
 *
 * @author Nico
 */
public class Song {
  /** The title of this song */
  private String title;

  /** The artist of this song */
  private String artist;

  /** The duration of this song in number of seconds */
  private int duration;

  /** This song's AudioUtility interface to javax.sound.sampled */
  private AudioUtility audioClip;

  /**
   * Initializes all instance data fields according to the provided values
   *
   * @param title the title of the song, set to empty string if null
   * @param artist the artist of this song, set to empty string if null
   * @param filepath the full relative path to the song file, begins with the "audio" directory for
   *     P08
   * @throws IllegalArgumentException if the song file cannot be read
   */
  public Song(String title, String artist, String filepath) throws IllegalArgumentException {
    
    // if the title or artist is null, set it to an empty string
    this.title = title == null ? "" : title;
    this.artist = artist == null ? "" : artist;

    // initialize the audioClip field
    try {
      audioClip = new AudioUtility(filepath);
    } catch (IOException e) {
      throw new IllegalArgumentException("Song constructor: file cannot be read");
    }

    // initialize the duration field
    this.duration = audioClip.getClipLength();

  }

  /**
   * Tests whether this song is currently playing using the AudioUtility
   *
   * @return true if the song is playing, false otherwise
   */
  public boolean isPlaying() {
    return audioClip.isRunning();
  }

  /**
   * Accessor method for the song's title
   *
   * @return the title of this song
   */
  public String getTitle() {
    return title;
  }

  /**
   * Accessor method for the song's artist
   *
   * @return the artist of this song
   */
  public String getArtist() {
    return artist;
  }

  /**
   * Uses the AudioUtility to start playback of this song, reopening the clip for playback if
   * necessary
   */
  public void play() {
    // check if the song is ready to play
    if (audioClip.isReadyToPlay()) {
      // if it is, play the song
      audioClip.startClip();
    } else {
      // if it isn't, reopen the clip and play the song
      audioClip.reopenClip();
      audioClip.startClip();
    }
    System.out.println("Playing " + this.toString());
  }

  /** Uses the AudioUtility to stop playback of this song */
  public void stop() {
    audioClip.stopClip();
  }

  /**
   * Creates and returns a string representation of this Song, for example: "Africa" (4:16) by Toto
   * The title should be in quotes, the duration should be split out into minutes and seconds
   * (recall it is stored as seconds only!), and the artist should be preceded by the word "by". It
   * is intended for this assignment to leave single-digit seconds represented as 0:6, for example,
   * but if you would like to represent them as 0:06, this is also allowed.
   *
   * @return a formatted string representation of this Song
   */
  @Override
  public String toString() {
    // calculate the minutes and seconds. Add zero padding if necessary
    int minutes = duration / 60;
    int seconds = duration % 60;
    String secondsString = String.format("%02d", seconds);
    
    // return the formatted string
    return "\"" + title + "\" (" + minutes + ":" + secondsString + ") by " + artist;
  }
}
