package org.yang.pojo;

public class OperationLogs {
	private int operation_id;
	private String operation_person;
	private String operation_content;
	private String operation_status;
	private String operation_time;
	public int getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}
	public String getOperation_person() {
		return operation_person;
	}
	public void setOperation_person(String operation_person) {
		this.operation_person = operation_person;
	}
	public String getOperation_content() {
		return operation_content;
	}
	public void setOperation_content(String operation_content) {
		this.operation_content = operation_content;
	}
	public String getOperation_status() {
		return operation_status;
	}
	public void setOperation_status(String operation_status) {
		this.operation_status = operation_status;
	}
	public String getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(String operation_time) {
		this.operation_time = operation_time;
	}
	@Override
	public String toString() {
		return "OperationLogs [operation_id=" + operation_id + ", operation_person=" + operation_person
				+ ", operation_content=" + operation_content + ", operation_status=" + operation_status
				+ ", operation_time=" + operation_time + "]";
	}
	
}
