package javabean;

public class OperationLog {
	private int logId;
	private String operationName;
	private String operationContent;
	private Long createTime;
	private Long updateTime;
	public OperationLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OperationLog(int logId, String operationName, String operationContent, Long createTime, Long updateTime) {
		super();
		this.logId = logId;
		this.operationName = operationName;
		this.operationContent = operationContent;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "OperationLog [logId=" + logId + ", operationName=" + operationName + ", operationContent="
				+ operationContent + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationContent() {
		return operationContent;
	}
	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
