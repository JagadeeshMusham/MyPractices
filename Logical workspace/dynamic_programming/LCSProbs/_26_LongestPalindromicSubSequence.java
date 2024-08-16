package dynamic_programming.LCSProbs;

public class _26_LongestPalindromicSubSequence {

    public static void main(String[] args) {
        StringBuilder firstString = new StringBuilder("ABCBDAB");
        runLPSS(firstString);

        firstString.setLength(0);
        firstString.append("AGGERTEGAB");
        runLPSS(firstString);

        firstString.setLength(0);
        firstString.append("BBABCBCAB");
        runLPSS(firstString);
    }

    private static void runLPSS(StringBuilder string) {
        String firstString = string.toString();
        String secondString = string.reverse().toString();
        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();
        int nLCS = getLCSWithDynamicProgram(first, second);
        System.out.println("getLPSSWithRec: " + getLPSSWithRec(first, 0, first.length - 1));
        System.out.printf("Length of Longest Palindromic Subsequence: %d from\n%s - %d to \n%s - %d\nLCS count: %d\n\n", nLCS, firstString, firstString.length(), secondString, secondString.length(), nLCS);
    }

    private static int getLPSSWithRec(char[] first, int left, int right) {
        if (left > right) {
            return 0;
        } else if (left == right) {
            return 1;
        } else {
            return first[left] == first[right] ? 2 + getLPSSWithRec(first, left + 1, right - 1) : Math.max(getLPSSWithRec(first, left, right - 1), getLPSSWithRec(first, left + 1, right));
        }
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
