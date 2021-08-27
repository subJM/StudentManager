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
			//Properties 는 키와 값으로 나뉘어져서 저장이 되기 때문에 객체를 생성함
			Properties properties = new Properties();
			//자료주소 가져오기(절대 경로면 절대경로를 가져오고, 동
			String filePath = StudentManager.class.getResource("db.properties").getPath();
			//코드화되어있는 문서를 읽기위해 URLDecoder 를 이용한다.
			filePath = URLDecoder.decode(filePath,"utf-8");
			//생성된 객체를 이용해 파일을 읽어서 가져온다.
			properties.load(new FileReader(filePath));
			
			//바인딩
			//바인딩을 위해 각 키와 값을 가져온다
			String driver = properties.getProperty("Driver");
			String url = properties.getProperty("URL");
			String userID = properties.getProperty("userID");
			String userPassword = properties.getProperty("userPassword");
			
			//드라이버 활성화
			Class.forName(driver);
			con = DriverManager.getConnection(url, userID, userPassword);
			
			
		} catch (Exception e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		}
		return con;
		
		
	}

}