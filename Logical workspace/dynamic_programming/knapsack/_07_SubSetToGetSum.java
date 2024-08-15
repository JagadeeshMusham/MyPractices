package dynamic_programming.knapsack;

public class _07_SubSetToGetSum {

    public static void main(String[] args) {
        int[] array = {2, 3, 7, 5, 10};
        int targetSum = 5;
        System.out.println(subSetToGetSumUsingRec(array, 0, targetSum));
        System.out.println(subSetToGetSumWithDP(array, targetSum));
    }

    private static boolean subSetToGetSumUsingRec(int[] input, int counter, int targetSum) {
        if (targetSum == 0) {
            return true;
        }

        if (counter >= input.length) {
            return false;
        }

        if (input[counter] > targetSum) {
            return subSetToGetSumUsingRec(input, counter + 1, targetSum);
        }

        return subSetToGetSumUsingRec(input, counter + 1, targetSum) ||
                subSetToGetSumUsingRec(input, counter + 1, targetSum - input[counter]);
    }

    private static boolean subSetToGetSumWithDP(int[] input, int targetSum) {
        boolean[][] dp = new boolean[input.length + 1][targetSum + 1];

        int rows;
        for(rows = 0; rows <= input.length; ++rows) {
            dp[rows][0] = true;
        }

        for(rows = 1; rows <= targetSum; ++rows) {
            dp[0][rows] = false;
        }

        for(rows = 1; rows <= input.length; ++rows) {
            for(int cols = 1; cols <= targetSum; ++cols) {
                if (input[rows - 1] > cols) {
                    dp[rows][cols] = dp[rows - 1][cols];
                } else {
                    dp[rows][cols] = dp[rows - 1][cols] || dp[rows - 1][cols - input[rows - 1]];
                }
            }
        }

        for(rows = 0; rows <= input.length; ++rows) {
            for(int cols = 0; cols <= targetSum; ++cols) {
                System.out.print(dp[rows][cols] + " ");
            }

            System.out.println();
        }
        return dp[input.length][targetSum];
    }
}
