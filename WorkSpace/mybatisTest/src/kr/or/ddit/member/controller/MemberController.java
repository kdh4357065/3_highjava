package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;



public class MemberController {
	private Scanner scan;
	private IMemberService service; // Service객체 변수 선언

	public MemberController() {
		scan = new Scanner(System.in);
		service = MemberServiceImpl.getInstance(); // Service객체 생성
	}

	public static void main(String[] args) {
		new MemberController().startMember();
	}

	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메소드
	private int displayMenu() {
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("   1. 자 료 추 가");
		System.out.println("   2. 자 료 삭 제");
		System.out.println("   3. 자료수정 (전체항목수정)");
		System.out.println("   4. 전 체 자 료 출 력");
		System.out.println("   5. 자료수정2(수정항목선택)");
		System.out.println("   6. 자료수정3(입력항목만수정)");
		System.out.println("   0. 작 업 끝");
		System.out.println("----------------------------------");
		System.out.print("작업 선택 >> ");

		return Integer.parseInt((scan.nextLine()));
	}

	// 작업을 시작하는 메소드
	public void startMember() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1:
				insertMember();
				break; // 추가
			case 2:
				deleteMeber();
				break; // 삭제
			case 3:
				updateMember();
				break; // 수정
			case 4:
				displayAllMember();
				break; // 전체 출력
			case 5:
				updateMember2();
				break; // 수정2
			case 6:
				updateMember3();
				break; // 수정3
			case 0:
				System.out.println("작업을 마칩니다...");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}

	// 회원 정보를 수정하는 메서드 ==> 원하는 항목 1개만 수정하기
	private void updateMember2() {

		System.out.print("수정할 자료의 ID를 입력해주세요 >> ");
		String id = scan.nextLine();

		int count = service.getMemberCount(id);

		if (count == 0) {
			System.out.println("존재하지 않는 아이디입니다...");
			System.out.println();
			return;
		}

		int num;
		String updateField = null;
		String updateFieldTitle = null;
		do {
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1.비밀번호   2.회원이름   3.전화번호   4.회원주소");
			System.out.println("---------------------------------------");
			System.out.print("수정 항목 입력 >> ");
			num = Integer.parseInt(scan.nextLine());

			switch (num) {
			case 1:
				updateField = "mem_pass";
				updateFieldTitle = "비밀번호";
				break;
			case 2:
				updateField = "mem_name";
				updateFieldTitle = "회원이름";
				break;
			case 3:
				updateField = "mem_tel";
				updateFieldTitle = "전화번호";
				break;
			case 4:
				updateField = "mem_addr";
				updateFieldTitle = "회원주소";
				break;
			default:
				System.out.println("수정 항목을 잘못 선택했습니다.");
				System.out.println("다시 선택하세요");
			}
		} while (num < 1 || num > 4);
		System.out.println();
		System.out.print("새로운 " + updateFieldTitle + " >> ");
		String updateData = scan.nextLine();

		// 구성한 데이터를 Map에 추가한다.
		// Map의 정보 ==> key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memId)
		Map<String, String> paramMap = new HashMap<>(); // Map객체 생성
		paramMap.put("field", updateField); // 수정할 컬럼명
		paramMap.put("data", updateData); // 수정할 데이터
		paramMap.put("memId", id); // 검색할 회원ID

		int cnt = service.updateMember2(paramMap);

		if (cnt != 0) {
			System.out.println("수정 작업 성공!!!");
			System.out.println();
		} else {
			System.out.println("수정 작업 실패~~~");
			System.out.println();
		}
	}

	// 회원 정보를 수정하는 메서드 ==>입력한 항복
	private void updateMember3() {
		System.out.print("수정할 자료의 ID를 입력해주세요 >> ");
		String id = scan.nextLine();

		int count = service.getMemberCount(id);

		if (count == 0) {
			System.out.println("존재하지 않는 아이디입니다...");
			System.out.println();
			return;
		}

		// key값 : 수정할 컬럼명, value값 : 수정할 데이터값
		// 수정할 데이터값이 있ㅇ르 때만 Map에 추가된다.
		Map<String, String> dataMap = new HashMap<>();

		System.out.print("수정할 비밀번호 입력 >> ");
		String chagpass = scan.nextLine().trim();
		if (!"".equals(chagpass)) {
			dataMap.put("mem_pass", chagpass);
		}

		System.out.print("수정할 이름 입력 >> ");
		String chagname = scan.nextLine().trim();
		if (!"".equals(chagname)) {
			dataMap.put("mem_name", chagname);
		}

		System.out.print("수정할 전화번호 입력 >> ");
		String chagtel = scan.nextLine().trim();
		if (!"".equals(chagtel)) {
			dataMap.put("mem_tel", chagtel);
		}

		System.out.print("수정할 주소 입력 >> ");
		String chagadd = scan.nextLine().trim();
		if (!"".equals(chagadd)) {
			dataMap.put("mem_addr", chagadd);
		}
		
		if(dataMap.size() == 0) {
			System.out.println("수정할 내용을 하나도 선택하지 않았습니다.");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		// Map에 검색할 회원 ID값을 'memId'키값으로 넣어준다
		dataMap.put("memId", id);

		int cnt = service.updateMember3(dataMap);

		if (cnt != 0) {
			System.out.println("수정이 완료되었습니다.");
			System.out.println();
		} else {
			System.out.println("수정을 실패하였습니다....");
			System.out.println();
		}
	}

	// 전체 회원 정보를 출력하는 메서드
	private void displayAllMember() {

		List<MemberVO> memList = service.getAllMember();

		if (memList == null || memList.size() == 0) {
			System.out.println("    등록된 회원 정보가 하나도 없습니다...");
		} else {
			// 반복문을 이용하여 출력한다.
			for (MemberVO memVo : memList) {
				String memID = memVo.getMem_id();
				String memPass = memVo.getMem_pass();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();

				System.out.println(memID + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("------------------------------------");
			System.out.println("출력 끝....");
		}
	}

	// 회원정보를 수정하는 메서드 -- 전체 수정
	private void updateMember() {
		System.out.print("수정할 데이터의 아이디를 입력하세요 >> ");
		String id = scan.nextLine();

		int count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "은(는) 등록된 회원ID가 아닙니다.");
			System.out.println("다른 회원ID를 입력하세요...");
			System.out.println();
		} else {
			System.out.print("변경할 비밀번호 >> ");
			String newPass = scan.nextLine();

			System.out.print("변경할 이름 >> ");
			String newName = scan.nextLine();

			System.out.print("변경할 전화번호 >> ");
			String newTel = scan.nextLine();

			System.out.print("변경할 주소 >> ");
			String newAddr = scan.nextLine();

			// 입력한 자료를 VO객체에 저장한다.
			MemberVO memVo = new MemberVO(); // VO객체 생성
			memVo.setMem_id(id);
			memVo.setMem_pass(newPass);
			memVo.setMem_name(newName);
			memVo.setMem_tel(newTel);
			memVo.setMem_addr(newAddr);

			int cnt = service.updateMember(memVo);

			if (cnt > 0) {
				System.out.println(id + " 회원 정보 수정 완료!!!");
			} else {
				System.out.println(id + " 회원 정보 수정 실패~~~");
			}
		}
	}
	

	// 회원정보를 삭제하는 메서드
	private void deleteMeber() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.nextLine();

		int count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "은(는) 등록된 회원ID가 아닙니다.");
			System.out.println("다른 회원ID를 입력하세요...");
			System.out.println();
		} else {

			int cnt = service.deleteMember(id);

			if (cnt > 0) {
				System.out.println("삭제에 성공하였습니다.");
			} else {
				System.out.println("삭제에 실패하였습니다.");
			}
		}
	}

	// 자료를 추가하는 메소드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		String id = null;
		int count = 0;
		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
		while(true){
			System.out.print("회원ID >> ");
			id = scan.nextLine();
			count = service.getMemberCount(id);
			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
				System.out.println();
			}else {
				break;
			}
		}

		System.out.print("비밀번호 >> ");
		String pass = scan.next();

		System.out.print("회원이름 >> ");
		String name = scan.next();

		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 버퍼 비우기
		System.out.print("회원주소 >> ");
		String addr = scan.nextLine();

		// 입력한 자료를 VO객체에 담는다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);

		// Service의 insertMember()메소드를 호출해서 실행한다.
		int cnt = service.insertMember(memVo);

		if (cnt > 0) {
			System.out.println(id + " 회원 정보 추가 완료!!!");
		} else {
			System.out.println(id + " 회원 정보 추가 실패...");
		}

	}
}
