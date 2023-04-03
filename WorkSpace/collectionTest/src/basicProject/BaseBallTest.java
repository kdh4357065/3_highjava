package basicProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

//문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
//     (컴푸터의 숫자는 난수를 이용하여 구한다. (1 ~ 9 사이의 값 3개)
//     (스트라이크는 S, 볼은 B로 나타낸다.)

//예시)
//  컴퓨터 난수 ==> 9 5 7

//실행예시)
//  숫자입력 >> 3 5 6
//  3 5 6 ==> 1S 0B
//  숫자입력 >> 7 8 9
//  7 8 9 ==> 0S 2B
//  숫자입력 >> 9 7 5
//  0 7 5 >> 1S 2B
//  숫자입력 >> 9 5 7
//  9 5 7 ==> 3S 0B

//  축하합니다...
//  당신은 4번째 만에 맞췄습니다.

public class BaseBallTest {
	private ArrayList<Integer> numList;// 난수가 저장될 리스트
	private ArrayList<Integer> userList;// 사용자가 입력한 값이 저장될 리스트

	private int strike;// 스트라이크의 개수
	private int ball;// 볼의 개수

	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new BaseBallTest().gameStart();

	}

	public void gameStart() {
		System.out.println("*********************************");
		System.out.println("    숫  자  야  구  게  임  시  작    ");
		System.out.println("*********************************");
		System.out.println();
		System.out.println("1 ~ 9 사이의 서로 다른 숫자 3개를 입력하세요...");
		System.out.println();
		
		//난수를 만드는 메서드 호출
		createNum();
		
		//만들어진 나수 확인하기
		System.out.println("만들어진 난수 : " + numList);
		
		int cnt = 0; //몇번만에 맞췄는지를 저장하는 변수 언언 및 초기화
		do {
			cnt++;
			
			inputData();//입력용 메서드 호출
			
			ballCount();//볼카운트 구하는 메서드 호출
			
		}while(strike != 3);

		System.out.println();
		System.out.println("축하합니다...");
		System.out.println("당신은 " + cnt + "번쨰만에 맞췄습니다.");
	}

	// 1 ~ 9 사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드
	// (Set객체 이용)
	public void createNum() {
		Set<Integer> numSet = new HashSet<>();
		Random rnd = new Random();

		// 1 ~ 9 사이의 난수 3개 만들기
		while (numSet.size() < 3) {
			numSet.add(rnd.nextInt(9) + 1);
		}

		// 만들어진 난수를 List에 저장한다.
		numList = new ArrayList<>(numSet);

		// List의 데이터를 섞어준다
		Collections.shuffle(numList);
	} // createNum()메서드 끝...

	// 사용자로부터 3개의 정수를 입력 받아 List에 추가하는 메서드
	// (단, 입력한 값들은 중복되지 않아야 한다.)
	public void inputData() {
		int num1, num2, num3;// 입력한 값이 저장될 변수 선언

		do {
			System.out.print("숫자입력 >> ");
			num1 = scan.nextInt();
			num2 = scan.nextInt();
			num3 = scan.nextInt();
			if (num1 == num2 || num1 == num3 || num2 == num3) {
				System.out.println("중복되는 값이 입력되었습니다. 다시 입력하세요.");
			}
		} while (num1 == num2 || num1 == num3 || num2 == num3);
		
		//입력한 값을 List에 추가하기
		userList = new ArrayList<>();
		userList.add(num1);
		userList.add(num2);
		userList.add(num3);
	}//inputData() 메서드 끝...
	
	//스트라이크와 볼의 개수를 구해서 출력하는 메서드
	public void ballCount() {
		strike = 0;
		ball = 0;//개수 초기화
		
		for(int i=0; i<userList.size(); i++) {
			for(int j=0; j<numList.size(); j++) {
				if(userList.get(i) == numList.get(j)) {//값이 같은지 검사
					if(i==j) {//위치가 같은지 검사
						strike++;
					}else {
						ball++;
					}
					
				}
			}
		}
		
		//구해진 결과를 출력한다.
		System.out.println(userList.get(0) + ", " + userList.get(1) + ", " +
		                   userList.get(2) + " ==> " + strike + "S " + ball + "b");	
	}//ballCount 메스드 끝...
}

//Random rnd = new Random();
//
//HashSet<Integer> randSet = new HashSet<>();while(randSet.size()<3)
//{
//	randSet.add(rnd.nextInt(9) + 1);
//}
//
//ArrayList<Integer> comList = new ArrayList<>(randSet);
//ArrayList<Integer> playerList = new ArrayList<>();Collections.shuffle(comList);for(
//Integer com:comList)
//{
//	System.out.print(com + " \n");
//}
//
//boolean gameCheck = true;
//int gameCount = 1;while(gameCheck)
//{
//			int sCount = 0;
//			int bCount = 0;
//			
//			Scanner sc = new Scanner(System.in);
//			System.out.print("숫자 입력 >> ");
//			playerList.add(sc.nextInt());
//			playerList.add(sc.nextInt());
//			playerList.add(sc.nextInt());
//			
//			for(int i=0; i<playerList.size(); i++) {
//				for(int j=0; j<comList.size(); j++) {
//					if(playerList.get(i) == comList.get(j)) {
//						if(i == j) {
//							sCount++;
//						}else {
//							bCount++;
//						}
//					} 
//				}
//			}
//			
//			if(sCount == 3) {
//				System.out.println("\n축하합니다...");
//				System.out.println("당신은 " + gameCount + "번째 만에 맞췄습니다.");
//				gameCheck = false;
//			} else {
//				for(Integer player : playerList) {
//					System.out.print(player + " ");
//				}
//				System.out.print("==> " + sCount + "S "+ bCount +"B\n" );
//				playerList.clear();
//			}
//			gameCount++;
//		}
