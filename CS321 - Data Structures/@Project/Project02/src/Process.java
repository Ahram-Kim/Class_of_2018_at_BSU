import java.util.*;

/**
 * Models a CPU process.
 * 
 * @author Ahram Kim
 */
public class Process
{
	private int priorityLevel;
	private int timeToFinish;
	private int timeNotProcessed;
	private int arrivalTime;
	private int maxPriorityLevel;
	
	/**
	 * Basic constructor with four parameters
	 * 
	 * @param arrivalTime
	 * @param priorityLevel
	 * @param timeToFinish
	 * @param maxPriorityLevel
	 */
	public Process(int arrivalTime, int priorityLevel, int timeToFinish, int maxPriorityLevel)
	{
		this.arrivalTime = arrivalTime;
		this.priorityLevel = priorityLevel;
		this.timeToFinish = timeToFinish;
		this.maxPriorityLevel = maxPriorityLevel;
	}
	
	/**
	 * Decrement the value of the time to finish
	 */
	public void reduceTimeRemaining()
	{
		timeToFinish--;
	}
	
	/**
	 * Increment the value of time which is not processed.
	 */
	public void incrementTimeNotProcessed()
	{
		timeNotProcessed++;
	}
	
	/**
	 * Return the true when time to process is finished
	 * 
	 * @return (timeToFinish <= 0)
	 */
	public boolean done()
	{
		return (timeToFinish <= 0);
	}
	
	/**
	 * Increment the value of priority level.
	 */
	public void incrementPriority()
	{
		priorityLevel++;
	}
	
	/**
	 * Represent time which is not processed is reset
	 */
	public void resetTimeNotProcessed()
	{
		timeNotProcessed = 0;
	}
	
	/**
	 * Sets timeToFinish to given value
	 * 
	 * @param timeToFinish
	 */
	private void setTimeToFinish(int timeToFinish)
	{
		this.timeToFinish = timeToFinish;
	}
	
	/**
	 * Get the value of timeToFinish
	 * 
	 * @return timeToFinish
	 */
	public int getTimeToFinish()
	{
		return timeToFinish;
	}
	
	/**
	 * Sets priorityLevel to given value 
	 * 
	 * @param priorityLevel - priorityLevel of type int
	 */
	private void setPriorityLevel(int priorityLevel)
	{
		this.priorityLevel = priorityLevel;
	}
	
	/**
	 * get the value of Priority
	 * 
	 * @return priorityLevel
	 */
	public int getPriority()
	{
		return priorityLevel;
	}
	
	/**
	 * Sets timeNotProcess to give value
	 * 
	 * @param timeNotProcess - timeNotProcess of type int
	 */
	private void setTimeNotProcessed(int timeNotProcess)
	{
		this.timeNotProcessed = timeNotProcess;
	}
	
	/**
	 * get the value of timeNotProcessed
	 * 
	 * @return timeNotProcessed
	 */
	public int getTimeNotProcessed()
	{
		return timeNotProcessed;	
	}
	
	/**
	 * Sets arrivalTime to given value
	 * 
	 * @param arrivalTime
	 */
	private void setArrivalTime(int arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * Get the value of arrivalTime
	 *  
	 * @return arrivalTime
	 */
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	/**
	 * Sets maxPriorityLevel to given value
	 * 
	 * @param maxPriorityLevel
	 */
	public void setMaxPriorityLevel(int maxPriorityLevel)
	{
		this.maxPriorityLevel = maxPriorityLevel;
	}
	
	/**
	 * Get the value of maxPriorityLevel
	 * 
	 * @return maxPriorityLevel
	 */
	public int getMaxPriorityLevel()
	{
		return maxPriorityLevel;
	}

	/**
	 * Get the value of timeRemaining;
	 * 
	 * @return 1-timeToFinish
	 */
	public int getTimeRemaining()
	{
		return 1-timeToFinish;
	}
}
