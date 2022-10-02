package Inheritance;

public class BikeRunner {
	public static void main(String args[]) {
		MountainBike mtb = new MountainBike(3, 100, 25);
		Bicycle obj = new MountainBike(3,100,25);
		System.out.println(obj.printInfo());
		System.out.println(mtb.printInfo());
		mtb.speedUp(24);
		System.out.println(mtb.printInfo());
		mtb.setHeight(50);
		System.out.println(mtb.printInfo());
	}
}
