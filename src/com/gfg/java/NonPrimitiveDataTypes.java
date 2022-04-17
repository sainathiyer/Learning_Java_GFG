package com.gfg.java;

public class NonPrimitiveDataTypes {
	
	public static void main(String args[]) {
		Point p = new Point();
		p.x=10;
		p.y=20;
		System.out.println(p.x+" "+p.y);
		System.out.println(p.a+" "+p.b);
	}
}

class Point{
	int x;
	int y;
	int a;
	int b;
}
