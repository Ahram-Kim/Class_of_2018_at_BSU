/**
 * A test class for validating correctness of the <code>Song</code> class.
 * This does not check everything, but it will check the basic methods to make
 * sure everything is on the right track.
 *
 * @author CS121 Instructors
 */
/**
 * @author Ahram Kim
 *
 */
public class SongTest
{
	private static final String[] SONG_TITLES = {"Mary Had a Little Lamb", "Yankee Doodle"};
	private static final String[] SONG_ARTISTS = {"Mother Goose", "George Washington"};
	private static final int[] SONG_TIMES = {5, 132};
	private static final String[] SONG_PATHS = {"sounds/hitchcock.wav", "sounds/classical.wav"};
	private static int status = 0;

	/**
	 * Runs test cases.
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		// There will be errors in this class until you write the Song class.
		// You don't need to AND SHOULD NOT change this class at all.

		Song song = new Song(SONG_TITLES[0], SONG_ARTISTS[0], SONG_TIMES[0], SONG_PATHS[0]);

		// Testing getters
		testGetTitle(song);
		testGetArtist(song);
		testGetPlayTime(song);
		testGetFilePath(song);
		testGetPlayCount(song);

		// Testing setters
		testSetTitle(song);
		testSetArtist(song);
		testSetPlayTime(song);
		testSetFilePath(song);

		// Testing play
		testPlay(song);

		// Testing toString
		testToString();

		System.exit(status);
	}

	/**
	 * @param song
	 */
	private static void testGetTitle(Song song) {
		String test = "getTitle";

		String expected = SONG_TITLES[0];
		String actual = song.getTitle();

		if(!actual.equals(expected)) {
			fail(test, expected, actual);
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testSetTitle(Song song) {
		String test = "setTitle";

		String newTitle = SONG_TITLES[1];
		String actual;

		song.setTitle(newTitle);

		actual = song.getTitle();

		if(!actual.equals(newTitle)) {
			fail(test, newTitle, actual);
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testGetArtist(Song song) {
		String test = "getArtist";

		String expected = SONG_ARTISTS[0];
		String actual = song.getArtist();

		if(!actual.equals(expected)) {
			fail(test, expected, actual);
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testSetArtist(Song song) {
		String test = "setArtist";

		String newArtist = SONG_ARTISTS[1];
		String actual;

		song.setArtist(newArtist);

		actual = song.getArtist();

		if(!actual.equals(newArtist)) {
			fail(test, newArtist, actual);
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testGetPlayTime(Song song) {    
		String test = "getPlayTime";

		int expected = SONG_TIMES[0];
		int actual = song.getPlayTime();

		if(expected != actual) {
			fail(test, Integer.toString(expected), Integer.toString(actual));
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testSetPlayTime(Song song) {
		String test = "setPlayTime";

		int newPlayTime = SONG_TIMES[1];
		int actual;

		song.setPlayTime(newPlayTime);

		actual = song.getPlayTime();

		if(actual != newPlayTime) {
			fail(test, Integer.toString(newPlayTime), Integer.toString(actual));
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testGetFilePath(Song song) {    
		String test = "getFilePath";

		String expected = SONG_PATHS[0];
		String actual = song.getFilePath();

		if(!actual.equals(expected)) {
			fail(test, expected, actual);
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testSetFilePath(Song song) {
		String test = "setFilePath";

		String newFilePath = SONG_PATHS[1];
		String actual;

		song.setFilePath(newFilePath);

		actual = song.getFilePath();

		if(!actual.equals(newFilePath)) {
			fail(test, newFilePath, actual);
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testGetPlayCount(Song song) {    
		String test = "getPlayCount";

		int expected = 0;
		int actual = song.getPlayCount();

		if(expected != actual) {
			fail(test, Integer.toString(expected), Integer.toString(actual));
		} else {
			pass(test);
		}
	}

	/**
	 * @param song
	 */
	private static void testPlay(Song song) {
		String test = "play";
		song.play();

		int playCount = song.getPlayCount();
		if(playCount != 1) {
			fail(test, "play count should equal 1", "play count equals " + playCount);
		} else {
			pass(test);
		}
	}

	private static void testToString() {
		String test = "toString";

		Song song = new Song(SONG_TITLES[0], SONG_ARTISTS[0], SONG_TIMES[0], SONG_PATHS[0]);

		String expected = String.format("%-20s %-20s %-25s %10d", 
				SONG_TITLES[0], SONG_ARTISTS[0], SONG_PATHS[0], SONG_TIMES[0]);

		String actual = song.toString();

		if(!equalsIgnoreWhitespace(actual, expected)) {
			fail(test, expected, actual);
		} else {
			pass(test);
		}
	}

	/**
	 * @param methodName
	 * @param expected
	 * @param actual
	 */
	private static void fail(String methodName, String expected, String actual) {
		System.err.println("FAILED: " + methodName);
		System.err.println("    --> expected: " + expected);
		System.err.println("    -->   actual: " + actual);
		status = 1;
	}

	/**
	 * @param methodName
	 */
	private static void pass(String methodName) {
		System.out.println("PASSED: " + methodName);
	}

	/**
	 * @param one
	 * @param two
	 * @return
	 */
	private static boolean equalsIgnoreWhitespace(String one, String two) {
		return one.replaceAll("\\s+","").equalsIgnoreCase(two.replaceAll("\\s+",""));
	}
}