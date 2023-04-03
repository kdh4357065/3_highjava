package basicProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BoardDAO {
	// 게시글 insert
	public int insertBoard(BoardVO vo) {
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
			builder.append("    INSERT INTO board ( ");
			builder.append("        board_no, ");
			builder.append("        board_title, ");
			builder.append("        board_content, ");
			builder.append("        board_writer ");
			builder.append("    ) VALUES ( ");
			builder.append("     seq_board.NEXTVAL, ");
			builder.append("     '" + vo.getTitle() + "', ");
			builder.append("     '" + vo.getContent() + "', ");
			builder.append("     '" + vo.getWriter() + "' ");
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
	
	// 내 게시글 목록 select
	public List<BoardVO> selectMyBoards(){
		ResultSet resultSet = null;
		Connection connection = null;
		Statement statement = null;
		List<BoardVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ");
			builder.append("    rpad(a.board_no,4) board_no, ");
			builder.append("    CASE ");
			builder.append("            WHEN EXISTS ( ");
			builder.append("                SELECT ");
			builder.append("                    b.board_no ");
			builder.append("                FROM ");
			builder.append("                    reply b ");
			builder.append("                WHERE ");
			builder.append("                    a.board_no = b.board_no ");
			builder.append("            ) THEN rpad(a.board_title || ' ✔',32) ");
			builder.append("            ELSE rpad(a.board_title,32) ");
			builder.append("        END ");
			builder.append("    AS board_title, ");
			builder.append("    rpad(a.board_register_date,10) board_register_date ");
			builder.append("FROM ");
			builder.append("    board a ");
			builder.append("WHERE ");
			builder.append("    a.board_writer = '" + Application.getSession().getId() + "' ");
			String sql = builder.toString();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int no = resultSet.getInt("board_no");
				String title = resultSet.getString("board_title");
				String registerDate = resultSet.getString("board_register_date");
				list.add(new BoardVO(no, title, registerDate));
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
	
	// 선택한 내 게시글 내용 select
	public BoardVO selectMyBoardContent(int selectNo){
		ResultSet resultSet = null;
		Connection connection = null;
		Statement statement = null;
		BoardVO vo = new BoardVO(Application.getSession().getId());
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ");
			builder.append("    rpad(a.board_no,4) board_no, ");
			builder.append("    CASE ");
			builder.append("            WHEN EXISTS ( ");
			builder.append("                SELECT ");
			builder.append("                    b.board_no ");
			builder.append("                FROM ");
			builder.append("                    reply b ");
			builder.append("                WHERE ");
			builder.append("                    a.board_no = b.board_no ");
			builder.append("            ) THEN rpad(a.board_title || ' ✔',32) ");
			builder.append("            ELSE rpad(a.board_title,32) ");
			builder.append("        END ");
			builder.append("    AS board_title, ");
			builder.append("    a.board_content, ");
			builder.append("    rpad(a.board_register_date,10) board_register_date ");
			builder.append("FROM ");
			builder.append("    board a ");
			builder.append("WHERE ");
			builder.append("    a.board_writer = '" + Application.getSession().getId() + "' ");
			builder.append("    AND   a.board_no = " + selectNo + " ");
			String sql = builder.toString();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int no = resultSet.getInt("board_no");
				String title = resultSet.getString("board_title");
				String content = resultSet.getString("board_content");
				String registerDate = resultSet.getString("board_register_date");
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegisterDate(registerDate);
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
	
	// 관리자가 전체 게시글 보기
	public List<BoardVO> selectBoards() {
		ResultSet resultSet = null;
		Connection connection = null;
		Statement statement = null;
		List<BoardVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append(" SELECT ");
			builder.append("     rpad(a.board_no,4) board_no, ");
			builder.append("     CASE ");
			builder.append("             WHEN EXISTS ( ");
			builder.append("                 SELECT ");
			builder.append("                     b.board_no ");
			builder.append("                 FROM ");
			builder.append("                     reply b ");
			builder.append("                 WHERE ");
			builder.append("                     a.board_no = b.board_no ");
			builder.append("             ) THEN rpad(a.board_title || ' ✔',32) ");
			builder.append("             ELSE rpad(a.board_title,32) ");
			builder.append("         END ");
			builder.append("     AS board_title, ");
			builder.append("     rpad((c.mem_name || '(' || a.board_writer || ')'),20) as board_writer, ");
			builder.append("     rpad(a.board_register_date,10) board_register_date ");
			builder.append(" FROM ");
			builder.append("     board a, member c ");
			builder.append(" where a.board_writer=c.mem_id ");
			String sql = builder.toString();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int no = resultSet.getInt("board_no");
				String title = resultSet.getString("board_title");
				String writer = resultSet.getString("board_writer");
				String registerDate = resultSet.getString("board_register_date");
				list.add(new BoardVO(no, title, writer, registerDate, ""));
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
	
	// 관리자가 한 게시글 보기
	public BoardVO selectBoard(int selectNo) {
		ResultSet resultSet = null;
		Connection connection = null;
		Statement statement = null;
		BoardVO vo = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			StringBuilder builder = new StringBuilder();
			builder.append(" SELECT ");
			builder.append("     rpad(a.board_no,4) board_no, ");
			builder.append("     CASE ");
			builder.append("             WHEN EXISTS ( ");
			builder.append("                 SELECT ");
			builder.append("                     b.board_no ");
			builder.append("                 FROM ");
			builder.append("                     reply b ");
			builder.append("                 WHERE ");
			builder.append("                     a.board_no = b.board_no ");
			builder.append("             ) THEN rpad(a.board_title || ' ✔',32) ");
			builder.append("             ELSE rpad(a.board_title,32) ");
			builder.append("         END ");
			builder.append("     AS board_title, ");
			builder.append("     board_content, ");
			builder.append("     rpad((c.mem_name || '(' || a.board_writer || ')'),20) as board_writer, ");
			builder.append("     rpad(a.board_register_date,10) board_register_date, ");
			builder.append("     rpad(a.board_modify_date,10) board_modify_date ");
			builder.append(" FROM ");
			builder.append("     board a, member c ");
			builder.append(" where a.board_writer=c.mem_id ");
			builder.append("    AND   a.board_no = " + selectNo + " ");
			String sql = builder.toString();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int no = resultSet.getInt("board_no");
				String title = resultSet.getString("board_title");
				String writer = resultSet.getString("board_writer");
				String content = resultSet.getString("board_content");
				String registerDate = resultSet.getString("board_register_date");
				String modifyDate = resultSet.getString("board_modify_date");
				vo = new BoardVO(no, title, content, writer, registerDate, modifyDate);
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