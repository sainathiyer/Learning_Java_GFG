package com.gfg.java;
import java.util.*;

public class GCDOf2Numbers {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		sc.close();
		int min = Math.min(n1, n2);
		int gcd=0;
		for(int i=1;i<=min;i++) {
			if(n1%i==0 && n2%i==0) {
				gcd = i;
			}
		}
		System.out.println(gcd);
		
	}

}
