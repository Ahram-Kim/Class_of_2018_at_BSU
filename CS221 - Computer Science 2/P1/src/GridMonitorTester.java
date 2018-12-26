import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for implementations of the GridMonitorInterface.
 * Uses TestNG framework.  
 * @author CS 221
 */
public class GridMonitorTester
{	
	/********** Variables for Tests *******************/
	// Test file names 
	private static final String sample = "sample.txt"; 
	private static final String sample4x5 = "sample4x5.txt"; 
	private static final String negatives = "negatives.txt"; 
	private static final String oneByOne = "oneByOne.txt"; 
	private static final String sampleDoubles = "sampleDoubles.txt"; 
	private static final String wideRange = "wideRange.txt"; 
    
	// Base grid for given files 
	private static final double[][] sampleBaseGrid = { {2, 10, 7},
																					{4, 5, 8},
																					{5, 6, 9} };
	private static final double[][] negativesBaseGrid = { {-5, -4, -5},
																						{-2, -1, -2},
																						{-1, -3, -1} }; 
	private static final double[][] oneByOneBaseGrid = { {5} };
	private static final double[][] sample4x5BaseGrid = { {1, 3, 7, 6, 5},
																						  {4, 10, 8, 7, 4},
																						  {5, 5, 7, 8, 7},
																						  {3, 6, 8, 9, 6} };
	private static final double[][] sampleDoublesBaseGrid = { {2.5, 10.5, 7.5},
																							    {4.5, 5.5, 8.5},
																							    {5.5, 6.5, 9.5} };
	private static final double[][] wideRangeBaseGrid= { {1, 1, 1},
																						 {-2000, 10, 2000},
																						 {1, 1, 1} };
	
	// Surrounding sum grid for given files 
	private static final double[][] sampleSumGrid = { 	{18, 24, 32},
																					{16, 28, 29},
																					{20, 25, 32} };
	private static final double[][] negativesSumGrid = { {-16, -15, -16},
																						{-9, -11, -9},
																						{-7, -6, -7} }; 
	private static final double[][] oneByOneSumGrid = { {20} };
	private static final double[][] sample4x5SumGrid = { {9, 21, 24, 25, 20},
																						  {20, 20, 31, 26, 23},
																						  {17, 28, 29, 30, 25},
																						  {17, 22, 30, 31, 28} };
	private static final double[][] sampleDoublesSumGrid = { {20, 26, 34},
																							    {18, 30, 31},
																							    {22, 27, 34} };
	private static final double[][] wideRangeSumGrid= { {-1997, 13, 2003},
																						 {-1988, 2, 2012},
																						 {-1997, 13, 2003} };
	// Surrounding average grid for given files 
	private static final double[][] sampleAvgGrid = { 	{4.5, 6.0, 8.0},
																					{4.0, 7.0, 7.25},
																					{5.0, 6.25, 8.0} };
	private static final double[][] negativesAvgGrid = { {-4, -3.75, -4},
																						{-2.25, -2.75, -2.25},
																						{-1.75, -1.5, -1.75} }; 
	private static final double[][] oneByOneAvgGrid = { {5} };
	private static final double[][] sample4x5AvgGrid = { {2.25, 5.25, 6, 6.25, 5},
																						 {5, 5, 7.75, 6.5, 5.75},
																						 {4.25, 7, 7.25, 7.5, 6.25},
																						 {4.25, 5.5, 7.5, 7.75, 7}};
	private static final double[][] sampleDoublesAvgGrid = { {5, 6.5, 8.5},
																							    {4.5, 7.5, 7.75},
																							    {5.5, 6.75, 8.5} };
	private static final double[][] wideRangeAvgGrid= {	 {-499.25, 3.25, 500.75},
																						 {-497, 0.5, 503},
																						 {-499.25, 3.25, 500.75} };
	// Delta grid for given files 
	private static final double[][] sampleDeltaGrid = { 	{2.25, 3.0, 4.0},
																						{2.0, 3.5, 3.625},
																						{2.5, 3.125, 4.0} };
	private static final double[][] negativesDeltaGrid = { {-2, -1.875, -2},
																						{-1.125, -1.375, -1.125},
																						{-0.875, -0.75, -0.875} }; 
	private static final double[][] oneByOneDeltaGrid = { {2.5} };
	private static final double[][] sample4x5DeltaGrid = { {1.125, 2.625, 3, 3.125, 2.5},
																						   {2.5, 2.5, 3.875, 3.25, 2.875},
																						   {2.125, 3.5, 3.625, 3.75, 3.125},
																						   {2.125, 2.75, 3.75, 3.875, 3.5} };
	private static final double[][] sampleDoublesDeltaGrid = { {2.5, 3.25, 4.25},
																							    {2.25, 3.75, 3.875},
																							    {2.75, 3.375, 4.25} };
	private static final double[][] wideRangeDeltaGrid= { {-249.625, 1.625, 250.375},
																						 {-248.5, 0.25, 251.5},
																						 {-249.625, 1.625, 250.375} };
	// Danger grid for given files 
	private static final boolean[][] sampleDangerGrid = { 	{true, true, false},
																							{false, false, false},
																							{false, false, false} };
	private static final boolean[][] negativesDangerGrid = { 	{true, true, true},
																								{true, true, true},
																								{true, true, true} };
	private static final boolean[][] oneByOneDangerGrid = { 	{false} };
	private static final boolean[][] sample4x5DangerGrid = { 	{true, false, false, false, false},
																								{false, true, false, false, false},
																								{false, false, false, false, false},
																								{false, false, false, false, false} };
	private static final boolean[][] sampleDoublesDangerGrid = { 	{false, true, false},
																										{false, false, false},
																										{false, false, false} };
	private static final boolean[][] wideRangeDangerGrid= { 	{true, true, true},
																								{true, true, true},
																								{true, true, true} };

