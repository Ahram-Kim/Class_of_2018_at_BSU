import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing for SimpleSet interface implementation: 
 * Tests for Scenario: [A,B] -> add(C) -> [A,B,C]
 *
 * @author Matt T
 */
public class TestSimpleSet_8
{
	// SimpleSet running tests on 
	private SimpleSet<Integer> set; 
	// elements in set
	private Integer element_A = SetTestCase.A;
	private Integer element_B = SetTestCase.B; 
	private Integer element_C = SetTestCase.C; 
	// elements for testing 
	private Integer ELEMENT = SetTestCase.E; 
	private Integer INVALID_ELEMENT = SetTestCase.F; 
	// variables for tests 
	private final int SIZE = 3; 
	private static final Object[][] VALID_ELEMENTS = { {SetTestCase.A}, {SetTestCase.B}, {SetTestCase.C} };
	
	/**
	 * Reinitializes set before each test. 
	 */
	@BeforeMethod 
	public void initializeSet()
	{
		set = new ArraySet<Integer>();
		set.add(element_A);
		set.add(element_B);
		set.add(element_C);
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
	 * Expected Result: SIZE (3)
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
	 * Test: contains(X) - whether set contains X, an element in set
	 * Expected Result: True 
	 */
	@Test(dataProvider = "validElements")
	public void testContains_validElement(Integer element)
	{
		SetTestCase.contains(set, element, true);
	}
	
	/**
	 * Test: contains(INVALID_ELEMENT) 
	 * - whether set contains an element (F) not in set
	 * Expected Result: False
	 */
	@Test
	public void testContains_invalidElement()
	{
		SetTestCase.contains(set, INVALID_ELEMENT, false);
	}
	
	/**
	 * Test: add(X) - tries to add elements (X) already in set to set
	 * Expected Result: No exceptions
	 */
	@Test(dataProvider = "validElements")
	public void testAdd_validElement(Integer element)
	{
		SetTestCase.add(set, element);
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
	 * Test: remove(X) - removes elements (X) in set from set
	 * Expected Result: Reference to X
	 */
	@Test(dataProvider = "validElements")
	public void testRemove_validElements(Integer element)
	{
		SetTestCase.remove(set, element);
	}
	
	/**
	 * Test: remove(INVALID_ELEMENT) 
	 * - tries to remove element (E) not in set from set
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testRemove_invalidElement()
	{
		SetTestCase.remove(set, INVALID_ELEMENT);
	}
	
	//********** Data Provider ***************************
	/**
	 * Data: Integer elements.
	 * @return 2D array of Integer elements
	 */
	@DataProvider
	   public static Object[][] validElements() 
	   {
	      return VALID_ELEMENTS; 
	   }
} // end SimpleSetTest_8
