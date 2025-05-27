package ds.graph;

import java.util.Iterator;
import java.util.LinkedList;

//This class represents a directed graph using adjacency list
//representation
class BreadthFirstTraversalGraph {
	private int nNodes; // No. of vertices
	private LinkedList<Integer> adjList[]; // Adjacency Lists

	// Constructor
	BreadthFirstTraversalGraph(int nNodes) {
		this.nNodes = nNodes;
		adjList = new LinkedList[nNodes];
		for (int counter = 0; counter < nNodes; ++counter)
			adjList[counter] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int node, int adjNode) {
		adjList[node].add(adjNode);
	}

	// prints BFS traversal from a given source startingNode
	void BFS(int startingNode) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[nNodes];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();

		// Mark the current node as visited and enqueue it
		visited[startingNode] = true;
		queue.add(startingNode);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			startingNode = queue.poll();
			System.out.print(startingNode + " ");

			// Get all adjacent vertices of the dequeued vertex startingNode
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> iterator = adjList[startingNode].listIterator();
			while (iterator.hasNext()) {
				int node = iterator.next();
				if (!visited[node]) {
					visited[node] = true;
					queue.add(node);
				}
			}
		}
	}

	// Driver method to
	public static void main(String args[]) {
		BreadthFirstTraversalGraph bftg = new BreadthFirstTraversalGraph(4);

		bftg.addEdge(0, 1);
		bftg.addEdge(0, 2);
		bftg.addEdge(1, 2);
		bftg.addEdge(2, 0);
		bftg.addEdge(2, 3);
		bftg.addEdge(3, 3);

		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

		bftg.BFS(2);
	}
}
