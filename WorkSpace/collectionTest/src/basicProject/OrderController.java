package basicProject;

import java.util.List;
import java.util.Scanner;

public class OrderController {
	private OrderService service = new OrderService();
	private OrderView view = new OrderView();
	
	//물건, 수량 고르고 담기 (루프필요) 
	public ProdVO addCart(Scanner scanner){
		ProdVO choiceProd = view.choiceProds(scanner);
		if (choiceProd == null) {
			return null;
		}
		return service.addCart(choiceProd);
	}
	
	//담은 내용 출력하기
	public void cartCheck(List<ProdVO> list) {
		view.cartCheck(list);
	}
	
	//배송정보 입력하기
	public OrderVO inputShipping(Scanner scanner) {
		OrderVO vo = view.inputOrder(scanner);
		return vo;
	}
	
	//주문하기 
	public int order(OrderVO vo, List<ProdVO> addList) {
		vo.setProdVO(addList);
		int orderResult = service.ordering(vo);
		return orderResult;
	}
	
	public void cancelOrder(String cancelOrderId) throws Exception{
	      int orderDetail = service.cancelOrderDetail(cancelOrderId);
	      int order = service.cancelOrder(cancelOrderId);
	      int count = orderDetail+order;
	      view.deleteResult(count);
	   }
}
