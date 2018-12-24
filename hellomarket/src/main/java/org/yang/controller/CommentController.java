package org.yang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.service.CommentImageService;
import org.yang.service.CommentService;

@Controller
public class CommentController {
	@Resource
	private CommentService commentServiceImpl;
	@Resource
	private CommentImageService commentImageServiceImpl;
	
	@RequestMapping("commentManage.do")
	public String commentManage(Model model,String condition,String startPage,HttpServletRequest request) {
		model.addAttribute("commentList", commentServiceImpl.showComment(condition, startPage, request));
		model.addAttribute("totalPage", commentServiceImpl.getTotalPage(condition));
		return "WEB-INF/pages/commentmanage.jsp";
	}
	@RequestMapping("deleteComment.do")
	@ResponseBody
	public String deleteComment(int comment_id,HttpServletRequest request) {
		return commentServiceImpl.deleteComment(comment_id, request);
	}
	@RequestMapping("commentImageManage.do")
	public String commentImageManage(Model model,int comment_id) {
		model.addAttribute("commentImageList", commentImageServiceImpl.showCommentImage(comment_id));
		return "WEB-INF/pages/commentimage.jsp";
	}
	@RequestMapping("deleteCommentImage.do")
	@ResponseBody
	public String deleteCommentImage(int commentImage_id,HttpServletRequest request) {
		return commentImageServiceImpl.deleteCommentImage(commentImage_id, request);
	}
}
