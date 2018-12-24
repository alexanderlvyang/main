package org.yang.mapper;

import java.util.List;

import org.yang.pojo.Category;

public interface CategoryMapper {
	List<Category> selectCategory();
	int insertCategory(Category category);
	int deleteCategory(Category category);
	Category selectCategoryByName(String name);
	Category selectCategoryById(int id);
	List<Category> selectCategoryByGrade(int grade);
}
