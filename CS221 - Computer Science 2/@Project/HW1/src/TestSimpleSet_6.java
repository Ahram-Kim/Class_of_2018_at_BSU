import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Testing for SimpleSet interface implementation: 
 * Tests for Scenario: [A,B] -> remove(A) -> [B]
 *
 * @author CS221
 */
public class TestSimpleSet_6
{
	// SimpleSet running tests on 
	private SimpleSet<Integer> set; 
	// elements in set
	private Integer element_A = SetTestCase.A;
	private Integer element_B = SetTestCase.B;
	// elements for testing 
	private Integer ELEMENT = SetTestCase.E; 
	private Integer INVALID_ELEMENT = SetTestCase.F; 
	
	// variable for tests 
	private final int SIZE = 1; 
	
	/**
	 * Reinitializes set before each test. 
	 */
	@BeforeMethod 
	public void initializeSet()
	{
		set = new ArraySet<Integer >();
		set.add(element_A);
		set.add(element_B);
		set.remove(element_A);
	}

	/**
	 * Test: toString() - String representation of set 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testToString()
	{
		SetTestCase.toString(set);
	}

	/**
	 * Test: size() - number of elements in set 
	 * Expected Result: SIZE (1)
	 */
	@Test
	public void testSize()
	{
		SetTestCase.size(set, SIZE);
	}

	/**
	 * Test: isEmpty() - whether set is empty
	 * Expected Result: False
	 */
	@Test
	public void testIsEmpty()
	{
		SetTestCase.isEmpty(set, false);
	}

	/**
	 * Test: contains(B) - whether set contains B 
	 * Expected Result: True 
	 */
	@Test
	public void testContains_B()
	{
		SetTestCase.contains(set, element_B, true);
	}
	
	/**
	 * Test: contains(INVALID_ELEMENT) 
	 * - whether set contains element (F) not in set
	 * Expected Result: False
	 */
	@Test
	public void testContains_invalidElement()
	{
		SetTestCase.contains(set, INVALID_ELEMENT, false);
	}
	
	/**
	 * Test: add(B) - tries to add B to set, but already in it
	 * Expected Result: No exceptions
	 */
	@Test
	public void testAdd_B()
	{
		SetTestCase.add(set, element_B);
	}
	
	/**
	 * Test: add(ELEMENT) - adds element (E) to set
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testAdd_element()
	{
		SetTestCase.add(set, ELEMENT);
	}
	
	/**
	 * Test: remove(B) - removes B from set
	 * Expected Result: Reference to B
	 */
	@Test
	public void testRemove_B()
	{
		SetTestCase.remove(set, element_B);
	}
	
	/**
	 * Test: remove(INVALID_ELEMENT) 
	 * - tries to remove element (F) not in set from set
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testRemove_invalidElement()
	{
		SetTestCase.remove(set, INVALID_ELEMENT);
	}
} // end SimpleSetTest_6
