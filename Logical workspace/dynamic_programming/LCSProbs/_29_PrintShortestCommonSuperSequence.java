package dynamic_programming.LCSProbs;

public class _29_PrintShortestCommonSuperSequence {

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

        firstString = "acbcf";
        secondString = "abcdaf";
        runSCSS(firstString, secondString);
    }

    private static void runSCSS(String firstString, String secondString) {
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();
        System.out.printf("The longest Common SuperSequence from \n%s \n%s\n %s\n\n", firstString, secondString, printSCSS(first, second));
    }

    private static String printSCSS(char[] first, char[] second) {
        int[][] dp = getLCSWithDynamicProgram(first, second);
        StringBuilder lcsString = new StringBuilder(first.length + second.length);
        int rowIndex = first.length;
        int colIndex = second.length;

        while(rowIndex > 0 && colIndex > 0) {
            if (first[rowIndex - 1] == second[colIndex - 1]) {
                lcsString.append(first[rowIndex - 1]);
                --rowIndex;
                --colIndex;
            } else if (dp[rowIndex][colIndex - 1] > dp[rowIndex - 1][colIndex]) {
                lcsString.append(second[colIndex - 1]);
                --colIndex;
            } else {
                lcsString.append(first[rowIndex - 1]);
                --rowIndex;
            }
        }

        if (rowIndex != 0) {
            while(rowIndex > 0) {
                lcsString.append(first[rowIndex - 1]);
                --rowIndex;
            }
        } else {
            while(colIndex > 0) {
                lcsString.append(second[colIndex - 1]);
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
}
