/**
 * Tracks number of processes executed and the
 * sum of their turn-around times for this simulation.
 * 
 * @author CS 321 
 */

public class Averager
{
    private int count;  		// number of processes
    private double sum;	// sum of their turn-around times 

    /**
     * Default constructor 
     */
    public Averager()
    {
    	setCount(0); 
    	setSum(0); 
    }

    /**
     * Adds value to average, if not overflow MAX_VALUE
     * @param value - double value 
     * @throws IllegalStateException - when max out number of processes
     */
    public void addNumber(double value)
    {
    	if (count == Integer.MAX_VALUE)
    	{
    		throw new IllegalStateException("Too many Processes.");
    	}
    	
    	count++;
    	updateSum(value); 
    }

    /**
     * 
     * @return - 
     */
    public double average()
    {
    	double average = Double.NaN; 
		if (count > 0)
		{
		    average = getSum()/getCount();
		}
		return average; 
    }

    /**
     * Return number of processes.
     * @return - int value 
     */
    public int howManyProcesses()
    {
    	return count;
    }
    
    /**
     * Return count. 
     * @return - int value 
     */
    private int getCount()
    {
    	return count; 
    }
    
    /**
     * Sets count to given value 
     * @param value - int value 
     */
    private void setCount(int value)
    {
    	count = value; 
    }
    
    /**
     * Returns sum.
     * @return - double value 
     */
    private double getSum()
    {
    	return sum; 
    }
    
    /**
     * Sets sum to given value. 
     * @param value
     */
    private void setSum(double value)
    {
    	sum = value; 
    }
    
    /**
     * Adds given value to the sum. 
     * @param value - double value 
     */
    private void updateSum(double value)
    {
    	sum += value; 
    }
}
