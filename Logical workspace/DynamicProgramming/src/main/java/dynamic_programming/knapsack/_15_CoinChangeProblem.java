package dynamic_programming.knapsack;

public class _15_CoinChangeProblem {
    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int sum = 5;

        System.out.printf("%d number of ways are there to get the sum of %d",
                getMaxNumberOfWays(coins, sum), sum);
    }

//    private static int getMaxNumberOfWays(int[] coins, int sum) {
//        int[] dp = new int[sum + 1];
//
//        for (int nSum = 0; nSum <= sum; nSum++) {
//            dp[nSum] = 0;
//        }
//    }

    private static int getMaxNumberOfWays(int[] coins, int sum) {
        int[][] dp = new int[coins.length+1][sum+1];

        int nCoin = 0;
        for(; nCoin <= coins.length; nCoin++) {
            dp[nCoin][0] = 1;
        }

        int nSum = 1;
        for(; nSum <= sum; nSum++) {
            dp[0][nSum] = 0;
        }

        for(nCoin = 1; nCoin <= coins.length; nCoin++) {
            for(nSum = 1; nSum <= sum; nSum++) {
                if (coins[nCoin-1] > nSum) {
                    dp[nCoin][nSum] = dp[nCoin-1][nSum];
                }
                else {
                    dp[nCoin][nSum] = dp[nCoin-1][nSum] +
                            dp[nCoin][nSum - coins[nCoin-1]];
                }
            }
        }

        return dp[coins.length][sum];
    }
}
