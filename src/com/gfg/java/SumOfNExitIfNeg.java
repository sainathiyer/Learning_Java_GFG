package com.gfg.java;
import java.util.*;

public class SumOfNExitIfNeg {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n<0) {
			System.out.println("Invalid Input");
			return;
		}
		System.out.println(n*(n+1)/2);
		sc.close();
	}

}
