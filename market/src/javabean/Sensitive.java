package javabean;

public class Sensitive {
	private int sensitiveId;
	private String sensitiveValue;
	public Sensitive() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sensitive(int sensitiveId, String sensitiveValue) {
		super();
		this.sensitiveId = sensitiveId;
		this.sensitiveValue = sensitiveValue;
	}
	@Override
	public String toString() {
		return "sensitive [sensitiveId=" + sensitiveId + ", sensitiveValue=" + sensitiveValue + "]";
	}
	public int getSensitiveId() {
		return sensitiveId;
	}
	public void setSensitiveId(int sensitiveId) {
		this.sensitiveId = sensitiveId;
	}
	public String getSensitiveValue() {
		return sensitiveValue;
	}
	public void setSensitiveValue(String sensitiveValue) {
		this.sensitiveValue = sensitiveValue;
	}

	
}
