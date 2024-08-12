package DynamicProgramming.MCM;

public class _36_PalindromePartitioning {
    public static void main(String[] args) {
        String str = "nitinm";
        runPalindromePartitioning(str);

        str = "abcbm";
        runPalindromePartitioning(str);

        str = "abccbafg";
        runPalindromePartitioning(str);

        str = "abccfg";
        runPalindromePartitioning(str);

        str = "abac";
        runPalindromePartitioning(str);
    }

    private static void runPalindromePartitioning(String input)
    {
        int result = minPalindromePartition(input, 0, input.length() - 1);
        int result1 = minPalindromePartition1(input, 0, input.length() - 1);
        int result2 = minPalindromePartition2(input, 0, input.length() - 1);
        System.out.println("Minimum cuts needed: " + result + " " + result1 + " " + result2);
    }

    //Its general method which I thought
    private static int minPalindromePartition2(String str, int left, int right) {
        if (right <= left)
            return 0;

        if(isPalindrome(str, left, right))
            return 0;

        return 1+ Math.min(minPalindromePartition2(str, left + 1, right),
                minPalindromePartition2(str, left, right-1));
    }

    // This is which I got from youtube
    private static int minPalindromePartition1(String str, int left, int right) {
        // Base case: if the string is empty or a palindrome, no cuts are needed
        if (left >= right || isPalindrome(str, left, right)) {
            return 0;
        }

        int minCuts = Integer.MAX_VALUE;

        // Try all possible partitions
        for (int i = left; i < right; i++) {
            int cuts = 1 + minPalindromePartition1(str, left, i) +
                    minPalindromePartition1(str, i + 1, right);
            minCuts = Math.min(minCuts, cuts);
        }

        return minCuts;
    }

    // This is what we got from ChatGPT
    private static int minPalindromePartition(String str, int left, int right) {
        // Base case: if the string is empty or a palindrome, no cuts are needed
        if (left >= right || isPalindrome(str, left, right)) {
            return 0;
        }

        int minCuts = Integer.MAX_VALUE;

        // Try all possible partitions
        for (int i = left; i < right; i++) {
            if (isPalindrome(str, left, i)) {
                int cuts = 1 + minPalindromePartition(str, i + 1, right);
                minCuts = Math.min(minCuts, cuts);
            }
        }

        return minCuts;
    }

    // Helper function to check if a substring is a palindrome
    private static boolean isPalindrome(String str, int left, int right) {
        String tmp = str;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
