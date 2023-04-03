package basicProject;

public class Application {
	private static MemberVO session = new MemberVO();
	public static MemberVO getSession() {
		return session;
	}
	public static void setSession(MemberVO vo) {
		session = vo;
	}

	public static void main(String[] args) throws Exception {
		FrontController controller = new FrontController();
		controller.process();

	}
}
