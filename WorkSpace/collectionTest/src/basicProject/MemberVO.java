package basicProject;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String add;
	private String hp;
	private int mileage;
	public MemberVO() {
	}
	
	// id 중복 확인
	public MemberVO(String id) {
		this.id = id;
	}
	
	// 로그인, 비밀번호 수정
	public MemberVO(String id, String password) {
		this.id = id;
		this.password = password;
	}

	// 로그인시 내정보 불러옴
	public MemberVO(String id, String name, String add, String hp, int mileage) {
		super();
		this.id = id;
		this.name = name;
		this.add = add;
		this.hp = hp;
		this.mileage = mileage;
	}
	
	// 회원가입
	public MemberVO(String id, String password, String name, String add, String hp) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.add = add;
		this.hp = hp;
	}
	
	// 회원정보 수정
	public MemberVO(String password, String add, String hp) {
		this.password = password;
		this.add = add;
		this.hp = hp;
	}

	// 회원 전체 정보
	public MemberVO(String id, String password, String name, String add, String hp, int mileage) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.add = add;
		this.hp = hp;
		this.mileage = mileage;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}
	
	public int getMileage() {
		return mileage;
	}
	
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return String.format("%-10s\t%-6s%-30s\t%-15s\t%-5d\n", id, name, add, hp, mileage);
	}
	
	
	

}
