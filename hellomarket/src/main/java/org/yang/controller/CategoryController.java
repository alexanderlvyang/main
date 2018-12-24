package org.yang.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.Category;
import org.yang.service.CategoryService;

@Controller
public class CategoryController {
	@Resource
	private CategoryService categoryServiceImpl;
	@RequestMapping("categoryManage.do")
	public String categoryManage(Model model) {
		List<Category> categoryList = categoryServiceImpl.showCategory("");
		model.addAttribute("categoryList", categoryList);
		return "/WEB-INF/pages/categorymanage.jsp";
	}
	@RequestMapping("addCategory.do")
	@ResponseBody
	public String addCategory(Category category,String parentName,HttpServletRequest request) {
		String addStatus = categoryServiceImpl.addCategory(category,parentName,request);
		return addStatus;
	}
	@RequestMapping("deleteCategory.do")
	@ResponseBody
	public String deleteCategory(Category category,HttpServletRequest request) {
		String deleteStatus=categoryServiceImpl.deleteCategory(category,request);
		return deleteStatus;
	}
}
