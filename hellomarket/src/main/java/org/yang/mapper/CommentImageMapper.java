package org.yang.mapper;

import java.util.List;

import org.yang.pojo.CommentImage;

public interface CommentImageMapper {
	List<CommentImage> selectCommentImage(int comment_id);
	int deleteCommentImage(int commentImage_id);
	int insertCommentIamge(CommentImage commentImage);
	List<CommentImage> selectCommentImageByProduct(int product_id);
}
