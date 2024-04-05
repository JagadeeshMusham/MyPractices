package general.shiftoperator;

public class ShiftOperators {
	public static void main(String [] args) {
		int a = 512;
		System.out.println("The original value: " + a);
		
		System.out.println("Shift right value:" + (a>>2));
		System.out.println("Shift right value with zeros:" + (a>>>2));
		System.out.println("Shift left value:" + (a<<2));
	}
}
