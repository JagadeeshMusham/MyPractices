package ds.graph;

import java.util.Iterator;
import java.util.LinkedList;

//This class represents a directed graph using adjacency list
//representation
public class DepthFirstTraversalGraph {
	private int nNodes; // No. of vertices

	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adjList[];

	// Constructor
	DepthFirstTraversalGraph(int nNodes) {
		this.nNodes = nNodes;
		adjList = new LinkedList[nNodes];
		for (int counter = 0; counter < nNodes; ++counter)
			adjList[counter] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int node, int adjNode) {
		adjList[node].add(adjNode);
	}
	// A function used by DFS
	void DFSUtil(int startingNode, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[startingNode] = true;
		System.out.print(startingNode + " ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> iterator = adjList[startingNode].listIterator();
		while (iterator.hasNext()) {
			int node = iterator.next();
			if (!visited[node])
				DFSUtil(node, visited);
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	void DFS(int startingNode) {
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[nNodes];

		// Call the recursive helper function to print DFS traversal
		DFSUtil(startingNode, visited);
	}

	public static void main(String args[]) {
		DepthFirstTraversalGraph dftg = new DepthFirstTraversalGraph(4);

		dftg.addEdge(0, 1);
		dftg.addEdge(0, 2);
		dftg.addEdge(1, 2);
		dftg.addEdge(2, 0);
		dftg.addEdge(2, 3);
		dftg.addEdge(3, 3);

		System.out.println("Following is Depth First Traversal " + "(starting from vertex 2)");

		dftg.DFS(2);
	}
}
