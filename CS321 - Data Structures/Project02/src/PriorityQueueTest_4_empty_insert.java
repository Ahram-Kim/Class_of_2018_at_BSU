import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing for implementation of PQueue objects: 
 * Tests for Change Scenario: empty priority queue -> insert -> 1 element priority queue
 * 
 * @author CS 321
 */

public class PriorityQueueTest_4_empty_insert
{
	// PQueue running tests on
	PQueue<Integer> pQueue;
	// Index of element in priority queue
	private static final int VALID_INDEX = 1; 
	// An element not in priority queue
	private static final Integer OBJECT = TestCase.K; 
	// Priority of element not in pQueue
	private static final Integer KEY = TestCase.KEY_K;
	// Element inserted into pQueue
	private static final Integer NEW_OBJECT = TestCase.L; 
	// Priority of element inserted into pQueue
	private static final Integer NEW_PRIORITY = TestCase.KEY_L; 
	// Max element in priority queue
	private static final Integer MAX = TestCase.L; 
	// Priority greater than max in pQueue
	private static final int BIG_KEY = TestCase.KEY_M; 
	// Number of objects in priority queue
	private static final int SIZE = 1;

	//********************Before Each Test Method********************
	/**
	* Sets up pQueue for testing.
	*/
	@BeforeMethod
	public void initialize()
	{
		// create an empty pQueue
		pQueue = TestCase.newPQueue();
		pQueue.insert(NEW_OBJECT, NEW_PRIORITY); 
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
	@Test()
	public void testIncreaseKey()
	{
		TestCase.increaseKey(pQueue, VALID_INDEX, BIG_KEY);
	}	

	/**
	* Test: insert(object) - inserts given object into priority queue
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
	* Test: isEmpty() - whether priority queue is empty
	* Expected Result: false
	*/
	@Test()
	public void testIsEmpty()
	{
		TestCase.isEmpty(pQueue, false);
	}

}
