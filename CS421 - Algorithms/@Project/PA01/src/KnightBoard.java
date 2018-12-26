
import java.util.*;

/**
 * Implementation for KnightTour.java
 * @author ahramkim
 */
public class KnightBoard 
{
	private Position pos;
	private int board[][];
	private int h1board[][];
	private int numMoving;
	private int boardSize;
	
	// Moves of a Knight
	private int xMoving[] = {-2, -1, 1, 2, 2, 1, -1, -2};
	private int yMoving[] = {1, 2,  2, 1, -1, -2, -2, -1};

	/**
	 * Basic constructor.
	 */
	public KnightBoard(int size, int initX, int initY) 
	{
		board = new int[size][size];
		h1board	= new int[size][size];
		boardSize = size;
		pos = new Position(size, initX, initY);	
		initializeBoard();
		initializeh1Board();
	}

	/**
	 * Initialize the chess board which can move the knight.
	 * 
	 * @param size
	 * @param initX
	 * @param initY
	 */
	public void init(int size, int initX, int initY)
	{
		board = new int[size][size];
		h1board	= new int[size][size];
		boardSize = size;
		pos = new Position(size, initX, initY	                                                                                                                    );	
		initializeBoard();
		initializeh1Board();
	}

	/**
	 * Initialize boardSize*boardSize sized knightboard
	 * and set the position with initial value '-1'
	 */
	private void initializeBoard()
	{
		for(int x = 0; x < boardSize; x++)
		{
			for(int y = 0; y < boardSize; y++)
			{
				board[x][y] = -1;
			}
		}
//		int initX = pos.getX();
//		int initY = pos.getY();
//		board[initX][initY] = 1;
	}
	
	/**
	 * Method for solve1();
	 * Initialize boardSize*boardSize sized knightboard for Heuristic 1 and
	 * set the position with initial score following the closest to the edge.
	 * ex) 0 0 0 0 0
	 * 	   0 1 1 1 0
	 * 	   0 1 2 1 0
	 *     0 1 1 1 0
	 *     0 0 0 0 0
	 */
	public void initializeh1Board()
	{
		for(int x = 0; x < boardSize; x++)
		{
			for(int y = 0; y < boardSize; y++)
			{
				h1board[x][y] = getScore(x,y);
			}
		}
	}
	
	/**
	 * get the position with initial score following the closest to the edge.
	 * ex) 0 0 0 0 0
	 * 	   0 1 1 1 0
	 * 	   0 1 2 1 0
	 *     0 1 1 1 0
	 *     0 0 0 0 0
	 * 
	 * @param x
	 * @param y
	 * @return value;
	 */
	public int getScore(int x, int y)
	{
		int value = 999;
		
		if(Math.abs(0 - x) < value)
		{
			value = Math.abs(0 - x);
		}
		if(Math.abs((boardSize - 1) - x) < value)
		{
			value = Math.abs((boardSize - 1) - x);
		}
		if(Math.abs(0 - y) < value)
		{
			value = Math.abs(0 - y);
		}
		if(Math.abs((boardSize - 1) - y) < value)
		{
			value = Math.abs((boardSize - 1) - y);
		}
		h1board[x][y] = value;
		return value;
	}
	
	/**
	 * Check the position whether the value in position is empty or not.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEmpty(int x, int y)
	{
		return board[x][y] == -1;
	}

	/**
	 * Check the position whether it is on the board or not.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isOnBoard(int x, int y)
	{
		return (x >= 0 && x < boardSize && y >= 0 && y < boardSize);
	}
	
	/**
	 * Solution for Heuristic search 0.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean solve0(int x, int y)
	{
		board[x][y] = 1;

		if(!moveClockWise(x, y, 2))
		{
			System.out.println("The total number of Moves is " + numMoving);
			System.out.println("There was no solution found!\n");
			return false;
		}
		else
		{
			System.out.println("The total number of Moves is " + numMoving);
			System.out.println(printBoard());
		}
		return moveClockWise(x, y, 2);
	}

	/**
	 * Solution for Heuristic search 0.
	 * Position is moving to clock wise.
	 * 
	 * @param x
	 * @param y
	 * @param movei
	 * @return
	 */
	public boolean moveClockWise(int x, int y, int movei)
	{
		int nextX, nextY, i;

		if(movei > (boardSize * boardSize))
		{
			return true;
		}
		
		// Recursion method to find all solution
		for(i = 0; i < 8; i++)
		{
			nextX = x + xMoving[i];
			nextY = y + yMoving[i];

			if(isOnBoard(nextX, nextY) && isEmpty(nextX, nextY))
			{
				board[nextX][nextY] = movei;
				numMoving++;

				if(moveClockWise(nextX, nextY, movei + 1))
				{
					return true;
				}
				else
				{
					board[nextX][nextY] = -1;
				}
			}
		}
		return false;
	}
	
