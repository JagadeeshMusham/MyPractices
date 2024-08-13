package dynamic_programming.MCM;

public class _41_ScrambledString {
    public static void main(String[] args) {
        String input = "great";
        String target = "ergat";
        System.out.printf("The string1 '%s' and string2 '%s' are in Scramble state: %b\n",
                input, target, isScrambled(input, target));

        input = "great";
        target = "taerg";
        System.out.printf("The string1 '%s' and string2 '%s' are in Scramble state: %b\n",
                input, target, isScrambled(input, target));

        input = "great";
        target = "greatt";
        System.out.printf("The string1 '%s' and string2 '%s' are in Scramble state: %b\n",
                input, target, isScrambled(input, target));
    }

    private static boolean isScrumbled(String input, String target) {
        if(input.equals(target)) {
            return true;
        }

        if(input.length() != target.length()) {
            return false;
        }

        int strLen = input.length();
        for(int index = 1; index < strLen; index++) {
            // Case 1: No swap between parts
            if(isScrumbled(input.substring(0, index), target.substring(0, index) ) &&
            isScrumbled(input.substring(index+1), target.substring(index+1))) {
                return true;
            }

            // Case 2: Swap between parts
            /**
             * Let's consider the following scenario:
             *
             *     Input: "great"
             *     Target: "eatgr"
             *     Index: 2
             *
             * We break the input "great" into "gr" and "eat" at index 2.
             * Similarly, we break the target "eatgr" into "eat" and "gr," observing a swap.
             *
             * This case confirms that "eatgr" is a scrambled version of "great" based on this condition.
             */
            if(isScrumbled(input.substring(0, index), target.substring(strLen - index)) &&
            isScrumbled(input.substring(strLen - index), target.substring(0, index))) {
                return true;
            }
        }

        return false;
    }











    private static boolean isScrambled(String input, String target) {
        if(input.length() != target.length())
            return false;

        if(input.equals(target)) {
            return true;
        }

        int strLen = input.length();

        for(int index = 1; index < input.length(); index++) {
            if (isScrambled(input.substring(0, index), target.substring(0, index)) &&
                    isScrambled(input.substring(index), target.substring(index))) {
                return true;
            }

            if(isScrambled(input.substring(0, index), target.substring(strLen - index)) &&
                    isScrambled(input.substring(index), target.substring(0, strLen - index))) {
                return true;
            }
        }

        return false;
    }
}
