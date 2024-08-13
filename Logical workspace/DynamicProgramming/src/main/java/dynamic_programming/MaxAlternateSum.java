package dynamic_programming;

public class MaxAlternateSum {
    public static void main(String args[]) {
        int arr1[] = {-2, 1, 3, -4, 5};
        printArray(arr1);
        System.out.println("The max adjucent sum value is: " + getMaxAlternateSum(arr1));

        int arr2[] = {-2, -1, -3};
        printArray(arr2);
        System.out.println("The max adjucent sum value is: " + getMaxAlternateSum(arr2));

        int arr3[] = {3, 7, 4, 6, 5};
        printArray(arr3);
        System.out.println("The max adjucent sum value is: " + getMaxAlternateSum(arr3));
    }

    private static void printArray(int[] arr) {
        System.out.print("The array is: [");
        for (int counter = 0; counter < arr.length; counter++) {
            System.out.print(arr[counter] + ", ");
        }
        System.out.print("\b\b]\n");
    }

    private static int getMaxAlternateSum(int[] arr) {
        int maxValue = 0;
        int arrLength = arr.length;

        for (int counter = 0; counter < arrLength; counter++) {
            int sumValue = arr[counter];
            for (int subCounter = counter+2; subCounter < arrLength; subCounter += 2) {
                sumValue += arr[subCounter];
                maxValue = Math.max(maxValue, sumValue);
            }
        }
        return maxValue;
    }
}
