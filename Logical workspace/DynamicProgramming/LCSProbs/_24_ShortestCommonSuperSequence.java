package DynamicProgramming.LCSProbs;

import java.util.Arrays;

/**
 * The Shortest Common Supersequence (SCS) of two strings is the shortest possible string that
 * contains both of the input strings as subsequences. In other words, the SCS is the smallest
 * string that you can form such that both original strings appear as a subsequence within it.
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

        int[][] mem = new int[first.length+1][second.length+1];
        for (int counter = 0; counter <= first.length; counter++) {
            Arrays.fill(mem[counter], -1);
        }

        System.out.printf("The shorted common Super sequence from \n%s \n%s\n %d\n\n",
                firstString, secondString,
                getShortedCommonSuperSequence(first, second));
    }

    private static int getShortedCommonSuperSequence(char[] first, char[] second) {
        return first.length + second.length - getLCSWithDynamicProgram(first, second);
    }

    private static int getLCSWithDynamicProgram(char[] first, char[] second) {
        int[][] dp = new int[first.length+1][second.length+1];

        int rowIndex = 0;
        for (; rowIndex <= first.length; rowIndex++) {
            dp[rowIndex][0] = 0;
        }

        int colIndex = 1;
        for (; colIndex <= second.length; colIndex++) {
            dp[0][colIndex] = 0;
        }

        for(rowIndex = 1; rowIndex <= first.length; rowIndex++) {
            for(colIndex = 1; colIndex <= second.length; colIndex++) {
                if(first[rowIndex-1] == second[colIndex-1]) {
                    dp[rowIndex][colIndex] = dp[rowIndex-1][colIndex-1] + 1;
                }
                else {
                    dp[rowIndex][colIndex] = Math.max(dp[rowIndex-1][colIndex], dp[rowIndex][colIndex-1]);
                }
            }
        }

        return dp[first.length][second.length];
    }

}
