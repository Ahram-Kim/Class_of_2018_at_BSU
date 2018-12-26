import java.io.File;
import java.util.Arrays;


/**
 * Simple unit tester for the TextStatistics class.
 * @author Ahram Kim
 *
 */
public class TextStatisticsTest
{
	private final static int PRECISION = 2; //number of digits after floating point to match

	/**
	 * Compares two doubles to see if they are equal to within the given precision
	 * @param x
	 * @param y
	 * @param precision number of digits after the decimal point to use in testing equality
	 * @return
	 */
	private static boolean approxEquals(double x, double y, int precision) {
		final double EPSILON = Math.pow(10, -precision);
		if (Math.abs(x - y) < EPSILON)
			return true;
		else
			return false;
	}

	/**
	 * Test given TextStatistics object with given expected results.
	 * @param stats  The TextStatistics object to test
	 * @param numChars number of characters
	 * @param numWords number of words
	 * @param numLines number of lines
	 * @param avgWordLength average word length
	 * @param wordFreq  array of word frequencies [0..23]
	 * @param letterFreq array of letter frequencies [0..25]
	 */
	private static void test(TextStatisticsInterface stats,
			int numChars,
			int numWords,
			int numLines,
			double avgWordLength,
			int[] wordFreq,
			int[] letterFreq)
	{

		if (stats.getCharCount() == numChars){
			System.out.println("Passed! getCharCount()");
		} else {
			System.out.println("----> Failed ! getCharCount()  correct: " + numChars + " generated: " + stats.getCharCount());
		}

		if (stats.getWordCount() == numWords) {
			System.out.println("Passed! getWordCount()");
		} else {
			System.out.println("----> Failed ! getWordCount()  correct: " + numWords + " generated: " + stats.getWordCount());
		}
		if (stats.getLineCount() == numLines) {
			System.out.println("Passed! getLineCount()");
		} else {
			System.out.println("----> Failed ! getLineCount()  correct: " + numLines + " generated: " + stats.getLineCount());
		}
		if (approxEquals(stats.getAverageWordLength(), avgWordLength, PRECISION)) {
			System.out.println("Passed! getAverageWordLength()");
		} else {
			System.out.println("----> Failed ! getAverageWordLength()  correct: " + avgWordLength + " generated: " + stats.getAverageWordLength());
		}

		int [] testWordFreq = stats.getWordLengthCount();
		if (Arrays.equals(testWordFreq, wordFreq)) {
			System.out.println("Passed! Word length frequencies");
		} else {
			System.out.println("\n----> Failed ! Word length frequencies\n\n" +
								"   correct: " + Arrays.toString(wordFreq) + "\n" +
								" generated: " + Arrays.toString(testWordFreq) + "\n");
		}

		int[] testLetterFreq = stats.getLetterCount();
		if (Arrays.equals(testLetterFreq, letterFreq)) {
			System.out.println("Passed! Letter frequencies");
		} else {
			System.out.println("\n----> Failed ! Letter frequencies\n\n" +
								"   correct: " + Arrays.toString(letterFreq) + "\n" +
								" generated: " + Arrays.toString(testLetterFreq) +"\n");
		}

		System.out.println();
	}

	/**
	 * Test over a list of predefined files.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// expected results
		String [] textfile = {"etext" + File.separator + "testfile.txt",
			              "etext" + File.separator + "Gettysburg-Address.txt",
				      "etext" + File.separator + "Alice-in-Wonderland.txt"};
		int[][] wordFreq = {{0, 3, 13, 24, 13, 10, 2, 5, 3, 1, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				            {0, 8, 50, 55, 61, 35, 27, 17, 7, 10, 6, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			                {0, 1705, 4412, 7062, 5782, 3340, 1951, 1569, 723, 448, 181, 108, 34, 11, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] letterFreq = {{27, 1, 11, 10, 33, 9, 7, 24, 25, 0, 2, 18, 5, 25, 26, 5, 0, 21, 30, 35, 7, 1, 10, 1, 2, 0},
				              {107, 18, 32, 61, 175, 28, 33, 81, 74, 0, 3, 47, 14, 86, 96, 17, 1, 84, 53, 132, 25, 27, 28, 0, 13, 0},
				              {8787, 1474, 2397, 4931, 13569, 2000, 2528, 7372, 7511, 146, 1158, 4713, 2107, 7013, 8141, 1522, 209, 5433,
			                   6495, 10684, 3468, 845, 2674, 148, 2264, 78}
		};
		int[] numChars = {465, 1622, 148482};
		int[] numWords = {79, 281, 27331};
		int[] numLines = {11, 39, 3610};
		double[] avgWordLength = {4.24, 4.40, 3.94};

		for (int i = 0; i < textfile.length; i++) {
			File nextFile = new File(textfile[i]);
			if (nextFile.exists() && nextFile.canRead()) {
				System.out.println("\nTesting on data file:" + textfile[i] + "\n");
				TextStatisticsInterface stats = new TextStatistics(nextFile);
				test(stats, numChars[i], numWords[i], numLines[i],
						avgWordLength[i], wordFreq[i], letterFreq[i]);
			} else {
				System.err.println("Cannot access test file: " + textfile[i]);
			}
		}
	}
}