package com.gfg.java;

public class AutoboxingAndUnboxing {
	public static void main(String args[]) {
		int x1 = 10;
		Integer x2 = x1;
		int x3 = x2;
		System.out.println(x1);
		System.out.println(x2);
		System.out.println(x3);
		Integer x5 = 40;
		Integer x6 = 40;
		if(x5==x6) {
			System.out.println("Same");
		}
		else {
			System.out.println("Not Same");
		}
		/*The output for above lines is "Same". The reason is Java caches some literals. So what happens in case of
		 * SMALLER values is that it caches the value and when we create more references to the same value
		 * instead of creating a new object, it makes those references to refer to the same literal.*/
		Integer x7 = 400;
		Integer x8 = 400;
		if(x7==x8) {
			System.out.println("Same");
		}
		else {
			System.out.println("Not Same");
		}
		/*The output for above lines is "Not Same"*/
	}

}
