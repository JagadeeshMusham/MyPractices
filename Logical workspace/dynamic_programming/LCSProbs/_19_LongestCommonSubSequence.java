package dynamic_programming.LCSProbs;

import java.util.Arrays;

public class _19_LongestCommonSubSequence {
    public _19_LongestCommonSubSequence() {
    }

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
    }

    private static void runLCS(String firstString, String secondString) {
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();
        int[][] mem = new int[first.length + 1][second.length + 1];

        for(int counter = 0; counter <= first.length; ++counter) {
            Arrays.fill(mem[counter], -1);
        }

        System.out.printf("The longest Common Sub Sequence from %s, %s: %d - %d - %d\n\n",
                firstString, secondString,
                getLCSwithRecursive(first, first.length, second, second.length),
                getLCSwithRecursiveWithMem(first, first.length, second, second.length, mem),
                getLCSwithDynamicProgram(first, second));
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

    private static int getLCSwithDynamicProgram(char[] first,  char[] second) {
        int firstLength = first.length;
        int secondLength = second.length;
        int[][] dp = new int[firstLength + 1][secondLength + 1];

        int rowIndex;
        for(rowIndex = 0; rowIndex <= firstLength; ++rowIndex) {
            dp[rowIndex][0] = 0;
        }

        int colIndex;
        for(colIndex = 1; colIndex <= secondLength; ++colIndex) {
            dp[0][colIndex] = 0;
        }

        for(rowIndex = 1; rowIndex <= firstLength; ++rowIndex) {
            for(colIndex = 1; colIndex <= secondLength; ++colIndex) {
                if (first[rowIndex - 1] == second[colIndex - 1]) {
                    dp[rowIndex][colIndex] = dp[rowIndex - 1][colIndex - 1] + 1;
                } else {
                    dp[rowIndex][colIndex] = Math.max(dp[rowIndex - 1][colIndex], dp[rowIndex][colIndex - 1]);
                }
            }
        }

        return dp[firstLength][secondLength];
    }

    private static int getLCSwithRecursive(char[] first, int firstLength, char[] second, int secondLength) {
        if (firstLength > 0 && secondLength > 0) {
            return first[firstLength - 1] == second[secondLength - 1] ? 1 + getLCSwithRecursive(first, firstLength - 1, second, secondLength - 1) : Math.max(getLCSwithRecursive(first, firstLength, second, secondLength - 1), getLCSwithRecursive(first, firstLength - 1, second, secondLength));
        } else {
            return 0;
        }
    }
}