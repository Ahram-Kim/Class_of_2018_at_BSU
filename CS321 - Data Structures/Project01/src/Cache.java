import java.util.NoSuchElementException;

/*
 * CS321 - Lab01
 * Implement a memory cache ADT using a double-inked list
 * to read data from files.
 * Create a test program for verifying the operation of cache.
 * 
 * @author Ahram Kim
 */

public class Cache<T> implements ICache<T>
{

	private int size;
	private int capOfCache;
	private int numOfHit;
	private int numOfAccess;
	private DLLNode<T> head, tail;
	

	
	public Cache()
	{
		capOfCache = 100;
	}
	
	public Cache(int capOfCache)
	{
		size = 0;
		numOfHit = 0;
		numOfAccess = 0;
		head = null;
		tail = null;
		this.capOfCache = capOfCache;
		
	}
	
	@Override
	public T get(T target) 
	{
		DLLNode<T> current = head;
		boolean found = false;
		
		while(!found && current != null)
		{
			if(current.getElement().equals(target))
			{
				found = true;
			}
			current = current.getNext();
		}
		
		if(found == true)
		{
			add(target);
			numOfHit++;
			numOfAccess++;
			return target;
		}
		else
		{
			numOfAccess++;
			return null;
		}
	}

	@Override
	public void clear() 
	{
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public void add(T data) 
	{
		if(size == capOfCache)
		{
			removeLast();
		}
		
		DLLNode<T> newNode = new DLLNode<T>(data);
		
		if(isEmpty())
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}
		size++;
	}

	@Override
	public void removeLast() 
	{
		if(isEmpty())
		{
			throw new IllegalStateException("ICache - removeLast");
		}
		
		DLLNode<T> newNode = tail;
		
		if(newNode.getPrevious() != null)
		{
			tail = tail.getPrevious();
			tail.setNext(null);
		}
		else
		{
			tail = null;
			head = null;
		}
		size--;
	}

	@Override
	public void remove(T target) 
	{
		DLLNode<T> current = head;
		boolean found = false;
		
		while(!found && current != null)
		{
			if(current.getElement() == target)
			{
				found = true;
			}
			else
			{
				current = current.getNext();
			}
		}
		
		if(!found)
		{
			throw new NoSuchElementException("Cache - remove");
		}
		
		DLLNode<T> previous = current.getPrevious();
		DLLNode<T> next = current.getNext();
		
		if(current == head)
		{
			if(size == 1)
			{
				head = null;
				tail = null;
			}
			else
			{
				head = next;
				head.setPrevious(null);
			}
		}
		else if(current == tail)
		{
			tail = previous;
			previous.setNext(null);
		}
		else
		{
			previous.setNext(next);
			next.setPrevious(previous);
		}
		size--;
	}

	@Override
	public void write(T data) 
	{
		DLLNode<T> current = head;
		boolean found = false;
		
		while(!found && current != null)
		{
			if(current.getElement().equals(data))
			{
				found = true;
			}
			current = current.getNext();
		}
		
		if(found = true)
		{
			remove(data);
			add(data);
		}
		else
		{
			throw new NoSuchElementException("Cache - write");
		}
	}

	@Override
	public double getHitRate() 
	{
		return (double) hitsCache() / (double) accessCache();
	}

	@Override
	public double getMissRate() 
	{
		return 1-getHitRate();
	}
	
	public double hitsCache()
	{
		return numOfHit;
	}
	
	public double accessCache()
	{
		return numOfAccess;
	}

	@Override
	public boolean isEmpty() 
	{
		return (size == 0);
	}
	
}
