package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.ProductSpecification;

public interface ProductSpecificationService {
	List<ProductSpecification> showSpecificationByProductId(int productId,String productColor);
	String addSpecification(ProductSpecification productSpecification,HttpServletRequest request);
	String updateSpecificationStatus(String status,int specification_id,HttpServletRequest request);
	ProductSpecification selectSpecificationBySpecificationId(int specification_id);
	String updateSpecification(ProductSpecification productSpecification,HttpServletRequest request);
	ProductSpecification selectSpecificationByColorAndSpecification(String color,String specification,String productId);
	ProductSpecification selectSpecificationBySpecification(String specification,String productId);
	List<String> selectColorByProductId(String productId);
	List<String> selectSpecificationByProductId(String productId);
	List<String> selectNotInColorBySpecification(String specification,String productId);
	List<String> selectNotInSpecificationByColor(String color,String productId);
	List<String> selectSpecificationIdByProductId(String productId);
}
