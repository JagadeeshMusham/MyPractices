package DynamicProgramming.knapsack;

/**
 * Problem:
 * Given a list of integers and a target sum, determine the number
 * of subsets of the list that sum up to the target value.
 *
 * input: 2, 3, 5, 7, 8, 10
 * Sum: 10
 * output: 4
 *
 * Input: 2, 3, 5, 7, 8, 10
 * Sum: 8
 * Output: 2
 */
public class _09_SubSetSumCount {
    public static void main(String[] args) {
        int[] input = {2, 3, 5, 7, 8, 10};

        int sum = 10;
        System.out.printf("%d - %d number of subsets the sum of %d \n",
                getSubSetSumCount(input, 0, sum),
                getSubSetSumCountUsingIteration(input, sum),
                sum);

        sum = 8;
        System.out.printf("%d - %d number of subsets the sum of %d \n",
                getSubSetSumCount(input, 0, sum),
                getSubSetSumCountUsingIteration(input, sum),
                sum);

        sum = 5;
        System.out.printf("%d - %d number of subsets the sum of %d \n",
                getSubSetSumCount(input, 0, sum),
                getSubSetSumCountUsingIteration(input, sum),
                sum);

        sum = 7;
        System.out.printf("%d - %d number of subsets the sum of %d \n",
                getSubSetSumCount(input, 0, sum),
                getSubSetSumCountUsingIteration(input, sum),
                sum);

        sum = 4;
        System.out.printf("%d - %d number of subsets the sum of %d \n",
                getSubSetSumCount(input, 0, sum),
                getSubSetSumCountUsingIteration(input, sum),
                sum);
        sum = 0;
        System.out.printf("%d - %d number of subsets the sum of %d \n",
                getSubSetSumCount(input, 0, sum),
                getSubSetSumCountUsingIteration(input, sum),
                sum);
    }

    private static int getSubSetSumCount(int[] input, int index, int sum) {
        if (sum == 0) {
            return 1;
        }

        if(index >= input.length) {
            return 0;
        }

        if(input[index] > sum) {
            return getSubSetSumCount(input, index+1, sum);
            //dp[rows][cols] = dp[rows - 1][cols];
        }
        else {
            return getSubSetSumCount(input, index+1, sum - input[index])
                    //dp[rows - 1][cols - array[rows - 1]]
                    +
                    getSubSetSumCount(input, index+1, sum);

        }
    }

    private static int getSubSetSumCountUsingIteration(int[] input, int sum) {
        int[][] dp = new int[input.length+1][sum+1];

        // Fill first column with 1 as sum of 0 is possible with only empty set
        for(int index = 0; index <= input.length; index++) {
            dp[index][0] = 1;
        }

        //Fill first row(except first col) with 0 as any sum is not possible with empty set
        for(int index = 1; index <= sum; index++) {
            dp[0][index] = 0;
        }

        for (int rows = 1; rows <= input.length; rows++) {
            for (int cols = 1; cols <= sum; cols++) {
                if(input[rows - 1] > cols) {
                    dp[rows][cols] = dp[rows - 1][cols];
                } else {
                    dp[rows][cols] = dp[rows - 1][cols] + dp[rows - 1][cols - input[rows - 1]];
                }
            }
        }

        return dp[input.length][sum];
    }
}
