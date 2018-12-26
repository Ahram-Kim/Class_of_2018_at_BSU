import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FormatChecker {
	
	public static void checkFormat(String fileName) throws FileNotFoundException {
		
		int row;
		int col;
		double[][] Grid;
		Scanner scan;
		
		try {
			
			scan = new Scanner(new File(fileName));

			row = scan.nextInt();
			col = scan.nextInt();
			int counter = 0;
			
			while (scan.hasNextLine()) {
				if(scan.nextLine().length() != 0) {
					counter++;
				} 
			}
			
			scan = new Scanner(new File(fileName));
			scan.nextLine();
			
			if (row != counter) {
				throw new DimensionMismatchException();
			}
			
			Grid = new double[row][col];

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(scan.hasNextDouble()) {
						Grid[i][j] = scan.nextDouble();
					} else if (scan.hasNext()){
						throw new NumberFormatException();
					} else {
						throw new DimensionMismatchException();
					}
				}
			}
			
			if (scan.hasNext()) {
				throw new DimensionMismatchException();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Found not File");
			throw new FileNotFoundException();
		} 
		
	}
}
