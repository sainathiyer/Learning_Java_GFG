package com.gfg.java;

public class CyclicString {
	public static void main(String args[]) {
		var str1 = "abcde";
		var str2 = "deabc";
		if(str1.length() == str2.length()) {
			str1 = str1.concat(str1);
			if(str1.indexOf(str2) != -1) {
				System.out.println("They are Cyclic Strings");
			} else {
				System.out.println("They are not Cyclic");
			}
		}
	}

}
