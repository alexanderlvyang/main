package javabean;

public class Order {
	private int customerId;
	private String productId;
	private String customerPhone;
	private double totalPrice;
	private String orderNumber;
	private String createTime;
	private String updateTime;
	private String aliOrderNumber;
	private String receiverPhone;
	private String receiverName;
	private String receiverAddress;
	private String orderState;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Order(int customerId, String productId, String customerPhone, double totalPrice, String orderNumber,
			String createTime, String updateTime, String aliOrderNumber, String receiverPhone, String receiverName,
			String receiverAddress, String orderState) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.customerPhone = customerPhone;
		this.totalPrice = totalPrice;
		this.orderNumber = orderNumber;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.aliOrderNumber = aliOrderNumber;
		this.receiverPhone = receiverPhone;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.orderState = orderState;
	}


	@Override
	public String toString() {
		return "Order [customerId=" + customerId + ", productId=" + productId + ", customerPhone=" + customerPhone
				+ ", totalPrice=" + totalPrice + ", orderNumber=" + orderNumber + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", aliOrderNumber=" + aliOrderNumber + ", receiverPhone="
				+ receiverPhone + ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress
				+ ", orderState=" + orderState + "]";
	}


	public int getCustomerId() {
		return customerId;
	}
	public Order setCustomerId(int customerId) {
		this.customerId = customerId;
		return this;
	}
	public String getProductId() {
		return productId;
	}
	public Order setProductId(String productId) {
		this.productId = productId;
		return this;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public Order setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
		return this;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public Order setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public Order setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}
	public String getCreateTime() {
		return createTime;
	}
	public Order setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public Order setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public String getAliOrderNumber() {
		return aliOrderNumber;
	}
	public Order setAliOrderNumber(String aliOrderNumber) {
		this.aliOrderNumber = aliOrderNumber;
		return this;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public Order setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
		return this;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public Order setReceiverName(String receiverName) {
		this.receiverName = receiverName;
		return this;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public Order setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
		return this;
	}

	public String getOrderState() {
		return orderState;
	}


	public Order setOrderState(String orderState) {
		this.orderState = orderState;
		return this;
	}
}
