package hacker_rank.binary_search_tree;

import java.util.Scanner;

/**
 * The provided Java program is about checking whether a given binary tree is a
 * Binary Search Tree (BST). Here's a breakdown of the key parts:
 */
public class BinarySearchTree {

	private static boolean bBST;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		bBST = true;

		Node root = new Node();
		root.setData(4);

		Node l1 = new Node();
		l1.setData(2);
		root.setLeft(l1);

		Node r1 = new Node();
		r1.setData(3);
		l1.setRight(r1);

		Node l2 = new Node();
		l2.setData(1);
		l1.setLeft(l2);

		Node r2 = new Node();
		r2.setData(6);
		root.setRight(r2);

		Node l3 = new Node();
		l3.setData(5);
		r2.setLeft(l3);

		Node r3 = new Node();
		r3.setData(7);
		r2.setRight(r3);

		System.out.println("The given tree is a binary search tree: " +
				BSTValidation(root));
	}

	private static boolean BSTValidation(Node root) {
		if (root != null) {
			if (!bBST) {
				return bBST;
			}

			if (root.getLeft() != null) {
				if (root.getLeft().getData() < root.getData()) {
					bBST = BSTValidation(root.getLeft());
					
					if (!bBST) {
						return bBST;
					}
				} else {
					bBST = false;
					return bBST;
				}
			}

			if (root.getRight() != null) {
				if (root.getRight().getData() > root.getData()) {
					bBST = BSTValidation(root.getRight());
					
					if (bBST) {
						return bBST;
					}
				} else {
					bBST = false;
					return bBST;
				}
			}
		}

		return bBST;
	}
}
