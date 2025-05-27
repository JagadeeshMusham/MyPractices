package ds.tree;

// Class representing a B-Tree node
class BTreeNode {
    int[] keys; // Array to store keys in the node
    int numKeys; // Current number of keys in the node
    BTreeNode[] children; // Array of child pointers
    boolean isLeaf; // True if the node is a leaf

    public BTreeNode(int order, boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new int[2 * order - 1]; // Max keys in a node
        this.children = new BTreeNode[2 * order]; // Max children
        this.numKeys = 0;
    }
}

// Class representing a B-Tree
class BTree {
    private BTreeNode root; // Root of the B-Tree
    private int order; // Minimum degree (order) of the B-Tree

    public BTree(int order) {
        this.order = order;
        this.root = new BTreeNode(order, true); // Initial root as a leaf node
    }

    // Search for a key in the B-Tree
    public BTreeNode search(int key) {
        return searchInNode(root, key);
    }

    private BTreeNode searchInNode(BTreeNode node, int key) {
        int i = 0;

        while (i < node.numKeys && key > node.keys[i]) {
            i++;
        }

        if (i < node.numKeys && key == node.keys[i]) {
            return node;
        }

        if (node.isLeaf) {
            return null;
        }

        return searchInNode(node.children[i], key);
    }

    // Insert a key into the B-Tree
    public void insert(int key) {
        BTreeNode rootNode = root;

        if (rootNode.numKeys == (2 * order - 1)) {
            BTreeNode newRoot = new BTreeNode(order, false);
            newRoot.children[0] = rootNode;
            splitChild(newRoot, 0, rootNode);
            root = newRoot;
        }

        insertNonFull(root, key);
    }

    private void insertNonFull(BTreeNode node, int key) {
        int i = node.numKeys - 1;

        if (node.isLeaf) {
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.numKeys++;
        } else {
            while (i >= 0 && key < node.keys[i]) {
                i--;
            }
            i++;

            if (node.children[i].numKeys == (2 * order - 1)) {
                splitChild(node, i, node.children[i]);
                if (key > node.keys[i]) {
                    i++;
                }
            }
            insertNonFull(node.children[i], key);
        }
    }

    // Split a full child node
    private void splitChild(BTreeNode parent, int index, BTreeNode child) {
        BTreeNode newChild = new BTreeNode(order, child.isLeaf);
        parent.numKeys++;

        for (int j = 0; j < order - 1; j++) {
            newChild.keys[j] = child.keys[j + order];
        }

        if (!child.isLeaf) {
            for (int j = 0; j < order; j++) {
                newChild.children[j] = child.children[j + order];
            }
        }

        child.numKeys = order - 1;

        for (int j = parent.numKeys; j > index + 1; j--) {
            parent.children[j] = parent.children[j - 1];
        }
        parent.children[index + 1] = newChild;

        for (int j = parent.numKeys - 1; j > index; j--) {
            parent.keys[j] = parent.keys[j - 1];
        }

        parent.keys[index] = child.keys[order - 1];
    }

    // Inorder traversal of the B-Tree
    public void inorderTraversal() {
        inorder(root);
        System.out.println();
    }

    private void inorder(BTreeNode node) {
        if (node != null) {
            for (int i = 0; i < node.numKeys; i++) {
                if (!node.isLeaf) {
                    inorder(node.children[i]);
                }
                System.out.print(node.keys[i] + " ");
            }
            if (!node.isLeaf) {
                inorder(node.children[node.numKeys]);
            }
        }
    }

    // Delete operation
    public void delete(int key) {
        deleteFromNode(root, key);

        // If the root becomes empty, adjust it
        if (root.numKeys == 0) {
            if (!root.isLeaf) {
                root = root.children[0]; // Set child as new root
            } else {
                root = new BTreeNode(order, true);
            }
        }
    }

    private void deleteFromNode(BTreeNode node, int key) {
        int i = 0;

        while (i < node.numKeys && key > node.keys[i]) {
            i++;
        }

        if (i < node.numKeys && node.keys[i] == key) {
            if (node.isLeaf) {
                removeFromLeaf(node, i);
            } else {
                removeFromInternal(node, i);
            }
        } else {
            if (node.isLeaf) {
                return;
            }

            boolean lastChild = (i == node.numKeys);
            if (node.children[i].numKeys < order) {
                fillNode(node, i);
            }

            if (lastChild && i > node.numKeys) {
                deleteFromNode(node.children[i - 1], key);
            } else {
                deleteFromNode(node.children[i], key);
            }
        }
    }

    private void removeFromLeaf(BTreeNode node, int index) {
        for (int i = index; i < node.numKeys - 1; i++) {
            node.keys[i] = node.keys[i + 1];
        }
        node.numKeys--;
    }

    private void removeFromInternal(BTreeNode node, int index) {
        int key = node.keys[index];

        if (node.children[index].numKeys >= order) {
            int predecessor = getPredecessor(node.children[index]);
            node.keys[index] = predecessor;
            deleteFromNode(node.children[index], predecessor);
        } else if (node.children[index + 1].numKeys >= order) {
            int successor = getSuccessor(node.children[index + 1]);
            node.keys[index] = successor;
            deleteFromNode(node.children[index + 1], successor);
        } else {
            mergeNodes(node, index);
            deleteFromNode(node.children[index], key);
        }
    }

    private int getPredecessor(BTreeNode node) {
        while (!node.isLeaf) {
            node = node.children[node.numKeys];
        }
        return node.keys[node.numKeys - 1];
    }

    private int getSuccessor(BTreeNode node) {
        while (!node.isLeaf) {
            node = node.children[0];
        }
        return node.keys[0];
    }

    private void fillNode(BTreeNode node, int index) {
        if (index > 0 && node.children[index - 1].numKeys >= order) {
            shiftFromLeft(node, index);
        } else if (index < node.numKeys && node.children[index + 1].numKeys >= order) {
            shiftFromRight(node, index);
        } else {
            mergeNodes(node, index);
        }
    }

    private void shiftFromLeft(BTreeNode node, int index) { /* Left shift logic */ }
    private void shiftFromRight(BTreeNode node, int index) { /* Right shift logic */ }
    private void mergeNodes(BTreeNode node, int index) { /* Merging logic */ }
}

// Driver Code
public class BTreeDemo {
    public static void main(String[] args) {
        BTree tree = new BTree(3);

        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);
        tree.insert(17);

        System.out.println("Inorder traversal before deletion:");
        tree.inorderTraversal();

        tree.delete(6);
        System.out.println("Inorder traversal after deletion:");
        tree.inorderTraversal();
    }
}
