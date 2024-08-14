package hacker_rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/luck-balance/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 */

public class MaxLuckBalance {

    public static void main(String[] args) {
        List<List<Integer>> contests = new ArrayList<>();
        int totalContext = 6;
        int maxCanLose = 3;

        List<Integer> contest = new ArrayList<>();
        contest.add(5);
        contest.add(1);
        contests.add(contest);

        contest = new ArrayList<>();
        contest.add(2);
        contest.add(1);
        contests.add(contest);

        contest = new ArrayList<>();
        contest.add(1);
        contest.add(1);
        contests.add(contest);

        contest = new ArrayList<>();
        contest.add(8);
        contest.add(1);
        contests.add(contest);

        contest = new ArrayList<>();
        contest.add(10);
        contest.add(0);
        contests.add(contest);

        contest = new ArrayList<>();
        contest.add(5);
        contest.add(0);
        contests.add(contest);

        int maxLuckBalance = getLuckBalance(maxCanLose, contests);
        System.out.println(maxLuckBalance);
    }

    public static int getLuckBalance(int k, List<List<Integer>> contests) {
        int luckBalance = 0;

        List<Integer> luckBalances = new ArrayList<>();

        for(int counter = 0; counter < contests.size(); counter++) {
            List<Integer> contest = contests.get(counter);

            int luck = contest.get(0);
            luckBalance += luck;
            if (contest.get(1) == 1) {
                luckBalances.add(luck);
            }
        }

        Collections.sort(luckBalances);

        for (int counter = 0; counter < luckBalances.size() - k; counter++) {
            luckBalance -= 2*luckBalances.get(counter);
        }

        return luckBalance;
    }
}
