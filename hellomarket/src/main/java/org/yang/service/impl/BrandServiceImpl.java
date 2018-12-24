package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.BrandMapper;
import org.yang.mapper.OperationLogsMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.Brand;
import org.yang.pojo.OperationLogs;
import org.yang.service.BrandService;
import org.yang.utils.Utils;
@Service
public class BrandServiceImpl implements BrandService {
	@Resource
	private BrandMapper brandMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Override
	public List<Brand> showBrandByPageAndCondition(String condition, String startPage, int limit,HttpServletRequest request) {
		List<Brand> brandList =null;
		if(condition==null&&startPage==null&&limit==0) {
			brandList=brandMapper.selectAllBrand();
		}else {
			if(startPage==null||startPage.equals("")) {
				startPage="1";
			}
			if(condition==null||condition.equals("")) {
				request.getSession().removeAttribute("condition");
				brandList= brandMapper.selectAllBrandByPage((Integer.parseInt(startPage)-1)*limit, limit);
			}else {
				condition = Utils.judgeChiese(condition);
				request.getSession().setAttribute("condition", condition);
				brandList=brandMapper.selectBrandByConditionAndPage(condition, (Integer.parseInt(startPage)-1)*limit, limit);
			}
		}
		return brandList;
	}

	@Override
	public int getTotalPage(int limit,String condition) {
		int count=0;
		if(condition==null||condition.equals("")) {
			count=brandMapper.selectCount();
		}else {
			condition = Utils.judgeChiese(condition);
			count=brandMapper.selectCountByCondition(condition);
		}
		int totalPage = (int) Math.ceil(count / (limit * 1.0));
		return totalPage;
	}

	@Override
	public String addBrand(Brand brand,HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String brand_name = brand.getBrand_name();
		String pinYin = Utils.getPinYin(brand_name);
		brand.setBrand_pinyin(pinYin);
		String currentTime = Utils.getCurrentTime();
		brand.setBrand_joinTime(currentTime);
		int insertStatus = brandMapper.insertBrand(brand);
		String content="添加名称为"+brand.getBrand_name()+"的品牌";
		if(insertStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "添加成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "添加失败";
		}
	}

	@Override
	public String deleteBrand(int brand_id,HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="删除id为"+brand_id+"的品牌";
		int deleteStatus = brandMapper.deleteBrand(brand_id);
		if(deleteStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除失败";
		}
	}

	@Override
	public Brand selectBrandByName(String brandName) {
		Brand brand = brandMapper.selectBrandByName(brandName);
		return brand;
	}

	@Override
	public List<Brand> showBrand() {
		return brandMapper.selectAllBrand();
	}

}
