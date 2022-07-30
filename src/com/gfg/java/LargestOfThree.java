package com.gfg.java;
import java.util.*;

public class LargestOfThree {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the First Number");
		int a = sc.nextInt();
		System.out.println("Enter the Second Number");
		int b = sc.nextInt();
		System.out.println("Enter the Third Number");
		int c = sc.nextInt();
		sc.close();
		System.out.println(Math.max(a, Math.max(c, b)));
	}
}
