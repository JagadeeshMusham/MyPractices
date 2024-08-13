package DynamicProgramming.src.main.java.ds.Stack;

import java.util.*;

import static java.lang.System.exit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Scanner scanner = new Scanner(System.in);
        StackImpl stack = new StackImpl(5);

        while(true) {
            System.out.println("Enter an option from below list");
            System.out.println("1. Push");
            System.out.println("2. Pull");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            int input = scanner.nextInt();

            System.out.println("Input: " + input);


            int num;
            switch (input) {
                case 1:
                    System.out.println("Enter a number to push to Stack");
                    if ((num = stack.push(scanner.nextInt())) == -1) {
                        System.out.println("The stack overflow");
                    } else {
                        System.out.println("The number entered: " + num);
                    }

                    break;
                case 2:
                    if((num = stack.pop()) == -1) {
                        System.out.println("The stack is undeflow");
                    }
                    else {
                        System.out.println("The number delete from the stack:" + num);
                    }
                    break;
                case 3:
                    System.out.println("The values in the stack are:");
                    stack.display();
                    break;
                case 4:
                    System.out.println("Selected to exit the application");
                    exit(0);
            }
        }
    }
}