package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * myBatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서 실행하고
 * SqlSession객체를 반환하는 메서드를 갖는 클래스
 * 
 * @author PC-25
 *
 */
public class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory = null;
	
	static {
		Reader rd = null;
		try {
			//1-1. 환경 설정 파일을 읽어와 올 스트림 객체를 생성한다.
			//	   (이때 읽어올 환경설정 파일을 지정해 준다.)
			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			//1-2. 환경 설정 파일을 읽어와 환경 설정을 완성한 후 SQL문을 호출해서 
			//	   실행할 수 있는 객체를 생성하는 SqlSessionFactory객체를 생성한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패!!!");
			e.printStackTrace();
		}finally {
			if(rd!=null)try {rd.close();} catch(IOException e) {}
		}
	}
	
	/**
	 * SqlSessionFactory객체를 이용하여 SQL문을 처리할 SqlSession객체를 반환하는 메서드
	 * 
	 * @return
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
}
