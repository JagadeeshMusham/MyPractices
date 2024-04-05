package hacker_rank.find_contacts;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FindContacts {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// int nOperations = scanner.nextInt();
		int nOperations = 4;
		for (int counter = 0; counter < nOperations; counter++) {
			System.out.println("Enter the operation: ");
			String op = scanner.next();

			System.out.println("Enter the name: ");
			String name = scanner.next();

			if ("add".equals(op)) {
				addContact(name);
			} else if ("find".equals(op)) {
				System.out.println(findName(name));
			}

		}
	}

	private static int findName(String name) {
		int occurences = 0;
		if (nameMap.containsKey(name)) {
			occurences = nameMap.get(name);
		}

		return occurences;
	}

	private static Map<String, Integer> nameMap = new HashMap<>();

	private static void addContact(String name) {
		for (int counter = 0; counter < name.length(); counter++) {
			String subName = name.substring(0, counter + 1);

			int occurences = 0;
			if (nameMap.containsKey(subName)) {
				occurences = nameMap.get(subName);
			}

			nameMap.put(subName, ++occurences);
		}
	}
}
