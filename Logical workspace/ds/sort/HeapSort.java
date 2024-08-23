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
        for (int unsortedIndex = array.length - 1; unsortedIndex > 0; unsortedIndex--) {
            heapify(array, unsortedIndex);
            swap(array, 0, unsortedIndex);
        }
    }

    // To build a max heap from a subtree rooted at the given index
    private static void heapify(int[] array, int unsortedIndex) {
        for (int currentIndex = unsortedIndex; currentIndex > 0; currentIndex--) {
            int rootIndex = (currentIndex - 1) / 2;
            if(array[currentIndex] > array[rootIndex]) {
                swap(array, rootIndex, currentIndex);

                heapifySubTree(array, currentIndex, unsortedIndex);
            }
        }
    }

    private static void heapifySubTree(int[] array, int rootIndex, int unsortedIndex) {

        int leftIndex = (rootIndex * 2) + 1;
        int rightIndex = (rootIndex * 2) + 2;

        if(leftIndex < unsortedIndex && array[rootIndex] < array[leftIndex]) {
            swap(array, rootIndex, leftIndex);
            heapifySubTree(array, leftIndex, unsortedIndex);
        }

        if(rightIndex < unsortedIndex && array[rootIndex] < array[rightIndex]) {
            swap(array, rootIndex, rightIndex);
            heapifySubTree(array, rightIndex, unsortedIndex);
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

//    // Main function to perform Heap Sort
//    public static void heapSort(int[] array) {
//        int length = array.length;
//
//        // Build a max heap
//        //length / 2 - 1: This is the index of the last non-leaf node.
//        for (int index = (length / 2) - 1; index >= 0; index--) {
//            buildMaxHeap(array, length, index);
//        }
//
//        // Extract elements from heap one by one
//        for (int lastIndex = length - 1; lastIndex > 0; lastIndex--) {
//            // Move the current root to the end
//            swap(array, 0, lastIndex);
//
//            // Call buildMaxHeap on the reduced heap
//            buildMaxHeap(array, lastIndex, 0);
//        }
//    }


//    // To build a max heap from a subtree rooted at the given index
//    private static void buildMaxHeap(int[] array, int heapSize, int rootIndex) {
//        int largestIndex = rootIndex; // Initialize largest as root
//        int leftChildIndex = 2 * rootIndex + 1; // left = 2*rootIndex + 1
//        int rightChildIndex = 2 * rootIndex + 2; // right = 2*rootIndex + 2
//
//        // See if left child exists and is greater than root
//        if (leftChildIndex < heapSize && array[leftChildIndex] > array[largestIndex]) {
//            largestIndex = leftChildIndex;
//        }
//
//        // See if right child exists and is greater than root
//        if (rightChildIndex < heapSize && array[rightChildIndex] > array[largestIndex]) {
//            largestIndex = rightChildIndex;
//        }
//
//        // Change root if needed
//        if (largestIndex != rootIndex) {
//            swap(array, rootIndex, largestIndex);
//
//            // Recursively build max heap for the affected subtree
//            buildMaxHeap(array, heapSize, largestIndex);
//        }
//    }

}
