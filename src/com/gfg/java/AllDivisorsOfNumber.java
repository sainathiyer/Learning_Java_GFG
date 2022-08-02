package com.gfg.java;
import java.util.*;

public class AllDivisorsOfNumber {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		sc.close();
		for(int i=1;i<=number;i++) {
			if(number%i==0) {
				System.out.println(i);
			}
		}
	}
}
