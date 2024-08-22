package dynamic_programming;

import java.util.Arrays;

public class MaximumProductSubArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1, -2, -3, 0, 7, -8, -2};
        System.out.printf("Maximum Sub array product is %d\n\n", maxSubarrayProduct(arr));

        arr = new int[]{6, -3, -10, 0, 2};
        System.out.printf("Maximum Sub array product is %d\n\n", maxSubarrayProduct(arr));

        arr = new int[]{-1, -3, -10, 0, 60};
        System.out.printf("Maximum Sub array product is %d\n\n", maxSubarrayProduct(arr));

        arr = new int[]{-1, 0, -10, -7, 0, 60};
        System.out.printf("Maximum Sub array product is %d\n\n", maxSubarrayProduct(arr));

        arr = new int[]{80, 0, -10, -7, 0, 60};
        System.out.printf("Maximum Sub array product is %d\n\n", maxSubarrayProduct(arr));
    }

    static int maxSubarrayProduct1(int[] arr) {
        int nLength = arr.length;
        int maxProduct = arr[0];
        int minProduct = arr[0];
        int result = arr[0];

        for (int i = 1; i < nLength; i++) {
            // If the current element is negative, swap maxProduct and minProduct
            if (arr[i] < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            // Calculate max and min products
            maxProduct = Math.max(arr[i], maxProduct * arr[i]);
            minProduct = Math.min(arr[i], minProduct * arr[i]);

            // Update result if maxProduct is greater
            result = Math.max(result, maxProduct);
        }

        System.out.println("Maximum subarray product is: " + result);
        return result;
    }


    static int maxSubarrayProduct(int[] arr) {
        System.out.print("The array is: ");
        printArray(arr, 0, arr.length);

        int maxProductValue = 1;
        int maxStart = 0;
        int maxEnd = 0;

        int curProductValue = 1;
        int curStartPoint = 0;
        int curEndPoint;

        for(int index = 0; index < arr.length; ++index) {
            if (curProductValue == 0) {
                curProductValue = 1;
                curStartPoint = index;
            }

            curProductValue *= arr[index];
            curEndPoint = index;
            if (maxProductValue < curProductValue) {
                maxStart = curStartPoint;
                maxEnd = curEndPoint;
                maxProductValue = curProductValue;
            }
        }

        System.out.print("The sub array is: ");
        printArray(arr, maxStart, maxEnd + 1);

        return maxProductValue;
    }

    private static void printArray(int[] arr, int start, int end) {
        for (int counter = start; counter < end; counter++) {
            System.out.print((counter == start ? "[" : "") +
                    arr[counter] +
                    (counter < end - 1 ? ", " : "]\n"));
        }
    }
}
