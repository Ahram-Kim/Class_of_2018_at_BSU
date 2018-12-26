import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** A container for storing elements of type T in one of several
 *  possible underlying data structures.
 *  Additional data structures (or variations on data structures)
 *  can be added by adding to the DataStructure enum values and
 *  adding corresponding cases to wrapper methods.
 *  
 *  @author CS221
 */
public class Storage<T> 
{
	/** supported underlying data structures for Storage to use */
	public static enum DataStructure {stack, queue}
	/** the data structure chosen for this Storage to use */
	private DataStructure dataStructure;
	/** the data structures - only one will be instantiated and used  */
	private Queue<T> queue;
	private Stack<T> stack;
	
	/** Constructor
	 * @param dataStructure choice of DataStructures 
	 */
	public Storage(DataStructure dataStructure) 
	{
		this.dataStructure = dataStructure;
		switch (this.dataStructure) 
		{
			case stack:
				stack = new Stack<T>();
				break;
			case queue:
				queue = new LinkedList<T>();
		}
	}

	/** Alternative to using the constructor returns
 	 * a Storage already configured to use a Stack
 	 * @return instance of Storage configured to use a Stack
 	 */
	public static <E> Storage<E> getStackInstance() 
	{
		return new Storage<E>(DataStructure.stack);
	}
	
	/** Alternative to using the constructor returns
 	 * a Storage already configured to use a Queue
 	 * @return instance of Storage configured to use a Queue
 	 */
	public static <E> Storage<E> getQueueInstance() 
	{
		return new Storage<E>(DataStructure.queue);
	}
	
	/** Add element to underlying data structure
	 * @param element T to store
	 */
	public void store(T element) 
	{
		switch (dataStructure) 
		{
			case stack:
				stack.push(element);
				break;
			case queue:
				queue.add(element);
		}
	}
	
	/** Remove and return the next T from storage
	 * @return next T from storage
	 */
	public T retreive() 
	{
		T next = null;
		switch (dataStructure) 
		{
			case stack:
				next = stack.pop();
				break;
			case queue:
				next = queue.remove();
		}
		
		return next;
	}
	
	/** @return true if store is empty, else false */
	public boolean isEmpty() 
	{
		boolean result = true;
		switch (dataStructure) 
		{
			case stack:
				result = stack.isEmpty();
				break;
			case queue:
				result = queue.isEmpty();
		}
		return result;
	}
	
	/** @return size of store */
	public int size() 
	{
		int size = 0;
		switch (dataStructure) 
		{
			case stack:
				size = stack.size();
				break;
			case queue:
				size = queue.size();
		} 
		return size;
	}
} // class Storage