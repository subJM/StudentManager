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
				System.out.println("���ڸ� �Է����ּ���");
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
				System.out.println("�����մϴ�");
				flag = true;
				break;
			default:
				System.out.println("���ڸ� �������ּ���.");
				break;

			}// end of switch
		} // end of while
	}// end of main

	private static void searchStudentMember() {
		Vector<StudentMemberTBL> list = new Vector<>();
		int selectNumber = 0;
		String studentName = null;
		while (true) {
			System.out.print("�˻��� �л� >>");
			try {
				studentName = scan.nextLine();
				if (studentName.length() <= 1 || studentName.length() >= 5) {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					break;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("�̸��� �Է����ּ���");
				return;
			}
		}

		list = DBController.searchStudentMemberTBL(studentName);
		System.out.println("======================================================");
		System.out.println("�̸�\t����\t����\t����\t����\t���\t���");
		System.out.println("======================================================");
		for (StudentMemberTBL smt : list) {
			System.out.println(smt.toString());
		}
	}

	private static void deleteStudent() {
		System.out.print("������ ����� �̸� >>");
		String deleteName = scan.nextLine();
		int count = 0;
		
		count =DBController.deleteStudentMemberTBL(deleteName);
		if (count != 0) {
			System.out.println(deleteName + "�� ������ �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println(deleteName + "���� �������� �ʰų� �߸��Է��ϼ̽��ϴ�.");
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
			System.out.print("������ �����Ͻðڽ��ϱ�? >>");
			studentName = scan.nextLine();
			if (studentName.length() >= 5 || studentName.length() <= 1) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�");
				return;
			} else {
				break;
			}
		}
		System.out.println("===============");
		System.out.println("1.�̸� 2.���� ");
		System.out.println("===============");
		System.out.print("���� >>");
		try {
			selectNumber = Integer.parseInt(scan.nextLine());
			if(selectNumber == 0) {
				return;
			}
		} catch (Exception e) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}

		switch (selectNumber) {
		case 1:
			System.out.print("������ �̸� >> ");
			afterName = scan.nextLine();

			DBController.updateStudentNameTBL(afterName, studentName);
			
			break;

		case 2:
			while (true) {
				System.out.print("�������� >> ");
				try {
					math = Integer.parseInt(scan.nextLine());
					if (math >= 0 && math <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					return;
				}
			}
			while (true) {
				System.out.print("�������� >> ");
				try {
					english = Integer.parseInt(scan.nextLine());
					if (english >= 0 && english <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					return;
				}
			}
			while (true) {
				System.out.print("�������� >> ");
				try {
					korean = Integer.parseInt(scan.nextLine());
					if (korean >= 0 && korean <= 100) {
						break;
					} else {
						continue;
					}
				} catch (Exception e) {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					return;
				}
			}
			Vector<StudentMemberTBL> list = new Vector<>();
			list=DBController.updateStudentScoreTBL(studentName, math, english, korean);
			
			if(list ==null) {
				System.out.println("����� �����Ͱ� ���ų� �˻����� ����");
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
			System.out.println("����� �����Ͱ� ���ų� �˻����� ����");
			return;
		}
		System.out.println("======================================================");
		System.out.println("�̸�\t����\t����\t����\t����\t���\t���");
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
			System.out.print("�л��� �̸��� �Է����ּ��� >> ");
			studentName = scan.nextLine();
			if (studentName.length() < 2 || studentName.length() > 4) {
				System.out.println("�̸��� �ٽ� �Է����ּ���");
				continue;
			} else {
				break;
			}
		}

		while (true) {
			System.out.print("�������� >>");
			try {
				math = Integer.parseInt(scan.nextLine());
				if (math > 100) {
					System.out.println("�ٽ� �Է����ּ���");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue;
			}
		}
		while (true) {
			System.out.print("�������� >> ");
			try {
				english = Integer.parseInt(scan.nextLine());
				if (english > 100) {
					System.out.println("�ٽ� �Է����ּ���");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue;
			}
		}
		while (true) {
			System.out.print("�ѱ������� >> ");
			try {
				korean = Integer.parseInt(scan.nextLine());
				if (korean > 100) {
					System.out.println("�ٽ� �Է����ּ���");
					continue;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				continue;
			}
		}
		count = DBController.insertStudentMemberTBL(studentName, math, english, korean);
		if (count != 0) {
			System.out.println(studentName + "�� ���Լ���");
		} else {
			System.out.println(studentName + "�� ���Խ���");
			return;
		}
	}

	private static void disPlayMenu() {
		System.out.println("\n\n�����������������������������������������������������");
		System.out.println("��� 1.�Է� |  2.���� | 3.�˻� | 4.��ü | 5.���� | 6.���� ���");
		System.out.println("�����������������������������������������������������");
		System.out.print("��ȣ���� >> ");
	}

}