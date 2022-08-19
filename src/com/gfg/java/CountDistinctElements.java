package com.gfg.java;

public class CountDistinctElements {
	public static void main(String args[]) {
		int arr[] = {10,20,30,10,20};
		int count=0;
		boolean isDistinct = true;
		for(int i=0;i<arr.length;i++) {
			for(int j = i-1;j>=0;j--) {
				if(arr[i]==arr[j]) {
					isDistinct = false;
					break;
				}
			}
			if(isDistinct == true) {
				count++;
			}
		}
		System.out.println(count);
	}
}
