package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.OperationLogsMapper;
import org.yang.mapper.RotationChartMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.OperationLogs;
import org.yang.pojo.RotationChart;
import org.yang.service.RotationChartService;
import org.yang.utils.Utils;
@Service
public class RotationChartServiceImpl implements RotationChartService {
	@Resource
	private RotationChartMapper rotationChartMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Override
	public List<RotationChart> showRotationChart() {
		return rotationChartMapper.selectRotationChart();
	}

	@Override
	public String addRotationChart(RotationChart rotationChart, HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		rotationChart.setCreateTime(currentTime);
		String content="添加了id为"+rotationChart.getChart_id()+"的轮播图";
		int insertStatus = rotationChartMapper.insertRotationChart(rotationChart);
		if(insertStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "添加成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "添加失败";
		}
	}

	@Override
	public String deleteRotationChartById(int chart_id, HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="删除了id为"+chart_id+"的轮播图";
		int deleteStatus = rotationChartMapper.deleteRotationChartById(chart_id);
		if(deleteStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除失败";
		}
	}

}
