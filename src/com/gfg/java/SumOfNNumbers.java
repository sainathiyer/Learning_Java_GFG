package com.gfg.java;
import java.util.*;

public class SumOfNNumbers {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int sum=0;
		for(int i=1;i<=n;i++) {
			System.out.println(i);
			sum += i;
		}
		sc.close();
		System.out.println(sum);
	}

}
