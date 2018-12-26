
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * "Good" implementation of IndexedUnsortedList interface 
 * using Java API LinkedList class. 
 * Doesn't implement listIterator methods.  
 * 
 * @param <T>  - type of elements held in this collection
 * @author CS 221
 */
public class GoodList<T> implements IndexedUnsortedList<T> {
	// uses LinkedList class in Java API
	private LinkedList<T> list;
	
	/**
	 * Default constructor 
	 */
	public GoodList() {
		list = new LinkedList<T>();
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new IllegalStateException("GoodList - removeFirst");
		}
		return list.removeFirst();
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new IllegalStateException("GoodList - removeLast");
		}
		return list.removeLast();
	}

	@Override
	public T remove(T element) {
		int idx = list.indexOf(element);
		if (idx < 0) {
			throw new NoSuchElementException("GoodList - remove(element)");
		}
		T t = list.get(idx);
		list.remove(t);
		return t;
	}

	@Override
	public T first() {
		if (isEmpty()) {
			throw new IllegalStateException("GoodList - first");
		}
		return list.getFirst();
	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new IllegalStateException("GoodList - last");
		}
		return list.getLast();
	}

	@Override
	public boolean contains(T target) {
		return list.contains(target);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void addToFront(T element) {
		list.addFirst(element);
	}

	@Override
	public void addToRear(T element) {
		list.addLast(element);
	}

	@Override
	public void addAfter(T element, T target) {
		int targetIdx = list.indexOf(target);
		if (targetIdx < 0) {
			throw new NoSuchElementException("GoodList - addAfter");
		} else {
			list.add(targetIdx+1, element);
		}
	}
	
	@Override
	public String toString() {
		return list.toString();
	}

	@Override
	public void add(int index, T element) {
		list.add(index, element);
	}

	@Override
	public void set(int index, T element) {
		list.set(index, element);
	}

	@Override
	public void add(T element) {
		list.add(element);
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(T element) {
		return list.indexOf(element);
	}

	@Override
	public T remove(int index) {
		return list.remove(index);
	}

	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>)list.iterator();
	}

	@Override
	public ListIterator<T> listIterator()
	{
		throw new UnsupportedOperationException("Goodlist - listIterator");
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex)
	{
		list.listIterator(startingIndex);  // throws IndexOutOfBoundException if invalid index 
		
		throw new UnsupportedOperationException("Goodlist - listIterator(startingIndex)");
	}

}
