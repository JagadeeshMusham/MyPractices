package ds.HashMap;

import java.util.*;

import static java.lang.System.exit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Map<Integer, Node> hashMap = new HashMap<>(100);

        Scanner scanner = new Scanner(System.in);

        int input;
        Node node;
        for(int counter = 0; counter < 5; counter++) {
            System.out.println("Add a value to enter");
            input = scanner.nextInt();

            node = new Node(input);
            hashMap.put(input, node);
        }

        while (true) {
            System.out.println("Add a value to find");
            input = scanner.nextInt();

            if(input == -1) {
                System.out.println("Got Exit command");
                exit(0);
            }

            if (hashMap.containsKey(input)) {
                System.out.printf("\nThe value for the input %d is: %d\n",
                        input, hashMap.get(input).getValue());
            } else
                System.out.printf("The input %d not found in the hashMap", input);
        }
    }
}