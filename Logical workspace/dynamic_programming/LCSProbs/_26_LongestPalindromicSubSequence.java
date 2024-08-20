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

        System.out.printf("Length of Longest Palindromic Subsequence: %d - %d from\n%s - %d to \n%s - %d\n\n",
                getLCSWithDynamicProgram(firstString, secondString),
                getLPSSWithRec(firstString, 0, firstString.length() - 1),
                firstString, firstString.length(), secondString, secondString.length());
    }

    private static int getLPSSWithRec(String first, int left, int right) {
        if (left > right) {
            return 0;
        } else if (left == right) {
            return 1;
        } else {
            return first.charAt(left) == first.charAt(right) ?
                    2 + getLPSSWithRec(first, left + 1, right - 1) :
                    Math.max(getLPSSWithRec(first, left, right - 1), getLPSSWithRec(first, left + 1, right));
        }
    }

    private static int getLCSWithDynamicProgram(String first, String second) {
        int[][] dp = new int[first.length() + 1][second.length() + 1];

        int rowIndex;
        for(rowIndex = 0; rowIndex <= first.length(); ++rowIndex) {
            dp[rowIndex][0] = 0;
        }

        int colIndex;
        for(colIndex = 1; colIndex <= second.length(); ++colIndex) {
            dp[0][colIndex] = 0;
        }

        for(rowIndex = 1; rowIndex <= first.length(); ++rowIndex) {
            for(colIndex = 1; colIndex <= second.length(); ++colIndex) {
                if (first.charAt(rowIndex - 1) == second.charAt(colIndex - 1)) {
                    dp[rowIndex][colIndex] = dp[rowIndex - 1][colIndex - 1] + 1;
                } else {
                    dp[rowIndex][colIndex] = Math.max(dp[rowIndex - 1][colIndex], dp[rowIndex][colIndex - 1]);
                }
            }
        }

        return dp[first.length()][second.length()];
    }
}
