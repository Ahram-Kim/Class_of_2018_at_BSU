import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 * @author Ahram Kim
 */
public class CircuitTracer 
{
	private CircuitBoard board;
	private Storage<TraceState> stateStore;
	private ArrayList<TraceState> bestPaths;
	private int minPath = Integer.MAX_VALUE;

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) 
	{
		if (args.length != 3) 
		{
			printUsage();
			System.exit(1);
		}
		try 
		{
			new CircuitTracer(args); //create this with args
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() 
	{
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		System.out.println("Enter again, Please");
		System.out.println("For First Arguments: ");
		System.out.println("-s for Stack or -q for Queue");
		System.out.println("For Second Arguments: ");
		System.out.println("-c for console");
		System.out.println("For Third Arguments: input file name");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	private CircuitTracer(String[] args) 
	{
		//TODO: parse command line args
		//TODO: initialize the Storage to use either a stack or queue
		//TODO: read in the CircuitBoard from the given file
		//TODO: run the search for best paths
		//TODO: output results to console or GUI, according to specified choice
		
		if(args.length < 3 || args.length > 3)
		{
			printUsage();
		}
		else
		{
			String first = args[0];
			
			switch(first)
			{
				case "-s":
					stateStore = new Storage<>(Storage.DataStructure.stack);
					break;
				case "-q":
					stateStore = new Storage<>(Storage.DataStructure.queue);
					break;
			}
			
			String second = args[1];
			
			switch(second)
			{
				case "-c":
					break;
				case "-g":
					System.out.println("Sorry, we do not support.");
					System.exit(1);
					break;
			}
			
			String fileName = args[2];
			
			try 
			{
				board = new CircuitBoard(fileName);
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("I cannot find the file name.");
				System.out.println("Try Again.");
			}
		}
		
		bestPaths = new ArrayList<TraceState>();
		
		if(board.isOpen(board.getStartingPoint().x, board.getStartingPoint().y-1))
		{
			TraceState traceState = new TraceState(board, board.getStartingPoint().x, board.getStartingPoint().y-1);
			stateStore.store(traceState);
		}
		if(board.isOpen(board.getStartingPoint().x, board.getStartingPoint().y+1))
		{
			TraceState traceState = new TraceState(board, board.getStartingPoint().x, board.getStartingPoint().y+1);
			stateStore.store(traceState);
		}
		if(board.isOpen(board.getStartingPoint().x-1, board.getStartingPoint().y))
		{
			TraceState traceState = new TraceState(board, board.getStartingPoint().x-1, board.getStartingPoint().y);
			stateStore.store(traceState);
		}
		if(board.isOpen(board.getStartingPoint().x+1, board.getStartingPoint().y))
		{
			TraceState traceState = new TraceState(board, board.getStartingPoint().x+1, board.getStartingPoint().y);
			stateStore.store(traceState);
		}
		
		TraceState currState;
		
		while(!stateStore.isEmpty())
		{
			currState = stateStore.retreive();
			
			if(currState.isComplete())
			{
				if(currState.getPath().size() == minPath)
				{
					bestPaths.add(currState);
					//minPath = currState.pathLength();
				}
				else if(currState.getPath().size() < minPath)
				{
					bestPaths.clear();
					bestPaths.add(currState);
					minPath = currState.getPath().size();
				}
			} 
			else
			{
				if(currState.isOpen(currState.getRow(), currState.getCol()-1))
				{
					TraceState traceState = new TraceState(currState, currState.getRow(), currState.getCol()-1);
					stateStore.store(traceState);
				}
				if(currState.isOpen(currState.getRow(), currState.getCol()+1))
				{
					TraceState traceState = new TraceState(currState, currState.getRow(), currState.getCol()+1);
					stateStore.store(traceState);
				}
				if(currState.isOpen(currState.getRow()-1, currState.getCol()))
				{
					TraceState traceState = new TraceState(currState, currState.getRow()-1, currState.getCol());
					stateStore.store(traceState);
				}
				if(currState.isOpen(currState.getRow()+1, currState.getCol()))
				{
					TraceState traceState = new TraceState(currState, currState.getRow()+1, currState.getCol());
					stateStore.store(traceState);
				}
			}
		}
		
		System.out.println("Original Circuit : ");
		System.out.println(board.toString());
		System.out.println();
		
		for(TraceState t: bestPaths)
		{
			System.out.println("The shortest path : ");
			System.out.println(t.getBoard().toString());
		}

		System.out.println();
	}
	
} // class CircuitTracer
