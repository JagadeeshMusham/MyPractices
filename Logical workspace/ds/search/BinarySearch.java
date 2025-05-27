package ds.search;

public class BinarySearch {
    public static void main(String [] args) {
        int array[] = {1, 2, 8, 13, 45, 50, 62, 70, 80, 90,95};

        int len = array.length;
        System.out.println(len);

        for (int search : array) {
            doBinarySearch(array, search, 0, len - 1);
        }
    }

    private static void doBinarySearch(int [] array, int search, int start, int end) {
        int mid = (start + end) / 2;

        if (array[mid] == search) {
            System.out.println("Found data: " + search + " at position: " + (mid + 1));
        }
        else if(array[mid] > search) {
            doBinarySearch(array, search, start, mid - 1);
        } else {
            doBinarySearch(array, search, mid + 1, end);
        }
    }
}
