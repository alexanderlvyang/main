package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.Brand;

public interface BrandMapper {
	List<Brand> selectAllBrandByPage(@Param("startPage") int startPage,@Param("limit") int limit);
	List<Brand> selectBrandByConditionAndPage(@Param("condition")String condition,@Param("startPage") int startPage,@Param("limit") int limit);
	int selectCountByCondition(@Param("condition")String condition);
	int selectCount();
	List<Brand> selectAllBrand();
	int insertBrand(Brand brand);
	int deleteBrand(int brand_id);
	Brand selectBrandByName(String brandName);
}
