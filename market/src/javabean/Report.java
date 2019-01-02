package javabean;

public class Report {
	private String brand;
	private String category;
	private int sellTotalNumber;
	private double sellTotalPrice;
	private String createTime;
	private String updateTime;
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Report(String brand, String category, int sellTotalNumber, double sellTotalPrice, String createTime,
			String updateTime) {
		super();
		this.brand = brand;
		this.category = category;
		this.sellTotalNumber = sellTotalNumber;
		this.sellTotalPrice = sellTotalPrice;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Report [brand=" + brand + ", category=" + category + ", sellTotalNumber=" + sellTotalNumber
				+ ", sellTotalPrice=" + sellTotalPrice + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSellTotalNumber() {
		return sellTotalNumber;
	}
	public void setSellTotalNumber(int sellTotalNumber) {
		this.sellTotalNumber = sellTotalNumber;
	}
	public double getSellTotalPrice() {
		return sellTotalPrice;
	}
	public void setSellTotalPrice(double sellTotalPrice) {
		this.sellTotalPrice = sellTotalPrice;
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
	
}
