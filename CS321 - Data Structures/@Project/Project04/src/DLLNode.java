/**
 * Simple node class for double linked list 
 * @author CS221 
 *
 * @param <T> generic type of elements stored in a node
 */

public class DLLNode<T>
{
	private DLLNode<T> next;				// reference to next node
	private DLLNode<T> previous;		// reference to previous node
	private T element;			// reference to object stored in node 
	
	/**
	 * Constructor - with given element 
	 * @param element - object of type T
	 */
	public DLLNode(T element)
	{
		setElement(element);
		setNext(null);
		setPrevious(null);
	}

	/**
	 * Returns reference to next node
	 * @return - ref to DLLNode<T> object 
	 */
	public DLLNode<T> getNext()
	{
		return next;
	}

	/**
	 * Assign reference to next node 
	 * @param next - ref to Node<T> object 
	 */
	public void setNext(DLLNode<T> next)
	{
		this.next = next;
	}

	/**
	 * Returns reference to node stored in node 
	 * @return - ref to object of type T 
	 */
	public T getElement()
	{
		return element;
	}

	/**
	 * Sets reference to element stored at node
	 * @param element - ref to object of type T
	 */
	public void setElement(T element)
	{
		this.element = element;
	}

	/**
	 * Returns reference to previous node
	 * @return - ref to DLLNode<T> object 
	 */
	public DLLNode<T> getPrevious()
	{
		return previous;
	}

	/**
	 * Assign reference to previous node 
	 * @param previous - ref to Node<T> object 
	 */	
	public void setPrevious(DLLNode<T> previous)
	{
		this.previous = previous;
	}

}
