import java.io.*;
import java.util.*;

/**
 * 0-1 Knapsack Problem by the dynamic programming
 * 
 * @author ahramkim
 */
public class KnapsackDP 
{
	static int numOfItems;			// the number of items
	static int maxWeight;			// the maximum weight a knapsack
	String weightFile = null;		// a file containing each individual item's weight
	String valueFile = null;		// a file containing each individual item's value
	
	int[] wList;
	int[] vList;

	static int selected[];
	static int refTable;
	int table[][];
	
	public static void main(String[] args)
	{
		try
		{
			new KnapsackDP(args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * 0-1 Knapsack for the dynamic programming approach
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 */
	private KnapsackDP(String[] args) throws FileNotFoundException
	{	
		if(args.length < 4 || args.length > 4)
		{
			System.err.println("Usage: java KnapsackDP <n(number of items)> "
					+ "<W(maximum weight)> <w.txt(individual item's weight> "
					+ "<v.txt(individual item's value)> \n");;
		}
		else
		{
			numOfItems = Integer.parseInt(args[0]);
			maxWeight = Integer.parseInt(args[1]);
			weightFile = args[2];
			valueFile = args[3];
			
			selected = new int[numOfItems + 1];
			table = new int[numOfItems + 1][maxWeight + 1];
			
			initializeTable(numOfItems, maxWeight);
			storeValueToArray(numOfItems, weightFile, valueFile);
			knapsackResult(numOfItems, maxWeight, selected);
		}
	}
	
	/**
	 * 0-1 Knapsack Solve Implementation for the dynamic programming approach
	 * 
	 * @param N
	 * @param W
	 * @param selected
	 * @return
	 * @throws FileNotFoundException
	 */
	public int knapsackDPSolve(int N, int W, int selected[]) throws FileNotFoundException
	{
		// Build table t[][] in bottom up manner
		for(int i = 0; i <= N; i++)
		{
			refTable++;
			
			for(int j= 0; j <= W; j++)
			{
				refTable++;
				// if i = 0 or j = 0
				if(i == 0 || j == 0)
				{
					refTable++;
					table[i][j] = 0;
				}
				//if W_i > j
				else if(wList[i - 1] > j)
				{
					refTable++;
					selected[i] = 0;
					table[i][j] = table[i - 1][j];

				}
				//if W_i <= j
				else if(wList[i - 1] <= j)
				{
					selected[i] = 1;
					table[i][j] = max(table[i - 1][j], (table[i - 1][j - wList[i - 1]] + vList[i - 1]));
				}
			}
		}
		return table[N][W];
	}
	
	/**
	 * Get the selected index of the value
	 * 
	 * @param N
	 * @param W
	 * @param selected
	 * @return
	 * @throws FileNotFoundException
	 */
	public int getSelectedIndex(int N, int W, int selected[]) throws FileNotFoundException
	{
		// Get the selected index of the value (Optimal Solution)
		int i, j, y = 0;		// to index in selected
		
		for(i = N; i > 0; i--)
		{
			if((W-wList[i-1] >= 0) && (table[i][W] - table[i-1][W-wList[i-1]] == vList[i-1]))
			{
				selected[y++] = i - 1;
				W -= wList[i - 1];
			}
		}
		
		System.out.println("Optimal solution: ");
		System.out.print("{ ");
		
		for(j = y-1; j >= 0; j--)
		{
			System.out.print(selected[j]+1 + ", ");
		}
		System.out.println("} ");
		
		return table[N][W];
	}
	
	/**
	 * Store the value from the each files and make an array
	 * 
	 * @param N
	 * @param wFile
	 * @param vFile
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	private void storeValueToArray(int N, String wFile, String vFile) throws FileNotFoundException
	{
		Scanner wFileScan = new Scanner(new File(wFile));
		Scanner vFileScan = new Scanner(new File(vFile));
		
		wList = new int[N];
		vList = new int[N];
				
		// Store the value from w.txt file to the array
		int i=0;
		while(wFileScan.hasNextLine())
		{
			wList[i] = Integer.parseInt(wFileScan.nextLine());
			i++;
		}

		// Store the value from v.txt file to the array
		i = 0;
		while(vFileScan.hasNextLine())
		{
			vList[i] = Integer.parseInt(vFileScan.nextLine());
			i++;
		}
	}
	
	/**
	 * Get a maximum number
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int max(int a, int b)
	{
		// (a > b) ? a : b;
		if(a > b)
		{
			return a;
		}
		else 
		{
			return b;
		}
	}
	
	/**
	 * Initialize Table with the default value -1.
	 * The size is based on string length.
	 * 
	 * @param N
	 * @param W
	 */
	private void initializeTable(int N, int W)
	{
		// Initialize the table 
		table = new int[N + 1][W + 1];
		
		for(int row = 0; row < (N + 1); row++)
		{
			for(int col = 0; col < (W + 1); col++)
			{
				// Set the table value with -1
				table[row][col] = -1;
			}
		}
	}
	
	/**
	 * Return the string to print out
	 * 
	 * @param N
	 * @param W
	 * @param selected
	 * @return
	 * @throws FileNotFoundException
	 */
	private boolean knapsackResult(int N, int W, int selected[]) throws FileNotFoundException
	{
		knapsackDPSolve(N, W, selected);
		
		// print table which is constructed
		for(int row = 0; row < (N + 1); row++)
		{
			for(int col = 0; col < (W + 1); col++)
			{
				int board = table[row][col];
				String boardString = String.format("%03d", board);
				System.out.print(boardString + " ");
			}
			System.out.println();
		}
		
		// print output the optimal solution, total weight,
		// optimal value, and number of table references.
		System.out.println();
		getSelectedIndex(N, W, selected);
		System.out.println("Total Weight : " + W);
		System.out.println("Optimal Value : " + table[N][W]);
		System.out.println("Number of table references : " + refTable);
		
		return false;
	}
}
