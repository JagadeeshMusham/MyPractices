package general.comparable;

public class Student implements Comparable<Student> {
	int rollno;
	String name;
	int age;

	Student(int rollno, String name, int age) {
		this.rollno = rollno;
		this.name = name;
		this.age = age;
	}

	public int compareTo(Student st) {
		if (name.equals(st.name))
			return 0;
		else if (name.compareTo(st.name) > 0)
			return 1;
		else
			return -1;
	}
}
