package com.gfg.java;

public class StringBuilderVsString {
	public static void main(String args[]) {
		String s1 = "geeks";
		String s2 = s1;
		s1 = s1.concat("forgeeks");
		if(s1==s2) {
			System.out.println("Same");
		}else {
			System.out.println("Not Same");
		}
		StringBuilder sb1 = new StringBuilder("geeks");
		StringBuilder sb2 = sb1;
		sb1 = sb1.append("forgeeks");
		if(sb1==sb2) {
			System.out.println("Same");
		}else {
			System.out.println("Not Same");
		}
	}

}
