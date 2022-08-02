package com.gfg.java;
import java.util.*;

public class LCMOf2Numbers {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		sc.close();
		int max = Math.max(n1, n2);
		int mul = n1*n2;
		int lcm=0;
		for(int i=max;i<=mul;i++) {
			if(i%n1==0 && i%n2==0) {
				lcm=i;
				break;
			}
		}
		System.out.println(lcm);
	}
}