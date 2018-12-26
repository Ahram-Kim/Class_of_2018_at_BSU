
/**
 * Simple Node class for double linked list
 * @author ahramkim
 *
 * @param <T> generic type of elements stored in queue
 */
public class DLLNode<T> 
{
	private DLLNode<T> next;
	private DLLNode<T> previous;
	private T element;
	
	/**
	 * Constructor - with given element
	 * @param element;
	 */
	public DLLNode(T element)
	{
		setElement(element);
		setNext(null);
	}

	/**
	 * Returns reference to the next code
	 * @return reference to the next code
	 */
	public DLLNode<T> getNext()
	{
		return next;
	}
	
	/**
	 * Assign reference to the next node
	 * @param next - reference to the next node
	 */
	public void setNext(DLLNode<T> next) 
	{
		this.next = next;		
	}
	
	/**
	 * Returns reference to the previous code
	 * @return reference to the previous code
	 */
	public DLLNode<T> getPrevious()
	{
		return previous;
		
	}
	/**
	 * Assign reference to the previous code
	 * @param previous - reference to the previous node
	 */
	public void setPrevious(DLLNode<T> previous) {
		this.previous = previous;
	}

	/**
	 * Returns reference to node stored in node
	 * @return reference to element stored in code
	 */
	public T getElement()
	{
		return element;
	}
	
	/**
	 * Sets reference to element stored at node
	 * @param element - reference to node stored in node
	 */
	public void setElement(T element) 
	{
		this.element = element;
	}

}
