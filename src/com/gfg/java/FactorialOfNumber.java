package com.gfg.java;
import java.util.Scanner;

public class FactorialOfNumber {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		int factorial=1;
		for(int i=1;i<=n;i++) {
			factorial *=i;
		}
		System.out.println(factorial);
	}

}
