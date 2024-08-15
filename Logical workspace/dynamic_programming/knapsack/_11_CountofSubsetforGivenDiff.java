package dynamic_programming.knapsack;

/**
 * The method getSubSetSumCountUsingDP aims to find the number of subsets in a
 * given array input such that the difference between the sum of elements in two
 * subsets is equal to a given value diff. This is done using dynamic programming (DP).
 *
 * The equation comes from transforming the problem:
 * S1 + S2 = Sum
 * S1 - S2 = diff
 *
 * Thus,
 * 2S1 = sum+diff
 * s1 = (sum+diff)/2
 *
 */

public class _11_CountofSubsetforGivenDiff {
    public _11_CountofSubsetforGivenDiff() {
    }

    public static void main(String[] args) {
        int[] input = {2, 3, 5, 7, 8, 10};
        int diff = 10;
        System.out.printf("%d number of subsets the sum of %d \n",
                getSubSetSumCountUsingDP(input, diff), diff);

        input = new int[]{1, 1, 2, 3};
        diff = 1;
        System.out.printf("%d number of subsets the sum of %d \n",
                getSubSetSumCountUsingDP(input, diff), diff);

        input = new int[]{1, 2};
        diff = 1;
        System.out.printf("%d number of subsets the sum of %d \n",
                getSubSetSumCountUsingDP(input, diff), diff);
    }

    private static int getSubSetSumCountUsingDP(int[] input, int diff) {
        int grandSum = 0;

        int set1_sum;
        for(set1_sum = 0; set1_sum < input.length; ++set1_sum) {
            grandSum += input[set1_sum];
        }

        set1_sum = (grandSum + diff) / 2;
        int[][] dp = new int[input.length + 1][set1_sum + 1];

        int rows;
        for(rows = 0; rows <= input.length; ++rows) {
            dp[rows][0] = 1;
        }

        for(rows = 1; rows <= set1_sum; ++rows) {
            dp[0][rows] = 0;
        }

        for(rows = 1; rows <= input.length; ++rows) {
            for(int cols = 1; cols <= set1_sum; ++cols) {
                if (input[rows - 1] > cols) {
                    dp[rows][cols] = dp[rows - 1][cols];
                } else {
                    dp[rows][cols] = dp[rows - 1][cols] + dp[rows - 1][cols - input[rows - 1]];
                }
            }
        }

        return dp[input.length][set1_sum];
    }
}
