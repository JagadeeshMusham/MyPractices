package dynamic_programming;

import java.util.Arrays;

public class MaxNonAdjucentSumWithMemo {

    public static int findMaxNonAdjacentSum(int[] array, int index, int[] memo) {
        if (index >= array.length) {
            return 0;
        }

        // Check if result for this index is already computed
        if (memo[index] != -1) {
            System.out.printf("The Memo value:%d at index %d\n", memo[index], index);
            return memo[index];
        }

        // Calculate the maximum sum for the current index
        int include = array[index] + findMaxNonAdjacentSum(array, index + 2, memo);
        int exclude = findMaxNonAdjacentSum(array, index + 1, memo);
        memo[index] = Math.max(include, exclude);   // to store max value at index

        return memo[index];
    }

    private static void printArray(int[] arr) {
        System.out.print("\nThe array is: [");
        for (int counter = 0; counter < arr.length; counter++) {
            System.out.print(arr[counter] + ", ");
        }
        System.out.print("\b\b]\n");
    }

    public static void main(String[] args) {
        int[] array6 = {3, 2, 7, 10};
        int[] memo = new int[array6.length + 10];
        Arrays.fill(memo, -1);  // Initialize memoization array with -1
        printArray(array6);
        System.out.println("Final Maximum Non-Adjacent Sum: " + findMaxNonAdjacentSum(array6, 0, memo));

        int array1[] = {-2, 1, 3, -4, 5};
        printArray(array1);
        Arrays.fill(memo, -1);  // Initialize memoization array with -1
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array1, 0, memo));

        int array2[] = {-2, -1, -3};
        printArray(array2);
        Arrays.fill(memo, -1);  // Initialize memoization array with -1
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array2, 0, memo));

        int array3[] = {3, 7, 4, 6, 5};
        printArray(array3);
        Arrays.fill(memo, -1);  // Initialize memoization array with -1
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array3, 0, memo));

        int array4[] = {3, 5, -7, 8, 10};
        printArray(array4);
        Arrays.fill(memo, -1);  // Initialize memoization array with -1
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array4, 0, memo));

        int array5[] = {2, 1, 5, 8, 4};
        printArray(array5);
        Arrays.fill(memo, -1);  // Initialize memoization array with -1
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array5, 0, memo));
    }
}
