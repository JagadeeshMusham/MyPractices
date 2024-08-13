package dynamic_programming;

/**
 * Given an array A of size N. FInd the maximum subset-sum of
 * elements that you can make from the given array such that for
 * every 2 consecutive element in the array, at least one of the
 * elements is present in our subset.
 *
 * Example 1:
 * Input:
 * N = 4
 * A[] = {1, -1, 3, 4}
 * output: 8
 *
 * Explanation:
 * We can choose 0th, 2nd, 3rd indexed values, so that it can
 * satisfy the condition & can make maximum sum 8.
 */

public class MaximumSubsetSum {

    public static void main(String[] args) {

        int array1[] = {1, -1, 3, 4};
        System.out.println("\nThe Maximum subset Sum is: " +
                findMaximumSubsetSum(array1, 0, false));

//        int array2[] = {1, -1, -3, 4};
//        System.out.println("\nThe Maximum subset Sum is: " +
//                findMaximumSubsetSum(array2, 0, false));
//
//        int array3[] = {-1, -1, -3, 4};
//        System.out.println("\nThe Maximum subset Sum is: " +
//                findMaximumSubsetSum(array3, 0, false));
    }

    private static int findMaximumSubsetSum(int[] array, int counter, boolean bTaken) {
        if (counter >= array.length) {
            return 0;
        }

        if (!bTaken) {
            return findMaximumSubsetSum(array, counter+1, true) + array[counter];
        }

        int notTakenValue = findMaximumSubsetSum(array, counter+1, false);
        int takenValue = findMaximumSubsetSum(array, counter+1, true);
        System.out.printf("The notTakenValue: %d takenValue: %d at counter: %d\n", notTakenValue, takenValue, counter);
        return Math.max(notTakenValue, takenValue + array[counter]);
    }
}
