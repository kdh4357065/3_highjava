package basicProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	// 로그인 - 전체 회원 조회 select
	public MemberVO loginMember(MemberVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.146.54:1521:xe", "basic_project",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    mem_id, ");
		builder.append("    mem_name, ");
		builder.append("    mem_add, ");
		builder.append("    mem_hp, ");
		builder.append("    mem_mileage ");
		builder.append("FROM ");
		builder.append("    member ");
		builder.append("WHERE ");
		builder.append("    mem_id = ? ");
		builder.append("    AND   mem_pw = func_md5_pw(?) ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getId());
		statement.setString(2, vo.getPassword());
		ResultSet resultSet = statement.executeQuery();
		MemberVO member = null;
		if (resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String name = resultSet.getString("mem_name");
			String add = resultSet.getString("mem_add");
			String hp = resultSet.getString("mem_hp");
			int mileage = resultSet.getInt("mem_mileage");
			member = new MemberVO(id, name, add, hp, mileage);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return member;
	}

	// 회원가입 insert
	public int joinMember(MemberVO vo) {
		Connection connection = null;
		PreparedStatement statement = null;
		int joinCount = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.146.54:1521:xe", "basic_project",
					"java");
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO member ( ");
			builder.append("    mem_id, ");
			builder.append("    mem_pw, ");
			builder.append("    mem_name, ");
			builder.append("    mem_add, ");
			builder.append("    mem_hp ");
			builder.append(") VALUES ( ");
			builder.append("    ?, ");
			builder.append("    func_md5_pw(?), ");
			builder.append("    ?, ");
			builder.append("    ?, ");
			builder.append("    ? ");
			builder.append(") ");

			String sql = builder.toString();
			statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getId());
			statement.setString(2, vo.getPassword());
			statement.setString(3, vo.getName());
			statement.setString(4, vo.getAdd());
			statement.setString(5, vo.getHp());
			joinCount = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return joinCount;
	}

	// id 중복 조회 select
	public MemberVO duplicateIdCheck(MemberVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.146.54:1521:xe", "basic_project",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    mem_id ");
		builder.append("FROM ");
		builder.append("    member ");
		builder.append("WHERE ");
		builder.append("    mem_id = ? ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getId());
		ResultSet resultSet = statement.executeQuery();
		MemberVO member = null;
		if (resultSet.next()) {// 같은 아이디가 존재하면 행 존재
			String id = resultSet.getString("mem_id");
			member = new MemberVO(id);
		}
		return member;
	}

	// 내정보 수정 update
	public int updateMember(MemberVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.146.54:1521:xe", "basic_project",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE member ");
		builder.append("    SET ");
		builder.append("        mem_name = ?, ");
		builder.append("        mem_add = ?, ");
		builder.append("        mem_hp = ? ");
		builder.append("WHERE ");
		builder.append("    mem_id = ? ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getName());
		statement.setString(2, vo.getAdd());
		statement.setString(3, vo.getHp());
		statement.setString(4, vo.getId());
		int updateCount = statement.executeUpdate();
		return updateCount;
	}

	// 비밀번호 수정 update
	public int updatePassword(List<String> list) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.146.54:1521:xe", "basic_project",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE member ");
		builder.append("    SET ");
		builder.append("        mem_pw = func_md5_pw(?) ");
		builder.append("WHERE ");
		builder.append("    mem_id = ? ");
		builder.append("    AND   mem_pw = func_md5_pw(?) ");

		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, list.get(1));
		statement.setString(2, Application.getSession().getId());
		statement.setString(3, list.get(0));
		int updatePasswordCount = statement.executeUpdate();
		return updatePasswordCount;
	}

	// 관리자 제외한 회원목록 조회 select
	public List<MemberVO> listMember(MemberVO vo) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.146.54:1521:xe", "basic_project",
				"java");
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    rpad(mem_id,10) as mem_id, ");
		builder.append("    rpad(mem_name,15) as mem_name, ");
		builder.append("    rpad(mem_add,45) as mem_add, ");
		builder.append("    rpad(mem_hp,15) as mem_hp, ");
		builder.append("    mem_mileage ");
		builder.append("FROM ");
		builder.append("    member ");
		builder.append("WHERE ");
		builder.append("    mem_id != 'admin' ");
		String sql = builder.toString();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<MemberVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String id = resultSet.getString("mem_id");
			String name = resultSet.getString("mem_name");
			String add = resultSet.getString("mem_add");
			String hp = resultSet.getString("mem_hp");
			int mileage = resultSet.getInt("mem_mileage");
			list.add(new MemberVO(id, name, add, hp, mileage));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	// 내 정보 불러오기
	public MemberVO selectMyInfo() {
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		MemberVO vo = new MemberVO();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.146.54:1521:xe";
			String user = "basic_project";
			String password = "java";
			connection = DriverManager.getConnection(url, user, password);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ");
			builder.append("	mem_id, ");
			builder.append("    mem_name, ");
			builder.append("    mem_add, ");
			builder.append("    mem_hp, ");
			builder.append("    mem_mileage ");
			builder.append("FROM ");
			builder.append("    member ");
			builder.append("WHERE ");
			builder.append("    mem_id = '" + Application.getSession().getId() + "' ");
			String sql = builder.toString();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String id = resultSet.getString("mem_id");
				String name = resultSet.getString("mem_name");
				String add = resultSet.getString("mem_add");
				String hp = resultSet.getString("mem_hp");
				int mileage = resultSet.getInt("mem_mileage");
				vo = new MemberVO(id, name, add, hp, mileage);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
