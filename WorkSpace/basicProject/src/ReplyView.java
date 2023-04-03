package basicProject;

import java.util.Scanner;

public class ReplyView {
	public ReplyVO inputReply(int selectNo, Scanner scanner) {
		String content = "";
		System.out.println("내용을 모두 작성하신 후 0을 입력해주세요.(줄바꿈 가능)");
		System.out.print("> ");
		for (int i = 0;; i++) {
			String input = scanner.nextLine();
			if ("0".equals(input)) {
				break;
			}
			if (i != 0) {
				content += "\n";
			}
			content += input;
		}
		return new ReplyVO(selectNo, content);
	}
	
	public boolean printSelectReply(ReplyVO vo) {
		if(vo.getRegistryDate() == null) {
			System.out.println("아직 답변이 등록되지 않았습니다.");
			return false;
		} else {
			System.out.println("▰─────────────────────────────────────────────────▰");
			System.out.println(" ╰┈➤ 문의사항 답변 드립니다.    관리자   " + vo.getRegistryDate());
			System.out.println("---------------------------------------------------");
			System.out.println(vo.getContent());
			System.out.println("▰─────────────────────────────────────────────────▰");
			return true;
		}
	}
}
