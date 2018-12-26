import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Contains utility methods for generating int arrays of given sizes.
 * @author mvail
 */
public class ArrayOfInts {

	/**
	 * Returns an array of consecutive ints from 1 to size.
	 * @param size number of elements in returned array
	 * @return array of consecutive ints from 1 to size 
	 */
	public static int[] orderedArray(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = i+1;
		}
		return a;
	}
	
	/**
	 * Returns a randomized array containing ints from 1 to size.
	 * @param size number of elements in returned array
	 * @return randomized array of ints from 1 to size
	 */
	public static int[] randomizedArray(int size) {
		ArrayList<Integer> aL = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			aL.add(i+1);
		}
		Collections.shuffle(aL);
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = aL.get(i);
		}
		return a;
	}
	
	/**
	 * Writes an int[] to a plain-text file with ints separated by spaces.
	 * Useful for creating input files for repeatable tests. 
	 * @param array array to write to outfile
	 * @param outfile name of file where array should be written
	 */
	public static void arrayToFile(int[] array, String outfile) {
		try {
			FileWriter fw = new FileWriter(outfile);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter outFile = new PrintWriter(bw);	
			for (int i : array) {
				outFile.print(i + " ");
			}
			outFile.close();
		} catch (IOException e) {
			System.err.println("Could not write to " + outfile + "\n" + e);
		}
	}
	
	/**
	 * Read ints from a file and return them in an int[]
	 * @param infile name of file containing ints separated by whitespace
	 * @return array of ints loaded from infile
	 * @throws FileNotFoundException if infile cannot be opened or read
	 * @throws InputMismatchException if infile contains anything other than ints
	 */
	public static int[] arrayFromFile(String infile) throws FileNotFoundException, InputMismatchException {
		Scanner scan = new Scanner(new File(infile));
		ArrayList<Integer> aL = new ArrayList<Integer>();
		while (scan.hasNext()) {
			aL.add(scan.nextInt());
		}
		scan.close();
		int[] a = new int[aL.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = aL.get(i);
		}
		return a;
	}
}
