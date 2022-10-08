package com.gfg.java;

import java.util.Arrays;

public class SecondMaxElement {
	public static void main(String args[]) {
		int arr1[] = {1,2,3,4,56,7,};
		Arrays.sort(arr1);
		System.out.println(arr1[arr1.length-2]);
	}

}
