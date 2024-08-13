package general.multithreading;

public class CountInteger {
	private int count;
	
	public int getCount() {
		return count;
	}
	
	public void increment() {
		++count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CountInteger() {
		count = 0;
	}
}
