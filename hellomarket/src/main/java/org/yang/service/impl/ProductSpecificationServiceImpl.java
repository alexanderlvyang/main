package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.OperationLogsMapper;
import org.yang.mapper.ProductMapper;
import org.yang.mapper.ProductSpecificationMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.OperationLogs;
import org.yang.pojo.ProductSpecification;
import org.yang.service.ProductSpecificationService;
import org.yang.utils.Utils;
@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {
	@Resource
	private ProductSpecificationMapper productSpecificationMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Resource
	private ProductMapper productMapper;
	@Override
	public List<ProductSpecification> showSpecificationByProductId(int productId,String productColor) {
		return productSpecificationMapper.selectSpecificationByProductId(productId,productColor);
	}
	@Override
	public String addSpecification(ProductSpecification productSpecification, HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		productSpecification.setCreateTime(currentTime);
		productSpecification.setStatus("上架");
		productSpecification.setUpdateTime(currentTime);
		/*String thumbnail=productSpecification.getProduct_thumbnail();
		thumbnail=thumbnail.substring(thumbnail.lastIndexOf("."), thumbnail.length());
		String thumbnailName=UUID.randomUUID()+thumbnail;
		productSpecification.setProduct_thumbnail(thumbnailName);*/
		String maxPrice = productSpecificationMapper.selectMaxPriceByProductId(productSpecification.getProduct_id());
		String minPrice = productSpecificationMapper.selectMinPriceByProductId(productSpecification.getProduct_id());
		String priceInterval="";
		if(minPrice==null&&maxPrice==null) {
			priceInterval=0+"~"+productSpecification.getPrice();
		}else if(minPrice.equals(maxPrice)){
				if(productSpecification.getPrice()>=Double.parseDouble(maxPrice)) {
					priceInterval=minPrice+"~"+productSpecification.getPrice();
				}else {
					priceInterval=productSpecification.getPrice()+"~"+maxPrice;
				}
			}else {
				if(productSpecification.getPrice()>=Double.parseDouble(maxPrice)) {
					priceInterval=minPrice+"~"+productSpecification.getPrice();
				}
				if(productSpecification.getPrice()<=Double.parseDouble(minPrice)) {
					priceInterval=productSpecification.getPrice()+"~"+String.valueOf(maxPrice);
				}
			}
		productMapper.updatePrice(priceInterval, productSpecification.getProduct_id());
		String content="添加了id为"+productSpecification.getProduct_id()+"商品的"+productSpecification.getProduct_color()+"颜色和"+productSpecification.getProduct_specification()+"规格";
		int insertStatus = productSpecificationMapper.insertSpecification(productSpecification);
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
	public String updateSpecificationStatus(String status, int specification_id,HttpServletRequest request) {
		Admin admin =(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="更新specificationid为"+specification_id+"的状态为"+status;
		int updateStatus = productSpecificationMapper.updateSpecificationStatus(status, specification_id,currentTime);
		if(updateStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新失败";
		}
	}
/*	@Override
	public String updateSpecificationBySpecificationId(int specification_id, HttpServletRequest request) {
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		return null;
	}*/
	@Override
	public ProductSpecification selectSpecificationBySpecificationId(int specification_id) {
		return productSpecificationMapper.selectSpecificationBySpecificationIdToUpdate(specification_id);
	}
	@Override
	public String updateSpecification(ProductSpecification productSpecification, HttpServletRequest request) {
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		productSpecification.setUpdateTime(currentTime);
		String content="更新id为"+productSpecification.getSpecification_id()+"的规格";
		int updateStatus = productSpecificationMapper.updateSpecification(productSpecification);
		if(updateStatus>0) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新失败",admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新失败";
		}
	}
	@Override
	public ProductSpecification selectSpecificationByColorAndSpecification(String color, String specification,String productId) {
		return productSpecificationMapper.selectSpecificationByColorAndSpecification(color, specification,Integer.parseInt(productId));
	}
	@Override
	public ProductSpecification selectSpecificationBySpecification(String specification, String productId) {
		return productSpecificationMapper.selectSpecificationBySpecfication(specification, Integer.parseInt(productId));
	}
	@Override
	public List<String> selectColorByProductId(String productId) {
		return productSpecificationMapper.selectColorByProductId(Integer.parseInt(productId));
	}
	@Override
	public List<String> selectSpecificationByProductId(String productId) {
		return productSpecificationMapper.selectProductSpecificationByProductId(Integer.parseInt(productId));
	}
	@Override
	public List<String> selectNotInColorBySpecification(String specification,String productId) {
		return productSpecificationMapper.selectNotInColorBySpecification(specification,Integer.parseInt(productId));
	}
	@Override
	public List<String> selectNotInSpecificationByColor(String color,String productId) {
		return	productSpecificationMapper.selectNotInSpecificationByColor(color,Integer.parseInt(productId));
	}
	@Override
	public List<String> selectSpecificationIdByProductId(String productId) {
		List<String> specificationIdList = productSpecificationMapper.selectSpecficationIdByProductId(productId);
		return specificationIdList;
	}
	
}
