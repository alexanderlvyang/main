package org.yang.pojo;

public class Comment {
	private int comment_id;
	private String comment_content;
	private String  username;
	private int product_id;
	private String comment_grade;
	private String createTime;
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment_grade() {
		return comment_grade;
	}
	public void setComment_grade(String comment_grade) {
		this.comment_grade = comment_grade;
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
		return "Comment{" +
				"comment_id=" + comment_id +
				", comment_content='" + comment_content + '\'' +
				", username='" + username + '\'' +
				", product_id=" + product_id +
				", comment_grade='" + comment_grade + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
