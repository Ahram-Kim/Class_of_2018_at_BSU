import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing for implementation of MaxHeap objects: 
 * Tests for Change Scenario: empty heap -> insert -> 1 element heap
 * 
 * @author CS 321
 */

public class MaxHeapTest_4_empty_insert
{
	// MaxHeap running tests on
	MaxHeap<Integer> heap;
	// Index of element in heap
	private static final int VALID_INDEX = 1; 
	// An element not in heap
	private static final Integer OBJECT = TestCase.K; 
	// Key of element not in heap
	private static final Integer KEY = TestCase.KEY_K;
	// Element inserted into heap
	private static final Integer NEW_OBJECT = TestCase.L; 
	// Key of element inserted into heap
	private static final Integer NEW_KEY = TestCase.KEY_L; 
	// Max element in heap
	private static final Integer MAX = TestCase.L; 
	// Key greater than max in heap
	private static final int BIG_KEY = TestCase.KEY_M; 
	// Number of objects in heap
	private static final int SIZE = 1;

	//********************Before Each Test Method********************
	/**
	* Sets up heap for testing.
	*/
	@BeforeMethod
	public void initialize()
	{
		// create an empty heap
		heap = TestCase.newMaxHeap();
		heap.maxHeapInsert(NEW_OBJECT, NEW_KEY); 
	}
	
	//******************* Tests ***************************
	/**
	 * Test: heapMax() - returns reference to max element in heap
	 * but doesn't remove it. 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testHeapMax()
	{
		TestCase.heapMax(heap, MAX);
	}	
	
	/**
	 * Test: extractHeapMax() - removes / returns reference to max element in heap 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testExtractHeapMax()
	{
		TestCase.heapMax(heap, MAX);
	}	

	/**
	 * Test: increaseHeapKey() - increase key of element at given index 
	 * Expected Result: No exceptions
	 */
	@Test()
	public void testIncreaseHeapKey()
	{
		TestCase.increaseHeapKey(heap, VALID_INDEX, BIG_KEY);
	}	

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
	* Expected Result: false
	*/
	@Test()
	public void testIsEmpty()
	{
		TestCase.isEmpty(heap, false);
	}

}
