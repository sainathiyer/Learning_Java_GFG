package com.gfg.java;

import java.util.Scanner;

public class ReverseOfString {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		sc.close();
		char arr[] = str.toCharArray();
		char revarr[] = new char[arr.length];
		for(int i=0;i<arr.length;i++) {
			revarr[i] = arr[(arr.length-1)-i];
		}
		revarr.toString();
		System.out.println(revarr);
	}
}
