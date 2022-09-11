package Encapsulation;

public class AccessorClass {
	public static void main(String args[]) {
		Student std = new Student("Zero",1);
		std.setName("Sainath");
		std.setCGPA(9);
		Student.setNumberOfStudents(10);
		System.out.println(std.getName());
		System.out.println(std.getCGPA());
		System.out.println(Student.getNumberOfStudents());
	}
}
