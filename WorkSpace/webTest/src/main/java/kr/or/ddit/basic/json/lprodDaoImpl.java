package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;

public class lprodDaoImpl {
	private static lprodDaoImpl dao;
	
	private lprodDaoImpl() {}
	
	public static lprodDaoImpl getInstance() {
		if(dao == null) dao = new lprodDaoImpl();
		return dao;
	}
	
	public List<lprodVO> allLpord() {
		SqlSession session = null;
		List<lprodVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			list = session.selectList("lprod.allLprod");
		} finally {
			session.close();
		}
		
		return list;
	}

}
