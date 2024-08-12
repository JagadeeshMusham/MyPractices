package DynamicProgramming.LCSProbs;

public class _30_LongestRepeatingSubSequence {
    public static void main(String[] args) {
        String firstString = "aabebcdd";
        getLRSS(firstString);
    }

    private static void getLRSS(String string) {
        char[] first = string.toCharArray();

        int[][] dp = new int[first.length+1][first.length+1];

        int firstIndex = 0;
        for (; firstIndex <= first.length; firstIndex++) {
            dp[firstIndex][0] = 0;
        }

        int secondIndex = 1;
        for(; secondIndex <= first.length; secondIndex++) {
            dp[0][secondIndex] = 0;
        }

        for(firstIndex = 1; firstIndex <= first.length; firstIndex++) {
            for(secondIndex = 1; secondIndex <= first.length; secondIndex++) {
                if(first[firstIndex-1] == first[secondIndex-1] && firstIndex != secondIndex) {
                    dp[firstIndex][secondIndex] = 1 + dp[firstIndex-1][secondIndex-1];
                }
                else {
                    dp[firstIndex][secondIndex] = Math.max(dp[firstIndex-1][secondIndex],
                            dp[firstIndex][secondIndex-1]);
                }
            }
        }

        System.out.printf("The longest Repeating SubSequence from \n%s\n %d\n\n",
                string, dp[first.length][first.length]);
    }
}
