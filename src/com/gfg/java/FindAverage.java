package com.gfg.java;

public class FindAverage {
	public static void main(String args[]) {
		int arr[] = {4,7,8,12};
		float sum=0;
		float average=0.0f;
		for(int i=0;i<arr.length;i++) {
			sum += arr[i];
		}
		average = sum/arr.length;
		System.out.println(average);
	}
}
