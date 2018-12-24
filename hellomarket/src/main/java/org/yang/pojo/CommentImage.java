package org.yang.pojo;

public class CommentImage {
	private int commentImage_id;
	private String comment_thumbnail;
	private int comment_id;
	private int product_id;
	public int getCommentImage_id() {
		return commentImage_id;
	}
	public void setCommentImage_id(int commentImage_id) {
		this.commentImage_id = commentImage_id;
	}
	public String getComment_thumbnail() {
		return comment_thumbnail;
	}
	public void setComment_thumbnail(String comment_thumbnail) {
		this.comment_thumbnail = comment_thumbnail;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "CommentImage{" +
				"commentImage_id=" + commentImage_id +
				", comment_thumbnail='" + comment_thumbnail + '\'' +
				", comment_id=" + comment_id +
				", product_id=" + product_id +
				'}';
	}
}
