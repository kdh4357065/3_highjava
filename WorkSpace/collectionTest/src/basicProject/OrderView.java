package basicProject;

import java.util.List;
import java.util.Scanner;

public class OrderView {

	public ProdVO choiceProds(Scanner scanner) {
		ProdVO vo = new ProdVO(null, 0);
		System.out.print("상품번호: ");
		String prodNo = scanner.nextLine();
		if(prodNo.equals("0")) {
			return null;
		}
		vo.setProdNo(prodNo);
		while (true) {
			System.out.print("수량: ");
			int prodQty = 0;
			try {
				prodQty = Integer.parseInt(scanner.nextLine());
				if(prodQty == 0) {
					System.out.println("수량이 입력되지 않았습니다.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("올바른 수량을 입력해주세요.");
				continue;
			}
			vo.setProdQty(prodQty);
			break;	// 확인하기
		}
		return vo;
	}
	
	public void cartCheck(List<ProdVO> addList) {
		System.out.println("\n[장바구니 목록]");
		System.out.println("------------------------------------------------------------------");
		System.out.println("번호	상품명					가격   수량  금액");
		System.out.println("------------------------------------------------------------------");
		int totalPrice = 0;
		for(ProdVO vo : addList) {
			System.out.printf("%-4s\t", vo.getProdNo());
			System.out.print(vo.getProdName() + "        " + vo.getProdPrice() + "    ");
			System.out.println(vo.getProdQty() + "    " + vo.getProdQty()*vo.getProdPrice());
			totalPrice += vo.getProdQty()*vo.getProdPrice();
		}
		System.out.println("==================================================================");
		System.out.println("** 총금액: " + totalPrice + "원\n");
	}
	
	public OrderVO inputOrder(Scanner scanner) {
		String add = Application.getSession().getAdd();
		String hp = Application.getSession().getHp();
		System.out.println("\n배송지: " + add);
		System.out.println("연락처: " + hp);
		System.out.println("1.기본주소로 주문\t 2.새 주소로 주문 ");
		System.out.println("▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱");
		while(true) {
			System.out.print("배송지 선택> ");
			int select = 0;
			try {
				select = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("올바른 메뉴를 입력해주세요\n");
				continue;
			}
			switch (select) {
			case 1:
	            while (true) {
	               System.out.println("결제수단: 카드\t\t계좌이체");
	               System.out.print("결제수단 선택> ");
	               String pay = scanner.nextLine();
	               if (!("카드".equals(pay) || "계좌이체".equals(pay))) {
	                  System.out.println("결제 수단을 다시 입력해주세요!");
	               } else {
	                  return new OrderVO(add, hp, pay);
	               } 
	            }
	         case 2:
	            System.out.print("\n배송지: ");
	            String newAdd = scanner.nextLine();
	            System.out.print("연락처: ");
	            String newHp = scanner.nextLine();
	            while (true) {
	            System.out.println("결제수단: 카드\t\t계좌이체");
	            System.out.print("결제수단 선택> ");
	            String newPay = scanner.nextLine();
	               if (!("카드".equals(newPay) || "계좌이체".equals(newPay))) {
	                  System.out.println("결제 수단을 다시 입력해주세요!");
	               } else {
	                  return new OrderVO(newAdd, newHp, newPay);
	               } 
	            }
			default:
				System.out.println("올바른 메뉴를 입력해주세요\n");
				continue;
			}
			
		}
	}
	
	public void deleteResult(int count) {
	      if(count >1) {
	         System.out.println("주문 취소가 완료 되었습니다.");
	         
	      } else {
	         System.out.println("주문 취소가 되지 않았습니다.");
	      }
	      
	   }
}
