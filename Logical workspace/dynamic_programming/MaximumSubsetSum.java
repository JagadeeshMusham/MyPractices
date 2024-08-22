package dynamic_programming;

public class MaximumSubsetSum {

    public static void main(String[] args) {
        int[] array = new int[]{1, -1, 3, 4};
        printArray(array);
        System.out.printf("The Maximum subset Sum is: %d - %d\n\n",
                findMaximumSubsetSum(array, 0, false),
                findMaximumSubsetSumWithSimpleRec(array, 0));

        array = new int[]{1, -10, -3, 4};
        printArray(array);
        System.out.printf("The Maximum subset Sum is: %d - %d\n\n",
                findMaximumSubsetSum(array, 0, false),
                findMaximumSubsetSumWithSimpleRec(array, 0));

        array = new int[]{-1, -1, 30, 4};
        printArray(array);
        System.out.printf("The Maximum subset Sum is: %d - %d\n\n",
                findMaximumSubsetSum(array, 0, false),
                findMaximumSubsetSumWithSimpleRec(array, 0));

        array = new int[]{-1, -1, -3, -4};
        printArray(array);
        System.out.printf("The Maximum subset Sum is: %d - %d\n\n",
                findMaximumSubsetSum(array, 0, false),
                findMaximumSubsetSumWithSimpleRec(array, 0));

        array = new int[]{1, 1, 3, 4};
        printArray(array);
        System.out.printf("The Maximum subset Sum is: %d - %d\n\n",
                findMaximumSubsetSum(array, 0, false),
                findMaximumSubsetSumWithSimpleRec(array, 0));
    }

    private static void printArray(int[] arr) {
        System.out.print("The array is: [");
        for(int counter = 0; counter<arr.length; counter++) {
            System.out.print(arr[counter] + (counter < (arr.length - 1) ? ", " : "]\n"));
        }
    }

    private static int findMaximumSubsetSumWithSimpleRec(int[] arr, int index) {
        if(index >= arr.length) {
            return 0;
        }

        return Math.max(arr[index] + findMaximumSubsetSumWithSimpleRec(arr, index + 1),
                findMaximumSubsetSumWithSimpleRec(arr, index + 1));
    }

    private static int findMaximumSubsetSum(int[] array, int counter, boolean bTaken) {
        if (counter >= array.length) {
            return 0;
        } else if (!bTaken) {
            return (array[counter] > 0 ? array[counter] : 0) +
                    findMaximumSubsetSum(array, counter + 1, true);
        } else {
            int notTakenValue = findMaximumSubsetSum(array, counter + 1, false);
            int takenValue = findMaximumSubsetSum(array, counter + 1, true);
            return Math.max(notTakenValue, takenValue + array[counter]);
        }
    }
}
