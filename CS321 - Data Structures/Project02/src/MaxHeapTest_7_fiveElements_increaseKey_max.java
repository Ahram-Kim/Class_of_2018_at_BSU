import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing for implementation of MaxHeap objects: 
 * Tests for Change Scenario: 5 elements -> increase key of last to max
 * 
 * @author CS 321
 */

public class MaxHeapTest_7_fiveElements_increaseKey_max
{
	// MaxHeap running tests on
	MaxHeap<Integer> heap;
	
	//****** Constants used in tests *****************
	// Objects in heap
	private static final Integer[] OBJECTS = { TestCase.B, TestCase.C, TestCase.D, TestCase.E }; 
	// Keys for objects in heap
	private static final int[] KEYS = { TestCase.KEY_B, TestCase.KEY_C, TestCase.KEY_D, TestCase.KEY_E }; 
	// Valid indexes to elements in heap 
	private static final Object[][] VALID_INDEXES = { {1}, {2}, {3}, {4}, {5} };
	// An element not in heap
	private static final Integer OBJECT = TestCase.K; 
	// Key of element not in heap
	private static final Integer KEY = TestCase.KEY_K; 
	// Increase key value 
	private static final Integer NEW_KEY = TestCase.KEY_L; 
	// Min element in heap, before increase key
	private static final Integer MIN = TestCase.A; 
	// Key of min element, before increase key
	private static final int MIN_KEY = TestCase.KEY_A;
	// Max element in heap, after increase key
	private static final Integer MAX = TestCase.A; 
	// Key greater than max in heap
	private static final int BIG_KEY = TestCase.KEY_M; 
	// Number of objects in heap
	private static final int SIZE = 5;

	//********************Before Each Test Method********************
	/**
	 * Sets up heap for testing.
	 */
	@BeforeMethod
	public void initialize()
	{
		// create an empty heap
		heap = TestCase.newMaxHeap(OBJECTS, KEYS);
		// to ensure this object is max, after increase key 
		heap.maxHeapInsert(MIN, MIN_KEY);
		heap.increaseHeapKey(SIZE, NEW_KEY); 
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
	@Test(dataProvider = "validIndexes")
	public void testIncreaseHeapKey(int validIndex)
	{
		TestCase.increaseHeapKey(heap, validIndex, BIG_KEY);
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

	//********************* Data Providers ****************
	
	/**
    * Data: Indexes of elements in heap.
    * 
    * @return 2D array (second dimension empty) indexes of heap
    */
   @DataProvider
   public static Object[][] validIndexes() 
   {
      return VALID_INDEXES; 
   }

}
