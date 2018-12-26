/**
 *  Simulates CPU variable-priority, round-robin scheduling. 
 *  
 * @author CS 321
 */
class CPUScheduler
{
	/**
	 * Main method
	 * @param args - String[]
	 */
    public static void main(String[] args)
    {
    	// simple input verification 
    	if (args.length != 5)
	    {
			System.out.println
			    ("Usage: java CPUScheduling <max-process-time> <max-level> <time-to-increment-priority> <simulation-time> <process arrival rate>");
			System.out.println
			    ("<max-process-time>: max number of unit time for executing a process.");
			System.out.println
			    ("<max-level>: priority levels are 1, 2, .. <max-level>");
			System.out.println
			    ("<time-to-increment-priority>: number of unit times before a process to increment its priority by 1 if this process does not get any CPU time during this time period."); 
			System.out.println
			    ("<simulation-time>: number of unit time for this simulation.");
			System.out.println
			    ("<process arrival rate>: the process arrival probability within each time unit.");
			System.exit(1);
	    }

    	// read in command-line arguments 
		int maxProcessTime = Integer.parseInt(args[0]);
		int maxLevel = Integer.parseInt(args[1]);
		int timeToIncrementPriority = Integer.parseInt(args[2]);
		int simulationTime = Integer.parseInt(args[3]);
		double probability = Double.parseDouble(args[4]);
			
		// initialize classes for running simulation 
		PQueue<Process> pqueue = new PQueue<Process>();
		Averager averager = new Averager();
		ProcessGenerator pGenerator = new ProcessGenerator(probability);
		Process p, next; 
		boolean noArrival; 
		
		// run simulation 
		for (int currentTime = 0; currentTime < simulationTime; currentTime++)
		{
				// print out seconds run 
				System.out.print("second " + currentTime + " :  ");
	
				// Check to see if there is any incoming new process.
				noArrival = false;
				if (pGenerator.query())
			    {
					p = pGenerator.getNewProcess(currentTime, maxProcessTime, maxLevel);
					System.out.print("JOB ID " + currentTime + " arrives, timeRequired " + p.getTimeRemaining() + ", priority " + p.getPriority() + "  ");
					pqueue.insert(p, p.getPriority());
			    }
	
				// If the priority queue is not empty, let the process with
				// highest priority has this time slice. 
				// Update the status of this process, and other processes in the priority queue.
				if (!pqueue.isEmpty())
			    {
					next = pqueue.extractMax();
					next.reduceTimeRemaining();
					pqueue = update(pqueue, timeToIncrementPriority);
					
					if (next.done())
				    {
							System.out.print("(JOB ID " + next.getArrivalTime() + " finish)");
							averager.addNumber(currentTime - next.getArrivalTime() + 1);
				    }
					else
				    {
						if (noArrival)
						{
							System.out.println();
						}
						next.resetTimeNotProcessed();
						pqueue.insert(next, next.getPriority());
				    }
			    }
				
				System.out.println();
		    }
		
			// print results 
			System.out.println("--- Simulation completed ---");
			System.out.println("Simulation time: " + args[3] + " unit time.");
			System.out.println("Number of processes: " 
				   + averager.howManyProcesses());
			System.out.println("Average Turn around time for a process: "
				   + averager.average());
	    }
    
    	/**
    	 * Increments time processes not serviced to avoid starvation. 
    	 * @param pq - PQueue object
    	 * @param timeToIncrementPriority - int value 
    	 *  @return PQueue<Process> object  
    	 */
    	public static PQueue<Process> update(PQueue<Process> pq, int timeToIncrementPriority)
    	{
    		int count = pq.size(); 
    		int timeNP;
    		int keys[] = new int[count]; 
    		Process processes[] = new Process[count]; 
    		Process p;
    		
    		// extract processes in priority queue and update time not processed
    		for(int i = 0; i < count; i++)
    		{
    			p = pq.extractMax();
    			p.incrementTimeNotProcessed();
    			timeNP = p.getTimeNotProcessed();
    			if(timeNP >= timeToIncrementPriority)
    			{
    				p.incrementPriority();
    				p.resetTimeNotProcessed();
    			}
    			keys[i] = p.getPriority();
    			processes[i] = p; 
    		}
    		// rebuild priority queue 
    		pq = new PQueue<Process>(processes, keys); 
    		return pq; 
    	}
}








