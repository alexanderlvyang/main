package org.yang.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.Brand;
import org.yang.service.BrandService;

@Controller
public class BrandController {
	@Resource
	private BrandService brandServiceImpl;
	@RequestMapping("brandManage.do")
	public String showAllBrandByPage(Model model,String condition,String startPage,HttpServletRequest request) {
		int limit=10;
		List<Brand> brandList = brandServiceImpl.showBrandByPageAndCondition(condition, startPage, limit, request);
		int totalPage = brandServiceImpl.getTotalPage(limit, condition);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("brandList", brandList);
		return "WEB-INF/pages/brandmanage.jsp";
	}
	@RequestMapping("addBrand.do")
	@ResponseBody
	public String addBrand(Brand brand,HttpServletRequest request) {
		String addStatus = brandServiceImpl.addBrand(brand,request);
		return addStatus;
	}
	@RequestMapping("deleteBrand.do")
	@ResponseBody
	public String deleteBrand(int brand_id,HttpServletRequest request) {
		return brandServiceImpl.deleteBrand(brand_id,request);
	}
}
