package com.gfg.java;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StringPatternSearch {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String txt = sc.nextLine();
		String pat = sc.next();
		sc.close();
		Set<Integer> sts = new HashSet<>();
		for(int i=0;i<txt.length();i++) {
			if(txt.indexOf(pat,i)>=0) {
			sts.add(txt.indexOf(pat,i));
			}
		}
		System.out.println(sts);
	}
}
