package dynamic_programming.knapsack;

public class _09_SubSetSumCount {
    public _09_SubSetSumCount() {
    }

    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 5, 7, 8, 10};
        int sum = 10;
        System.out.printf("%d - %d number of subsets the sum of %d \n", getSubSetSumCount(input, 0, sum), getSubSetSumCountUsingIteration(input, sum), Integer.valueOf(sum));
        sum = 8;
        System.out.printf("%d - %d number of subsets the sum of %d \n", getSubSetSumCount(input, 0, sum), getSubSetSumCountUsingIteration(input, sum), Integer.valueOf(sum));
        sum = 5;
        System.out.printf("%d - %d number of subsets the sum of %d \n", getSubSetSumCount(input, 0, sum), getSubSetSumCountUsingIteration(input, sum), Integer.valueOf(sum));
        sum = 7;
        System.out.printf("%d - %d number of subsets the sum of %d \n", getSubSetSumCount(input, 0, sum), getSubSetSumCountUsingIteration(input, sum), Integer.valueOf(sum));
        sum = 4;
        System.out.printf("%d - %d number of subsets the sum of %d \n", getSubSetSumCount(input, 0, sum), getSubSetSumCountUsingIteration(input, sum), Integer.valueOf(sum));
        sum = 0;
        System.out.printf("%d - %d number of subsets the sum of %d \n", getSubSetSumCount(input, 0, sum), getSubSetSumCountUsingIteration(input, sum), Integer.valueOf(sum));
    }

    private static int getSubSetSumCount(int[] input, int index, int sum) {
        if (sum == 0) {
            return 1;
        } else if (index >= input.length) {
            return 0;
        } else {
            if (input[index] > sum) {
                return getSubSetSumCount(input, index + 1, sum);
            } else {
                // The count if v are not considering + The count if v are considering
                return getSubSetSumCount(input, index + 1, sum) + getSubSetSumCount(input, index + 1, sum - input[index]);
            }
        }
    }

    private static int getSubSetSumCountUsingIteration(int[] input, int sum) {
        int[][] dp = new int[input.length + 1][sum + 1];

        int rows;
        for(rows = 0; rows <= input.length; ++rows) {
            dp[rows][0] = 1;
        }

        for(rows = 1; rows <= sum; ++rows) {
            dp[0][rows] = 0;
        }

        for(rows = 1; rows <= input.length; ++rows) {
            for(int cols = 1; cols <= sum; ++cols) {
                if (input[rows - 1] > cols) {
                    dp[rows][cols] = dp[rows - 1][cols];
                } else {
                    dp[rows][cols] = dp[rows - 1][cols] + dp[rows - 1][cols - input[rows - 1]];
                }
            }
        }

        return dp[input.length][sum];
    }
}
