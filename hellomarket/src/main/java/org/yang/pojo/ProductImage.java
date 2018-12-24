package org.yang.pojo;

public class ProductImage {
	private int	productImage_id;
	private int product_id;
	private String thumbnail;
	private String createTime;
	public int getProductImage_id() {
		return productImage_id;
	}
	public void setProductImage_id(int productImage_id) {
		this.productImage_id = productImage_id;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	@Override
	public String toString() {
		return "ProductImage [productImge_id=" + productImage_id + ", product_id=" + product_id + ", thumbnail="
				+ thumbnail + ", createTime=" + createTime + "]";
	}
	
	
}
