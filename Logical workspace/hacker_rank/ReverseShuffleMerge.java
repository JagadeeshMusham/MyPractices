package hacker_rank;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 */
public class ReverseShuffleMerge {
    public static void main(String[] args) {
        String str = "eggegg";
        System.out.println(reverseShuffleMerge(str));

        str = "abcdefgabcdefg";
        System.out.println(reverseShuffleMerge(str));

        str = "aeiouuoiea";
        System.out.println(reverseShuffleMerge(str));

    }

    // Todo J, Have to re-look into this:
    private static String reverseShuffleMerge(String s) {
        // Create a frequency map of characters needed in the desired string A
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            freq.put(entry.getKey(), entry.getValue() / 2);
        }

        StringBuilder solution = new StringBuilder();
        int n = s.length();
        while (solution.length() < n / 2) {
            Map<Character, Integer> leftFreq = new HashMap<>();
            for (char c : s.toCharArray()) {
                leftFreq.put(c, leftFreq.getOrDefault(c, 0) + 1);
            }

            Map<Character, Integer> nextChar = new HashMap<>();
            char minChar = Character.MAX_VALUE;

            // Search original string S backwards
            for (int index = s.length() - 1; index >= 0; index--) {
                char currentChar = s.charAt(index);
                // Check if it is possible to take this char as the next one
                if (!nextChar.containsKey(currentChar) && freq.get(currentChar) > 0) {
                    nextChar.put(currentChar, index);
                    if (currentChar < minChar) {
                        minChar = currentChar;
                    }
                }

                // Update left frequency map
                leftFreq.put(currentChar, leftFreq.get(currentChar) - 1);
                if (leftFreq.get(currentChar) < freq.get(currentChar)) {
                    break;
                }
            }

            // Add minimal char as the next one (greedy approach)
            solution.append(minChar);
            freq.put(minChar, freq.get(minChar) - 1);
            s = s.substring(0, nextChar.get(minChar));
        }

        return solution.toString();

//        // Write your code here
//        int len = s.length();
//        char[] intialStringBuff = s.toCharArray();
//        Arrays.sort(intialStringBuff);
//
//        StringBuilder first = new StringBuilder();
//        for(int couner = 0; couner < len / 2; couner++) {
//            first.append(intialStringBuff[couner]);
//        }
//
//        StringBuilder second = new StringBuilder();
//        for(int couner = len/2 + 1; couner < len; couner++) {
//            second.append(intialStringBuff[couner]);
//        }
//
//        second = second.reverse();
//
//        second.append(first).reverse();
//
//        StringBuilder finalString = new StringBuilder();
//
//        int firstIndex = 0;
//        int secondIndex = 0;
//        while (firstIndex < first.length() && secondIndex < second.length())
//        {
//            if(first.charAt(firstIndex) <= second.charAt(secondIndex)) {
//                finalString.append(first.charAt(firstIndex));
//                firstIndex++;
//            }
//            else {
//                finalString.append(second.charAt(secondIndex));
//                secondIndex++;
//            }
//        }
//
//        if (firstIndex < first.length()) {
//            finalString.append(first.substring(firstIndex));
//        }
//
//        if(secondIndex < second.length()) {
//            finalString.append(second.substring(secondIndex));
//        }
//
//        return finalString.substring(0, first.length()).toString();
    }

//    private static StringBuilder getReverse(StringBuilder str) {
//        char ch;
//        int len = str.length();
//
//        for(int counter = 0; counter < len / 2; counter++) {
//            ch = str.charAt(len - counter - 1);
//            str.setCharAt(len - counter - 1, str.charAt(counter));
//            str.setCharAt(counter, ch);
//        }
//
//        return str;
//
//    }
}
