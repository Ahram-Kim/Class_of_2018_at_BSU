
public class Position implements Comparable
{
	private int x = -1;		// starting point row
	private int y = -1;		// starting point col
	private int score=0;

	/**
	 * Basic constructor
	 * 
	 * @param x
	 * @param y
	 * @param score
	 */
	public Position(int x, int y, int score)
	{
		this.x = x;
		this.y = y;
		this.score = score;
	}
	
	/**
	 * Basic constructor 
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get x(row).
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Set x(row) to given value.
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get y(col).
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set y(col) to given value.
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get a score.
	 * 
	 * @return score
	 */
	public int getSore()
	{
		return score;
	}

	/**
	 * Set score to given value.
	 * @param score
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
	
	/**
	 * Compare cpos and ypos with x and y.
	 * 
	 * @param xpos
	 * @param ypos
	 * @return
	 */
	public boolean compare(int xpos, int ypos)
	{
		if(x==xpos && y == ypos)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int compareTo(Object o) {
		int compareScore=((Position)o).getSore();
        /* For Ascending order*/
        return this.score-compareScore;
	}
	
	/**
	 * Return a string to print out
	 */
	public String toString()
	{
		return "[ x = " + x + ", y = " + y + ", score = " + score + " ]";
	}
		
}
