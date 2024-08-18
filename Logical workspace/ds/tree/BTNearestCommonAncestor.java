package ds.tree;

public class BTNearestCommonAncestor {
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
        Node ncaNode = null;
        findNCA(root, input1, input2, ncaNode);

        if (ncaNode != null) {
            System.out.println("Lowest Common Ancestor of " + input1 + " and " +
                    input2 + " is " + ncaNode.data);
        } else {
            System.out.println("Lowest Common Ancestor not found.");
        }
    }

    public static Node findNCA(Node node, int input1, int input2, Node ncaNode) {
        // Base case: If the node is null, return null
        if (node == null) {
            return null;
        }

        Node leftNode = findNCA(node.left, input1, input2, ncaNode);
        Node rightNode = findNCA(node.right, input1, input2, ncaNode);

        // If both nodes found then
        if (leftNode != null && rightNode != null) {
            ncaNode = node;
        }

        // If either of the data found return that node
        if (input1 == node.data || input2 == node.data) {
            return node;
        }

        //if nothing found then return null
        return null;
    }

}
