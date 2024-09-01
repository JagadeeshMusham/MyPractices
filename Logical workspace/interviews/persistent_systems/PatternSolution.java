package interviews.persistent_systems;

public class PatternSolution {
    public static void main(String[] args) {
        int[] input = new int[] {1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        solution(input);

        input = new int[]{1, 1, 1, 2, 2, 3, 3, 4, 5, 5, 5};
        solution(input);
    }

    private static void solution(int[] input) {
        System.out.print("The input is: ");
        printArray(input);

        for (int itr = input.length - 2; itr > 0; itr--) {
            if(input[itr] == input[itr - 1] && input[itr] == input[itr + 1] ) {
                input[itr - 1]++;
                input = deleteEnties(input, itr);
                itr = input.length - 1;
            }
        }

        System.out.print("The output is: ");
        printArray(input);
    }

    private static int[] deleteEnties(int[] input, int position) {
        int[] newInput = new int[input.length - 2];

        for (int counter = 0; counter < position; counter++) {
            newInput[counter] = input[counter];
        }

        for(int counter = position + 2; counter < input.length; counter++) {
            newInput[counter - 2] = input[counter];
        }

        return newInput;
    }

    private static void printArray(int[] input) {
        System.out.print("[");

        for(int itr = 0; itr < input.length; itr++) {
            System.out.print(input[itr] +
                    (itr == input.length - 1 ? "]\n" : ", ") );
        }
    }
}
