/**
 * Represents the face value of standard playing cards.
 *
 * @author CS121 Instructors
 */
public enum FaceValue
{
	Two(2),
	Three(3),
	Four(4),
	Five(5),
	Six(6),
	Seven(7),
	Eight(8),
	Nine(9),
	Ten(10),
	Jack(11),
	Queen(12),
	King(13),
	Ace(14);

	private int rank;

	FaceValue(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}
}