package DynamicProgramming.MCM;

// Todo - J, Have to re-verify the logic mkj
public class _43_EggDroppingProblem {
    public static void main(String[] args) {
        int eggs = 2;
        int floors = 10;
        System.out.println("Minimum number of trials in worst case with " +
                eggs + " eggs and " + floors + " floors is " +
                eggDropWithRec(eggs, floors) + " - " + findMinimumAttemptsWithDP(eggs, floors));
    }

    public static int eggDropWithRec(int nEggs, int nFloors) {
        // Base cases
        if (nFloors == 0 || nFloors == 1) {
            return nFloors;  // 0 floors require 0 trials, 1 floor requires 1 trial
        }
        if (nEggs == 1) {
            return nFloors;  // With one egg, we have to try each floor
        }

        int minAttempts = Integer.MAX_VALUE;

        // Consider dropping the egg from each floor from 1 to floors and
        // return the minimum number of trials needed in the worst case
        for (int floor = 1; floor <= nFloors; floor++) {
            int attemptsIfEggBreaks = eggDropWithRec(nEggs - 1, floor - 1); // Egg breaks
            int attemptsIfEggDoesNotBreak = eggDropWithRec(nEggs, nFloors - floor); // Egg doesn't break

            // We take the maximum of the two scenarios since we are working with the worst case
            int worstCaseAttempts = 1 + Math.max(attemptsIfEggBreaks, attemptsIfEggDoesNotBreak);

            // Minimize the number of attempts
            minAttempts = Math.min(minAttempts, worstCaseAttempts);
        }

        return minAttempts;
    }

    public static int findMinimumAttemptsWithDP(int numberOfEggs, int numberOfFloors) {
        // Create a 2D array where dp[i][j] represents the minimum number of trials
        // needed for i eggs and j floors.
        int[][] dp = new int[numberOfEggs + 1][numberOfFloors + 1];

        // Fill the base cases
        for (int egg = 1; egg <= numberOfEggs; egg++) {
            dp[egg][0] = 0; // 0 trials for 0 floors
            dp[egg][1] = 1; // 1 trial for 1 floor
        }

        for (int floor = 1; floor <= numberOfFloors; floor++) {
            dp[1][floor] = floor; // With 1 egg, we need to try each floor
        }

        // Fill the rest of the dp table
        for (int egg = 2; egg <= numberOfEggs; egg++) {
            for (int floor = 2; floor <= numberOfFloors; floor++) {
                dp[egg][floor] = Integer.MAX_VALUE;

                // Try dropping the egg from each floor from 1 to floor
                for (int currentFloor = 1; currentFloor <= floor; currentFloor++) {
                    int attemptsIfEggBreaks = dp[egg - 1][currentFloor - 1]; // Egg breaks
                    int attemptsIfEggDoesNotBreak = dp[egg][floor - currentFloor]; // Egg doesn't break

                    // We take the maximum of these two scenarios
                    int worstCaseAttempts = 1 + Math.max(attemptsIfEggBreaks, attemptsIfEggDoesNotBreak);

                    // We choose the minimum number of attempts among all floors
                    dp[egg][floor] = Math.min(dp[egg][floor], worstCaseAttempts);
                }
            }
        }

        for (int row = 0; row < numberOfEggs; row++) {
            for(int col = 0; col < numberOfFloors; col++) {
                System.out.print(dp[row][col] + " ");
            }
            System.out.println();
        }

        // The answer is stored in dp[numberOfEggs][numberOfFloors]
        return dp[numberOfEggs][numberOfFloors];
    }
}

