package basicProject;

import java.util.List;

public class OrderedView {

	public void printOrderId(OrderedVO vo) {
		System.out.println("▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱");
		System.out.println("주문번호: " + vo.getOrderId());
	}

	public void printOrder(OrderedVO vo) {
		System.out.print("상품명: " + vo.getName() + "   ");
		if (vo.getQty() > 9) {
			System.out.print(vo.getQty() + "개  ");
		} else {
			System.out.print(vo.getQty() + "개   ");
		}

		if (vo.getSum() < 9999) {
			System.out.print(vo.getSum() + "원   ");
		} else {
			System.out.print(vo.getSum() + "원  ");
		}
		System.out.println();
	}
	

	public void printEtc(OrderedVO vo,int allMoney) {
		System.out.println("배송지: " + vo.getAdd() + "   ");
		System.out.println("결제 방법: " + vo.getPay() + "   ");
		System.out.println("총 주문 금액: " + allMoney + "원");
		System.out.println("▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱");
		System.out.println();
	}
	
	public void printItemRemain(List<OrderedVO> list) {
		System.out.println("[상품 재고 조회]");
		System.out.println("-----------------------------------------------------");
		System.out.println("번호	상품명					재고");
		System.out.println("-----------------------------------------------------");
		for (OrderedVO vo : list) {
			System.out.printf("%-4s\t%-20s\t%d\n",vo.getId(), vo.getName(), vo.getProdRemain());
		}
		System.out.println("▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱");
	}
}
