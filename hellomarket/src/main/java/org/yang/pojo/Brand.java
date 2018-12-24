package org.yang.pojo;

public class Brand {
	private int brand_id;
	private String brand_name;
	private String brand_company;
	private String brand_englishName;
	private String brand_pinyin;
	private String brand_joinTime;
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getBrand_company() {
		return brand_company;
	}
	public void setBrand_company(String brand_company) {
		this.brand_company = brand_company;
	}
	public String getBrand_englishName() {
		return brand_englishName;
	}
	public void setBrand_englishName(String brand_englishName) {
		this.brand_englishName = brand_englishName;
	}
	public String getBrand_pinyin() {
		return brand_pinyin;
	}
	public void setBrand_pinyin(String brand_pinyin) {
		this.brand_pinyin = brand_pinyin;
	}
	public String getBrand_joinTime() {
		return brand_joinTime;
	}
	public void setBrand_joinTime(String brand_joinTime) {
		this.brand_joinTime = brand_joinTime;
	}
	@Override
	public String toString() {
		return "Brand [brand_id=" + brand_id + ", brand_name=" + brand_name + ", brand_company=" + brand_company
				+ ", brand_englishName=" + brand_englishName + ", brand_pinyin=" + brand_pinyin + ", brand_joinTime="
				+ brand_joinTime + "]";
	}
}
