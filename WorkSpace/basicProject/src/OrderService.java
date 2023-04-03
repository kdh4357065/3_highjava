package basicProject;


public class OrderService {
	OrderDAO dao = new OrderDAO();

	public ProdVO addCart(ProdVO addlist){
		return dao.addCart(addlist);
	}
	public int ordering(OrderVO vo) {
		return dao.ordering(vo);
	}
	public int cancelOrderDetail(String cancelOrderId) throws Exception{
	      return dao.cancelOrderDetail(cancelOrderId);
	   }
	   
	   public int cancelOrder(String cancelOrderId) throws Exception{
	      return dao.cancelOrder(cancelOrderId);
	   }
}
