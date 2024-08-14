package dynamic_programming.knapsack;

public class _07_SubSetToGetSum {
    public _07_SubSetToGetSum() {
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 7, 5, 10};
        int targetSum = 5;
        System.out.println(subSetToGetSum(array, 0, targetSum));
        System.out.println(subSetToGetSumUsingIteration(array, targetSum));
    }

    private static boolean subSetToGetSum(int[] array, int counter, int targetSum) {
        if (targetSum == 0) {
            return true;
        } else if (counter >= array.length) {
            return false;
        } else if (array[counter] > targetSum) {
            return subSetToGetSum(array, counter + 1, targetSum);
        } else {
            return subSetToGetSum(array, counter + 1, targetSum - array[counter]) || subSetToGetSum(array, counter + 1, targetSum);
        }
    }

    private static boolean subSetToGetSumUsingIteration(int[] array, int targetSum) {
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
