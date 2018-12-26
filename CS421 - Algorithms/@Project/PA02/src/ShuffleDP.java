import java.sql.Ref;

/**
 * Shuffle-ness Problem by the dynamic programming approach
 * 
 * @author ahramkim
 */
public class ShuffleDP
{
	static String X, Y, Z;
	static int a, b, c;
	static int tableRefNum;
	static int table[][];
	
	public static void main(String[] args) 
	{
		 try
		 {
			 new ShuffleDP(args);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 System.exit(1);
		 }
	}
	
	/**
	 * Shuffle-ness Problem for the dynamic programming approach
	 * 
	 * @param args
	 */
	private ShuffleDP(String[] args)
	{	
		if(args.length < 3 || args.length > 3)
		{
			System.err.println("Usage : java ShuffleDP <String X> <String Y> <String Z> \n");
		}
		else 
		{
			X = args[0];
			Y = args[1];
			Z = args[2];
	
			a = X.length() + 1;
			b = Y.length() + 1;
			c = Z.length() + 2;
			
			if(c != (a + b))
			{
				System.err.println("The length of Z should be equal the length of X plus "
								+ "the length of Y. Type again.");
			}
			else
			{
				initializeTable(a, b);
				shuffleDPSolve(); 
			}
		}	
	}
	
	/**
	 * Shuffle-ness Solve Implementation for the dynamic programming approach
	 */
	private void shuffleDPSolve()
	{
		// 1. if i = 0 and j = 0
		int i = 0, j = 0;
		table[i][j] = 1;
		
		// 2. if i > 0 and j = 0;
		for(i = 0; i < X.length(); i++)
		{
			if(X.charAt(i) == Z.charAt(i))
			{
				table[i+1][0] = table[i][0];
				tableRefNum++;
 			}
			else 
			{
				table[i+1][0] = 0;
			}
		}
		
		// 3. if i = 0 and j > 0
		for(j = 0; j < Y.length(); j++)
		{
			if(Y.charAt(j) == Z.charAt(j))
			{
				table[0][j+1] = table[0][j];
				tableRefNum++;
			}
			else
			{
				table[0][j+1] = 0;
			}
		}
		
		// 4. if i > 0 and j > 0
		for(i = 0; i < X.length(); i++)
		{
			for(j = 0; j < Y.length(); j++)
			{	
				if(X.charAt(i) == Z.charAt(i+j+1))
				{
					tableRefNum++;
					if(table[i][j+1] == 1)
					{
						table[i+1][j+1] = 1;
					}
					else
					{
						table[i+1][j+1] = 0;
					}
				}
				
				if(Y.charAt(j) == Z.charAt(i+j+1))
				{
					tableRefNum++;
					if(table[i+1][j] == 1)
					{
						table[i+1][j+1] = 1;
					}
					else
					{
						table[i+1][j+1] = 0;
					}
				}
				
				if(X.charAt(i) == Y.charAt(j))
				{
					if(table[i][j+1] == 1 | table[i+1][j] == 1)
					{
						table[i+1][j+1] = 1;
					}
					else
					{
						table[i+1][j+1] = 0;
					}
				}
				if((X.charAt(i) != Z.charAt(i+j+1)) &&(Y.charAt(j) != Z.charAt(i+j+1))) 
				{
					table[i+1][j+1] = 0;
				}
			}	
		}
		shuffleResult(table, a, b);
	}

	/**
	 * Initialize Table with the default value -1.
	 * The size is based on string length.
	 * 
	 * @param a
	 * @param b
	 */
	private void initializeTable(int a, int b)
	{
		// Initialize the table 
		table = new int[a][b];
		
		for(int row = 0; row < a; row++)
		{
			for(int col = 0; col < b; col++)
			{
				// Set the table value with -1
				table[row][col] = -1;
			}
		}
	}
	
	/**
	 * Return the string to print out the result
	 * 
	 * @param table
	 * @param a
	 * @param b
	 */
	private void shuffleResult(int[][] table, int a, int b)
	{		
		// print table which is constructed
		for(int row = 0; row < a; row++)
		{
			for(int col = 0; col < b; col++)
			{
				int board = table[row][col];
				String boardString = String.format("%02d", board);
				System.out.print(boardString + " ");
			}
			System.out.println();
		}
		
		// print the final yes-no solution whether if Z is shuffle of X and Y
		boolean checkShuffle = false;
		int i = a - 1;
		int j = b - 1;
		
		while(i > 0 || j > 0)
		{
			if(i > 0 && table[i-1][j] == 1)
			{
				i--;
				if(i == 0 && j == 0)
				{
					checkShuffle = true;
				}
			}
			else if(j > 0 && table[i][j-1] == 1)
			{
				j--;
				if(i == 0 && j == 0)
				{
					checkShuffle = true;
				}
			}
			else if(i > 0 && j > 0 && table[i-1][j-1] == 1)
			{
				i--;
				j--;
				if(i == 0 && j == 0)
				{
					checkShuffle = true;
				}
			}
			else break;
		}
		
		if(checkShuffle)
		{
			System.out.println("yes");	
			// print the number of recursive calls
			System.out.println("Number of recursive calls: " + tableRefNum + "\n");
		}
		else
		{
			System.out.println("no");
			// print the number of recursive calls
			System.out.println("Number of recursive calls: " + tableRefNum + "\n");
		}
	}
}
