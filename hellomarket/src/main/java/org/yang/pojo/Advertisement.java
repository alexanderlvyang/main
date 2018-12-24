package org.yang.pojo;

public class Advertisement {
	private int advertise_id;
	private String advertise_name;
	private String advertise_thumbnail;
	private String advertise_range;
	private String advertise_link;
	private String advertise_content;
	private String advertise_type;
	private String beginTime;
	private String endTime;
	private String createTime;
	private String updateTime;
	public int getAdvertise_id() {
		return advertise_id;
	}
	public void setAdvertise_id(int advertise_id) {
		this.advertise_id = advertise_id;
	}
	public String getAdvertise_name() {
		return advertise_name;
	}
	public void setAdvertise_name(String advertise_name) {
		this.advertise_name = advertise_name;
	}
	public String getAdvertise_thumbnail() {
		return advertise_thumbnail;
	}
	public void setAdvertise_thumbnail(String advertise_thumbnail) {
		this.advertise_thumbnail = advertise_thumbnail;
	}
	public String getAdvertise_range() {
		return advertise_range;
	}
	public void setAdvertise_range(String advertise_range) {
		this.advertise_range = advertise_range;
	}
	public String getAdvertise_link() {
		return advertise_link;
	}
	public void setAdvertise_link(String advertise_link) {
		this.advertise_link = advertise_link;
	}
	public String getAdvertise_content() {
		return advertise_content;
	}
	public void setAdvertise_content(String advertise_content) {
		this.advertise_content = advertise_content;
	}
	public String getAdvertise_type() {
		return advertise_type;
	}
	public void setAdvertise_type(String advertise_type) {
		this.advertise_type = advertise_type;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Advertisement [advertise_id=" + advertise_id + ", advertise_name=" + advertise_name
				+ ", advertise_thumbnail=" + advertise_thumbnail + ", advertise_range=" + advertise_range
				+ ", advertise_lingk=" + advertise_link + ", advertise_content=" + advertise_content
				+ ", advertise_type=" + advertise_type + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	
}
