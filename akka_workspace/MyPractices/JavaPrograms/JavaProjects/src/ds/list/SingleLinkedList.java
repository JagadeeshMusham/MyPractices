package ds.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sun.security.x509.DeltaCRLIndicatorExtension;

public class SingleLinkedList {

	private Node head;
	private Node tail;

	List<Node> nodeList;
	int totalNodeCount;
	
	public Node getHead() {
		return head;
	}

	SingleLinkedList() {
		nodeList = new ArrayList<>();

		head = null;
		tail = null;

		totalNodeCount = 0;
	}

	SingleLinkedList(int data) {
		nodeList = new ArrayList<>();

		Node node = new Node();
		node.setData(data);
		node.setPrev(null);
		node.setNext(null);

		head = node;
		tail = node;

		totalNodeCount = 1;

		// nodeList.add(node);
	}

	public void addNode(int data, int position) {
		if (position > totalNodeCount + 1) {
			System.out.println("Error! wrong position: " + position);
			return;
		}

		Node curNode = new Node();
		curNode.setData(data);

		if (position == 1) {
			curNode.setNext(head);
			head = curNode;
			++totalNodeCount;
		} else if (position == totalNodeCount + 1) {
			curNode.setPrev(tail);
			tail = curNode;
			curNode.getPrev().setNext(curNode);
			++totalNodeCount;
		} else {
			Node prevNode = getPreviousNode(position);
			if (prevNode != null) {
				curNode.setNext(prevNode.getNext());
				prevNode.setNext(curNode);
				++totalNodeCount;
			}
		}

	}

	public void addNode(int data) {
		int position = 0;
		Node node = tail;
		if (node != null) {
			++position;
			while (node.getNext() != null) {
				node = node.getNext();
				++position;
			}
		}

		if (position > totalNodeCount + 1) {
			System.out.println("Error! wrong position: " + position);
			return;
		}

		Node curNode = new Node();
		curNode.setData(data);

		if (position == 1) {
			curNode.setNext(head);
			head = curNode;
			++totalNodeCount;
		} else if (position == totalNodeCount + 1) {
			curNode.setPrev(tail);
			tail = curNode;
			curNode.getPrev().setNext(curNode);
			++totalNodeCount;
		} else {
			Node prevNode = getPreviousNode(position);
			if (prevNode != null) {
				curNode.setNext(prevNode.getNext());
				prevNode.setNext(curNode);
				++totalNodeCount;
			}
		}
	}

	private Node getPreviousNode(int position) {
		Node curNode = head;
		for (int counter = 1; counter < position - 1; counter++) {
			if (curNode.getNext() == null) {
				System.out.println("Error! wrong position: " + position);
				return null;
			}

			curNode = curNode.getNext();
		}
		return curNode;
	}

	public void displayNodes() {
		Node curNode = head;
		System.out.println("Displaying nodes: ");
		for (; curNode != null; curNode = curNode.getNext()) {
			System.out.print(curNode.getData() + " ");
		}
	}

	//Todo J, 
	public void deleteNode(Node node) {
		Node curentNode = head;
//		while()
	}

	
	Node Reverse(Node cur) {
	    if (cur.getNext() == null) {
	    	this.head = cur;
	        return cur;
	    }
	    
	    Node node = Reverse(cur.getNext());
	    cur.setNext(null);
	    node.setNext(cur);
	    this.tail = cur;
	    return cur;
	}

	boolean isVisited(Node node) {
	    Node addNode = new Node();
	    if (visitedNodeHead == null)
	    {
		    addNode.setData(node.getData());
		    addNode.setNext(null);
	        visitedNodeHead = addNode;
	        return false;
	    }

	    Node cur = visitedNodeHead;
	    Node prev = null;
	    while (cur != null) {
	        if (cur.getData() == node.getData()) {
	            return true;
	        }
	        
	        prev = cur;
	        cur = cur.getNext();
	    }
	    
	    addNode.setData(node.getData());
	    addNode.setNext(null);
	    prev.setNext(addNode);

	    return false;
	}

	Node visitedNodeHead = null;

	boolean hasCycle(Node head) {
	    if (head == null)
	        return false;
	    
	    visitedNodeHead = null;
	    
	    while (head != null) {
	        if (isVisited(head))
	        {
	            return true;
	        }
	        
	        head = head.getNext();
	    }
	    
	    return false;
	}}
