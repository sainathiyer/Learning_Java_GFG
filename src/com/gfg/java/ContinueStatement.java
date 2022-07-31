package com.gfg.java;
import java.util.*;

public class ContinueStatement {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<5;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			if(y==0) {
				continue;
			}
			System.out.println(x/y);
		}
		sc.close();
	}
}
