package hacker_rank.coin_change;

import java.util.Scanner;

public class CoinChange {
	public static long makeChange(int[] coins, int money, int n) {
		// If n is 0 then there is 1 solution (do not include any coin)
		if (n == 0)
			return 1;

		// If n is less than 0 then no solution exists
		if (n < 0)
			return 0;

		// If there are no coins and n is greater than 0, then no solution exist
		if (money <= 0 && n >= 1)
			return 0;

		// count is sum of solutions (i) including S[m-1] (ii) excluding S[m-1]
		return makeChange(coins, money - 1, n) + makeChange(coins, money, n - coins[money - 1]);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int coins[] = new int[m];
		for (int coins_i = 0; coins_i < m; coins_i++) {
			coins[coins_i] = in.nextInt();
		}
		System.out.println(makeChange(coins, n, 4));
	}
}
