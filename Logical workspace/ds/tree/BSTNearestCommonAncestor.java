package ds.tree;

public class BSTNearestCommonAncestor {

    public static void main(String[] args) {
        // Creating a binary search tree
        Node root = new Node(50);
        root.left = new Node(35);
        root.right = new Node(60);
        root.left.left = new Node(30);
        root.left.right = new Node(36);
        root.left.right.right = new Node(37);

        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(88);

        int input1 = 30;
        int input2 = 37;
        getNCA(root, input1, input2);

        input1 = 65;
        input2 = 88;
        getNCA(root, input1, input2);
    }

    private static void getNCA(Node root, int input1, int input2) {
        Node node = findNCA(root, input1, input2);

        if (node != null) {
            System.out.println("Lowest Common Ancestor of " + input1 + " and " +
                    input2 + " is " + node.data);
        } else {
            System.out.println("Lowest Common Ancestor not found.");
        }
    }

    public static Node findNCA(Node node, int input1, int input2) {
        // Base case: If the node is null, return null
        if (node == null) {
            return null;
        }

        // If both nodes are smaller than node, move to left
        if (input1 < node.data && input2 < node.data) {
            return findNCA(node.left, input1, input2);
        }

        // If both nodes are greater than node, move to right
        if (input1 > node.data && input2 > node.data) {
            return findNCA(node.right, input1, input2);
        }

        // If the node is between the two nodes, then return the node
        return node;
    }

}

