package general.generics;

import java.util.HashMap;
import java.util.Map;

class GenericsTest<T> {
	T var;
	
	public void show(T var) {
		System.out.println(var);
	}
}

public class CollectionsDemo {
	
	Map<String, String> map = new HashMap<>();
	
//	private void test(Map<T> hashMap) {
//
//	}

	public static void main() {
		System.out.println();
	}
}
