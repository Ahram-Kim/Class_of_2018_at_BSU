import java.util.*;

/**
 * Represents a Priority Queue ADT
 * 
 * @author Ahram Kim
 *
 * @param <T>
 */
public class PQueue<T>
{
	private MaxHeap<T> maxHeap;
	private int size; 
	
	/**
	 * Default constructor
	 * A list of generic objects that should be added to the priority queue
	 */
	public PQueue()
	{
		maxHeap = new MaxHeap<T>();
		size = 0;
	}
	
	/**
	 * Constructor supporting preloading of pqueue contents. 
	 * A list of keys values associated with each object.
	 * 
	 * @param objects
	 * @param keys
	 */
	public PQueue(T[] objects, int[] keys)
	{
		this.size = objects.length;
		this.maxHeap = new MaxHeap(objects, keys);
	}
	
	/**
	 * Return the heapMax of maxHeap class.
	 * 
	 * @return maxHeap.heapMax();
	 */
	public T maximum()
	{
		return maxHeap.heapMax();
	}
	
	/**
	 * Return the extractHeapMax of maxHeap class
	 * 
	 * @return maxHeap.extractHeapMax();
	 */
	public T extractMax()
	{
		return maxHeap.extractHeapMax();
	}
	
	/**
	 * Get the increaseHeapKey of MaxHeap
	 * 
	 * @param index
	 * @param keys
	 */
	public void increaseKey(int index, int keys)
	{
		maxHeap.increaseHeapKey(index, keys);
	}
	
	/**
	 * Get the maxHeapInsert of MaxHeap
	 * 
	 * @param object
	 * @param keys
	 */
	public void insert(T object, int keys)
	{
		maxHeap.maxHeapInsert(object, keys);
	}
	
	/**
	 * return when heap size is zero
	 * Get the isEmpty() of MaxHeap
	 * 
	 * @return maxHeap.isEmpty 
	 */
	public boolean isEmpty()
	{
		return maxHeap.isEmpty();
	}
	
	/**
	 * Get the getHeapSize of MaxHeap
	 * 
	 * @return maxHeap.getHeapSize
	 */
	public int size()
	{
		return maxHeap.getHeapSize();
	}
}
