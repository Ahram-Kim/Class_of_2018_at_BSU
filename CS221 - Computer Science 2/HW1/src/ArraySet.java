import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * An array-based implementation of the SimpleSet interface.
 * 
 * @param <T> type to store in the set
 * 
 * @author mvail
 */
public class ArraySet<T> implements SimpleSet<T> {
	private static final int DEFAULT_CAPACITY = 3;
	private T[] setArray; //holds current elements of the set in consecutive indexes beginning with 0
	private int rear; //the next open index where a new element could be added, also the size of the set

	/**
	 * Constructor - initializes new set for use
	 */
	public ArraySet() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructor - initializes new set for use
	 * @param initialCapacity specifies expected maximum capacity needed by the set
	 */
	@SuppressWarnings("unchecked")
	public ArraySet(int initialCapacity) {
		this.setArray = (T[]) new Object[initialCapacity];
		this.rear = 0;
	}

	@Override
	public int size() {
		return rear;
	}

	@Override
	public boolean isEmpty() {
		return (rear == 0);
	}

	@Override
	public boolean contains(T element) {
		for (int i = 0; i < rear; i++) {
			if (setArray[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void add(T element) {
		if (!contains(element)) {
			if (rear >= setArray.length) {
				expandCapacity();
			}
			setArray[rear] = element;
			rear++;
		}
	}

	@Override
	public T remove(T element) {
		T retVal = null;
		int i = 0;
		//locate the element to be removed
		while (retVal == null && i < rear) {
			if (setArray[i].equals(element)) {
				retVal = setArray[i];
				//shift all following elements one space forward
				//so there are no gaps in the array
				//3
				while (i < rear-1 ) {
					setArray[i] = setArray[i+1];
					i++;
				}
			}
			i++;
		}
		if (retVal == null) {
			throw new NoSuchElementException("ArraySet");
		}
		rear--;
		return retVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < rear; i++) {
			str.append(setArray[i]);
			str.append(", ");
		}
		if (rear > 0) {
			str.delete(str.length()-2, str.length()); //drop trailing ", "
		}
		str.append("]");
		return str.toString();
	}
	
	/**
	 * Doubles the capacity of setArray
	 */
	private void expandCapacity() {
		setArray = Arrays.copyOf(setArray, setArray.length * 2);
	}
}

