package DynamicProgramming;

public class TestProgram {

    public static void main(String[] args) {
        String input = "12  3";

        char[] inputArray = input.toCharArray();

        for(int counter = inputArray.length-1; counter >= 0; counter--) {
            if(inputArray[counter] != ' ') {
                System.out.print(inputArray[counter]);
            }
        }
    }
}
