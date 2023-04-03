package basicProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdDAO {
	//상품목록 select
		public List<ProdVO> selectProducts() {
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			List<ProdVO> list = new ArrayList<>();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
				String user = "basic_project";
				String password = "java";
				connection = DriverManager.getConnection(url, user, password);
				statement = connection.createStatement();
				StringBuilder builder = new StringBuilder();
				builder.append("SELECT rpad(A.PROD_ID,2) PROD_ID, ");
				builder.append("       rpad(A.PROD_NAME,32) PROD_NAME, ");
				builder.append("       rpad(A.PROD_PRICE,6) PROD_PRICE, ");
				builder.append("       rpad(B.REMAIN_J_99,6) REMAIN_J_99 ");
				builder.append("  FROM PROD A, REMAIN B ");
				builder.append(" WHERE A.PROD_ID=B.PROD_ID ");
				String sql = builder.toString();
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					String prodNo = resultSet.getString("PROD_ID");
					String prodName = resultSet.getString("PROD_NAME");
					int prodPrice = resultSet.getInt("PROD_PRICE");
					int prodRemain = resultSet.getInt("REMAIN_J_99");
					list.add(new ProdVO(prodNo, prodName, prodPrice, prodRemain) );
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(statement != null) {
						statement.close();
					}
					if(resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		
		public List<ProdVO> searchProds(String searchWord) {
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			List<ProdVO> list = new ArrayList<>();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
				String user = "basic_project";
				String password = "java";
				connection = DriverManager.getConnection(url, user, password);
				statement = connection.createStatement();
				StringBuilder builder = new StringBuilder();
				builder.append("SELECT rpad(A.PROD_ID,2) PROD_ID, ");
				builder.append("       rpad(A.PROD_NAME,32) PROD_NAME, ");
				builder.append("       rpad(A.PROD_PRICE,6) PROD_PRICE, ");
				builder.append("       rpad(B.REMAIN_J_99,6) PROD_REMAIN ");
				builder.append("  FROM PROD A, REMAIN B ");
				builder.append(" WHERE A.PROD_ID=B.PROD_ID ");
				builder.append("   and PROD_NAME like '%" + searchWord + "%' ");
				String sql = builder.toString();
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					String prodNo = resultSet.getString("PROD_ID");
					String prodName = resultSet.getString("PROD_NAME");
					int prodPrice = resultSet.getInt("PROD_PRICE");
					int prodRemain = resultSet.getInt("PROD_REMAIN");
					list.add(new ProdVO(prodNo, prodName, prodPrice, prodRemain));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(connection != null) {
						connection.close();
					}
					if(statement != null) {
						statement.close();
					}
					if(resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
}
