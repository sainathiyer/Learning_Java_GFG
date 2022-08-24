package com.gfg.java;

public class IsArraySorted {
	public static void main(String args[]) {
		int arr[] = {10,13,45,67,83};
		System.out.println(isSorted(arr));
	}
	
	public static boolean isSorted(int arr[]) {
		for(int i=1;i<arr.length;i++) {
			if(arr[i]<arr[i-1]) {
				return false;
			}
		}
		return true;
	}
}
