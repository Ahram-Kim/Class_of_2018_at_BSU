import java.awt.Point;
import java.util.ArrayList;

/**
 * Represents a search state including a potential path through a CircuitBoard
 * 
 * @author mvail
 */
public class TraceState {
	private CircuitBoard board;
	private ArrayList<Point> path;
	
	/** Initial state with the trace path beginning at given row and column
	 * @param startingBoard 
	 * @param row initial path row
	 * @param col initial path column
	 */
	public TraceState(CircuitBoard startingBoard, int row, int col) {
		board = new CircuitBoard(startingBoard);
		path = new ArrayList<Point>();
		board.makeTrace(row, col); //will throw exception if row, col is occupied
		path.add(new Point(row, col));
	}
	
	/** New state adding given row and column position to the path from previous state
	 * @param previousState
	 * @param row row of next point to add to the path
	 * @param col column of next point to add to the path
	 */
	public TraceState(TraceState previousState, int row, int col) {
		board = new CircuitBoard(previousState.board);
		path = new ArrayList<Point>(previousState.path);
		board.makeTrace(row, col); //will throw exception if row, col is occupied
		path.add(new Point(row, col));
	}
	
	/** Indicates if a position is open in this state's board
	 * @param row row of position to check
	 * @param col column of position to check
	 * @return true if given row and column position is open
	 */
	public boolean isOpen(int row, int col) {
		return board.isOpen(row, col);
	}
	
	/** @return path length */
	public int pathLength() {
		return path.size();
	}
	
	/** @return row of the last point in the path */
	public int getRow() {
		return path.get(path.size()-1).x;
	}
	
	/** @return column of the last point in the path */
	public int getCol() {
		return path.get(path.size()-1).y;
	}
	
	/** @return the current CircuitBoard from this state with the path filled in with 'T's */
	public CircuitBoard getBoard() {
		return new CircuitBoard(board);
	}
	
	/** @return list of row, column points that make up the path */
	public ArrayList<Point> getPath() {
		return new ArrayList<Point>(path);
	}
	
	/** @return true if path ends adjacent to ending component */
	public boolean isComplete() {
		return adjacent(path.get(path.size()-1), board.getEndingPoint());
	}
	
	/**
	 * @param p1 first Point
	 * @param p2 second Point
	 * @return true if p1 and p2 are adjacent, else false
	 */
	private boolean adjacent(Point p1, Point p2) {
		if (p1.x-1 == p2.x && p1.y == p2.y) {
			return true;
		}
		if (p1.x+1 == p2.x && p1.y == p2.y) {
			return true;
		}
		if (p1.x == p2.x && p1.y-1 == p2.y) {
			return true;
		}
		if (p1.x == p2.x && p1.y+1 == p2.y) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return board.toString();
	}
}
