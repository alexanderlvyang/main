package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.StaffOpertaion;
import javabean.Staff;
import util.DbManager;

public class StaffOperationImpl implements StaffOpertaion {
	PreparedStatement prep=null;
	ResultSet result=null;
	//登陆验证
	@Override
	public Staff LoginVrify(String staff_username, String staff_password) {
		if(staff_username==null||staff_password==null){
			return null;
		}
		String sql="select * from staff where staff_username=? and staff_password=?";
		Connection conn=DbManager.getConnection();//获取数据库连接
		Staff staff=new Staff();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, staff_username);
			prep.setString(2, staff_password);
			result=prep.executeQuery();
			if(result.next()){
				staff.setStaffId(result.getInt("staff_id"));
				staff.setStaffName(result.getString("staff_name"));
				staff.setStaffUsername(result.getString("staff_username"));
				staff.setStaffPassword(result.getString("staff_password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		
		return staff;
	}

}
