package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
     문제) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에
          이 ArrayList에 저장된 이름들 중에 '김'씨 성의 이름을 찾아 모두 출력하시오.
          (단, 입력은 Scanner객체를 이용한다.) 
 
 */
public class ArrayListTest02 {
	public static void main(String[] args) {
		ArrayList<String> name = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			System.out.print("이름을 입력하세오 : ");
			name.add(sc.nextLine());
		}
		System.out.println("김씨 성을 가지 사람의 이름");
		
		for(String str : name) {
//			if(str.startsWith("김")) {//startsWith는 str의 시작이 김과 같으면 true로 이름 출력
//				System.out.println("이름 : " + str);
//			}
			
//			if(str.indexOf("김")==0) {
//				System.out.println("이름 : " + str);
//			}
			
//			if(str.substring(0,1).equals("김")) {
//				System.out.println("이름 : " + str);
//			}
			
//			if(str.charAt(0) == '김') {
//				System.out.println("이름 : " + str);
//			}
		}
	}
}