import java.util.Random;
import java.util.Scanner;

/**
 * Demonstrates the use of a programmer-defined class.
 * @author Ahram Kim
 */

public class DiceRoller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		Die die1 = new Die(5,15);
		Die die2 = new Die(7,13);
		die1.roll();
		die2.roll();
		String inputString;
		int countuserwin = 0;
		int countcomputerwin = 0;
		int countties = 0;
		
		PairOfDice dice = new PairOfDice(6, 4, 12);
		
		do {
			dice.roll();
		
			int user;
			user = dice.getTotal();
		
			System.out.println("Your roll: " + dice.toString());
		
			dice.roll();
			int computer;
			computer = dice.getTotal();
			
			
			System.out.println("Computer's roll: " +dice.toString());
		
			if (user > computer) {
				System.out.println("You win!");
				System.out.println();
				countuserwin++;
			} else if (user < computer) {
				System.out.println("You lose!");
				System.out.println();
				countcomputerwin++;
			} else if (user == computer) {
				System.out.println("You ties!");
				countties++;
			}
			
			System.out.println("Your wins: " + countuserwin +"  Computer's wins: " + countcomputerwin + "  Ties: " + countties);
		
			System.out.println("Do you want to roll again?");
			System.out.println("(Y)es to continue, anything else to quit.");
			inputString = scanner.nextLine();
			System.out.println(inputString.equals("Y"));
			
			} while (inputString.equals("Y"));
		
			System.out.println();
			System.out.println("Thanks for playing!");
		
	}

}
