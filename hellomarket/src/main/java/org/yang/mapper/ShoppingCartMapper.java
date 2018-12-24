package org.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.ProductInfo;
import org.yang.pojo.ShoppingCart;

public interface ShoppingCartMapper {
	int insertShoppingCart(ShoppingCart shoppingCart);
	List<ShoppingCart> selectShoppingCartById(int id);
	List<ProductInfo> selectProductInfoById(int id);
	ShoppingCart selectShoppingCartBySpecificationId(int specificationId);
	int updateShoppingCartBySpecificationId(@Param("count")int count,@Param("specificationId")int specificationId);
	int deleteShoppingCartBySpecificationId(int specificationId);
}
