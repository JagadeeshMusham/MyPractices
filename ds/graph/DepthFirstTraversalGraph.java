package ds.graph;

import java.util.Iterator;
import java.util.LinkedList;

//This class represents a directed graph using adjacency list
//representation
public class DepthFirstTraversalGraph {
	private int V; // No. of vertices

	// Array of lists for Adjacency List Representation
	private LinkedList<Integer> adj[];

	// Constructor
	DepthFirstTraversalGraph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w); // Add w to v's list.
	}

	// A function used by DFS
	void DFSUtil(int v, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				DFSUtil(n, visited);
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	void DFS(int v) {
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[V];

		// Call the recursive helper function to print DFS traversal
		DFSUtil(v, visited);
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
