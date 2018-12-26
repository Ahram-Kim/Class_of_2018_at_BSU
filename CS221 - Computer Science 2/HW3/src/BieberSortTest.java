/**
 * Run BieberSort
 * @author Shane K. Panter
 */
public class BieberSortTest {

	public static void main(String[] args){
		if (args.length != 1) {
			System.out.println("Usage: java BieberSortTest numberOfBiebz");
			System.exit(1);
		}
		
		try {
			int n = Integer.parseInt(args[0]);
			BieberSort bs = new BieberSort(n);
			bs.printBiebers();
			System.out.println("---------");
			
			bs.sort();	// this is the method to measure
			
			bs.printBiebers();
		} catch (NumberFormatException nfe) {
			System.out.println("number of Biebz must be a positive integer");
			nfe.printStackTrace();
		}
	}
}
