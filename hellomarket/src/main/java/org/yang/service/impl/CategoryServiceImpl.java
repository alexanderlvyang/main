package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.yang.mapper.CategoryMapper;
import org.yang.mapper.OperationLogsMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.Category;
import org.yang.pojo.OperationLogs;
import org.yang.service.CategoryService;
import org.yang.utils.Utils;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryMapper categoryMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	private Logger logger=Logger.getLogger(this.getClass().getName());
	@Override
	public List<Category> showCategory(String grade) {
		List<Category> categoryList=null;
		if(grade==null||grade.equals("")) {
			categoryList=categoryMapper.selectCategory();
		}else {
			categoryList=categoryMapper.selectCategoryByGrade(Integer.parseInt(grade));
		}
		return categoryList;
	}
	@Override
	public String addCategory(Category category,String parentName,HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		if(parentName==null||parentName.equals("")) {
			category.setParentId(0);
			category.setGrade(0);
		}else {
			int parentId=categoryMapper.selectCategoryByName(parentName).getId();
			category.setGrade(1);
			Category categoryById = categoryMapper.selectCategoryById(parentId);
			if(categoryById.getParentId()!=0) {
				category.setGrade(2);
			}
			category.setParentId(parentId);
		}
		Category categoryByName = categoryMapper.selectCategoryByName(category.getName());
		String currentTime=Utils.getCurrentTime();
		category.setCreateTime(currentTime);
		if(categoryByName==null) {
			String pinyin =Utils.getPinYin(category.getName());
			category.setPinyin(pinyin);
			String content="添加名称为"+category.getName()+"的分类";
			int addStatus = categoryMapper.insertCategory(category);
			if(addStatus==1) {
				OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
				operationLogsMapper.insertOperation(operationLogs);
				return "添加成功";	
			}else {
				OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
				operationLogsMapper.insertOperation(operationLogs);
				return "添加失败";
			}
		}else {
			return "该分类已存在";
		}
	}
	@Override
	public String deleteCategory(Category category,HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		int categoryId=categoryMapper.selectCategoryByName(category.getName()).getId();
		category.setId(categoryId);
		String currentTime=Utils.getCurrentTime();
		int deleteStatus = categoryMapper.deleteCategory(category);
		String content="删除名称为"+category.getName()+"的分类";
		if(deleteStatus>0) {
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
	public Category selectCategoryByName(String categoryName) {
		Category category = categoryMapper.selectCategoryByName(categoryName);
		return category;
	}
	
}
