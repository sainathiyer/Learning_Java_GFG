package com.gfg.java;
import java.util.*;
public class Swap2Numbers {
	
	public static void main(String args[]) {
		System.out.println("Please Enter 2 Numbers");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.println("The Entered values are "+a+" and "+b);
		int temp = 0;
		temp = a;
		a = b;
		b = temp;
		System.out.println("The Swapped values are "+a+" and "+b);
	}

}
