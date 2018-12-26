import java.util.NoSuchElementException;

/***
 * CS321
 * This class uses DLLNode implementation and implements ICache interface.
 * It contains all necessary methods for the operation of caches.
 * Cache stores data so future requests for that data can be served faster.
 * @author Ella Lee
 *
 * @param <T>
 */
public class Cache<T> implements ICache<T>{

	private int capacity, size;
	public int hit, miss, access;
	private DLLNode<T> head, tail;

	/*
	 * Default constructor - capacity is 100.
	 */
	public Cache(){
		capacity = 100;
	}

	/*
	 * Constructor 
	 */
	public Cache(int capacity){
		head = null;
		tail = null;
		size = 0;
		hit = 0;
		miss = 0;
		access = 0;
		this.capacity = capacity; 
	}

	@Override
	public T get(T target){

		if(contains(target)){
			write(target);
			hit++;
			access++;
			return target;
		} else{
			miss++;
			access++;
			return null;
		}		
	}

	@Override
	public void clear(){
		head = null;
		tail = null;
		size = 0;		
	}

	@Override
	public void add(T data){
		if(size == capacity){
			removeLast();
		}

		DLLNode<T> newNode = new DLLNode<T>(data);
		if(isEmpty()){	
			head = newNode;
			tail = newNode;
		}
		else{
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}

		size++;

	}

	@Override
	public void removeLast(){		
		if(isEmpty()){
			throw new IllegalStateException("removeLast - it's empty");
		}

		//special case
		if(size == 1){
			tail = null;
			head = null;
		}
		else{
			tail = tail.getPrevious();
			tail.setNext(null);
		}
		size--;
	}

	@Override
	public void remove(T target){
		DLLNode<T> current = head;
		boolean found = false;

		//find the location
		while(!found && current != null){
			if(current.getElement().equals(target)){
				found = true;
			}
			else{
				current = current.getNext();
			}
		}

		if(!found){
			throw new NoSuchElementException("remove - no target found");			
		}

		DLLNode<T> next = current.getNext();
		DLLNode<T> previous = current.getPrevious();

		if(current == head){
			if(size == 1) {
				head = null;
				tail = null;
			}
			else{
				head = next;
				head.setPrevious(null);
			}
		}else if(current == tail){
			tail = previous;
			previous.setNext(null);
		}else{
			previous.setNext(next);
			next.setPrevious(previous);
		}

		size--;
	}

	@Override
	public void write(T data){
		if(contains(data)){
			remove(data);
			add(data);		
		}
		else{
			throw new NoSuchElementException("no data");
		}

	}

	@Override
	public double getHitRate(){

		return (double)hit/(double)access;
	}

	@Override
	public double getMissRate(){

		return 1-getHitRate();
	}

	@Override
	public boolean isEmpty(){

		return size == 0 ;
	}

	/**
	 * Returns true if this cache contains the specified target element.
	 * @param target 
	 * @return true if it contains the target data
	 */
	public boolean contains(T target){
		boolean contain = false;

		DLLNode<T> current = head;

		while(!contain && current != null){

			if(current.getElement().equals(target)){
				contain = true;
			}
			else{
				current = current.getNext();
			}
		}
		return contain;
	}

}
