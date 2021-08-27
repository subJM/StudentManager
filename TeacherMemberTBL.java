package jdbc;

import java.util.Objects;

public class TeacherMemberTBL {
	private String teacherName;
	private String className;
	private String subjectName;

	public TeacherMemberTBL(String teacherName, String className, String subjectName) {
		super();
		this.teacherName = teacherName;
		this.className = className;
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(teacherName);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TeacherMemberTBL) {
			TeacherMemberTBL teacherMemberTBL = (TeacherMemberTBL) obj;
			return this.teacherName.equals(teacherMemberTBL.teacherName);
		}
		return false;
	}

	@Override
	public String toString() {
		return "TeacherMemberTBL [teacherName=" + teacherName + ", className=" + className + ", subjectName="
				+ subjectName + "]";
	}

}
