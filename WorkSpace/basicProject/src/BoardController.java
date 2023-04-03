package basicProject;

import java.util.List;
import java.util.Scanner;

public class BoardController {
	private BoardService service = new BoardService();
	private BoardView view = new BoardView();

	public int insertBoard(Scanner scanner) {
		BoardVO vo = view.inputBoard(scanner);
		int insertResult = service.insertBoard(vo);
		return insertResult;
	}
	public void selectMyBoards() {
		List<BoardVO> list = service.selectMyBoards();
		view.printMyBoards(list);
	}
	public int selectMyBoardContent(int selectNo) {
		BoardVO vo = service.selectMyBoardContent(selectNo);
		if(vo.getTitle() == null) {
			return 0;
		} else {
			view.printMyBoardContent(vo);
			return 1;
		}
	}
	public void selectAllBoards() {
		List<BoardVO> list = service.selectAllBoards();
		view.printAllBoards(list);
	}
	public int selectBoard(int selectNo) {
		BoardVO vo = service.selectBoard(selectNo);
		if(vo == null) {
			return 0;
		} else {
			view.printChoiceContent(vo);
			return 1;
		}
	}
}
