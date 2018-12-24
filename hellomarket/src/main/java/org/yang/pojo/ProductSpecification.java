package org.yang.pojo;

public class ProductSpecification {
	private int specification_id;
	private int product_id;
	private String product_color;
	private String product_specification;
	private String product_thumbnail;
	private String status;
	private double  price;
	private String createTime;
	private String updateTime;
	public int getSpecification_id() {
		return specification_id;
	}
	public void setSpecification_id(int specification_id) {
		this.specification_id = specification_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProduct_thumbnail() {
		return product_thumbnail;
	}
	public void setProduct_thumbnail(String product_thumbnail) {
		this.product_thumbnail = product_thumbnail;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "ProductSpecification [specification_id=" + specification_id + ", product_id=" + product_id
				+ ", product_color=" + product_color + ", product_specification=" + product_specification
				+ ", product_thumbnail=" + product_thumbnail + ", status=" + status + ", price=" + price
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	
	
}
