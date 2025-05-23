package ds;

import java.util.Stack;

public class PostFixNotation {
    // char stack
    static Stack<Character> stack = new Stack<>();

    public static int operatorImportance(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '(':
            case ')':
                return 4;
        }

        return 0;
    }

    public static boolean isOperator(char ch) {
        switch (ch) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
            case '^':
                return true;
        }

        return false;
    }

    public static StringBuilder convert(String infix, StringBuilder postFixExpr) {
        stack.push('#');
        for(char ch : infix.toCharArray()) {
            if (!isOperator(ch)) {
                postFixExpr.append(ch);
            } else {
                if (ch == '(') {
                    stack.push(ch);
                }
                else if (ch == ')') {
                    while(stack.peek() != '(') {
                        postFixExpr.append(stack.pop());
                    }
                    stack.pop();
                }
                else {
                    if (operatorImportance(stack.peek()) > operatorImportance(ch)) {
                        stack.push(ch);
                    } else {
                        while (operatorImportance(stack.peek()) >= operatorImportance(ch)) {
                            postFixExpr.append(stack.pop());
                        }

                        stack.push(ch);
                    }
                }
            }

        }

        while (stack.peek() != '#') {
            postFixExpr.append(stack.pop());
        }

        return postFixExpr;
    }

    public static boolean isDigit(char c) {
        if (c - '0' >= 0 && c - '0' <= 9) {
            return true;
        }

        return false;
    }

    public static int evaluate(String postFix) {
        Stack<Integer> stack = new Stack<>();

        for(char ch : postFix.toCharArray()) {
            if (isDigit(ch)){
                stack.push(ch - '0');
            }
            else if (isOperator(ch)) {
                int second = stack.pop();
                int first = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push(first + second);
                        break;
                    case '-':
                        stack.push(first - second);
                        break;
                    case '*':
                        stack.push(first * second);
                        break;
                    case '/':
                        stack.push(first / second);
                        break;
                    case '^':
                        stack.push(first ^ second);
                        break;
                }

            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String infix = "2*(5+7)/2";
        StringBuilder postfixExpr = new StringBuilder();
        convert(infix, postfixExpr);
        System.out.println("Infix expression is: " + infix);
        System.out.println("Postfix expression is: " + postfixExpr);
        System.out.println("Evaluated expression is: " + evaluate(postfixExpr.toString()));
    }
}