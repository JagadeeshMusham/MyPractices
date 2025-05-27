package ds.tree;

class AVLTreeNode {
    int value, height;
    AVLTreeNode leftChild, rightChild;

    AVLTreeNode(int data) {
        value = data;
        height = 1;
    }
}

class AVLTree {
    AVLTreeNode root;

    // Get height of a node
    int getHeight(AVLTreeNode node) {
        return (node == null) ? 0 : node.height;
    }

    void insertNode(int value) {
        root = insert(root, value);
    }

    // Insert a node into the AVL tree
    AVLTreeNode insert(AVLTreeNode node, int value) {
        if (node == null)
            return new AVLTreeNode(value);

        if (value < node.value)
            node.leftChild = insert(node.leftChild, value);
        else if (value > node.value)
            node.rightChild = insert(node.rightChild, value);
        else
            return node; // Duplicate values not allowed

        node.height = Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;

        int balanceFactor = getBalanceFactor(node);

        // Left-heavy (LL Rotation)
        if (balanceFactor > 1 && value < node.leftChild.value)
            return rotateRight(node);

        // Right-heavy (RR Rotation)
        if (balanceFactor < -1 && value > node.rightChild.value)
            return rotateLeft(node);

        // Left-Right case (LR Rotation)
        if (balanceFactor > 1 && value > node.leftChild.value) {
            node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        }

        // Right-Left case (RL Rotation)
        if (balanceFactor < -1 && value < node.rightChild.value) {
            node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }

        return node;
    }

    // Get balance factor
    int getBalanceFactor(AVLTreeNode node) {
        return (node == null) ? 0 : getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    // Perform right rotation
    AVLTreeNode rotateRight(AVLTreeNode root) {
        AVLTreeNode newRoot = root.leftChild;
        AVLTreeNode newRootActualRight = newRoot.rightChild;

        newRoot.rightChild = root;
        root.leftChild = newRootActualRight;

        root.height = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.leftChild), getHeight(newRoot.rightChild)) + 1;

        return newRoot;
    }

    // Perform left rotation
    AVLTreeNode rotateLeft(AVLTreeNode root) {
        AVLTreeNode newRoot = root.rightChild;
        AVLTreeNode newRootActualLeft = newRoot.leftChild;

        newRoot.leftChild = root;
        root.rightChild = newRootActualLeft;

        root.height = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.leftChild), getHeight(newRoot.rightChild)) + 1;

        return newRoot;
    }

    // Inorder traversal of the AVL tree
    void inorderTraversal(AVLTreeNode root) {
        if (root != null) {
            inorderTraversal(root.leftChild);
            System.out.print(root.value + " ");
            inorderTraversal(root.rightChild);
        }
    }

    AVLTreeNode delete(AVLTreeNode root, int value) {
        if (root == null) return root;

        if (value < root.value)
            root.leftChild = delete(root.leftChild, value);
        else if (value > root.value)
            root.rightChild = delete(root.rightChild, value);
        else {
            //AVLTreeNode deleteNode = root;
            if ((root.leftChild == null) || (root.rightChild == null)) {
                AVLTreeNode childNode = (root.leftChild != null) ? root.leftChild : root.rightChild;
                if (childNode == null) {    // It's a Leaf Node
                    childNode = root; //Does this stmt require ???
                    root = null;
                } else
                    root = childNode;
            } else {
                AVLTreeNode rightMinNode = rightMinValueNode(root.rightChild);
                root.value = rightMinNode.value;
                root.rightChild = delete(root.rightChild, rightMinNode.value);
            }
        }

        if (root == null) return root;

        root.height = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;

        int balanceFactor = getBalanceFactor(root);

        if (balanceFactor > 1 && getBalanceFactor(root.leftChild) >= 0)
            return rotateRight(root);

        if (balanceFactor > 1 && getBalanceFactor(root.leftChild) < 0) {
            root.leftChild = rotateLeft(root.leftChild);
            return rotateRight(root);
        }

        if (balanceFactor < -1 && getBalanceFactor(root.rightChild) <= 0)
            return rotateLeft(root);

        if (balanceFactor < -1 && getBalanceFactor(root.rightChild) > 0) {
            root.rightChild = rotateRight(root.rightChild);
            return rotateLeft(root);
        }

        return root;
    }

    AVLTreeNode rightMinValueNode(AVLTreeNode root) {
        AVLTreeNode currentNode = root;

        while (currentNode.leftChild != null)
            currentNode = currentNode.leftChild;

        return currentNode;
    }

    void displayInorder() {
        inorderTraversal(root);
        System.out.println();
    }
}

// Driver code
public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insertNode(10);
        tree.insertNode(20);
        tree.insertNode(30);
        tree.insertNode(40);
        tree.insertNode(50);
        tree.insertNode(25);

        System.out.println("Inorder traversal of AVL tree:");
        tree.displayInorder();

        tree.delete(tree.root,20);
    }
}
