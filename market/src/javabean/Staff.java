package javabean;

public class Staff {
private int staffId;//Ա��id
private String staffName;//Ա������
private String staffPhoneNumber;//Ա���ֻ���
private String staffUsername;//Ա���û���
private String staffPassword;//Ա������
private String staffState;//Ա��״̬����ְ/��ְ��
public Staff() {
	super();
	// TODO Auto-generated constructor stub
}
public Staff(int staffId, String staffName, String staffPhoneNumber, String staffUsername, String staffPassword,
		String staffState) {
	super();
	this.staffId = staffId;
	this.staffName = staffName;
	this.staffPhoneNumber = staffPhoneNumber;
	this.staffUsername = staffUsername;
	this.staffPassword = staffPassword;
	this.staffState = staffState;
}
public int getStaffId() {
	return staffId;
}
public Staff setStaffId(int staffId) {
	this.staffId = staffId;
	return this;
}
public String getStaffName() {
	return staffName;
}
public Staff setStaffName(String staffName) {
	this.staffName = staffName;
	return this;
}
public String getStaffPhoneNumber() {
	return staffPhoneNumber;
}
public Staff setStaffPhoneNumber(String staffPhoneNumber) {
	this.staffPhoneNumber = staffPhoneNumber;
	return this;
}
public String getStaffUsername() {
	return staffUsername;
}
public Staff setStaffUsername(String staffUsername) {
	this.staffUsername = staffUsername;
	return this;
}
public String getStaffPassword() {
	return staffPassword;
}
public Staff setStaffPassword(String staffPassword) {
	this.staffPassword = staffPassword;
	return this;
}
public String getStaffState() {
	return staffState;
}
public Staff setStaffState(String staffState) {
	this.staffState = staffState;
	return this;
}
@Override
public String toString() {
	return "staff [staffId=" + staffId + ", staffName=" + staffName + ", staffPhoneNumber=" + staffPhoneNumber
			+ ", staffUsername=" + staffUsername + ", staffPassword=" + staffPassword + ", staffState=" + staffState
			+ "]";
}


}
