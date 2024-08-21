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

    private static boolean isScrambled(String input, String target) {
        if (input.length() != target.length()) {
            return false;
        } else if (input.equals(target)) {
            return true;
        } else {
            int strLen = input.length();

            for(int index = 1; index < input.length(); ++index) {
                if (isScrambled(input.substring(0, index), target.substring(0, index)) &&
                        isScrambled(input.substring(index), target.substring(index))) {
                    return true;
                }

                if (isScrambled(input.substring(0, index), target.substring(strLen - index)) &&
                        isScrambled(input.substring(index), target.substring(0, strLen - index))) {
                    return true;
                }
            }

            return false;
        }
    }
}
