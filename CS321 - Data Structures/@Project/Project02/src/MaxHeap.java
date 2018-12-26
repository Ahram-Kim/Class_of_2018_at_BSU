import java.util.*;

/**
 * Represents a Max-Heap ADT
 * 
 * @author Ahram Kim
 *
 * @param <T> - generic object of type T
 */
public class MaxHeap<T> 
{
	private final int DEFAULT_CAPACITY = 50;
	private int heapSize;
	private int capacity;

	private HeapNode[] heap;

	/**
	 * Basic constructor
	 * A list of generic objects that should be added to the heap
	 */
	public MaxHeap() 
	{
		heap = new HeapNode[DEFAULT_CAPACITY+1];
		capacity = DEFAULT_CAPACITY;
		heapSize = 0;
	}
	
	/**
	 * Constructor supporting preloading of heap contents.
	 * A list of key values assiciated with each object.
	 * 
	 * @param objects
	 * @param keys
	 */
	public MaxHeap(T[] objects, int[] keys)
	{
		this.heap = new HeapNode[DEFAULT_CAPACITY];
		this.heapSize = 0;
		capacity = DEFAULT_CAPACITY;
		
		for(int i = 0; i < objects.length; i++)
		{
			maxHeapInsert(objects[i], keys[i]);
		}
	}
	
	/**
	 * Get the value of the maximum number
	 * 
	 * @return heap[1].getObject()
	 */
	public T heapMax()
	{
		return (T) heap[1].getObject();
	}
	
	/**
	 * The maximum element of heap and as a heap with this element removed
	 * 
	 * @return largestNum 
	 */
	public T extractHeapMax()
	{
		T largestNum = (T) heap[1].getObject();
		
		heap[1] = heap[heapSize];
		heapSize = heapSize - 1;
		maxHeapify(1);
		
		return largestNum;
	}
	
	/**
	 * Represent a heap where the key of index was increased to keys
	 * 
	 * @param index
	 * @param keys
	 */
	public void increaseHeapKey(int index, int keys)
	{
		if(keys < index)
		{
			return;
		}
		
		heap[index].setKey(keys);
		
		while((index > 1) && (heap[parent(index)].getKey() < heap[index].getKey()))
		{
			exchange(index, parent(index));
			index = parent(index);
		}
	}
	
	/**
	 * Insert value into heap
	 * 
	 * @param index
	 * @param keys
	 */
	public void maxHeapInsert(T index, int keys)
	{
		if(heapSize == capacity)
		{
			expandCapacity();
		}
		
		heapSize = heapSize + 1;
		heap[heapSize] = new HeapNode<T>(index, Integer.MIN_VALUE);
		
		increaseHeapKey(heapSize, keys);
	}
	
	/**
	 * Represent the expand capacity
	 */
	private void expandCapacity()
	{
		capacity = capacity * 2;
		HeapNode newHeap[] = new HeapNode[capacity + 1];
		
		for(int i = 0; i < heap.length; i++)
		{
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}
	
	/**
	 * Heapify the contents of Heap
	 * 
	 * @param index
	 */
	public void maxHeapify(int index)
	{
		int largeNum;
		int left = left(index);
		int right = right(index);
		 
		if(left <= heapSize && heap[index].getKey() < heap[left].getKey())
		{
			largeNum = left;
		}
		else
		{
			largeNum = index;
		}
		
		if(right <= heapSize && heap[right].getKey() > heap[largeNum].getKey())
		{
			largeNum = right;
		}
		
		if(largeNum != index)
		{
			exchange(index, largeNum);
			maxHeapify(largeNum);
		}
	}
	
	/**
	 * Represent to move up the maximum number.
	 * 
	 * @param maxNum
	 */
	private void moveUp(int maxNum) 
	{
		maxNum = this.heapSize;
		
		while(maxNum > 0)
		{
			exchange(maxNum, parent(maxNum));
			maxNum = parent(maxNum);
		}	
	}
	
	/**
	 * Get the value of heap size.
	 * 
	 * @return heapSize
	 */
	public int getHeapSize()
	{
		return heapSize;
	}
	
	/** 
	 * return true when nothing in heap
	 * 
	 * @return (heapSize == 0) 
	 */
	public boolean isEmpty() 
	{
		return (heapSize == 0);
	}
	
	/**
	 * Represent to swap index1 and index2
	 * 
	 * @param index1
	 * @param index2
	 */
	private void exchange(int index1, int index2) 
	{
		HeapNode temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}
	
	/**
	 * return the position for left child of parent
	 * 
	 * @param parent
	 * @return parent*2 - return left child value
	 */
	private int left(int parent)
	{
		return parent * 2;
	}
	
	/**
	 * return the position for right child of parent
	 * 
	 * @param parent
	 * @return parent * 2 +1 - return right child value
	 */
	private int right(int parent)
	{
		return parent * 2 + 1;
	}
	
	/**
	 * Return the parent value of children.
	 * 
	 * @param child
	 * @return child / 2 - return the parent value
	 */
	private int parent(int child)
	{
		return child / 2;
	}
	
	/**
	 * Sets heapSize to given value. 
	 * 
	 * @param heapSize - heapSize of type int
	 */
	private void setHeapSize(int heapSize)
	{
		this.heapSize = heapSize;
	}
	
	/**
	 * Sets capacity to given value
	 * 
	 * @param capacity - capacity of type int
	 */
	private void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
}
