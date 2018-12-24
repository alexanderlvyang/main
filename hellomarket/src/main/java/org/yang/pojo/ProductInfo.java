package org.yang.pojo;

public class ProductInfo {
	private int product_id;
	private int specification_id;
	private String product_name;
	private String product_introduction;
	private String product_describe;
	private String product_color;
	private String product_specification;
	private String product_thumbnail;
	private double price;
	private int count;
	private double total_price;
	
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	private String product_price;
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getSpecification_id() {
		return specification_id;
	}
	public void setSpecification_id(int specification_id) {
		this.specification_id = specification_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_introduction() {
		return product_introduction;
	}
	public void setProduct_introduction(String product_introduction) {
		this.product_introduction = product_introduction;
	}
	public String getProduct_describe() {
		return product_describe;
	}
	public void setProduct_describe(String product_describe) {
		this.product_describe = product_describe;
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
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	@Override
	public String toString() {
		return "ProductInfo [product_id=" + product_id + ", specification_id=" + specification_id + ", product_name="
				+ product_name + ", product_introduction=" + product_introduction + ", product_describe="
				+ product_describe + ", product_color=" + product_color + ", product_specification="
				+ product_specification + ", product_thumbnail=" + product_thumbnail + ", price=" + price + ", count="
				+ count + ", total_price=" + total_price + ", product_price=" + product_price + "]";
	}
	

	
	
}
