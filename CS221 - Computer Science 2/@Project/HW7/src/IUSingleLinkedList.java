import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T> 
{

	private int size;
	private int modCount;
	private SLLNode<T> head, tail;
	
	/**
	 * default constructor - empty list
	 */
	public IUSingleLinkedList() 
	{
		size = 0;
		modCount = 0;
		head = null;
		tail = null;
	}
	
	@Override
	public void addToFront(T element) 
	{
		
		SLLNode<T> newNode = new SLLNode<T>(element);
		
		if(isEmpty()) 
		{
			head = newNode;
			tail = newNode;
		} 
		else 
		{
			newNode.setNext(head);
			head = newNode;
		}
		
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) 
	{
		
		SLLNode<T> newNode = new SLLNode<T>(element);
		
		if(isEmpty()) 
		{
			head = newNode;
			tail = newNode;
		} 
		else 
		{
			tail.setNext(newNode);
			tail = newNode;
		}
		
		size++;
		modCount++;
		
	}

	@Override
	public void add(T element) 	
	{
		
		SLLNode<T> newNode = new SLLNode<T>(element);
		
		if(isEmpty()) 
		{
			head = newNode;
			tail = newNode;
		} 
		else 
		{
			tail.setNext(newNode);
			tail = newNode;
		}
		
		size++;
		modCount++;
	}

	@Override
	public void addAfter(T element, T target) 
	{
				
		boolean found = false;
		SLLNode<T> current = head;
		SLLNode<T> newNode = new SLLNode<T>(element);
		
		if (!contains(target)) 
		{
			throw new NoSuchElementException("LinkedUnorderedList");
		}
		
        while (current != null && !found) 
        {
            if (target.equals(current.getElement())) 
            {
                found = true;
            } 
            else 
            {
                current = current.getNext();
            }
        }
		
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		
		if(current == tail) 
		{
			tail = newNode;
		}
		
		size++;
		modCount++;
	}

	@Override
	public void add(int index, T element) 	
	{
		
		if(index < 0 || index > size) 
		{
			throw new IndexOutOfBoundsException("IUSingLinkedList - add(index)");
		}
		
		SLLNode<T> newNode = new SLLNode<T>(element);
		
		if(index == 0) 
		{
			newNode.setNext(head);
			head = newNode;
		} 
		else 
		{
			SLLNode<T> current = head;
			
			for(int i = 0; i < index - 1; i++) 
			{
				current = current.getNext();
			}
			
			SLLNode<T> next = current.getNext();
			newNode.setNext(next);
			current.setNext(newNode);
		}
		
		if ( index == size) 
		{
			tail = newNode;
		}
		
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() 
	{
		if(isEmpty()) 
		{
			throw new IllegalStateException("IUSingLinkedList - removeFirst");
		}
		
		SLLNode<T> result = head; 
	    
	    if (size == 1) 
	    {
	    	head = null;
	    } 
	    else 
	    {
	    	head = head.getNext();
	    }
	    
	    size--;
	    modCount++;

	    return result.getElement();
	}

	@Override
	public T removeLast() 
	{
		
		if(isEmpty()) 
		{
			throw new IllegalStateException("IUSingLinkedList - removeLast");
		}
		
		SLLNode<T> previous = tail;
		SLLNode<T> current = head;
		
		
		if(size == 1) 
		{
			head = null;
			tail = null;
		} 
		else 
		{
			while(!current.getNext().equals(tail)) 
			{
				current = current.getNext();
			}
			current.setNext(null);
			tail = current;
		}
		
		size--;
		modCount++;
		
		return previous.getElement();
		
	}

	@Override
	public T remove(T element) 
	{
		
		boolean found = false; 
		SLLNode<T> previous = null;
		SLLNode<T> current = head;
		
		while(!found && current != null) 
		{
			if(current.getElement() == element) 
			{
				found = true;
			} 
			else 
			{
				previous = current;
				current = current.getNext();
			}
		}
		
		if(size == 0 || !found) 
		{
			throw new NoSuchElementException("IUSingleLinkedList - remove");
		}
		
		SLLNode<T> nextValue = current.getNext();
		
		if(previous == null) 
		{
			head = nextValue;
		}
		else
		{
			previous.setNext(nextValue);
		}
		current.setNext(null);
		
		if(current == tail)
		{
			tail = previous;
		}
				
		size--;
		modCount++;
		
		return current.getElement();
	}

	@Override
	public T remove(int index) 
	{
		if(index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList - remove(index)");
		}
		
		SLLNode<T> previous = null;
		SLLNode<T> current = head;
		
		for(int i=0; i < index; i++) 
		{
			previous = current;
			current = current.getNext();
		}
		
		if(current == head)
		{
			head = current.getNext();
		} 
		else if(current == tail)
		{
			previous.setNext(null);
			tail = previous;
		}
		else {
			previous.setNext(current.getNext());
		}
		
		size--;
		modCount++;
		
		return current.getElement();
	}

	@Override
	public void set(int index, T element) 
	{
		if(index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList - set");
		}
		
		SLLNode<T> current = head;
		
		for(int i = 0; i < index; i++) 
		{
			current = current.getNext();
		}
		
		current.setElement(element);
	}

	@Override
	public T get(int index) 
	{
		if(index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList = get");
		}
		
		SLLNode<T> current = head;
		
		for(int i = 0; i < index; i++) 
		{
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public int indexOf(T element) 
	{
		int index = 0;
		boolean found = false;
		SLLNode<T> current = head;
		
		while(!found && index < size) 
		{
			if(current.getElement() == element) 
			{
				found = true;
			} 
			else 
			{
				current = current.getNext();
				index++;
			}
		}
		
		if (!found) 
		{
			return -1;
		}
		
		return index;
	}

	@Override
	public T first() 
	{
		if(size == 0) 
		{
			throw new IllegalStateException("IUSingleLinkedList - first");
		}
		
		return head.getElement();
	}

	@Override
	public T last() 
	{
		if(size == 0) 
		{
			throw new IllegalStateException("IUSingleLinkedList - last");
		}
		
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) 
	{
		boolean found = false;
		
		SLLNode<T> current = head;
		
		while(current != null && !found) 
		{
			if(target.equals(current.getElement())) 
			{
				found = true;
			} 
			else 
			{
				current = current.getNext();
			}
		}
		return found;
	}

	@Override
	public boolean isEmpty() 
	{
		return (size == 0);
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new IUSingleLinkedListIterator();
	}

	@Override
	public ListIterator<T> listIterator() 
	{
		throw new UnsupportedOperationException("IUSingLinkedList - listIterator");
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) 
	{
		if(startingIndex < 0 || startingIndex > size) 
		{
			throw new IndexOutOfBoundsException("IUSingLinkedList - listIterator(startingIndex)");
		}
		throw new UnsupportedOperationException("IUSingLinkedList - listIterator(startingIndex)");
	}
	
	private class IUSingleLinkedListIterator implements Iterator<T> 
	{
		private int itrModCount;
		private SLLNode<T> next;
		private SLLNode<T> current;
		private SLLNode<T> previous;
		private boolean canRemove;
		
		public IUSingleLinkedListIterator() 
		{
			itrModCount = modCount;
			next = head;
			current = null;
			previous = null;
			canRemove = false;
		}

		private void checkModCount() 
		{
			if(itrModCount != modCount)
			{
				throw new ConcurrentModificationException("IUSingLinkedListIterator - checkModCount");
			}
		}
		
		@Override
		public boolean hasNext() 
		{
			return (next != null);
		}

		@Override
		public T next() 
		{
			checkModCount();
			
			if(!hasNext()) 
			{
				throw new NoSuchElementException("IUSingLinkedListIterator - next"); 
			}
			
			T element = next.getElement();
			
			previous = current; 
			current = next;
			next  = next.getNext();
	
			canRemove = true; 
			
			return element;
		}
		
		public void remove() 
		{
			checkModCount();
			
			if(canRemove)
			{
				if(previous == null)
				{
					head = next;
				}
				else
				{
					previous.setNext(next);
				}
				
				current.setNext(null);
				
				if(next == null)
				{
					tail = previous;
				}
				
				current = null;
				size--; 
				modCount++;
				itrModCount = modCount;
				canRemove = false;
			}
			else
			{
				throw new IllegalStateException("IUSingleLinkedListIterator - remove");
			}
		}
	}
}
