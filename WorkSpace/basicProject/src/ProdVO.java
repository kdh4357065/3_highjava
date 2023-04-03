package basicProject;

public class ProdVO {
	private String prodNo;
	private String prodName;
	private int prodPrice;
	private int prodQty;
	private int prodRemain;
	
	//select
	public ProdVO(String prodNo, String prodName, int prodPrice, int prodRemain) {
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodRemain = prodRemain;
	}
	
//	public ProdVO(String prodNo, String prodName, int prodQty, int prodPrice, int a) {
//		this.prodNo = prodNo;
//		this.prodName = prodName;
//		this.prodPrice = prodPrice;
//		this.prodRemain = prodRemain;
//		this.a = a;
//	}


	//ordering
	public ProdVO(String prodNo, int prodQty) {
		this.prodNo = prodNo;
		this.prodQty = prodQty;
	}
	
	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public int getProdQty() {
		return prodQty;
	}

	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}

	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t%s\t", prodNo,
				prodName, prodPrice, prodRemain);
	}


	public int getProdRemain() {
		return prodRemain;
	}



	public void setProdRemain(int prodRemain) {
		this.prodRemain = prodRemain;
	}

	
}
