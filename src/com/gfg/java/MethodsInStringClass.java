package com.gfg.java;

public class MethodsInStringClass {
	public static void main(String args[]) {
		
		// contains() function returns true if the substring is present
		String s1 = "geeksforgeeks";
		String s2 = "geeks";
		System.out.println(s1.contains(s2));
		
		// equals() function returns true if the content of both Strings are same
		String s3 = "geeksforgeeks";
		String s4 = "geeksforgeeks";
		System.out.println(s3.equals(s4));
		
		// compareTo() function: returns 0 if both the strings are exactly same
		// returns a positive number if s5 is lexicographically > s6
		// returns a negative number if s5 is lexicographically < s6
		String s5 = "geeksforgeeks";
		String s6 = "for";
		int res = s5.compareTo(s6);
		if(res==0) {
			System.out.println("Same");
		}
		if(res>0) {
			System.out.println("S5 is Greater");
		}
		if(res<0) {
			System.out.println("S5 is Smaller");
		}
		
		// indexOf() function returns a negative number if string s8 is not present in s7
		// Otherwise it returns index of first occurrence of s8 in s7
		String s7 = "geeksforgeeks";
		String s8 = "for";
		System.out.println(s7.indexOf(s8));
		
		// lastIndexOf() function
		String s9 = "geeksforgeeks";
		String s10 = "geeks";
		System.out.println(s9.lastIndexOf(s10));
	}
}
