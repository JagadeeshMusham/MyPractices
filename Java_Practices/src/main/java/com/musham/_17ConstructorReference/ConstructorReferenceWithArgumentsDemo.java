package com.musham._17ConstructorReference;

/**
 * @author jmusham
 *
 */
class ConstructorRef
{
	/**
	 * @param name
	 */
	ConstructorRef(String name)
	{
		System.out.println("Sample class with argument: " + name);
	}
	
	/**
	 * The print method
	 */
	void print()
	{
		System.out.println("Print method of ConstructorRef");
	}
}

/**
 * @author jmusham
 *
 */
interface Interface
{
	/**
	 * @param name
	 * @return
	 */
	public ConstructorRef get(String name);
}

/**
 * @author jmusham
 *
 */
public class ConstructorReferenceWithArgumentsDemo
{
	/**
	 * @param args
	 */
	public static void main(String [] args)
	{
		Interface interf = ConstructorRef::new;
		ConstructorRef ref = interf.get("Ciena");
		ref.print();
	}
}

