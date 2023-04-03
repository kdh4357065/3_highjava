package kr.or.ddit.util2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil4 {
	private static ResourceBundle bundle; // ResourceBundle객체 변수 선언
	static {
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo"); // 객체 생성

		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	//Connection객체를 반환하는 메서드
		public static Connection getConnection() {
			try {
				return DriverManager.getConnection(
						bundle.getString("url"),
						bundle.getString("user"),
						bundle.getString("pass"));
			} catch (SQLException e) {
				System.out.println("DB연결 실패!!!");
				return null;
			}
		}
}
