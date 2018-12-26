/**
 * Represents the suits of standard playing cards
 *
 * @author CS121 Instructors
 */
public enum Suit
{
	Clubs("\u2663"),
	Diamonds("\u2666"),
	Hearts("\u2665"),
	Spades("\u2660");

	private String symbol;

	Suit(String symbol)
	{
		this.symbol = symbol;
	}

	/**
	 * Returns the unicode symbol for this Suit.
	 * @return String the unicode value.
	 */
	public String getSymbol()
	{
		return this.symbol;
	}

	@Override
	public String toString()
	{
		return this.name() + " (" + symbol + ")";
	}
}