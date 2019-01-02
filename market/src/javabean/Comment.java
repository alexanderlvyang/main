package javabean;

public class Comment {
	private int commentId;
	private String commentValue;
	private int productId;
	private int customerId;
	private String customerName;
	private String commentLevel;
	private String createTime;
	private String updateTime;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int commentId, String commentValue, int productId, int customerId, String customerName,
			String commentLevel, String createTime, String updateTime) {
		super();
		this.commentId = commentId;
		this.commentValue = commentValue;
		this.productId = productId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.commentLevel = commentLevel;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentValue=" + commentValue + ", productId=" + productId
				+ ", customerId=" + customerId + ", customerName=" + customerName + ", commentLevel=" + commentLevel
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	public int getCommentId() {
		return commentId;
	}
	public Comment setCommentId(int commentId) {
		this.commentId = commentId;
		return this;
	}
	public String getCommentValue() {
		return commentValue;
	}
	public Comment setCommentValue(String commentValue) {
		this.commentValue = commentValue;
		return this;
	}
	public int getProductId() {
		return productId;
	}
	public Comment setProductId(int productId) {
		this.productId = productId;
		return this;
	}
	public int getCustomerId() {
		return customerId;
	}
	public Comment setCustomerId(int customerId) {
		this.customerId = customerId;
		return this;
	}
	public String getCustomerName() {
		return customerName;
	}
	public Comment setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public String getCommentLevel() {
		return commentLevel;
	}
	public Comment setCommentLevel(String commentLevel) {
		this.commentLevel = commentLevel;
		return this;
	}
	public String getCreateTime() {
		return createTime;
	}
	public Comment setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public Comment setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
}
