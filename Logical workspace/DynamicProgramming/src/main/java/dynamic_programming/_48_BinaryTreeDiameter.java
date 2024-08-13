package dynamic_programming;

class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

class BinaryTree {
    // Class-level variable to store the maximum diameter
    private int diameter;

    public BinaryTree() {
        this.diameter = 0; // Initialize diameter to 0
    }

    // Helper function to calculate height and update diameter
    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively calculate the height of the left and right subtrees
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Update the diameter if the path through this node is larger
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return height of the subtree rooted at this node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Function to get the maximum diameter of the binary tree
    public int getMaxDiameter(TreeNode root) {
        calculateHeight(root); // This updates the diameter variable
        return diameter;
    }
}

public class _48_BinaryTreeDiameter {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.right = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.left.right.right.right = new TreeNode(9);
        root.left.right.right.right.right = new TreeNode(10);
        root.left.right.right.left = new TreeNode(11);
        root.left.left.right = new TreeNode(13);
        root.left.left.right.left = new TreeNode(14);
        root.left.left.right.left.left = new TreeNode(15);
        root.left.left.right.left.right = new TreeNode(16);


        System.out.println("The maximum diameter of the binary tree is: " + tree.getMaxDiameter(root));
    }
}

