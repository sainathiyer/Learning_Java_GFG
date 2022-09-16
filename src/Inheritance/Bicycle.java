package Inheritance;

//Super Class or Parent Class
public class Bicycle {
	public int gear;
	public int speed;

	public Bicycle(int gear, int speed) {
		this.gear = gear;
		this.speed = speed;
	}

	public void applyBrake(int decrement) {
		speed -= decrement;
	}

	public void speedUp(int increment) {
		speed += increment;
	}

	public String printInfo() {
		return ("No of Gears = " + gear + "\n" + "Speed of the bicycle is = " + speed);
	}
}
