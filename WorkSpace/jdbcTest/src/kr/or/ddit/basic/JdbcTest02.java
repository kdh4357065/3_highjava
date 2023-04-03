package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 문제) 사용자로부터 Lprod_id값을 입력 받아 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오
*/
public class JdbcTest02 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "KDH99", "java");
			
			System.out.print("아이디를 입력하세요 >> ");
			int inputId = scan.nextInt();
			
			String sql = "select * from lprod where "+inputId+" < LPROD_ID";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == SQL문 처리 결과 ==");
			while(rs.next()) {
				System.out.println("Lprod_id : "+rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : "+rs.getString(2));
				System.out.println("Lprod_nm : "+rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}

	}

}
