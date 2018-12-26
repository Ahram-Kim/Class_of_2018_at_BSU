/**
 * Returns index of a value in an int[] or -1 if it isn't found.
 * @author mvail
 */
public class Find {
	/**
	 * Return index where value is found in array or -1 if not found.
	 * @param array ints where value may be found
	 * @param value int that may be in array
	 * @return index where value is found or -1 if not found
	 */
	
	private static long numStatements = 0;
	
	
	public static int find(int[] array, int value) {
		
		numStatements ++;
		
		for (int i = 0; i < array.length; i++) {
			
			numStatements ++;
			
			if (array[i] == value) {
				
				numStatements ++;
				
				return i; 
			}
		}
		numStatements ++;
		return -1;  
	}

	public static long getStatements() {
		return numStatements;
	}
	
	public static void resetStatements() {
		numStatements = 0;
	}
}