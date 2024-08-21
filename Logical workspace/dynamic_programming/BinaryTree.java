package dynamic_programming;

class BinaryTree {
    int maxDiameter;
    int maxPathSum;

    public BinaryTree() {
        this.maxDiameter = 0;
    }

    public int getMaxDiameter(TreeNode root) {
        calculateDiameter(root);
        return maxDiameter;
    }

    private int calculateDiameter(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftLevel = calculateDiameter(root.left);
        int rightLevel = calculateDiameter(root.right);

        int diameter = 1 + leftLevel + rightLevel;

        maxDiameter = Math.max(maxDiameter, diameter);
        return Math.max(leftLevel, rightLevel) + 1;
    }

    public int getMaxPathSum(TreeNode root) {
        calculateMaxPathSum(root);
        return maxPathSum;
    }

    private int calculateMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftPathSum = calculateMaxPathSum(root.left);
        int rightPathSum = calculateMaxPathSum(root.right);

        int currentMaxPath = root.value + leftPathSum + rightPathSum;

        maxPathSum = Math.max(maxPathSum, currentMaxPath);

        return root.value + Math.max(leftPathSum, rightPathSum);
    }
}
