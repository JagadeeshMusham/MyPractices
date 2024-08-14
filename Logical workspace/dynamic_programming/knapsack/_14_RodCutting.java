package dynamic_programming.knapsack;

public class _14_RodCutting {

    private static int calculateMaxRevenueWithDP(int[] prices, int rodLen) {
        int[][] dp = new int[prices.length + 1][rodLen + 1];

        int pieceLen;
        for (pieceLen = 0; pieceLen < prices.length; ++pieceLen) {
            dp[pieceLen][0] = 0;
        }

        int reqLen;
        for (reqLen = 0; reqLen < rodLen; ++reqLen) {
            dp[0][reqLen] = 0;
        }

        for (pieceLen = 1; pieceLen <= prices.length; ++pieceLen) {
            for (reqLen = 1; reqLen <= rodLen; ++reqLen) {
                if (pieceLen > reqLen) {
                    dp[pieceLen][reqLen] = dp[pieceLen - 1][reqLen];
                } else {
                    dp[pieceLen][reqLen] = Math.max(dp[pieceLen - 1][reqLen],
                            prices[pieceLen - 1] + dp[pieceLen][reqLen - pieceLen]);
                }
            }
        }

        if (rodLen == 9) {
            for (pieceLen = 0; pieceLen <= prices.length; ++pieceLen) {
                for (reqLen = 0; reqLen <= rodLen; ++reqLen) {
                    System.out.print(dp[pieceLen][reqLen] + "\t");
                }
                System.out.println();
            }
        }

        return dp[prices.length][rodLen];
    }

    public static int maxRevenueWithIterator(int[] prices, int roadLen) {
        int[] dp = new int[roadLen + 1];
        dp[0] = 0;

        for (int reqLen = 1; reqLen <= roadLen; ++reqLen) {
            int maxVal = Integer.MIN_VALUE;

            for (int pieceLen = 1; pieceLen <= reqLen; ++pieceLen) {
                if (pieceLen > prices.length) {
                    int multiplyValue = pieceLen / prices.length;
                    maxVal = Math.max(maxVal, dp[prices.length] * multiplyValue + dp[pieceLen - multiplyValue * prices.length]);
                } else {
                    maxVal = Math.max(maxVal, prices[pieceLen - 1] + dp[reqLen - pieceLen]);
                }
            }

            dp[reqLen] = maxVal;
        }

        return dp[roadLen];
    }

    private int test(int[] prices, int rodLen) {
        if (rodLen == 0 || prices.length == 0) {
            return 0;
        }

        int maxRevenue = 0;

        for (int curRoadLen = 0; curRoadLen <= rodLen; curRoadLen++) {
            if (curRoadLen < prices.length) {
                int curRevenue = prices[curRoadLen] + test(prices, rodLen - (curRoadLen + 1));
                maxRevenue = Math.max(maxRevenue, curRevenue);
            }
        }
        return maxRevenue;
    }

    //Todo J, this is my own way
    public static int getRevenueWithRec(int[] prices, int rodLength) {
        int maxRevenue = 0;
        if (rodLength > prices.length) {
            int curRevenue = maxRevenueWithRec(prices, prices.length);
            maxRevenue = curRevenue * (rodLength / prices.length);
            maxRevenue += maxRevenueWithRec(prices, rodLength % prices.length);
        } else {
            maxRevenue = maxRevenueWithRec(prices, rodLength);
        }

        return maxRevenue;
    }

    public static int maxRevenueWithRec(int[] prices, int remainingRodLength) {
        if (remainingRodLength == 0) {
            return 0;
        }

        int maxRevenue = 0;

        for (int curRoadLen = 0; curRoadLen < remainingRodLength; ++curRoadLen) {
            int curRevenue = prices[curRoadLen] + maxRevenueWithRec(prices, remainingRodLength - (curRoadLen + 1));
            maxRevenue = Math.max(maxRevenue, curRevenue);
        }

        return maxRevenue;
    }

    public static void main(String[] args) {
        int[] pricePerLength = {2, 5, 7, 8};

        int rodLength = 4;
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenueWithDP(pricePerLength, rodLength),
                maxRevenueWithIterator(pricePerLength, rodLength),
                getRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 8;
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenueWithDP(pricePerLength, rodLength),
                maxRevenueWithIterator(pricePerLength, rodLength),
                getRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 6;
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenueWithDP(pricePerLength, rodLength),
                maxRevenueWithIterator(pricePerLength, rodLength),
                getRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 9;
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenueWithDP(pricePerLength, rodLength),
                maxRevenueWithIterator(pricePerLength, rodLength),
                getRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 2;
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenueWithDP(pricePerLength, rodLength),
                maxRevenueWithIterator(pricePerLength, rodLength),
                getRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 50;
        System.out.printf("Maximum revenue %d -%d to get the rod length of %d\n",
                calculateMaxRevenueWithDP(pricePerLength, rodLength),
                maxRevenueWithIterator(pricePerLength, rodLength), rodLength);

        System.out.printf("Maximum revenue %d to get the rod length of %d\n",
                getRevenueWithRec(pricePerLength, rodLength), rodLength);
    }
}
