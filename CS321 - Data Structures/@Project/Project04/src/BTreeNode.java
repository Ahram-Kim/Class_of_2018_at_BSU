import java.util.Arrays;

public class BTreeNode {
	
	public TreeObject objects[]; //12
	public int pointers[]; //4
	public boolean isLeaf; // 1 byte
	private int numOfObjects; // 4 bytes
	private int position; // 4 bytes
	
	/**
	 * Basic a constructor
	 * @param degree
	 */
	public BTreeNode(int degree) {
		this.objects = new TreeObject[degree*2-1];
		for(int i=0; i<(degree*2-1); i++) {
			this.objects[i]= new TreeObject(0,0);
			
		}
		this.pointers = new int[degree*2];
		this.isLeaf = true;
		this.numOfObjects = 0;
		this.position = 0;
	}

	/**
	 * Get an object from TreeObject class
	 * @return objects
	 */
	public TreeObject[] getObjects() {
		return objects;
	}

	/**
	 * Set object to the given value
	 * @param objects
	 */
	public void setObjects(TreeObject[] objects) {
		this.objects = objects;
	}

	/**
	 * Get a pointer
	 * @return pointer
	 */
	public int[] getPointers() {
		return pointers;
	}

	/**
	 * Set pointers to the given value
	 * @param pointers
	 */
	public void setPointers(int[] pointers) {
		this.pointers = pointers;
	}

	/**
	 * Get a isLeaf
	 * @return isLeaf 
	 */
	public boolean isLeaf()
	{
		return isLeaf;
	}
	
	/**
	 * Set isLeaf to the given value
	 * @param isLeaf
	 */
	public void setIsLeaf(boolean isLeaf)
	{
		this.isLeaf = isLeaf;
	}
	
	/**
	 * Get the number of objects
	 * @return numobject
	 */
	public int getNumOfObjects() 
	{
		return numOfObjects;
	}

	/**
	 * Set the number of object to the given value
	 * @param numOfObjects
	 */
	public void setNumOfObjects(int numOfObjects) {
		this.numOfObjects = numOfObjects;
	}

	/**
	 * Get a position
	 * @return position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Set the position to the given value
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Return the string to print out
	 */
	@Override
	public String toString() {
		return "BTreeNode [objects=" + Arrays.toString(objects) + ", pointers=" + Arrays.toString(pointers)
				+ ", isLeaf=" + isLeaf + ", numOfObjects=" + numOfObjects + ", position=" + position + "]";
	}
}