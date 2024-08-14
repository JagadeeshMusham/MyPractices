package hacker_rank.ice_cream_parlor;

import java.util.Arrays;
import java.util.Scanner;

public class IceCreamDemo {
	public static int binarySearch(int first, int last, IceCream[] arr, int search) {

		return 0;
	}

	private static boolean printFlavors(IceCream[] iceCreamPrice, int pooledAmount) {
		for (int counter = 0; counter < iceCreamPrice.length; counter++) {
			for (int subCounter = counter + 1; subCounter < iceCreamPrice.length; subCounter++) {
				if (iceCreamPrice[counter].price + iceCreamPrice[subCounter].price == pooledAmount) {
					System.out.println(Math.min(iceCreamPrice[counter].flavor, iceCreamPrice[subCounter].flavor) + " "
							+ Math.max(iceCreamPrice[counter].flavor, iceCreamPrice[subCounter].flavor));
					return true;
				} else if (iceCreamPrice[counter].price + iceCreamPrice[subCounter].price > pooledAmount) {
					break;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of trips: ");
		int nTrips = in.nextInt();

		int pooledAmount;
		int nFlavors;

		for (int test = 0; test < nTrips; test++) {
			System.out.println("Enter the pooled amount: ");
			pooledAmount = in.nextInt();

			System.out.println("Enter the flavors of ice cream: ");
			nFlavors = in.nextInt();
			IceCream[] iceCreamPrice = new IceCream[nFlavors];

			for (int counter = 0; counter < nFlavors; counter++) {
				System.out.println("Enter the amount for flavor " + counter);
				iceCreamPrice[counter] = new IceCream(counter + 1, in.nextInt());
			}

			Arrays.sort(iceCreamPrice);

			if (!printFlavors(iceCreamPrice, pooledAmount)) {
				System.out.println("-1");
			}
		}
	}
}
