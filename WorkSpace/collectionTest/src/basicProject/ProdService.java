package basicProject;

import java.util.List;

public class ProdService {
	ProdDAO dao = new ProdDAO();
	public List<ProdVO> selectProducts() {
		return dao.selectProducts();
	}
	public List<ProdVO> searchProds(String searchWord) {
		return dao.searchProds(searchWord);
	}
}
