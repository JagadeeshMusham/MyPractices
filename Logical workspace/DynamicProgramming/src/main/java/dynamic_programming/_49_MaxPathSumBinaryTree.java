package dynamic_programming;

/**
 * To find the maximum path sum in a binary tree, you can use a depth-first search (DFS) approach. The idea is to calculate the maximum path sum for each node, considering paths that go through the node and potentially both its left and right children.
 * Key Concepts:
 *
 *     Path Sum: The sum of node values along a path in the tree. The path may start and end at any node in the tree, but it must go downwards (from parent nodes to child nodes).
 *     Global Maximum: During the traversal, we maintain a global variable that tracks the maximum path sum found so far.
 *
 * Steps:
 *
 *     For each node, calculate the maximum sum path that passes through the node and returns the maximum single path (either through the left child or right child or just the node itself).
 *     Update the global maximum path sum if the sum including both left and right children is higher.
 *     Recursively calculate this for every node in the tree.
 */

class BinaryTree1 {
    private int maxPathSum = Integer.MIN_VALUE;

    // Helper function to calculate max path sum from a given node
    private int calculateMaxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively calculate the maximum path sum of the left and right subtrees
        int leftMaxSum = Math.max(0, calculateMaxPathSum(node.left));  // Ignore paths with negative sums
        int rightMaxSum = Math.max(0, calculateMaxPathSum(node.right));

        // Calculate the path sum passing through the current node
        int currentPathSum = node.value + leftMaxSum + rightMaxSum;

        // Update the global max path sum if the current path sum is greater
        maxPathSum = Math.max(maxPathSum, currentPathSum);

        // Return the maximum sum path that includes the current node and one of its subtrees
        return node.value + Math.max(leftMaxSum, rightMaxSum);
    }

    public int getMaxPathSum(TreeNode root) {
        calculateMaxPathSum(root);
        return maxPathSum;
    }
}

public class _49_MaxPathSumBinaryTree
{
    public static void main(String[] args) {
        BinaryTree1 tree = new BinaryTree1();

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

        System.out.println("The maximum path sum in the binary tree is: " + tree.getMaxPathSum(root));
    }
}
