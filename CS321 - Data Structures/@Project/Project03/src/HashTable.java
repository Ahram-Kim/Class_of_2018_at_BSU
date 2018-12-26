
/**
 * Hash Table Implementation
 * 
 * @class CS321-004
 * @author Ahram Kim
 * @date October 20, 2017
 *
 * @param <T>
 */
public class HashTable<T>
{
	public final int LINEAR_PROBING = 0;
	public final int DOUBLE_HASHING = 1;
	
	private HashObject<T>[] hashTable;
 
	private int probeCount;
	private int insertCount;
	private int type;
	private int capacity = tableSize();;
	
	/**
	 * Constructs a new, empty hashtable with type
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int type)
	{
		this.hashTable = new HashObject[capacity];
		this.type = type;
		this.insertCount = 0;
		this.probeCount = 0;
	}

	/**
	 * Check the value of the table size to be 
	 * a prime in the range [95500....96000]
	 * 
	 * @param n
	 * @return true
	 */
	public boolean isPrimes(int n)
	{
		int count = 0;
		
		for(int i = 1; i <= n; i++)
		{
			if(n % i == 0)
			{
				count++;
			}
		}
		if(count == 2)
		{
			return true;
		}
		else
		{
			return false;			
		}
	}
	
	/**
	 * get the value of table size
	 * 
	 * @return
	 */
	public int tableSize()
	{
		int maxSize = 96000;
		int minSize = 95500;
		int size =0; 
		for(int i = minSize; i <= maxSize; i++)
		{
			if(isPrimes(i) == true && isPrimes(i+2) == true)
			{
				size = i;
				break;
			}
		}
		return size + 2;	
	}
	
	/**
	 * Return the length of hashtable.
	 * 
	 * @return capacity
	 */
	public int hashTableLength()
	{
		return capacity;
	}
	
	/**
	 * Get the number of duplication.
	 * 
	 * @return duplicateCount
	 */
	public int duplicateCount()
	{
		int sum = 0;
		for(int i = 0; i < hashTable.length; i++)
		{
			if(hashTable[i] != null && hashTable[i].getDuplicate() > 0)
			{
				sum++;
			}
		}
		return sum;
	}
	
	
	/**
	 * Get the double value of the number of Count
	 * 
	 * @return ((double) sum) / insertCounts
	 */
	public double probeCount()
	{
		int sum = 0;
		int numElements = 0;
		
		for(int i = 0; i < hashTable.length; i++)
		{
			if(hashTable[i] != null)
			{	
				sum += hashTable[i].getProve();
				numElements++;
			}
		}
		return ((double)sum) / numElements;
	}
	
	/**
	 * Insert the value 
	 * 
	 * @param value
	 */
	public boolean insert(T value)
	{
		HashObject<T> temp = new HashObject<T>(value);
		int i = 0;
		int j = 0;
		
		do 
		{
			temp.increaseProve();
			probeCount++;
			j = Hashing(temp.getKey(), i);
			
			if(hashTable[j] == null)
			{
				hashTable[j] = temp;
				insertCount++;
				return false;
			}
			else if(hashTable[j] != null && hashTable[j].equals(temp))
			{
				hashTable[j].increaseDuplicate();
				return true;
				
			}
			
			i++;
			
		} while(i < capacity);
		return true;
	}

	/**
	 * Return the linear probing and double hashing 
	 * 
	 * @param key
	 * @param index
	 * @return index
	 */
	private int Hashing(int key, int index)
	{
		// linear probing
		if(type == 0)
		{
			return ((hash1(key) + index) % capacity);			
		}
		// double Hashing
		else if(type == 1)
		{
			return ((hash1(key) + (index * hash2(key))) % capacity);			
		}

		return index;
	}
	
	/**
	 * Return h1(k) = k mod m
	 * 
	 * @param key
	 * @return (key % capacity)
	 */
	public int hash1(int key)
	{
		return (key % capacity);
	}
	
	/**
	 * Return h2(k) = 1 + (k mod (m - 2))
	 * 
	 * @param key
	 * @return (1 + (key % (capacity - 2)))
	 */
	public int hash2(int key)
	{
		return (1 + (key % (capacity - 2)));
	}

	/**
	 * print hashTable
	 * @return output
	 */
	public String printHashTable()
	{
		String output = "";
		for(int i = 0; i < hashTable.length; i++)
		{ 
			if(hashTable[i] != null)
			{
				output += "Table[" + i + "]: " + hashTable[i].toString() + "\n";
			}
		}
		return output;
	}
}
