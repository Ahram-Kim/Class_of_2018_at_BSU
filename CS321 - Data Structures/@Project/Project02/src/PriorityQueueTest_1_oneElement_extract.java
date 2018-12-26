import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing for implementation of PQueue objects: 
 * Tests for Change Scenario: 1 element -> extract -> empty priority queue
 * 
 * @author CS 321
 */

public class PriorityQueueTest_1_oneElement_extract
{
	// PQueue running tests on
	PQueue<Integer> pQueue;
	
	//****** Constants used in tests *****************
	// Objects in pQueue
	private static final Integer[] OBJECTS = { TestCase.A }; 
	// Priorities for objects in pQueue
	private static final int[] PRIORITIES = { TestCase.KEY_A }; 
	
	// An element not in pQueue
	private static final Integer OBJECT = TestCase.K; 
	// Priority of element not in pQueue
	private static final Integer PRIORITY = TestCase.KEY_K; 
	// Number of objects in pQueue
	private static final int SIZE = 0;
	
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
	 * Test: insert(object) - inserts given object into pQueue
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testPQueueInsert()
	{
		TestCase.insert(pQueue, OBJECT, PRIORITY);
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
	 * Test: getIsEmpty() - whether pQueue is empty
	 * Expected Result: true
	 */
	@Test()
	public void testIsEmpty()
	{
		TestCase.isEmpty(pQueue, true);
	}

}
