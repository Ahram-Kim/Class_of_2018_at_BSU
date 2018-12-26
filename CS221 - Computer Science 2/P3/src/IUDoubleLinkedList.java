import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> 
{

	private int size;
	private DLLNode<T> head, tail, end;
	private int modCount;
	
	public IUDoubleLinkedList() 
	{
		head = null;
		tail = null;
		modCount = 0;
		size = 0;
	}
	
	@Override
	public void addToFront(T element) 
	{
		DLLNode<T> newNode = new DLLNode<T>(element);
		
		if(isEmpty())
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			head.setPrevious(newNode);
			newNode.setNext(head);
			head = newNode;
		}
		
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) 
	{
		DLLNode<T> newNode = new DLLNode<T>(element);
		
		if(isEmpty())
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}
		size++;
		modCount++;	
	}

	@Override
	public void add(T element) 
	{
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) 
	{
		DLLNode<T> current = head;
		
		if(!contains(target))
		{
			throw new NoSuchElementException("IUDoubleLinkedList - addAfter(element");
		}
		
		while(current != null)
		{
			if(target.equals(current.getElement()))
			{
				break;
			}
			else
			{
			current = current.getNext();
			}
		}
		
		
		DLLNode<T> newNode = new DLLNode<T>(element);
		
		if(current == tail)
		{
			addToRear(element);
		}
		else if(current == head)
		{
			add(1, element);
		}
		else
		{
			newNode.setPrevious(current);
			newNode.setNext(current.getNext());
			current.getNext().setPrevious(newNode);
			current.setNext(newNode);
			
			size++;
			modCount++;
		}
	}

	@Override
	public void add(int index, T element) 
	{
		DLLNode<T> newNode = new DLLNode<T> (element);
		
		if(index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("IUDoubleLinkedList - add(index)");
		}
		
		if(index == 0)
		{
			addToFront(element);
		}
		else if (index == size)
		{
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
			
			size++;
			modCount++;
		}
		else
		{
			DLLNode<T> current = head;
			
			for(int i = 0; i < index; i++)
			{
				current = current.getNext();
			}
			
			DLLNode<T> previous = current.getPrevious();
			previous.setNext(newNode);
			current.setPrevious(newNode);
			newNode.setNext(current);
			newNode.setPrevious(previous);
			
			size++;
			modCount++;
		}
	}

	@Override
	public T removeFirst() 
	{
		if(isEmpty())
		{
			throw new IllegalStateException("IUDoubleLinkedList - removeFirst");
		}
		
		DLLNode<T> newNode = head;
		
		if(newNode.getNext() != null)
		{
			head = head.getNext();
			head.setPrevious(null);
		}
		else
		{
			tail = null;
			head = null;
		}
		
		size--;
		modCount++;
		return newNode.getElement();
	}

	@Override
	public T removeLast() 
	{
		if(isEmpty())
		{
			throw new IllegalStateException("IUDoubleLinkedList - removeLast");
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
		modCount++;
		
		return newNode.getElement();
	}

	@Override
	public T remove(T element) 
	{
		DLLNode<T> current = head;
		boolean found = false;
		
		while(!found && current!=null)
		{
			if(current.getElement() == element)
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
			throw new NoSuchElementException("IUDoubleLinkedList - remove");
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
			tail.setNext(null);
		}
		else
		{
			previous.setNext(next);
			next.setPrevious(previous);
		}
		size--;
		modCount++;
		
		return element;
	}

	@Override
	public T remove(int index) 
	{
		if(index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList - remove(index)");
		}
		
		DLLNode<T> current = head;
		
		
		for(int i=0; i < index; i++) 
		{
			current = current.getNext();
		}
		
		if(current == head)
		{
			head = current.getNext();
			
			if(head!=null)
			{
			head.setPrevious(null);
			}
		} 
		else if(current == tail)
		{
			tail = current.getPrevious();
			tail.setNext(null);
		}
		else 
		{
			current.getPrevious().setNext(current.getNext());
			current.getNext().setPrevious(current.getPrevious());
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
		
		DLLNode<T> current = head;
		
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
		
		DLLNode<T> current = head;
		
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
		DLLNode<T> current = head;
		
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
		if(isEmpty()) 
		{
			throw new IllegalStateException("IUDoubleLinkedList - first");
		}
		
		return head.getElement();
	}

	@Override
	public T last() 
	{
		if(isEmpty()) 
		{
			throw new IllegalStateException("IUDoubleLinkedList - last");
		}
		
		return tail.getElement();
	}

	@Override
	public boolean contains(T target)
	{
		boolean found = false;
		DLLNode<T> current = head;
		
		if(!isEmpty())
		{
			while(!found && current != null)
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
		return new IUDoubleLinkedListIterator(0);
	}

	@Override
	public ListIterator<T> listIterator() 
	{
		return new IUDoubleLinkedListIterator(0);
		
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) 
	{
		if(startingIndex < 0 || startingIndex > size) 
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList = get");
		}	
		return new IUDoubleLinkedListIterator(startingIndex);
	}
	
	private class IUDoubleLinkedListIterator implements ListIterator<T>
	{
		private int itrModCount;
		private DLLNode<T> next, previous;
		private boolean canModify, movingNext, movingPrevious;
		private int nextIndex, previousIndex;
		
		public IUDoubleLinkedListIterator(int index)
		{
			itrModCount = modCount;
			next = head;
			previous = null;
			canModify = false;
			movingNext=false;
			movingPrevious=false;
			
			nextIndex = 0;
			previousIndex = -1;
		}
		
		private void checkModCount()
		{
			if(itrModCount != modCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - checkModCount");
			}
		}
		
		@Override
		public boolean hasNext()
		{
			checkModCount();
			return (next != null);
		}	
		
		public boolean hasPrevious()
		{
			checkModCount();
			return (previous!= null);
		}
		
		@Override
		public T next() 
		{
			checkModCount();
			
			if(!hasNext())
			{
				throw new NoSuchElementException("IUDoubleLinkedListIterator - hasNext");
			}
			
			T element = next.getElement();
			
			previous = next;
			next = next.getNext();
			
			canModify = true;
			movingNext=true;
			movingPrevious=false;
			
			nextIndex++;
			previousIndex++;
			
			return element;
		}
		
		public T previous()
		{
			checkModCount();
			
			if(!hasPrevious())
			{
				throw new NoSuchElementException("IUDoubleLinkedListIterator - hasNext");
			}
			
			T element = previous.getElement();
			
			next = previous;
			previous = previous.getPrevious();
			canModify = true;
			movingNext = false;
			movingPrevious=true;
			
			nextIndex--;
			previousIndex--;
			
			return element;
		}
		
		@Override
		public void remove()
		{
			checkModCount();
			
			if(canModify)
			{
				if(previous == null)
				{	
					next = next.getNext();
					head = next;
					if(next!=null){
					head.setPrevious(null);
					}
				}
				else
				{
					if(movingNext){
						previous.getPrevious().setNext(next);
						if(next!=null){
						next.setPrevious(previous.getPrevious());
						}
						previous = previous.getPrevious();
					}else{
						previous.setNext(next.getNext());
						if(next!=tail){
						next.getNext().setPrevious(previous);
						}
						next = next.getNext();
					}	
				}
				
				if(next == null)
				{
					tail = previous;
					if(tail!=null){
					tail.setNext(null);
					}
				}
			}
			else
			{
				throw new IllegalStateException("IUDoubleLinkedListIterator - remove");
			}	
			canModify = false;
			
			if(movingNext){
				nextIndex--;
				previousIndex--;
			}
			size--;
			modCount++;
			itrModCount++;
		}
		
		public void add(T arg0)
		{
			DLLNode<T> newNode = new DLLNode<T>(arg0);
		
			if(previous == null)
			{
				head = newNode;
				
				if(size==0)
				{
					tail = newNode;
				}
				head.setNext(next);
				previous = head;
			}
			else if(previous==tail)
			{
				previous.setNext(newNode);
				newNode.setPrevious(previous);
				tail =previous = newNode;
			}else
			{
				newNode.setNext(previous.getNext());
				newNode.setPrevious(previous);
				previous.getNext().setPrevious(newNode);
				previous.setNext(newNode);
				previous=newNode;
			}

			canModify = false;
			
			nextIndex++;
			previousIndex++;
			size++;
			modCount++;
			itrModCount = modCount;
		}
		
		public int nextIndex()
		{
			return this.nextIndex;
		}


		public int previousIndex()
		{
			return this.previousIndex;
		}

		public void set(T arg0)
		{
			if(canModify == false)
			{
				throw new IllegalStateException("IUDoubleLinkedListIterator - set");
			}
			
			if(canModify == true)
			{
				next.setElement(arg0);
			}
			
			modCount++;
			itrModCount = modCount;
			
		}
		
	}

}