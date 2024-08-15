package dynamic_programming.knapsack;

/**
 * This method getMaxNumberOfWays computes the number of different
 * ways to make a given sum using a set of coin denominations.
 * The algorithm is an implementation of the dynamic programming
 * approach to solve the "Coin Change" problem.
 */
public class _15_CoinChangeProblem {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 3};
        int sum = 5;
        System.out.printf("%d - %d number of ways are there to get the sum of %d",
                getMaxNumberOfWaysUsingDP(coins, sum),
                getMaxNumberOfWaysUsingRec(coins, coins.length, sum), Integer.valueOf(sum));
    }

    /**
     *
     * @param coins
     * @param pos
     * @param sum
     * @return
     */
    private static int getMaxNumberOfWaysUsingRec(int[] coins, int pos, int sum) {
        if(sum == 0) {
            return 1;
        }

        if(sum < 0 || coins.length == 0 || pos <= 0) {
            return 0;
        }

        return getMaxNumberOfWaysUsingRec(coins, pos, sum - coins[pos - 1])
                +
                getMaxNumberOfWaysUsingRec(coins, pos-1, sum);
    }

    /**
     *
     * @param coins
     * @param sum
     * @return
     */
    private static int getMaxNumberOfWaysUsingDP(int[] coins, int sum) {
        int[][] dp = new int[coins.length + 1][sum + 1];

        int nCoin;
        for(nCoin = 0; nCoin <= coins.length; ++nCoin) {
            dp[nCoin][0] = 1;
        }

        int nSum;
        for(nSum = 1; nSum <= sum; ++nSum) {
            dp[0][nSum] = 0;
        }

        for(nCoin = 1; nCoin <= coins.length; ++nCoin) {
            for(nSum = 1; nSum <= sum; ++nSum) {
                if (coins[nCoin - 1] > nSum) {
                    dp[nCoin][nSum] = dp[nCoin - 1][nSum];
                } else {
                    dp[nCoin][nSum] = dp[nCoin - 1][nSum] +
                            dp[nCoin][nSum - coins[nCoin - 1]];
                }
            }
        }

        for(nCoin = 1; nCoin <= coins.length; ++nCoin) {
            for(nSum = 1; nSum <= sum; ++nSum) {
                System.out.print(dp[nCoin][nSum] + "\t");
            }
            System.out.println();
        }

        return dp[coins.length][sum];
    }
}
