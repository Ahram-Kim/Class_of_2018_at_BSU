public class TreeObject {
	
	private long key; //8 bytes
	private int frequency; // 4 bytes
	
	/**
	 * Basic Constructor
	 * @param k
	 * @param f
	 */
	public TreeObject(long k, int f) {
		this.key = k;
		this.frequency = f;
	}

	/**
	 * Get a key
	 * @return key
	 */
	public long getKey() {
		return key;
	}

	/**
	 * Sets probability to given value.
	 * @param key
	 */
	public void setKey(long key) {
		this.key = key;
	}

	/**
	 * Get a frequency
	 * @return frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Set frequency to the given value
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Return a string to print out 
	 */
	@Override
	public String toString() {
		return "TreeObject [key=" + key + ", frequency=" + frequency + "]";
	}

}