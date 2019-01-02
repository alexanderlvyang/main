package javabean;

public class Brand {
	private int brand_id;
	private String brand_name;
	private String brand_englishName;
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Brand(int brand_id, String brand_name, String brand_englishName) {
		super();
		this.brand_id = brand_id;
		this.brand_name = brand_name;
		this.brand_englishName = brand_englishName;
	}
	@Override
	public String toString() {
		return "Brand [brand_id=" + brand_id + ", brand_name=" + brand_name + ", brand_englishName=" + brand_englishName
				+ "]";
	}
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
	public String getBrand_englishName() {
		return brand_englishName;
	}
	public void setBrand_englishName(String brand_englishName) {
		this.brand_englishName = brand_englishName;
	}
	
}
