import org.testng.Assert;

/**
 * TestCase class for the PQueue and MaxHeap implementations. 
 * 
 * @author CS 321
 *
 */
public class TestCase
{
		// Objects that can be in the heap
		public static final Integer A = new Integer(1);
		public static final Integer B = new Integer(2);
		public static final Integer C = new Integer(3);
		public static final Integer D = new Integer(4);
		public static final Integer E = new Integer(5);
		public static final Integer F = new Integer(6);
		public static final Integer G = new Integer(7);
		public static final Integer H = new Integer(8);
		public static final Integer I = new Integer(9);
		public static final Integer J = new Integer(10);
		public static final Integer K = new Integer(11);
		public static final Integer L = new Integer(12);
		public static final Integer M = new Integer(13);
		
		// Keys for objects in the heap
		public static final int KEY_ZERO = 0; 
		public static final int KEY_A = 1;
		public static final int KEY_B = 2;
		public static final int KEY_C = 3;
		public static final int KEY_D = 4;
		public static final int KEY_E = 5;
		public static final int KEY_F = 6;
		public static final int KEY_G = 7;
		public static final int KEY_H = 8;
		public static final int KEY_I = 9;
		public static final int KEY_J = 10;
		public static final int KEY_K = 11;
		public static final int KEY_L = 12;
		public static final int KEY_M = 13;
		
/******** MaxHeap Tests ******************************/ 	
		
		/**
		 * Constructs empty max heap for testing. 
		 * 
		 * @return - new MaxHeap object
		 */
		public static MaxHeap<Integer> newMaxHeap()
		{
			MaxHeap<Integer> newHeap = new MaxHeap<Integer>();
		
			return newHeap; 
		}
		
		/**
		 * Constructs max heap with given Integers / keys for testing. 
		 * @param objects[] - array of Integer objects
		 * @param keys[] - array of int values 
		 * @return - new MaxHeap object with given Integer objects / keys
		 */
		public static MaxHeap<Integer> newMaxHeap(Integer[] objects, int[] keys)
		{
			MaxHeap<Integer> newHeap = new MaxHeap<Integer>(objects, keys);
		
			return newHeap; 
		}

		/**
		 * Tests heapMax method
		 * @param heap - MaxHeap implementation
		 * @param expectedResult - Integer object
		 */
		public static void heapMax(MaxHeap<Integer> heap, Integer expectedResult)
		{
			Integer result = heap.heapMax(); 
			Assert.assertEquals(result, expectedResult);
		}

		/**
		 * Tests extractHeapMax method
		 * @param heap - MaxHeap implementation
		 * @param expectedResult - Integer object
		 */
		public static void extractHeapMax(MaxHeap<Integer> heap, Integer expectedResult)
		{
			Integer result = heap.extractHeapMax(); 
			Assert.assertEquals(result, expectedResult);
		}

		/**
		 * Tests increaseHeapKey method
		 * @param heap - MaxHeap implementation
		 * @param position - int value
		 * @param key - int value
		 */
		public static void increaseHeapKey(MaxHeap<Integer> heap, int position, int key)
		{
			heap.increaseHeapKey(position, key); 
		}

		/**
		 * Tests maxHeapInsert method
		 * @param heap - MaxHeap implementation
		 * @param object - Integer object
		 * @param key - int value 
		 */
		public static void maxHeapInsert(MaxHeap<Integer> heap, Integer object, int key)
		{
			heap.maxHeapInsert(object, key); 
		}

		/**
		 * Tests maxHeapify method
		 * @param heap - MaxHeap implementation
		 * @param position - int value 
		 */
		public static void maxHeapify(MaxHeap<Integer> heap, int position)
		{
			heap.maxHeapify(position); 
		}
		
		/**
		 * Tests getHeapSize method
		 * @param heap - MaxHeap implementation
		 * @param expectedResult - int value 
		 */
		public static void getHeapSize(MaxHeap<Integer> heap, int expectedResult)
		{
			int result = heap.getHeapSize(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests isEmpty method
		 * @param heap - MaxHeap implementation
		 * @param expectedResult - boolean value 
		 */
		public static void isEmpty(MaxHeap<Integer> heap, boolean expectedResult)
		{
			boolean result = heap.isEmpty(); 
			Assert.assertEquals(result, expectedResult);
		}

/******** PQueue Tests ******************************/ 	

		/**
		 * Constructs empty priority queue for testing. 
		 * 
		 * @return - new PQueue object
		 */
		public static PQueue<Integer> newPQueue()
		{
			PQueue<Integer> newPQueue = new PQueue<Integer>();
		
			return newPQueue; 
		}
		
		/**
		 * Constructs priority queue with given Integers / priorities for testing. 
		 * @param objects[] - array of Integer objects
		 * @param priorities[] - array of int values 
		 * @return - new PQueue object with given Integer objects / priorities
		 */
		public static PQueue<Integer> newPQueue(Integer[] objects, int[] priorities)
		{
			PQueue<Integer> newPQueue = new PQueue<Integer>(objects, priorities);
		
			return newPQueue; 
		}

		/**
		 * Tests maximum method
		 * @param pQueue - PQueue implementation
		 * @param expectedResult - Integer object
		 */
		public static void maximum(PQueue<Integer> pQueue, Integer expectedResult)
		{
			Integer result = pQueue.maximum(); 
			Assert.assertEquals(result, expectedResult);
		}

		/**
		 * Tests extractMax method
		 * @param pQueue - PQueue implementation
		 * @param expectedResult - Integer object
		 */
		public static void extractMax(PQueue<Integer> pQueue, Integer expectedResult)
		{
			Integer result = pQueue.extractMax(); 
			Assert.assertEquals(result, expectedResult);
		}

		/**
		 * Tests increaseKey method
		 * @param pQueue - PQueue implementation
		 * @param position - int value
		 * @param priority - int value
		 */
		public static void increaseKey(PQueue<Integer> pQueue, int position, int priority)
		{
			pQueue.increaseKey(position, priority); 
		}

		/**
		 * Tests insert method
		 * @param pQueue - PQueue implementation
		 * @param object - Integer object
		 * @param priority - int value 
		 */
		public static void insert(PQueue<Integer> pQueue, Integer object, int priority)
		{
			pQueue.insert(object, priority); 
		}

		/**
		 * Tests size method
		 * @param pQueue - PQueue implementation
		 * @param expectedResult - int value 
		 */
		public static void size(PQueue<Integer> pQueue, int expectedResult)
		{
			int result = pQueue.size(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests isEmpty method
		 * @param pQueue - PQueue implementation
		 * @param expectedResult - boolean value 
		 */
		public static void isEmpty(PQueue<Integer> pQueue, boolean expectedResult)
		{
			boolean result = pQueue.isEmpty(); 
			Assert.assertEquals(result, expectedResult);
		}

}