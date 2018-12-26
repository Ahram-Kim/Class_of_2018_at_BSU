import java.io.*;
import java.util.*;

/**
 * 0-1 Knapsack Problem by the pure recursion
 * 
 * @author ahramkim
 */
public class KnapsackRec 
{
	String str = "";
	
	int numOfItems;					// the number of items
	int maxWeight;					// the maximum weight a knapsack
	String weightFile = null;		// a file containing each individual item's weight
	String valueFile = null;		// a file containing each individual item's value
	
	int[] wList;
	int[] vList;
	
	static int[] selected;
	static int recurNum;
	
	public static void main(String[] args) 
	{
		try
		{
			new KnapsackRec(args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * 0-1 Knapsack for the pure recursion approach
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 */
	private KnapsackRec(String[] args) throws FileNotFoundException
	{	
		if(args.length < 4 || args.length > 4)
		{
			System.err.println("Usage: java KnapsackRec <n(number of items)> "
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

			storeValueToArray(numOfItems, weightFile, valueFile);
			toString(numOfItems, maxWeight, selected);
		}
	}
	
	/**
	 * 0-1 Knapsack Solve Implementation for the pure recursion approach
	 * 
	 * @param N
	 * @param W
	 * @param selected
	 * @return
	 * @throws FileNotFoundException
	 */
	public int knapsackRecSolve(int N, int W, int selected[]) throws FileNotFoundException
	{
		recurNum++;
		
		if(N == 0 || W == 0)
		{
			return 0;
		} 
		
		if(wList[N - 1] > W)
		{
			return knapsackRecSolve(N - 1, W, selected);
		}
		else if(wList[N - 1] <= W)
		{
			return max(knapsackRecSolve(N - 1, W, selected), 
					knapsackRecSolve(N - 1, W - wList[N - 1], selected) + vList[N - 1]);
		}
		return knapsackRecSolve(N, W, selected);
	}
	
	/**
	 * Selected Index Solve Implementation for the pure recursion approach
	 * 
	 * @param N
	 * @param W
	 * @param selected
	 * @return
	 * @throws FileNotFoundException
	 */
	public int getSelectedSolve(int N, int W, int selected[]) throws FileNotFoundException
	{
		if(N == 0 || W == 0)
		{
			return 0;
		} 
		
		if(wList[N - 1] > W)
		{
			return getSelectedSolve(N - 1, W, selected);
		}
		else if(wList[N - 1] <= W)
		{
			return max(getSelectedSolve(N - 1, W, selected), 
					getSelectedSolve(N - 1, W - wList[N - 1], selected) + vList[N - 1]);
		}
		return getSelectedSolve(N, W, selected);
	}
	
	/**
	 * Get the selected index of the value
	 * 
	 * @param N
	 * @param W
	 * @param selected
	 * @throws FileNotFoundException
	 */
	public void getSelectedIndex(int N, int W, int[] selected) throws FileNotFoundException
	{
		int i, j, y =0;
		
		for(i = N; i > 0; i--)
		{
			if((W-wList[i-1] >= 0) && getSelectedSolve(i, W, selected) - 
					getSelectedSolve(i-1, W-wList[i-1], selected) == vList[i-1])
			{
				selected[y++] = i - 1;
				W -= wList[i-1];
			}
		}
		System.out.println("Optimal solution: ");
		System.out.print("{ ");
		
		for(j = y-1; j >= 0; j--)
		{
			System.out.print(selected[j]+1 + ", ");
		}
		System.out.println("} ");
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
	 * Return the string to print out
	 * 
	 * @param N
	 * @param W
	 * @param selected
	 * @return
	 * @throws FileNotFoundException
	 */
	private boolean toString(int N, int W, int[] selected) throws FileNotFoundException
	{
		// print output the optimal solution, total weight,
		// optimal value, and number of recursive calls.
		getSelectedIndex(N, W, selected);
		System.out.println("Total Weight : " + W);
		System.out.println("Optimal Value : " + knapsackRecSolve(N, W, selected));
		System.out.println("Number of recursive calls : " + recurNum);
		return false;
	}
}
