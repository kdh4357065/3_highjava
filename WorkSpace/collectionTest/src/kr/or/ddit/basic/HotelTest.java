package kr.or.ddit.basic;

import java.util.*;

public class HotelTest {
	Scanner scan = new Scanner(System.in);
	private Map<Integer, Room> room = new HashMap<>();

	public HotelTest() {
		for (int i = 200; i < 500; i += 100) {
			for (int j = 1; j < 10; j++) {
				switch (i / 100) {
				case 2:
					room.put(i + j, new Room(i + j, "싱글룸", "-"));
					break;
				case 3:
					room.put(i + j, new Room(i + j, "더블룸", "-"));
					break;
				case 4:
					room.put(i + j, new Room(i + j, "스위트룸", "-"));
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		new HotelTest().hoterService();
	}

	public void hoterService() {
		System.out.println("*********************************************");
		System.out.println("         호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();

		while (true) {
			System.out.println("----------------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
			System.out.println("----------------------------------------------");
			System.out.print("선택 >> ");
			int answer = Integer.parseInt(scan.nextLine());

			switch (answer) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				showAllRoom();
				break;
			case 4:
				System.out.println();
				System.out.println("***********************************************");
				System.out.println("                호텔문을 닫습니다.");
				System.out.println("***********************************************");
				System.exit(0);
			default:
				System.out.println("지원하지 않는 서비스 입니다. 다시 입력해 주세요");
				break;
			}
		}

	}

	public void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("\t체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >> ");
		int roomNumber = Integer.parseInt(scan.nextLine());

		if (room.containsKey(roomNumber)) {
			if (room.get(roomNumber).getRoomGuest().equals("-")) {
				System.out.println("누구로 체크인 하겠습니까?");
				System.out.print("이름 입력 >> ");
				String name = scan.nextLine();

				room.get(roomNumber).setRoomGuest(name);
				System.out.println("체크인이 완료되었습니다.");
				System.out.println();
			} else {
				System.out.println(roomNumber + "호 객실은 이미 손님이 있습니다.");
				System.out.println();
			}
		} else {
			System.out.println(roomNumber + "호 객실은 존재하지 않습니다.");
			System.out.println();
		}
	}

	public void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("\t체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.print("체크아웃 할 방 번호를 입력하세요 >> ");
		int roomNumber = Integer.parseInt(scan.nextLine());

		if (room.containsKey(roomNumber)) {
			if (!room.get(roomNumber).getRoomGuest().equals("-")) {
				System.out.println(roomNumber + "호 객실의 " + room.get(roomNumber).getRoomGuest() + "님 체크아웃을 완료했습니다.");
				room.get(roomNumber).setRoomGuest("-");
				System.out.println();
			} else {
				System.out.println(roomNumber + "호 객실에는 체크인 한 사람이 없습니다.");
				System.out.println();
			}

		} else {
			System.out.println(roomNumber + "호 객실은 존재하지 않습니다.");
			System.out.println();
		}
	}

	public void showAllRoom() {
		System.out.println("----------------------------------------------");
		System.out.println("현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호\t방 종류\t투숙객 이름");
		System.out.println("----------------------------------------------");

		ArrayList<Integer> roomList = new ArrayList<>(room.keySet());
		//room.keySet으로 key값만 뺴와서 리스트에 저장
		
		Collections.sort(roomList);//Integer타입이어서 내부 정렬이 있음
		for (Integer r : roomList) {
			Room value = room.get(r);
			System.out.println(value.getRoomNum() + "\t" + value.getRoomKind() + "\t" + value.getRoomGuest());
		}
		System.out.println("----------------------------------------------");
		System.out.println();
	}
}

class Room {
	private int roomNum;
	private String roomKind;
	private String roomGuest;

	public Room(int roomNum, String roomKind, String roomGuest) {
		super();
		this.roomNum = roomNum;
		this.roomKind = roomKind;
		this.roomGuest = roomGuest;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomKind() {
		return roomKind;
	}

	public void setRoomKind(String roomKind) {
		this.roomKind = roomKind;
	}

	public String getRoomGuest() {
		return roomGuest;
	}

	public void setRoomGuest(String roomGuest) {
		this.roomGuest = roomGuest;
	}
}