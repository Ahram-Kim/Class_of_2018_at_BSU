
/**
 * HashTable Object Implementation
 * 
 * @class CS321-004
 * @author Ahram Kim
 * @date October 20, 2017
 *
 * @param <T>
 */
public class HashObject<T>
{
	private T value;
	private int duplicateCount;		
	private int probeCount;
	private int insertCount;
	
	/**
	 * A basic constructor
	 * 
	 * @param value2
	 */
	public HashObject(T value)
	{
		this.value = value;
		this.duplicateCount = 0;
		this.probeCount = 0;
	}

	/**
	 * Get the value of T object.
	 * 
	 * @return value
	 */
	public T getValue()
	{
		return value;
	}

	/**
	 * Check if the value's key and the obj's key are same.
	 * 
	 * @param obj
	 * @return (value.getKey() == obj.getKey())
	 */
	public boolean equals(HashObject<T> obj)
	{
		if(obj == this)
		{
			return true;
		}
		return (value.equals(obj.getValue()));
	}
	
	/**
	 * Get the value of the key
	 * 
	 * @return Math.abs(value.hashCode())
	 */
	public int getKey()
	{
		return Math.abs(value.hashCode());
	}

	
	/**
	 * Get the string
	 */
	public String toString()
	{
		String str = "";
		str = value + " " + duplicateCount + " " + probeCount;
		return str;
	}
	
	/**
	 * Get the value of the number of probe
	 * 
	 * @return probeCount
	 */
	public int getProve()
	{
		return probeCount;
	}
	
	/**
	 * Increase the number of probe count
	 * 
	 * @return probeCount
	 */
	public int increaseProve()
	{
		return probeCount++;
	}
	
	/**
	 * Get the value of the number of duplicate
	 * 
	 * @return duplicateCount
	 */
	public int getDuplicate()
	{
		return duplicateCount;
	}
	
	/**
	 * Increase the number of duplicate count.
	 * @return duplicateCount
	 */
	public int increaseDuplicate()
	{
		return duplicateCount++;
	}
	
	/**
	 * Get the value of the number of insert
	 * 
	 * @return insertCount
	 */
	public int getInsert()
	{
		return insertCount;
	}
	
	/**
	 * Increase the number of insert count
	 * 
	 * @return insert
	 */
	public int increaseInsert()
	{
		return insertCount++;
	}

}
