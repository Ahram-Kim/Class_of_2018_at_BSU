import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * A simple application to test use of String, Math, DecimalFormat and NumberFormat classes.
 *
 * @author CS121 instructors (starter code)
 * @author Ahram Kim, Seong Hoon Son
 * 
 */
public class MyNameIs
{
	/**
	 * @param args (unused)
	 */
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.print("First name: ");
		String firstName = keyboard.nextLine();

		System.out.print("Last name: ");
		String lastName = keyboard.nextLine();
		
		char firstInitial = lastName.charAt(0);
		
		System.out.print("Enter a number: ");
		double n1 = keyboard.nextDouble();
		DecimalFormat dFormat = new DecimalFormat("0.00");
		String n11 = dFormat.format(n1);
		
		System.out.print("Enter another number (between 0 and 1): ");
		double n2 = keyboard.nextDouble();
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		String n22 = percentFormat.format(n2);
		
		double n3 = n2*n1;
		String n33 = dFormat.format(n3);
		
		double n4 = Math.pow(n1, n2);
		String n44 = dFormat.format(n4);
		
		System.out.println("\nHi, my name is " + firstName + " " + lastName + ".");
		System.out.println("You'll find me under " + "\"" + lastName + ", " + firstName  + "\"" + ".");
		System.out.println("My name badge: " + "\"" + firstInitial + ". " + firstName + "\"" + ".");
		System.out.println(n22 + " of " + n11 + " is " + n33);
		System.out.println(n11 + " raised to the power of " + n2 + " is " + n44 + ".");

		// TODO: Finish the program according to the lab specifications.

		keyboard.close();
	}
}