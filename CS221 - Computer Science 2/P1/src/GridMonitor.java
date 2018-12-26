import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridMonitor implements GridMonitorInterface {

	private double[][] baseGrid;
	private double[][] sumGrid;
	private double[][] avgGrid;
	private double[][] deltaGrid;
	private boolean[][] dangerGrid;
	private int row, col;

	public GridMonitor(String filename) {

		Scanner scan;
		try {
			scan = new Scanner(new File(filename));

			row = scan.nextInt();
			col = scan.nextInt();

			baseGrid = new double[row][col];

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					baseGrid[i][j] = scan.nextDouble();
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}

		sumGrid = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				double sum = 0;

				// Check top neighbor
				if (i == 0) {
					sum += baseGrid[i][j];
				} else {
					sum += baseGrid[i - 1][j];
				}

				// Check below neighbor
				if (i == row - 1) {
					sum += baseGrid[i][j];
				} else {
					sum += baseGrid[i + 1][j];
				}

				// Check left neighbor
				if (j == 0) {
					sum += baseGrid[i][j];
				} else {
					sum += baseGrid[i][j - 1];
				}

				// Check right neighbor
				if (j == col - 1) {
					sum += baseGrid[i][j];
				} else {
					sum += baseGrid[i][j + 1];
				}
				sumGrid[i][j] = sum;
			}
		}

		avgGrid = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				avgGrid[i][j] = sumGrid[i][j] / 4;
			}
		}

		deltaGrid = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				deltaGrid[i][j] = avgGrid[i][j] / 2;
			}
		}

		dangerGrid = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				double min = avgGrid[i][j] - deltaGrid[i][j];
				double max = avgGrid[i][j] + deltaGrid[i][j];

				if (baseGrid[i][j] > max || baseGrid[i][j] < min) {
					dangerGrid[i][j] = true;
				} else {
					dangerGrid[i][j] = false;
				}

			}
		}

	}
	
	@Override
	public double[][] getBaseGrid() {

		double[][] baseTable = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				baseTable[i][j] = baseGrid[i][j];
			}
		}
		return baseTable;
	}

	@Override
	public double[][] getSurroundingSumGrid() {
		// TODO Auto-generated method stub

		double[][] sumTable = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sumTable[i][j] = sumGrid[i][j];
			}
		}
		return sumTable;
	}

	@Override
	public double[][] getSurroundingAvgGrid() {
		// TODO Auto-generated method stub

		double[][] avgTable = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				avgTable[i][j] = avgGrid[i][j];
			}
		}
		return avgTable;
	}

	@Override
	public double[][] getDeltaGrid() {
		// TODO Auto-generated method stub

		double[][] deltaTable = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				deltaTable[i][j] = deltaGrid[i][j];
			}
		}
		return deltaTable;

	}

	@Override
	public boolean[][] getDangerGrid() {
		// TODO Auto-generated method stub

		boolean[][] dangerTable = new boolean[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				dangerTable[i][j] = dangerGrid[i][j];
			}
		}
		return dangerTable;

	}
}