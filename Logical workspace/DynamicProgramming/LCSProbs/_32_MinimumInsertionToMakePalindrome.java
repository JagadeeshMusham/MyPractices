package DynamicProgramming.LCSProbs;

/**
 * This is similar to MinDeletion2MakePalindromeFromString
 *
 * If we delete two characters from a string to make it a palindrome, then we
 * only need to add two characters to the original string to make it a palindrome.
 *
 * This revision provides clarity by explicitly stating the relationship between
 * the deletion and addition of characters concerning palindromes.
 */

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
        char[] first = string.toString().toCharArray();
        char[] second = string.reverse().toString().toCharArray();

        int nLCS = getLongestCommonSubSequenceWithDP(first, second);

        System.out.printf("The LCS count: %d, total char count: %d\n", nLCS, first.length);
        System.out.printf("Have to add minimum number of character " +
                "from %s to make palindrome: %d\n\n", string.toString(), first.length-nLCS);
    }

    private static int getLongestCommonSubSequenceWithDP(char[] first, char[] second) {
        int[][] dp = new int[first.length+1][second.length+1];

        int firstIndex = 0;
        for(; firstIndex <= first.length; firstIndex++) {
            dp[firstIndex][0] = 0;
        }

        int secondIndex = 1;
        for(; secondIndex <= second.length; secondIndex++) {
            dp[0][secondIndex] = 0;
        }

        for(firstIndex = 1; firstIndex <= first.length; firstIndex++) {
            for(secondIndex = 1; secondIndex <= second.length; secondIndex++){
                if(first[firstIndex-1] == second[secondIndex-1] ) {
                    dp[firstIndex][secondIndex] = 1+dp[firstIndex-1][secondIndex-1];
                }
                else {
                    dp[firstIndex][secondIndex] = Math.max(dp[firstIndex-1][secondIndex],
                            dp[firstIndex][secondIndex-1]);
                }
            }
        }

        return dp[first.length][second.length];
    }

}
