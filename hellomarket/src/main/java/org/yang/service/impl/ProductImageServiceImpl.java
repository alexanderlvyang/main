package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.OperationLogsMapper;
import org.yang.mapper.ProductImageMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.OperationLogs;
import org.yang.pojo.ProductImage;
import org.yang.service.ProductImageService;
import org.yang.utils.Utils;
@Service
public class ProductImageServiceImpl implements ProductImageService{
	@Resource
	private ProductImageMapper productImageMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Override
	public List<ProductImage> showProductImage(int product_id) {
		return productImageMapper.selectProductImage(product_id);
	}

	@Override
	public String deleteProductImageById(int productImage_id, HttpServletRequest request) {
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="删除了id为"+productImage_id+"的图片";
		int deleteStatus = productImageMapper.deleteProductImageById(productImage_id);
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
	public String addProductImage(ProductImage productImage, HttpServletRequest request) {
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="添加了id为"+productImage.getProductImage_id()+"的图片";
		productImage.setCreateTime(currentTime);
		int insertStatus = productImageMapper.insertProductImage(productImage);
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

}
