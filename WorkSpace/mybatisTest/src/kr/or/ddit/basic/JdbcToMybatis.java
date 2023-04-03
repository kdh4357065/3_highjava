package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisSqlSessionFactory;

/*
 LPROD테이블에 새로운 데이터 추가하기
 
 Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
 Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.
 
 입력 받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 
 --> JDBC예제 중 JdbcTest05.java를 myBatis용으로 변경하시오
 --> mapper파일명은 'jdbc-mapper.xml'로 한다.
 */

public class JdbcToMybatis {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		/*Reader rd = null;
		SqlSessionFactory sqlSessionFactory = null;
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
		}*/
		//--------------------------------------------------------------
		SqlSession session = null;
		
		try {
			//session = sqlSessionFactory.openSession();
			session = MybatisSqlSessionFactory.getSqlSession();
			
			//제일 큰 값 보다 1 큰 값 구하기
			int nexeId = session.selectOne("lprod2.getMexId");
			
			//LPROD_GU값을 입력 받아 중복되면 다시 입력 받기
			String gu;
			int count = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next();
				
				count = session.selectOne("lprod2.getLprodCount", gu);
				
				if(count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "은(는) 이미 등록된 코드입니다.");
					System.out.println("다른 상품 분류 코드를 다시 입력하세요...");
					System.out.println();
				}
			}while(count>0);
			
			System.out.print("상품 분류명(LRPOD_NM) 입력 >> ");
			String nm = scan.next();
			
			//입력값들을 VO에 저장하기
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(nexeId);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			int cnt = session.insert("lprod2.insertLprod", lvo);
			
			if(cnt>0) {
				System.out.println("등록 성공!!!");
			}else {
				System.out.println("등록 실패~~~");
			}
		} finally {
			session.commit();
			session.close();
		}

//		System.out.println("insert 작업 시작...");
//		String lprodGu;
//		String lprodNm;
//		
//		//gu중복 체크
//		while (true) {
//			System.out.print("Lprod_gu 입력 >> ");
//			lprodGu = scan.next();
//
//			try {
//				session = sqlSessionFactory.openSession();
//
//				int lvo3 = session.selectOne("lprod2.checkGu", lprodGu);
//				
//				if(lvo3 == 0) {
//					break;
//				}else {
//					System.out.println("중복된 데이터가 있습니다. 다시 입력하세요.");
//					System.out.println();
//				}
//			} finally {
//				// SqlSession을 생성할 때 AutoCommit이 비활성화된 상태일 때는 commit을 직접 실행해야 한다.
//				session.commit();
//
//				// 작업이 완료되면 SqlSession객체를 닫아준다.
//				session.close();
//			}
//		}
//		
//		System.out.print("Lprod_nm 입력 >> ");
//		lprodNm = scan.next();
//		
//		LprodVO lprodVo = new LprodVO();
//		lprodVo.setLprod_gu(lprodGu);
//		lprodVo.setLprod_nm(lprodNm);
//		try {
//			session= sqlSessionFactory.openSession();
//			
//			int insertCnt = session.insert("lprod2.inLprod", lprodVo);
//			
//			if(insertCnt>0) {
//				System.out.println("insert 작업 성공!!!");
//			}else {
//				System.out.println("insert 작업 실패~~~");
//			}
//		} finally {
//			//SqlSession을 생성할 때 AutoCommit이 비활성화된 상태일 때는 commit을 직접 실행해야 한다.
//			session.commit();
//			
//			//작업이 완료되면 SqlSession객체를 닫아준다.
//			session.close();
//		}
	}
}
