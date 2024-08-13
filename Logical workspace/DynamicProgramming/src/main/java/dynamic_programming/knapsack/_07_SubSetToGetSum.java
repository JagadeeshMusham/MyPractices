package dynamic_programming.knapsack;

public class _07_SubSetToGetSum {
    public static void main(String[] args) {
        int[] array = {2, 3, 7, 5, 10};
        int targetSum = 5;

        System.out.println(subSetToGetSum(array, 0, targetSum));
        System.out.println(subSetToGetSumUsingIteration(array, targetSum));
    }

    private static boolean subSetToGetSum(int[] array, int counter, int targetSum) {
        if(targetSum == 0) {
            return true;
        }

        if (counter >= array.length) {
            return false;
        }

        if (array[counter] <= targetSum) {
            return (subSetToGetSum(array, counter + 1,
                    targetSum - array[counter]))
                    ||
                    (subSetToGetSum(array, counter + 1,
                            targetSum));
        } else {
            return subSetToGetSum(array, counter + 1,
                    targetSum);
        }
    }

    private static boolean subSetToGetSumUsingIteration(int[] array, int targetSum) {
        boolean[][] dp = new boolean[array.length+1][targetSum+1];

        // Fill first column with True as sum of 0 is possible with any set
        for(int index = 0; index <= array.length; index++) {
            dp[index][0] = true;
        }

        //Fill first row(except first col) with false as any sum is not possible with empty set
        for(int index = 1; index <= targetSum; index++) {
            dp[0][index] = false;
        }

        for (int rows = 1; rows <= array.length; rows++) {
            for (int cols = 1; cols <= targetSum; cols++) {
                if (array[rows - 1] > cols) {
//                    dp[rows][cols] = false;
                    dp[rows][cols] = dp[rows - 1][cols];
                }
                else {
//                    dp[rows][cols] = dp[rows - 1][cols] || dp[rows - 1][cols - array[rows - 1]];
                    dp[rows][cols] = dp[rows - 1][cols] || dp[rows - 1][cols - array[rows - 1]];
                }
            }
        }

        return dp[array.length][targetSum];
    }
}
