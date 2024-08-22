package dynamic_programming;

public class MaxAlternateSum {

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, 3, -4, 5};
        printArray(arr);
        int[] maxValue = new int[1];
        getMaxAlternateSumUsingRec(arr, 0, maxValue);
        System.out.printf("The max alternate sum value is: %d - %d\n\n", getMaxAlternateSum(arr), maxValue[0]);

        arr = new int[]{-2, -1, -3};
        printArray(arr);
        maxValue[0] = 0;
        getMaxAlternateSumUsingRec(arr, 0, maxValue);
        System.out.printf("The max alternate sum value is: %d - %d\n\n", getMaxAlternateSum(arr), maxValue[0]);

        arr = new int[]{3, 7, 4, 6, 5};
        printArray(arr);
        maxValue[0] = 0;
        getMaxAlternateSumUsingRec(arr, 0, maxValue);
        System.out.printf("The max alternate sum value is: %d - %d\n\n", getMaxAlternateSum(arr), maxValue[0]);
    }

    private static void printArray(int[] arr) {
        System.out.print("The array is: [");

        for(int counter = 0; counter < arr.length; ++counter) {
            System.out.print(arr[counter] + ", ");
        }

        System.out.print("\b\b]\n");
    }

    private static int getMaxAlternateSumUsingRec(int[] arr, int index, int[] maxValue) {
        if (index >= arr.length) {
            return 0;
        }

        int max = Math.max(getMaxAlternateSumUsingRec(arr, index + 1, maxValue),
                arr[index] + getMaxAlternateSumUsingRec(arr, index+2, maxValue));

        maxValue[0] = Math.max(max, maxValue[0]);

        return max;
    }

    private static int getMaxAlternateSum(int[] arr) {
        int maxValue = 0;
        int arrLength = arr.length;

        for(int counter = 0; counter < arrLength; ++counter) {
            int sumValue = arr[counter];

            for(int subCounter = counter + 2; subCounter < arrLength; subCounter += 2) {
                sumValue += arr[subCounter];
                maxValue = Math.max(maxValue, sumValue);
            }
        }

        return maxValue;
    }
}
