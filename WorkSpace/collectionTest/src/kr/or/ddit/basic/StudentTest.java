package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
//     Student클래스를 만든다.
//     이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 인수로 받아서
//     초기화 처리를 한다.

//     이 Student객체는 List에 저장하여 관리한다.

//     List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 기준을 구현하고,
//     총점의 역수능로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준도
//     구현하여 정렬된 결과를 출력하시오.(클래스명 : SortByTotal)

//    (등수는 List에 전체 데이터가 추가된 후에 구해서 저장한다.)

public class StudentTest {
	//등수를 구하는 메서드
	public void setRanking(ArrayList<Student> stuList) {
		for(Student std1 : stuList) { //등수를 구할 기준 데이터를 구하기 위한 반복문
			int rank = 1; //처음에는 1들으로 초기화해 놓고 시작한다.
			
			for(Student std2 : stuList) { //비교 대상을 나타내는 반복문
				
				//기준값보다 큰 값을 만나면 rank변수값을 증가 시킨다.
				if(std1.getSum() < std2.getSum()) {
					rank++;
				}
			}
			//구해진 등수를 Student객체의 rank변수에 저장한다.
			std1.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		ArrayList<Student> stuList = new ArrayList<>();

		stuList.add(new Student(201, "박지성", 80, 50, 60));
		stuList.add(new Student(205, "손흥민", 85, 60, 90));
		stuList.add(new Student(202, "차범근", 100, 85, 100));
		stuList.add(new Student(210, "김신욱", 90, 20, 35));
		stuList.add(new Student(216, "이영표", 100, 85, 100));
		stuList.add(new Student(208, "기성용", 15, 90, 75));
		stuList.add(new Student(211, "이청용", 30, 70, 75));
		stuList.add(new Student(223, "구자철", 85, 90, 95));

		//등수 구하는 메서드 호출
		test.setRanking(stuList);
		
		System.out.println("정렬 전..");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("----------------------------------------------------");
		
		Collections.sort(stuList);
		
		System.out.println("학번으로 오름차순 정렬 후..");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("----------------------------------------------------");
		
		Collections.sort(stuList, new SortByTotal());
		

		System.out.println("총점으로 내림차순 정렬 후..(총점이 같을 경우 이름으로 오름차순 정렬)");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("----------------------------------------------------");
	}
}

class Student implements Comparable<Student>{
	int stuNum;
	String name;
	int korNum;
	int engNum;
	int mathNum;
	int sum;
	int rank;

	public Student(int stuNum, String name, int korNum, int engNum, int mathNum) {
		super();
		this.stuNum = stuNum;
		this.name = name;
		this.korNum = korNum;
		this.engNum = engNum;
		this.mathNum = mathNum;
		this.sum = korNum+engNum+mathNum;
	}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorNum() {
		return korNum;
	}

	public void setKorNum(int korNum) {
		this.korNum = korNum;
	}

	public int getEngNum() {
		return engNum;
	}

	public void setEngNum(int engNum) {
		this.engNum = engNum;
	}

	public int getMathNum() {
		return mathNum;
	}

	public void setMathNum(int mathNum) {
		this.mathNum = mathNum;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [stuNum=" + stuNum + ", name=" + name + ", korNum=" + korNum + ", engNum=" + engNum
				+ ", mathNum=" + mathNum + ", sum=" + sum + ", rank=" + rank + "]";
	}
	
//학번의 오름차순으로 정렬하기
	@Override
	public int compareTo(Student stu) {
		return Integer.compare(this.getStuNum(), stu.getStuNum());
	}
}

class SortByTotal implements Comparator<Student>{
	@Override
	public int compare(Student stu1, Student stu2) {
//		if(stu1.getSum() > stu2.getSum()) {
//			return -1;
//		} else if (stu1.getSum() < stu2.getSum()) {
//			return 1;
//		}else {
//			return stu1.getName().compareTo(stu2.getName());
//		}
		
		if(stu1.getSum() == stu2.getSum()) {
			return stu1.getName().compareTo(stu2.getName());
		} else {
			return new Integer(stu1.getSum()).compareTo(stu2.getSum()) * -1;
		}
			
	}
	
}
