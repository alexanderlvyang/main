package javabean;

public class CategoryAttribute {
	private int attributeId;
	public String attributeName;
	private int categoryId;
	public CategoryAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryAttribute(int attributeId, String attributeName, int categoryId) {
		super();
		this.attributeId = attributeId;
		this.attributeName = attributeName;
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "CategoryAttribute [attributeId=" + attributeId + ", attributeName=" + attributeName + ", categoryId="
				+ categoryId + "]";
	}
	public int getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
