package DynamicProgramming;

public class TestDP {
    /**
     * write a program to get a subset which give sum of given number
     */

    public static void main(String[] args) {
        int[] input = {1, 2, 5};

        System.out.println("The input is: ");
        printArray(input);

        int sum = 6;
        System.out.printf("The given input can form the sum of %d: %b\n",
                sum, getSubsetofSum(input, sum));

        sum = 4;
        System.out.printf("The given input can form the sum of %d: %b\n",
                sum, getSubsetofSum(input, sum));

        sum = 3;
        System.out.printf("The given input can form the sum of %d: %b\n",
                sum, getSubsetofSum(input, sum));

        sum = 9;
        System.out.printf("The given input can form the sum of %d: %b\n",
                sum, getSubsetofSum(input, sum));

        sum = 0;
        System.out.printf("The given input can form the sum of %d: %b\n",
                sum, getSubsetofSum(input, sum));

    }

    private static void printArray(int[] input) {
        for(int row = 0; row < input.length; row++) {
            System.out.print(input[row] + "\t");
        }

        System.out.println();
    }

    private static boolean getSubsetofSum(int[] input, int sum) {
        boolean[][] dp = new boolean[input.length+1][sum+1];

        for(int row = 0; row<= input.length; row++) {
            dp[row][0] = true;
        }

        for(int col = 1; col <= sum; col++) {
            dp[0][col] = false;
        }

        for(int row = 1; row<= input.length; row++) {
            for(int col = 1; col <= sum; col++) {
                if(input[row-1] > col) {
                    dp[row][col] = dp[row-1][col];
                }
                else {
                    dp[row][col] = dp[row-1][col] || dp[row-1][col - input[row-1]];
                }
            }
        }

        return dp[input.length][sum];


















//        boolean[][] dp = new boolean[input.length+1][sum+1];
//
//        for(int counter = 0; counter <= input.length; counter++) {
//            dp[counter][0] = true;
//        }
//
//        for(int counter = 1; counter <= sum; counter++) {
//            dp[0][counter] = false;
//        }
//
//        for(int row = 1; row <= input.length; row++) {
//            for (int col = 1; col <= sum; col++) {
//                if (input[row - 1] > col) {
//                    dp[row][col] = dp[row-1][col];
//                }
//                else {
//                    dp[row][col] = dp[row-1][col] || dp[row-1][col - input[row - 1]];
//                }
//            }
//        }
//
//        for(int row = 0; row <= input.length; row++) {
//            for (int col = 0; col <= sum; col++) {
//                System.out.print(dp[row][col] + "\t");
//            }
//            System.out.println();
//        }
//
//        return dp[input.length][sum];
//








//
//        boolean[][] dp = new boolean[input.length+1][sum+1];
//
//        for(int counter = 0; counter <= input.length; counter++) {
//            dp[counter][0] = true;
//        }
//
//        for (int counter = 1; counter <= sum; counter++) {
//            dp[0][counter] = false;
//        }
//
//        for(int row = 1; row <= input.length; row++) {
//            for (int col = 1; col <= sum; col++) {
//                if (input[row-1] > col) {
//                    dp[row][col] = dp[row-1][col];
//                }
//                else {
//                    dp[row][col] = dp[row-1][col] || dp[row-1][col - input[row-1]];
//                }
//            }
//        }
//
//        return dp[input.length][sum];
    }
}
