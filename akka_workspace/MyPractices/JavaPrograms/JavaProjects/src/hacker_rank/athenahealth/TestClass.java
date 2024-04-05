package hacker_rank.athenahealth;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TestClass {
	public static void main(String args[]) throws Exception {
		StringBuilder output = new StringBuilder();
		
		Scanner scanner = new Scanner(System.in);

		int nTestCases = scanner.nextInt();

		while (nTestCases-- > 0) {
			int selectedCells = scanner.nextInt();

			Map<Integer, Integer> diffMap = new HashMap<>();
			Map<Integer, Integer> sumMap = new HashMap<>();

			while (selectedCells-- > 0) {
				int xValue = scanner.nextInt();
				int yValue = scanner.nextInt();

				int diff = xValue - yValue;
				int sum = xValue + yValue;

				if (diffMap.containsKey(diff)) {
					diffMap.put(diff, diffMap.get(diff) + 1);
				} else {
					diffMap.put(diff, 1);
				}

				if (sumMap.containsKey(sum)) {
					sumMap.put(sum, sumMap.get(sum) + 1);
				} else {
					sumMap.put(sum, 1);
				}
			}

			long diagonalPairs = 0;

			for (int count : diffMap.values()) {
				diagonalPairs += (count * (count - 1)) / 2;
			}

			for (int count : sumMap.values()) {
				diagonalPairs += (count * (count - 1)) / 2;
			}

			output.append(diagonalPairs).append("\n");
		}

		System.out.println(output);
	}
}
