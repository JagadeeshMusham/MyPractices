package dynamic_programming.LCSProbs;

public class _25_MinimumNoOfDeletionAndInsertion2ConvertFirst2Second {

    public static void main(String[] args) {
        String firstString = "abcdefgh";
        String secondString = "ardbyfuv";
        runMDICFS(firstString, secondString);

        firstString = "ABCBDAB";
        secondString = "BDCAB";
        runMDICFS(firstString, secondString);

        firstString = "AGGTAB";
        secondString = "GXTXAYB";
        runMDICFS(firstString, secondString);
    }

    private static void runMDICFS(String firstString, String secondString) {
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();
        int nLCS = getLCSWithDynamicProgram(first, second);
        System.out.printf("%d minimum insertion %d minimum deletions are required to convert " +
                "\n%s - %d to \n%s - %d\nLCS count: %d\n\n", first.length - nLCS, second.length - nLCS,
                firstString, firstString.length(), secondString, secondString.length(), nLCS);
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
