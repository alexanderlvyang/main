package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Advertisement;

public interface AdvertisementService {
	List<Advertisement> showAdvertisementByType(String advertise_type,HttpServletRequest request);
	String addAdvertisement(Advertisement advertisement, HttpServletRequest request);
	String updateAadvertisementById(Advertisement advertisement, HttpServletRequest request);
	String deleteAdvertisementById(int advertise_id, HttpServletRequest request);
	Advertisement showAdvertisementById(int advertise_id);
	List<Advertisement> showAdvertiseByRangeAndTime(String advertise_range,String currentTime,String type);
}