	// Objects arrays for DataProviders 
	private static final Object[][] FILES = {  	{sample}, 
																	{negatives}, 
																	{oneByOne}, 
																	{sample4x5}, 
																	{sampleDoubles}, 
																	{wideRange} };

	private static final Object[][] BASE_GRIDS = {  	{sample, sampleBaseGrid}, 
			 																	{negatives, negativesBaseGrid}, 
			 																	{oneByOne, oneByOneBaseGrid}, 
			 																	{sample4x5, sample4x5BaseGrid}, 
			 																	{sampleDoubles, sampleDoublesBaseGrid}, 
			 																	{wideRange, wideRangeBaseGrid} };

	private static final Object[][] SUM_GRIDS = {  	{sample, sampleSumGrid}, 
																				{negatives, negativesSumGrid}, 
																				{oneByOne, oneByOneSumGrid}, 
																				{sample4x5, sample4x5SumGrid}, 
																				{sampleDoubles, sampleDoublesSumGrid}, 
																				{wideRange, wideRangeSumGrid} };

	private static final Object[][] AVG_GRIDS = {  	{sample, sampleAvgGrid}, 
																				{negatives, negativesAvgGrid}, 
																				{oneByOne, oneByOneAvgGrid}, 
																				{sample4x5, sample4x5AvgGrid}, 
																				{sampleDoubles, sampleDoublesAvgGrid}, 
																				{wideRange, wideRangeAvgGrid} };

	private static final Object[][] DELTA_GRIDS = {  	{sample, sampleDeltaGrid}, 
																				{negatives, negativesDeltaGrid}, 
																				{oneByOne, oneByOneDeltaGrid}, 
																				{sample4x5, sample4x5DeltaGrid}, 
																				{sampleDoubles, sampleDoublesDeltaGrid}, 
																				{wideRange, wideRangeDeltaGrid} };

	private static final Object[][] DANGER_GRIDS = {  	{sample, sampleDangerGrid}, 
																					{negatives, negativesDangerGrid}, 
																					{oneByOne, oneByOneDangerGrid}, 
																					{sample4x5, sample4x5DangerGrid}, 
																					{sampleDoubles, sampleDoublesDangerGrid}, 
																					{wideRange, wideRangeDangerGrid} };

	private static final Object[][] DANGER_GRIDS_ENCAPSULATION = {  	{sample, sampleDangerGrid, false}, 
																													{negatives, negativesDangerGrid, false}, 
																													{oneByOne, oneByOneDangerGrid, true}, 
																													{sample4x5, sample4x5DangerGrid, false}, 
																													{sampleDoubles, sampleDoublesDangerGrid, true}, 
																													{wideRange, wideRangeDangerGrid, false} };

	/******************** Tests **************************************/ 
	
	/**
	 * Test: GridMonitor implements GridMonitorInterface
	 * Expected Result: No exceptions 
	 */
	@SuppressWarnings("unused")
	@Test
	public void testGridMonitorInterface() 
	{
		GridMonitor grid = null;
		// invalid assignment will prevent the test class from compiling
		GridMonitorInterface gridInterface = grid;
	}
	
	/**
	 * Test: GridMonitor handles FileNotFoundException
	 * Expected Result: No exception 
	 */
	@SuppressWarnings("unused")
	@Test
	public void testFileNotFoundException() 
	{
		GridMonitor grid = new GridMonitor("NoSuchFile.txt");
	}
	
