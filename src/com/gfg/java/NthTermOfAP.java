package com.gfg.java;
import java.util.*;


public class NthTermOfAP {
 
    public static void main(String[] args) {
    	System.out.println("Hi There, Let's find the Nth Term");
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Please Enter the first term");
    	int a = sc.nextInt();
    	System.out.println("Please Enter the difference");
    	int d = sc.nextInt();
    	System.out.println("Which term would you like to find");
    	int N = sc.nextInt();
    	sc.close();
    	int answer = getNthTerm(a,d,N);
    	System.out.println(answer);
    	
    }
    public static int getNthTerm(int a, int d, int N) {
    	int ans = a + ((N-1)*d);
    	return ans;
    }
}