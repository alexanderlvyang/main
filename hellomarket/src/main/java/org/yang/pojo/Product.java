package org.yang.pojo;

public class Product {
	private int product_id;
	private String product_name;
	private String product_introduction;
	private String product_status;
	private String product_joinTime;
	private String product_price;
	private String product_describe;
	private int product_category;
	private int product_brand;
	private String pinyin;
	private String product_englishName;
	private String updateTime;
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public String getProduct_joinTime() {
		return product_joinTime;
	}
	public void setProduct_joinTime(String product_joinTime) {
		this.product_joinTime = product_joinTime;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getProduct_describe() {
		return product_describe;
	}
	public void setProduct_describe(String product_describe) {
		this.product_describe = product_describe;
	}
	public int getProduct_category() {
		return product_category;
	}
	public void setProduct_category(int product_category) {
		this.product_category = product_category;
	}
	public int getProduct_brand() {
		return product_brand;
	}
	public void setProduct_brand(int product_brand) {
		this.product_brand = product_brand;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getProduct_englishName() {
		return product_englishName;
	}
	public void setProduct_englishName(String product_englishName) {
		this.product_englishName = product_englishName;
	}
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_introduction="
				+ product_introduction + ", product_status=" + product_status + ", product_joinTime=" + product_joinTime
				+ ", product_price=" + product_price + ", product_describe=" + product_describe + ", product_category="
				+ product_category + ", product_brand=" + product_brand + ", pinyin=" + pinyin
				+ ", product_englishName=" + product_englishName + ", updateTime=" + updateTime + "]";
	}
	
	
}
