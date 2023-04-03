package kr.or.ddit.basic;

import java.util.*;

//문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오
//(단, 각 별명의 길이는 모두 다르게 입력한다.)
public class ArrayListTest03 {
	public static void main(String[] args) {
		ArrayList<String> name = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		int max = 0;
		String longName = null;

		for (int i = 0; i < 5; i++) {
			System.out.print("별명을 입력하세오 : ");
			name.add(sc.nextLine());
			
			if(max < name.get(i).length()) {
				longName = name.get(i);
			}
		}
		
		System.out.println("가장 긴 별명 : " + longName);
	}
}
