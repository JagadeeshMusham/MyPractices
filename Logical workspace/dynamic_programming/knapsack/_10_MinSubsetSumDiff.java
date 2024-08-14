package dynamic_programming.knapsack;

public class _10_MinSubsetSumDiff {
    public _10_MinSubsetSumDiff() {
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 7};
        printArray(input1);
        System.out.println("The minimum difference between two subsets is: " + findMinSubsetSumDifference(input1));

        int[] input2 = new int[]{1, 6, 11, 5};
        printArray(input2);
        System.out.println("The minimum difference between two subsets is: " + findMinSubsetSumDifference(input2));

        int[] input3 = new int[]{1, 2, 3, 9};
        printArray(input3);
        System.out.println("The minimum difference between two subsets is: " + findMinSubsetSumDifference(input3));

        int[] input4 = new int[]{1, 1, 1, 1, 1, 1, 1};
        printArray(input4);
        System.out.println("The minimum difference between two subsets is: " + findMinSubsetSumDifference(input4));

        int[] input5 = new int[]{5, 1, 11, 5};
        printArray(input5);
        System.out.println("The minimum difference between two subsets is: " + findMinSubsetSumDifference(input5));
    }

    private static void printArray(int[] input) {
        for(int counter = 0; counter < input.length; ++counter) {
            System.out.print(input[counter] + " ");
        }

        System.out.println();
    }

    private static int findMinSubsetSumDifference(int[] input) {
        int inputLength = input.length;
        int sum = 0;

        int rowIndex;
        for(rowIndex = 0; rowIndex < inputLength; ++rowIndex) {
            sum += input[rowIndex];
        }

        int targetSum = sum / 2;
        boolean[][] dp = new boolean[inputLength + 1][targetSum + 1];

        for(rowIndex = 0; rowIndex <= inputLength; ++rowIndex) {
            dp[rowIndex][0] = true;
        }

        int colIndex;
        for(colIndex = 1; colIndex <= targetSum; ++colIndex) {
            dp[0][colIndex] = false;
        }

        for(rowIndex = 1; rowIndex <= inputLength; ++rowIndex) {
            for(colIndex = 1; colIndex <= targetSum; ++colIndex) {
                if (input[rowIndex - 1] > colIndex) {
                    dp[rowIndex][colIndex] = dp[rowIndex - 1][colIndex];
                } else {
                    dp[rowIndex][colIndex] = dp[rowIndex - 1][colIndex] || dp[rowIndex - 1][colIndex - input[rowIndex - 1]];
                }
            }
        }

        rowIndex = sum;

        for(colIndex = targetSum; colIndex >= 0; --colIndex) {
            if (dp[inputLength][colIndex]) {
                rowIndex = sum - 2 * colIndex;
                break;
            }
        }

        return rowIndex;
    }
}
