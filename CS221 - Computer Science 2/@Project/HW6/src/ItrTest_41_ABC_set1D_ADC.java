import java.util.Iterator;
import java.util.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Testing for IndexedUnsortedList interface implementation: 
 * Iterator Tests for Change Scenario 41: [A, B, C] -> set(1, D) -> [A, D, C]
 * 
 * @author Matt T 
 */
public class ItrTest_41_ABC_set1D_ADC
{
	// List running tests on
	private IndexedUnsortedList<Integer> list;

	// Iterator reference for tests 
	private Iterator<Integer> itr; 
	
	// First element in list
	private static final Integer FIRST = TestCase.A;
	// Middle element in list
	private static final Integer MIDDLE = TestCase.D;
	// Last element in list 
	private static final Integer LAST = TestCase.C;	
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
		// create empty list 
		list = TestCase.newList(listType);
		// state of list before change scenario
		list.add(TestCase.A);
		list.add(TestCase.B);
		list.add(TestCase.C);
		// the change made to the list 
		list.set(1, TestCase.D);
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
	 * Expected Result: true
	 */
	@Test
	public void testItrNext_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.next);
		TestCase.hasNext(itr, true); 
	}
	
	/**
	 * Test: new Iterator, call next(); test next() - returns ref to next element in the Iterator list
	 * Expected Result: Reference to middle element in list
	 */
	@Test
	public void testItrNext_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.next);
		TestCase.next(itr, MIDDLE); 
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
	
	/******** Call next() twice, then run tests **************/
	/**
	 * Test: new Iterator, call next() twice; test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: true
	 */
	@Test
	public void testItrNextNext_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNext);
		TestCase.hasNext(itr, true); 
	}
	
	/**
	 * Test: new Iterator, call next() twice; test next() - returns ref to next element in the Iterator list
	 * Expected Result: Reference to last element in list
	 */
	@Test
	public void testItrNextNext_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNext);
		TestCase.next(itr, LAST); 
	}

	/**
	 * Test: new Iterator, call next() twice; test remove() - removes last element returned by next in the Iterator list
	 * Expected Result: No exception
	 */
	@Test 
	public void testItrNextNext_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNext);
		TestCase.remove(itr); 
	}
	
	/********* Call next(), remove(), then run tests*********/
	/**
	 * Test: new Iterator, call next(), remove(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: true
	 */
	@Test
	public void testItrNextRemove_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemove);
		TestCase.hasNext(itr, true); 
	}
	
	/**
	 * Test: new Iterator, call next(), remove(); test next() - returns ref to next element in the Iterator list
	 * Expected Result: Reference to last element in list
	 */
	@Test
	public void testItrNextRemove_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemove);
		TestCase.next(itr, MIDDLE); 
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
	
	/*******Call next(), remove(), next(), then run tests********/
	/**
	 * Test: new Iterator, call next(), remove(), next(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: true
	 */
	@Test
	public void testItrNextRemoveNext_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemoveNext);
		TestCase.hasNext(itr, true); 
	}
	
	/**
	 * Test: new Iterator, call next(), remove(), next(); test next() - returns ref to next element in the Iterator list
	 * Expected Result: Reference to last element in list
	 */
	@Test
	public void testItrNextRemoveNext_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemoveNext);
		TestCase.next(itr, LAST); 
	}

	/**
	 * Test: new Iterator, call next(), remove(), next(); test remove() - removes last element returned by next in the Iterator list
	 * Expected Result: No exception
	 */
	@Test
	public void testItrNextRemoveNext_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextRemoveNext);
		TestCase.remove(itr); 
	}
	
	/*********Call next() twice, remove(), then run tests**********/
	/**
	 * Test: new Iterator, call next(), next(), remove(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: true
	 */
	@Test
	public void testItrNextNextRemove_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextRemove);
		TestCase.hasNext(itr, true); 
	}
	
	/**
	 * Test: new Iterator, call next(), next(), remove(); test next() - returns ref to next element in the Iterator list
	 * Expected Result: Reference to last element in list
	 */
	@Test
	public void testItrNextNextRemove_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextRemove);
		TestCase.next(itr, LAST); 
	}

	/**
	 * Test: new Iterator, call next(), next(), remove(); test remove() - tries to remove last element returned by next in the Iterator list
	 * Expected Result: IllegalStateException
	 */
	@Test(expectedExceptions = IllegalStateException.class) 
	public void testItrNextNextRemove_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextRemove);
		TestCase.remove(itr); 
	}
	
	/*********Call next() three times, then run tests**********/
	/**
	 * Test: new Iterator, call next(), next(), next(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: false
	 */
	@Test
	public void testItrNextNextNext_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextNext);
		TestCase.hasNext(itr, false); 
	}
	
	/**
	 * Test: new Iterator, call next(), next(), next(); test next() - tries to return ref to next element in the Iterator list
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class) 
	public void testItrNextNextNext_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextNext);
		TestCase.next(itr, INVALID_ELEMENT); 
	}

	/**
	 * Test: new Iterator, call next(), next(), next(); test remove() - removes last element returned by next in the Iterator list
	 * Expected Result: No exception
	 */
	@Test
	public void testItrNextNextNext_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextNext);
		TestCase.remove(itr); 
	}

	/*********Call next() three times, remove(), then run tests**********/
	/**
	 * Test: new Iterator, call next(), next(), next(), remove(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: false
	 */
	@Test
	public void testItrNextNextNextRemove_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextNextRemove);
		TestCase.hasNext(itr, false); 
	}
	
	/**
	 * Test: new Iterator, call next(), next(), next(), remove(); test next() - tries to return ref to next element in the Iterator list
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class) 
	public void testItrNextNextNextRemove_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextNextRemove);
		TestCase.next(itr, INVALID_ELEMENT); 
	}

	/**
	 * Test: new Iterator, call next(), next(), next(), remove(); test remove() - tries to remove last element returned by next in the Iterator list
	 * Expected Result: IllegalStateException
	 */
	@Test(expectedExceptions = IllegalStateException.class) 
	public void testItrNextNextNextRemove_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextNextRemove);
		TestCase.remove(itr); 
	}

	/*********Call next() twice, remove(), next(), then run tests**********/
	/**
	 * Test: new Iterator, call next(), next(), remove(), next(); test hasNext() - whether there's a next element in the Iterator list
	 * Expected Result: false
	 */
	@Test
	public void testItrNextNextRemoveNext_hasNext()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextRemoveNext);
		TestCase.hasNext(itr, false); 
	}
	
	/**
	 * Test: new Iterator, call next(), next(), remove(), next(); test next() - tries to return ref to next element in the Iterator list
	 * Expected Result: NoSuchElementException
	 */
	@Test(expectedExceptions = NoSuchElementException.class) 
	public void testItrNextNextRemoveNext_next()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextRemoveNext);
		TestCase.next(itr, INVALID_ELEMENT); 
	}

	/**
	 * Test: new Iterator, call next(), next(), remove(), next(); test remove() - removes last element returned by next in the Iterator list
	 * Expected Result: No exception
	 */
	@Test
	public void testItrNextNextRemoveNext_remove()
	{
		itr = TestCase.initItr(list, TestCase.ItrState.nextNextRemoveNext);
		TestCase.remove(itr); 
	}

}
