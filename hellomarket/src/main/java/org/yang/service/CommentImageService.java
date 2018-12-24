package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.CommentImage;

public interface CommentImageService {
	List<CommentImage> showCommentImage(int comment_id);
	String deleteCommentImage(int commentImage_id,HttpServletRequest request );
	int addCommentImage(CommentImage commentImage);
	List<CommentImage> selectCommentImageByProductId(int product_id);
}
