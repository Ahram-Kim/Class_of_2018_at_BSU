import java.io.File;

/**
 * 
 * Illustrate how to use command line arguments. Run it as follows:
 * 
 * java ProcessText <string> <int> <double>
 * 
 * @author Ahram Kim
 *
 */
public class ProcessText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length <= 0) {
			System.err.println("Usage: java ProcessText file1 [file2 ...]");
			System.exit(1);
		}	
		for (String fileName : args) {
			File file = new File(fileName);
			if(file.exists()) {
				TextStatistics text = new TextStatistics(file);
				System.out.println(text.toString());
			}
			
		
		}
	}
}