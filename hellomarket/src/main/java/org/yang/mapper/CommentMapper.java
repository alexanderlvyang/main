package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.Comment;

public interface CommentMapper {
	List<Comment> selectComment(@Param("startPage") int startPage,@Param("limit") int limit);
	List<Comment> selectCommentByCondition(@Param("condition") String condition,@Param("startPage") int startPage,@Param("limit") int limit);
	int deleteComment(int comment_id);
	int selectCount();
	int selectCountByCondition(String condition);
	List<Comment> selectCommentByProductId(@Param("productId")int product_id, @Param("startPage")int startPage, @Param("limit")int limit,@Param("grade")String grade);
	int selectCountByProductId(@Param("productId")int productId,@Param("grade")String grade);
	int insertComment(Comment comment);
}
