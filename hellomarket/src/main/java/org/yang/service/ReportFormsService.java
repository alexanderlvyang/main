package org.yang.service;

import java.util.List;
import java.util.Map;

public interface ReportFormsService {
	Map<String,String> getColumnChartData();
	List<String> getIncomeByMonth(); 
	List<String> getUsersStatusCount();
}
