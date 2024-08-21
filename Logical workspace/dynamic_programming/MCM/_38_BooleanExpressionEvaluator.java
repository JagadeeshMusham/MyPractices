package dynamic_programming.MCM;

import java.util.Arrays;

public class _38_BooleanExpressionEvaluator {

    public static void main(String[] args) {
        String expression = "T|F";
        int[][][] mem = getNewMem(expression.length());
        System.out.printf("Number of ways to evaluate the expression '%s' to true: %d - %d\n\n",
                expression, evaluate(expression, 0, expression.length() - 1, true),
                evaluateWithMem(expression, 0, expression.length() - 1, true, mem));

        expression = "T|T&F^T";
        mem = getNewMem(expression.length());
        System.out.printf("Number of ways to evaluate the expression '%s' to true: %d - %d\n\n",
                expression, evaluate(expression, 0, expression.length() - 1, true),
                evaluateWithMem(expression, 0, expression.length() - 1, true, mem));

        expression = "T^F|T";
        mem = getNewMem(expression.length());
        System.out.printf("Number of ways to evaluate the expression '%s' to true: %d - %d\n\n",
                expression, evaluate(expression, 0, expression.length() - 1, true),
                evaluateWithMem(expression, 0, expression.length() - 1, true, mem));

        expression = "T&T&F";
        mem = getNewMem(expression.length());
        System.out.printf("Number of ways to evaluate the expression '%s' to true: %d - %d\n\n",
                expression, evaluate(expression, 0, expression.length() - 1, true),
                evaluateWithMem(expression, 0, expression.length() - 1, true, mem));
    }

    /**
     * This method is to fill -1s in the mem and return that memory
     *
     * @param size, size of the first and second indices
     */
    private static int[][][] getNewMem(int size) {
        int[][][] mem = new int[size + 1][size + 1][2];

        for(int counter = 0; counter <= size; ++counter) {
            for(int subCounter = 0; subCounter <= size; ++subCounter) {
                Arrays.fill(mem[counter][subCounter], -1);
            }
        }

        return mem;
    }

    private static int evaluate(String expression, int left, int right, boolean bTrue) {
        if (left >= right) {
            if (bTrue) {
                return expression.charAt(left) == 'T' ? 1 : 0;
            } else {
                return expression.charAt(left) == 'F' ? 1 : 0;
            }
        }

        int nPossibilities = 0;

        for(int counter = left + 1; counter <= right; counter += 2) {
            int leftTrue = evaluate(expression, left, counter - 1, true);
            int leftFalse = evaluate(expression, left, counter - 1, false);
            int rightTrue = evaluate(expression, counter + 1, right, true);
            int rightFalse = evaluate(expression, counter + 1, right, false);
            char operator = expression.charAt(counter);
            if (operator == '&') {
                if (bTrue) {
                    nPossibilities += leftTrue * rightTrue;
                } else {
                    nPossibilities += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
                }
            } else if (operator == '|') {
                if (bTrue) {
                    nPossibilities += leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
                } else {
                    nPossibilities += leftFalse * rightFalse;
                }
            } else if (operator == '^') {
                if (bTrue) {
                    nPossibilities += leftTrue * rightFalse + leftFalse * rightTrue;
                } else {
                    nPossibilities += leftTrue * rightTrue + leftFalse * rightFalse;
                }
            }
        }

        return nPossibilities;
    }

    private static int evaluateWithMem(String expression, int left, int right, boolean bTrue, int[][][] mem) {
        int thirdD = bTrue ? 1 : 0;
        if (mem[left][right][thirdD] != -1) {
            return mem[left][right][thirdD];
        } else if (left >= right) {
            if (bTrue) {
                mem[left][right][thirdD] = expression.charAt(left) == 'T' ? 1 : 0;
            } else {
                mem[left][right][thirdD] = expression.charAt(left) == 'F' ? 1 : 0;
            }

            return mem[left][right][thirdD];
        } else {
            int nPossibilities = 0;

            for(int counter = left + 1; counter <= right; counter += 2) {
                int leftTrue = evaluateWithMem(expression, left, counter - 1, true, mem);
                int leftFalse = evaluateWithMem(expression, left, counter - 1, false, mem);
                int rightTrue = evaluateWithMem(expression, counter + 1, right, true, mem);
                int rightFalse = evaluateWithMem(expression, counter + 1, right, false, mem);
                char operator = expression.charAt(counter);
                if (operator == '&') {
                    if (bTrue) {
                        nPossibilities += leftTrue * rightTrue;
                    } else {
                        nPossibilities += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
                    }
                } else if (operator == '|') {
                    if (bTrue) {
                        nPossibilities += leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
                    } else {
                        nPossibilities += leftFalse * rightFalse;
                    }
                } else if (operator == '^') {
                    if (bTrue) {
                        nPossibilities += leftTrue * rightFalse + leftFalse * rightTrue;
                    } else {
                        nPossibilities += leftTrue * rightTrue + leftFalse * rightFalse;
                    }
                }
            }

            mem[left][right][thirdD] = nPossibilities;
            return mem[left][right][thirdD];
        }
    }
}
