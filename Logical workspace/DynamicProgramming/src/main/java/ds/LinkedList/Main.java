package DynamicProgramming.src.main.java.ds.LinkedList;

import java.util.Scanner;

import static java.lang.System.exit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Scanner scanner = new Scanner(System.in);
        int input;

        Queue q = new Queue();

        while(true) {

            System.out.print("\n1. Add");
            System.out.print("\t2. Delete");
            System.out.print("\t3. Display");
            System.out.print("\t4. Display in reverse order");
            System.out.println("\t5. Exit");

            input = scanner.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Enter the value to insert:");
                    int value = scanner.nextInt();
                    q.addNode(value);
                    break;
                case 2:
                    q.deleteNode();
                    break;
                case 3:
                    q.displayQueue(false);
                    break;
                case 4:
                    q.displayQueue(true);
                    break;
                case 5:
                    System.out.println("Got request to exit");
                    exit(0);

            }
        }

    }
}