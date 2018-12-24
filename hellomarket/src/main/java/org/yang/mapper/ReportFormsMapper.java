package org.yang.mapper;

public interface ReportFormsMapper {
	int selectProductCount();
	int selectUsersCount();
	int selectOrdersCount();
	int selectCommentCount();
	int selectBrandCount();
	int selectCategoryCount();
	String selectIncomeByMonth(String month);
	String selectUsersStatusCount(String status);
}
