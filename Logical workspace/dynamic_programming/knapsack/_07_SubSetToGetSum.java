package dynamic_programming.knapsack;

public class _07_SubSetToGetSum {

    public static void main(String[] args) {
        int[] array = {2, 3, 7, 5, 10};
        int targetSum = 5;
        System.out.println(subSetToGetSumUsingRec(array, 0, targetSum));
        System.out.println(subSetToGetSumWithDP(array, targetSum));
    }

    private static boolean subSetToGetSumUsingRec(int[] array, int counter, int targetSum) {
        if (targetSum == 0) {
            return true;
        }

        if (counter >= array.length) {
            return false;
        }

        if (array[counter] > targetSum) {
            return subSetToGetSumUsingRec(array, counter + 1, targetSum);
        }

        return subSetToGetSumUsingRec(array, counter + 1, targetSum) ||
                subSetToGetSumUsingRec(array, counter + 1, targetSum - array[counter]);
    }

    private static boolean subSetToGetSumWithDP(int[] array, int targetSum) {
        boolean[][] dp = new boolean[array.length + 1][targetSum + 1];

        int rows;
        for(rows = 0; rows <= array.length; ++rows) {
            dp[rows][0] = true;
        }

        for(rows = 1; rows <= targetSum; ++rows) {
            dp[0][rows] = false;
        }

        for(rows = 1; rows <= array.length; ++rows) {
            for(int cols = 1; cols <= targetSum; ++cols) {
                if (array[rows - 1] > cols) {
                    dp[rows][cols] = dp[rows - 1][cols];
                } else {
                    dp[rows][cols] = dp[rows - 1][cols] || dp[rows - 1][cols - array[rows - 1]];
                }
            }
        }

        return dp[array.length][targetSum];
    }
}
