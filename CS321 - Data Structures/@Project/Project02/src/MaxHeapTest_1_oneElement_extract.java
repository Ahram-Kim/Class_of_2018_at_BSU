import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing for implementation of MaxHeap objects: 
 * Tests for Change Scenario: 1 element -> extract -> empty heap
 * 
 * @author CS 321
 */

public class MaxHeapTest_1_oneElement_extract
{
	// MaxHeap running tests on
	MaxHeap<Integer> heap;
	
	//****** Constants used in tests *****************
	// Objects in heap
	private static final Integer[] OBJECTS = { TestCase.A }; 
	// Keys for objects in heap
	private static final int[] KEYS = { TestCase.KEY_A }; 
	
	// An element not in heap
	private static final Integer OBJECT = TestCase.K; 
	// Key of element not in heap
	private static final Integer KEY = TestCase.KEY_K; 
	// Number of objects in heap
	private static final int SIZE = 0;
	
	//********************Before Each Test Method********************
	/**
	 * Sets up heap for testing.
	 */
	@BeforeMethod
	public void initialize()
	{
		// create an empty heap
		heap = TestCase.newMaxHeap(OBJECTS, KEYS);
		heap.extractHeapMax(); 
	}
	
	//******************* Tests ***************************
	/**
	 * Test: maxHeapInsert(object) - inserts given object into heap
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testMaxHeapInsert()
	{
		TestCase.maxHeapInsert(heap, OBJECT, KEY);
	}

	/**
	 * Test: getHeapSize() - returns number of objects in heap
	 * Expected Result: SIZE
	 */
	@Test()
	public void testGetHeapSize()
	{
		TestCase.getHeapSize(heap, SIZE);
	}

	/**
	 * Test: isEmpty() - whether heap is empty
	 * Expected Result: true
	 */
	@Test()
	public void testIsEmpty()
	{
		TestCase.isEmpty(heap, true);
	}

}
