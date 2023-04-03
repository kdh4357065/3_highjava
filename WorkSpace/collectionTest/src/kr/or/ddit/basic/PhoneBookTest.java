package kr.or.ddit.basic;

//Scanner의 next(0, nextInt(), nextBouble() 등...
//   ==> 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리도니 자료만 읽어간다. 

//Scanner의 nextLint()
//   ==> 한 줄 단위로 입력한다.
//       (즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서 Enter키를 뺀 나머지를 반환한다.)

//- 컴퓨터의 입력 작업은 입력된 데이터를 입력 버퍼에 저장하고 이것을 차례로 꺼내가는
//  방법으로 처리된다.
//그래서 next(), nextInt()등과 같은 메서드를 실행한 후에 nextLine()을 사용할 때는
//입력 버퍼를 비워줘야 한다.(방법 : nextLine()을 한번 더 사용한다.)

//- 추가 조건)
// 1) '6. 전화번호 저장'메뉴를 추가하고 구현한다.
//     (저장파일명은 'phoneData.bin')
// 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
// 3) 프로그램을 종료할 떄 Map의 데이터의 변화가 있으면 
//    (데이터의 추가, 수정, 삭제작업 후 저장하지 않은 상태) 자료를 저장한 후 종료되도록 한다.

import java.util.Scanner;
import java.util.Set;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class PhoneBookTest {
	Scanner scan;
	HashMap<String, Phone> phone;
	private String fileName = "d:/d_other/phoneData.bin"; //저장 파일명
	
	//데이터의 변화가 있었는지 여부를 저장하는 변수
	//데이터의 변화가 있으면 이 변수값이 true가 된다.
	private boolean isDataChange = false;

	public PhoneBookTest() {
		phone = load(); //파일 내용을 읽어와 Map에 저장하기
		if(phone == null) {
			phone = new HashMap<>();
		}
		
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
	}

	public void phoneBookStart() {
		while (true) {
			System.out.println("---------------------------");
			System.out.println("다음 메뉴를 선택하세요.");
			System.out.println("1. 전봐번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체 출력");
			System.out.println("6. 전화번호 저장");
			System.out.println("0. 종료");
			System.out.println("---------------------------");
			System.out.print("번호입력 >> ");
			int answer = Integer.parseInt(scan.nextLine());

			switch (answer) {
			case 1:
				addData();
				break;
			case 2:
				changeData();
				break;
			case 3:
				deleteData();
				break;
			case 4:
				searchData();
				break;
			case 5:
				getAllData();
				break;
			case 6: //저장
				save();
				break;
			case 0:
				if(isDataChange == true) {
					System.out.println("변경된 자료가 있어서 저장 후 종료합니다...");
					save();
				}
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("번호를 다시 입력하세요.");
				break;
			}
		}

	}
	
	//파일로 저장된 전화번호 정보를 읽어와서 Map에 추가한 후 반환하는 메서드
	private HashMap<String, Phone> load(){
		HashMap<String,Phone> pMap = null; //반환값이 저장될 변수 선언
		File file = new File(fileName);
		if(!file.exists()) { //저장된 파일이 없으면...
			return null;
		}
		
		//저장된 파일이 있을 떄 처리되는 영역...
		ObjectInputStream oin = null;
		try {
			//입력용 스트림 객체 생성
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			
			//파일에 저장된 페이터를 읽어와 Map객체에 저장하기
			
			//'방법1'로 저장했을 때(Map자체ㅐ를 저장했을 때...)
			//pMap = (<HashMap<String, Phone>)oin.readObject();
			
			//'방법2'로 저장했을 때
			Object obj = null; //읽어온 데이터가 저장될 변수
			pMap = new HashMap<>(); //저장할 Map객체 생성
			while((obj = oin.readObject())!= null) {
				Phone p = (Phone) obj;
				pMap.put(p.getName(), p);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			// TODO: Auto-generated catch block
			e.printStackTrace();
		}finally {
			//스트림 닫기
			if(oin!=null) try {oin.close(); }catch(IOException e) {}
		}
		return pMap;
	}
	
	//전화번호 정보를 파일로 저장하는 메서드
	private void save() {
		ObjectOutputStream oout = null;
		try {
			//객체 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName)));
			
			//Map에 저장된 전화번호 정보를 파일로 출력한다.
			//oout.writeObject(phone); //Map객체 자체 저장하기(방법1)
			
			//Map에 저장된 Phone객체를 하나씩 꺼내서 저장하기
			for(String name : phone.keySet()) {
				Phone p = phone.get(name);
				oout.writeObject(p);
			}
			oout.writeObject(null);
			
			System.out.println("저장이 완료되었습니다.");
			
			isDataChange = false;
			
		} catch (IOException e) {
			// TODO: handle exception
		}finally {
			//스트림 닫기
			if(oout!=null) try {oout.close(); } catch(IOException e) {}
		}
	}

	public void addData() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이룸 >> ");
		String inputName = scan.nextLine();
		if (phone.containsKey(inputName)) {
			System.out.println();
			System.out.println(inputName + "씨는 이미 등록된 사람입니다.");
		} else {
			System.out.print("전화번호 >> ");
			String inputNumber = scan.nextLine();
			System.out.print("주소 >> ");
			String inputAdd = scan.nextLine();

			phone.put(inputName, new Phone(inputName, inputNumber, inputAdd));

			System.out.println();
			System.out.println(inputName + "씨의 전화번호 정보가 등록되었습니다.");
			
			isDataChange = true;
		}
	}

	public void changeData() {
		System.out.print("수정할 이름 입력 >> ");
		String changeName = scan.nextLine();
		if (phone.containsKey(changeName)) {
			System.out.print("수정할 전화번호 입력 >> ");
			String changePhoneNumber = scan.nextLine();
			System.out.print("수정할 주소 입력 >> ");
			String changeAdd = scan.nextLine();

			phone.put(changeName, new Phone(changeName, changePhoneNumber, changeAdd));

			System.out.println();
			System.out.println(changeName + "씨의 정보 수정이 완료되었습니다.");
			
			isDataChange = true;
		} else {
			System.out.println();
			System.out.println(changeName + "씨는 존재하지 않습니다.");
		}
	}

	public void deleteData() {
		System.out.print("삭제할 이름 입력 >> ");
		String delName = scan.nextLine();
		if (phone.containsKey(delName)) {
			phone.remove(delName);

			System.out.println();
			System.out.println(delName + "씨의 정보가 삭제 되었습니다.");
			
			isDataChange = true;
		} else {
			System.out.println();
			System.out.println(delName + "씨는 존재하지 않습니다.");
		}
	}

	public void searchData() {
		System.out.print("검색할 이름 입력 >> ");
		String searchName = scan.nextLine();
		if (phone.containsKey(searchName)) {
			Phone p = phone.get(searchName);

			System.out.println();
			System.out.println("검색결과");
			System.out.println("------------------------------------");
			System.out.println("이름\t전화번호\t주소");
			System.out.print(searchName + "\t" + p.getPhoneNum() + "\t" + p.getAdd());
			System.out.println("------------------------------------");
		} else {
			System.out.println();
			System.out.println(searchName +"씨는 존재하지 않습니다.");
		}
	}

	public void getAllData() {
		System.out.println("저장된 정보 전체 출력");
		System.out.println("------------------------------------");
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("------------------------------------");

		Set<String> phoneKeySet = phone.keySet();

		if (phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int num = 1;
			for (String key : phone.keySet()) {
				Phone value = phone.get(key);
				System.out.print(num + "\t" + key + "\t" + value.getPhoneNum() + "\t" + value.getAdd());
				System.out.println();
				num++;
			}
			System.out.println("------------------------------------");

			System.out.println();
			System.out.println("출력이 완료되었습니다.");
		}
	}
}

class Phone implements Serializable{
	private String name;
	private String add;
	private String phoneNum;

	public Phone(String name, String phoneNum, String add) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
		this.add = add;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
