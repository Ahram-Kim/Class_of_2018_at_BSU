import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing for implementation of PQueue objects: 
 * Tests for Change Scenario: 5 elements -> increase priority of last to max
 * 
 * @author CS 321
 */
public class PriorityQueueTest_7_fiveElements_increaseKey_max
{
	// PQueue running tests on
	PQueue<Integer> pQueue;
	
	//****** Constants used in tests *****************
	// Objects in priority queue
	private static final Integer[] OBJECTS = { TestCase.B, TestCase.C, TestCase.D, TestCase.E }; 
	// Priorities for objects in priority queue
	private static final int[] PRIORITIES = { TestCase.KEY_B, TestCase.KEY_C, TestCase.KEY_D, TestCase.KEY_E }; 
	// Valid indexes to elements in priority queue
	private static final Object[][] VALID_INDEXES = { {1}, {2}, {3}, {4}, {5} };
	// An element not in priority queue
	private static final Integer OBJECT = TestCase.K; 
	// Priority of element not in priority queue
	private static final Integer PRIORITY = TestCase.KEY_K; 
	// Increase priority value 
	private static final Integer NEW_PRIORITY = TestCase.KEY_L; 
	// Min element in priority queue, before increase key
	private static final Integer MIN = TestCase.A; 
	// Key of min element, before increase key
	private static final int MIN_KEY = TestCase.KEY_A;
	// Max element in priority queue, after increase key
	private static final Integer MAX = TestCase.A; 
	// Key greater than max in priority queue
	private static final int BIG_PRIORITY = TestCase.KEY_M; 
	// Number of objects in priority queue
	private static final int SIZE = 5;

	//********************Before Each Test Method********************
	/**
	 * Sets up priority queue for testing.
	 */
	@BeforeMethod
	public void initialize()
	{
		// create an empty pQueue
		pQueue = TestCase.newPQueue(OBJECTS, PRIORITIES);
		// to ensure this object is max, after increase key 
		pQueue.insert(MIN, MIN_KEY);
		pQueue.increaseKey(SIZE, NEW_PRIORITY); 
	}

	//******************* Tests ***************************
	/**
	 * Test: maximum() - returns reference to max element in priority queue
	 * but doesn't remove it. 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testMaximum()
	{
		TestCase.maximum(pQueue, MAX);
	}	
	
	/**
	 * Test: extractMax() - removes / returns reference to max element in priority queue 
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
		TestCase.increaseKey(pQueue, validIndex, BIG_PRIORITY);
	}	
	
	/**
	 * Test: insert(object) - inserts given object into priority queue
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testInsert()
	{
		TestCase.insert(pQueue, OBJECT, PRIORITY);
	}

	/**
	 * Test: size() - returns number of objects in priority queue
	 * Expected Result: SIZE
	 */
	@Test()
	public void testSize()
	{
		TestCase.size(pQueue, SIZE);
	}

	/**
	 * Test: isEmpty() - whether priority queue is empty
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
