package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.ProductSpecification;

public interface ProductSpecificationMapper {
	List<ProductSpecification> selectSpecificationByProductId(@Param("productId")int productId,@Param("productColor")String productColor);
	int insertSpecification(ProductSpecification productSpecification);
	int updateSpecificationStatus(@Param("status")String status,@Param("specification_id")int specification_id, @Param("updateTime")String updateTime);
	String selectMaxPriceByProductId(int product_id);
	String selectMinPriceByProductId(int product_id);
	ProductSpecification selectSpecificationBySpecificationId(int specification_id);
	ProductSpecification selectSpecificationBySpecificationIdToUpdate(int specification_id);
	int updateSpecification(ProductSpecification productSpecification);
	ProductSpecification selectSpecificationByColorAndSpecification(@Param("productColor")String productColor,@Param("productSpecification")String productSpeciifcation,@Param("productId")int productId);
	ProductSpecification selectSpecificationBySpecfication(@Param("productSpecification")String productSpeciifcation,@Param("productId")int productId);
	List<String> selectColorByProductId(int productId);
	List<String> selectProductSpecificationByProductId(int productId);
	List<String> selectNotInSpecificationByColor(@Param("color")String color,@Param("productId")int productId);
	List<String> selectNotInColorBySpecification(@Param("specification")String specification,@Param("productId")int productId);
	List<String> selectSpecficationIdByProductId(String productId);
}
