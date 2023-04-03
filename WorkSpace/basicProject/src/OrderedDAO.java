package basicProject;


import java.sql.*;
import java.util.*;

public class OrderedDAO {
	public List<OrderedVO> selectList(String searchWord) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    rpad(A.PROD_ID,2), ");
		builder.append("    rpad(A.prod_name,32), ");
		builder.append("    rpad(A.prod_price,6), ");
		builder.append("    rpad(b.remain_j_99,6) ");
		builder.append("FROM ");
		builder.append("    prod a, ");
		builder.append("    remain b ");
		builder.append("WHERE ");
		builder.append("    a.prod_id = b.prod_id ");
		
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<OrderedVO> list = new ArrayList<>();
		while(resultSet.next()) {
			String id = resultSet.getString("prod_id");
			String name = resultSet.getString("PROD_NAME");
			int price = resultSet.getInt("PROD_PRICE");
			int prodRemain = resultSet.getInt("remain_j_99");
			list.add(new OrderedVO(id,name,price,prodRemain));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	public List<OrderedVO> searchLists(String searchId) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    mem_id, ");
		builder.append("    mem_name, ");
		builder.append("    mem_add, ");
		builder.append("    mem_hp, ");
		builder.append("    mem_mileage ");
		builder.append("FROM ");
		builder.append("    member ");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<OrderedVO> list = new ArrayList<>();
		while(resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String name = resultSet.getString("mem_name");
			String add = resultSet.getString("mem_add");
			String hp = resultSet.getString("mem_hp");
			int mileage = resultSet.getInt("mem_mileage");
			if(searchId.equals(id)) {
			list.add(new OrderedVO(id,name,add,hp,mileage));
			}
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	public List<OrderedVO> serchOrder(String memId) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    e.order_id, ");
		builder.append("    d.cname, ");
		builder.append("    d.cqty, ");
		builder.append("    d.csum, ");
		builder.append("    e.mem_id, ");
		builder.append("    rpad(e.order_add,30,' ') as rorder, "); //
		builder.append("    e.order_pay ");
		builder.append("FROM ");
		builder.append("    ( ");
		builder.append("        SELECT ");
		builder.append("            b.order_id AS cid, ");
		builder.append("            rpad(a.prod_name,35) AS cname, "); //
		builder.append("            b.order_qty AS cqty, ");
		builder.append("            SUM(a.prod_price * b.order_qty) AS csum ");
		builder.append("        FROM ");
		builder.append("            prod a, ");
		builder.append("            order_detail b ");
		builder.append("        WHERE ");
		builder.append("            a.prod_id = b.prod_id ");
		builder.append("        GROUP BY ");
		builder.append("            b.order_id, ");
		builder.append("            rpad(a.prod_name,35), "); //
		builder.append("            b.order_qty ");
		builder.append("        ORDER BY ");
		builder.append("            1 ");
		builder.append("    ) d, ");
		builder.append("    orders e ");
		builder.append("WHERE ");
		builder.append("    d.cid = e.order_id ");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<OrderedVO> list = new ArrayList<>();
		while(resultSet.next()) {
			String orderId = resultSet.getString("order_id");
			String id = resultSet.getString("mem_id");
			String orderName = resultSet.getString("cname");
			String orderAdd = resultSet.getString("rorder");
			String orderPay = resultSet.getString("order_pay");
			int orderQty = resultSet.getInt("cqty");
			int orderSum = resultSet.getInt("csum");
			if(memId.equals(id)){
				list.add(new OrderedVO(orderId,id,orderName,orderAdd,orderPay,orderQty,orderSum));
			}
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	//상품별 재고조회
	public List<OrderedVO> selectRemain(OrderedVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
		String user = "basic_project";
		String password = "java";
		Connection connection = DriverManager.getConnection(url,user,password);
		Statement statement = connection.createStatement();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    RPAD(b.prod_id,4) AS prod_id, ");
		builder.append("    RPAD(b.prod_name,32) AS prod_name, ");
		builder.append("    RPAD(a.remain_j_99,20) AS remain_j_99 ");
		builder.append("FROM ");
		builder.append("    remain a, ");
		builder.append("    prod b ");
		builder.append("WHERE ");
		builder.append("    a.prod_id = b.prod_id ");
		String sql = builder.toString();
		ResultSet resultSet = statement.executeQuery(sql);
		List<OrderedVO> list = new ArrayList<>();
		while(resultSet.next()) {
			String prodId = resultSet.getString("prod_id");
			String prodName = resultSet.getString("prod_name");
			int prodRemain = resultSet.getInt("remain_j_99");
			list.add(new OrderedVO(prodId, prodName, prodRemain));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
		
	}
}
