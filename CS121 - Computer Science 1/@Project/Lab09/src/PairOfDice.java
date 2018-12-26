
/**
 * Die.java
 *
 * Represents pair of dice
 *
 * @author Ahram KIm
 */

/**
 * Constructor: Sets the initial face value of this PairOfDice.
 */

public class PairOfDice {

	// Instance variables
	
	Die die1;
	Die die2;

	/**
	 * @param sides
	 */
	public PairOfDice (int sides) {
		die1 = new Die(sides);
		die2 = new Die(sides);
	}
	
	/**
	 * @param sides
	 * @param seed1
	 * @param seed2
	 */
	public PairOfDice (int sides, long seed1, long seed2) {
		die1 = new Die(sides, seed1);
		die2 = new Die(sides, seed2);
	}
	
	/**
	 * @return getTotal()
	 */
	public int roll() {
		die1.roll();
		die2.roll();
		//int faceValue1 = rand.nextInt(diceSides) + 1;
		return getTotal();
	}
	
	
	/**
	 * @return faceValue1
	 */
	public int getFaceValue1() {
		int faceValue1 = die1.getFaceValue();
		return faceValue1;
	}
	
	/**
	 * @return faceValue2
	 */
	public int getFaceValue2() {
		int faceValue2 = die2.getFaceValue();
		return faceValue2;
	}
	
	/**
	 * @return total
	 */
	public int getTotal() {
		int total = getFaceValue1() + getFaceValue2(); 
		return total;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String string = "";
		string += String.format("%s ( %s + %s )", getTotal(), getFaceValue1(), getFaceValue2());
		return string;
	}
}
