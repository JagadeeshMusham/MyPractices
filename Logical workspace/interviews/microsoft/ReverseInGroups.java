package interviews.microsoft;

public class ReverseInGroups {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        int groupIn = 3;
        doReverseInGroups(input, groupIn);
        displayReverseInRecursion(input, groupIn, 0);

        groupIn = 4;
        doReverseInGroups(input, groupIn);
        displayReverseInRecursion(input, groupIn, 0);

        groupIn = 5;
        doReverseInGroups(input, groupIn);
        displayReverseInRecursion(input, groupIn, 0);
    }

    private static void doReverseInGroups(int[] input, int groupIn) {
        System.out.println("\n\nDisplay the input in reverse in groups: " + groupIn);
        System.out.print("In iterative method: \t");
        for(int counter = 0; counter <= (input.length/groupIn)*groupIn; counter +=groupIn) {
            for(int iterator = counter + groupIn - 1; iterator >= counter; iterator--) {
                if(iterator >= input.length) {
                    iterator = input.length - 1;
                }
                System.out.print(input[iterator] + " ");
            }
        }

        System.out.println();
    }

    private static void displayReverseInRecursion(int[] input, int groupIn, int position) {
        if(position > input.length) {
            return;
        }

        if (position == 0) {
            System.out.print("In recursion method: \t");
        }

        for(int counter = position + groupIn - 1; counter >= position; counter--) {
            if(counter >= input.length) {
                counter = input.length - 1;
            }
            System.out.print(input[counter] + " ");
        }

        displayReverseInRecursion(input, groupIn, position + groupIn);

    }
}
