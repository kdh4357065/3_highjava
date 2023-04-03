package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 LRPOD테이블에 새로운 데이터 추가하기
 
 Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
 Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.
 
 입력 받은 Lprod_gu가 이미 등록되어 있음면 다시 입력 받아서 처리한다.
 */
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			/*
			 * Class.forName("oracle.jdbc.driver.OracleDriver"); conn =
			 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KDH99",
			 * "java");
			 */
			conn = DBUtil.getConnection();
			

			while (true) {
				System.out.println("정보 추가하기...");
				System.out.print("제품 아이디 입력>> ");
				String gu = scan.next();

				String sql = "select count(*) from lprod where lprod_gu= ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, gu);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					if (rs.getInt(1) != 1) {
						System.out.print("제품 종류 입력 >> ");
						String nm = scan.next();

						String sql2 = "select max(lprod_id)+1 from lprod";
						pstmt = conn.prepareStatement(sql2);

						rs = pstmt.executeQuery();
						int idNum = 0;
						
						while(rs.next()){
							idNum = rs.getInt(1);
						}

						System.out.println("부여 받은 아이디 : " + idNum);
						String sql3 = "insert into LPROD (LPROD_ID, LPROD_GU, LPROD_NM)" + "values(?, ?, ?)";

						pstmt = conn.prepareStatement(sql3);

						pstmt.setInt(1, idNum);
						pstmt.setString(2, gu);
						pstmt.setString(3, nm);
						
						pstmt.executeUpdate();
						System.out.println("등록이 완료 되었습니다.");
						return;
					} else {
						System.out.println("이미 등록된 아이디 입니다. 다시 입력하세요");
						System.out.println();
					}
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} /*
			 * catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
}
