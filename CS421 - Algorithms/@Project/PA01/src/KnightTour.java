
/**
 * 
 * @author ahramkim
 */
public class KnightTour {

	/**
	 * Taking care of the arguments
	 */
	public static void main(String[] args) 
	{
		if(args.length != 4) 
		{
			System.err.println("Usage: java KnightTour <0/1/2 (no/heuristic1/heuristic2 search)> "
					+ "<size> <starting point x> <starting point y>");
		}
		
		try 
		{
			new KnightTour(args); 		// create this with args
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private KnightTour(String[] args)
	{
		KnightBoard board = new KnightBoard(0, 0, 0);
		
		int searchMethod = 0;		// 0(no) 1(heuristic1) 2(heuristic2)
		int n = 0;		// size
		int x = 0;		// row
		int y = 0;		// column
		
		if(args[0].equals("0")) 
		{
			// no search method (Move Clock Wise)
			
			searchMethod = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
			x = Integer.parseInt(args[2]);
			y = Integer.parseInt(args[3]);
			
			board.init(n, x, y);
			board.solve0(x, y);
		} 
		else if(args[0].equals("1")) 
		{
			// Heuristic 1 (Move Close to the Edge)
			
			searchMethod = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
			x = Integer.parseInt(args[2]);
			y = Integer.parseInt(args[3]);
			
			board.init(n, x, y);;
			board.solve1(x, y);
			
		} 
		else if(args[0].equals("2")) 
		{
			// Heuristic 2 (Warnsdorff's heuristic)
			
			searchMethod = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
			x = Integer.parseInt(args[2]);
			y = Integer.parseInt(args[3]);
			
			board.init(n, x, y);
			board.solve2(x, y);
		
		} 
		else 
		{
			// else
			
			System.err.println("There is no option.");
			System.err.println("Please select 0, 1 or 2 for the first argument");
		}

		
	}
}
