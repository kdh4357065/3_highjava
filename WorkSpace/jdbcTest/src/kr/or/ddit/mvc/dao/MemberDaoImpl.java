package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	//1번
	private static MemberDaoImpl dao;
	
	//2번
	private MemberDaoImpl(){
		
	}
	
	//3번
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; //반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember(MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL,MEM_ADDR)"
					+ "values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			memList = new ArrayList<>(); //List객체 생성
			while(rs.next()) {
				MemberVO memVo = new MemberVO(); //1개의 레코드가 저장될 VO객체 생성
				
				//ResultSet의 데이터를 꺼내와 VO객체에 셋팅한다.
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_pass(rs.getString("mem_pass"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				//VO객체를 List에 추가한다.
				memList.add(memVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		int count = 0; //반환값이 저장될 변수 선언
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select count(*) from mymember where mem_id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return count;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;  //반환값이 저장도리 변수
		
		try {
			conn = DBUtil3.getConnection();
			String temp = ""; //SQL문의 set 이후에 수정할 컬럼 설정하는 부분이 저장될 변수
			int num = 0;
//			for(String fieldName : dataMap.keySet()) {
//				if(!"".equals(temp)) {
//					temp += ", ";
//				}
//				temp += fieldName + " = '" + dataMap.get(fieldName) + "'";
//			}
			for(String fieldName : dataMap.keySet()) {
				//Map의 key값 중 'memId'는 검색할 ID값에 대한 정보이기 때문에 수정할 컬럼을 설정할 때는 포함하지 않는다.
				if (!"memId".equals(fieldName)) { 
					if (!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " = ?";
				}
			}
			
			String sql = "update mymember set " + temp + " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			int num2 = 1;
			for(String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) { 
				pstmt.setString(num2++, dataMap.get(fieldName));	
				}
			}
			pstmt.setString(num2, dataMap.get("emeId"));
			
			cnt = pstmt.executeUpdate();
			
			if(cnt!=0) {
				System.out.println("수정이 완료되었습니다.");
				System.out.println();
			}else {
				System.out.println("수정을 실패하였습니다....");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	//Map의 정보 ==> key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memId)
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;  //반환값이 저장도리 변수
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set " +paramMap.get("field")
			       + " = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return cnt;
	}

}
