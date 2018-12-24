package org.yang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.Advertisement;
import org.yang.service.AdvertisementService;

@Controller
public class AdvertisementController {
	@Resource
	private AdvertisementService advertisementServiceImpl;
	@RequestMapping("advertisementManage.do")
	public String advertisementManage(Model model,String advertise_type,HttpServletRequest request) {
		model.addAttribute("advertisementList", advertisementServiceImpl.showAdvertisementByType(advertise_type,request));
		return "WEB-INF/pages/advertisement.jsp";
	}
	@RequestMapping("addAdvertisement.do")
	@ResponseBody
	public String addAdvertisement(Advertisement advertisement,HttpServletRequest request) {
		return advertisementServiceImpl.addAdvertisement(advertisement, request);
	}
	@RequestMapping("deleteAdvertisement.do")
	@ResponseBody
	public String deleteAdvertisement(int advertise_id,HttpServletRequest request) {
		return advertisementServiceImpl.deleteAdvertisementById(advertise_id, request);
	}
	@RequestMapping("showAdvertiseById")
	public String showAdvertiseById(Model model,int advertise_id) {
		model.addAttribute("advertisement", advertisementServiceImpl.showAdvertisementById(advertise_id));
		return "WEB-INF/pages/updateadvertisement.jsp";
	}
	@RequestMapping("updateAdvertisement.do")
	@ResponseBody
	public String updateAdvertisement(Advertisement advertisement,HttpServletRequest request) {
		return advertisementServiceImpl.updateAadvertisementById(advertisement, request);
	}
}
