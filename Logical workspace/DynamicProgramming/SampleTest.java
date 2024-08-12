package DynamicProgramming;


/**
 * q = 1, 2, 3, 4, 5, 6
 *
 * K= 3
 *
 * q = 3, 2, 1, 6, 5, 4
 *
 * q.enque - enter
 * q.deq - dele
 * q.size -> size
 * head 1
 * tail 6
 *
 * K = 2
 * q = 1, 2, 3, 4, 5, 6
 *
 * q = 2, 1, 4, 3, 6, 5
 *
 *
 * Solution-1. The Stack method
 * Stack the K
 * Repeat the loop for q.size times, current Ntimes
 *      deQ
 *      Store that in a stock
 *      if (current +1) % k == 0
 *          for traverse stack entries N times O(K)
 *            delete one entry from stack
 *            enq
 *
 *      repeat the loop
 *
 * Solution-2. By simple traversing
 *
 * Step 1: take left value as starting point
 * Step 2: right value as left + K
 * Step 3: Swap the values at left and right
 * Step 4: increase left and decrease the right
 * Step 5: perform step 3
 * Step 6: repeat step 4 & 3 util int crosess each other
 * step 7: perform above all steps till the input traversal gets completed
 *
 *
 * Solution-3: Using recursive call
 *  Array of size K
 *
 *  limited local variable. recursive call
 *
 *  rec(number, count)
 *      If Count(%K == 0)
 *          print number;
 *          return;
 *
 *
 *      read from the stream;
 *      rec()
 *
 */

public class SampleTest {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        int len = input.length;

        for (int counter = 0; counter < len; counter += k) {
            recursionMethod(input, counter, k);
        }

        inplaceMethod(input, k);
    }

    private static void inplaceMethod(int[] input, int k) {
        System.out.println("\n=============inplaceMethod start==============");
        int start = 0;
        int end = start + k - 1;

        for(int iterator = 0; iterator <= (input.length / k) + 1; iterator++) {
            start = iterator * k;
            end = start + k - 1;

            if (end >= input.length) {
                end = input.length - 1;
            }

            /**
             * we can replace above if check with below statement
             * end = Math.min(start +k - 1, input.length - 1)
             */

            while(start < end) {
                int temp = input[start];
                input[start] = input[end];
                input[end] = temp;

                start++;
                end--;
            }
        }

        for (int value : input) {
            System.out.println(value);
        }
    }

    private static void recursionMethod(int[] input, int counter, int k) {
        System.out.println("\n\n============Recursion Method Start at counter " + counter);

        /**
         * K = 3;
         * counter = 0;
         * 1, 2, 3, 4, 5, 6
         *
         * input =                 1, 2, 3,
         * Counter =               0, 1, 2,
         * displayValue = false;   f, f, t
         *
         * Print 3, 2, 1,
         *
         */
        if(counter >= input.length) {
            return;
        }

        if((counter+1) % k == 0) {
            System.out.println(input[counter]);
            return;
        }

        recursionMethod(input, counter+1, k);
        System.out.println(input[counter]);
    }

}
