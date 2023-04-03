package basicProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReplyDAO {
	// 답변 insert
	public int insertReply(ReplyVO vo) {
		Connection connection = null;
		Statement statement = null;
		int insertResult = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			StringBuilder builder = new StringBuilder();
			builder.append("    INSERT INTO reply ( ");
			builder.append("        board_no, ");
			builder.append("        reply_content ");
			builder.append("    ) VALUES ( ");
			builder.append("        " + vo.getNo() + ", ");
			builder.append("        '" + vo.getContent() + "' ");
			builder.append("    ) ");
			String sql = builder.toString();
			statement = connection.createStatement();
			insertResult = statement.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertResult;
	}
	
	// 답변 select
	public ReplyVO selectReply(int selectNo) {
		ResultSet resultSet = null;
		Connection connection = null;
		Statement statement = null;
		ReplyVO vo = new ReplyVO(selectNo);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ");
			builder.append("    reply_content, ");
			builder.append("    to_char(reply_register_date,'yy/mm/dd') reply_register_date ");
			builder.append("FROM ");
			builder.append("    reply ");
			builder.append("WHERE ");
			builder.append("    board_no = " + selectNo + " ");
			String sql = builder.toString();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String content = resultSet.getString("reply_content");
				String registerDate = resultSet.getString("reply_register_date");
				vo.setContent(content);
				vo.setRegistryDate(registerDate);
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
		return vo;
	}
}
