import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing for implementation of PQueue objects: 
 * Tests for Change Scenario: 5 elements -> insert -> 6 element priority queue
 * 
 * @author CS 321
 */

public class PriorityQueueTest_5_fiveElements_insert
{
	// PQueue running tests on
	PQueue<Integer> PQueue;
	
	//****** Constants used in tests *****************
	// Objects in PQueue
	private static final Integer[] OBJECTS = { TestCase.A, TestCase.B, TestCase.C, TestCase.D, TestCase.E }; 
	// Priorities for objects in PQueue
	private static final int[] PRIORITIES = { TestCase.KEY_A, TestCase.KEY_B, TestCase.KEY_C, TestCase.KEY_D, TestCase.KEY_E }; 
	// Valid indexes to elements in PQueue 
	private static final Object[][] VALID_INDEXES = { {1}, {2}, {3}, {4}, {5}, {6} };
	// An element not in PQueue
	private static final Integer OBJECT = TestCase.K; 
	// Priority of element not in PQueue
	private static final Integer PRIORITY = TestCase.KEY_K; 
	// Element inserted into PQueue
	private static final Integer NEW_OBJECT = TestCase.L; 
	// Priority of element inserted into PQueue
	private static final Integer NEW_PRIORITY = TestCase.KEY_L; 
	// Max element in PQueue
	private static final Integer MAX = NEW_OBJECT; 
	// Priority greater than max in PQueue
	private static final int BIG_PRIORITY = TestCase.KEY_M; 
	// Number of objects in PQueue
	private static final int SIZE = 6;

	//********************Before Each Test Method********************
	/**
	 * Sets up PQueue for testing.
	 */
	@BeforeMethod
	public void initialize()
	{
		// create an empty PQueue
		PQueue = TestCase.newPQueue(OBJECTS, PRIORITIES);
		PQueue.insert(NEW_OBJECT, NEW_PRIORITY); 
	}

	//******************* Tests ***************************
	/**
	 * Test: maximum() - returns reference to max element in PQueue
	 * but doesn't remove it. 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testMaximum()
	{
		TestCase.maximum(PQueue, MAX);
	}	
	
	/**
	 * Test: extractMax() - removes / returns reference to max element in PQueue 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testExtractMax()
	{
		TestCase.extractMax(PQueue, MAX);
	}	

	/**
	 * Test: increaseKey() - increase priority of element at given index 
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "validIndexes")
	public void testIncreaseKey(int validIndex)
	{
		TestCase.increaseKey(PQueue, validIndex, BIG_PRIORITY);
	}	
	
	/**
	 * Test: insert(object) - inserts given object into PQueue
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testInsert()
	{
		TestCase.insert(PQueue, OBJECT, PRIORITY);
	}

	/**
	 * Test: size() - returns number of objects in PQueue
	 * Expected Result: SIZE
	 */
	@Test()
	public void testSize()
	{
		TestCase.size(PQueue, SIZE);
	}

	/**
	 * Test: isEmpty() - whether PQueue is empty
	 * Expected Result: false
	 */
	@Test()
	public void testIsEmpty()
	{
		TestCase.isEmpty(PQueue, false);
	}

	//********************* Data Providers ****************
	
	/**
    * Data: Indexes of elements in PQueue.
    * 
    * @return 2D array (second dimension empty) indexes of PQueue
    */
   @DataProvider
   public static Object[][] validIndexes() 
   {
      return VALID_INDEXES; 
   }

}
