package com.gfg.java;

public class StringBuilderBufferMethods {
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
		sb1.insert(0, "false");
		System.out.println(sb1);
		sb1.setCharAt(0,'g');
		System.out.println(sb1);
		sb1.reverse();
		System.out.println(sb1);
		sb1.deleteCharAt(0);
		System.out.println(sb1);
		sb1.delete(0, 4);
		System.out.println(sb1);
		System.out.println(sb1.capacity());
		sb1.replace(0, 3, "sai");
		System.out.println(sb1);
	}
}
