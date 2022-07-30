package com.gfg.java;

import java.util.Scanner;

public class LastDigitOfNumber {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int y = Math.abs(n);
		int lastDigit = y%10;
		System.out.println(lastDigit);
		sc.close();
	}

}
