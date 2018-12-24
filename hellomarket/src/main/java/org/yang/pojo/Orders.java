package org.yang.pojo;

public class Orders {
	private long order_id;
	private int addressee_id;
	private String order_status;
	private double order_totalPrice;
	private String specification_id;
	private String createTime;
	private String updateTime;
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public int getAddressee_id() {
		return addressee_id;
	}
	public void setAddressee_id(int addressee_id) {
		this.addressee_id = addressee_id;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public double getOrder_totalPrice() {
		return order_totalPrice;
	}
	public void setOrder_totalPrice(double order_totalPrice) {
		this.order_totalPrice = order_totalPrice;
	}
	public String getSpecification_id() {
		return specification_id;
	}
	public void setSpecification_id(String specification_id) {
		this.specification_id = specification_id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", addressee_id=" + addressee_id + ", order_status=" + order_status
				+ ", order_totalPrice=" + order_totalPrice + ", specification_id=" + specification_id + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	

	
}
