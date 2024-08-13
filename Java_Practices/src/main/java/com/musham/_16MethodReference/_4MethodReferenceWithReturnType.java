package com.musham._16MethodReference;

/**
 * @author jmusham
 *
 */
interface Interface {
	public int refererMethod();
}

/**
 * @author jmusham
 *
 */
public class _4MethodReferenceWithReturnType {

	/**
	 * @return
	 */
	public int geRandomInt() {
		return (int) (Math.random() * 100);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		_4MethodReferenceWithReturnType methodRef = new _4MethodReferenceWithReturnType();
		Interface iface = methodRef::geRandomInt;
		System.out.println("The random number is:" + iface.refererMethod());
	}
}