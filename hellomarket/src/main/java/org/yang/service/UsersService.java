package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Admin;
import org.yang.pojo.Users;

public interface UsersService {
	String registerUser(Users user);

	StringBuffer createCaptcha();

	String sendEmail(String emailAddress, HttpServletRequest request, String emailCaptcha, String message);

	Users verifyPhone(String phone);

	Users verifyUsername(String username);

	Users verifyUser(Users user);

	String getUserEmail(String username);

	int updatePassword(String newPassword, String username,String updateTime);

	int getTotalPage(int limit, String condition);

	List<Users> selectUserByPageAndCondition(String currentPage, int limit, String condition,
			HttpServletRequest request);

	String updateStatus(int id, String status,String updateTime);
	Admin verifyAdmin(Admin admin);
	String updateUsernameById(int id,String username);
	String updatePhoneById(int id,String phone);
	String updateEmailById(int id,String email);
	String deleteAddress(int addressId);
}
