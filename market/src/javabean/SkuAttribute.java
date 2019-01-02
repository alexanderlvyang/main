package javabean;

public class SkuAttribute {
	private int skuId;
	private int productId;
	private int attributeId;
	private String attributeValue;
	private String attributeName;
	public SkuAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SkuAttribute(int skuId, int productId, int attributeId, String attributeValue, String attributeName) {
		super();
		this.skuId = skuId;
		this.productId = productId;
		this.attributeId = attributeId;
		this.attributeValue = attributeValue;
		this.attributeName = attributeName;
	}
	@Override
	public String toString() {
		return "SkuAttribute [skuId=" + skuId + ", productId=" + productId + ", attributeId=" + attributeId
				+ ", attributeValue=" + attributeValue + ", attributeName=" + attributeName + "]";
	}
	public int getSkuId() {
		return skuId;
	}
	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	
}