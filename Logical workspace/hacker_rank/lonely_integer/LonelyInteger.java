package hacker_rank.lonely_integer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class LonelyInteger {

	private static Map<Integer, Integer> numMap;

	public static int lonelyInteger(int[] a) {
		for (Entry<Integer, Integer> entry : numMap.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];

		numMap = new HashMap<>();

		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();

			int occurences = 0;
			if (numMap.containsKey(a[a_i])) {
				occurences = numMap.get(a[a_i]);
			}

			numMap.put(a[a_i], ++occurences);
		}

		System.out.println(lonelyInteger(a));
	}
}
