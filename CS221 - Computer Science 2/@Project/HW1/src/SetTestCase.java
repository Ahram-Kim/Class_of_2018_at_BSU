import org.testng.Assert;

/**
 * A test case class for implementations of the SimpleSet ADT. 
 * 
 * @author CS221
 */
public class SetTestCase 
{
	// named elements for use in tests
	public static final Integer A = new Integer(1);
	public static final Integer B = new Integer(2);
	public static final Integer C = new Integer(3);
	public static final Integer D = new Integer(4);
	public static final Integer E = new Integer(5);
	public static final Integer F = new Integer(6);
	
	/**
	 * Tests add() method on given set. 
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param element - Integer to add
	 */
	public static void add(SimpleSet<Integer> set, Integer element)
	{
		set.add(element);
	}

	/**
	 * Tests remove() method on given set.
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param element - Integer to remove
	 */
	public static void remove(SimpleSet<Integer> set, Integer element)
	{
		Integer retVal = set.remove(element);
		Assert.assertEquals(retVal, element);
	}

	/**
	 * Tests contains() method on a given set. 
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param element - Integer value
	 * @param expectedResult - boolean value
	 */
	public static void contains(SimpleSet<Integer> set, Integer element, boolean expectedResult)
	{
		boolean result = set.contains(element);
		Assert.assertEquals(result, expectedResult);
	}

	/**
	 * Tests isEmpty() method on a given set.
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param expectedResult - boolean value
	 */
	public static void isEmpty(SimpleSet<Integer> set, boolean expectedResult)
	{
		boolean result = set.isEmpty();
		Assert.assertEquals(result, expectedResult);
	}

	/**
	 * Tests size() method on a given set.
	 * 
	 * @param set - implementation of the SimpleSet interface
	 * @param expectedSize - int value
	 */
	public static void size(SimpleSet<Integer> set, int expectedSize)
	{
		int size = set.size(); 
		Assert.assertEquals(size, expectedSize);
	}

	/**
	 * Tests toString() method on given set, confirms that default address output has been overridden
	 * 
	 * @param set - implementation of the SimpleSet interface
	 */
	public static void toString(SimpleSet<Integer> set)
	{
		String str = set.toString();
		char lastChar = str.charAt(str.length() - 1);
		Assert.assertFalse(str.contains("@") && !str.contains(" ") && Character.isLetter(str.charAt(0)) && (Character.isDigit(lastChar) || (lastChar >= 'a' && lastChar <= 'f')));
	}

}// end class SetTestCase
