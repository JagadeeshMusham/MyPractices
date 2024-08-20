package dynamic_programming.LCSProbs;

public class _30_LongestRepeatingSubSequence {

    public static void main(String[] args) {
        String string = "aabebcdd";

        System.out.printf("The longest Repeating SubSequence:%d - %d from \n%s\n\n",
                getLRSSWithDP(string),
                getLRSWithRec(string, 0, 0), string);
    }

    private static int getLRSWithRec(String string, int firstPos, int secondPos) {
        if (string == null || firstPos >= string.length() || secondPos >= string.length()) {
            return 0;
        }

        if(string.charAt(firstPos) == string.charAt(secondPos) &&
                firstPos != secondPos) {
            return 1 + getLRSWithRec(string, firstPos + 1, secondPos + 1);
        }
        else {
            return Math.max(getLRSWithRec(string, firstPos + 1, secondPos),
                    getLRSWithRec(string, firstPos, secondPos + 1));
        }
    }

    private static int getLRSSWithDP(String string) {
        int[][] dp = new int[string.length() + 1][string.length() + 1];

        int firstIndex;
        for(firstIndex = 0; firstIndex <= string.length(); ++firstIndex) {
            dp[firstIndex][0] = 0;
        }

        int secondIndex;
        for(secondIndex = 1; secondIndex <= string.length(); ++secondIndex) {
            dp[0][secondIndex] = 0;
        }

        for(firstIndex = 1; firstIndex <= string.length(); ++firstIndex) {
            for(secondIndex = 1; secondIndex <= string.length(); ++secondIndex) {
                if (string.charAt(firstIndex - 1) == string.charAt(secondIndex - 1) && firstIndex != secondIndex) {
                    dp[firstIndex][secondIndex] = 1 + dp[firstIndex - 1][secondIndex - 1];
                } else {
                    dp[firstIndex][secondIndex] = Math.max(dp[firstIndex - 1][secondIndex], dp[firstIndex][secondIndex - 1]);
                }
            }
        }

        return dp[string.length()][string.length()];
    }
}
