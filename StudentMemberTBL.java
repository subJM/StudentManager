package jdbc;

import java.util.Objects;

public class StudentMemberTBL {
	private String studentName;
	private int math;
	private int english;
	private int korean;
	private int totalScore;
	private double average;
	private String grade;
	
	public StudentMemberTBL(String studentName, int math, int english, int korean, int totalScore, double average, String grade) {
		super();
		this.studentName = studentName;
		this.math = math;
		this.english = english;
		this.korean = korean;
		this.totalScore = totalScore;
		this.average = average;
		this.grade = grade;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(studentName);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StudentMemberTBL) {
			StudentMemberTBL studentMemberTBL = (StudentMemberTBL) obj;
			return this.studentName.equals(studentMemberTBL.studentName);
			
		}
		return false;
	}
	@Override
	public String toString() {
		return studentName + "\t" + math	+ "\t" + english + "\t" + korean + 
			"\t" + totalScore + "\t" + String.format("%.2f", average) + "\t" +  grade;
	}
	
}