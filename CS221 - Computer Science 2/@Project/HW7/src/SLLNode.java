/**
 * Simple node class for single linked list 
 * @author CS221 
 *
 * @param <T> generic type of elements stored in a node
 */
public class SLLNode<T>
{
	private SLLNode<T> next;		// reference to next node
	private T element;			// reference to object stored in node 
	
	/**
	 * Constructor - with given element 
	 * @param element - object of type T
	 */
	public SLLNode(T element)
	{
		setElement(element);
		setNext(null);
	}

	/**
	 * Returns reference to next node
	 * @return - ref to SLLNode<T> object 
	 */
	public SLLNode<T> getNext()
	{
		return next;
	}

	/**
	 * Assign reference to next node 
	 * @param next - ref to Node<T> object 
	 */
	public void setNext(SLLNode<T> next)
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
	
	
}
