package dynamic_programming.LCSProbs;

public class _32_MinimumInsertionToMakePalindrome {

    public static void main(String[] args) {
        StringBuilder firstString = new StringBuilder("ABCBDAB");
        runMIMPS(firstString);

        firstString.setLength(0);
        firstString.append("AGGERTEGAB");
        runMIMPS(firstString);

        firstString.setLength(0);
        firstString.append("BBABCBCAB");
        runMIMPS(firstString);
    }

    private static void runMIMPS(StringBuilder string) {
        StringBuilder second = new StringBuilder(string.reverse());
        int nLCS = getLongestCommonSubSequenceWithDP(string, second);
        System.out.printf("The LCS count: %d, total char count: %d\n", nLCS, string.length());
        System.out.printf("Have to add minimum number of character from %s to make palindrome: %d\n\n",
                string.toString(), string.length() - nLCS);
    }

    private static int getLongestCommonSubSequenceWithDP(StringBuilder first, StringBuilder second) {
        int[][] dp = new int[first.length() + 1][second.length() + 1];

        int firstIndex;
        for(firstIndex = 0; firstIndex <= first.length(); ++firstIndex) {
            dp[firstIndex][0] = 0;
        }

        int secondIndex;
        for(secondIndex = 1; secondIndex <= second.length(); ++secondIndex) {
            dp[0][secondIndex] = 0;
        }

        for(firstIndex = 1; firstIndex <= first.length(); ++firstIndex) {
            for(secondIndex = 1; secondIndex <= second.length(); ++secondIndex) {
                if (first.charAt(firstIndex - 1) == second.charAt(secondIndex - 1)) {
                    dp[firstIndex][secondIndex] = 1 + dp[firstIndex - 1][secondIndex - 1];
                } else {
                    dp[firstIndex][secondIndex] = Math.max(dp[firstIndex - 1][secondIndex], dp[firstIndex][secondIndex - 1]);
                }
            }
        }

        return dp[first.length()][second.length()];
    }
}
