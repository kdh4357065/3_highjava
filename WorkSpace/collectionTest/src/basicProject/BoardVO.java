package basicProject;

public class BoardVO {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String writerName;
	private String registerDate;
	private String modifyDate;
	
	// insert
	public BoardVO(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	// 내 id만
	public BoardVO(String writer) {
		this.writer = writer;
	}

	// select myBoard
	public BoardVO(int no, String title, String registerDate) {
		this.no = no;
		this.title = title;
		this.registerDate = registerDate;
	}
	
	// select myBoardContent
	public BoardVO(int no, String title, String content, String registerDate) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.registerDate = registerDate;
	}
	
	// select memberBoards
	public BoardVO(int no, String title, String writer, String registerDate, String modifyDate) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.registerDate = registerDate;
	}

	// select memberBoardContent
	public BoardVO(int no, String title, String content, String writer, String registerDate, String modifyDate) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.registerDate = registerDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	@Override
	public String toString() {
		return String.format("%s\t%s%s", no, title, registerDate);
	}
}
