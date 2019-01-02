package javabean;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerUsername;
	private String customerPassword;
	private String customerPhoneNumber;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int customerId, String customerName, String customerUsername, String customerPassword,
			String customerPhoneNumber) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.customerPhoneNumber = customerPhoneNumber;
	}
	@Override
	public String toString() {
		return "customer [customerId=" + customerId + ", customerName=" + customerName + ", customerUsername="
				+ customerUsername + ", customerPassword=" + customerPassword + ", customerPhoneNumber="
				+ customerPhoneNumber + "]";
	}
	public int getCustomerId() {
		return customerId;
	}
	public Customer setCustomerId(int customerId) {
		this.customerId = customerId;
		return this;
	}
	public String getCustomerName() {
		return customerName;
	}
	public Customer setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public Customer setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
		return this;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public Customer setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
		return this;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public Customer setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
		return this;
	}
	
}
