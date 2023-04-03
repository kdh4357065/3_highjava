package basicProject;

import java.util.List;
import java.util.Scanner;

public class BoardView {
	// 등록할 게시글의 제목, 내용 입력받기
	public BoardVO inputBoard(Scanner scanner) {
		BoardVO vo = new BoardVO(Application.getSession().getId());
		System.out.println("\n[문의글 작성]");
	    System.out.println("▰─────────────────────────────────────────────────────────────────────▰");
		System.out.print("제목> ");
		vo.setTitle(scanner.nextLine());
		String content = "";
		System.out.println("----------------------------------------------------------------------");
	    System.out.println("(내용을 모두 작성하신 후 다음 줄에 0을 입력해주세요.(줄바꿈 가능))");
		System.out.print("내용> ");
		for (int i = 0;; i++) {
			String input = scanner.nextLine();
			if ("0".equals(input)) {
				break;
			}
			if (i == 0) {
				content += input;
				continue;
			}
			content += "\n" + input;
		}
		vo.setContent(content);
		System.out.println("▰─────────────────────────────────────────────────────────────────────▰");
		return vo;
	}
	
	// 내 게시글 목록 출력
	public void printMyBoards(List<BoardVO> list) {
		System.out.println("\n[내 문의내역]");
		System.out.println("-------------------------------------------------");
		System.out.println("번호    제목                            작성일자");
		System.out.println("-------------------------------------------------");
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
		System.out.println("=================================================");
	}
	
	// 선택한 내 게시글 내용 출력
	public void printMyBoardContent(BoardVO vo) {
		System.out.println();
		System.out.println("▰─────────────────────────────────────────────────▰");
		System.out.println(vo);
		System.out.println("---------------------------------------------------");
		System.out.println(vo.getContent());
		System.out.println("▰─────────────────────────────────────────────────▰");
	}
	
	// (관리자) 모든 게시글 목록 출력
	public void printAllBoards(List<BoardVO> list) {
		System.out.println("\n[모든 문의내역]");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("번호  제목                            작성자              작성일자");
		System.out.println("--------------------------------------------------------------------");
		for (BoardVO vo : list) {
			System.out.printf("%-6s%s%s%s\n", vo.getNo(), vo.getTitle(), vo.getWriter(), vo.getRegisterDate());
		}
		System.out.println("====================================================================");
	}
		
	// (관리자) 선택한 게시글 출력	
	public void printChoiceContent(BoardVO vo) {
		System.out.println();
		System.out.println("▰───────────────────────────────────────────────────────────────────▰");
		System.out.printf("%-6s%s%s%s\n", vo.getNo(), vo.getTitle(), vo.getWriter(), vo.getRegisterDate());
		System.out.println("---------------------------------------------------------------------");
		System.out.println(vo.getContent());
		System.out.println("▰───────────────────────────────────────────────────────────────────▰");
	}
}