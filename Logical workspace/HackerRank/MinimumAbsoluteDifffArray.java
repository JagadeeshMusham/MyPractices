package HackerRank;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 */
public class MinimumAbsoluteDifffArray {
    public static void main(String[] args) throws IOException {

        List<Integer> arrList = new ArrayList<>(10);
        arrList.add(2);
        arrList.add(-7);
        arrList.add(0);

        int result = Result.minimumAbsoluteDifference(arrList);

        System.out.println(result);

    }
}

class Result {

    /*
     * Complete the 'minimumAbsoluteDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        Collections.sort(arr);
        int minAbsoluteValue = Integer.MAX_VALUE;

        for(int counter = 0; counter < arr.size() - 1; counter++) {
            minAbsoluteValue = Math.min(minAbsoluteValue, Math.abs(arr.get(counter) - arr.get(counter+1)));
        }

        return minAbsoluteValue;

    }

}

