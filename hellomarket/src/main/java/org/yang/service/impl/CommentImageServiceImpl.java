package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.CommentImageMapper;
import org.yang.mapper.OperationLogsMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.CommentImage;
import org.yang.pojo.OperationLogs;
import org.yang.service.CommentImageService;
import org.yang.utils.Utils;
@Service
public class CommentImageServiceImpl implements CommentImageService {
	@Resource
	private CommentImageMapper commentImageMapper;
	@Resource
	private OperationLogsMapper operaitonLogsMapper;
	@Override
	public List<CommentImage> showCommentImage(int comment_id) {
		return commentImageMapper.selectCommentImage(comment_id);
	}
	
	@Override
	public String deleteCommentImage(int commentImage_id,HttpServletRequest request ) {
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="删除了id为"+commentImage_id+"的评论图片";
		int deleteStatus = commentImageMapper.deleteCommentImage(commentImage_id);
		if(deleteStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除成功", admin.getUsername());
			operaitonLogsMapper.insertOperation(operationLogs);
			return "删除成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除失败", admin.getUsername());
			operaitonLogsMapper.insertOperation(operationLogs);
			return "删除失败";
		}
	}

	@Override
	public int addCommentImage(CommentImage commentImage) {
		return commentImageMapper.insertCommentIamge(commentImage);
	}

	@Override
	public List<CommentImage> selectCommentImageByProductId(int product_id) {
		return commentImageMapper.selectCommentImageByProduct(product_id);
	}

}
