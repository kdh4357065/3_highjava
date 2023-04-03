package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 10마리의 말들이 경주하는 프로그램 작성하기
 
 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데
 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준을 갖고 있다.
 (Comparable인터페이스 구현)
 
  경기 구간은 1 ~ 50구간으로 되어 있다.
  경기가 끝나면 등수 순으로 출력한다.
  
  경기 중간 중간에 각 말들의 위치를 아래와 같이 출력한다.
  예시)
  말이름01 : ---->---------------------------------------------
  말이름02 : ------>-------------------------------------------
  말이름03 : --->----------------------------------------------
  ...
  말이름10 : --------->----------------------------------------
 */
public class ThreadTest13 {

	public static void main(String[] args) {
		List<Horse> horseArr = new ArrayList<>();
		horseArr.add(new Horse("1번말"));
		horseArr.add(new Horse("2번말"));
		horseArr.add(new Horse("3번말"));
		horseArr.add(new Horse("4번말"));
		horseArr.add(new Horse("5번말"));
		horseArr.add(new Horse("6번말"));
		horseArr.add(new Horse("7번말"));
		horseArr.add(new Horse("8번말"));
		horseArr.add(new Horse("9번말"));
		horseArr.add(new Horse("10번말"));

		gameState gs = new gameState(horseArr);

		for (Horse hr : horseArr) {
			hr.start();
		}
		gs.start();
	}
}

class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank = 1; // 말의 등수를 구할 때 사용
	private String horsename;// 말이름
	private int rank;// 등수
	private int location;// 현재 위치

	public Horse(String horsename) {
		this.horsename = horsename;
	}

	public String getHorsename() {
		return horsename;
	}

	public void setHorsename(String horsename) {
		this.horsename = horsename;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마" + horsename + "는(은) " + rank + "등 입니다.";
	}

	// 등수는 오름차순 정렬의 내부 정렬 기준
	@Override
	public int compareTo(Horse hr) {
		return (this.rank - hr.rank);
	}

	@Override
	public void run() {
		Random rnd = new Random();
		for (int i = 1; i <= 50; i++) {
			this.location = i; // 말의 현재위치 저장
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		// 한 마리의 말의 경주가 끝나면 현재의 등수를 구해서 저장한다.
		this.rank = currentRank;
		currentRank++;
	}
}

class gameState extends Thread {
	private List<Horse> horseArr;

	public gameState(List<Horse> horseArr) {
		this.horseArr = horseArr;
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < horseArr.size(); i++) {
				System.out.print(horseArr.get(i).getHorsename() + "\t: ");
				for (int j = 1; j <= 50; j++) {
					if (horseArr.get(i).getLocation() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			if (Horse.currentRank == horseArr.size()) {
				System.out.println("프로그램을 종료합니다.");
				System.out.println("-- 결 과 --");
				Collections.sort(horseArr);
				for (Horse hr : horseArr) {
					System.out.println("순위 :\t" + hr.getRank() + ",\t말이름 : " + hr.getHorsename());
				}
				System.exit(0);
			}
		}
	}
}
