package dynamic_programming.knapsack;

public class _16_MinCoinChangeProblem {
    public _16_MinCoinChangeProblem() {
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5, 10};
        int sum = 7;
        System.out.printf("Min coins required to get sum of %d: %d - %d\n", Integer.valueOf(sum), getMinCoins(coins, sum), getMinCoinsUsingRec(coins, coins.length, sum));
        sum = 13;
        System.out.printf("Min coins required to get sum of %d: %d - %d\n", Integer.valueOf(sum), getMinCoins(coins, sum), getMinCoinsUsingRec(coins, coins.length, sum));
        sum = 15;
        System.out.printf("Min coins required to get sum of %d: %d - %d\n", Integer.valueOf(sum), getMinCoins(coins, sum), getMinCoinsUsingRec(coins, coins.length, sum));
        sum = 21;
        System.out.printf("Min coins required to get sum of %d: %d - %d\n", Integer.valueOf(sum), getMinCoins(coins, sum), getMinCoinsUsingRec(coins, coins.length, sum));
        sum = 5;
        System.out.printf("Min coins required to get sum of %d: %d - %d\n", Integer.valueOf(sum), getMinCoins(coins, sum), getMinCoinsUsingRec(coins, coins.length, sum));
        sum = 19;
        System.out.printf("Min coins required to get sum of %d: %d - %d\n", Integer.valueOf(sum), getMinCoins(coins, sum), getMinCoinsUsingRec(coins, coins.length, sum));
    }

    private static int getMinCoinsUsingRec(int[] coins, int coinsLength, int sum) {
        if (sum == 0) {
            return 0;
        } else if (sum < 0) {
            return Integer.MAX_VALUE;
        } else if (coinsLength <= 0) {
            return Integer.MAX_VALUE;
        } else {
            int excludeCurrentCoin = getMinCoinsUsingRec(coins, coinsLength - 1, sum);
            int includeCurrentCoin = getMinCoinsUsingRec(coins, coinsLength, sum - coins[coinsLength - 1]);
            if (includeCurrentCoin != Integer.MAX_VALUE) {
                ++includeCurrentCoin;
            }

            return Math.min(excludeCurrentCoin, includeCurrentCoin);
        }
    }

    private static int getMinCoins(int[] coins, int sum) {
        int[][] dp = new int[coins.length + 1][sum + 1];

        int nCoins;
        for(nCoins = 0; nCoins <= coins.length; ++nCoins) {
            dp[nCoins][0] = 0;
        }

        int nSum;
        for(nSum = 0; nSum <= sum; ++nSum) {
            dp[0][nSum] = 2147483646;
        }

        for(nCoins = 1; nCoins <= coins.length; ++nCoins) {
            for(nSum = 1; nSum <= sum; ++nSum) {
                if (coins[nCoins - 1] > nSum) {
                    dp[nCoins][nSum] = dp[nCoins - 1][nSum];
                } else {
                    dp[nCoins][nSum] = Math.min(dp[nCoins - 1][nSum], dp[nCoins][nSum - coins[nCoins - 1]] + 1);
                }
            }
        }

        return dp[coins.length][sum];
    }
}
