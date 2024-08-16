package dynamic_programming.LCSProbs;

public class _22_LongestCommonSubString {

    public static void main(String[] args) {
        String firstString = "abcdefgh";
        String secondString = "ardbyfuv";
        runLCSubString(firstString, secondString);
        firstString = "ABCBDABC";
        secondString = "BDCABD";
        runLCSubString(firstString, secondString);
        firstString = "AGGTAB";
        secondString = "GXTXAYB";
        runLCSubString(firstString, secondString);
    }

    private static void runLCSubString(String firstString, String secondString) {
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();
        System.out.printf("The longest Common Sub Sequence from \n%s\n%s\n%d \n\n", firstString, secondString, getLCSubString(first, first.length, second, second.length));
    }

    private static int getLCSubString(char[] first, int firstLength, char[] second, int secondLength) {
        int[][] dp = new int[firstLength + 1][secondLength + 1];

        int firstIndex;
        for(firstIndex = 0; firstIndex <= firstLength; ++firstIndex) {
            dp[firstIndex][0] = 0;
        }

        int secondIndex;
        for(secondIndex = 1; secondIndex <= secondLength; ++secondIndex) {
            dp[0][secondIndex] = 0;
        }

        int maxLCSubStringValue = 0;

        for(firstIndex = 1; firstIndex <= firstLength; ++firstIndex) {
            for(secondIndex = 1; secondIndex <= secondLength; ++secondIndex) {
                if (first[firstIndex - 1] == second[secondIndex - 1]) {
                    dp[firstIndex][secondIndex] = dp[firstIndex - 1][secondIndex - 1] + 1;
                    maxLCSubStringValue = Math.max(maxLCSubStringValue, dp[firstIndex][secondIndex]);
                } else {
                    dp[firstIndex][secondIndex] = 0;
                }
            }
        }

        for(firstIndex = 0; firstIndex <= firstLength; ++firstIndex) {
            for(secondIndex = 0; secondIndex <= secondLength; ++secondIndex) {
                System.out.print(dp[firstIndex][secondIndex] + "\t");
            }

            System.out.println();
        }

        return maxLCSubStringValue;
    }
}
