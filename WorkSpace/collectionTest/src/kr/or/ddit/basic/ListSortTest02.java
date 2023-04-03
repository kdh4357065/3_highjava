package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();

		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));

		System.out.println("정렬 전...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");

		Collections.sort(memList);
		// 내부 정렬 기준이 없기 때문에 오류가 남
		// 실행하기 위해서는 외부 정렬 기준을 만들어 주거나 내부 정렬 기준을 만들어 줘야한다.

		System.out.println("정렬 후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");

		// 회원 번호(num)를 기준으로 내림차순 정렬하여 출력하시오
		// 이미 내부 정렬기준이 있기 때문에 외부 정렬 기준 클래스를 작서앟여 처리한다.(클래스 이름 : SortNumDesc)
		Collections.sort(memList, new SortNumDesc());
		System.out.println("회원번호 내림차순 정렬 후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");

	}
}

class SortNumDesc implements Comparator<Member> {
	@Override
	public int compare(Member str1, Member str2) {
//		if (str1.getNum() > str2.getNum()) { // str1의 값이 str2의 값보다 크면 음수를 반환하여 순서가 바뀌지 않는다
//			return -1;
//		} else if (str1.getNum() < str2.getNum()) {// str1의 값이 str2의 값보다 작으면 양수를 반환하여 순서가 바뀐다
//			return 1;
//		} else {// str1의 값이 str2의 값과 같으면 그대로
//			return 0;
//		}
		
		return (str1.getNum() - str2.getNum()) * -1;
		//안에 들어가는 숫자가 전부 양수이면 사용가능하지만 음수가 있는 경우에는 오류가 발생할 수 있다.
		
		//Wrapper클래스를 이용하는 방법1
		//return new Integer(str1.getNum()).compareTo(str2.getNum()) * -1;
		//-1은 곱해주는 이유는 오름차순으로 표현되는 것을 내림차순으로 표현해주기 위함이다.
		
		//Wrapper클래스를 이용하는 방법2
		//return Integer.compare(str1.getNum(), str2.getNum()) * -1;
	}
}

//Member클래스의 '회원이름'을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가하기
//==> Comparable인터페이스를 구현한다.
class Member implements Comparable<Member> {
	private int num; // 회원번호
	private String name; // 회원이름
	private String tel; // 전화번호

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(Member mem) {// 매개변수가 하나인 이유는 this와 비겨하기 때문
		// 회원이름의 오름차순
		return this.getName().compareTo(mem.getName());
	}

}