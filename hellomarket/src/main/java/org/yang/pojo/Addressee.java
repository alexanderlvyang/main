package org.yang.pojo;
public class Addressee {
	private int addressee_id;
	private String addressee_name;
	private String addressee_phone;
	private String addressee_address;
	private int id;
	private String createTime;
	public int getAddressee_id() {
		return addressee_id;
	}
	public void setAddressee_id(int addressee_id) {
		this.addressee_id = addressee_id;
	}
	public String getAddressee_name() {
		return addressee_name;
	}
	public void setAddressee_name(String addressee_name) {
		this.addressee_name = addressee_name;
	}
	public String getAddressee_phone() {
		return addressee_phone;
	}
	public void setAddressee_phone(String addressee_phone) {
		this.addressee_phone = addressee_phone;
	}
	public String getAddressee_address() {
		return addressee_address;
	}
	public void setAddressee_address(String addressee_address) {
		this.addressee_address = addressee_address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Addressee [addressee_id=" + addressee_id + ", addressee_name=" + addressee_name + ", addressee_phone="
				+ addressee_phone + ", addressee_address=" + addressee_address + ", id=" + id + ", createTime="
				+ createTime + "]";
	}

	
}
