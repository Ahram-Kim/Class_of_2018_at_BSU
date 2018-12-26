import java.util.Iterator;
import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Testing for IndexedUnsortedList interface implementation: 
 * Iterator Tests for Change Scenario 3: [] -> addToRear(A) -> [A]
 * 
 * @author Matt T 
 */
public class ItrTest_3_emptylist_addToRearA_A
{
	// List running tests on
	private IndexedUnsortedList<Integer> list;

	// Iterator reference for tests 
	private Iterator<Integer> itr; 
	
	// First element in list
	private static final Integer FIRST = TestCase.A;
	// Element not in list - used for negative testing 
	private static final Integer INVALID_ELEMENT = TestCase.F;
		
	//********************Before Each Test Method********************
	/**
	 * Sets up list for testing: uses Parameter in XML file to select the 
	 * dynamic type of the list. 
	 * @param listType - String representing the dynamic type of the list. 
	 */
	@BeforeMethod
	@Parameters("listType")		
	public void initList(String listType)
	{
		// create empty list - state before change
		list = TestCase.newList(listType);
		// the change made to the list 
		list.addToRear(TestCase.A);
	}
	
	/****** Tests for a new Iterator*****************/ 
	/**
	 * Test: new Iterator, hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: true
	 */
	@Test
	public void testItr_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.init);
		TestCase.hasNext(itr, true); 
	}
	
	/**
	 * Test: new Iterator, next() - returns ref to next element in the Iterator list
	 * Expected Result: Reference to first element in list
	 */
	@Test 
	public void testItr_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.init);
		TestCase.next(itr, FIRST); 
	}

	/**
	 * Test: new Iterator, remove() - tries to remove last element returned by next in the Iterator list
	 * Expected Result: IllegalStateException
	 */
	@Test(expectedExceptions = IllegalStateException.class) 
	public void testItr_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.init);
		TestCase.remove(itr); 
	}

	/******Call next() once, then run tests******/
	/**
	 * Test: new Iterator, call next(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: false
	 */
	@Test
	public void testItrNext_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.next);
		TestCase.hasNext(itr, false); 
	}
	
	/**
	 * Test: new Iterator, call next(); test next() - tries to return ref to next element in the Iterator list
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class) 
	public void testItrNext_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.next);
		TestCase.next(itr, INVALID_ELEMENT); 
	}

	/**
	 * Test: new Iterator, call next(); test remove() - removes last element returned by next in the Iterator list
	 * Expected Result: No exception
	 */
	@Test
	public void testItrNext_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.next);
		TestCase.remove(itr); 
	}
	
	/********* Call next(), remove(), then run tests*********/
	/**
	 * Test: new Iterator, call next(), remove(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: false
	 */
	@Test
	public void testItrNextRemove_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemove);
		TestCase.hasNext(itr, false); 
	}
	
	/**
	 * Test: new Iterator, call next(), remove(); test next() - tries to return ref to next element in the Iterator list
	 * Expected Result: NoSuchElementException 
	 */
	@Test(expectedExceptions = NoSuchElementException.class) 
	public void testItrNextRemove_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemove);
		TestCase.next(itr, INVALID_ELEMENT); 
	}

	/**
	 * Test: new Iterator, call next(), remove(); test remove() - tries to remove last element returned by next in the Iterator list
	 * Expected Result: IllegalStateException
	 */
	@Test(expectedExceptions = IllegalStateException.class) 
	public void testItrNextRemove_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemove);
		TestCase.remove(itr); 
	}

}
