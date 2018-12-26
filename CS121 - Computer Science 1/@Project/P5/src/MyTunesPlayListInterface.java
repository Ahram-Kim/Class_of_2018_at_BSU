import java.util.ArrayList;

/**
 * @author Ahram Kim
 *
 */
public interface MyTunesPlayListInterface
{
	/**
	 * Sets the name of this playlist.
	 * @param name The name.
	 */
	public void setName(String name);

	/**
	 * Returns the name of this playlist.
	 * @return The name.
	 */
	public String getName();

	/**
	 * Returns the song that is currently playing.
	 * @return The song that is currently playing.
	 */
	public Song getPlaying();

	/**
	 * Adds the given song to the end of this playlist.
	 * @param s The song to add.
	 */
	public void addSong(Song s);

	/**
	 * Returns the song with the given index from this playlist, or null
	 * if the index is invalid.
	 * @param index The index of the song to retrieve.
	 * @return The song at index or null if none exists.
	 */
	public Song getSong(int index);
	
	/**
	 * Removes the song with the given index from this playlist, or null
	 * if the index is invalid.
	 * @param index The index of the song to remove.
	 * @return The song at index or null if none exists.
	 */
	public Song removeSong(int index);

	/**
	 * Returns the number of songs in this playlist.
	 * @return The number of songs.
	 */
	public int getNumSongs();

	/**
	 * Returns the total playtime of all the songs in this playlist.
	 * @return The total play time in seconds.
	 */
	public int getTotalPlayTime();

	/**
	 * Returns the songlist. This is not a copy, so be careful!
	 * @return A reference to the songlist.
	 */
	public ArrayList<Song> getSongList();

	/**
	 * Plays the song at the specified index. 
	 * @param index The index of the song to play.
	 */
	public void playSong(int index);
	
	/**
	 * Added for P5.
	 * Plays the given song (only if the song list contains the song). If it doesn't, then
	 * it does nothing.
	 * @param the song to play.
	 */
	public void playSong(Song song);
	
	/**
	 * Added for P5.
	 * Stops the currently playing song (if any) and sets playing song to null.
	 */
	public void stop();
	
	/**
	 * Added for P5.
	 * Returns an array of all the songs in the playlist.
	 * @return An array of songs.
	 */
	public Song[] getSongArray();

	/**
	 * Added for P5.
	 * Moves the song at the given index to the previous index in the list (index - 1). All other elements
	 * in the list will be shifted. If the index given is zero, it will wrap around and move the song to the
	 * end of the list.
	 * 
	 * @param index The index of the song to move.
	 * @return The new index of the song (after the move).  If a song at the given index does not exist,
	 * or could not be moved for some other reason, returns the original index.
	 */
	public int moveUp(int index);

	/**
	 * Added for P5.
	 * Moves the song at the given index to the next index in the list (index + 1). All other elements
	 * in the list will be shifted. If the given index is the last song in the list, it will wrap around
	 * and move the song to the beginning of the list.
	 * @param index The index of the song to move.
	 * @return The new index of the song (after the move). If a song at the given index does not exist,
	 * or could not be moved for some other reason, returns the original index.
	 */
	public int moveDown(int index);

	/**
	 * Added for P5.
	 * Returns a 2 dimensional musical square. The dimension of the square is calculated based on the number of
	 * songs in the playlist. If the number of songs in the list are not a square number, then the remaining slots
	 * are filled starting with the first song.
	 *
	 * <p>
	 * For example, if the playlist contains 7 songs, the generated array would contain songs in the following
	 * order.
	 * </p>
	 *
	 * <pre>
	 * song0 song1 song2
	 * song3 song4 song5
	 * song6 song0 song1
	 * </pre>
	 * @return - the 2 dimensional array of songs.
	 */
	public Song[][] getMusicSquare();
	

}