import java.util.Arrays;
import java.util.Random;

public class DeckOfCards implements DeckOfCardsInterface {

	private Card[] cards;
	private final int DECTSIZE = 52;
	private int nextCardIndex = 0;
	//private Card[] remcards;
	
	public DeckOfCards() {
		
		cards = new Card[DECTSIZE];
		
		int i = 0;
		
		for(Suit s : Suit.values()) {
			for(FaceValue v : FaceValue.values()) {
				cards[i] = new Card(s, v);
				i++;
			}
		}	
	}
	
	public void shuffle() {
		
		Random generator = new Random();
		
		//Attempt to swap each card with a random card in the deck.
	    //This isn't a perfect random shuffle but it is a simple one.
	    //A better shuffle requires a more complex algorithm.
		
		for(int i=0; i<cards.length; i++) {
			int j = generator.nextInt(cards.length);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
		//Reset instance variable that keeps track of dealt and remaining cards.
	    nextCardIndex = 0;
	}
	
	

	@Override
	public Card draw() {
		// TODO Auto-generated method stub
		if(nextCardIndex < DECTSIZE) {
			return cards[nextCardIndex++];
		} else {
			return null;
		}
	}

	@Override
	public void restoreDeck() {
		// TODO Auto-generated method stub
		this.nextCardIndex = 0;
		for(Suit s: Suit.values()) {
			for(FaceValue v: FaceValue.values()) {
				cards[nextCardIndex] = new Card(s, v);
			}
		}
	}

	@Override
	public int numCardsRemaining() {
		// TODO Auto-generated method stub
		return cards.length - nextCardIndex;
	}

	@Override
	public int numCardsDealt() {
		// TODO Auto-generated method stub
		return nextCardIndex;
	}

	@Override
	public Card[] dealtCards() {
		// TODO Auto-generated method stub
		
		//remcards= Arrays.copyOfRange(cards, 0, nextCardIndex);
		//return remcards;
		return Arrays.copyOfRange(cards, 0, nextCardIndex);
		
	}

	@Override
	public Card[] remainingCards() {
		// TODO Auto-generated method stub
		//remcards= Arrays.copyOfRange(cards, nextCardIndex, cards.length);
		//return remcards;
		return Arrays.copyOfRange(cards, nextCardIndex, cards.length);
	
	}
	
	public String toString() {
		String s = "";
		for(int i=0; i<cards.length; i++) {
			if(i%4==0)
			{
				s+="\n";
			}
			s += cards[i].toString();
		}
		return s;
	}
}
