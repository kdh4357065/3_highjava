package basicProject;

import java.util.List;

public class BoardService {
	private BoardDAO dao = new BoardDAO();
	
	public int insertBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}
	public List<BoardVO> selectMyBoards(){
		return dao.selectMyBoards();
	}
	public BoardVO selectMyBoardContent(int selectNo){
		if(selectNo == 0) {
			return null;
		}
		return dao.selectMyBoardContent(selectNo);
	}
	public List<BoardVO> selectAllBoards() {
		return dao.selectBoards();
	}
	public BoardVO selectBoard(int selectNo) {
		return dao.selectBoard(selectNo);
	}
}
