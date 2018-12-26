/**
 * Generate all combinations of n bits (represented as an int[])
 * and count the pairs of consecutive 0s and 1s.
 * 
 * @author mvail
 */
public class ConsecutivePairs {
	private int n;
	private long pairsCount;
	
	private long numStatements = 0; //looking for the dominant section as n gets large
	
	/**
	 * Generates all combinations of numberOfBits and counts all matched bit pairs.
	 * @param numberOfBits a positive integer
	 * @throws IllegalArgumentException
	 */
	public ConsecutivePairs(int numberOfBits) throws IllegalArgumentException {
		if (numberOfBits < 1) {
			throw new IllegalArgumentException("number of bits must be positive");
		}
		n = numberOfBits;
		pairsCount = 0;
		
		int[] bit = new int[n];
		for (int i = 0; i < bit.length; i++) {
			bit[i] = 0;
		}
		
		//generate all combinations of n "bits" by mimicking a binary counter
		while(positivesCount(bit) < n) {
			
			//numStatements++; //are these the most important statements?
			
			//count "bits" that match their neighbor
			for(int i = 0; i < bit.length - 1; i++) {
				
				//numStatements++; //are these the most important statements?
				
				if(bit[i] == bit[i+1]) {
					pairsCount++;
				}
			}
			
			//add 1 to binary counter (eventually produces all combinations
			//of 1s and 0s in bit[]
			int b = 0;
			while (b < bit.length && bit[b] == 1) {
				bit[b] = 0;
				b++;
				
				//numStatements++; //are these the most important statements?
				
			}
			if (b < bit.length) {
				bit[b] = 1;
			}
		}
		//still need to count last state where all bits are 1s
		//count "bits" that match their neighbor
		for(int i = 0; i < bit.length - 1; i++) {
			
			//numStatements++; //are these the most important statements?
			
			if(bit[i] == bit[i+1]) {
				pairsCount++;
			}
		}
	}
	
	/**
	 * @param bits array to examine
	 * @return number of elements with value greater than zero
	 */
	private int positivesCount(int[] intsArray) {
		int positives = 0;
		for(int i = 0; i < intsArray.length; i++) {
			
			//numStatements++; //are these the most important statements?
			
			if(intsArray[i] > 0) {
				positives++;
			}
		}
		return positives;
	}
	
	/**
	 * @return number of consecutive matches in all combinations of n bits
	 */
	public long getPairsCount() {
		return pairsCount;
	}
	
	/**
	 * @return number of bits used
	 */
	public int getNumberOfBits() {
		return n;
	}
	
	/**
	 * @return approximate number of statements executed
	 */
	public long getNumStatements() {
		return numStatements;
	}
}
