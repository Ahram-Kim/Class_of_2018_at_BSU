/**
 * Takes an array and reorders it so elements are in ascending order.
 * @author mvail
 */
public class Order {

	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	
	private static long numStatements = 0;
	
	public static void order(int[] array) {
		
		numStatements ++;
		
        for (int next = 1; next < array.length; next++) { //1, n+1, n
        	
        	numStatements += 3;
        	
            int val = array[next]; // 1
            int index = next;  // 1
            
            numStatements += 2;
            
            while (index > 0 && val < array[index - 1]) {//n
            	
            	numStatements += 2;
                
            	array[index] = array[index - 1]; //1
                index--;  //1
            }
            
            numStatements ++;
            
            array[index] = val; //1
        }
	}
	
	public static long getStatements() {
		return numStatements;
	}
	
	public static void resetStatements() {
		numStatements = 0;
	}
}
