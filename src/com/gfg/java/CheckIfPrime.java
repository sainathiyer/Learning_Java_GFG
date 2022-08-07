package com.gfg.java;
import java.util.Scanner;

public class CheckIfPrime {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		System.out.println(checkPrime(number));
		sc.close();
	}

	private static boolean checkPrime(int number) {
		if(number==1) {
			return false;
		}
		for(int i=2;i*i<=number;i++) {
			if(number%i==0) {
				return false;
			}
		}
		return true;
	}

}
