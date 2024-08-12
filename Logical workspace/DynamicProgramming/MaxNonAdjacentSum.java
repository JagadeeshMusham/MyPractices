package DynamicProgramming;

/**
 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset. It is possible that the maximum sum is
 *
 * , the case when all elements are negative.
 *
 * Example
 * arr = [-2, 1, 3, -4, 5]
 * The following subsets with more than element exist. These exclude the empty subset and single element subsets which are also valid.
 *
 * Subset      Sum
 * [-2, 3, 5]   6
 * [-2, 3]      1
 * [-2, -4]    -6
 * [-2, 5]      3
 * [1, -4]     -3
 * [1, 5]       6
 * [3, 5]       8
 *
 * The maximum subset sum is . Note that any individual element is a subset as well.
 */

public class MaxNonAdjacentSum {
    public static void main(String[] args) {
        int array1[] = {-2, 1, 3, -4, 5};
        printArray(array1);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array1, 0, array1.length));

        int array2[] = {-2, -1, -3};
        printArray(array2);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array2, 0, array2.length));

        int array3[] = {3, 7, 4, 6, 5};
        printArray(array3);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array3, 0, array3.length));

        int array4[] = {3, 5, -7, 8, 10};
        printArray(array4);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array4, 0, array4.length));

        int array5[] = {2, 1, 5, 8, 4};
        printArray(array5);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array5, 0, array5.length));

    }

    private static void printArray(int[] arr) {
        System.out.print("\n\nThe array is: [");
        for (int counter = 0; counter < arr.length; counter++) {
            System.out.print(arr[counter] + ", ");
        }
        System.out.print("\b\b]\n");
    }

    private static int findMaxNonAdjacentSum(int[] array, int index, int arrayLength) {
        if (index >= arrayLength)
            return 0;
        return Math.max(array[index] + findMaxNonAdjacentSum(array, index+2, arrayLength),
                findMaxNonAdjacentSum(array, index+1, arrayLength));
    }
}
