package com.gfg.java;
import java.util.*;

public class TableOfNumbers {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=1;i<=10;i++) {
			System.out.println(i*n);
		}
		sc.close();
	}

}
