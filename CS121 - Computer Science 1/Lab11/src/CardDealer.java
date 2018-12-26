
/**
 * @author Ahram Kim
 * 
 * 
 * 1. How did this program use encapsulation?
 *    : If a field is declared private, it cannot be accessed by anyone outside
 *      the class, thereby hiding the fields within the class.
 *    
 * 2. What was the most challenge part and why?
 *    : to use for-each loop function. easier to use for loop than for-each loop.
 *      to use copyOfArray. never used in java
 *
 */
public class CardDealer {

	public static void main(String[] args) {
		
		DeckOfCards deck = new DeckOfCards();
		
		System.out.println("fresh, unshuffled deck");
		System.out.println(deck.toString());
		
		deck.shuffle();
		System.out.println();
		System.out.println("shuffled dexk");
		System.out.println(deck);
		
		while(deck.numCardsRemaining()>0) {
			
			System.out.println();
			System.out.println("Drawing cards . . .");
			
			Card player1 = deck.draw();
			Card player2 = deck.draw();
			
			System.out.println("Player 1: " + player1);
			System.out.println("Player 2: " + player2);

			int winner = player1.compareTo(player2);

			if(winner == 0) {
				System.out.println("player 1 wins!");
			} else if(winner>0) {
				System.out.println("player 2 wins!");
			} else {
				System.out.println("Everyone wins!");
			}
		}
		deck.restoreDeck();
		System.out.println();
		System.out.println("restored deck");
		System.out.println(deck);
		
	}
}
