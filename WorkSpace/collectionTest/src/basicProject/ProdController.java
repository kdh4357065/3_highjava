package basicProject;

import java.util.List;
import java.util.Scanner;

public class ProdController {
	private ProdService service = new ProdService();
	private ProdView view = new ProdView();
	
	public void selectProds() {
		view.printProds(service.selectProducts());
	}
	
	public void searchProds(Scanner scanner) {
		String searchWord = view.inputSearchWord(scanner);
		List<ProdVO> vo = service.searchProds(searchWord);
		view.printSearchedMemos(vo, searchWord);
	}
}
