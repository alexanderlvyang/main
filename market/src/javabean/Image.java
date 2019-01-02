package javabean;

public class Image {
	private int imageId;
	private int productId;
	private String imageUrl;
	private String imageCategory;
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image(int imageId, int productId, String imageUrl, String imageCategory) {
		super();
		this.imageId = imageId;
		this.productId = productId;
		this.imageUrl = imageUrl;
		this.imageCategory = imageCategory;
	}
	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", productId=" + productId + ", imageUrl=" + imageUrl + ", imageCategory="
				+ imageCategory + "]";
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageCategory() {
		return imageCategory;
	}
	public void setImageCategory(String imageCategory) {
		this.imageCategory = imageCategory;
	}
	
	
}
