package com.gfg.java;
import java.util.*;

public class PrimeFactorization {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		primeFactors(n);
	}
	
	public static boolean isPrime(int n) {
		for(int i=2;i<n;i++) {
			if(n%i==0) {
				return false;
			}
		}
		return true;
	}

	private static void primeFactors(int n) {
		for(int i=2;i<n;i++) {
			if(isPrime(i)) {
				int x=i;
				while(n%x==0) {
					System.out.println(i);
					x=x*i;
				}
			}
		}
	}
}
