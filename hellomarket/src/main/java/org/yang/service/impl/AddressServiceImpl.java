package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.AddressMapper;
import org.yang.pojo.Addressee;
import org.yang.pojo.Users;
import org.yang.service.AddressService;
import org.yang.utils.Utils;
@Service
public class AddressServiceImpl implements AddressService{
	@Resource
	private AddressMapper addressMapper;
	@Override
	public List<Addressee> showAddressById(HttpServletRequest request) {
		Users user =(Users) request.getSession().getAttribute("user");
		return addressMapper.selectAddressById(user.getId()) ;
	}
	@Override
	public String addAddress(Addressee address, HttpServletRequest request) {
		Users user =(Users) request.getSession().getAttribute("user");
		address.setCreateTime(Utils.getCurrentDate());
		address.setId(user.getId());
		int insertStatus = addressMapper.insertAddress(address);
		if(insertStatus==1) {
			return "添加成功";
		}else {
			return "添加失败";
		}
		
	}

}
