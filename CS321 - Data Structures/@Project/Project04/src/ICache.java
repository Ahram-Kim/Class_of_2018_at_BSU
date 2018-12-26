/***
 * Interface for a simple memory cache ADT.  
 * @author CS 321
 *
 * @param <T> - generic type of objects stored in cache
 */
public interface ICache<T>
{
	/**
	 * Gets the data from cache and moves it to the front, if it's there.
	 * If not, returns null reference. 
	 * @param target - object of type T
	 * @return object of type T, or null reference 
	 */
	public T get(T target); 
	
	/***
	 * Clears contents of the cache,
	 * but doesn't change its capacity. 
	 */
	public void clear(); 
	
	/***
	 * Adds given data to front of cache. 
	 * Removes data in last position, if full.
	 * @param data - object of type T
	 */
	public void add(T data); 
	
	/***
	 * Removes data in last position in cache.
	 * @throws IllegalStateException - if cache is empty. 
	 */
	public void removeLast(); 
	
	/**
	 * Removes the given target data from the cache.
	 * @throws NoSuchElementException - if target not found 
	 * @param target - object of type T 
	 */
	public void remove(T target); 
	
	/**
	 * Moves data already in cache to the front. 
	 * @throws NoSuchElementException - if data not in cache   
	 * @param data - object of type T
	 */
	public void write(T data); 
		
	/**
	 * Get hit rate of the cache.
	 * @return double value 
	 */
	public double getHitRate();
	
	/**
	 * Get miss rate of the cache.  
	 * @return double value 
	 */
	public double getMissRate(); 

	/**
	 * Whether there's any data in cache. 
	 * @return boolean value 
	 */
	public boolean isEmpty();
}
