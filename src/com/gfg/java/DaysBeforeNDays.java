package com.gfg.java;

import java.util.Scanner;

public class DaysBeforeNDays {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the d");
		int d = sc.nextInt();
		System.out.println("Enter the n");
		int n = sc.nextInt();
		System.out.println(utility(d, n));
		sc.close();	
	}
	
	public static int utility(int d, int n) {
		for(int i=1;i<=n;i++)
		{
			--d;
			if(d<0) {
				d = 6;
			}
		}
		return d;
	}

}
