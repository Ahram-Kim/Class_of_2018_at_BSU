public class FindTest {

	/**
	 * Get an array of specified size and find the indexes of all elements
	 * in the array using Find.find().
	 * @param args size of array to generate or file containing ints
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage:  java FindTest sizeOfArray\n"
					+ "\tor\n\tjava FindTest arrayFile");
			System.exit(1);
		}
		
		// create or read the int[]
		int size = 0;
		int[] array = new int[0];
		try {
			size = Integer.parseInt(args[0]);
			array = ArrayOfInts.randomizedArray(size);
		} catch (NumberFormatException nfe) {
			try {
				array = ArrayOfInts.arrayFromFile(args[0]);
				size = array.length;
			} catch (Exception e) {
				System.err.println("unable to read array from " + args[0]);
				System.exit(1);
			}
		}

		// find the index of every element
		for (int i = 1; i <= array.length; i++) {
		 	//this is the method of interest, but you'll need to average
			//the number of statements needed to find each element for
			//meaningful results
			int index = Find.find(array, i);
			
			System.out.printf("%d found at index %d\n", i, index);
			
			array = ArrayOfInts.randomizedArray(size);
			WrongSort.resetStatements();
			WrongSort.wrongSortModified(array);
			
			
		}
		long statements = WrongSort.getStatements();
		System.out.printf("\nfor n = %d, took %d statements to put elements in (wrong) order\n",
				size, statements);
	}

}
