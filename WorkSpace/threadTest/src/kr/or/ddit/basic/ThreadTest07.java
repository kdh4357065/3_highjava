package kr.or.ddit.basic;

import java.util.Random;
import javax.swing.JOptionPane;

/*
컴퓨터와 가위 바위 보를 진행하느 프로그램을 작성하시오.

컴퓨터의 가위 바위 보는 난수를 이횽해서 구하고
사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력 받는다.

입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
5초 안에 입력이 없으면 게임에 진것으로 처리한다.

5초 안에 입력이 완료되면 승패를 구해서 출력한다.

결과 예시)
1)5초 안에 입력하지 못했을 경우
  --- 결 과 ---
  시간 초과로 당신이 졌습ㄴ디ㅏ.
  

2) 5초 안에 입력했을 경우
  --- 결 과 ---
  컴푸터 : 가위
  사용자 : 바위
  결 과 : 당신이 이겼습니다.
*/
public class ThreadTest07 {
	public static boolean check = false;

	public static void main(String[] args) {
		Thread dth = new downNum();

		// 컴퓨터의 가위바위보를 정해준다.
		String[] comData = new String[] { "가위", "바위", "보" };
		Random rand = new Random();
		int randNum = rand.nextInt(3);
		String com = comData[randNum];

		// 사용자의 가위 바위 보 입력
		dth.start();// 카운트 다운 시작
		String player = null;
		do {
			player = JOptionPane.showInputDialog("무엇을 내실 건가요?");
		} while (!("가위".equals(player) || "바위".equals(player) || "보".equals(player)));
		check = true;

		// 결과를 판정하여 출력하기
		String result = "";
		if (player.equals(com)) {
			result = "결 과 : 비겼습니다.";
		} else if (player.equals("가위") && com.equals("보") || player.equals("바위") && com.equals("가위")
				|| player.equals("보") && com.equals("바위")) {
			result = "결 과 : Player가 이겼습니다.";
		} else {
			result = "결 과 : Com이 이겼습니다.";
		}
		System.out.println("--- 결 과 ---");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + player);
		System.out.println("결 과 : " + result);
	}
}

class downNum extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (ThreadTest07.check == true) {
				return;
			}
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("--- 결 과 ---");
		System.out.println("시간 초과로 Player가 졌습니다.");
		System.exit(0);
	}
}
