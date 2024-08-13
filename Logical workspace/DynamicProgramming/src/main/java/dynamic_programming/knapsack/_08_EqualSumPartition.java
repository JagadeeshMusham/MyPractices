package dynamic_programming.knapsack;

public class _08_EqualSumPartition {
    public static void main(String[] args) {
        int[] input = {5, 1, 11, 5, 1, 1, 2, 3, 1};

        System.out.printf("Can we make equal sum partition from give input: %b - %b",
                canEqualSumPartition(input, 0, true),
                canEqualSumPartition(input, 0, false));
    }

    private static boolean canEqualSumPartition(int[] input, int index, boolean bRecursive) {
        int sum = 0;
        for (int inputValue : input) {
            sum += inputValue;
        }

        if (sum % 2 != 0) {
            return false;
        }

        if( bRecursive) {
            return equalSumPartition(input, 0, sum / 2);
        } else {
            return equalSumPartitionUsingIteration(input, sum / 2);
        }
    }

    private static boolean equalSumPartition(int[] input, int index, int sum) {
        if (sum == 0) {
            return true;
        }

        if(index >= input.length) {
            return false;
        }

        if (input[index] > sum) {
            return equalSumPartition(input, index+1, sum);
        }
        else {
            return equalSumPartition(input, index+1, sum-input[index]);
        }
    }

    private static boolean equalSumPartitionUsingIteration(int[] input, int sum) {
        boolean[][] dp = new boolean[input.length+1][sum+1];

        for (int row = 0; row <= input.length; row++) {
            dp[row][0] = true;
        }

        for(int col = 1; col <= sum; col++) {
            dp[0][col] = false;
        }

        for (int row = 1; row <= input.length; row++) {
            for(int col = 1; col <= sum; col++) {
                if (input[row-1] > col) {
                    dp[row][col] = dp[row-1][col];
                } else {
                    dp[row][col] = dp[row-1][col] || dp[row-1][col-input[row-1]];
                }
            }
        }

        return dp[input.length][sum];
    }
}
