package basicProject;

public class ReplyVO {
	private int no;
	private String content;
	private String registryDate;
	
	public ReplyVO(int no, String content, String registryDate) {
		this.no = no;
		this.content = content;
		this.registryDate = registryDate;
	}
	// insert
	public ReplyVO(int no, String content) {
		this.no = no;
		this.content = content;
	}
	// select
	public ReplyVO(int no) {
		this.no = no;
	}
	
	// input
	public ReplyVO(String content) {
		this.content = content;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegistryDate() {
		return registryDate;
	}
	public void setRegistryDate(String registryDate) {
		this.registryDate = registryDate;
	}
	
	@Override
	public String toString() {
		return String.format("%s\n%s", registryDate, content);
	}
}
