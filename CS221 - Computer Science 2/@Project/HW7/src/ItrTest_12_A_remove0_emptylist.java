import java.util.Iterator;
import java.util.NoSuchElementException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Testing for IndexedUnsortedList interface implementation: 
 * Iterator Tests for Change Scenario 12: [A] -> remove(0) -> []
 *
 * @author Matt T
 *
 */

public class ItrTest_12_A_remove0_emptylist
{
	// List running tests on
	private IndexedUnsortedList<Integer> list;

	// Iterator reference for tests 
	private Iterator<Integer> itr; 
	
	// Element not in list - used for negative testing 
	private static final Integer INVALID_ELEMENT = TestCase.F;
		
	//********************Before Each Test Method********************
	/**
	 * Sets up list for testing: uses Parameter in XML file to select the 
	 * dynamic type of the list. 
	 * @param listType - String representing the dynamic type of the list. 
	 */
	@Parameters("listType")
	@BeforeMethod
	public void initList(String listType)
	{
		// create an empty list 
		list = TestCase.newList(listType);
		// state of list before change scenario
		list.add(TestCase.A);
		// the change made to the list 
		list.remove(0); 
	}
	
	/****** Tests for a new Iterator*****************/ 
	/**
	 * Test: new Iterator, hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: false
	 */
	@Test
	public void testItr_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.init);
		TestCase.hasNext(itr, false); 
	}
	
	/**
	 * Test: new Iterator, next() - tries to return ref to next element in the Iterator list
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class)  
	public void testItr_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.init);
		TestCase.next(itr, INVALID_ELEMENT); 
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

}
