package com.gfg.java;

import java.util.Scanner;

public class CheckForPalindrome {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		sc.close();
		char arr[] = str.toCharArray();
		StringBuffer sbr = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sbr.append(arr[(arr.length - 1) - i]);
		}
		if (str.equals(sbr.toString())) {
			System.out.println("Yes, its a palindrome");
		} else {
			System.out.println("No, its not a palindrome");
		}
	}
}
