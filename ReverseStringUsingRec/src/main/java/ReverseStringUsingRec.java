
public class ReverseStringUsingRec {
	public static void main(String args[]) {
		String input = "string";
		
		String output = reverse(input);
		System.out.println(output);
	}
	
	
	private static String reverse(String input)
	{
		if (input.isEmpty())
			return input;
		return input.charAt(0) + reverse(input.substring(1));
	}

}
