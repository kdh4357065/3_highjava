package kr.or.ddit.basic;

import java.util.*;

//문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오
//(단, 각 별명의 길이가 같을 수 있다.)

public class ArrayListTest04 {
	public static void main(String[] args) {
		ArrayList<String> name = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		int max = 0;

		for (int i = 0; i < 5; i++) {
			System.out.print("별명을 입력하세오 : ");
			name.add(sc.nextLine());
			
			if(max < name.get(i).length()) {
				max = name.get(i).length();
			}
		}

		System.out.println("가장 긴 별명");
		for(String str : name) {
			if(str.length()==max) {
				System.out.print(str);
			}
		}

	}

}
