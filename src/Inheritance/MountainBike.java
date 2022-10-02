package Inheritance;

//Sub Class or Child Class
public class MountainBike extends Bicycle {

	// Mountain Bike Sub Class adds one more field
	public int seatHeight;

	// Constructor of Mountain Bike
	public MountainBike(int gear, int speed, int seatHeight) {
		// super class constructor should always be the first statement
		super(gear, speed);
		this.seatHeight = seatHeight;
	}

	public void setHeight(int newValue) {
		this.seatHeight = newValue;
	}
	
	@Override
	public String printInfo() {
		return (super.printInfo() + "\n" + "Height of the bicycle is = " + seatHeight);
	}

}
