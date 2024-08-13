package dynamic_programming;

public class CanTransformValidation {
    public static void main(String[] args) {
        String first = "AbcDE";
        String second = "ABDE";
        System.out.printf("Can Transform %s to %s: %s - %s\n\n",
                first, second, canTransform(first, second),
                canTransformWithIteration(first, second));

        first = "AbcDE";
        second = "AFDE";
        System.out.printf("Can Transform %s to %s: %s-%s\n\n",
                first, second, canTransform(first, second),
                canTransformWithIteration(first, second));

        first = "daBcd";
        second = "ABC";
        System.out.printf("Can Transform %s to %s: %s - %s\n\n",
                first, second, canTransform(first, second),
                canTransformWithIteration(first, second));

        first = "KXzQ";
        second = "k";
        System.out.printf("Can Transform %s to %s: %s - %s\n\n",
                first, second, canTransform(first, second),
                canTransformWithIteration(first, second));

    }

    private static String canTransformWithIteration(String first, String second) {
        char[] firstCharBuf = first.toCharArray();
        char[] secondCharBuff = second.toCharArray();

        int secondPos = 0;

        System.out.println("secondCharBuff.length: " + secondCharBuff.length);

        for (char ch : firstCharBuf) {
            if(secondPos < secondCharBuff.length &&
                    (ch == secondCharBuff[secondPos] ||
                    Character.toUpperCase(ch) == secondCharBuff[secondPos])) {
                secondPos++;
            }
        }

        if (secondPos == secondCharBuff.length) {
            return "YES";
        }

        return "NO";
    }

    private static String canTransform(String first, String second) {
        int firstLength = first.length();
        int secondLength = second.length();
        boolean[][] dp = new boolean[firstLength + 1][secondLength + 1];

        // Initialization: empty string matches with empty string
        dp[0][0] = true;

        // Fill the first column (b is empty)
        // Initialize first column: first[firstIndex] can match empty second string
        for (int firstIndex = 0; firstIndex <= firstLength; firstIndex++) {
//            dp[firstIndex][0] = dp[firstIndex - 1][0] && Character.isLowerCase(first.charAt(firstIndex - 1));
            dp[firstIndex][0] = true;
        }

        for (int secondIndex = 1; secondIndex <= secondLength; secondIndex++) {
            dp[0][secondIndex] = false;
        }


        // Fill the DP table
        for (int firstIndex = 1; firstIndex <= firstLength; firstIndex++) {
            for (int secondIndex = 1; secondIndex <= secondLength; secondIndex++) {
                char firstChar = first.charAt(firstIndex - 1);
                char secondChar = second.charAt(secondIndex - 1);

                if (Character.toUpperCase(firstChar) == secondChar) {
                    // We can either use the character from first or ignore it
                    dp[firstIndex][secondIndex] = dp[firstIndex - 1][secondIndex - 1] ||
                            (Character.isLowerCase(firstChar) && dp[firstIndex - 1][secondIndex]);
                } else if (Character.isLowerCase(firstChar)) {
                    // Ignore the character from first string
//                    dp[firstIndex][secondIndex] = dp[firstIndex - 1][secondIndex];
                    // Todo J, have to re-look why the isLowerCase is required here
                    dp[firstIndex][secondIndex] = Character.isLowerCase(firstChar) && dp[firstIndex - 1][secondIndex];
                }
            }
        }

        for (int firstIndex = 0; firstIndex <= firstLength; firstIndex++) {
            for (int secondIndex = 0; secondIndex <= secondLength; secondIndex++) {
                System.out.print(dp[firstIndex][secondIndex] + " ");
            }
            System.out.println();
        }

        return dp[firstLength][secondLength] ? "YES" : "NO";
        }
}
