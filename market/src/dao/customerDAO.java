package dao;

import javabean.Customer;

public interface customerDAO {
	public abstract Customer findCustomerById(int customerid);
}
