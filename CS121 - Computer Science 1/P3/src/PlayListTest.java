import java.text.DecimalFormat;
import java.lang.IndexOutOfBoundsException;

/**
 * A test class for validating correctness of the <code>PlayList</code> class.
 * This does not check everything, but it will check the basic methods to make
 * sure everything is on the right track.
 *
 * @author CS121 Instructors
 */
/**
 * @author Ahram Kim
 *
 */
public class PlayListTest
{
	private static final String PLAYLIST_NAME = "Test List";
	private static final String[] SONG_TITLES = {"Mary Had a Little Lamb", "Yankee Doodle"};
	private static final String[] SONG_ARTISTS = {"Mother Goose", "George Washington"};
	private static final int[] SONG_TIMES = {5, 10};
	private static final String[] SONG_PATHS = {"sounds/hitchcock.wav", "sounds/classical.wav"};
	private static int status = 0;
	
	private static Song[] testSongs;
	private static PlayList testPlayList;

	/**
	 * Runs test cases.
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		// There will be errors in this class until you write the Song and PlayList classes.
		// You don't need to AND SHOULD NOT change this class at all.

		createTestSongs();

		testGetName();
		testSetName();
		
		testGetPlaying();

		testAddSong();
		testGetSong();
		testRemoveSong();
		
		testGetNumSongs();
		testGetTotalPlayTime();
		
		testPlaySong();
		
		testGetInfo();
		testToString();

		System.exit(status);
	}
	
	private static void createTestSongs()
	{
		testSongs = new Song[SONG_TITLES.length];
		for(int i = 0; i < testSongs.length; i++) {
			testSongs[i] = new Song(SONG_TITLES[i], SONG_ARTISTS[i], SONG_TIMES[i], SONG_PATHS[i]);
		}
	}
	
	private static void setUp()
	{
		testPlayList = new PlayList(PLAYLIST_NAME);
	}
	
	private static void testGetName()
	{
		setUp();
		
		String test = "getName";
		
		String name = testPlayList.getName();
		
		if(!name.equals(PLAYLIST_NAME)) {
			fail(test, PLAYLIST_NAME, name);
		} else {
			pass(test);
		}
	}
	
	private static void testSetName()
	{
		setUp();
		
		String test = "setName";
		
		String newName = "Super List";
		
		testPlayList.setName(newName);
		
		String actual = testPlayList.getName();
		
		if(!actual.equals(newName)) {
			fail(test, newName, actual);
		} else {
			pass(test);
		}
		
		testPlayList.setName(PLAYLIST_NAME);
	}
	
	private static void testAddSong()
	{
		setUp();
		
		String test = "addSong";
		
		testPlayList.addSong(testSongs[0]);

		int numSongs = testPlayList.getNumSongs();
		if(numSongs != 1) {
			fail(test, "Size of playList should be " + Integer.toString(1),
					"Size of playList is " + Integer.toString(numSongs));
		} else {
			pass(test);
		}
	}
	
	private static void testGetPlaying()
	{
		setUp();
		
		String test = "getPlaying - nothing playing";

		Song actual = testPlayList.getPlaying();
		if(actual != null) {
			fail(test, null, actual.toString());
		} else {
			pass(test);
		}
	}
	
	private static void testGetSong()
	{	
		setUp();
		
		String test = "getSong - invalid index";
		try {
			testPlayList.getSong(0); // should fail to remove song.
		} catch (IndexOutOfBoundsException e) {
			fail(test + " - Throws exception! Don't forget to check if index is in range before getting.",
					"do nothing", "throws IndexOutOfBoundsException");
		}
		test = "getSong - negative index";
		try {
			testPlayList.getSong(-1); // should fail to remove song.
		} catch (IndexOutOfBoundsException e) {
			fail(test + " - Throws exception! Don't forget to check if index is in range before getting.",
					"do nothing", "throws IndexOutOfBoundsException");
		}
		
		/* load up songs for testing... */
		for(Song s : testSongs) {
			testPlayList.addSong(s);
		}
		
		test = "getSong";
		Song song = testPlayList.getSong(0);
		
