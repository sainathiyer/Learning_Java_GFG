package Polymorphism;

public class DriverClass {
	public static void main(String args[]) {
		DriverClass dc = new DriverClass();
		System.out.println(dc.add(1, 2));
		System.out.println(dc.add(1, 2, 3));
	}
	
	public int add(int a, int b) {
		return a+b;
	}
	
	public int add(int a,int b,int c) {
		return a+b+c;
	}

}
