package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MybatisSqlSessionFactory;

public class MemberDaoImpl implements IMemberDao {
	// 1번
	private static MemberDaoImpl dao;

	// 2번
	private MemberDaoImpl() {

	}

	// 3번
	public static MemberDaoImpl getInstance() {
		if (dao == null)
			dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.insert("member.insertMember", memVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.delete("member.deleteMember", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.update("member.updateMember", memVo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> memList = null;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			memList = session.selectList("member.allMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.selectOne("member.checkId", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.update("member.updateMember2", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {

		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.update("member.updateMember3", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

}
