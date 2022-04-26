package com.gfg.java;

public class TypeConversion {
	public static void main(String args[]) {
		//Widening or Implicit Conversion
		char c = 'c';
		int x = c;
		long y = x;
		float z = y;
		System.out.println(c);
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);	
		//Narrowing or Explicit Conversion
		double d = 65.4;
		int i = (int) d;
		char ch = (char)i;
		System.out.println(i);
		System.out.println(ch);
	}
}
