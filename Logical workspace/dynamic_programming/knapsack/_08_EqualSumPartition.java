package dynamic_programming.knapsack;

public class _08_EqualSumPartition {
    public _08_EqualSumPartition() {
    }

    public static void main(String[] args) {
        int[] input = {5, 1, 11, 5, 1, 1, 2, 3, 1};
        System.out.printf("Can we make equal sum partition from give input: %b - %b",
                canEqualSumPartition(input, 0, true),
                canEqualSumPartition(input, 0, false));
    }

    private static boolean canEqualSumPartition(int[] input, int index, boolean bRecursive) {
        int sum = 0;

        for (int iterator = 0; iterator < input.length; iterator++) {
            int inputValue = input[iterator];
            sum += inputValue;
        }

        if (sum % 2 != 0) {
            return false;
        }

        return bRecursive ?
                equalSumPartitionUsingRec(input, 0, sum / 2) :
                equalSumPartitionUsingDP(input, sum / 2);
    }

    private static boolean equalSumPartitionUsingRec(int[] input, int index, int sum) {
        if (sum == 0) {
            return true;
        }

        if (index >= input.length) {
            return false;
        }

        return input[index] > sum ?
                equalSumPartitionUsingRec(input, index + 1, sum) :
                equalSumPartitionUsingRec(input, index + 1, sum - input[index]);
    }

    private static boolean equalSumPartitionUsingDP(int[] input, int sum) {
        boolean[][] dp = new boolean[input.length + 1][sum + 1];

        int row = 0;
        for (; row <= input.length; ++row) {
            dp[row][0] = true;
        }

        int col = 1;
        for (; col <= sum; col++) {
            dp[0][col] = false;
        }

        for (row = 1; row <= input.length; ++row) {
            for (col = 1; col <= sum; ++col) {
                if (input[row - 1] > col) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = dp[row - 1][col] || dp[row - 1][col - input[row - 1]];
                }
            }
        }

        if (false) {
            for (row = 0; row <= 4; ++row) {
                for (col = 0; col <= 11; ++col) {
                    System.out.print(dp[row][col] + " ");
                }

                System.out.println();
            }
            System.out.println();
            System.out.println();
        }

        return dp[input.length][sum];
    }
}