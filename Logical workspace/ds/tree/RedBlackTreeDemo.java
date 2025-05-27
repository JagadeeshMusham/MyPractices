package ds.tree;

// Node class for Red-Black Tree
class RedBlackNode {
    int value;
    RedBlackNode leftChild, rightChild, parent;
    boolean isRed; // True for red, false for black

    public RedBlackNode(int value) {
        this.value = value;
        this.isRed = true; // New nodes are initially red
        this.leftChild = this.rightChild = this.parent = null;
    }
}

// Red-Black Tree class
class RedBlackTree {
    private RedBlackNode root;
    private final RedBlackNode NIL; // Sentinel NIL node

    public RedBlackTree() {
        NIL = new RedBlackNode(-1);
        NIL.isRed = false; // NIL nodes are always black
        root = NIL;
    }

    // Perform left rotation
    private void rotateLeft(RedBlackNode node) {
        RedBlackNode rightChild = node.rightChild;
        node.rightChild = rightChild.leftChild;

        if (rightChild.leftChild != NIL) {
            rightChild.leftChild.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == NIL) {
            root = rightChild;
        } else if (node == node.parent.leftChild) {
            node.parent.leftChild = rightChild;
        } else {
            node.parent.rightChild = rightChild;
        }

        rightChild.leftChild = node;
        node.parent = rightChild;
    }

    // Perform right rotation
    private void rotateRight(RedBlackNode node) {
        RedBlackNode leftChild = node.leftChild;
        node.leftChild = leftChild.rightChild;

        if (leftChild.rightChild != NIL) {
            leftChild.rightChild.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == NIL) {
            root = leftChild;
        } else if (node == node.parent.rightChild) {
            node.parent.rightChild = leftChild;
        } else {
            node.parent.leftChild = leftChild;
        }

        leftChild.rightChild = node;
        node.parent = leftChild;
    }

    // Insert a new node
    public void insertNode(int value) {
        RedBlackNode newNode = new RedBlackNode(value);
        newNode.leftChild = newNode.rightChild = NIL;

        RedBlackNode current = root;
        RedBlackNode parentNode = NIL;

        while (current != NIL) {
            parentNode = current;
            if (value < current.value)
                current = current.leftChild;
            else
                current = current.rightChild;
        }

        newNode.parent = parentNode;
        if (parentNode == NIL) {
            root = newNode;
        } else if (value < parentNode.value) {
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }

        newNode.isRed = true;
        fixInsertion(newNode);
    }

    // Fix Red-Black Tree properties after insertion
    private void fixInsertion(RedBlackNode node) {
        while (node.parent.isRed) {
            RedBlackNode grandParent = node.parent.parent;

            if (node.parent == grandParent.leftChild) {
                RedBlackNode uncle = grandParent.rightChild;

                if (uncle.isRed) {
                    grandParent.isRed = true;
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node = grandParent;
                } else {
                    if (node == node.parent.rightChild) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.isRed = false;
                    grandParent.isRed = true;
                    rotateRight(grandParent);
                }
            } else {
                RedBlackNode uncle = grandParent.leftChild;

                if (uncle.isRed) {
                    grandParent.isRed = true;
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node = grandParent;
                } else {
                    if (node == node.parent.leftChild) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.isRed = false;
                    grandParent.isRed = true;
                    rotateLeft(grandParent);
                }
            }
        }
        root.isRed = false;
    }

    // Delete a node
    public void deleteNode(int value) {
        RedBlackNode nodeToDelete = searchNode(value);
        if (nodeToDelete == NIL) {
            System.out.println("Value not found in the tree.");
            return;
        }
        delete(nodeToDelete);
    }

    private void delete(RedBlackNode node) {
        RedBlackNode successor = node;
        boolean originalColor = successor.isRed;

        RedBlackNode replacementNode;

        if (node.leftChild == NIL) {
            replacementNode = node.rightChild;
            transplant(node, node.rightChild);
        } else if (node.rightChild == NIL) {
            replacementNode = node.leftChild;
            transplant(node, node.leftChild);
        } else {
            successor = findMinimum(node.rightChild);
            originalColor = successor.isRed;
            replacementNode = successor.rightChild;

            if (successor.parent == node) {
                replacementNode.parent = successor;
            } else {
                transplant(successor, successor.rightChild);
                successor.rightChild = node.rightChild;
                successor.rightChild.parent = successor;
            }

            transplant(node, successor);
            successor.leftChild = node.leftChild;
            successor.leftChild.parent = successor;
            successor.isRed = node.isRed;
        }

        if (!originalColor) {
            fixDeletion(replacementNode);
        }
    }

    // Fix Red-Black properties after deletion
    private void fixDeletion(RedBlackNode node) {
        while (node != root && !node.isRed) {
            if (node == node.parent.leftChild) {
                RedBlackNode sibling = node.parent.rightChild;
                if (sibling.isRed) {
                    sibling.isRed = false;
                    node.parent.isRed = true;
                    rotateLeft(node.parent);
                    sibling = node.parent.rightChild;
                }
                if (!sibling.leftChild.isRed && !sibling.rightChild.isRed) {
                    sibling.isRed = true;
                    node = node.parent;
                } else {
                    if (!sibling.rightChild.isRed) {
                        sibling.leftChild.isRed = false;
                        sibling.isRed = true;
                        rotateRight(sibling);
                        sibling = node.parent.rightChild;
                    }
                    sibling.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    sibling.rightChild.isRed = false;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                // Symmetric case
                fixDeletion(node.parent.leftChild);
            }
        }
        node.isRed = false;
    }

    // Find minimum node in a subtree
    private RedBlackNode findMinimum(RedBlackNode node) {
        while (node.leftChild != NIL)
            node = node.leftChild;
        return node;
    }

    // Transplant nodes
    private void transplant(RedBlackNode u, RedBlackNode v) {
        if (u.parent == NIL) {
            root = v;
        } else if (u == u.parent.leftChild) {
            u.parent.leftChild = v;
        } else {
            u.parent.rightChild = v;
        }
        v.parent = u.parent;
    }


    // Inorder traversal to display elements
    public void displayInorder() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(RedBlackNode node) {
        if (node != NIL) {
            inorderTraversal(node.leftChild);
            System.out.print(node.value + " ");
            inorderTraversal(node.rightChild);
        }
    }

    // Searching for a value in the tree
    public RedBlackNode searchNode(int value) {
        RedBlackNode current = root;
        while (current != NIL) {
            if (value == current.value) {
                return current; // Found
            }
            current = (value < current.value) ? current.leftChild : current.rightChild;
        }
        return null; // Not found
    }
}

// Driver Code (Main Program)
public class RedBlackTreeDemo {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insertNode(10);
        tree.insertNode(20);
        tree.insertNode(30);
        tree.insertNode(15);
        tree.insertNode(25);
        tree.insertNode(5);

        System.out.println("Inorder traversal of the Red-Black Tree:");
        tree.displayInorder();

        tree.deleteNode(20);
        System.out.println("Inorder traversal after deletion:");
        tree.displayInorder();

        int searchValue = 20;
        System.out.println("Searching for " + searchValue + ": " + (tree.searchNode(searchValue) != null ? "Found" : "Not Found"));
    }
}
