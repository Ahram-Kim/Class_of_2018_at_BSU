import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Put an array of ints in ascending order... incorrectly.
 * @author mvail
 */
public class WrongSortTest {
	/**
	 * Get an array of specified size and pass it to WrongSort.wrongSort().
	 * Report the results.
	 * @param args either size of a new array, or a file containing ints
	 */
	public static void main(String[] args) {
//ORIGINAL main() CODE, COMMENTED OUT TO FOCUS ON DATA COLLECTION
//		if (args.length != 1) {
//			System.out.println("Usage:  java WrongSortTest sizeOfArray\n"
//					+ "\tor\n\tjava WrongSortTest arrayFile");
//			System.exit(1);
//		}
//
//		// create or read the int[]
		int size = 0;
		int[] array = new int[0];
//		try {
//			size = Integer.parseInt(args[0]);
//			array = ArrayOfInts.randomizedArray(size);
//		} catch (NumberFormatException nfe) {
//			try {
//				array = ArrayOfInts.arrayFromFile(args[0]);
//				size = array.length;
//			} catch (Exception e) {
//				System.err.println("unable to read array from " + args[0]);
//				System.exit(1);
//			}
//		}
//
//		System.out.println("before:");
//		for (int i = 0; i < array.length; i++) {
//			System.out.printf(((i+1) % 10 > 0) ? " %d" : " %d\n", array[i]);
//		}
//
//		WrongSort.wrongSort(array); //this is the call we want to measure
//
//		System.out.println("\nafter:");
//		for (int i = 0; i < array.length; i++) {
//			System.out.printf(((i+1) % 10 > 0) ? " %d" : " %d\n", array[i]);
//		}

//NEW CODE, JUST FOR DATA COLLECTION
		//call modified wrongSort() with different array sizes and write results to
		// "wrongSort.csv" with a PrintStream
		try {
			PrintStream outfile = new PrintStream("wrongSort.csv");
			outfile.println("size, statements");
			for (size = 1000; size < 10001; size += 1000) {
				array = ArrayOfInts.randomizedArray(size);
				WrongSort.resetStatements();
				WrongSort.wrongSortModified(array);
				long statements = WrongSort.getStatements();
				outfile.println(size + ", " + statements);

				System.out.printf("\nfor n = %d, took %d statements to put elements in (wrong) order\n",
						size, statements);
			}
			outfile.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
}
