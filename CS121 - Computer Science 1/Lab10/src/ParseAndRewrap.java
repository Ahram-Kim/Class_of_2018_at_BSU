import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Ahram Kim, Seong Hoon Son
 *
 */
public class ParseAndRewrap {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String outLine = "";
		int longestLine = 0;
		int shortestLine = 9999;
		
	
		System.out.print("Please enter a plain text file name: ");
		String filename = s.nextLine().trim();
		System.out.print("Please enter the maximum number of characters in a single ine: " );
		int maxNum = s.nextInt();
		System.out.println();
		
		System.out.println(filename + " reformatted with maximum line length of " + maxNum + ":");
		System.out.println();
		
		File file = new File(filename);
		try {
		
			Scanner fileScan = new Scanner(file);
		
			while(fileScan.hasNextLine()) {
			
				String line = fileScan.nextLine();
				Scanner newscan = new Scanner(line);
			
				while(newscan.hasNext()) {
				
					String token = newscan.next();
					
				
					if(outLine.length()+token.length()<maxNum) {
						outLine += token + " ";	
					} else if(outLine.length()+token.length()==maxNum) {		
						outLine += token;
					} else {
						
						if (outLine.length()> longestLine) {
							longestLine = outLine.length();
						} else if (outLine.length() < shortestLine) {
							shortestLine = outLine.length();
						}
						
						System.out.println(outLine);
						outLine = token + " ";
						
					}
				}
				
			newscan.close();
			
			}
			System.out.print(outLine);
			System.out.println();
		
			if (outLine.length()> longestLine) {
				longestLine = outLine.length();
			} else if (outLine.length() < shortestLine) {
				shortestLine = outLine.length();
			}
			
			System.out.println("Longest line: " + longestLine);
			System.out.println("Shortest line: " + shortestLine);
			fileScan.close();
		
		}
		
		catch(FileNotFoundException f) {
			System.out.println(f.getMessage());
			System.out.println();
		
		}
		
	}
}
