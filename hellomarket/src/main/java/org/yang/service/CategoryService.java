package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Category;

public interface CategoryService {
	List<Category> showCategory(String grade);
	String addCategory(Category category,String parentName,HttpServletRequest request);
	String deleteCategory(Category category,HttpServletRequest request);
	Category selectCategoryByName(String categoryName);
}
