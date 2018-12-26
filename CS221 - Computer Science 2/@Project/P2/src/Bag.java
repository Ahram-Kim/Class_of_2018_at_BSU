import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A simple data structure that holds items of type T 
 * using an array implementation. 
 * @author Matt T 
 * @version summer 16
 */
public class Bag<T>
{
	private final int INITIAL_CAPACITY = 50;   // default capacity of bag
	private int capacity;  // number of T objects the bag can hold 
	private T[] data;  // array to hold objects of type T in the Bag
	private int count; // number of integers stored in the array

	/**
	 * Default constructor creates an empty Bag
	 */
	@SuppressWarnings("unchecked")
	public Bag() 
	{
		// create array of T elements
		setCapacity(INITIAL_CAPACITY);
		data = (T[])new Object[capacity];
		count = 0;
	}

	/**
	 * Mutator for capacity of the bag. 
	 * @param (int) newCapacity - integer representing new size of bag
	 */
	public void setCapacity(int newCapacity)
	{
		capacity = newCapacity;
	}

	/**
	 * Accessor method for number of elements in the bag. 
	 * @return (int) current number of elements in the bag 
	 */
	public int getSize()
	{
		return count;
	}
	
	/**
	 * Adds a new element to the bag. 
	 *@param element (T) object to be added to the bag
	 */
	public void add(T element) 
	{
		// if current number of elements is at capacity
		if (count >= capacity) 
		{
			resize();		// expand size of bag 
		} 
		// add item at end of the array
		else 
		{
			data[count] = element;
			count++;
		}
	}

	/**
	 * Increases size of the bag by one half.  
	 */
	private void resize()
	{
		capacity *= 1.5; 
		data = Arrays.copyOf(data, capacity);
	}
	
	/**
	 * Print elements of the bag.
	 */
	public void print() 
	{
		// print out number of elements
		System.out.println("The bag has " + count + " elements:");
		// if not empty
		if (!isEmpty()) 
		{
			// uses toString method of objects to print them
			// separated by commas
			System.out.print(data[0].toString());
			for (int i = 1; i < count; i++) 
			{
				System.out.print(", " + data[i]);
			}
			System.out.println();
		}
	}

	/**
	 * Removes last element from the bag,
	 * throws exception if empty. 
	 * @return (T) reference to the element removed
	 * @throws IllegalStateException
	 */
	public T remove()
	{
		T target = null;
		// can't remove an element if empty
		if(isEmpty())
		{
			throw new IllegalStateException("Bag - remove()");
		}
		//otherwise, remove last element in the bag
		target = data[count - 1];
		data[count - 1] = null;
		count--;

		return target; 
	}
	
	/**
	 * Removes particular element from the bag,
	 * throws exception if not found. 
	 * @param element (T) item looking for 
	 * @return (T) reference to element if found 
	 * @throws NoSuchElementException
	 */
	public T remove(T element)
	{
		T target = null;
		Boolean done = false;  // still looking for  element 
		int i = 0; 	// loop variable
		
		// iterate through array looking for element
		while(i < count && !done)
		{
			if (data[i] == element) 
			{
				// get return value
				target = data[i];
				//move last element into space left by this element
				data[i] = data[count-1];
				// set old last position to null
				data[count - 1] = null;
				// update count
				count--;
				// done, so set to true 
				done = true;
			}
			i++;
		}
		// if not done, didn't find element 
		if(!done)
		{
			throw new NoSuchElementException("Bag - remove(element)");
		}
		return target;
	}

	/**
	 *  Whether bag contains a particular element or not
	 * @param target (T) element looking for
	 * @return (boolean) whether element is found 
	 */
	public boolean contains(T target)
	{
		Boolean found = false;
		int i = 0; 	// loop variable
		
		// iterate through array looking for element
		while(i < count && !found)
		{
			// if found, set found to true 
			if (data[i] == target) 
			{
				found = true;
			}
			i++;
		}
		return found;
	}

	/**
	 * Whether bag is empty or not. 
	 * @return (boolean) representing whether empty or not 
	 */
	public boolean isEmpty()
	{
		// empty if count equals 0, not otherwise 
		return (count == 0);
	}
	
	/**
	 * Returns reference to element at given index. 
	 * @param index (int) index in the array
	 * @return (T) reference to element at that index
	 * @throws IndexOutOfBoundsException 
	 */
	public T get(int index)
	{
		T target = null;
		// if try to access index that contains an element
		if(index < 0 || index >= count)
		{
			throw new IndexOutOfBoundsException("Bag - get(index)");
		}
		target = data[index];
		return target;
	}

}
