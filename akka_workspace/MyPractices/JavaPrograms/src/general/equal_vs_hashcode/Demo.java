package general.equal_vs_hashcode;

import java.util.HashMap;
import java.util.Map;

class Employee {
	private int num;
	private String name;

	public Employee(int num, String name) {
		this.num = num;
		this.name = name;
	}

	@Override
	public String toString() {
		System.out.println("This is in toString");
		return num + " " + name + "\n";
	}

	// @Override
	// public int hashCode() {
	// System.out.println("This is in the hash code method");
	//
	// return ((Integer) num).hashCode();
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	//
	// System.out.println("In the equals method");
	// if (obj == null)
	// return false;
	// if (this.getClass() != obj.getClass())
	// return false;
	//
	// Employee other = (Employee) obj;
	// if (this.num == other.num) {
	// return true;
	// }
	//
	// return false;
	// }

}

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		Employee emp1 = new Employee(1, "abc");

		Map<Employee, Employee> empMap = new HashMap<>();
		while (true) {
			System.out.println("Put emp1");
			empMap.put(emp1, emp1);

			Employee emp2 = new Employee(1, "abc");
			System.out.println("emp1 == emp2 ??");
			if (emp1 == emp2) {
				System.out.println("emp1 = emp2");
			}

			System.out.println("emp1 equals emp2 ??");
			if (emp1.equals(emp2)) {
				System.out.println("emp1 equals emp2");
			}

			System.out.println("put emp2");
			empMap.put(emp2, emp2);

			System.out.println("Display " + empMap.size() + " map entry list");
			System.out.println("Following is the list:");
			System.out.println(empMap);
			
			Thread.sleep(100);
		}
	}
}
