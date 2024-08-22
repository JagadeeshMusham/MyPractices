package dynamic_programming;

public class MaxNonAdjacentSum {

    public static void main(String[] args) {
        int[] array = new int[]{-2, 1, 3, -4, 5};
        printArray(array);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0));

        array = new int[]{-2, -1, -3};
        printArray(array);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0));

        array = new int[]{3, 7, 4, 6, 5};
        printArray(array);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0));

        array = new int[]{3, 5, -7, 8, 10};
        printArray(array);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0));

        array = new int[]{2, 1, 5, 8, 4};
        printArray(array);
        System.out.println("The max non adjucent sum value is: " +
                findMaxNonAdjacentSum(array, 0));
    }

    private static void printArray(int[] arr) {
        System.out.print("\nThe array is: [");

        for (int counter = 0; counter < arr.length; ++counter) {
            System.out.print(arr[counter] + (counter < arr.length - 1 ?", " : "]\n"));
        }

    }

    private static int findMaxNonAdjacentSum(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }

        return Math.max(array[index] + findMaxNonAdjacentSum(array, index + 2),
                findMaxNonAdjacentSum(array, index + 1));
    }
}
