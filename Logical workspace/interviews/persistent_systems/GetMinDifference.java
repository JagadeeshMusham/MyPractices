package interviews.persistent_systems;

import java.util.Arrays;

public class GetMinDifference {
    public static void main(String[] args) {
        int[] set1 = {3, 5};
        int[] set2 = {6, 7};
        getMinDiff(set1, set2);

        set1 = new int[]{1, 3, 5, 7};
        set2 = new int[]{2, 4, 6, 8};
        getMinDiff(set1, set2);

        set1 = new int[]{10, 20, 15, 5};
        set2 = new int[]{30, 25, 10};
        getMinDiff(set1, set2);

        set1 = new int[]{1, 2, 3};
        set2 = new int[]{7, 8, 9};
        getMinDiff(set1, set2);

    }

    private static void getMinDiff(int[] set1, int[] set2) {
        displayArray(set1);
        displayArray(set2);

        Arrays.sort(set1);
        Arrays.sort(set2);

        int minDif = Integer.MAX_VALUE;
        int firstValue = 0;
        int secondValue = 0;

        boolean bFirstBreak = false;

        for(int itr1 = 0; itr1 < set1.length; itr1++) {
            for(int itr2 = 0; itr2 < set2.length; itr2++) {
                int curDiff = Math.abs(set1[itr1] - set2[itr2]);
                if(minDif > curDiff) {
                    minDif = curDiff;
                    firstValue = set1[itr1];
                    secondValue = set2[itr2];
                }
                else {
                    if (itr2 == 0) {
                        bFirstBreak = true;
                    }

                    break;
                }
            }

            if (bFirstBreak) {
                break;
            }
        }

        System.out.printf("The min difference between %d and %d is: %d\n\n",
                firstValue, secondValue, minDif);

    }

    private static void displayArray(int[] set) {
        System.out.print("[");

        for(int counter = 0; counter < set.length; counter++) {
            System.out.print(set[counter] +
                    (counter == (set.length - 1) ? "]\n" : ", "));
        }
    }
}
