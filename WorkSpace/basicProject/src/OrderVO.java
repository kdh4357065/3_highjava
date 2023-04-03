package basicProject;

import java.util.List;

public class OrderVO {
	private List<ProdVO> prodVO;
	private String address;
	private String phoneNumber;
	private String paymentMethod;
	
	//배송정보
	public OrderVO(String address, String phoneNumber, String paymentMethod) {
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.paymentMethod = paymentMethod;
	}
	
	//최종주문
	public OrderVO(List<ProdVO> prodVO, String address, String phoneNumber, String paymentMethod) {
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.paymentMethod = paymentMethod;
		this.setProdVO(prodVO);
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public List<ProdVO> getProdVO() {
		return prodVO;
	}

	public void setProdVO(List<ProdVO> prodVO) {
		this.prodVO = prodVO;
	}
	
	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t", address, phoneNumber, paymentMethod);
	}
}
