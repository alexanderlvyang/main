package org.yang.pojo;

public class SensitiveWord {
	private int sensitive_id;
	private String sensitive_content;
	private String createTime;
	public int getSensitive_id() {
		return sensitive_id;
	}
	public void setSensitive_id(int sensitive_id) {
		this.sensitive_id = sensitive_id;
	}
	public String getSensitive_content() {
		return sensitive_content;
	}
	public void setSensitive_content(String sensitive_content) {
		this.sensitive_content = sensitive_content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "SensitiveWord [sensitive_id=" + sensitive_id + ", sensitive_content=" + sensitive_content
				+ ", createTime=" + createTime + "]";
	}
	
	
}
