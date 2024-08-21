package dynamic_programming.MCM;

public class _43_EggDroppingProblem {

    public static void main(String[] args) {
        int eggs = 2;
        int floors = 10;
        System.out.printf("Minimum number of trials in worst case with %d eggs and %d floors is %d - %d",
                eggs, floors, eggDropWithRec(eggs, floors), findMinimumAttemptsWithDP(eggs, floors));
    }

    public static int eggDropWithRec(int nEggs, int nFloors) {
        if (nFloors == 0 || nFloors == 1) {
            return nFloors;
        }

        if (nEggs == 1) {
            return nFloors;
        }

        int minAttempts = Integer.MAX_VALUE;

        for(int floor = 1; floor <= nFloors; ++floor) {
            int attemptsIfEggBreaks = eggDropWithRec(nEggs - 1, floor - 1);
            int attemptsIfEggDoesNotBreak = eggDropWithRec(nEggs, nFloors - floor);

            int worstCaseAttempts = 1 + Math.max(attemptsIfEggBreaks, attemptsIfEggDoesNotBreak);

            minAttempts = Math.min(minAttempts, worstCaseAttempts);
        }

        return minAttempts;

    }

    public static int findMinimumAttemptsWithDP(int numberOfEggs, int numberOfFloors) {
        int[][] dp = new int[numberOfEggs + 1][numberOfFloors + 1];

        int egg;
        for(egg = 1; egg <= numberOfEggs; ++egg) {
            dp[egg][0] = 0;
            dp[egg][1] = 1;
        }

        for(egg = 1; egg <= numberOfFloors; egg++) {
            dp[1][egg] = egg;
        }

        int floor;
        for(egg = 2; egg <= numberOfEggs; ++egg) {
            for(floor = 2; floor <= numberOfFloors; ++floor) {
                dp[egg][floor] = Integer.MAX_VALUE;

                for(int currentFloor = 1; currentFloor <= floor; ++currentFloor) {
                    int attemptsIfEggBreaks = dp[egg - 1][currentFloor - 1];
                    int attemptsIfEggDoesNotBreak = dp[egg][floor - currentFloor];
                    int worstCaseAttempts = 1 + Math.max(attemptsIfEggBreaks, attemptsIfEggDoesNotBreak);
                    dp[egg][floor] = Math.min(dp[egg][floor], worstCaseAttempts);
                }
            }
        }

        for(egg = 0; egg < numberOfEggs; ++egg) {
            for(floor = 0; floor < numberOfFloors; ++floor) {
                System.out.print(dp[egg][floor] + " ");
            }

            System.out.println();
        }

        return dp[numberOfEggs][numberOfFloors];
    }
}
