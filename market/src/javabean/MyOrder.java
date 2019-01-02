package javabean;

import java.util.ArrayList;

public class MyOrder {
	private double totalPrice;
	private String orderState;
	private String receiverName;
	private ArrayList<Product> productList;
	private String orderNumber;
	private String createTime;
	public MyOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public MyOrder(double totalPrice, String orderState, String receiverName, ArrayList<Product> productList,
			String orderNumber, String createTime) {
		super();
		this.totalPrice = totalPrice;
		this.orderState = orderState;
		this.receiverName = receiverName;
		this.productList = productList;
		this.orderNumber = orderNumber;
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "MyOrder [totalPrice=" + totalPrice + ", orderState=" + orderState + ", receiverName=" + receiverName
				+ ", productList=" + productList + ", orderNumber=" + orderNumber + ", createTime=" + createTime + "]";
	}


	public double getTotalPrice() {
		return totalPrice;
	}
	public MyOrder setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}
	public String getOrderState() {
		return orderState;
	}
	public MyOrder setOrderState(String orderState) {
		this.orderState = orderState;
		return this;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public MyOrder setReceiverName(String receiverName) {
		this.receiverName = receiverName;
		return this;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public MyOrder setProductList(ArrayList<Product> productList) {
		this.productList = productList;
		return this;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public MyOrder setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}


	public String getCreateTime() {
		return createTime;
	}


	public MyOrder setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
}
