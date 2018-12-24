package org.yang.pojo;

public class RotationChart {
	private int chart_id;
	private String chart_thumbnail;
	private String chart_title;
	private String chart_link;
	private String  remarks;
	private String createTime;
	public int getChart_id() {
		return chart_id;
	}
	public void setChart_id(int chart_id) {
		this.chart_id = chart_id;
	}
	public String getChart_thumbnail() {
		return chart_thumbnail;
	}
	public void setChart_thumbnail(String chart_thumbnail) {
		this.chart_thumbnail = chart_thumbnail;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getChart_title() {
		return chart_title;
	}
	public void setChart_title(String chart_title) {
		this.chart_title = chart_title;
	}
	public String getChart_link() {
		return chart_link;
	}
	public void setChart_link(String chart_link) {
		this.chart_link = chart_link;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "RotationChart [chart_id=" + chart_id + ", chart_thumbnail=" + chart_thumbnail + ", chart_title="
				+ chart_title + ", chart_link=" + chart_link + ", remarks=" + remarks + ", createTime=" + createTime
				+ "]";
	}
	
	
	
}
