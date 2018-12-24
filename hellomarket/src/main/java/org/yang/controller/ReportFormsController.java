package org.yang.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.service.ReportFormsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ReportFormsController {
	@Resource
	private ReportFormsService reportFormsServiceImpl;
	@RequestMapping("reportFormsManage.do")
	public String reposrtFormsManage() {
		return "WEB-INF/pages/reportforms.jsp";
	}
	@RequestMapping("getColumnChartData")
	@ResponseBody
	public Object getColumnChartData() {
		Map<String,String> columnChartDataMap=reportFormsServiceImpl.getColumnChartData();
		JSONObject json=JSONObject.fromObject(columnChartDataMap);
		return json;
	}
	@RequestMapping("getIncome")
	@ResponseBody
	public Object getIncome() {
		List<String> incomeList = reportFormsServiceImpl.getIncomeByMonth();
		JSONArray json=JSONArray.fromObject(incomeList);
		return json;
	}
	@RequestMapping("getUsersStatusCount")
	@ResponseBody
	public Object getUsersStatusCount() {
		List<String> userStatusList = reportFormsServiceImpl.getUsersStatusCount();
		JSONArray json=JSONArray.fromObject(userStatusList);
		return json;
	}
}
