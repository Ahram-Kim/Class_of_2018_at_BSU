import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ParseForCaps {

	public static void main(String[] args) throws FileNotFoundException  {
		
		Scanner s = new Scanner(System.in);
		System.out.print("Please enter a plain text file name: ");
		String filename = s.nextLine().trim();
		
		
		File file = new File(filename);
		
		Scanner filescanner = new Scanner(file);
	
		while (filescanner.hasNextLine()) {
			
			String line = filescanner.nextLine();
			Scanner newscanner = new Scanner(line);

			while (newscanner.hasNext()) {
				
				String token = newscanner.next();
				
				//System.out.println("Capital first letters: " + c);
				char x = token.charAt(0);
				if (x>= 'A' && x<= 'Z')
				{
					System.out.print(x);
				}
				
			}
			newscanner.close();
			
		}
		filescanner.close();
	}
	
}