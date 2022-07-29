package com.gfg.java;
import java.util.*;


public class NthTermOfGP {
 
    public static void main(String[] args) {
    	System.out.println("Hi There, Let's find the Nth Term");
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Please Enter the first term");
    	int a = sc.nextInt();
    	System.out.println("Please Enter the ratio");
    	int r = sc.nextInt();
    	System.out.println("Which term would you like to find");
    	int N = sc.nextInt();
    	sc.close();
    	int answer = getNthTerm(a,r,N);
    	System.out.println(answer);
    	
    }
    public static int getNthTerm(int a, int r, int N) {
    	int ans = (int)(a*(Math.pow(r, N-1)));
    	return ans;
    }
}