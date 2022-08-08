package com.gfg.java;

public class MethodsInJava {
	public static void main(String args[]) {
		Coordinate p = new Coordinate();
		p.x = 5; p.y = 10;
		System.out.println(p.x+ " " + p.y);
		fun(p);
		System.out.println(p.x+ " " + p.y);
	}
	public static void fun(Coordinate p) {
		//p = new Coordinate();
		p.x=10;
		p.y=10;
	}
}

