/**
 * A class for keeping track of time. 
 *
 * @author CS 221
  */
public class Time
{
	// constants for calculating time in seconds 
	private final int SECONDS_PER_MINUTE = 60;
	private final int MINUTES_PER_HOUR = 60;
	private final int HOURS_PER_DAY = 24;
	private final int SECONDS_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
	// data members 
	private long startTime;	// start time in seconds
	private long deltaTime;	// seconds passed since startTime
	private double totalDrift; 		// seconds lost since start time
	private double driftPerSecond; // amount of drift each second 
	
	/**
	 * Default constructor - time set to midnight, zero drift 
	 */
	public Time()
	{
		this(0, 0, 0, 0);
	}
	
	/**
	 * Constructor for Time class. 
	 * @param hour - integer representing the starting hour (0-23) 
 	 * @param minute - (int) representing the starting minute (0-59)
	 * @param second - (int) representing the starting second (0-59)
	 * @param driftPerSecond - (double) representing amount of drift (time lost) per second 
	 */
	public Time(int hour, int minute, int second, double driftPerSecond)
	{
		startTime = hour * MINUTES_PER_HOUR * SECONDS_PER_MINUTE +
				 minute * SECONDS_PER_MINUTE +
				 second;
		this.driftPerSecond = driftPerSecond; 
		deltaTime = 0;
		totalDrift = 0;
	}
	
	/**
	 * Increment the time by 1 second
	 */
	public void incrementTime()
	{
		// update deltaTime 
		deltaTime++; 
		// update drift
		totalDrift += driftPerSecond; 		
	}
	
	/**
	 * Reset the time to its starting time.  
	 */
	public void resetToStartTime()
	{
		deltaTime = 0;
		totalDrift = 0;
	}
	
	/**
	 * @return - (long) number seconds passed minus accumulated drift
	 */
	public long elapsedTime() 
	{
		return deltaTime - (long)totalDrift; 
	}
		
	/**
	 * @return (int) hour, taking into account accumulated drift
	 */
	public int hour() 
	{
		int seconds = (int)((startTime + elapsedTime()) % SECONDS_PER_DAY);
		int hours = seconds / (MINUTES_PER_HOUR * SECONDS_PER_MINUTE); 
		return hours % 24;
	}
	
	/**
	 * @return (int) minutes, taking into account accumulated drift 
	 */
	public int minute() 
	{
		int seconds = (int)((startTime + elapsedTime()) % SECONDS_PER_DAY);
		int hours = seconds / (MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
		seconds -= hours * (MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
		int minutes = seconds/SECONDS_PER_MINUTE;
		return minutes;
	}

	/**
	 * @return (int) seconds, taking into account accumulated drift 
	 */
	public int second() 
	{
		int seconds = (int)((startTime + elapsedTime()) % SECONDS_PER_DAY);
		int hour = seconds / (MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
		seconds -= hour * (MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
		int minutes = seconds/SECONDS_PER_MINUTE;
		seconds -= minutes * SECONDS_PER_MINUTE;
		return seconds;
	}
	
	/**
	 * Display the values stored in this instance of a Time object 
	 */
	public String formattedTime()
	{
		return String.format("%2d:%02d:%02d", hour(), minute(), second());
	}
	
	/**
	 * @return (double) representing total amount of drift in seconds 
	 */
	public double getTotalDrift()
	{
		return totalDrift;
	}

	/**
	 * @return (double) representing amount of drift per second 
	 */
	public double getDriftPerSecond()
	{
		return driftPerSecond;
	}

} // Time class 
