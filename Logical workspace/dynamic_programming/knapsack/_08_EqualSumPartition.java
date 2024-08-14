package dynamic_programming.knapsack;

public class _08_EqualSumPartition {
    public _08_EqualSumPartition() {
    }

    public static void main(String[] args) {
        int[] input = new int[]{5, 1, 11, 5, 1, 1, 2, 3, 1};
        System.out.printf("Can we make equal sum partition from give input: %b - %b", canEqualSumPartition(input, 0, true), canEqualSumPartition(input, 0, false));
    }

    private static boolean canEqualSumPartition(int[] input, int index, boolean bRecursive) {
        int sum = 0;
        int[] var4 = input;
        int var5 = input.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            int inputValue = var4[var6];
            sum += inputValue;
        }

        if (sum % 2 != 0) {
            return false;
        } else {
            return bRecursive ? equalSumPartition(input, 0, sum / 2) : equalSumPartitionUsingIteration(input, sum / 2);
        }
    }

    private static boolean equalSumPartition(int[] input, int index, int sum) {
        if (sum == 0) {
            return true;
        } else if (index >= input.length) {
            return false;
        } else {
            return input[index] > sum ? equalSumPartition(input, index + 1, sum) : equalSumPartition(input, index + 1, sum - input[index]);
        }
    }

    private static boolean equalSumPartitionUsingIteration(int[] input, int sum) {
        boolean[][] dp = new boolean[input.length + 1][sum + 1];

        int row;
        for(row = 0; row <= input.length; ++row) {
            dp[row][0] = true;
        }

        for(row = 1; row <= sum; ++row) {
            dp[0][row] = false;
        }

        for(row = 1; row <= input.length; ++row) {
            for(int col = 1; col <= sum; ++col) {
                if (input[row - 1] > col) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = dp[row - 1][col] || dp[row - 1][col - input[row - 1]];
                }
            }
        }

        for(row = 0; row <= 4; ++row) {
            for(int col = 0; col <= 11; ++col) {
                System.out.print(dp[row][col] + " ");
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();

        return dp[input.length][sum];
    }
}