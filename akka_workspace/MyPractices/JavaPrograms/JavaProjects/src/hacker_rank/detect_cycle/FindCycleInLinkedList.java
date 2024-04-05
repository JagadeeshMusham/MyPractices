package hacker_rank.detect_cycle;

import java.util.ArrayList;
import java.util.List;

public class FindCycleInLinkedList {
	private static List<Node> visitedNodeList;

	public static void main(String[] args) {
		visitedNodeList = new ArrayList<>();

		Node node1 = new Node(1);
		Node head = node1;

		Node node2 = new Node(2);
		node1.setNext(node2);

		Node node3 = new Node(3);
		node2.setNext(node2);
		node3.setNext(node2);

		hasCycle(head);
	}

	private static boolean isNodeVisited(Node node) {
		if (visitedNodeList.size() == 0) {
			return false;
		}

		for (int counter = 0; counter < visitedNodeList.size(); counter++) {
			Node currentNode = visitedNodeList.get(counter);
			if (node.getData() == currentNode.getData() && node.getNext() == currentNode.getNext()) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasCycle(Node head) {
		boolean bFoundCycle = false;
		for (Node node = head; node != null; node = node.getNext()) {
			if (isNodeVisited(node)) {
				bFoundCycle = true;
				break;
			} else {
				visitedNodeList.add(node);
			}
		}
		return bFoundCycle;
	}

}