	/**
	 * Solution for Heuristic search 1.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean solve1(int x, int y)
	{
		board[x][y] = 1;

		if(!moveClosetoEdge(x, y, 2))
		{
			System.out.println("The total number of Moves is " + numMoving);
			System.out.println("There was no solution found!\n");
			return false;
		}
		else
		{
			System.out.println("The total number of Moves is " + numMoving);
			System.out.println(printBoard());
//			System.out.println(printh1Table());
		}
		return moveClosetoEdge(x, y, 2);
	}

	/**
	 * Solution for Heuristic search 1.
	 * Position is moving close to the edge of board.
	 * 
	 * @param x
	 * @param y
	 * @param movei
	 * @return
	 */
	public boolean moveClosetoEdge(int x, int y, int movei)
	{
		int nextX, nextY;
		
		if(movei > (boardSize * boardSize))
		{
			return true;
		}

		//generate a sorted arrayList of Position
		ArrayList<Position> arrayPos;
		initializeh1Board();
		arrayPos= getSortedPositions(x, y);

		for(Position pos: arrayPos)
		{
			nextX = pos.getX();
			nextY = pos.getY();

			if(isOnBoard(nextX, nextY) && isEmpty(nextX, nextY))
			{
				board[nextX][nextY] = movei;
				numMoving++;

				if(moveClosetoEdge(nextX, nextY, movei + 1))
				{
					return true;
				}
				else
				{
					board[nextX][nextY] = -1;
				}
			}
		}
		return false;
	}
	
	/**
	 * Solution for Heuristic search 1
	 * To find all eligible position.
	 * 
	 * @param x
	 * @param y
	 * @return list of position sorted to border
	 */
	@SuppressWarnings({ "unchecked" })
	private ArrayList<Position> getSortedPositions(int x, int y)
	{
		int nextX, nextY;
		
		ArrayList<Position> arrayPos = new ArrayList<Position>();
		for(int i = 0; i < 8; i++)
		{
			nextX = x + xMoving[i];
			nextY = y + yMoving[i];
			
			if(isOnBoard(nextX, nextY) && isEmpty(nextX, nextY))
			{
				Position pos = new Position(nextX, nextY);
				pos.setScore(h1board[nextX][nextY]);
				arrayPos.add(pos);
			}
		}
		Collections.sort(arrayPos);
		return arrayPos;
	}

	/**
	 * Solve for Heuristic search 2
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean solve2(int x, int y)
	{
		board[x][y] = 1;

		if(!ruleOfWarndorff(x, y, 2))
		{
			System.out.println("The total number of Moves is " + numMoving);
			System.out.println("There was no solution found!\n");
		}
		else
		{
			System.out.println("The total number of Moves is " + numMoving);
			System.out.println(printBoard());
		}
		return ruleOfWarndorff(x, y, 2);
	}
	
	/**
	 * Solution for Heuristic search 2 (Warndorff's Rule)
	 * 
	 * @param x
	 * @param y
	 * @param movei
	 * @return
	 */
	public boolean ruleOfWarndorff(int x, int y, int movei)
	{
		int nextX, nextY;
		if(movei > (boardSize * boardSize))
		{
			return true;
		}

		//generate a sorted arrayList of Position
		ArrayList<Position> arrayPos = getSortedWarndorff(x, y);
		
		for(Position pos : arrayPos)
		{
			nextX = pos.getX();
			nextY = pos.getY();

			if(isOnBoard(nextX, nextY) && isEmpty(nextX, nextY))
			{
				board[nextX][nextY] = movei;
				numMoving++;

				if(ruleOfWarndorff(nextX, nextY, movei + 1))
				{
					return true;
				}
				else
				{
					board[nextX][nextY] = -1;
				}
			}
		}
		return false;
	}
	
	/**
	 * Solution for Heuristic search 2 (Warndorff's Rule)
	 * To find the number of moving.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private ArrayList<Position> getSortedWarndorff(int x, int y)
	{
		int nextX, nextY;
		ArrayList<Position> arrayPos = new ArrayList<Position>();
		
		for(int i = 0; i < 8; i++)
		{
			nextX = x + xMoving[i];
			nextY = y + yMoving[i];

			if(isOnBoard(nextX, nextY) && isEmpty(nextX, nextY))
			{
				Position pos = new Position(nextX, nextY);
				pos.setScore(getNumOfMoves(nextX, nextY));
				arrayPos.add(pos);
			}
		}
		Collections.sort(arrayPos);
		return arrayPos;
	}
	
	/**
	 * Solution for Heuristic search 2 (Warndorff's Rule)
	 * To get the number of Movings
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int getNumOfMoves(int x,int y)
	{
		int nextX, nextY, i, counter = 0;
		
		for(i = 0; i < 8; i++)
		{
			nextX = x + xMoving[i];
			nextY = y + yMoving[i];
			
			if(isOnBoard(nextX, nextY) && isEmpty(nextX, nextY))
			{
				counter++;
			}
		}
		return counter;
	} 

	/**
	 * Print the chess board table.
	 * 
	 * @return str.toString();
	 */
	public String printBoard()
	{
		StringBuilder str = new StringBuilder();

		for(int row = 0; row < boardSize; row++)
		{
			for(int col = 0; col < boardSize; col++)
			{
				int b = board[row][col];
				String boardString = String.format("%02d", b);
				str.append(boardString + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
	/**
	 * Print the chess board table for Heuristic search 1.
	 * 
	 * @return str.toString();
	 */
	public String printH1Board()
	{
		StringBuilder str = new StringBuilder();

		for(int row = 0; row < boardSize; row++)
		{
			for(int col = 0; col < boardSize; col++)
			{
				int b = h1board[row][col];
				String boardString = String.format("%02d", b);
				str.append(boardString + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
}
