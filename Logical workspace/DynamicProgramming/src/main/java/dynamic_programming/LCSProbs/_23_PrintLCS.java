package dynamic_programming.LCSProbs;

import java.util.Arrays;

/**
 * To print the Longest Common Subsequence (LCS) characters,
 * you can use dynamic programming to first construct the LCS length table and
 * then backtrack through the table to find and print the LCS characters.
 * Following are the steps to do this after dp[][] calculation:
 *
 * Step1: Initialize rowIndex to first.length and colIndex to second.length.
 * Step2: Create an empty StringBuilder to build the LCS.
 * Step3: While rowIndex > 0 and colIndex > 0:
 *
 *     If first[rowIndex-1] == second[colIndex-1]:
 *         Append first[rowIndex-1] to StringBuilder.
 *         Move diagonally: rowIndex-- and colIndex--.
 *     Else if dp[rowIndex-1][colIndex] > dp[rowIndex][colIndex-1]:
 *         Move up: rowIndex--.
 *     Else:
 *         Move left: colIndex--.
 *
 * Step4: Reverse the StringBuilder and return its string representation.
 */

public class _23_PrintLCS {
    public static void main(String[] args) {
        String firstString = "abcdefgh";
        String secondString = "ardbyfuv";
        runLCS(firstString, secondString);

        firstString = "ABCBDAB";
        secondString = "BDCAB";
        runLCS(firstString, secondString);

        firstString = "AGGTAB";
        secondString = "GXTXAYB";
        runLCS(firstString, secondString);

        firstString = "acbcf";
        secondString = "abcdaf";
        runLCS(firstString, secondString);
    }

    private static void runLCS(String firstString, String secondString) {
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();

        int[][] mem = new int[first.length+1][second.length+1];
        for (int counter = 0; counter <= first.length; counter++) {
            Arrays.fill(mem[counter], -1);
        }

        System.out.printf("The longest Common Sub Sequence from \n%s \n%s\n %d - %d - %s\n\n",
                firstString, secondString,
                getLCSwithRecursive(first, first.length, second, second.length),
                getLCSwithRecursiveWithMem(first, first.length, second, second.length, mem),
                printLCS(first, second));
    }

    private static String printLCS(char[] first, char[] second) {
        int[][] dp = getLCSWithDynamicProgram(first, second);

        StringBuilder lcsString = new StringBuilder(first.length);
        int rowIndex = first.length;
        int colIndex = second.length;
        while(rowIndex > 0 && colIndex > 0) {
            if(first[rowIndex-1] == second[colIndex-1]) {
                lcsString.append(first[rowIndex-1]);
                rowIndex--;
                colIndex--;
            }
            else if(dp[rowIndex-1][colIndex] > dp[rowIndex][colIndex-1]) {
                rowIndex--;
            }
            else {
                colIndex--;
            }
        }

        return lcsString.reverse().toString();
    }

    private static int[][] getLCSWithDynamicProgram(char[] first, char[] second) {
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

        return dp;
    }

    private static int getLCSwithRecursiveWithMem(char[] first, int firstLength, char[] second, int secondLength, int[][] mem) {
        if(firstLength <= 0 || secondLength <= 0) {
            return 0;
        }

        if(mem[firstLength][secondLength] != -1) {
            return mem[firstLength][secondLength];
        }

        if(first[firstLength-1] == second[secondLength-1]) {
            mem[firstLength][secondLength] = 1+getLCSwithRecursive(first, firstLength - 1, second, secondLength - 1);
        }
        else {
            mem[firstLength][secondLength] = Math.max(getLCSwithRecursive(first, firstLength, second, secondLength - 1),
                    getLCSwithRecursive(first, firstLength - 1, second, secondLength));
        }

        return mem[firstLength][secondLength];
    }

    private static int getLCSwithRecursive(char[] first, int firstLength,
                                           char[] second, int secondLength) {
        if(firstLength <= 0 || secondLength <= 0) {
            return 0;
        }

        if(first[firstLength-1] == second[secondLength-1]) {
            return 1+getLCSwithRecursive(first, firstLength - 1, second, secondLength - 1);
        }
        else {
            return Math.max(getLCSwithRecursive(first, firstLength, second, secondLength - 1),
                    getLCSwithRecursive(first, firstLength - 1, second, secondLength));
        }
    }

}
