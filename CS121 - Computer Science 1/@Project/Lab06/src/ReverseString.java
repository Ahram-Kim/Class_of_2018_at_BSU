
import java.util.Scanner;

public class ReverseString {
	public static void main(String[] args)  {
		
		Scanner reverse = new Scanner(System.in);
		
		System.out.println("Enter a string: ");
		String string = reverse.nextLine().trim();
		System.out.println("Your string: " + string);
		
		for(int i=0; i>=0; i--)  {
			StringBuffer string2 = new StringBuffer(string);
			System.out.println("Reversed: " + string2.reverse());
			
		}
		reverse.close();
	}

}
