package basicProject;

import java.util.List;

public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	public MemberVO loginMember(MemberVO vo) throws Exception {
		return dao.loginMember(vo);
		
	}
	public int joinMember(MemberVO vo) throws Exception {
		return dao.joinMember(vo);
	}
	
	public MemberVO duplicateIdCheck(MemberVO vo) throws Exception {
		return dao.duplicateIdCheck(vo);
	}

	public int updateMember(MemberVO vo) throws Exception {
		return dao.updateMember(vo);
	}
	
	public int updatePassword(List<String> list) throws Exception {
		return dao.updatePassword(list);
	}
	
	public List<MemberVO> listMember(MemberVO vo) throws Exception {
		return dao.listMember(vo);
	}
	
	public MemberVO selectMyInfo() {
		return dao.selectMyInfo();
	}
}
