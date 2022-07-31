package com.gfg.java;
import java.util.*;

public class CountDigits {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int count = 0;
		sc.close();
		while(number>0) {
			number= number/10;
			count++;
		}
		System.out.println(count);
		
	}

}
