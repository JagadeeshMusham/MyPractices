package hacker_rank.biggest_region;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BiggestRegion {
	public static int getBiggestRegion(int[][] matrix, int row, int column) {
		List<Node> selectedNode = new ArrayList<>();

		int selectedRegion = 0;
		for (int currentRow = 0; currentRow < row; currentRow++) {
			for (int currentCol = 0; currentCol < column; currentCol++) {
				List<Node> visitedNode = new ArrayList<>();
				Node currentNode = new Node(currentRow, currentCol);

				if (!nodeVisisted(selectedNode, currentNode)) {
					if (matrix[currentRow][currentCol] == 0) {
						continue;
					}

					Node startNode = new Node(currentRow, currentCol);
					visitedNode.add(startNode);

					findRegion(matrix, row, column, currentRow, currentCol, visitedNode);

					if (visitedNode.size() > selectedRegion) {
						selectedRegion = visitedNode.size();
						selectedNode.addAll(visitedNode);
					}
				}
			}
		}
		return selectedRegion;
	}

	private static boolean nodeVisisted(List<Node> visitedNodes, Node node) {
		for (Node visitedNode : visitedNodes) {
			if (visitedNode.col == node.col && visitedNode.row == node.row) {
				return true;
			}
		}

		return false;
	}

	private static void findRegion(int[][] matrix, int totalRow, int totalColumn, int startRow, int startCol,
			List<Node> visitedNode) {
		if (matrix[startRow][startCol] == 0) {
			return;
		}

		for (int counter = 0; counter < 8; counter++) {
			int currentRow = startRow;
			int currentColumn = startCol;
			switch (counter) {
			case 0:
				if (currentRow == 0 || currentColumn == 0) {
					continue;
				}
				--currentRow;
				--currentColumn;
				break;
			case 1:
				if (currentRow == 0) {
					continue;
				}

				--currentRow;
				break;
			case 2:
				if (currentRow == 0 || currentColumn >= totalColumn - 1) {
					continue;
				}

				--currentRow;
				++currentColumn;
				break;
			case 3:
				if (currentColumn == 0) {
					continue;
				}

				--currentColumn;
				break;
			case 4:
				if (currentColumn >= totalColumn - 1) {
					continue;
				}

				++currentColumn;
				break;
			case 5:
				if (currentRow >= totalRow - 1 || currentColumn == 0) {
					continue;
				}

				++currentRow;
				--currentColumn;
				break;
			case 6:
				if (currentRow >= totalRow - 1) {
					continue;
				}

				++currentRow;
				break;
			case 7:
				if (currentRow >= totalRow - 1 || currentColumn >= totalColumn - 1) {
					continue;
				}

				++currentRow;
				++currentColumn;
				break;
			}
			if (matrix[currentRow][currentColumn] == 1) {
				Node node = new Node(currentRow, currentColumn);
				if (!nodeVisisted(visitedNode, node)) {
					visitedNode.add(node);
					findRegion(matrix, totalRow, totalColumn, currentRow, currentColumn, visitedNode);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int row = in.nextInt();
		int column = in.nextInt();
		int grid[][] = new int[row][column];
		for (int grid_i = 0; grid_i < row; grid_i++) {
			for (int grid_j = 0; grid_j < column; grid_j++) {
				grid[grid_i][grid_j] = in.nextInt();
			}
		}
		System.out.println(getBiggestRegion(grid, row, column));
	}

}
