package com.gfg.java;
import java.util.*;

public class LeapYearOrNot {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a Year");
		int year = sc.nextInt();
		if(year%4==0 && year%100!=0) {
			System.out.println("Yes");
		}else if(year%400 == 0) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		sc.close();
	}

}
