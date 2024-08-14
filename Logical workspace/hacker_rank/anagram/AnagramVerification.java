package hacker_rank.anagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * An anagram is a word or phrase formed by rearranging the letters of another
 * word or phrase, typically using all the original letters exactly once.
 * For example:
 *     Word: "listen"
 *     Anagram: "silent"
 *
 * In this example, "silent" is an anagram of "listen" because it uses all the letters of "listen" in a different order.
 */
public class AnagramVerification {

	public static void main(String[] args) {
		String src = "abcabc";
		String target = "bacbcd";

		isAnagram(src, target);
		areAnagrams(src, target);

		target = "abc";
		makeAnagram(src, target);
	}

	private static void makeAnagram(String first, String second) {
		Map<Character, Integer> srcCharacters = new HashMap<>();
		int count;
		for (count = 0; count < first.length(); count++) {
			int repetitions = 0;
			if (srcCharacters.containsKey(first.charAt(count))) {
				repetitions = srcCharacters.get(first.charAt(count));
			}

			srcCharacters.get(first.charAt(count));
			srcCharacters.put(first.charAt(count), ++repetitions);
		}

		int notInSrc = 0;

		for (count = 0; count < second.length(); count++) {
			char c = second.charAt(count);

			if (srcCharacters.containsKey(second.charAt(count))) {
				int repetitions = srcCharacters.get(c);
				if (repetitions == 1) {
					srcCharacters.remove(c);
				} else {
					srcCharacters.put(c, --repetitions);
				}

			} else {
				notInSrc++;
			}
		}

		int notInTarget = 0;
		for (Character key : srcCharacters.keySet()) {
			notInTarget += srcCharacters.get(key);
		}

		System.out.println("not in src " + notInSrc + " not in target " + notInTarget);
	}

	private static void isAnagram(String src, String target) {
		
		if (src.length() != target.length()) {
			System.out.println("Not a anagram");
			return;
		}

		Map<Character, Integer> targetCharacters = new HashMap<>();
		int count = 0;
		for (; count < target.length(); count++) {
			int repetitions = 0;
			if (targetCharacters.containsKey(target.charAt(count))) {
				repetitions = targetCharacters.get(target.charAt(count));
			}

			targetCharacters.get(target.charAt(count));
			targetCharacters.put(target.charAt(count), ++repetitions);
		}

		Map<Character, Integer> srcCharacters = new HashMap<>();
		for (count = 0; count < src.length(); count++) {
			int repetitions = 0;
			if (srcCharacters.containsKey(src.charAt(count))) {
				repetitions = srcCharacters.get(src.charAt(count));
			}

			srcCharacters.get(src.charAt(count));
			srcCharacters.put(src.charAt(count), ++repetitions);
		}

		int srcCount;
		int dstCount;
		for (Character key : srcCharacters.keySet()) {
			srcCount = srcCharacters.get(key);
			dstCount = targetCharacters.get(key);

			if (srcCount != dstCount) {
				System.out.println("Given strings are not an anagram");
				return;
			}
		}

		System.out.println("Given strings are anagram");

	}

	public static void areAnagrams(String str1, String str2) {
		// Remove spaces and convert to lowercase for case-insensitive comparison
		str1 = str1.replaceAll("\\s", "").toLowerCase();
		str2 = str2.replaceAll("\\s", "").toLowerCase();

		// If lengths of the strings differ, they can't be anagrams
		if (str1.length() != str2.length()) {
			System.out.println("Given strings are not an anagram");
			return;
		}

		// Convert strings to character arrays
		char[] charArray1 = str1.toCharArray();
		char[] charArray2 = str2.toCharArray();

		// Sort the character arrays
		Arrays.sort(charArray1);
		Arrays.sort(charArray2);

		// Compare the sorted arrays
		if (Arrays.equals(charArray1, charArray2)) {
			System.out.println("Given strings are anagram");
			return;
		}

		System.out.println("Given strings are not an anagram");
	}
}
