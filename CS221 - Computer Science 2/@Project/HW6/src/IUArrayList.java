import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IUArrayList<T> implements IndexedUnsortedList<T>{

	private int size;
	private int DEFAULT_CAPACITY = 100;
	private int capacity;
	private T[] list;
	private int modCount;
	
	@SuppressWarnings("unchecked")
	public IUArrayList() {
		
		size = 0;
		capacity = DEFAULT_CAPACITY;
		list = (T[])(new Object[capacity]);
		modCount = 0;
		
	}
	
	/**
	 * doubles the capacity
	 */
	private void resize() {
		
		capacity *= 2;
		list = Arrays.copyOf(list, capacity);
		
	}
	
	
	
	@Override
	public void addToFront(T element) {
		
		if(size == list.length) {
			resize();
		}
		
		for(int i = size; i > 0; i--) {
			list[i] = list[i-1];
		}
		
		list[0] = element;
		size++;
		
	}

	@Override
	public void addToRear(T element) {
		
		if(size == capacity) {
			resize();
		}
		
		list[size] = element;
		size++;
		
	}

	@Override
	public void add(T element) {
		
		if(size == capacity) {
			resize();
		}
		
		list[size] = element;
		size++;
		modCount++;
		
	}

	@Override
	public void addAfter(T element, T target) {
		
		if(size == capacity) {
			resize();
		}
		
		int i = 0;
		while(i < size && !target.equals(list[i])) {
			i++;
		}
			
		if(i == size) {
			throw new NoSuchElementException("List - addAfter(element, target)");
		}
		
		i++;
		
		for(int j=size; j > i; j--) {
			list[j] = list[j-1];
		}
		list[i] = element;
		size++;
	}

	@Override
	public void add(int index, T element) {
		
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("List - add(index)");
		}
		
		if(size == capacity) {
			resize();
		}
		
		for(int i = size; i > index; i--) {
			list[i] = list[i-1];
		}
		
		list[index] = element;
		size++;
		modCount++;
		
	}

	@Override
	public T removeFirst() {
		
		if(size() == 0) {
			throw new IllegalStateException("List - removeFirst");
		}
		
		T result = list[0];
		
		
		for(int i = 0; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		
		list[size - 1] = null;
		size--;
		return result;
	}

	@Override
	public T removeLast() {
		
		if(size == 0) {
			throw new IllegalStateException("List - removeLast");
		}
		
		T result;
		
		size--;
		result = list[size];
		list[size] = null;
		return result;
		
	}

	@Override
	public T remove(T element) {
		int index = indexOf(element);
		
		if(size == 0 || index==-1) {
			throw new NoSuchElementException("List - remove(element");
		}
				
		for(int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		
		list[size - 1] = null;
		size--;
		modCount--;
		return element;
		
	}

	@Override
	public T remove(int index) {
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("List - remove(index)");
		}
		
		T result = list[index];
		
		for(int i = index; i <= size - 1; i++){
			list[i] = list[i + 1];
		}
		
		list[size - 1] = null;
		size--;
		modCount++;
		return result;
		
	}

	@Override
	public void set(int index, T element) {
		
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("List - set");
		}
		
		list[index] = element;
		modCount++;
		
	}

	@Override
	public T get(int index) {
		
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("List - get");
		}
		
		return list[index];
		
	}

	@Override
	public int indexOf(T element) {
		
		int index = 0;
		boolean found = false;
		
		while (!found && index < size) {
			
			if(element == list[index]) {
				found = true;
			} else {
				index++;
			}
		}
		
		if(!found) {
			
			return -1;
		}
		
		return index;
		
	}	

	@Override
	public T first() {
		
		if(size() == 0) {
			throw new IllegalStateException("List - first()");
		}
		
		return list[0];
		
	}

	@Override
	public T last() {
		
		if(size() == 0) {
			throw new IllegalStateException("List - last()");
		}
		
		return list[size - 1];
		
	}

	@Override
	public boolean contains(T target) {
		
		int i = 0;
		boolean found = false;
		
		if(!isEmpty()) {
			while(!found && i < size){
				if(target.equals(list[i])){
					found = true;
					break;
				} else {
					i++;
				}
			}
		}
		
		return found;
		
	}

	@Override
	public boolean isEmpty() {
		
		return (size == 0);
		
	}

	@Override
	public int size() {
		
		return size;
		
	}

	@Override
	public Iterator<T> iterator() {
		
		return new ListIterator();
	}

	@Override
	public java.util.ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("List - listIterator");
	}

	@Override
	public java.util.ListIterator<T> listIterator(int startingIndex) {
		if(startingIndex < 0 || startingIndex > size) {
			throw new IndexOutOfBoundsException("List - listIterator(startingIndex)");
		}
		throw new UnsupportedOperationException("List - listIterator(startingIndex)");	
	}
	
	/**
	 * ITerator class for my List class
	 * @author ahramkim
	 *
	 */
	private class ListIterator implements Iterator<T> {

		private int itrModCount;
		private int next;
		private boolean canRemove;
		
		public ListIterator() {
			
			itrModCount = modCount;
			next = 0;
			canRemove = false;
			
		}
		
		private void checkModCount() {
			
			if(modCount != itrModCount) {
				throw new ConcurrentModificationException("ListIterator - checkModCount");
			}
		}
		
		@Override
		public boolean hasNext() {
			
			checkModCount();
			return (next < size);
			
			
		}

		@Override
		public T next() {

			T temp;
			
			if(hasNext()) {
				
				temp = (T) list[next];
				next++;
				canRemove = true;
			
			} else {
				
				throw new NoSuchElementException("ListIterator - next");
			
			}
			
			return temp;
			
		}
		
		public void remove() {
			
			checkModCount();
						
			if(canRemove) {
				
				for(int i = next - 1; i < size - 1; i++) {
					list[i] = list[i + 1];
				}
				
				list[size - 1] = null;
				size--;
				next--;
				modCount++;
				itrModCount++;
				canRemove = false;
				
			} else {
				
				throw new IllegalStateException("ListIterator - remove");
			}
			
		}
		
	}

}
