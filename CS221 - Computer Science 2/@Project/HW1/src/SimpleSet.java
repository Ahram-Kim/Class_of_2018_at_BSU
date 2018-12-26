
/**
 * Operations required of a simple Set, a collection containing no duplicate elements and order is not important.
 * 
 * @author mvail
 */
public interface SimpleSet<T> {
	/**
	 * Reports the number of elements in the set.
	 * @return the number of elements in the set
	 */
	public int size();
	
	/**
	 * Determines if the set is empty (i.e. contains no elements).
	 * @return true if the set is empty, else false
	 */
	public boolean isEmpty();
	
	/**
	 * Determines if the given element is present in the set.
	 * @param element the element to find
	 * @return true if the element is in the set, else false
	 */
	public boolean contains(T element);
	
	/**
	 * Adds the given element to the set, if it is not already present.
	 * @param element the element to add
	 */
	public void add(T element);
	
	/**
	 * Removes the given element from the set, if present. Throws an exception if the element is not in the set. 
	 * @param element element to remove
	 * @return the removed element
	 * @throws NoSuchElementException if element is not contained in the set
	 */
	public T remove(T element);
	
}
