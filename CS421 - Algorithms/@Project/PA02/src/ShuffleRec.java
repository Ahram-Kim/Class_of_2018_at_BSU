
/**
 * Shuffle-ness Problem by the pure recursion approach
 * 
 * @author ahramkim
 */
public class ShuffleRec 
{
	static String X, Y, Z;
	static int a, b, c;
	static int recurNum;
	
	public static void main(String[] args) 
	{
		 try
		 {
			 new ShuffleRec(args);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 System.exit(1);
		 }
	}
	
	/**
	 * Shuffle-ness Problem by the pure recursion approach
	 * 
	 * @param args
	 */
	private ShuffleRec(String[] args)
	{
		if(args.length < 3 || args.length > 3)
		{
			System.err.println("Usage : java ShuffleRec <String X> <String Y> <String Z> \n");
		}
		else 
		{
			X = args[0];
			Y = args[1];
			Z = args[2];

			a = X.length();
			b = Y.length();
			c = Z.length();
			
			if(c != (a + b))
			{
				System.err.println("The length of Z should be equal the length of X plus "
								+ "the length of Y. Type again.");
			}
			else
			{
				shuffleRecResult();
			}
		}
	}
	
	/**
	 * Shuffle-ness Solve Implementation by the pure recursion
	 * 
	 * @param a - X.length
	 * @param b - Y.length
	 * @return
	 */
	private boolean shuffleRecSolve(int a, int b)
	{
		recurNum++;
		
		// Shuffle.def recursion
		
		// 1. if i = 0 and j = 0
		if(a == 0 && b == 0 && (a+b) == 0)
		{
			return true;
		}

		// 2. if i > 0 and j = 0
		else if(a > 0 && b == 0)
		{
			if(X.charAt(a-1) == Z.charAt(a+b-1))
			{
				if(shuffleRecSolve(a-1, b))
				{
					return true;
				}
				else return false;
			}
			else return false;
		}
		
		// 3. if i = 0 and j > 0
		else if(a == 0 && b > 0)
		{
			if(Y.charAt(b-1) == Z.charAt(a+b-1))
			{
				if(shuffleRecSolve(a,b-1))
				{
					return true;
				}
				else return false;
			}
			else return false;
		}
		
		// 4. if i > 0 and j > 0
		else if(a > 0 && b > 0)
		{
			if(X.charAt(a-1) == Z.charAt(a+b-1))
			{
				if(shuffleRecSolve(a-1, b))
				{
					return true;
				}
				else if(Y.charAt(b-1) == Z.charAt(a+b-1))
				{
					if(shuffleRecSolve(a, b-1))
					{
						return true;
					}
					else return false;
				}
			}
			else if(Y.charAt(b-1) == Z.charAt(a+b-1))
			{
				if(shuffleRecSolve(a, b-1))
				{
					return true;
				}
				else if(Y.charAt(a-1) == Z.charAt(a+b-1))
				{
					if(shuffleRecSolve(a-1, b))
					{
						return true;
					}
					else return false;
				}
			}	
			else return false;
		}
		return false;
	}
	
	/**
	 * Return the string to print out
	 * 
	 * @return
	 */
	private boolean shuffleRecResult()
	{
		// print the final yes-no solution whether if Z is shuffle of X and Y
		if(shuffleRecSolve(a, b))
		{
			System.out.println("yes");	
			// print the number of recursive calls
			System.out.println("Number of recursive calls: " + recurNum + "\n");
			return false;
		}
		else
		{
			System.out.println("no");
			// print the number of recursive calls
			System.out.println("Number of recursive calls: " + recurNum + "\n");
			return true;
		}
	}
}