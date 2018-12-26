import org.testng.Assert;

/**
 * Test-case class for implementations of GridMonitor class.  
 * Uses TestNG framework. 
 * @author CS221
 */

public class GridMonitorTestCase
{
	// margin of error two doubles have to be within to be considered the same value 
	private static final double TOLERANCE = Math.pow(10, -14);
	
	/******************** Test Cases *****************************/

	/**
	 * Tests getBaseGrid() method on given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of base grid values
	 */
	public static void getBaseGrid(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getBaseGrid(); 
		Assert.assertTrue(isEquivalentDoubleArrays(result, expectedResult));
	}
	
	/**
	 * Tests getSurroundingSumGrid() method on given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of sums of values surrounding each cell
	 */
	public static void getSurroundingSumGrid(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getSurroundingSumGrid(); 
		Assert.assertTrue(isEquivalentDoubleArrays(result, expectedResult));	
	}
	
	/**
	 * Tests getSurroundingAvgGrid() method on given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of averages of values surrounding each cell
	 */
	public static void getSurroundingAvgGrid(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getSurroundingAvgGrid(); 
		Assert.assertTrue(isEquivalentDoubleArrays(result, expectedResult));		
	}
	
	/**
	 * Tests getDeltaGrid() method on given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of max deltas of values surrounding each cell
	 */
	public static void getDeltaGrid(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getDeltaGrid(); 
		Assert.assertTrue(isEquivalentDoubleArrays(result, expectedResult));			
	}

	/**
	 * Tests getDangerGrid() method on given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - boolean[][] - whether a given cell is in Danger Zone or not
	 */
	public static void getDangerGrid(GridMonitor gridMonitor, boolean[][] expectedResult)
	{
		boolean[][] result = gridMonitor.getDangerGrid(); 
		Assert.assertTrue(isEquivalentBooleanArrays(result, expectedResult));			
	}
	
	/**
	 * Tests encapsulation of base grid for given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of base grid values
	 */
	public static void baseGridEncapsulation(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getBaseGrid(); 
		result[0][0] = 0.0;
		Assert.assertFalse(isEquivalentDoubleArrays(result, expectedResult));
	}
	
	/**
	 * Tests encapsulation of surrounding sum grid for given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of sums of values surrounding each cell
	 */
	public static void surroundingSumGridEncapsulation(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getSurroundingSumGrid();
		result[0][0] = 0.0;
		Assert.assertFalse(isEquivalentDoubleArrays(result, expectedResult));	
	}
	
	/**
	 * Tests encapsulation of surrounding average grid for given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of averages of values surrounding each cell
	 */
	public static void surroundingAvgGridEncapsulation(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getSurroundingAvgGrid(); 
		result[0][0] = 0.0;
		Assert.assertFalse(isEquivalentDoubleArrays(result, expectedResult));		
	}
	
	/**
	 * Tests encapsulation of delta grid for given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - double[][] of max deltas of values surrounding each cell
	 */
	public static void deltaGridEncapsulation(GridMonitor gridMonitor, double[][] expectedResult)
	{
		double[][] result = gridMonitor.getDeltaGrid(); 
		result[0][0] = 0.0;
		Assert.assertFalse(isEquivalentDoubleArrays(result, expectedResult));			
	}

	/**
	 * Tests encapsulation of danger grid for given GridMonitor object. 
	 * 
	 * @param gridMonitor - implementation of the GridMonitor interface
	 * @param expectedResult - boolean[][] - whether a given cell is in Danger Zone or not
	 * @param oppositeValue - boolean - value opposite expected boolean at expectedResult[0][0]
	 */
	public static void dangerGridEncapsulation(GridMonitor gridMonitor, boolean[][] expectedResult, boolean oppositeValue)
	{
		boolean[][] result = gridMonitor.getDangerGrid(); 
		result[0][0] = oppositeValue;
		Assert.assertFalse(isEquivalentBooleanArrays(result, expectedResult));			
	}

	
	/******************** Utility Functions *****************************/
	
	/** Compare two doubles and return true if they are within TOLERANCE, else false
	 * @param v1 - first value to compare
	 * @param v2 - second value to compare
	 * @return boolean - whether values are within TOLERANCE
	 */
	private static boolean isClose(double v1, double v2) 
	{
		return (Math.abs(v1 - v2) < TOLERANCE);
	}

	/** Compare two two-dimensional double arrays for equivalence.
	 * @param a1 - double[][]
	 * @param a2 - double[][]
	 * @return boolean - true if all values are within the given tolerance; false otherwise
	 */
	private static boolean isEquivalentDoubleArrays(double[][] a1, double[][] a2) 
	{
		// return true, unless set otherwise
		boolean equivalent = true;
		
		// not true if number of rows don't match 
		if (a1.length != a2.length || (a1.length > 1 && a1[0].length != a2[0].length)) 
		{
			equivalent = false;
		} 
		else 
		{
			// loop through comparing each cell 
			for (int row = 0; row < a1.length; row++) 
			{
				for (int col = 0; col < a1[0].length; col++) 
				{
					// not true is number of columns don't match 
					if (row >= a2.length || a1[row].length != a2[row].length) 
					{
						equivalent = false;
					} 
					else 
					{
						// not true if values not within level of tolerance 
						if (!isClose(a1[row][col], a2[row][col])) 
						{
							equivalent = false;
						}
					}
				}
			}
		}
		return equivalent;
	}
	
	/** Compare two two-dimensional boolean arrays for equivalence.
	 * @param a1 first - boolean[][]
	 * @param a2 second - boolean[][]
	 * @return boolean - true if all values are identical; false otherwise
	 */
	private static boolean isEquivalentBooleanArrays(boolean[][] a1, boolean[][] a2) 
	{
		// assume true, unless set otherwise
		boolean equivalent = true;
		
		// not true if number of rows not the same 
		if (a1.length != a2.length || (a1.length > 1 && a1[0].length != a2[0].length)) 
		{
			equivalent = false;
		} 
		else 
		{
			// loop through comparing cells 
			for (int row = 0; row < a1.length; row++) 
			{
				for (int col = 0; col < a1[0].length; col++) 
				{
					// not true, if number of columns not same 
					if (row >= a2.length || a1[row].length != a2[row].length) 
					{
						equivalent = false;
					} 
					else 
					{
						// not true is values not the same
						if (a1[row][col] != a2[row][col]) 
						{
							equivalent = false;
						}
					}
				}
			}
		}
		return equivalent;
	}

}