	/**
	 * Test: GridMonitor constructor 
	 * Expected Result: No exceptions
	 */
	@SuppressWarnings("unused")
	@Test(dataProvider = "files")
	public void testConstructor(String fileName) 
	{
		GridMonitor grid = new GridMonitor(fileName);
	}

	/**
	 * Test: getBaseGrid method
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "baseGrids")
	public void testGetBaseGrid(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.getBaseGrid(gridMonitor, expectedResult);
	}

	/**
	 * Test: getSurroundingSumGrid method 
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "sumGrids")
	public void testGetSurroundingSumGrid(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.getSurroundingSumGrid(gridMonitor, expectedResult);
	}

	/**
	 * Test: getSurroundingAvgGrid method  
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "avgGrids")
	public void testGetSurroundingAvgGrid(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.getSurroundingAvgGrid(gridMonitor, expectedResult);
	}

	/**
	 * Test: getDeltaGrid method  
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "deltaGrids")
	public void testGetDeltaGrid(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.getDeltaGrid(gridMonitor, expectedResult);
	}

	/**
	 * Test: getDangerGrid method 
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "dangerGrids")
	public void testGetDangerGrid(String fileName, boolean[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.getDangerGrid(gridMonitor, expectedResult);
	}
	
	/**
	 * Test: Encapsulation of base grid
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "baseGrids")
	public void testBaseGridEncapsulation(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.baseGridEncapsulation(gridMonitor, expectedResult);
	}

	/**
	 * Test: Encapsulation of surrounding sum grid  
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "sumGrids")
	public void testSurroundingSumGridEncapsulation(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.surroundingSumGridEncapsulation(gridMonitor, expectedResult);
	}

	/**
	 * Test: Encapsulation of surrounding average grid  
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "avgGrids")
	public void testSurroundingAvgGridEncapsulation(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.surroundingAvgGridEncapsulation(gridMonitor, expectedResult);
	}

	/**
	 * Test: Encapsulation of delta grid  
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "deltaGrids")
	public void testDeltaGridEncapsulation(String fileName, double[][] expectedResult) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.deltaGridEncapsulation(gridMonitor, expectedResult);
	}

	/**
	 * Test: Encapsulation of danger grid 
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "dangerGridsEncapsulation")
	public void testDangerGridEncapsulation(String fileName, boolean[][] expectedResult, boolean oppositeValue) 
	{
		GridMonitor gridMonitor = new GridMonitor(fileName);
		GridMonitorTestCase.dangerGridEncapsulation(gridMonitor, expectedResult, oppositeValue);
	}


	/******************** Data Providers *******************************/ 
	
	/**
	 * Data: Strings - names of test files 
	 * @return array of Strings 
	 */
	@DataProvider
	   public static Object[][] files() 
	   {
	      return FILES; 
	   }
	
	/**
	 * Data: Strings - names of test files, 2D array of base grid values 
	 * @return Strings, 2D array of doubles 
	 */
	@DataProvider
	   public static Object[][] baseGrids() 
	   {
	      return BASE_GRIDS; 
	   }
	
	/**
	 * Data: Strings - names of test files, 2D array of surrounding sum grid values 
	 * @return Strings, 2D array of doubles 
	 */
	@DataProvider
	   public static Object[][] sumGrids() 
	   {
	      return SUM_GRIDS; 
	   }

	/**
	 * Data: Strings - names of test files, 2D array of surrounding average grid values 
	 * @return Strings, 2D array of doubles 
	 */
	@DataProvider
	   public static Object[][] avgGrids() 
	   {
	      return AVG_GRIDS; 
	   }

	/**
	 * Data: Strings - names of test files, 2D array of delta grid values 
	 * @return Strings, 2D array of doubles 
	 */
	@DataProvider
	   public static Object[][] deltaGrids() 
	   {
	      return DELTA_GRIDS; 
	   }

	/**
	 * Data: Strings - names of test files, 2D array of danger grid values 
	 * @return Strings, 2D array of boolean values
	 */
	@DataProvider
	   public static Object[][] dangerGrids() 
	   {
	      return DANGER_GRIDS; 
	   }
	
	/**
	 * Data: Strings - names of test files, 2D array of danger grid values, boolean values 
	 * @return Strings, 2D array of boolean values, boolean values
	 */
	@DataProvider
	   public static Object[][] dangerGridsEncapsulation() 
	   {
	      return DANGER_GRIDS_ENCAPSULATION; 
	   }


}
