package jdbc;
import java.util.Scanner;
import java.util.Vector;

public class StudentManager {
	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Vector<StudentMemberTBL> list = new Vector<>();
		boolean flag = false;
		int select = 0;

		while (!flag) {
			disPlayMenu();
			try {
				select = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요");
				continue;
			}

			switch (select) {
			case 1:
				insertStudentMember();
				break;
			case 2:
				updateStudent();
				break;
			case 3:
				searchStudentMember();
				break;
			case 4:
				studentTotalScore();
				break;
			case 5:
				deleteStudent();
				break;
			case 6:
				System.out.println("종료합니다");
				flag = true;
				break;
			default:
				System.out.println("숫자를 선택해주세요.");
				break;

			}// end of switch
		} // end of while
	}// end of main

	private static void searchStudentMember() {
		Vector<StudentMemberTBL> list = new Vector<>();
		int selectNumber = 0;
		String studentName = null;
		while (true) {
			System.out.print("검색할 학생 >>");
			try {
				studentName = scan.nextLine();
				if (studentName.length() <= 1 || studentName.length() >= 5) {
					System.out.println("잘못입력하셨습니다.");
					break;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("이름을 입력해주세요");
				return;
			}
		}

		list = DBController.searchStudentMemberTBL(studentName);
		System.out.println("======================================================");
		System.out.println("이름\t수학\t영어\t국어\t총점\t평균\t등급");
		System.out.println("======================================================");
		for (StudentMemberTBL smt : list) {
			System.out.println(smt.toString());
		}
	}

	private static void deleteStudent() {
		System.out.print("삭제할 사람의 이름 >>");
		String deleteName = scan.nextLine();
		int count = 0;
		
		count =DBController.deleteStudentMemberTBL(deleteName);
		if (count != 0) {
			System.out.println(deleteName + "님 삭제가 완료되었습니다.");
		} else {
			System.out.println(deleteName + "님이 존재하지 않거나 잘못입력하셨습니다.");
		}
	}

	private static void updateStudent() {
		
		int selectNumber = 0;
		String studentName = null;
		int math = 0;
		int english = 0;
		int korean = 0;
		int totalScore = 0;
		double average = 0.0;
		int count = 0;
		String afterName = null;
		
		while (true) {
			System.out.print("누구를 수정하시겠습니까? >>");
			studentName = scan.nextLine();
			if (studentName.length() >= 5 || studentName.length() <= 1) {
				System.out.println("잘못 입력하셨습니다");
				return;
			} else {
				break;
			}
		}
		System.out.println("===============");
		System.out.println("1.이름 2.점수 ");
		System.out.println("===============");
		System.out.print("선택 >>");
		try {
			selectNumber = Integer.parseInt(scan.nextLine());
			if(selectNumber == 0) {
				return;
			}
		} catch (Exception e) {
			System.out.println("잘못 입력하셨습니다.");
		}

		switch (selectNumber) {
		case 1:
			System.out.print("변경할 이름 >> ");
			afterName = scan.nextLine();

			DBController.updateStudentNameTBL(afterName, studentName);
			
			break;

		case 2:
			while (true) {
				System.out.print("수학점수 >> ");
				try {
					math = Integer.parseInt(scan.nextLine());
					if (math >= 0 && math <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("잘못입력하셨습니다.");
					return;
				}
			}
			while (true) {
				System.out.print("영어점수 >> ");
				try {
					english = Integer.parseInt(scan.nextLine());
					if (english >= 0 && english <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("잘못입력하셨습니다.");
					return;
				}
			}
			while (true) {
				System.out.print("국어점수 >> ");
				try {
					korean = Integer.parseInt(scan.nextLine());
					if (korean >= 0 && korean <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("잘못입력하셨습니다.");
					return;
				}
			}
			Vector<StudentMemberTBL> list = new Vector<>();
			list=DBController.updateStudentScoreTBL(studentName, math, english, korean);
			
			if(list ==null) {
				System.out.println("출력할 데이터가 없거나 검색범위 오류");
			}
			
			for(StudentMemberTBL smt : list) {
				System.out.println(smt);
			}
			break;
		}
	}

	private static void studentTotalScore() {
		Vector<StudentMemberTBL> list = new Vector<>();
		list = DBController.studentTotalScoreTBL();
		if (list.size() <= 0) {
			System.out.println("출력할 데이터가 없거나 검색범위 오류");
			return;
		}
		System.out.println("======================================================");
		System.out.println("이름\t수학\t영어\t국어\t총점\t평균\t등급");
		System.out.println("======================================================");
		for (StudentMemberTBL smt : list) {
			System.out.println(smt);
		}
	}

	private static void insertStudentMember() {
		Vector<StudentMemberTBL> list = new Vector<>();
		String studentName = null;
		int math = 0;
		int english = 0;
		int korean = 0;
		int count = 0;		

		while (true) {
			System.out.print("학생의 이름을 입력해주세요 >> ");
			studentName = scan.nextLine();
			if (studentName.length() < 2 || studentName.length() > 4) {
				System.out.println("이름을 다시 입력해주세요");
				continue;
			} else {
				break;
			}
		}

		while (true) {
			System.out.print("수학점수 >>");
			try {
				math = Integer.parseInt(scan.nextLine());
				if (math > 100) {
					System.out.println("다시 입력해주세요");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		}
		while (true) {
			System.out.print("영어점수 >> ");
			try {
				english = Integer.parseInt(scan.nextLine());
				if (english > 100) {
					System.out.println("다시 입력해주세요");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		}
		while (true) {
			System.out.print("한국어점수 >> ");
			try {
				korean = Integer.parseInt(scan.nextLine());
				if (korean > 100) {
					System.out.println("다시 입력해주세요");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		}
		count = DBController.insertStudentMemberTBL(studentName, math, english, korean);
		if (count != 0) {
			System.out.println(studentName + "님 삽입성공");
		} else {
			System.out.println(studentName + "님 삽입실패");
			return;
		}
	}

	private static void disPlayMenu() {
		System.out.println("\n\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("■■ 1.입력 |  2.수정 | 3.검색 | 4.전체 | 5.삭제 | 6.종료 ■■");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.print("번호선택 >> ");
	}

}