		if(song == null) {
			fail(test, testSongs[0].toString(), null);
		} else if(song != testSongs[0]) {
			fail(test, testSongs[0].toString(), song.toString());
		} else {
			pass(test);
		}
	}
	
	private static void testRemoveSong()
	{
		setUp();
		
		String test = "removeSong - invalid index";
		try {
			testPlayList.removeSong(0); // should fail to remove song.
		} catch (IndexOutOfBoundsException e) {
			fail(test + " - Throws exception! Don't forget to check if index is in range before removing.",
					"do nothing", "throws IndexOutOfBoundsException");
		}
		test = "removeSong - negative index";
		try {
			testPlayList.removeSong(-1); // should fail to remove song.
		} catch (IndexOutOfBoundsException e) {
			fail(test + " - Throws exception! Don't forget to check if index is in range before removing.",
					"do nothing", "throws IndexOutOfBoundsException");
		}
		
		/* load up songs for testing... */
		for(Song s : testSongs) {
			testPlayList.addSong(s);
		}
		
		test = "removeSong";
		Song removed = testPlayList.removeSong(0);
		int numSongs = testPlayList.getNumSongs();
		
		if(removed == null) {
			fail(test, testSongs[0].toString(), null);
		} else if(removed != testSongs[0]) {
			fail(test, testSongs[0].toString(), removed.toString());
		} else if (testPlayList.getNumSongs() != testSongs.length-1) {
			fail(test + " - getNumSongs after remove", 
					Integer.toString(testSongs.length-1), Integer.toString(numSongs));
		} else {
			pass(test);
		}
	}
	
	private static void testGetNumSongs()
	{
		setUp();
		
		String test = "getNumSongs - empty list";
		
		int expected = 0;
		int actual = testPlayList.getNumSongs();
		if(expected != actual) {
			fail(test, Integer.toString(expected), Integer.toString(actual));
		} else {
			pass(test);
		}
		
		test = "getNumSongs";
		for(Song s : testSongs) {
			testPlayList.addSong(s);
			expected += s.getPlayTime();
		}
		expected = testSongs.length;
		actual = testPlayList.getNumSongs();
		if(expected != actual) {
			fail(test, Integer.toString(expected), Integer.toString(actual));
		} else {
			pass(test);
		}
	}
	
	private static void testGetTotalPlayTime()
	{
		setUp();
		
		String test = "getTotalPlayTime - empty list";
		
		int expected = 0;
		int actual = testPlayList.getTotalPlayTime();
		if(expected != actual) {
			fail(test, Integer.toString(expected), Integer.toString(actual));
		} else {
			pass(test);
		}
		
		test = "getTotalPlayTime";
		
		for(Song s : testSongs) {
			testPlayList.addSong(s);
			expected += s.getPlayTime();
		}
		
		actual = testPlayList.getTotalPlayTime();
		if(expected != actual) {
			fail(test, Integer.toString(expected), Integer.toString(actual));
		} else {
			pass(test);
		}
	}
	
	private static void testPlaySong()
	{
		setUp();
		
		/* test out of bounds first */
		String test = "playSong - invalid index";
		try {
			testPlayList.playSong(2); // should fail to play song.
		} catch (IndexOutOfBoundsException e) {
			fail(test + " - Throws exception! Don't forget to check if id is in range before playing.",
					"do nothing", "throws IndexOutOfBoundsException");
		}
		
		test = "playSong - negative index";
		try {
			testPlayList.playSong(-1); // should fail to play song.
		} catch (IndexOutOfBoundsException e) {
			fail(test + " - Throws exception! Don't forget to check if id is in range before playing.",
					"do nothing", "throws IndexOutOfBoundsException");
		}
		
		/* Add song and play it */
		test = "playSong";
		
		testPlayList.addSong(testSongs[0]);
		testPlayList.playSong(0);
		pass(test);

		test = "getPlaying - after playSong";
		Song playing = testPlayList.getPlaying();
		if(playing == null) {
			fail(test, testSongs[0].getTitle(), null);
		} else if(testSongs[0] != playing) {
			fail(test, testSongs[0].getTitle(), playing.getTitle());
		} else {
			pass(test);
		}
	}
	
	private static void testGetInfo()
	{
		setUp();
	
		String test = "getInfo - empty list";
		
		String expected = "There are no songs.\n";
		
		String actual = testPlayList.getInfo();

		if(!equalsIgnoreWhitespace(actual, expected)) {
			fail(test, "\n" + expected, "\n" + actual);
		} else {
			pass(test);
		}
		
		test = "getInfo - with songs";
		
		
		for(Song s : testSongs) {
			testPlayList.addSong(s);
		}
		int total = 15;
		double average = 7.50;
		DecimalFormat df = new DecimalFormat("0.00");
		
		StringBuilder builder = new StringBuilder();
		builder.append("The average play time is: " + df.format(average) + " seconds\n");
		builder.append("The shortest song is: " + testSongs[0] + "\n");
		builder.append("The longest song is: " + testSongs[testSongs.length-1] + "\n");
		builder.append("Total play time: " + total + "\n");
		expected = builder.toString();
		
		actual = testPlayList.getInfo();
		
		if(!equalsIgnoreWhitespace(actual, expected)) {
			fail(test, "\n" + expected, "\n" + actual);
		} else {
			pass(test);
		}
	}

	private static void testToString()
	{
		setUp();
	
		String test = "toString - empty list";
		
		StringBuilder builder = new StringBuilder();
		builder.append("------------------\n");
		builder.append(PLAYLIST_NAME + " (" + 0 + " songs)\n");
		builder.append("------------------\n");
		builder.append("There are no songs.\n");
		builder.append("------------------\n");
		String expected = builder.toString();
		
		String actual = testPlayList.toString();

		if(!equalsIgnoreWhitespace(actual, expected)) {
			fail(test, "\n" + expected, "\n" + actual);
		} else {
			pass(test);
		}
		
		test = "toString - with songs";
		
		for(Song s : testSongs) {
			testPlayList.addSong(s);
		}
		
		builder = new StringBuilder();
		builder.append("\n------------------\n");
		builder.append(PLAYLIST_NAME + " (" + testSongs.length + " songs)\n");
		builder.append("------------------\n");
		for(int i = 0; i < testSongs.length; i++) {
			builder.append("(" + i + ") ");
			builder.append(String.format("%-20s %-20s %-25s %10d\n",
						SONG_TITLES[i], SONG_ARTISTS[i], SONG_PATHS[i], SONG_TIMES[i]));
		}
		builder.append("------------------\n");
		expected = builder.toString();
		
		actual = testPlayList.toString();
		
		if(!equalsIgnoreWhitespace(actual, expected)) {
			fail(test, "\n" + expected, "\n" + actual);
		} else {
			pass(test);
		}
	}

	private static void fail(String methodName, String expected, String actual)
	{
		System.err.println("FAILED: " + methodName);
		System.err.println("    --> expected: " + expected);
		System.err.println("    -->   actual: " + actual);
		status = 1;
	}

	private static void pass(String methodName)
	{
		System.out.println("PASSED: " + methodName);
	}

	private static boolean equalsIgnoreWhitespace(String one, String two)
	{
		return one.replaceAll("\\s+","").equalsIgnoreCase(two.replaceAll("\\s+",""));
	}
}