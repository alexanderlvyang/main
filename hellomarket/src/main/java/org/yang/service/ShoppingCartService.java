package org.yang.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.yang.pojo.ShoppingCart;

public interface ShoppingCartService {
	int addShoppingCart(ShoppingCart shoppingCart);
	List<ShoppingCart> showShoppingCart(int userId);
	void addShoppingCartNotUser(String productId,String color,String specification,String count,HttpServletRequest request,HttpServletResponse response);
	void showCart(HttpServletRequest request,Model model);
	String deleteShoppingCart(String specificationId,HttpServletRequest request,HttpServletResponse response);
}
