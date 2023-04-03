package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	// 일을 시킬 DAO객체 변수 선언
	private IMemberDao dao;
	
	//1번
	private static MemberServiceImpl service;
	
	//2번
	private MemberServiceImpl() {
		//dao = new MemberDaoImpl(); //DAO객체 생성
		dao = MemberDaoImpl.getInstance(); //DAO객체 생성
	}
	
	// 3번
	public static MemberServiceImpl getInstance() {
		if (service == null) service = new MemberServiceImpl();
		return service;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}
	

	@Override
	public List<MemberVO> getAllMember() {
		return getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		return dao.updateMember3(dataMap);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return dao.updateMember2(paramMap);
	}

}
