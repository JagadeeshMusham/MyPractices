package dynamic_programming.LCSProbs;

public class _28_MinDeletion2MakePalindromeFromString {

    public static void main(String[] args) {

        StringBuilder firstString = new StringBuilder("ABCBDAB");
        runMDMPS(firstString);

        firstString.setLength(0);
        firstString.append("AGGERTEGAB");
        runMDMPS(firstString);

        firstString.setLength(0);
        firstString.append("BBABCBCAB");
        runMDMPS(firstString);
    }

    private static void runMDMPS(StringBuilder input) {
        String firstString = input.toString();
        input.reverse();
        int nLCS = getLongestCommonSubSequenceWithDP(firstString, input.toString());
        System.out.printf("The LCS count: %d, total char count: %d\n", nLCS, firstString.length());
        System.out.printf("Have to delete minimum number character from %s to make palindrome: %d\n\n",
                firstString, input.length() - nLCS);
    }

    private static int getLongestCommonSubSequenceWithDP(String first, String second) {
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
