package basicProject;

import java.util.Scanner;

public class ReplyController {
	private ReplyView view = new ReplyView();
	private ReplyService service = new ReplyService();
	
	public int insertReply(int selectNo, Scanner scanner) {
		ReplyVO vo = view.inputReply(selectNo, scanner);
		return service.insertReply(vo);
	}
	
	public boolean selectReply(int selectNo) {
		ReplyVO vo = service.selectReply(selectNo);
		return view.printSelectReply(vo);
	}
}
