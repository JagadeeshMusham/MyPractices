package general;

public class SequenceCharacters {
	public static void main(String[] args) {
		String characters = "11011110101001110110011101101";

		int prev1 = -1;
		int next1 = -1;
		int pos1 = -1;

		int prev2 = -1;
		int next2 = -1;
		int pos2 = -1;

		int current = 0;

		int counter = 0;
		for (; counter < characters.length(); counter++) {
			char c = characters.charAt(counter);
			if (c == '0') {
				if (pos1 == -1) {
					pos1 = counter;
					prev1 = current;
				} else if (pos2 == -1) {
					next1 = current;
					pos2 = counter;
					prev2 = current;
				} else {
					next2 = current;
					if ((prev1 + next1) < (prev2 + next2)) {
						pos1 = pos2;
						prev1 = prev2;
						next1 = next2;
					}
					
					prev2 = current;
					next2 = 0;
					pos2 = counter;
				}
				current = 0;
			} else {
				++current;
			}
		}

		next2 = current;
		if ((prev1 + next1) < (prev2 + next2)) {
			pos1 = pos2;
			prev1 = prev2;
			next1 = next2;
		}

		System.out.println("We have to replace position: " + pos1);
	}
}
