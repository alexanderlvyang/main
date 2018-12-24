package org.yang.mapper;

import java.util.List;

import org.yang.pojo.ProductImage;

public interface ProductImageMapper {
	List<ProductImage> selectProductImage(int product_id);
	int deleteProductImageById(int productImage_id);
	int insertProductImage(ProductImage productImage);
}
