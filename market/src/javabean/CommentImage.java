package javabean;

public class CommentImage {
	private int commentImageId;
	private int productId;
	private int commentId;
	private int customerId;
	private String customerName;
	private String imageSrc;
	private String createTime;
	public CommentImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentImage(int commentImageId, int productId, int commentId, int customerId, String customerName,
			String imageSrc, String createTime) {
		super();
		this.commentImageId = commentImageId;
		this.productId = productId;
		this.commentId = commentId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.imageSrc = imageSrc;
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CommentImage [commentImageId=" + commentImageId + ", productId=" + productId + ", commentId="
				+ commentId + ", customerId=" + customerId + ", customerName=" + customerName + ", imageSrc=" + imageSrc
				+ ", createTime=" + createTime + "]";
	}
	public int getCommentImageId() {
		return commentImageId;
	}
	public CommentImage setCommentImageId(int commentImageId) {
		this.commentImageId = commentImageId;
		return this;
	}
	public int getProductId() {
		return productId;
	}
	public CommentImage setProductId(int productId) {
		this.productId = productId;
		return this;
	}
	public int getCommentId() {
		return commentId;
	}
	public CommentImage setCommentId(int commentId) {
		this.commentId = commentId;
		return this;
	}
	public int getCustomerId() {
		return customerId;
	}
	public CommentImage setCustomerId(int customerId) {
		this.customerId = customerId;
		return this;
	}
	public String getCustomerName() {
		return customerName;
	}
	public CommentImage setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public CommentImage setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
		return this;
	}
	public String getCreateTime() {
		return createTime;
	}
	public CommentImage setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}
	
}
