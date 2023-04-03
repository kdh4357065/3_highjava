package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MybatisSqlSessionFactory;

public class BoardDao implements daoInterface{
	//1번(singlton만들기)
	private static BoardDao dao;
	//2번
	private BoardDao() {}
	//3번
	public static BoardDao getInstance() {
		if(dao == null) dao = new BoardDao();
		return dao;
	}
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.insert("board.insertBoard", boardVo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
	
	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0; //반환값 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.delete("board.deleteBoard", boardNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
	
	@Override
	public int updateBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0; //반환값 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.update("board.updateBoard", boardVo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
	
	@Override
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO boardVo = null; //반환값 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			boardVo = session.selectOne("board.getBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return boardVo;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		SqlSession session = null;
		List<BoardVO> boardList = null; //반환값 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			boardList = session.selectList("board.getAllBoardList");
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return boardList;
	}
	
	@Override
	public List<BoardVO> getSearchBoardList(String title) {
		SqlSession session = null;
		List<BoardVO> boardList = null; //반환값 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			boardList = session.selectList("board.getSearchBoardList", title);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return boardList;
	}
	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0; //반환값 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.update("board.setCountIncrement", boardNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
}
