package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.Product;
import org.yang.pojo.ProductInfo;

public interface ProductService {
	List<Product> showProduct(String condition,String startPage,int limit,HttpServletRequest request);
	int getTotalPage(String condition,int limit);
	String addProduct(Product product,HttpServletRequest request);
	String updateStatus(int product_id,String product_status,HttpServletRequest requests);
	Product showProductById(int product_id);
	String updateProductById(Product product,HttpServletRequest request);
	List<ProductInfo> searchProduct(String condition,String startPage,HttpServletRequest request);
	int getProductInfoTotalPage(String condition);
	List<ProductInfo> selectProductInfoByProductId(int productId);
	List<ProductInfo> selectProductInfoBySpecification(String specification);
	ProductInfo selectProductInfoByProductIdAndSpecificationAndColor(String productId,String color,String specification);
	ProductInfo selectProductInfoByProductIdAndSpecification(String productId,String specification);
}
