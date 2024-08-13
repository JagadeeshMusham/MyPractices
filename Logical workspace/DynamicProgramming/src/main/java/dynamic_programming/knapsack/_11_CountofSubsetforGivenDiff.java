package dynamic_programming.knapsack;

public class _11_CountofSubsetforGivenDiff {
    public static void main(String[] args) {
        int[] input1 = {2, 3, 5, 7, 8, 10};
        // 22
        //2, 3, 7, 10
        //5, 7, 10
        //7, 8, 5, 2
        int diff = 10;
        System.out.printf("%d number of subsets the sum of %d \n",
                getSubSetSumCountUsingIteration(input1, diff),
                diff);

        int[] input2 = {1, 1, 2, 3};
        diff = 1;
        System.out.printf("%d number of subsets the sum of %d \n",
                getSubSetSumCountUsingIteration(input2, diff),
                diff);

        int[] input3 = {1, 2};
        diff = 1;
        System.out.printf("%d number of subsets the sum of %d \n",
                getSubSetSumCountUsingIteration(input3, diff),
                diff);
    }

    private static int getSubSetSumCountUsingIteration(int[] input, int diff) {
        int grandSum = 0;
        for (int counter = 0; counter < input.length; counter++) {
            grandSum += input[counter];
        }

        /**
         * Set1_sum + Set2_sum = GrandSum
         * Set1_sum - Set2_sum = diff
         *
         * 2Set1_sum = GranSum + diff
         *
         * Set1_sum = (GrandSum + diff) / 2;
         */

        int set1_sum = (grandSum + diff) / 2;

        int[][] dp = new int[input.length+1][set1_sum+1];

        // Fill first column with 1 as sum of 0 is possible with only empty set
        for(int index = 0; index <= input.length; index++) {
            dp[index][0] = 1;
        }

        //Fill first row(except first col) with 0 as any sum is not possible with empty set
        for(int index = 1; index <= set1_sum; index++) {
            dp[0][index] = 0;
        }

        for (int rows = 1; rows <= input.length; rows++) {
            for (int cols = 1; cols <= set1_sum; cols++) {
                if (input[rows-1] > cols) {
                    dp[rows][cols] = dp[rows-1][cols];
                }
                else {
                    dp[rows][cols] = dp[rows-1][cols] + dp[rows-1][cols - input[rows-1]];
                }
            }

        }

        return dp[input.length][set1_sum];
    }
}
