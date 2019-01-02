package javabean;

public class Refund {
	private Order order;
	private String createTime;
	private int staffId;
	private String refundIntroduction;
	public Refund() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Refund(Order order, String createTime, int staffId, String refundIntroduction) {
		super();
		this.order = order;
		this.createTime = createTime;
		this.staffId = staffId;
		this.refundIntroduction = refundIntroduction;
	}
	@Override
	public String toString() {
		return "refund [order=" + order + ", createTime=" + createTime + ", staffId=" + staffId
				+ ", refundIntroduction=" + refundIntroduction + "]";
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getRefundIntroduction() {
		return refundIntroduction;
	}
	public void setRefundIntroduction(String refundIntroduction) {
		this.refundIntroduction = refundIntroduction;
	}
	
}
