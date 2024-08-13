package HackerRank;

import java.sql.Array;
import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/greedy-florist/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 */
public class FlowersMinCost {

    public static void main(String[] args) {
//        int totalFlowers = 5;     //The problem stmt defines this
        int nFriends = 3;

        int[] flowerCost = {7, 9, 3, 5, 1};

        System.out.println(getMinimumCost(nFriends, flowerCost));
    }

    /**
     *
     * @param k, number of friends
     * @param c, cost of flowers
     * @return, minimum cost
     */
    private static int getMinimumCost(int k, int[] c) {
        int minCost = 0;
        int multiplier = 1;

        Arrays.sort(c);

        for(int counter = c.length - 1; counter >= 0; counter--) {
            minCost += multiplier * c[counter];

            if((c.length - counter) % k == 0) {
                multiplier++;
            }
        }

        return minCost;

    }
}
