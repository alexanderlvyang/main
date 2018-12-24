package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Brand;

public interface BrandService {
	List<Brand> showBrandByPageAndCondition(String condition, String startPage, int limit,HttpServletRequest request);
	int getTotalPage(int limit,String condition);
	String addBrand(Brand brand,HttpServletRequest request);
	String deleteBrand(int brand_id,HttpServletRequest request);
	Brand selectBrandByName(String brandName);
	List<Brand> showBrand();
}
