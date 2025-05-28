package ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing a B+ Tree node.
 */
class BPlusTreeNode {
    List<Integer> keys; // Stores keys in the node
    List<BPlusTreeNode> children; // Stores references to child nodes
    boolean isLeaf; // Indicates whether the node is a leaf
    BPlusTreeNode next; // Pointer to the next leaf node (for range queries)

    public BPlusTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.next = null;
    }
}

/**
 * Class representing a B+ Tree with insert, delete, search, and findNode methods.
 */
class BPlusTree {
    private int degree; // Maximum number of children per node
    private BPlusTreeNode root; // Root of the B+ Tree

    public BPlusTree(int degree) {
        this.degree = degree;
        this.root = new BPlusTreeNode(true); // Initialize with an empty leaf node
    }

    /**
     * Inserts a key into the B+ Tree.
     *
     * @param key The key to be inserted
     */
    public void insert(int key) {
        BPlusTreeNode node = root;
        if (node.keys.size() >= degree - 1) {
            BPlusTreeNode newRoot = new BPlusTreeNode(false);
            newRoot.children.add(node);
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, key);
    }

    private void insertNonFull(BPlusTreeNode node, int key) {
        if (node.isLeaf) {
            node.keys.add(key);
            Collections.sort(node.keys);
        } else {
            int index = 0;
            while (index < node.keys.size() && key > node.keys.get(index)) {
                index++;
            }
            insertNonFull(node.children.get(index), key);

            if (node.children.get(index).keys.size() >= degree) {
                splitChild(node, index);
            }
        }
    }

    private void splitChild(BPlusTreeNode parent, int index) {
        BPlusTreeNode child = parent.children.get(index);
        BPlusTreeNode newChild = new BPlusTreeNode(child.isLeaf);
        int midIndex = degree / 2;

        newChild.keys.addAll(child.keys.subList(midIndex, child.keys.size()));
        child.keys.subList(midIndex, child.keys.size()).clear();

        parent.keys.add(midIndex, child.keys.get(midIndex - 1));
        parent.children.add(index + 1, newChild);

        if (child.isLeaf) {
            newChild.next = child.next;
            child.next = newChild;
        }
    }

    /**
     * Deletes a key from the B+ Tree.
     *
     * @param key The key to be deleted
     */
    public void delete(int key) {
        BPlusTreeNode node = findNode(key);
        if (node != null) {
            node.keys.remove(Integer.valueOf(key));
            System.out.println("Key " + key + " deleted.");
        } else {
            System.out.println("Key " + key + " not found in the B+ Tree.");
        }
    }

    /**
     * Searches for a key in the B+ Tree.
     *
     * @param key The key to search for
     * @return True if the key is found, otherwise false
     */
    public boolean search(int key) {
        BPlusTreeNode node = root;
        while (!node.isLeaf) {
            int index = 0;
            while (index < node.keys.size() && key > node.keys.get(index)) {
                index++;
            }
            node = node.children.get(index);
        }
        return node.keys.contains(key);
    }

    /**
     * Finds the leaf node containing the given key.
     *
     * @param key The key to search for
     * @return The leaf node containing the key, or null if not found
     */
    public BPlusTreeNode findNode(int key) {
        BPlusTreeNode node = root;
        while (!node.isLeaf) {
            int index = 0;
            while (index < node.keys.size() && key > node.keys.get(index)) {
                index++;
            }
            node = node.children.get(index);
        }
        return node.keys.contains(key) ? node : null;
    }

    /**
     * Prints the B+ Tree keys at each level.
     */
    public void printTree() {
        LinkedList<BPlusTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BPlusTreeNode node = queue.poll();
            System.out.println(node.keys);
            if (!node.isLeaf) {
                queue.addAll(node.children);
            }
        }
    }
}

/**
 * Main program to demonstrate B+ Tree operations.
 */
public class BPlusTreeDemo {
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree(4);

        // Insert keys into the B+ Tree
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);
        tree.insert(17);

        // Print the B+ Tree structure
        System.out.println("B+ Tree structure:");
        tree.printTree();

        // Search for a key in the B+ Tree
        int searchKey = 12;
        System.out.println("Search for key " + searchKey + ": " + tree.search(searchKey));

        // Find the node containing a key
        BPlusTreeNode node = tree.findNode(searchKey);
        if (node != null) {
            System.out.println("Key " + searchKey + " found in node: " + node.keys);
        } else {
            System.out.println("Key " + searchKey + " not found.");
        }

        // Delete a key
        int deleteKey = 12;
        tree.delete(deleteKey);

        System.out.println("B+ Tree structure after deletion:");
        tree.printTree();
    }
}
