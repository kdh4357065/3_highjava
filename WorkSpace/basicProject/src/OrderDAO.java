package basicProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDAO {
	//선택한 상품번호의 이름, 가격 찾아서 list로 리턴하기 
	public ProdVO addCart(ProdVO addProd){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ");
			builder.append("    rpad(A.prod_name,32) as prod_name, ");
			builder.append("    rpad(A.prod_price,6) as prod_price, ");
			builder.append("    rpad(B.REMAIN_J_99,6) as REMAIN_J_99 ");
			builder.append("FROM ");
			builder.append("    prod A, remain B ");
			builder.append("WHERE ");
			builder.append("a.prod_id = '" + addProd.getProdNo() + "' ");
			builder.append("and A.PROD_ID=B.PROD_ID ");
			String sql = builder.toString();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String prodName = resultSet.getString("prod_name"); 
				int prodPrice = resultSet.getInt("prod_price");
				int prodRemain = resultSet.getInt("REMAIN_J_99");
				addProd.setProdName(prodName);
				addProd.setProdPrice(prodPrice);
				addProd.setProdRemain(prodRemain);
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
		return addProd;
	}

	//주문하기
	public int ordering(OrderVO vo) {
		Connection connection = null;
		Statement statement = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String orderId = format.format(date);
		int orderResult = 0;
		int totalPrice = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			for(ProdVO pvo : vo.getProdVO()) {
				totalPrice += pvo.getProdPrice() * pvo.getProdQty();
			}
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT ALL ");
			builder.append("    INTO orders  ");
			builder.append("VALUES( ");
			builder.append(" '" + orderId + "', ");
			builder.append(" '" + vo.getAddress() + "', ");
			builder.append(" '" + vo.getPaymentMethod() + "', ");
			builder.append(" '" + vo.getPhoneNumber() + "', ");
			builder.append("    '" + Application.getSession().getId() + "', ");	//로그인한 아이디
			builder.append(" " + totalPrice + ") ");
			for(int i=0; i<vo.getProdVO().size(); i++) {
				ProdVO pvo = vo.getProdVO().get(i);
				builder.append("INTO order_detail  ");
				builder.append("VALUES ( ");
				builder.append(" '" + orderId + "', ");
				builder.append(" '" + pvo.getProdNo() + "', ");
				builder.append(" " + pvo.getProdQty() + " ");
				builder.append(") ");
			}
			builder.append("select * from dual ");
			String sql = builder.toString();
			statement = connection.createStatement();
			orderResult = statement.executeUpdate(sql);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderResult;
	}
	public int cancelOrderDetail(String cancelOrderId) throws Exception{
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
	      String user = "basic_project";
	      String password = "java";
	      Connection connection = DriverManager.getConnection(url, user, password);
	      StringBuilder builder = new StringBuilder();
	      builder.append("DELETE FROM order_detail WHERE ");
	      builder.append("    order_id = ? ");
	      String sql = builder.toString();
	      PreparedStatement statement = connection.prepareStatement(sql);
	      statement.setString(1, cancelOrderId);

	      int count = statement.executeUpdate();
	      connection.close();
	      statement.close();
	      return count;
	   }
	   
	   public int cancelOrder(String cancelOrderId) throws Exception{
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
	      String user = "basic_project";
	      String password = "java";
	      Connection connection = DriverManager.getConnection(url, user, password);
	      StringBuilder builder = new StringBuilder();
	      builder.append("DELETE FROM orders WHERE ");
	      builder.append("    order_id = ? ");
	      String sql = builder.toString();
	      PreparedStatement statement = connection.prepareStatement(sql);
	      statement.setString(1, cancelOrderId);

	      int count = statement.executeUpdate();
	      connection.close();
	      statement.close();
	      return count;
	   }

}
