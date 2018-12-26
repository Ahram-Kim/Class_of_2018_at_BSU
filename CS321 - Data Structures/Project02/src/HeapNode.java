/**
 * Class representing a node in a heap ADT. 
 * 
 * @author CS 321 
 *
 * @param <T> - generic object of type T 
 */
public class HeapNode<T>
{
	private T object;	// reference to object stored at node
	private int key;		// reference to key of object 
	
	/**
	 * Basic constructor
	 * 
	 * @param object
	 * @param key
	 */
	public HeapNode(T object, int key)
	{
		setObject(object); 
		setKey(key); 
	}
	
	/**
	 * Returns object stored in node. 
	 * @return - object of type T 
	 */
	public T getObject()
	{
		return object;
	}
	
	/**
	 * Sets object stored in node to given value. 
	 * @param object - object of type T 
	 */
	public void setObject(T object)
	{
		this.object = object;
	}
	
	/**
	 * Returns key value of the node
	 * @return - int value 
	 */
	public int getKey()
	{
		return key;
	}
	
	/**
	 * Sets key to given value 
	 * @param key - int value 
	 */
	public void setKey(int key)
	{
		this.key = key;
	} 
	
}
