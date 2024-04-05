package general;

public class EnumUtilities {
	private enum Student {
		John(11), Bella(10), Sam(13), Viraaj(9);
		private int age; // variable defined in enum Student

		int getage() {
			return age;
		} // method defined in enum Student

		Student(int age) // constructor defined in enum Student
		{
			System.out.println("Enum constructor...");
			this.age = age;
		}
	}

	public static void main(String args[]) {
		System.out.println("In Main method");
		Student S;
		System.out.println("Age of Viraaj is " + Student.Viraaj.getage() + " years");
	}
}
