package com.gfg.java;

public class StringConcatCompare {
	public static void main(String args[]) {
		String s1 = "geeks";
		String s2 = s1;
		s1 = s1.concat("forgeeks");
		System.out.println(s1);
		System.out.println(s1==s2);
	}
}
