package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.Product;
import org.yang.pojo.ProductInfo;

public interface ProductMapper {
	List<Product> selectAllProductByPage(@Param("startPage")int startPage,@Param("limit")int limit);
	List<Product> selectAllproductByPageAndCondition(@Param("condition")String condition,@Param("startPage")int startPage,@Param("limit")int limit);
	int insertProduct(Product product);
	int selectCountByCondition(String condition);
	int selectCount();
	int updateStatus( @Param("product_id") int product_id,@Param("product_status") String product_status);
	int updatePrice(@Param("product_price")String product_price,@Param("product_id")int product_id);
	Product selectProductById(int product_id);
	int updateProductById(Product product);
	List<ProductInfo> selectProductInfo(@Param("condition")String condition,@Param("category")int category,@Param("brand")int brand,@Param("startPage")int startPage,@Param("limit") int limit);
	List<ProductInfo> selectProductInfoByFirstCategory(@Param("condition")String condition,@Param("category")String category,@Param("brand")int brand,@Param("startPage")int startPage,@Param("limit") int limit);
	List<ProductInfo> selectProductInfoBySecondCategory(@Param("condition")String condition,@Param("category")String category,@Param("brand")int brand,@Param("startPage")int startPage,@Param("limit") int limit);
	int selectProductInfoCount(@Param("condition")String condition,@Param("category")int category,@Param("brand")int brand);
	int selectProductInfoCountByFirstCategory(@Param("condition")String condition,@Param("category")String category,@Param("brand")int brand);
	int selectProductInfoCountBySecondCategory(@Param("condition")String condition,@Param("category")String category,@Param("brand")int brand);
	List<ProductInfo> selectProductInfoByProductId(int productId);
	List<ProductInfo> selectProductInfoBySpecification(String specification);
	ProductInfo selectProductInfoByProductIdAndSpecification(@Param("productId")int productId,@Param("specification")String specification);
	ProductInfo selectProductInfoByProductIdAndSpecificationAndColor(@Param("productId")int productId,@Param("specification")String specification,@Param("color")String color);
	ProductInfo selectProductInfoBySpecificationId(int specificationId);
	ProductInfo selectProductInfoByProductSpecificationId(int specificationId);
	
}
