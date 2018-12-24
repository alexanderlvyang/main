package org.yang.pojo;

import java.util.List;

public class OrdersInfo {
	private String addressee_name;
	private String addressee_phone;
	private String addressee_address;
	private long order_id;
	private String order_status;
	private double order_totalPrice;
/*	private String product_name;
	private String product_color;
	private String product_specification;
	private String product_thumbnail;
	private double price;*/
	private String specification_id;
	private List<ProductInfo> productInfoList; 
	private String phone;
	private String createTime;
	private String updateTime;
	public String getAddressee_name() {
		return addressee_name;
	}
	public void setAddressee_name(String addressee_name) {
		this.addressee_name = addressee_name;
	}
	public String getAddressee_phone() {
		return addressee_phone;
	}
	public void setAddressee_phone(String addressee_phone) {
		this.addressee_phone = addressee_phone;
	}
	public String getAddressee_address() {
		return addressee_address;
	}
	public void setAddressee_address(String addressee_address) {
		this.addressee_address = addressee_address;
	}
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
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
	
	/*public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_color() {
		return product_color;
	}
	public void setProduct_color(String product_color) {
		this.product_color = product_color;
	}
	public String getProduct_specification() {
		return product_specification;
	}
	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}
	public String getProduct_thumbnail() {
		return product_thumbnail;
	}
	public void setProduct_thumbnail(String product_thumbnail) {
		this.product_thumbnail = product_thumbnail;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}*/
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public List<ProductInfo> getProductInfoList() {
		return productInfoList;
	}
	public void setProductInfoList(List<ProductInfo> productInfoList) {
		this.productInfoList = productInfoList;
	}
	@Override
	public String toString() {
		return "OrdersInfo [addressee_name=" + addressee_name + ", addressee_phone=" + addressee_phone
				+ ", addressee_address=" + addressee_address + ", order_id=" + order_id + ", order_status="
				+ order_status + ", order_totalPrice=" + order_totalPrice + ", specification_id=" + specification_id
				+ ", productInfoList=" + productInfoList + ", phone=" + phone + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
	
	
	
	
	
	
	
	
}
