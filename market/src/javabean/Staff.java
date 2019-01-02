package javabean;

public class Staff {
private int staffId;//员工id
private String staffName;//员工姓名
private String staffPhoneNumber;//员工手机号
private String staffUsername;//员工用户名
private String staffPassword;//员工密码
private String staffState;//员工状态（在职/离职）
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
