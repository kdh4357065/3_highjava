package basicProject;

import java.util.List;
import java.util.Scanner;

public class MemberController {
	public MemberService service = new MemberService();
	public MemberView view = new MemberView();
	
	public void joinMember(Scanner scanner) throws Exception {
		MemberVO vo = view.inputJoinMember(scanner);
		MemberVO idCheck = service.duplicateIdCheck(vo);
		while (view.duplicateIdCheck(idCheck)) {
			vo = view.inputJoinMember(scanner);
			idCheck = service.duplicateIdCheck(vo);
		}
		int joinMember = service.joinMember(vo);
		view.joinResult(joinMember);
	}
	
	public MemberVO loginMember(Scanner scanner) throws Exception {// 내정보
		boolean flag = true;
		MemberVO member = null;
		while(flag) {
			MemberVO vo = view.inputLoginMember(scanner);
			member = service.loginMember(vo);
			flag = view.loginResult(member);
		}
		// 로그인한 사용자 정보 저장 => 세션
		Application.setSession(member);
		return member;
	}
	
	
	
	public void duplicateIdCheck(Scanner scanner) throws Exception {
		MemberVO vo = view.inputJoinMember(scanner);
		boolean flag = true;
		while (flag) {
			service.duplicateIdCheck(vo);
			flag = view.duplicateIdCheck(vo);
		}
	}

	public void updateMember(Scanner scanner) throws Exception {
		MemberVO vo = Application.getSession();
		MemberVO updateMember = view.updateMember(scanner, vo);
		int result = service.updateMember(updateMember);
		view.resultUpdate(result);
	}
	
	public void selectMember() {
		MemberVO vo = Application.getSession();
		view.printMember(vo);
	}
	
	public int updatePassword(Scanner scanner) throws Exception {
		List<String> list = view.updatePassword(scanner);
		int result = service.updatePassword(list);
		view.resultUpdate(result);
		return result;
	}
	
	public void listMember(MemberVO vo) throws Exception {
		List<MemberVO> list = service.listMember(vo);
		view.printListMember(list);
	}
	
	public MemberVO selectMyInfo() {
		return service.selectMyInfo();
	}
}
	
