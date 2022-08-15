package com.gfg.java;

public class ArraysInJava {
	public static void main(String args[]) {
		int []a = {8,2,6,4,3};
		int sum=0;
		int sqSum=0;
		//Enhanced For Loop
		for(int i:a) {
			sum += i;
			sqSum += i*i;
		}
		System.out.println(sum);
		System.out.println(sqSum);
	}
}
