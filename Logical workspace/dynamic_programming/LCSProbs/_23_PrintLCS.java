package dynamic_programming.LCSProbs;

import java.util.Arrays;

/**
 * Print LCS
 * 1. start assigning the max row and col values in DP.
 * 2. Compare col and row characters,
 *      2.a. if it matches print char
 *      2.b if it not matches
 *          move towards max value of row and columns DP values
 * 3. continue step-2 until it reaches to 0th row and column
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
        int[][] mem = new int[first.length + 1][second.length + 1];

        for(int counter = 0; counter <= first.length; ++counter) {
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
            if (first[rowIndex - 1] == second[colIndex - 1]) {
                lcsString.append(first[rowIndex - 1]);
                --rowIndex;
                --colIndex;
            } else if (dp[rowIndex - 1][colIndex] > dp[rowIndex][colIndex - 1]) {
                --rowIndex;
            } else {
                --colIndex;
            }
        }

        return lcsString.reverse().toString();
    }

    private static int[][] getLCSWithDynamicProgram(char[] first, char[] second) {
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

        return dp;
    }

    private static int getLCSwithRecursiveWithMem(char[] first, int firstLength, char[] second, int secondLength, int[][] mem) {
        if (firstLength > 0 && secondLength > 0) {
            if (mem[firstLength][secondLength] != -1) {
                return mem[firstLength][secondLength];
            } else {
                if (first[firstLength - 1] == second[secondLength - 1]) {
                    mem[firstLength][secondLength] = 1 + getLCSwithRecursive(first, firstLength - 1, second, secondLength - 1);
                } else {
                    mem[firstLength][secondLength] = Math.max(getLCSwithRecursive(first, firstLength, second, secondLength - 1), getLCSwithRecursive(first, firstLength - 1, second, secondLength));
                }

                return mem[firstLength][secondLength];
            }
        } else {
            return 0;
        }
    }

    private static int getLCSwithRecursive(char[] first, int firstLength, char[] second, int secondLength) {
        if (firstLength > 0 && secondLength > 0) {
            return first[firstLength - 1] == second[secondLength - 1] ? 1 + getLCSwithRecursive(first, firstLength - 1, second, secondLength - 1) : Math.max(getLCSwithRecursive(first, firstLength, second, secondLength - 1), getLCSwithRecursive(first, firstLength - 1, second, secondLength));
        } else {
            return 0;
        }
    }
}
