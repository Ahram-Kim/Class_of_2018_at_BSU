
public class ConsecutivePairsTest {

	/**
	 * @param args expect one integer for n - the number of "bits"
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java ConsecutivePairsTest numberOfBits");
			System.exit(1);
		}

		try {
			int n = Integer.parseInt(args[0]);
			
			ConsecutivePairs pairs = new ConsecutivePairs(n); //this is the call to measure
			
			System.out.printf("for n = %d, there are %d matched bit pairs\n",
					n, pairs.getPairsCount());
			System.out.printf("approximately %d statements were executed to calculate this answer",
					pairs.getNumStatements());

		} catch (Exception nfe) {
			System.out.println("number of bits must be a positive integer");
			nfe.printStackTrace();
			System.exit(1);
		}
	}
}
