package ds.sort;

/**
 * Time Complexity: O(nlogn)
 * Space Complexity: O(1)
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{12, 11, 13, 5, 6, 7};
        System.out.print("Unsorted array:");
        printArray(array);

        doHeapSortUsingMaxHeap(array);

        System.out.print("\nSorted array:");
        printArray(array);



        array = new int[]{10, 20, 15, 30, 40, 50, 25, 5};
        System.out.print("Unsorted array:");
        printArray(array);

        doHeapSortUsingMaxHeap(array);

        System.out.print("\nSorted array:");
        printArray(array);
    }

    private static void doHeapSortUsingMaxHeap(int[] array) {
        int n = array.length;

        // Step 1: Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int endIndex = array.length - 1; endIndex > 0; endIndex--) {
            swap(array, 0, endIndex);  // Swap the root (maximum element) with the last element.
            heapify(array, endIndex, 0);
        }
    }

    // To build a max heap from a subtree rooted at the given index
    private static void heapify(int[] array, int endIndex, int rootIndex) {
        //Assume the root is the largest initially
        int largestPos = rootIndex;

        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        if (leftChild < endIndex  &&        // If the left child exists
                array[leftChild] > array[largestPos]) {
            largestPos = leftChild;
        }

        if (rightChild < endIndex  &&       // If the right child exists
                array[rightChild] > array[largestPos]) {
            largestPos = rightChild;
        }

        if (largestPos != rootIndex) {
            swap(array, rootIndex, largestPos);
            heapify(array, endIndex , largestPos);
        }
    }

    // A utility function to swap two elements
    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    private static void printArray(int[] input) {
        for (int counter = 0 ; counter < input.length; counter++) {
            System.out.print((counter == 0 ? "[" : "") +
                    input[counter] +
                    (counter < input.length - 1 ? ", " : "]\n"));
        }
    }

}
