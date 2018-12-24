package org.yang.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yang.mapper.ReportFormsMapper;
import org.yang.service.ReportFormsService;
@Service
public class ReportFormsServiceImpl implements ReportFormsService {
	@Resource
	private ReportFormsMapper reportFormsMapper;
	@Override
	public Map<String, String> getColumnChartData() {
		Map<String,String> columnChartDataMap=new HashMap<String,String>();
		columnChartDataMap.put("商品数", String.valueOf(reportFormsMapper.selectProductCount()));
		columnChartDataMap.put("用户数", String.valueOf(reportFormsMapper.selectUsersCount()));
		columnChartDataMap.put("订单数", String.valueOf(reportFormsMapper.selectOrdersCount()));
		columnChartDataMap.put("评论数", String.valueOf(reportFormsMapper.selectCommentCount()));
		columnChartDataMap.put("品牌数", String.valueOf(reportFormsMapper.selectBrandCount()));
		columnChartDataMap.put("分类数", String.valueOf(reportFormsMapper.selectCategoryCount()));
		return columnChartDataMap;
	}
	@Override
	public List<String> getIncomeByMonth() {
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		List<String> incomeList=new ArrayList<String>();
		for(int i=1;i<=12;i++) {
			String income=reportFormsMapper.selectIncomeByMonth(year+"-"+i+"-");
			if(income==null) {
				income="0";
			}
			incomeList.add(String.valueOf(income));
		}
		return incomeList;
	}
	@Override
	public List<String> getUsersStatusCount() {
		List<String> incomeList=new ArrayList<String>();
		String VIPCount=reportFormsMapper.selectUsersStatusCount("VIP");
		String normalCount=reportFormsMapper.selectUsersStatusCount("普通用户");
		String blockadeCount=reportFormsMapper.selectUsersStatusCount("封号");
		if(VIPCount==null) {
			VIPCount="0";
		}
		if(normalCount==null) {
			normalCount="0";
		}
		if(blockadeCount==null) {
			blockadeCount="0";
		}
		incomeList.add(VIPCount);
		incomeList.add(normalCount);
		incomeList.add(blockadeCount);
		return incomeList;
	}

}
