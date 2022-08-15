package com.gfg.java;

public class MaxInArray {
	public static void main(String args[]) {
		int arr[] = {5,7,6,8};
		int max=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++) {
			if(max<arr[i]) {
				max = arr[i];
			}
		}
		System.out.println(max);
	}
}
