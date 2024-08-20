package dynamic_programming.LCSProbs;

public class _31_SequencePatternMatching {

    public static void main(String[] args) {
        String firstString = "AXY";
        String secondString = "ADXCPY";
        runSPM(firstString, secondString);

        firstString = "ASXY";
        secondString = "ADXCPYS";
        runSPM(firstString, secondString);

        firstString = "AEFG";
        secondString = "ABCDEFGH";
        runSPM(firstString, secondString);
    }

    private static void runSPM(String firstString, String secondString) {
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();
        int[][] dp = getDPTable(first, second);
        System.out.printf("The Sequence Pattern Matched with first %s and second %s: %b\n\n",
                firstString, secondString, first.length == dp[first.length][second.length]);
    }

    private static int[][] getDPTable(char[] first, char[] second) {
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
                    dp[rowIndex][colIndex] = 1 + dp[rowIndex - 1][colIndex - 1];
                } else {
                    dp[rowIndex][colIndex] = Math.max(dp[rowIndex - 1][colIndex], dp[rowIndex][colIndex - 1]);
                }
            }
        }

        return dp;
    }
}
