package com.gfg.java;
import java.util.*;

public class FirstDigitOfNumber {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		System.out.println(firstDigit(n));
	}
	public static int firstDigit(int n) {
		double power = Math.log10(n);
		int p = (int) power;
		int a = (int) Math.pow(10, p);
		int ans = n/a;
		return ans;
	}

}
