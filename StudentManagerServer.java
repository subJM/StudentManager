package jdbc;

import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class StudentManagerServer  {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			//Properties �� Ű�� ������ ���������� ������ �Ǳ� ������ ��ü�� ������
			Properties properties = new Properties();
			//�ڷ��ּ� ��������(���� ��θ� �����θ� ��������, ��
			String filePath = StudentManager.class.getResource("db.properties").getPath();
			//�ڵ�ȭ�Ǿ��ִ� ������ �б����� URLDecoder �� �̿��Ѵ�.
			filePath = URLDecoder.decode(filePath,"utf-8");
			//������ ��ü�� �̿��� ������ �о �����´�.
			properties.load(new FileReader(filePath));
			
			//���ε�
			//���ε��� ���� �� Ű�� ���� �����´�
			String driver = properties.getProperty("Driver");
			String url = properties.getProperty("URL");
			String userID = properties.getProperty("userID");
			String userPassword = properties.getProperty("userPassword");
			
			//����̹� Ȱ��ȭ
			Class.forName(driver);
			con = DriverManager.getConnection(url, userID, userPassword);
			
			
		} catch (Exception e) {
			System.out.println("DB���� ����");
			e.printStackTrace();
		}
		return con;
		
		
	}

}