package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.ProductImage;

public interface ProductImageService {
	List<ProductImage> showProductImage(int product_id);
	String deleteProductImageById(int productImage_id, HttpServletRequest request);
	String addProductImage(ProductImage productImage, HttpServletRequest request);
}
