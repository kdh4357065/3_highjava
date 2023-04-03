package basicProject;

public class ReplyService {
	private ReplyDAO dao = new ReplyDAO();
	
	public int insertReply(ReplyVO vo) {
		return dao.insertReply(vo);
	}
	public ReplyVO selectReply(int selectNo) {
		return dao.selectReply(selectNo);
	}
}
