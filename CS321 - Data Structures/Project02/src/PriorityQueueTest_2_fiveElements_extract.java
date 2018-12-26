import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing for implementation of PQueue objects: 
 * Tests for Change Scenario: 5 elements -> extract -> 4 element priority queue
 * 
 * @author CS 321
 */

public class PriorityQueueTest_2_fiveElements_extract
{
	// PQueue running tests on
	PQueue<Integer> pQueue;
	
	//****** Constants used in tests *****************
	// Objects in pQueue
	private static final Integer[] OBJECTS = { TestCase.A, TestCase.B, TestCase.C, TestCase.D, TestCase.E }; 
	// Priorities for objects in pQueue
	private static final int[] PRIORITIES = { TestCase.KEY_A, TestCase.KEY_B, TestCase.KEY_C, TestCase.KEY_D, TestCase.KEY_E }; 
	// Valid indexes to elements in pQueue 
	private static final Object[][] VALID_INDEXES = { {1}, {2}, {3}, {4} };
	// Max element in pQueue
	private static final Integer MAX = TestCase.D; 
	// An element not in pQueue
	private static final Integer OBJECT = TestCase.K; 
	// Priority of element not in pQueue
	private static final Integer KEY = TestCase.KEY_K; 
	// Priority greater than max in pQueue
	private static final int BIG_KEY = TestCase.KEY_M; 
	// Number of objects in pQueue
	private static final int SIZE = 4;

	//********************Before Each Test Method********************
	/**
	 * Sets up pQueue for testing.
	 */
	@BeforeMethod
	public void initialize()
	{
		// create an empty pQueue
		pQueue = TestCase.newPQueue(OBJECTS, PRIORITIES);
		pQueue.extractMax(); 
	}
	
	//******************* Tests ***************************
	/**
	 * Test: maximum() - returns reference to max element in pQueue
	 * but doesn't remove it. 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testMaximum()
	{
		TestCase.maximum(pQueue, MAX);
	}	
	
	/**
	 * Test: extractMax() - removes / returns reference to max element in pQueue 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testExtractMax()
	{
		TestCase.extractMax(pQueue, MAX);
	}	

	/**
	 * Test: increaseKey() - increase priority of element at given index 
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "validIndexes")
	public void testIncreaseKey(int validIndex)
	{
		TestCase.increaseKey(pQueue, validIndex, BIG_KEY);
	}	
	
	/**
	 * Test: insert(object) - inserts given object into pQueue
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testInsert()
	{
		TestCase.insert(pQueue, OBJECT, KEY);
	}

	/**
	 * Test: size() - returns number of objects in pQueue
	 * Expected Result: SIZE
	 */
	@Test()
	public void testSize()
	{
		TestCase.size(pQueue, SIZE);
	}

	/**
	 * Test: isEmpty() - whether pQueue is empty
	 * Expected Result: false
	 */
	@Test()
	public void testIsEmpty()
	{
		TestCase.isEmpty(pQueue, false);
	}

	//********************* Data Providers ****************
	
	/**
    * Data: Indexes of elements in pQueue.
    * 
    * @return 2D array (second dimension empty) indexes of pQueue
    */
   @DataProvider
   public static Object[][] validIndexes() 
   {
      return VALID_INDEXES; 
   }

}
