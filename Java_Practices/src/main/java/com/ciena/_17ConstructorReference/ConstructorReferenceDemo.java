package com.ciena._17ConstructorReference;

/**
 * @author jmusham
 *
 */
class ConstructorReference
{
	/**
	 * The Constructor
	 */
	ConstructorReference()
	{
		System.out.println("ConstructorReference constructor");
	}
	
	/**
	 * The print method
	 */
	void print()
	{
		System.out.println("Print method of ConstructorReference");
	}
}

/**
 * @author jmusham
 *
 */
interface Interf
{
	/**
	 * @return
	 */
	public ConstructorReference get();
}

/**
 * @author jmusham
 *
 */
public class ConstructorReferenceDemo
{
	public static void main(String [] args)
	{
		Interf interf = ConstructorReference::new;
		ConstructorReference reference = interf.get();
		reference.print();
	}
}
