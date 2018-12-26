import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Simple class to test whether TestNG is properly installed. 
 * 
 * @author CS 221
 */
public class TestNGSimpleTest
{	
	// variables for tests 
	private String str = "TestNG is working fine";	
	private static final Object[][] VALID_ELEMENTS = { {new Integer(1)}, {new Integer(2)} };

	/**
	 * Simple test. 
	 */
   @Test
   public void testString()
   {
      assertEquals("TestNG is working fine", str);
   }
   
   /**
    * Test data provider functionality.  
    * @param element - Integer 
    */
	@Test(dataProvider = "validElements")
	public void testDataProvider(Integer element)
	{
		assertEquals(element.getClass(), Integer.class);
	}
   
	/*********Data Provider *******/
	/**
	 * Data: Integer elements.
	 * @return 2D array of Integer elements
	 */
	@DataProvider
	   public static Object[][] validElements() 
	   {
	      return VALID_ELEMENTS; 
	   }
}
