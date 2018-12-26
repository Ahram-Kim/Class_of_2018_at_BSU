import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for the FormatChecker class. 
 *  
 * @author Matt T
 */
public class FormatCheckerTester
{
	// Files used in tests
	private final String noSuchFile = "noSuchFile.txt";
	private static final Object[][] VALID_FILES = { { "valid1.dat" }, { "valid2.dat" }, { "valid3.dat" } }; 
	private static final Object[][] INPUT_MISMATCH_FILES = { { "invalid1.dat" }, { "invalid3.dat" }, { "invalid6.dat" }, 
																								{ "invalid8.dat" }, { "invalid10.dat" }, { "invalid13.dat" }  }; 
	private static final Object[][] DIMENSION_MISMATCH_FILES = { { "invalid2.dat" }, { "invalid4.dat" }, 
																										  { "invalid5.dat" } , { "invalid7.dat" },
																										  { "invalid9.dat" }, { "invalid11.dat" }, 
																										  { "invalid12.dat" } , { "invalid14.dat" } }; 
	
	/**
	 * Test: File not found 
	 * Expected Result: FileNotFoundException
	 */
	@Test(groups = "notFound", expectedExceptions = FileNotFoundException.class)
	public void testFileNotFound() throws FileNotFoundException
	{
		FormatChecker.checkFormat(noSuchFile);
	}

	/**
	 * Test: Files with valid data 
	 * Expected Result: No exception
	 */
	@Test(groups = "valid", dataProvider = "validFiles")
	public void testValidFiles(String fileName) throws FileNotFoundException
	{
		FormatChecker.checkFormat(fileName);
	}
	
	/**
	 * Test: Invalid files with data that is not a number  
	 * Expected Result: InputMismatchException or NumberFormatException
	 */
	@Test(groups = "inputMismatch", dataProvider = "inputMismatchFiles", expectedExceptions = { NumberFormatException.class, InputMismatchException.class} )
	public void testInputMismatchFiles(String fileName) throws FileNotFoundException
	{
		FormatChecker.checkFormat(fileName);
	}
	
	/**
	 * Test: Invalid files with dimensions not matching data 
	 * Expected Result: DimensionMismatchException
	 */
	@Test(groups = "dimensionMismatch", dataProvider = "dimensionMismatchFiles", expectedExceptions = DimensionMismatchException.class)
	public void testDimensionMismatch(String fileName) throws FileNotFoundException
	{
		FormatChecker.checkFormat(fileName);
	}
	
	//********** Data Providers ***************************
	   /**
	    * Data: names of files that should cause InputMismatchException
	    * or NumberFormatException
	    * 
	    * @return 2D array (second dimension empty) -
	    *      Strings representing names of files 	    
	    * */
	   @DataProvider
	   public static Object[][] inputMismatchFiles() 
	   {
	      return INPUT_MISMATCH_FILES;
	   }
	   
	   /**
	    * Data: names of files that should cause DimensionMismatchException
	    * 
	    * @return 2D array (second dimension empty) -
	    *      Strings representing names of files 	    
	    * */
	   @DataProvider
	   public static Object[][] dimensionMismatchFiles() 
	   {
	      return DIMENSION_MISMATCH_FILES;
	   }

	   /**
	    * Data: names of files that should cause ValidFileException
	    * 
	    * @return 2D array (second dimension empty) -
	    *      Strings representing names of files 	    
	    * */
	   @DataProvider
	   public static Object[][] validFiles() 
	   {
	      return VALID_FILES;
	   }
}
