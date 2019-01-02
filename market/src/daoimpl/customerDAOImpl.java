package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.customerDAO;
import javabean.Customer;
import util.DbManager;

public class customerDAOImpl implements customerDAO {

	@Override
	public Customer findCustomerById(int customerid) {
		String sql="select * from customer where customer=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		Customer customer=new Customer();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, customerid);
			result=prep.executeQuery();
			while(result.next()){
				customer.setCustomerId(result.getInt("customer_id")).setCustomerName(result.getString("customer_name"))
				;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

}
