import java.util.*;

/**
 * Demonstrates the use of a block statement in an if-else.
 *
 * @author Ahram Kim
 */
public class Guessing {
	/**
	 * Plays a simple guessing game with the user.
	 * 
	 */
	public static void main(String[] args) {
		final int MAX = 10;
		int answer, guess;

		Scanner scan = new Scanner(System.in);
		Random generator = new Random();

		answer = generator.nextInt(MAX) + 1;

		System.out.print("I'm thinking of a number between 1 and " + MAX + ". Guess what it is: ");

		do {
			guess = scan.nextInt();
			if (guess < 0 || guess > 10) {
				System.out.println("Your guess is out of range. Guess again: ");
			} else if (guess < answer) {
				System.out.println("Higher. Guess again: ");
			} else if (guess > answer) {
				System.out.println("lower. Guess again:");
			}

		} while (guess != answer);
		System.out.println("You got it! Good guessing!");
		scan.close();
	}
}