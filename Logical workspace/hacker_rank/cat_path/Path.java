package hacker_rank.cat_path;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Path {
	private static List<Node> visitedNodeList;
	private static List<Node> nodeList;

	private static int startRow;
	private static int startCol;
	private static int endRow;
	private static int endCol;

	private static int maxRows;
	private static int maxCols;

	private static int[][] matrix;
	
	private static boolean foundPath;

	public static void main(String[] args) {
		visitedNodeList = new ArrayList<>();
		nodeList = new ArrayList<>();
		foundPath = false;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of rows");
		// int rows = scanner.nextInt();
		maxRows = 4;
		System.out.println("Enter the number of columns");
		// int cols = scanner.nextInt();
		maxCols = 4;

		matrix = new int[maxRows][maxCols];

		// System.out.println("Enter the position for " + rows + " " + cols);
		// for (int r = 0; r < rows; r++) {
		// for (int c = 0; c < cols; c++) {
		// System.out.println("Enter the position for " + r + " " + c);
		// matrix[r][c] = scanner.nextInt();
		// }
		// }

		matrix[0][0] = 1;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[0][3] = 0;
		matrix[1][0] = 1;
		matrix[1][1] = 1;
		matrix[1][2] = 1;
		matrix[1][3] = 1;
		matrix[2][0] = 0;
		matrix[2][1] = 1;
		matrix[2][2] = 0;
		matrix[2][3] = 0;
		matrix[3][0] = 1;
		matrix[3][1] = 1;
		matrix[3][2] = 1;
		matrix[3][3] = 1;

		System.out.println("Enter the row starting position ");
		startRow = scanner.nextInt();
		System.out.println("Enter the column starting position ");
		startCol = scanner.nextInt();

		System.out.println("Enter the row ending position ");
		endRow = scanner.nextInt();
		System.out.println("Enter the column ending position ");
		endCol = scanner.nextInt();

		findPath();

		System.out.println("We have path now");
		printPath();
	}

	private static void findPath() {
		Node node = new Node();
		node.setRow(startRow);
		node.setCol(startCol);

		nodeList.add(node);
		visitedNodeList.add(node);

		getPath(node);

	}

	private static void getPath(Node node) {
		for (int counter = 0; counter < 4; counter++) {
			if (foundPath) {
				return;
			}
			int row = node.getRow();
			int col = node.getCol();

			if (counter == 0) {
				if (row == 0) {
					continue;
				}
				row -= 1;
			} else if (counter == 1) {
				if (col == maxCols - 1) {
					continue;
				}
				col += 1;
			} else if (counter == 2) {
				if (row == maxCols - 1) {
					continue;
				}
				row += 1;
			} else if (counter == 3) {
				if (col == 0) {
					continue;
				}
				col -= 1;
			}

			if (matrix[row][col] == 1) {
				Node addedNode = addNode(row, col);
				if (addedNode != null) {
					getPath(addedNode);
				}
			}
		}
		
		if (node.getRow() != endRow || node.getCol() != endCol) {
			nodeList.remove(nodeList.size() - 1);
		} else {
			foundPath = true;
		}
	}

	private static Node addNode(int curRow, int curCol) {
		Node node = new Node();
		node.setRow(curRow);
		node.setCol(curCol);

		if (nodeNotVisited(node)) {
			nodeList.add(node);
			visitedNodeList.add(node);
			return node;
		}

		return null;
	}

	private static boolean nodeNotVisited(Node node) {
		for (int counter = 0; counter < visitedNodeList.size(); counter++) {
			Node curNode = visitedNodeList.get(counter);
			if (node.getCol() == curNode.getCol() && node.getRow() == curNode.getRow())
				return false;
		}
		return true;
	}

	private static void printPath() {
		System.out.println("The Path is: ");
		for (int counter = 0; counter < nodeList.size(); counter++) {
			Node curNode = nodeList.get(counter);
			System.out.print("<" + curNode.getRow() + "," + curNode.getCol() + "> ");
		}
	}
}
