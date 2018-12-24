package org.yang.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yang.service.OrdersService;

@Controller
public class MyOrderController {
	@Resource
	private OrdersService ordersServiceImpl;
	@RequestMapping("confirmGetGoods")
	public String confirmGetGoods(String orderId) {
		String order_status="已收货";
		ordersServiceImpl.updateOrderStatusByOrderId(orderId, order_status);
		return "myOrder";
	}
	@RequestMapping("goComment")
	public String goComment(String orderId) {
		String productId = ordersServiceImpl.getProductIdByOrderId(orderId);
		return "productDetail?productId="+productId;
	}
	
}
