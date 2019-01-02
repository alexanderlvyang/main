package dao;

import java.sql.Connection;

import javabean.Staff;

public interface StaffOpertaion {
	//µÇÂ½ÑéÖ¤
	public abstract Staff LoginVrify(String staff_username,String staff_password);
}
