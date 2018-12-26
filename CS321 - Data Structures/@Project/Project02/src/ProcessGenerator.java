import java.util.*;


/**
 * Models processes arriving at a CPU
 * 
 * @author Ahram Kim
 */
public class ProcessGenerator 
{
	private double probability;
	private Random random;
	
	/**
	 * Basic constructor supporting preloading of process generator contents. 
	 * 
	 * @param probability
	 */
	public ProcessGenerator(double probability)
	{
		random = new Random();
		this.probability = probability;
	}
	
	/**
	 * Return random value is less than and equal with probability
	 * 
	 * @return random.nextDouble() <= probability
	 */
	public boolean query()
	{
		return (random.nextDouble() <= probability);	
	}
	
	/**
	 * return the processGenerator of type Process.
	 * 
	 * @param currentTime
	 * @param maxProcessTime
	 * @param maxPriorityLevel
	 * 
	 * @return pGenerator
	 */
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxPriorityLevel)
	{
		Process pGenerator = new Process(currentTime, random.nextInt(maxPriorityLevel), random.nextInt(maxProcessTime), maxPriorityLevel);
		
		return pGenerator;
	}
	
	/**
	 * Sets probability to given value.
	 * 
	 * @param probability - probability of type double
	 */
	private void setProbability(double probability)
	{
		this.probability = probability;
	}
} 
