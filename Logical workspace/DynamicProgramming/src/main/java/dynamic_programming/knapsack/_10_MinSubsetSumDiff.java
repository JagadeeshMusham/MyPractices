package dynamic_programming.knapsack;

/**
 * Problem Statement:
 * Given a set of integers, divide it into two subsets such that the
 * difference between their sums is minimized.
 */
public class _10_MinSubsetSumDiff {
    public static void main(String[] args){
        int[] input1 = {1, 2, 7};
        printArray(input1);
        System.out.println("The minimum difference between two subsets is: " +
                findMinSubsetSumDifference(input1));

        int[] input2 = {1, 6, 11, 5};
        printArray(input2);
        System.out.println("The minimum difference between two subsets is: " +
                findMinSubsetSumDifference(input2));

        int[] input3 = {1, 2, 3, 9};
        printArray(input3);
        System.out.println("The minimum difference between two subsets is: " +
                findMinSubsetSumDifference(input3));

        int[] input4 = {1, 1, 1, 1, 1, 1, 1};
        printArray(input4);
        System.out.println("The minimum difference between two subsets is: " +
                findMinSubsetSumDifference(input4));

    }

    private static void printArray(int[] input) {
        for (int counter = 0; counter < input.length; counter++) {
            System.out.print(input[counter] + " ");
        }
        System.out.println();
    }

    private static int findMinSubsetSumDifference(int[] input) {
        int inputLength = input.length;
        int sum = 0;

        for (int counter = 0; counter < inputLength; counter++) {
            sum += input[counter];
        }

        int target = sum/2;

        boolean[][] dp = new boolean[inputLength + 1][target + 1];

        for (int counter = 0; counter <= inputLength; counter++) {
            dp[counter][0] = true;
        }

        for (int counter = 1; counter <= target; counter++) {
            dp[0][counter] = false;
        }

        for (int row = 1; row <= inputLength; row++) {
            for (int col = 1; col <= target; col++) {
                if(input[row - 1] > col) {
                    dp[row][col] = dp[row-1][col];
                }
                else {
                    dp[row][col] = dp[row - 1][col] ||
                            dp[row-1][col - input[row - 1]];
                }
            }
        }

        // Find the maximum sum from the target(sum/2) such that dp[n][j]
        // is true where j is as close as possible to target
        int minDiff = sum;
        for (int counter = target; counter >= 0; counter--) {
            if(dp[inputLength][counter]) {
                minDiff = sum - 2*counter;
                break;
            }
        }

        return minDiff;
    }
}
