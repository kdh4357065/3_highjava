package basicProject;

import java.util.*;

public class OrderedService {
	private OrderedDAO dao = new OrderedDAO();
	
	public List<OrderedVO> selectList(String serchWord) throws Exception{
		return dao.selectList(serchWord);
	}
	public List<OrderedVO> searchLists(String searchId) throws Exception{
		return dao.searchLists(searchId);
	}
	
	public List<OrderedVO> serchOrder(String memId) throws Exception{
		return dao.serchOrder(memId);
	}
	
	//상품별 재고조회
	public List<OrderedVO> selectItemRemain(OrderedVO vo) throws Exception{
		return dao.selectRemain(vo);
	}
}
