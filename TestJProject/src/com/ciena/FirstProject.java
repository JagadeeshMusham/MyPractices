package com.ciena;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

	private static boolean isInOrder(List<Integer> arr) {
		int current = -1;
		int previous = -1;

		for (int counter = 0; counter < arr.size(); counter++) {
			current = arr.get(counter);
			if (counter == 0) {
				previous = current;
				continue;
			}

			if (previous >= current) {
				return false;
			}
			
			previous = current;
		}

		return true;
	}

	/*
	 * Complete the 'whoIsTheWinner' function below.
	 *
	 * The function is expected to return a STRING. The function accepts
	 * INTEGER_ARRAY arr as parameter.
	 */

	public static String whoIsTheWinner(List<Integer> arr) {
		// WRITE DOWN YOUR CODE HERE

		int sizeofArray = arr.size();

		int counter = 0;

		while (counter > -1) {
			++counter;
			if (isInOrder(arr)) {
				if (counter % 2 == 0)
					return "Second";
				else
					return "First";
			}

			List<Integer> newArr = new ArrayList<>();
			int index = 0;
			int newCounter = 0;
			for (; index < arr.size(); index++) {
				newArr.clear();
				for (int subIndex = 0; subIndex < arr.size(); subIndex++) {
					if (subIndex == index)
						continue;
					newArr.add(arr.get(subIndex));
				}

				if (isInOrder(newArr) == false) {
					arr.remove(index);
					break;
				}

			}

			arr.remove(0);

		}

		return "First";

	}

}

public class FirstProject {
	
	/*
	 * 	Input: 	a13v4g6
		Output:	g13v4a6
	 */
	public static void reverse() {
		String str1 = "a13v4gse54dsfgdr67sed6";
		char[] str = str1.toCharArray();
		
		int start = 0;
		int end = str.length - 1;
		char tmp;
		
		while (end > start) {
			System.out.println("Starting char: " + str[start] + "Ending Char: " + str[end]);
			if (!Character.isDigit(str[start])) {
				if (!Character.isDigit(str[end])) {
					tmp = str[start];
					str[start] = str[end];
					str[end] = tmp;
					
					start++;
					end--;
					
					
				} else {
					end--;
				}
			} else {
				start++;
			}
		}
		
		
		System.out.println(str1);
		System.out.println(str);
	}
	
	public static void main(String[] args) throws IOException {
		reverse();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String filePath = "/Users/jmusham/1.txt";
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

			int t = Integer.parseInt(bufferedReader.readLine().trim());

			IntStream.range(0, t).forEach(tItr -> {
				try {
					int n = Integer.parseInt(bufferedReader.readLine().trim());

					List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
							.map(Integer::parseInt).collect(toList());

					String result = Result.whoIsTheWinner(arr);
					System.out.println("The winner is:" + result);

					bufferedWriter.write(result);
					bufferedWriter.newLine();
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			});

			bufferedReader.close();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
