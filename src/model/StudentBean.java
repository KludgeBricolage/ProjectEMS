package model;

public class StudentBean {
	private String studentId;
	private String firstName;
	private String lastName;
	private String course;
	
	public StudentBean(String studentId, String firstName, String lastName, String course) {
		setStudentId(studentId);
		setFirstName(firstName);
		setLastName(lastName);
		setStudentId(studentId);
	}
	//Getters and Setters
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
}
