package basicProject;

public class OrderedVO {
	private String id;
	private String orderId;
	private String name;
	private String add;
	private String hp;
	private String pay;
	private int qty;
	private int sum;
	private int mileage;
	private int price;
	private int prodRemain;
	
	
	public OrderedVO(String id, String name, int price, int prodRemain) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.prodRemain = prodRemain;
	}

	//상품별 재고 저장
	public OrderedVO(String id, String name, int prodRemain) {
		super();
		this.id = id;
		this.name = name;
		this.prodRemain = prodRemain;
	}

	public OrderedVO(String id, String name, String add, String hp,int mileage) {
		this.id = id;
		this.name = name;
		this.add = add;
		this.hp = hp;
		this.mileage = mileage;
	}
	
	public OrderedVO(String orderId, String id,String orderName, String orderAdd,String orderPay,int orderQty, int orderSum) {
		this.orderId = orderId;
		this.id = id;
		this.name = orderName;
		this.add = orderAdd;
		this.pay = orderPay;
		this.qty = orderQty;
		this.sum = orderSum;
	}
	
	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getProdRemain() {
		return prodRemain;
	}

	public void setProdRemain(int prodRemain) {
		this.prodRemain = prodRemain;
	}

	@Override
	public String toString() {
		return String.format("%s\t%d\t%s\t",  id, price, name);
	}

	
	
}
