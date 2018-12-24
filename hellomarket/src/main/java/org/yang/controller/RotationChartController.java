package org.yang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.RotationChart;
import org.yang.service.RotationChartService;

@Controller
public class RotationChartController {
	@Resource
	private RotationChartService rotationChartServiceImpl;
	
	@RequestMapping("rotationChartManage.do")
	public String rotationChart(Model model) {
		model.addAttribute("rotationChartList", rotationChartServiceImpl.showRotationChart());
		return "WEB-INF/pages/rotationchartmanage.jsp";
	}
	@RequestMapping("deleteRotationChart.do")
	@ResponseBody
	public String deleteRotationChart(int chart_id,HttpServletRequest request) {
		return rotationChartServiceImpl.deleteRotationChartById(chart_id, request);
	}
	
	@RequestMapping("addRotationChart.do")
	@ResponseBody
	public String addRotationChart(RotationChart rotationChart, HttpServletRequest request) {
		return rotationChartServiceImpl.addRotationChart(rotationChart, request);
	}
}
