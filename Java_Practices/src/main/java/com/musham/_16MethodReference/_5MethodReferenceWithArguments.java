package com.musham._16MethodReference;

/**
 * @author jmusham
 *
 */
interface Interface1 {
	public int refererMethod(int input1, int input2j);
}

/**
 * @author jmusham
 *
 */
public class _5MethodReferenceWithArguments {

	/**
	 * @param input1
	 * @param input2
	 * @return
	 */
	public int getAdditionValue(int input1, int input2) {
		return input1 + input2;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		_5MethodReferenceWithArguments methodRef = new _5MethodReferenceWithArguments();
		Interface1 iface = methodRef::getAdditionValue;
		System.out.println("The addition value is:" + iface.refererMethod(5, 10));
	}
}