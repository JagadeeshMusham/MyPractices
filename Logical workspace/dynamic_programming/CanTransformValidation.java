package dynamic_programming;

public class CanTransformValidation {

    // [21st August] J, have to re-look the code

    public static void main(String[] args) {
        String first = "AbcDE";
        String second = "ABDE";
        System.out.printf("Can Transform %s to %s: %s - %s\n\n", first, second,
                canTransform(first, second), canTransformWithIteration(first, second));

        first = "AbcDE";
        second = "AFDE";
        System.out.printf("Can Transform %s to %s: %s - %s\n\n", first, second,
                canTransform(first, second), canTransformWithIteration(first, second));

        first = "daBcd";
        second = "ABC";
        System.out.printf("Can Transform %s to %s: %s - %s\n\n", first, second,
                canTransform(first, second), canTransformWithIteration(first, second));

        first = "KXzQ";
        second = "k";
        System.out.printf("Can Transform %s to %s: %s - %s\n\n", first, second,
                canTransform(first, second), canTransformWithIteration(first, second));
    }

    private static String canTransformWithIteration(String first, String second) {
        int secondPosition = 0;

        for (int i = 0; i < first.length(); i++) {
            char ch = first.charAt(i);
            if (secondPosition < second.length() &&
                    (ch == second.charAt(secondPosition) ||
                            Character.toUpperCase(ch) == second.charAt(secondPosition))) {
                secondPosition++;
            }
        }

        return (secondPosition == second.length()) ? "YES" : "NO";
    }

    private static String canTransform(String first, String second) {
        int firstLength = first.length();
        int secondLength = second.length();
        boolean[][] dp = new boolean[firstLength + 1][secondLength + 1];

        int firstIndex;
        for(firstIndex = 0; firstIndex <= firstLength; ++firstIndex) {
            dp[firstIndex][0] = true;
        }

        int secondIndex;
        for(secondIndex = 1; secondIndex <= secondLength; ++secondIndex) {
            dp[0][secondIndex] = false;
        }

        for(firstIndex = 1; firstIndex <= firstLength; ++firstIndex) {
            for(secondIndex = 1; secondIndex <= secondLength; ++secondIndex) {
                char firstChar = first.charAt(firstIndex - 1);
                char secondChar = second.charAt(secondIndex - 1);
                if (Character.toUpperCase(firstChar) == secondChar) {
                    dp[firstIndex][secondIndex] = dp[firstIndex - 1][secondIndex - 1]/* ||
                            (dp[firstIndex - 1][secondIndex])*/;
                } else {
                    dp[firstIndex][secondIndex] = dp[firstIndex - 1][secondIndex];
                }
            }
        }

//        for(firstIndex = 0; firstIndex <= firstLength; ++firstIndex) {
//            for(secondIndex = 0; secondIndex <= secondLength; ++secondIndex) {
//                System.out.print(dp[firstIndex][secondIndex] + " ");
//            }
//
//            System.out.println();
//        }

        return dp[firstLength][secondLength] ? "YES" : "NO";
    }
}
