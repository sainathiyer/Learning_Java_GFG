package com.gfg.java;

public class ArraysInJava {
	public static void main(String args[]) {
		int []a = {8,2,6,4,3};
		int sum=0;
		int squareSum=0;
		//Enhanced For Loop
		for(int i:a) {
			sum += i;
			squareSum += i*i;
		}
		System.out.println(sum);
		System.out.println(squareSum);
	}
}
