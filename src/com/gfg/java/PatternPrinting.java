package com.gfg.java;

import java.util.Scanner;

public class PatternPrinting {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int i=1;
		sc.close();
		while(i<=n) {
			int j=1;
			while(j<=(n-i)) {
				System.out.print(" ");
				j++;
			}
			int k=1;
			while(k<=(2*i-1)) {
				System.out.print("*");
				k++;
			}
			i++;
			System.out.println();
		}
	}

}
