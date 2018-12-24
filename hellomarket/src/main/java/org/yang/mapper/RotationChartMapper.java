package org.yang.mapper;

import java.util.List;

import org.yang.pojo.RotationChart;

public interface RotationChartMapper {
	List<RotationChart> selectRotationChart();
	int deleteRotationChartById(int chart_id);
	int insertRotationChart(RotationChart rotationChart);
}
