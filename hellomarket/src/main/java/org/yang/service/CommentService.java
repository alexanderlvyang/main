package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Comment;

public interface CommentService {
	List<Comment> showComment(String condition,String startPage,HttpServletRequest request);
	String deleteComment(int comment_id,HttpServletRequest request);
	int getTotalPage(String condition);
	List<Comment> showCommentByProductId(int productId, String startPage,String grade);
	int getTotalPageByProductId(int productId,String grade);
	int addComment(String grade,String username,String productId,String comment_content);
}
