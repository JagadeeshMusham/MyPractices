package DynamicProgramming;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MaximumProductSubArray {

    public static void main(String[] args) {
        int arr[] = { 1, -2, -3, 0, 7, -8, -2 };
        System.out.println("Maximum Sub array product is "
                + maxSubarrayProduct(arr));

        int arr1[] = {6, -3, -10, 0, 2};
        System.out.println("Maximum Sub array product is "
                + maxSubarrayProduct(arr1));

        int arr2[] = {-1, -3, -10, 0, 60};
        System.out.println("Maximum Sub array product is "
                + maxSubarrayProduct(arr2));

        int arr3[] = {-1, 0, -10, -7, 0, 60};
        System.out.println(arr3);
        System.out.println("Maximum Sub array product is "
                + maxSubarrayProduct(arr3));

        int arr4[] = {80, 0, -10, -7, 0, 60};
        System.out.println(arr4);
        System.out.println("Maximum Sub array product is "
                + maxSubarrayProduct(arr4));
    }

    static int maxSubarrayProduct(int arr[])
    {
        int nLength = arr.length;
        int maxSum = arr[0];
        int calculateMaxSum = 1;

        System.out.print("\n\nThe array is: [");
        for (int n : arr) {
            System.out.print(n + ", ");
        }
        System.out.println("\b\b]");

        int maxStart = 0;
        int maxEnd = 0;

        int startPoint = 0;
        int endingPoint = 0;

        for (int counter = 0; counter < nLength; counter++) {
            if (calculateMaxSum == 0) {
                calculateMaxSum = 1;

                startPoint = counter;
                endingPoint = counter;
            }
            calculateMaxSum *= arr[counter];
            endingPoint = counter;

            if (calculateMaxSum > maxSum) {
                maxStart = startPoint;
                maxEnd = endingPoint;
                maxSum = calculateMaxSum;
            }
        }

        System.out.print("The sub array to get maximum sum of product is: [");
        for (int counter = maxStart; counter <= maxEnd; counter++) {
            System.out.print(arr[counter] + ", ");
        }
        System.out.println("\b\b]");

        return maxSum;
    }
}