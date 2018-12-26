import java.util.*;

// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

/**
 * A class that will store assigned variables in the future
 * 
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class Environment 
{
	private HashMap<String, Double> hm;
	
	/**
	 * Basic Constructor
	 */
	public Environment()
	{
		hm = new HashMap<String, Double>();
	}
	
	/**
	 * Put a variable into the environment
	 * 
	 * @param var
	 * @param user
	 * @return val
	 */
	public double put(String var, double user) 
	{ 
		hm.put(var, user);
		return user; 
	}

	/**
	 * Get a variable from the environment
	 *  
	 * @param pos - the position of the scanner/parser where the variable should be
	 * @param var - variable
	 * @return hm.get(var) 
	 * @throws EvalException
	 */
	public double get(int pos, String var) throws EvalException 
	{ 
		if(hm.containsKey(var))
			return hm.get(var); 
		throw new EvalException(pos, "error");
	}
}