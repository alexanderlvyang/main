package javabean;

public class ReceiverAddress {
	private int receiverId;
	private String receiverName;
	private String receiverAddress;
	private String email;
	private String otherAddressName;
	private String receiverPhone;
	private int customerId;
	public ReceiverAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReceiverAddress(int receiverId, String receiverName, String receiverAddress, String email,
			String otherAddressName, String receiverPhone, int customerId) {
		super();
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.email = email;
		this.otherAddressName = otherAddressName;
		this.receiverPhone = receiverPhone;
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "ReceiverAddress [receiverId=" + receiverId + ", receiverName=" + receiverName + ", receiverAddress="
				+ receiverAddress + ", email=" + email + ", otherAddressName=" + otherAddressName + ", receiverPhone="
				+ receiverPhone + ", customerId=" + customerId + "]";
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtherAddressName() {
		return otherAddressName;
	}
	public void setOtherAddressName(String otherAddressName) {
		this.otherAddressName = otherAddressName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
