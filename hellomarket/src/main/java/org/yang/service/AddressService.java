package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Addressee;

public interface AddressService {
	List<Addressee> showAddressById(HttpServletRequest request);
	String addAddress(Addressee address,HttpServletRequest request);
}	
