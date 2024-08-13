package hacker_rank.shortest_path_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShortestPath {
	private static Map<Integer, List<Integer>> verticesMap;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of vertices: ");
		int nVertices = scanner.nextInt();

		System.out.println("Enter number edges: ");
		int nEdges = scanner.nextInt();

		verticesMap = new HashMap<>();

		int counter;
		for (counter = 1; counter <= nVertices; counter++) {
			verticesMap.put(counter, null);
		}

		for (counter = 0; counter < nEdges; counter++) {
			System.out.println("Enter the edge " + counter);
			int source = scanner.nextInt();
			int destination = scanner.nextInt();

			List<Integer> adjacentVertices = null;

			if (verticesMap.containsKey(source)) {
				adjacentVertices = verticesMap.get(source);
			}

			if (adjacentVertices == null) {
				adjacentVertices = new ArrayList<>();
				verticesMap.put(source, adjacentVertices);
			}

			adjacentVertices.add(destination);
		}

		System.out.println("Enter the starting position of the graph");
		int startNode = scanner.nextInt();
		findShortestDistanceForAllNode(nVertices, startNode);
	}

	private static void findShortestDistanceForAllNode(int nVertices, int startNode) {
		for (int counter = 1; counter <= nVertices; counter++) {
			if (startNode == counter) {
				continue;
			}

			List<Integer> visitedNodes = null;
			int shortestPath = Integer.MAX_VALUE;

			shortestPath = findShortestDistance(startNode, counter, visitedNodes, shortestPath);
			System.out.println(counter + " " + shortestPath);
		}

	}

	private static int findShortestDistance(int startNode, int endNode, List<Integer> visitedNodes, int shortestPath) {
		if (startNode == endNode) {
			if (visitedNodes != null && visitedNodes.size() - 1 < shortestPath) {
				shortestPath = visitedNodes.size() - 1;
			}

			return shortestPath;
		}

		List<Integer> adjacentNodes = verticesMap.get(startNode);
		if (adjacentNodes == null) {
			return -1;
		}

		for (int adjacentNode : adjacentNodes) {
			if (visitedNodes == null) {
				visitedNodes = new ArrayList<>();
				visitedNodes.add(startNode);
			}

			if (visitedNode(visitedNodes, adjacentNode)) {
				continue;
			}

			visitedNodes.add(adjacentNode);

			int tempPath = findShortestDistance(adjacentNode, endNode, visitedNodes, shortestPath);
			if (tempPath > 0 && tempPath < shortestPath) {
				shortestPath = tempPath;
			}

			visitedNodes.remove(visitedNodes.size() - 1);
		}

		if (shortestPath == Integer.MAX_VALUE) {
			return -1;
		}

		return shortestPath;
	}

	private static boolean visitedNode(List<Integer> visitedNodes, int node) {
		for (int visitedNode : visitedNodes) {
			if (node == visitedNode) {
				return true;
			}
		}

		return false;
	}
}
