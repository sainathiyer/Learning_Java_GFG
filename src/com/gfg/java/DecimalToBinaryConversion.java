package com.gfg.java;

import java.util.Scanner;

public class DecimalToBinaryConversion {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		sc.close();
		DecToBin(number);
	}
	
	public static void DecToBin(int number) {
		String s = " ";
		while(number>0) {
			s = number%2+s;
			number = number/2;
		}
		System.out.println(s);
	}
}
