package general;

interface A {
	public void abc();
}

interface B extends A {
	public void abc();
}

public class Test /* extends Thread implements Comparable */ {
	// Integer id;
	//
	// Test(int id) {
	// this.id = id;
	// }
	//
	// public String toString() {
	// return this.id.toString();
	// }
	//
	// public int hashCode() {
	// int result = 1;
	// result = 31 + ((id == null) ? 0 : id.hashCode());
	// return result;
	// }
	//
	// public Boolean equals(Test obj) {
	// // Test other = (Test) obj;
	// // Boolean ret = false;
	// // if (other.id.equals(this.id))
	// // ret = true;
	// // return ret;
	// return this.id.equals(((Test) obj).id);
	// }

	// public void run() {
	// for (int i = 0; i < 3; i++) {
	// System.out.println("C");
	// System.out.println("D");
	// }
	//
	// }

	public static void main(String[] args) {
		Example t = new Example() {
			public void run() {
				System.out.println("foo");
			}
		};
		t.start();
		// ThreadEx t1 = new ThreadEx();
		// Test t2 = new Test();
		// t1.start();
		// t2.start();

		// new ThreadEx().start();
		// new ThreadEx(new RunnableEx()).start();

		// int arr[] = new int[10];
		// int i = 5;
		// arr[i++] = i++ + i++;
		// System.out.println(arr[5] + ":" + arr[6]);
		// Set s = new TreeSet();
		// Test t1 = new Test(1);
		// Test t3 = new Test(2);
		//
		// s.add(t1);
		// s.add(t3);

		// Map m = new TreeMap();
		//
		// m.put(t1, 1);
		// m.put(t3, 1);

		// System.out.println(s.size());

		// Set s = m.keySet();
		// Iterator iter = s.iterator();
		//
		// while (iter.hasNext())
		// System.out.println(iter.next());
	}

	// @Override
	// public int compareTo(Object o) {
	// return 0;
	// }
}

// class RunnableEx implements Runnable {
// public void run() {
// // System.out.println("Inside RunnableEx");
// }
// }

class Example extends Thread {
	// ThreadEx() {
	// }
	//
	// ThreadEx(Runnable r) {
	// super(r);
	// }
	//
	Example() {
		System.out.println("Example");
	}

	public void run(String s) {
		System.out.println("baz");
		// for (int i = 0; i < 3; i++) {
		// System.out.println("A");
		// System.out.println("B");
		// }
	}

	public void run() {
		System.out.println("bar");
	}
}
