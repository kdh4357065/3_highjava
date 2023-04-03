package basicProject;

import java.util.*;

public class OrderedController {
	private OrderedService service = new OrderedService();
	private OrderedView view = new OrderedView();
	
	public void newOrder() throws Exception {
		System.out.println("\n[내 주문 정보]");
		List<OrderedVO> list = service.serchOrder(Application.getSession().getId());

		String subOrderId = null;
		int allMoney = 0;
			if (!list.get(list.size()-1).getOrderId().equals(subOrderId)) {
				view.printOrderId(list.get(list.size()-1));
				subOrderId = list.get(list.size()-1).getOrderId();
			for (OrderedVO mo : list) {
				if(mo.getOrderId().equals(subOrderId)) {
				view.printOrder(mo);
				allMoney += mo.getSum();
				}
			}
			view.printEtc(list.get(list.size()-1),allMoney);
			allMoney=0;
		}
	}

	public void myOrder() throws Exception {
		System.out.println("\n[내 주문 정보]");
		List<OrderedVO> list = service.serchOrder(Application.getSession().getId());

		String subOrderId = null;
		int allMoney = 0;
		if(list.size() == 0) {
			System.out.println("주문 정보가 없습니다.");
		} else {
			for (int i=0; i<list.size();i++) {
				if (!list.get(i).getOrderId().equals(subOrderId)) {
					view.printOrderId(list.get(i));
					subOrderId = list.get(i).getOrderId();
				}
				for (OrderedVO mo : list) {
					if(mo.getOrderId().equals(subOrderId)) {
						view.printOrder(mo);
						allMoney += mo.getSum();
						i++;
					}
				}
				i -= 1;
				view.printEtc(list.get(i),allMoney);
				allMoney=0;
			}
		}
	}
	
	public void selectRemain(OrderedVO vo) throws Exception {
		List<OrderedVO> itemRemain = service.selectItemRemain(vo);
		view.printItemRemain(itemRemain);
	}

}
