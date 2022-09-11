package Encapsulation;

public class Student {
	
	private String name;
	private int CGPA;
	private static int numberOfStudents;
	
	public Student(String name, int CGPA) {
		this.name = name;
		this.CGPA = CGPA;
	}
	
	public static int getNumberOfStudents() {
		return numberOfStudents;
	}

	public static void setNumberOfStudents(int numberOfStudents) {
		Student.numberOfStudents = numberOfStudents;
	}

	public int getCGPA() {
		return CGPA;
	}

	public void setCGPA(int grade) {
		this.CGPA = grade;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
}
