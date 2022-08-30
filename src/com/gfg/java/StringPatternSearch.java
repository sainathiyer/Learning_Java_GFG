package com.gfg.java;

import java.util.Scanner;

public class StringPatternSearch {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String txt = sc.nextLine();
		String pat = sc.next();
		sc.close();
		int pos = txt.indexOf(pat);
		while(pos >= 0) {
			System.out.print(pos+" ");
			pos = txt.indexOf(pat,pos+1);
		}
	}
}
