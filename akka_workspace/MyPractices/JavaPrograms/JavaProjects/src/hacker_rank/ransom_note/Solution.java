package hacker_rank.ransom_note;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		int m = scanner.nextInt();
//		int n = scanner.nextInt();
		int m = 6;
		int n = 4;
		String magazine[] = new String[m];
//		for (int magazine_i = 0; magazine_i < m; magazine_i++) {
//			magazine[magazine_i] = scanner.next();
//		}
		magazine[0] = "give";
		magazine[1] = "me";
		magazine[2] = "one";
		magazine[3] = "grand";
		magazine[4] = "today";
		magazine[5] = "night";
		
		String ransom[] = new String[n];
//		for (int ransom_i = 0; ransom_i < n; ransom_i++) {
//			ransom[ransom_i] = scanner.next();
//		}
		
		ransom[0] = "give";
		ransom[1] = "one";
		ransom[2] = "grand";
		ransom[3] = "today";
		
		Map<String, Integer> magazineWordMap = new HashMap<>();
		
		for (String magazineWord : magazine) {
			int occurences = 0;
			if (magazineWordMap.containsKey(magazineWord)) {
				occurences = magazineWordMap.get(magazineWord);
			}
			
			magazineWordMap.put(magazineWord, ++occurences);
		}
		
		boolean bWordFound = true;
		for (String ransomWord : ransom) {
			if (magazineWordMap.containsKey(ransomWord)) {
				int occurences = magazineWordMap.get(ransomWord);
				if (occurences == 1) {
					magazineWordMap.remove(ransomWord);
					continue;
				}
				magazineWordMap.put(ransomWord, --occurences);
			} else {
				bWordFound = false;
				break;
			}
		}
		
		if (bWordFound) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
