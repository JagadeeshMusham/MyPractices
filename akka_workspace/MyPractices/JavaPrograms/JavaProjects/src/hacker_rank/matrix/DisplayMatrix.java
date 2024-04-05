package hacker_rank.matrix;

import java.util.Scanner;

public class DisplayMatrix {
	private static int nTotalRows;
	private static int nTotalCols;

	private static int[][] matrix;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter number of rows: ");
		nTotalRows = scanner.nextInt();

		System.out.println("Enter number of cols: ");
		nTotalCols = scanner.nextInt();

		matrix = new int[nTotalRows][nTotalCols];

		for (int currentRow = 0; currentRow < nTotalRows; currentRow++) {
			for (int currentCol = 0; currentCol < nTotalCols; currentCol++) {
				matrix[currentRow][currentCol] = scanner.nextInt();
			}
		}

		System.out.println("Total matrix is:");
		printMatrix(0, 0);
	}

	private static void printMatrix(int currentRow, int currentCol) {
		System.out.print(matrix[currentRow][currentCol] + " ");

		if (currentRow >= nTotalRows - 1) {
			if (currentCol == nTotalCols - 1) {
				return;
			}

			if (currentCol >= nTotalCols - 1) {
				++currentRow;
				currentCol = 0;
			} else {
				++currentCol;
			}
		} else {
			if (currentCol >= nTotalCols - 1) {
				System.out.println();
				++currentRow;
				currentCol = 0;
			} else {
				++currentCol;
			}
		}

		printMatrix(currentRow, currentCol);
	}
}
