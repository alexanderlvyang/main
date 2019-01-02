package javabean;

public class CategoryAttributeValue {
	private int attributeValueId;
	private int attributeId;
	private String attributeValue;
	public CategoryAttributeValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryAttributeValue(int attributeValueId, int attributeId, String attributeValue) {
		super();
		this.attributeValueId = attributeValueId;
		this.attributeId = attributeId;
		this.attributeValue = attributeValue;
	}
	@Override
	public String toString() {
		return "CategoryAttributeValue [attributeValueId=" + attributeValueId + ", attributeId=" + attributeId
				+ ", attributeValue=" + attributeValue + "]";
	}
	public int getAttributeValueId() {
		return attributeValueId;
	}
	public void setAttributeValueId(int attributeValueId) {
		this.attributeValueId = attributeValueId;
	}
	public int getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
}
