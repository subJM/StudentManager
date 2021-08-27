package jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DBController {
	// 삭제
	public static int deleteStudentMemberTBL(String deleteName) {
		Connection con = StudentManagerServer.getConnection();
		String deletQuery = "DELETE FROM studentmanagement.studentmembertbl WHERE studentName=?";
		PreparedStatement preparedStatement = null;
		int count = 0;

		try {
			preparedStatement = con.prepareStatement(deletQuery);
			preparedStatement.setString(1, deleteName);
			count = preparedStatement.executeUpdate();
			

		} catch (Exception e) {
			System.out.println(deleteName + "님이 존재하지 않거나 잘못입력하셨습니다.");
			e.printStackTrace();
		}
		return count;
	}

	public static void updateStudentNameTBL(String afterName, String studentName) {
		Connection con = null;
		PreparedStatement prepareStatement = null;
		con = StudentManagerServer.getConnection();
		String updateQuery = "UPDATE studentMemberTBL set studentName = ? where studentName = ? ";
		prepareStatement = null;

		try {
			prepareStatement = con.prepareStatement(updateQuery);
			prepareStatement.setString(1, afterName);
			prepareStatement.setString(2, studentName);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Vector<StudentMemberTBL> updateStudentScoreTBL(
			String studentName, int math, int english,int korean) {
		Vector<StudentMemberTBL> list = new Vector<>();
		Connection con = StudentManagerServer.getConnection();
		PreparedStatement prepareStatement = null;
		String updateQuery = "select func_studentmembertbl(?,?,?,?)";
		int count = 0;

		ResultSet resultSet = null;
		try {
			prepareStatement = con.prepareStatement(updateQuery);
			prepareStatement.setString(1, studentName);
			prepareStatement.setInt(2, math);
			prepareStatement.setInt(3, english);
			prepareStatement.setInt(4, korean);
			resultSet = prepareStatement.executeQuery();
			try {

				String Name = resultSet.getString(1);
				int inMath = resultSet.getInt(2);
				int inEnglish = resultSet.getInt(3);
				int inKorean = resultSet.getInt(4);
				int inTotalScore = resultSet.getInt(5);
				double inAvg = resultSet.getDouble(6);
				String inGrade = resultSet.getString(7);

				StudentMemberTBL studentMemberTBL = new StudentMemberTBL(Name, inMath, inEnglish, inKorean,
						inTotalScore, inAvg, inGrade);
				list.add(studentMemberTBL);

			} catch (SQLException e) {

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static Vector<StudentMemberTBL> searchStudentMemberTBL(String studentName) {
		Vector<StudentMemberTBL> searchlist = new Vector<>();
		Connection con = StudentManagerServer.getConnection();
		PreparedStatement preparedStatement = null;
		String searchQuery = "select * from studentMemberTBL where studentName = ?";
		ResultSet resultSet = null;
		int count = 0;
		try {
			preparedStatement = con.prepareStatement(searchQuery);
			preparedStatement.setString(1, studentName);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String Name = resultSet.getString(1);
				int math = resultSet.getInt(2);
				int english = resultSet.getInt(3);
				int korean = resultSet.getInt(4);
				int totalScore = resultSet.getInt(5);
				double average = resultSet.getDouble(6);
				String grade = resultSet.getString(7);
				
				StudentMemberTBL studentMemberTBL = new StudentMemberTBL(Name, math, english, korean,totalScore, average,grade);
				searchlist.add(studentMemberTBL);
			}
			
		} catch (Exception e) {
			System.out.println("검색 오류입니다.");
			
		}
		return searchlist;
	};
	
	public static Vector<StudentMemberTBL> studentTotalScoreTBL() {
		Vector<StudentMemberTBL> list = new Vector<>();
		Connection con = StudentManagerServer.getConnection();
		String insertQuery = "select * from studentMemberTBL order by totalScore desc";
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			prepareStatement = con.prepareStatement(insertQuery);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				String studentName = resultSet.getString(1);
				int math = resultSet.getInt(2);
				int english = resultSet.getInt(3);
				int korean = resultSet.getInt(4);
				int totalScore = resultSet.getInt(5);
				double average = resultSet.getDouble(6);
				String grade = resultSet.getString(7);
				
				list.add(new StudentMemberTBL(studentName, math, english, korean, totalScore, average, grade));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static int insertStudentMemberTBL(String studentName,int math,int english,int korean) {
		int totalScore = 0;
		double average = 0.0;
		String grade = null;
		int count=0;
		
		totalScore = (math + english + korean);

		average = (totalScore / (double) 3.0);

		StudentMemberTBL studentMemberTBL = new StudentMemberTBL(
				studentName, math, english, korean, totalScore,	average, grade);
		Connection con = StudentManagerServer.getConnection();
		String insertQuery = "call pro_updateStudentmembertbl(?,?,?,?)";
		PreparedStatement prepareStatement = null;

		try {

			prepareStatement = con.prepareStatement(insertQuery);
			prepareStatement.setString(1, studentMemberTBL.getStudentName());
			prepareStatement.setInt(2, studentMemberTBL.getMath());
			prepareStatement.setInt(3, studentMemberTBL.getEnglish());
			prepareStatement.setInt(4, studentMemberTBL.getKorean());
			
			count = prepareStatement.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
