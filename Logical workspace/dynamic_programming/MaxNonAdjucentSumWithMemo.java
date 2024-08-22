package dynamic_programming;

import java.util.Arrays;

public class MaxNonAdjucentSumWithMemo {

    public static void main(String[] args) {
        int[] memo = new int[19];   // this value hav to be bigger than any array below

        int[] array = new int[]{-2, -1, -3};
        printArray(array);
        Arrays.fill(memo, Integer.MIN_VALUE);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0, memo));

        array = new int[]{3, 2, 7, 10};
        Arrays.fill(memo, Integer.MIN_VALUE);
        printArray(array);
        System.out.println("Final Maximum Non-Adjacent Sum: " +
                findMaxNonAdjacentSum(array, 0, memo));

        array = new int[]{-2, 1, 3, -4, 5};
        printArray(array);
        Arrays.fill(memo, Integer.MIN_VALUE);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0, memo));

        array = new int[]{3, 7, 4, 6, 5};
        printArray(array);
        Arrays.fill(memo, Integer.MIN_VALUE);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0, memo));

        array = new int[]{3, 5, -7, 8, 10};
        printArray(array);
        Arrays.fill(memo, Integer.MIN_VALUE);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0, memo));

        array = new int[]{2, 1, 5, 8, 4};
        printArray(array);
        Arrays.fill(memo, Integer.MIN_VALUE);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0, memo));
    }

    public static int findMaxNonAdjacentSum(int[] array, int index, int[] memo) {
        if (index >= array.length) {
            return 0;
        }

        if (memo[index] != Integer.MIN_VALUE) {
            return memo[index];
        }

        int include = array[index] + findMaxNonAdjacentSum(array, index + 2, memo);
        int exclude = findMaxNonAdjacentSum(array, index + 1, memo);
        memo[index] = Math.max(Math.max(include, exclude), memo[index]);
        return memo[index];
    }

    private static void printArray(int[] arr) {
        System.out.print("\nThe array is: [");

        for(int counter = 0; counter < arr.length; ++counter) {
            System.out.print(arr[counter] + (counter < arr.length - 1 ? ", " : "]\n"));
        }
    }

}
