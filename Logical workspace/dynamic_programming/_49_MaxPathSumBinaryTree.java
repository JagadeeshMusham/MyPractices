package dynamic_programming;

public class _49_MaxPathSumBinaryTree {

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

        System.out.println("The maximum path sum in the binary tree is: " + tree.getMaxPathSum(root));
    }
}
