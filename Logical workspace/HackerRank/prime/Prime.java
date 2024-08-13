package hacker_rank.prime;

import java.util.Scanner;

public class Prime {
	public static boolean isPrime(int number) {
		if (number == 1){
			return false;
		}
	
		for (int counter = 2; counter <= Math.sqrt(number); counter++) {
			if (number%counter == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		for (int a0 = 0; a0 < p; a0++) {
			int n = in.nextInt();
			
			if (isPrime(n)) {
				System.out.println("Prime");
			} else {
				System.out.println("Not prime");
			}
		}
	}
}
