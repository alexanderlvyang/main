package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.Admin;
import org.yang.pojo.Users;

public interface UsersMapper {
	int insertUsers(Users user);

	Users selectPhone(String phone);

	Users selectUsername(String username);

	Users selectUser(Users user);

	List<Users> selectAllUserByPageAndCondition(@Param("startPage") int startPage, @Param("limit") int limit,
			@Param("condition") String condition);

	int selectUserCountByCondition(@Param("condition") String condition);

	int selectUserCount();

	List<Users> selectAllUserByPage(@Param("startPage") int startPage, @Param("limit") int limit);

	int updatePassword(@Param("newPassword") String newPassword, @Param("username") String username,@Param("updateTime")String updateTime);

	int updateStatus(@Param("id") int id, @Param("status") String status,@Param("updateTime")String updateTime);
	
	Admin selectAdmin(Admin admin);
	//Admin selectAdminByUsername(Admin admin);
	int updateUsernameById(@Param("username") String username,@Param("id")int id);
	int updatePhoneById(@Param("phone") String phone,@Param("id")int id);
	int updateEmailById(@Param("email") String email,@Param("id")int id);
}
