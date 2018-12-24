package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.RotationChart;

public interface RotationChartService {
	List<RotationChart> showRotationChart();
	String addRotationChart(RotationChart rotationChart,HttpServletRequest request);
	String deleteRotationChartById(int chart_id, HttpServletRequest request);
}
