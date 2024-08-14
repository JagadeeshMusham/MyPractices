package hacker_rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/angry-children/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 */
public class MaxMin {
    public static void main(String[] args) {
//        int nSize = 10; //The problem stmt defines this
        int subArraySize = 5;
        List<Integer> list = new ArrayList<>();
        list.add(4504);
        list.add(1520);
        list.add(5857);
        list.add(4094);
        list.add(4157);
        list.add(3902);
        list.add(822);
        list.add(6643);
        list.add(2422);
        list.add(7288);
        list.add(8245);
        list.add(9948);
        list.add(2822);
        list.add(1784);
        list.add(7802);
        list.add(3142);
        list.add(9739);
        list.add(5629);
        list.add(5413);
        list.add(7232);

        System.out.println(getMaxMin(subArraySize, list));
    }

    private static int getMaxMin(int k, List<Integer> arr) {
        // Sort the list first
        Collections.sort(arr);

        // Initialize the minimum difference with a large value
        int minDifference = Integer.MAX_VALUE;

        // Slide the window over the sorted list
        for (int counter = 0; counter <= arr.size() - k; counter++) {
            int currentDifference = arr.get(counter + k - 1) - arr.get(counter);
            minDifference = Math.min(minDifference, currentDifference);
        }

        return minDifference;
    }

    private static int getMaxMin1(int k, List<Integer> arr) {
        Collections.sort(arr);

        return arr.get(k - 1) - arr.get(0);
    }
}
