package org.yang.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.CommentMapper;
import org.yang.mapper.OperationLogsMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.Comment;
import org.yang.pojo.OperationLogs;
import org.yang.service.CommentService;
import org.yang.service.SensitiveWordService;
import org.yang.utils.Utils;
@Service
public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentMapper commentMapper; 
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Resource
	private SensitiveWordService sensitiveWordServiceImpl;
	private int limit=20;
	@Override
	public List<Comment> showComment(String condition, String startPage, HttpServletRequest request) {
		List<Comment> commentList;
		if(Utils.judgEmpty(startPage)) {
			startPage="1";
		}
		if(Utils.judgEmpty(condition)) {
			request.getSession().removeAttribute("condition");
			commentList=commentMapper.selectComment((Integer.parseInt(startPage)-1)*limit, limit);
		}else {
			condition=Utils.judgeChiese(condition);
			request.getSession().setAttribute("condition", condition);
			commentList=commentMapper.selectCommentByCondition(condition,(Integer.parseInt(startPage)-1)*limit, limit);
		}
		return commentList;
	}

	@Override
	public String deleteComment(int comment_id,HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="删除了id为"+comment_id+"评论";
		int deleteStatus=commentMapper.deleteComment(comment_id);
		if(deleteStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除失败";
		}
	}

	@Override
	public int getTotalPage(String condition) {
		int count=0;
		if(Utils.judgEmpty(condition)) {
			count=commentMapper.selectCount();
		}else {
			condition=Utils.judgeChiese(condition);
			count=commentMapper.selectCountByCondition(condition);
		}
		int totalPage=(int) Math.ceil(count/(limit*1.0));
		return totalPage;
	}

	@Override
	public List<Comment> showCommentByProductId(int productId, String startPage,String grade) {
		int limit = 10;
		if(Utils.judgEmpty(startPage)) {
			startPage="1";
		}
		return commentMapper.selectCommentByProductId(productId,(Integer.parseInt(startPage)-1)*limit,limit,grade);
	}

	@Override
	public int getTotalPageByProductId(int productId,String grade) {
		int limit = 10;
		int count=commentMapper.selectCountByProductId(productId,grade);
		int totalPage=(int) Math.ceil(count/(limit*1.0));
		return totalPage;
	}

	@Override
	public int addComment(String grade,String username,String productId,String comment_content) {
		Set<String> sensitiveWordSet = sensitiveWordServiceImpl.getSensitiveWordSet();
		Utils.init(sensitiveWordSet);
		boolean isExist =false;
		try {
			isExist= Utils.contains(comment_content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isExist) {
			try {
				comment_content=Utils.replaceSensitiveWord(comment_content, '*');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Comment comment = new Comment();
		comment.setComment_content(comment_content);
		comment.setComment_grade(grade);
		comment.setCreateTime(Utils.getCurrentTime());
		comment.setProduct_id(Integer.parseInt(productId));
		comment.setUsername(username);
		commentMapper.insertComment(comment);
		return comment.getComment_id();
	}


}
