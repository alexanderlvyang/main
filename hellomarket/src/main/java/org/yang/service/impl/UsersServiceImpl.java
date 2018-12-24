package org.yang.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.AddressMapper;
import org.yang.mapper.UsersMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.Users;
import org.yang.service.UsersService;
import org.yang.utils.Utils;

@Service
public class UsersServiceImpl implements UsersService {
	@Resource
	private UsersMapper usersMapper;
	@Resource
	private AddressMapper addressMapper;

	public String registerUser(Users user) {
		String password = user.getPassword();
		String encodePassword = "";
		String currentTime=Utils.getCurrentTime();
		user.setCreateTime(currentTime);
		user.setUpdateTime(currentTime);
		try {
			encodePassword = Utils.MD5Encode(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPassword(encodePassword);
		int registerStatus = usersMapper.insertUsers(user);
		if (registerStatus == 1) {
			return "注册成功";
		} else {
			return "注册失败";
		}
	}

	public StringBuffer createCaptcha() {
		Random rand = new Random();
		StringBuffer captcha = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			captcha.append(rand.nextInt(10) + "");
		}
		return captcha;
	}

	@Override
	public String sendEmail(String emailAddress, HttpServletRequest request, String emailCaptcha, String message) {
		String status = Utils.sendEmail(emailAddress, request, emailCaptcha, message);
		request.getSession().setAttribute("emailCaptcha", emailCaptcha);
		return status;
	}

	@Override
	public Users verifyPhone(String phone) {
		return usersMapper.selectPhone(phone);
	}

	@Override
	public Users verifyUsername(String username) {
		return usersMapper.selectUsername(username);
	}

	@Override
	public Users verifyUser(Users user) {
		return usersMapper.selectUser(user);
	}

	@Override
	public String getUserEmail(String username) {
		return usersMapper.selectUsername(username).getEmail();
	}

	@Override
	public int updatePassword(String newPassword, String username,String updateTime) {
		int updateStatus = usersMapper.updatePassword(newPassword, username,updateTime);
		return updateStatus;
	}

	@Override
	public int getTotalPage(int limit, String condition) {
		int count = 0;
		if (condition == null || condition.equals("")) {
			count = usersMapper.selectUserCount();
		} else {
			Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher matcher = pattern.matcher(condition);
			if (!(matcher.find())) {
				try {
					condition = new String(condition.getBytes("iso8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			count = usersMapper.selectUserCountByCondition(condition);
		}
		int totalPage = (int) Math.ceil(count / (limit * 1.0));
		return totalPage;
	}

	@Override
	public List<Users> selectUserByPageAndCondition(String currentPage, int limit, String condition,
			HttpServletRequest request) {
		List<Users> allUserList = null;
		int startPage = 0;
		if (currentPage == null || currentPage.equals("")) {
			currentPage = "1";
		}
		try {
			startPage = Integer.parseInt(currentPage);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (condition == null || condition.equals("")) {
			request.getSession().removeAttribute("condition");
			allUserList = usersMapper.selectAllUserByPage((startPage - 1) * limit, limit);
		} else {
			String newCondition = Utils.judgeChiese(condition);
			request.getSession().setAttribute("condition", newCondition);
			allUserList = usersMapper.selectAllUserByPageAndCondition((startPage - 1) * limit, limit, newCondition);
		}
		// List<Users> usersList =
		// usersMapper.selectAllUserByPageAndCondition(startPage, limit,condition);
		return allUserList;
	}

	/*
	 * @Override public List<Users> selectAllUserByPage(int startPage, int limit) {
	 * List<Users> usersList=usersMapper.selectAllUserByPage(startPage, limit);
	 * 
	 */
	@Override
	public String updateStatus(int id, String status,String updateTime) {
		int updateStatus = usersMapper.updateStatus(id, status,updateTime);
		if (updateStatus == 1) {
			return "更新成功";
		} else {
			return "更新失败";
		}
	}

	@Override
	public Admin verifyAdmin(Admin admin) {
		return usersMapper.selectAdmin(admin);
	}

	@Override
	public String updateUsernameById(int id,String username) {
		if(usersMapper.selectUsername(username)!=null) {
			return "已存在";
		}else if(usersMapper.updateUsernameById(username, id)==1) {
			return "修改成功";
		}else{
			return "修改失败";
		}
	}

	@Override
	public String updatePhoneById(int id, String phone) {
		if(usersMapper.selectPhone(phone)!=null) {
			return "已存在";
		}else if(usersMapper.updatePhoneById(phone, id)==1) {
			return "修改成功";
		}else{
			return "修改失败";
		}
	}

	@Override
	public String updateEmailById(int id, String email) {
		if(usersMapper.updateEmailById(email, id)==1) {
			return "修改成功";
		}else{
			return "修改失败";
		}
	}

	@Override
	public String deleteAddress(int addressId) {
		if(addressMapper.deleteAddressById(addressId)==1) {
			return "删除成功";
		}else {
			return "删除失败";
		}
	}

/*	@Override
	public Admin verifyAdminByUsername(Admin admin) {
		// TODO Auto-generated method stub
		return usersMapper.selectAdminByUsername(admin);
	}*/
 
}
