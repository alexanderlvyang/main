package org.yang.mapper;

import java.util.List;

import org.yang.pojo.Addressee;

public interface AddressMapper {
	List<Addressee> selectAddressById(int id);
	int deleteAddressById(int addressId);
	int insertAddress(Addressee address);
}
