package dao;

import java.sql.Connection;

import javabean.Staff;

public interface StaffOpertaion {
	//��½��֤
	public abstract Staff LoginVrify(String staff_username,String staff_password);
}
