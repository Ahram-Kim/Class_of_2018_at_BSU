
public class Prelab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final int NROWS = 3;
		final int NCOLS = 5;
		final int MAXVAL = 10;
		
		int[][] grid = new int[NROWS][NCOLS];
		
		for(int row= 0; row<grid.length; row++) {
			for(int col = 0; col<grid[row].length; col++) {
				//grid[row][col]=row*grid[row].length+col;
				grid[row][col]=col+(NCOLS*row);
				System.out.println(grid[row][col]);
			}	 
		}
		
		for(int row = 0; row<grid.length; row++) {
			for(int col=0; col<grid.length; col++) {
				//grid[row][col]=row*grid[row].length+MAXVAL;
				grid[row][col] = (col+(NCOLS*row))% (MAXVAL+1);
				System.out.println(grid[row][col]);
			}
		}
		
//		Photo[][] photoSquare = new Photo[3][3];
//		Photo photo = photoSquare[2][1];
//		for()
		
	}

}
