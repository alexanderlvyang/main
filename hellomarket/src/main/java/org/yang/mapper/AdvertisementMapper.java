package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.Advertisement;

public interface AdvertisementMapper {
	List<Advertisement> selectAdvertisementBytype(String advertise_type);
	int insertAdvertisement(Advertisement advertisement);
	int updateAdvertisementById(Advertisement advertisement);
	int deleteAdvertisementById(int advertise_id);
	Advertisement selectAdvertisementById(int advertise_id);
	List<Advertisement> selectAdvertisementByRangeAndTime(@Param("advertise_range")String advertise_range,@Param("currentTime")String currentTime,@Param("type")String type);
	int selectCountByRangeAndType(@Param("advertise_range")String advertise_range,@Param("type")String type);
}
