package dynamic_programming.LCSProbs;

import java.util.Arrays;

/**
 * Shortest common super sequence means =   Length of First +
 *                                          Length of Second -
 *                                          Length of LCS
 */

public class _24_ShortestCommonSuperSequence {

    public static void main(String[] args) {
        String firstString = "abcdefgh";
        String secondString = "ardbyfuv";
        runSCSS(firstString, secondString);

        firstString = "ABCBDAB";
        secondString = "BDCAB";
        runSCSS(firstString, secondString);

        firstString = "AGGTAB";
        secondString = "GXTXAYB";
        runSCSS(firstString, secondString);
    }

    private static void runSCSS(String firstString, String secondString) {
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();
        int[][] mem = new int[first.length + 1][second.length + 1];

        for(int counter = 0; counter <= first.length; ++counter) {
            Arrays.fill(mem[counter], -1);
        }

        System.out.printf("The shorted common Super sequence from \n%s \n%s\n %d\n\n",
                firstString, secondString, getShortestCommonSuperSequence(first, second));
    }

    private static int getShortestCommonSuperSequence(char[] first, char[] second) {
        return first.length + second.length - getLCSWithDynamicProgram(first, second);
    }

    private static int getLCSWithDynamicProgram(char[] first, char[] second) {
        int[][] dp = new int[first.length + 1][second.length + 1];

        int rowIndex;
        for(rowIndex = 0; rowIndex <= first.length; ++rowIndex) {
            dp[rowIndex][0] = 0;
        }

        int colIndex;
        for(colIndex = 1; colIndex <= second.length; ++colIndex) {
            dp[0][colIndex] = 0;
        }

        for(rowIndex = 1; rowIndex <= first.length; ++rowIndex) {
            for(colIndex = 1; colIndex <= second.length; ++colIndex) {
                if (first[rowIndex - 1] == second[colIndex - 1]) {
                    dp[rowIndex][colIndex] = dp[rowIndex - 1][colIndex - 1] + 1;
                } else {
                    dp[rowIndex][colIndex] = Math.max(dp[rowIndex - 1][colIndex], dp[rowIndex][colIndex - 1]);
                }
            }
        }

        return dp[first.length][second.length];
    }
}
