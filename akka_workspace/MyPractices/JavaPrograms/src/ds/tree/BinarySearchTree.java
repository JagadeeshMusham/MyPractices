package ds.tree;

class Node {
	int data;
	Node left;
	Node right;
}

public class BinarySearchTree {
	Node root;

	public BinarySearchTree() {
		root = null;
	}

	private Node getPosition(int value) {
		Node posNode = root;

		while (true) {
			if (posNode.data > value) {
				if (posNode.left == null) {
					return posNode;
				}

				posNode = posNode.left;
			} else if (posNode.data < value) {
				if (posNode.right == null) {
					return posNode;
				}
				posNode = posNode.right;
			}
		}
	}

	public void insertNode(int value) {
		Node currentNode = new Node();
		currentNode.data = value;
		currentNode.left = null;
		currentNode.right = null;

		if (root == null) {
			root = currentNode;
			return;
		}

		Node posNode = getPosition(value);

		if (posNode.data > value) {
			posNode.left = currentNode;
		} else if (posNode.data < value) {
			posNode.right = currentNode;
		}
	}

	public void traverseInOrder(Node node) {
		if (node == null) {
			return;
		}

		traverseInOrder(node.left);
		System.out.print(node.data + " ");
		traverseInOrder(node.right);
	}

	private int getHeightOfTree(Node node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(getHeightOfTree(node.left), getHeightOfTree(node.right));
	}

	private void traverseLevelOrder(Node node) {
		int height = getHeightOfTree(node);

		for (int level = 0; level < height; level++) {
			printLevelOfTree(node, level);
		}
	}

	private void printLevelOfTree(Node node, int level) {
		if (node == null)
			return;

		if (level == 0) {
			System.out.print(node.data + " ");
		} else {
			printLevelOfTree(node.left, level - 1);
			printLevelOfTree(node.right, level - 1);
		}
	}

	private Node parentNodeOfDeletingNode(Node root, int data) {
		Node parentNode = root;

		while (parentNode != null) {
			if ((parentNode.left != null && parentNode.left.data == data)
					|| (parentNode.right != null && parentNode.right.data == data)) {
				break;
			}

			if (parentNode.data < data) {
				parentNode = parentNode.right;
			} else {
				parentNode = parentNode.left;
			}
		}

		return parentNode;
	}

	private Node getParentNodeOfRightMinNode(Node node) {
		Node currentNode = node;

		while (currentNode.left.left != null) {
			currentNode = currentNode.left;
		}

		return currentNode;
	}

	public void deleteNode(int data) {
		Node node = root;
		if (node == null) {
			return;
		}

		if (node.data == data) { // root deletion
			adjustTree(node);
			return;
		}

		Node parentNodeOfDeletingNode = parentNodeOfDeletingNode(node, data);
		if (parentNodeOfDeletingNode == null) {
			return;
		}

		Node deletingNode;
		if (parentNodeOfDeletingNode.data > data) {
			deletingNode = parentNodeOfDeletingNode.left;
		} else {
			deletingNode = parentNodeOfDeletingNode.right;
		}

		if (deletingNode.left == null) {
			if (parentNodeOfDeletingNode.left == deletingNode) {
				parentNodeOfDeletingNode.left = deletingNode.right;
			} else {
				parentNodeOfDeletingNode.left = deletingNode.left;
			}
		} else if (deletingNode.right == null) {
			if (parentNodeOfDeletingNode.left == deletingNode) {
				parentNodeOfDeletingNode.left = deletingNode.right;
			} else {
				parentNodeOfDeletingNode.right = deletingNode.left;
			}
		} else {
			adjustTree(deletingNode);
		}
	}

	private void adjustTree(Node deletingNode) {
		Node rightMinParentNode = getParentNodeOfRightMinNode(deletingNode.right);
		Node rightMinNode = rightMinParentNode.left;

		rightMinParentNode.left = rightMinNode.right;
		deletingNode.data = rightMinNode.data;
	}

	private static void persistTree() {

	}

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();

		bst.insertNode(10);
		bst.insertNode(15);
		bst.insertNode(5);
		bst.insertNode(12);
		bst.insertNode(14);
		bst.insertNode(6);
		bst.insertNode(20);
		bst.insertNode(11);
		bst.insertNode(13);
		bst.insertNode(18);
		bst.insertNode(16);
		bst.insertNode(17);

		bst.traverseInOrder(bst.root);

		System.out.println("\nThe level order of the tree is: ");
		bst.traverseLevelOrder(bst.root);

		bst.deleteNode(15);
		System.out.println();
		bst.traverseInOrder(bst.root);

		bst.deleteNode(20);
		System.out.println();
		bst.traverseInOrder(bst.root);

		bst.deleteNode(10);
		System.out.println();
		bst.traverseInOrder(bst.root);
		
		bst.deleteNode(10);
		System.out.println();
		bst.traverseInOrder(bst.root);

		bst.deleteNode(18);
		System.out.println();
		bst.traverseInOrder(bst.root);
	}
}
