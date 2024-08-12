package DynamicProgramming.knapsack;

/**
 * Problem Statement:
 *    You are given a rod of length n and an array prices[] where prices[i] represents the
 *    price of a rod of length i+1. The task is to determine the maximum revenue that can be
 *    obtained by cutting the rod into smaller lengths and selling the pieces.
 */
public class _14_RodCutting {
    // Function to find the maximum revenue obtainable by cutting the rod
    private static int calculateMaxRevenue(int[] prices, int rodLen) {
        int[][] dp = new int[prices.length+1][rodLen+1];

        int pieceLen = 0;
        for(; pieceLen < prices.length; pieceLen++) {
            dp[pieceLen][0] = 0;
        }

        int reqLen = 0;
        for(; reqLen < rodLen; reqLen++) {
            dp[0][reqLen] = 0;
        }

        for(pieceLen = 1; pieceLen <= prices.length; pieceLen++) {   // row
            for(reqLen = 1; reqLen <= rodLen; reqLen++) { // col
                if(pieceLen > reqLen) {
                    // If the piece length is greater than the required length, we can't use
                    // that piece to build the required length hence considering previous value
                    dp[pieceLen][reqLen] = dp[pieceLen - 1][reqLen];
                }
                else {
                    // we are trying to achieve maximum among:
                    // 1. previous value
                    // 2. Value of CurrentPieceLength + value of remaining piece
                    //     Ex: Assume if pieceLen = 2 and reqLen = 3 then
                    //         we will take value of 2 + value of 1 (nothing but 2 - 1)
                    dp[pieceLen][reqLen] = Math.max(dp[pieceLen - 1][reqLen],
                            prices[pieceLen - 1] + dp[pieceLen][reqLen - pieceLen]);
                }
            }
        }

        //Display the DP value matrix
//        for(curRodLen = 0; curRodLen <= prices.length; curRodLen++) {
//            for (pieceLen = 0; pieceLen <= rodLen; pieceLen++) {
//                System.out.print(dp[curRodLen][pieceLen] + " ");
//            }
//            System.out.println();
//        }

        return dp[prices.length][rodLen];
    }

    public static int maxRevenue(int[] prices, int roadLen) {
        // Array to store the maximum revenue for each length
        int[] dp = new int[roadLen + 1];

        // Base case: maximum revenue for a rod of length 0 is 0
        dp[0] = 0;

        // Fill the dp array for all lengths from 1 to n
        for (int reqLen = 1; reqLen <= roadLen; reqLen++) {
            int maxVal = Integer.MIN_VALUE;

            // Try all possible cuts by considering each piece length j
            for (int pieceLen = 1; pieceLen <= reqLen; pieceLen++) {

                //if the pieceLen is greater than the given size
                if (pieceLen > prices.length) {
                    int multiplyValue = pieceLen / prices.length;
                    maxVal = Math.max(maxVal,
                            (dp[prices.length] * multiplyValue) + dp[pieceLen - multiplyValue*prices.length]);
                } else {
                    maxVal = Math.max(maxVal, prices[pieceLen - 1] + dp[reqLen - pieceLen]);
                }

            }

            dp[reqLen] = maxVal;
        }

//        for (int counter = 0; counter <= roadLen; counter++) {
//            System.out.print(dp[counter] + " ");
//        }
//        System.out.println();

        return dp[roadLen];
    }

    public static int maxRevenueWithRec(int[] prices, int remainingRodLength) {
        // Base case: if the remaining rod length is 0, no revenue can be generated
        if (remainingRodLength == 0) {
            return 0;
        }

        int maxRevenue = 0;

        // Try every possible piece length (from 1 to remainingRodLength) and recursively find the best option
        for (int curRoadLen = 0; curRoadLen < remainingRodLength; curRoadLen++) {
            if(curRoadLen < prices.length) {
                int curRevenue = prices[curRoadLen] +
                        maxRevenueWithRec(prices, remainingRodLength - (curRoadLen + 1));
                maxRevenue = Math.max(maxRevenue, curRevenue);
            }
        }

        return maxRevenue;
    }

    public static void main(String[] args) {
        int[] pricePerLength = {2, 5, 7, 8}; // Prices for lengths 1, 2, 3, 4

        int rodLength = 4; // required length of the rod
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenue(pricePerLength, rodLength),
                maxRevenue(pricePerLength, rodLength),
                maxRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 8; // required length of the rod
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenue(pricePerLength, rodLength),
                maxRevenue(pricePerLength, rodLength),
                maxRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 6; // required length of the rod
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenue(pricePerLength, rodLength),
                maxRevenue(pricePerLength, rodLength),
                maxRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 9; // required length of the rod
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenue(pricePerLength, rodLength),
                maxRevenue(pricePerLength, rodLength),
                maxRevenueWithRec(pricePerLength, rodLength), rodLength);

        rodLength = 2; // required length of the rod
        System.out.printf("Maximum revenue %d - %d - %d to get the rod length of %d\n",
                calculateMaxRevenue(pricePerLength, rodLength),
                maxRevenue(pricePerLength, rodLength),
                maxRevenueWithRec(pricePerLength, rodLength), rodLength);
    }
}
