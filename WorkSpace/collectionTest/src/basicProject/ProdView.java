package basicProject;

import java.util.List;
import java.util.Scanner;

public class ProdView {
	public void printProds(List<ProdVO> list) {
		System.out.println();
		System.out.println("[상품 전체 목록]");
		System.out.println("-------------------------------------------------------------");
		System.out.println("번호	상품명					가격	재고");
		System.out.println("-------------------------------------------------------------");
		for(ProdVO vo : list) {
			System.out.println(vo);
		}
		System.out.println("=============================================================");
	}
	public String inputSearchWord(Scanner scanner) {
		System.out.println("▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱▰▱");
		System.out.print("검색: ");
		return scanner.nextLine();
	}
	public void printSearchedMemos(List<ProdVO> list, String searchWord) {
		System.out.println();
		System.out.println("'" + searchWord + "'" + " 검색 결과 (" + list.size() + "개)");
		System.out.println("-------------------------------------------------------------");
		System.out.println("번호	상품명					가격	재고");
		System.out.println("-------------------------------------------------------------");
		for(ProdVO vo : list) {
		System.out.println(vo);
		}
		System.out.println("=============================================================");
	}

	
}
