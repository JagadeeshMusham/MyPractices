package dynamic_programming;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }
}

public class _50_BinaryTreeMaxLeafToLeafPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-15);
        root.left = new TreeNode(5);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(-8);
        root.left.right = new TreeNode(1);
        root.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(6);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(9);
        root.right.right.right = new TreeNode(0);
        root.right.right.right.left = new TreeNode(4);
        root.right.right.right.right = new TreeNode(-1);
        root.right.right.right.right.left = new TreeNode(10);

        int maxSum = findMaxLeafToLeafPathSum(root);
        System.out.println("Maximum Leaf-to-Leaf Path Sum: " + maxSum);
    }

    public static int findMaxLeafToLeafPathSum(TreeNode root) {
        int[] maxSum = new int[]{Integer.MIN_VALUE};
        calculateLeafToLeafPathSum(root, maxSum);
        return maxSum[0];
    }

    private static int calculateLeafToLeafPathSum(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }

        int leftPathSum = calculateLeafToLeafPathSum(node.left, maxSum);
        int rightPathSum = calculateLeafToLeafPathSum(node.right, maxSum);

        if (node.left != null && node.right != null) {
            int currentPathSum = leftPathSum + rightPathSum + node.value;
            maxSum[0] = Math.max(maxSum[0], currentPathSum);
            return Math.max(leftPathSum, rightPathSum) + node.value;
        }

        return node.left == null ? rightPathSum + node.value : leftPathSum + node.value;
    }
}
